package demo.inloop.orders.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import demo.inloop.orders.AppContext;
import demo.inloop.orders.R;
import demo.inloop.orders.data.Contact;

/**
 * A fragment contains a new contact form.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class ContactAddFragment extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener interactionListener;
    private EditText contactNameEdit;
    private EditText contactPhoneEdit;

    public ContactAddFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_add, container, false);
        Button button = (Button) view.findViewById(R.id.new_contact_add);
        if (button != null) {
            button.setOnClickListener(this);
        }
        contactNameEdit = addResetTextWatcherToEditText((EditText) view.findViewById(R.id.new_contact_name));
        contactPhoneEdit = addResetTextWatcherToEditText((EditText) view.findViewById(R.id.new_contact_phone));

        return view;
    }

    /**
     * Resets any EditText errors after a character is written.
     *
     * @param et
     * @return
     */
    private EditText addResetTextWatcherToEditText(final EditText et) {
        if (et != null) {
            et.addTextChangedListener(new TextWatcher() {
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    et.setError(null);
                }

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                }

                @Override
                public void afterTextChanged(Editable editable) {
                }
            });
        }
        return et;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            interactionListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionListener = null;
    }

    @Override
    public void onClick(View view) {
        saveContact();
    }

    /**
     * Creates new contact object and sends it to a server.
     */
    private void saveContact() {
        if (checkInvalid()) {
            return;
        }

        final Contact contact = new Contact();
        contact.setName(contactNameEdit.getText().toString());
        contact.setPhone(contactPhoneEdit.getText().toString());

        String url = getString(R.string.url_contacts);

        JSONObject jsonBody;
        try {
            jsonBody = new JSONObject(new Gson().toJson(contact));
        } catch (JSONException ex) {
            Toast.makeText(AppContext.getContext(), R.string.error_contact_data, Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(
                url,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (interactionListener != null) {
                            interactionListener.onFragmentInteraction(contact);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AppContext.getContext(), R.string.error_sending, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        AppContext.getInstance().addToVolleyRequestQueue(jsonObjReq);
    }

    /**
     * Checks for invalid elements.
     * @return
     */
    private boolean checkInvalid() {
        boolean error = false;
        if (checkMinLength(contactNameEdit, R.string.error_empty_name, R.string.error_min_length_name, 5)) {
            error = true;
        }
        if (checkMinLength(contactPhoneEdit, R.string.error_empty_phone, R.string.error_min_length_phone, 5)) {
            error = true;
        }
        return error;
    }

    /**
     * Sets error and shows a toast message on empty text or less than minimum text length.
     * @param et
     * @param errorEmptyResourceId
     * @param errorShortResourceId
     * @param minLength
     * @return
     */
    private boolean checkMinLength(EditText et, int errorEmptyResourceId, int errorShortResourceId, int minLength) {
        String error = null;
        if (et.getText().length() == 0) {
            error = getString(errorEmptyResourceId);
        } else if (et.getText().length() < minLength) {
            error = getString(errorShortResourceId);
        }
        if (error != null) {
            et.setError(error);
            Toast.makeText(AppContext.getContext(), error, Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    /**
     * Interface for communication with the activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Contact contact);
    }
}

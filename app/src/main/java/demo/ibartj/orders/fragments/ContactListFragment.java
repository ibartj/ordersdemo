package demo.ibartj.orders.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.Arrays;

import demo.ibartj.orders.AppContext;
import demo.ibartj.orders.R;
import demo.ibartj.orders.data.Contact;
import demo.ibartj.orders.data.ContactList;
import demo.ibartj.orders.utils.GsonRequest;
import demo.ibartj.orders.utils.Network;

/**
 * Contains a list of contacts in a RecyclerView.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class ContactListFragment extends Fragment {
    private OnListFragmentInteractionListener interactionListener;
    private ContactsRecyclerViewAdapter recyclerViewAdapter;

    public ContactListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);
        if (view instanceof RecyclerView) {
            recyclerViewAdapter = new ContactsRecyclerViewAdapter(new ArrayList<Contact>(), interactionListener);
            ((RecyclerView) view).setAdapter(recyclerViewAdapter);
            getData((RecyclerView) view);
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            interactionListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        interactionListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        View view = getView();
        if (view instanceof RecyclerView) {
            getData((RecyclerView) view);
        }
    }

    /**
     * Downloads data from the server and updates the RecyclerViewAdapter. JSON format:
     * {"items": [{"id": "4845036374065152", "name": "Fddfffd", "phone": "+8788884"},...]}
     *
     * @param recyclerView
     */
    private void getData(final RecyclerView recyclerView) {
        if (!Network.isConnected()) {
            Toast.makeText(getContext(), R.string.error_network, Toast.LENGTH_SHORT).show();
            return;
        }
        String url = getString(R.string.url_contacts);

        GsonRequest<ContactList> jsonObjReq = new GsonRequest<>(
                url,
                ContactList.class,
                null,
                new Response.Listener<ContactList>() {
                    @Override
                    public void onResponse(ContactList response) {
                        recyclerViewAdapter = new ContactsRecyclerViewAdapter(Arrays.asList(response.getItems()), interactionListener);
                        recyclerView.swapAdapter(recyclerViewAdapter, true);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), R.string.error_response, Toast.LENGTH_SHORT).show();
            }
        });

        AppContext.getInstance().addToVolleyRequestQueue(jsonObjReq);
    }

    /**
     * Interface for communication with the activity.
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Contact contact);
    }
}

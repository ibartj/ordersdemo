package demo.inloop.orders.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import demo.inloop.orders.R;
import demo.inloop.orders.data.Contact;

/**
 * A fragment contains an info about the contact - phone number at the moment.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class ContactInfoFragment extends Fragment {
    private static final String ARG_CONTACT = "contact";

    private Contact contact;

    public ContactInfoFragment() {
    }

    /**
     * Creates new instance.
     *
     * @param contact
     * @return
     */
    public static ContactInfoFragment newInstance(Contact contact) {
        ContactInfoFragment fragment = new ContactInfoFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_CONTACT, contact);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contact = (Contact) getArguments().getSerializable(ARG_CONTACT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact_info, container, false);
        TextView phone = (TextView) v.findViewById(R.id.info_phone);
        if (contact != null && phone != null) {
            phone.setText(contact.getPhone());
        }
        return v;
    }
}

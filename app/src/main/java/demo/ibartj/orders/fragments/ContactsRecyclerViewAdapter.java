package demo.inloop.orders.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import demo.inloop.orders.AppContext;
import demo.inloop.orders.R;
import demo.inloop.orders.data.Contact;
import demo.inloop.orders.fragments.ContactListFragment.OnListFragmentInteractionListener;

/**
 * A RecyclerViewAdapter for the contact list.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class ContactsRecyclerViewAdapter extends RecyclerView.Adapter<ContactsRecyclerViewAdapter.ViewHolder> {

    private final List<Contact> mContacts;
    private final OnListFragmentInteractionListener mListener;

    public ContactsRecyclerViewAdapter(List<Contact> contacts, OnListFragmentInteractionListener listener) {
        mContacts = contacts;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contact_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mContact = mContacts.get(position);
        String url = mContacts.get(position).getPictureUrl();
        if (url != null) {
            holder.mImageView.setImageURI(preparePictureUrl(url));
        } else {
            holder.mImageView.setImageURI((String) null);
        }
        holder.mNameView.setText(mContacts.get(position).getName());
        holder.mPhoneView.setText(mContacts.get(position).getPhone());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mContact);
                }
            }
        });
    }

    /**
     * Prepends pictures that have no protocol at the start with a server URL.
     *
     * @param url
     * @return
     */
    private String preparePictureUrl(String url) {
        if (url != null && (url.startsWith("http:") || url.startsWith("https:"))) {
            return url;
        } else {
            return AppContext.getContext().getString(R.string.url_images, url);
        }
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final SimpleDraweeView mImageView;
        public final TextView mNameView;
        public final TextView mPhoneView;
        public Contact mContact;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (SimpleDraweeView) view.findViewById(R.id.contactListImage);
            mNameView = (TextView) view.findViewById(R.id.contactListName);
            mPhoneView = (TextView) view.findViewById(R.id.contactListPhone);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNameView.getText() + "'";
        }
    }
}

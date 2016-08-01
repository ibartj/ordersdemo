package demo.inloop.orders.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import demo.inloop.orders.R;
import demo.inloop.orders.data.Contact;
import demo.inloop.orders.fragments.ContactListFragment;

/**
 * Activity contains a list of contacts.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class ContactListActivity extends AppCompatActivity implements ContactListFragment.OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        getSupportFragmentManager().findFragmentById(R.id.fragment_contacts).setRetainInstance(true);
        setupToolbar();
    }

    /**
     * Sets up the toolbar/actionbar.
     */
    private void setupToolbar() {
        Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(false);
            bar.setDisplayShowHomeEnabled(true);
            bar.setDisplayShowTitleEnabled(true);
            bar.setTitle(R.string.activity_contact_list);
            bar.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                startActivity(new Intent(this, ContactAddActivity.class));

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * ContactListFragment.OnListFragmentInteractionListener implementation allows interaction with the fragment.
     * Starts new OrderListActivity on tap at a contact.
     *
     * @param contact
     * @see demo.inloop.orders.fragments.ContactListFragment.OnListFragmentInteractionListener
     * @see OrderListActivity
     */
    @Override
    public void onListFragmentInteraction(Contact contact) {
        Intent intent = new Intent(this, OrderListActivity.class);
        intent.putExtra(OrderListActivity.EXTRA_CONTACT, contact);
        startActivity(intent);
    }
}

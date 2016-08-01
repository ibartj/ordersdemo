package demo.inloop.orders.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import demo.inloop.orders.R;
import demo.inloop.orders.data.Contact;
import demo.inloop.orders.fragments.ContactInfoFragment;
import demo.inloop.orders.fragments.OrderListFragment;

/**
 * Activity contains a list of orders for given contact.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class OrderListActivity extends AppCompatActivity {
    public final static String EXTRA_CONTACT = "contact";
    private Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        receiveExtras();
        if(savedInstanceState==null) {
            setupContactInfoFragment();
            setupOrdersFragment();
        }
        setupToolbar();
    }

    /**
     * Receives parameters.
     */
    private void receiveExtras() {
        contact = (Contact) getIntent().getSerializableExtra(EXTRA_CONTACT);
    }

    /**
     * Instantiates ContactInfoFragment - info about the contact.
     */
    private void setupContactInfoFragment() {
        ContactInfoFragment fragment = ContactInfoFragment.newInstance(contact);
        fragment.setRetainInstance(true);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contact_info, fragment);
        ft.commit();
    }

    /**
     * Instantiates OrderListFragment - a list of orders.
     */
    private void setupOrdersFragment() {
        OrderListFragment fragment = OrderListFragment.newInstance(contact);
        fragment.setRetainInstance(true);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.contact_orders, fragment);
        ft.commit();
    }

    /**
     * Sets up the toolbar/actionbar.
     */
    private void setupToolbar() {
        Toolbar actionbar = (Toolbar) findViewById(R.id.actionbar);
        setSupportActionBar(actionbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
            bar.setDisplayShowTitleEnabled(true);
            bar.setDisplayUseLogoEnabled(true);
            bar.setHomeButtonEnabled(true);
            bar.setTitle(contact.getName());
            bar.show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

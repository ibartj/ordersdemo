package demo.ibartj.orders.activities;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import demo.ibartj.orders.R;
import demo.ibartj.orders.data.Contact;
import demo.ibartj.orders.fragments.ContactAddFragment;

/**
 * Activity contains the new contact form.
 *
 * @author Jan Bartovský
 * @version %I%, %G%
 */
public class ContactAddActivity extends AppCompatActivity implements ContactAddFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_add);
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
            bar.setDisplayHomeAsUpEnabled(true);
            bar.setDisplayShowHomeEnabled(true);
            bar.setDisplayShowTitleEnabled(true);
            bar.setDisplayUseLogoEnabled(true);
            bar.setHomeButtonEnabled(true);
            bar.setTitle(R.string.activity_contact_add);
            bar.show();
        }
    }

    /**
     * ContactAddFragment.OnListFragmentInteractionListener implementation allows interaction with the fragment.
     *
     * @param contact
     * @see demo.ibartj.orders.fragments.ContactAddFragment.OnFragmentInteractionListener
     */
    @Override
    public void onFragmentInteraction(Contact contact) {
        NavUtils.navigateUpFromSameTask(this);
    }
}

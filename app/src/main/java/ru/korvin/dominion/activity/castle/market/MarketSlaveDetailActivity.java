package ru.korvin.dominion.activity.castle.market;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;

import ru.korvin.dominion.R;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

/**
 * An activity representing a single MarketSlave detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MarketSlaveListActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link MarketSlaveDetailFragment}.
 */
public class MarketSlaveDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_slave_detail);

        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(MarketSlaveDetailFragment.ARG_SLAVE_ID,
                    getIntent().getExtras().getInt(MarketSlaveDetailFragment.ARG_SLAVE_ID));
            MarketSlaveDetailFragment fragment = new MarketSlaveDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .add(R.id.market_slave_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpFromSameTask(this);
            //navigateUpTo(new Intent(this, MarketSlaveListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

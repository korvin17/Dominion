package ru.korvin.dominion.activity.castle.market;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

/**
 * An activity representing a list of MarketSlaves. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link MarketSlaveDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link MarketSlaveListFragment} and the item details
 * (if present) is a {@link MarketSlaveDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link ru.korvin.dominion.activity.castle.market.MarketSlaveListFragment.MarketCallbacks} interface
 * to listen for item selections.
 */
public class MarketSlaveListActivity extends Activity
        implements MarketSlaveListFragment.MarketCallbacks,MarketSlaveDetailFragment.Callback {
    private static final String STATE_ACTIVATED_POSITION = "activated_position";
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market_slave_list);
        // Show the Up button in the action bar.
        getActionBar().setDisplayHomeAsUpEnabled(true);

        if (findViewById(R.id.market_slave_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((MarketSlaveListFragment) getFragmentManager()
                    .findFragmentById(R.id.market_slave_list))
                    .setActivateOnItemClick(true);
            int active_position = savedInstanceState != null ? savedInstanceState.getInt(STATE_ACTIVATED_POSITION, 0) : 0;
            ((MarketSlaveListFragment) getFragmentManager()
                    .findFragmentById(R.id.market_slave_list))
                    .initPosition(active_position);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Callback method from {@link ru.korvin.dominion.activity.castle.market.MarketSlaveListFragment.MarketCallbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onSlaveSelected(Person slave) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(MarketSlaveDetailFragment.ARG_SLAVE_ID, slave.getId());
            MarketSlaveDetailFragment fragment = new MarketSlaveDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.market_slave_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, MarketSlaveDetailActivity.class);
            detailIntent.putExtra(MarketSlaveDetailFragment.ARG_SLAVE_ID, slave.getId());
            startActivity(detailIntent);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        int active_position = ((MarketSlaveListFragment) getFragmentManager()
                .findFragmentById(R.id.market_slave_list))
                .getActivatedPosition();
        outState.putInt(STATE_ACTIVATED_POSITION, active_position);
    }

    @Override
    public boolean buyPerson(Person person) {
        ((MarketSlaveListFragment) getFragmentManager()
                .findFragmentById(R.id.market_slave_list)).buyPerson(person);
        return true;
    }
}

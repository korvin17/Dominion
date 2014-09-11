package ru.korvin.dominion.activity.girlinfo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import ru.korvin.dominion.R;


/**
 * An activity representing a list of GirlsInfo. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link GirlInfoDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link GirlInfoListFragment} and the item details
 * (if present) is a {@link GirlInfoDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link GirlInfoListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class GirlInfoListActivity extends Activity
        implements GirlInfoListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_girl_info_list);
        // getActionBar().setHomeButtonEnabled(true);
        if (findViewById(R.id.girlinfo_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((GirlInfoListFragment) getFragmentManager()
                    .findFragmentById(R.id.girlinfo_list))
                    .setActivateOnItemClick(true);
        }
        //onSlaveSelected();
        // TODO: If exposing deep links into your app, handle intents here.
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.girlinfo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        onItemSelected("1");
    }

    /**
     * Callback method from {@link GirlInfoListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(GirlInfoDetailFragment.ARG_ITEM_ID, id);
            GirlInfoDetailFragment fragment = new GirlInfoDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.girlinfo_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, GirlInfoDetailActivity.class);
            detailIntent.putExtra(GirlInfoDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}

package ru.korvin.dominion.activity.castle.room;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import android.view.MenuItem;
import android.view.View;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;

import static android.support.v4.app.NavUtils.navigateUpFromSameTask;

/**
 * An activity representing a list of Rooms. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RoomDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link WorkRoomListFragment} and the item details
 * (if present) is a {@link RoomDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link WorkRoomListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class WorkRoomMainFragment extends Fragment
        implements WorkRoomListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    public static WorkRoomMainFragment newInstance() {
        WorkRoomMainFragment fragment = new WorkRoomMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    /*    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_work_room_list);*/
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        // Show the Up button in the action bar.
        if (view.findViewById(R.id.work_room_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((WorkRoomListFragment) getFragmentManager()
                    .findFragmentById(R.id.room_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link WorkRoomListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onRoomSelected(Room room) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            //arguments.putString(RoomDetailFragment.ARG_ITEM_ID, id);
            RoomDetailFragment fragment = new RoomDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.work_room_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(getActivity(), RoomDetailActivity.class);
            //detailIntent.putExtra(RoomDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}

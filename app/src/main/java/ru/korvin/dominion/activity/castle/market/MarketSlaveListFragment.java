package ru.korvin.dominion.activity.castle.market;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import ru.korvin.dominion.activity.main.MainTabActivity;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.mechanic.baseObject.castle.room.market.Market;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;

/**
 * A list fragment representing a list of MarketSlaves. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link MarketSlaveDetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link ru.korvin.dominion.activity.castle.market.MarketSlaveListFragment.MarketCallbacks}
 * interface.
 */
public class MarketSlaveListFragment extends ListFragment {
    private MarketCallbacks mCallbacks = null;
    private int mActivatedPosition = ListView.INVALID_POSITION;
    private ArrayAdapter<Person> adapter;
    private Market market;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int roomId = getActivity().getIntent().getExtras().getInt(MainTabActivity.EXTRA_ROOM_ID);
        market = (Market) GameApplication.getDefaultGameApplication().getServer().getRoomWithID(roomId);
        adapter = new ArrayAdapter<Person>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, market.getAvailableSlave()
        );
        // TODO: replace with a real list adapter.
        setListAdapter(adapter);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof MarketCallbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (MarketCallbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    public void buyPerson(Person person) {
        adapter.remove(person);

    }

    public interface MarketCallbacks {
        public void onSlaveSelected(Person slave);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        if (mCallbacks != null)
            mCallbacks.onSlaveSelected(adapter.getItem(position));
    }

    public void initPosition(int position) {
        this.setActivatedPosition(position);
        this.onListItemClick(null, null, position, 0);
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }
        mActivatedPosition = position;
    }

    public int getActivatedPosition() {
        return mActivatedPosition;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MarketSlaveListFragment() {
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }
}

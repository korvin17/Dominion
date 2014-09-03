package ru.korvin.dominion.activity.castle.market;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.korvin.dominion.R;
import ru.korvin.dominion.activity.castle.stats.GirlStatsInfoFragment;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;

/**
 * A fragment representing a single MarketSlave detail screen.
 * This fragment is either contained in a {@link MarketSlaveListActivity}
 * in two-pane mode (on tablets) or a {@link MarketSlaveDetailActivity}
 * on handsets.
 */
public class MarketSlaveDetailFragment extends Fragment {
    public static final String ARG_SLAVE_ID = "slave_id";
    private Person slave;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MarketSlaveDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_SLAVE_ID)) {
            slave = GameApplication.getDefaultGameApplication().getServer().getPersonWithID(getArguments().getInt(ARG_SLAVE_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_marketslave_detail, container, false);

        if (slave != null) {
            GirlStatsInfoFragment statsInfoFragment = (GirlStatsInfoFragment) getActivity().getFragmentManager().findFragmentById(R.id.market_slave_girl_stats);
            statsInfoFragment.setPerson(slave);
        }

        return rootView;
    }
}

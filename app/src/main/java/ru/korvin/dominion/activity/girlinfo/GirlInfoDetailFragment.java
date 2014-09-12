package ru.korvin.dominion.activity.girlinfo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ru.korvin.dominion.R;
import ru.korvin.dominion.activity.castle.stats.GirlStatsInfoFragment;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.server.GameConst;

/**
 * A fragment representing a single GirlInfo detail screen.
 * This fragment is either contained in a {@link GirlInfoListMainFragment}
 * in two-pane mode (on tablets) or a {@link GirlInfoDetailActivity}
 * on handsets.
 */
public class GirlInfoDetailFragment extends Fragment {
    private Person slave;
    private ImageView mImageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(GameConst.ARG_SLAVE_ID)) {
            slave = GameApplication.getDefaultGameApplication().getServer().getPersonWithID(getArguments().getInt(GameConst.ARG_SLAVE_ID));
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_girl_info_detail, container, false);
        mImageView= (ImageView) rootView.findViewById(R.id.slave_image_view);

        if (slave != null) {
            GirlStatsInfoFragment statsInfoFragment = GirlStatsInfoFragment.newInstance(slave);
            getFragmentManager().beginTransaction()
                    .replace(R.id.slave_girl_stats_container, statsInfoFragment)
                    .commit();
            mImageView.setImageResource(slave.getImageMarketSlave());
        }

        return rootView;
    }
    public GirlInfoDetailFragment() {
    }


}

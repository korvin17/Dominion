package ru.korvin.dominion.activity.girlinfo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.server.GameConst;


public class GirlInfoListMainFragment extends Fragment
        implements GirlInfoListFragment.Callbacks {

   private View mView;
    private boolean mTwoPane;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (mView == null)
            mView = inflater.inflate(R.layout.fragment_girl_info_twopane, container, false);
        if (mView.findViewById(R.id.girl_info_detail_container) != null) {
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
        ((GirlInfoListFragment) getFragmentManager()
                .findFragmentById(R.id.girlinfo_list))
                .setCallbacks(this);
        return mView;
    }
    /**
     * Callback method from {@link GirlInfoListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onSlaveSelected(Person slave) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(GameConst.ARG_SLAVE_ID, slave.getId());
            GirlInfoDetailFragment fragment = new GirlInfoDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.girl_info_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(getActivity(), GirlInfoDetailActivity.class);
            detailIntent.putExtra(GameConst.ARG_SLAVE_ID, slave.getId());
            startActivity(detailIntent);
        }
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
            ((GirlInfoListFragment) getFragmentManager()
                .findFragmentById(R.id.girlinfo_list)).setUserVisibleHint(isVisibleToUser);
    }
    public static GirlInfoListMainFragment newInstance() {
        GirlInfoListMainFragment fragment = new GirlInfoListMainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }
}

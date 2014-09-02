package ru.korvin.dominion.activity.girlinfo;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;


import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;

/**
 * A fragment representing a single GirlInfo detail screen.
 * This fragment is either contained in a {@link GirlInfoListActivity}
 * in two-pane mode (on tablets) or a {@link GirlInfoDetailActivity}
 * on handsets.
 */
public class GirlInfoDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The Person content this fragment is presenting.
     */
    private Person mPerson;
    private GridView gv;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public GirlInfoDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
//            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_girlinfo_detail, container, false);
        //   gv = (GridView) container.findViewById(R.id.girl_info_skills_list);
        //   gv.setAdapter(new GirlIfonContent(new Person(), rootView.getContext()));
        // Show the dummy content as text in a TextView.
        /*if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.girlinfo_detail)).setText(mItem.content);
        }*/

        return rootView;
    }

}

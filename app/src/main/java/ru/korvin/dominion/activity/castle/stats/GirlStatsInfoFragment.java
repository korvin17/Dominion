package ru.korvin.dominion.activity.castle.stats;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;


public class GirlStatsInfoFragment extends Fragment {
    private AbsListView statsList;
    private Person person;
    private LayoutInflater inflater;


    public static GirlStatsInfoFragment newInstance(Person person){
        GirlStatsInfoFragment fragment=new GirlStatsInfoFragment();
        fragment.person=person;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_girl_stats_info, container, false);
        this.statsList = (AbsListView) view.findViewById(R.id.girl_info_stats_list);
        this.inflater = inflater;
        return view;
    }

    public void setPerson(Person person) {
        this.person = person;
        if (person != null)
            statsList.setAdapter(new StatsAdapter(inflater, person));
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public GirlStatsInfoFragment() {
        // Required empty public constructor
    }

}

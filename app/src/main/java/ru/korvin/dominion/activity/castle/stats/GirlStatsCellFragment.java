package ru.korvin.dominion.activity.castle.stats;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.korvin.dominion.R;

public class GirlStatsCellFragment extends Fragment {


    public GirlStatsCellFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_girl_stats_cell, container, false);
    }


}

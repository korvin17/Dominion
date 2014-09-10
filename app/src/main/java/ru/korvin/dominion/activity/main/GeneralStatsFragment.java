package ru.korvin.dominion.activity.main;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import ru.korvin.dominion.R;
import ru.korvin.dominion.activity.newday.NextDayActivity;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.mechanic.server.event.Event;

public class GeneralStatsFragment extends Fragment implements View.OnClickListener {
    Button mNextDayButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_general_stats, container, false);
        mNextDayButton = (Button) rootView.findViewById(R.id.next_day_button);
        mNextDayButton.setOnClickListener(this);
        return rootView;
    }

    private void nextDay() {
        GameApplication.getDefaultGameApplication().initiateNewDay();
        Event event = GameApplication.getDefaultGameApplication().nextEvent();
        startActivity(event.getIntent(getActivity()));
    }

    @Override
    public void onClick(View v) {
        if (v == mNextDayButton) {
            this.nextDay();
        }
    }

    public static GeneralStatsFragment newInstance() {
        return new GeneralStatsFragment();
    }

    public GeneralStatsFragment() {
    }

}

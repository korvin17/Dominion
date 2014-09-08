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
        Intent intent = new Intent(getActivity(), NextDayActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        if (v == mNextDayButton) {
            this.nextDay();
        }
    }

    public GeneralStatsFragment() {
    }

}

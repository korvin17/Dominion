package ru.korvin.dominion.activity.castle.stats;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;

public class StatsAdapter extends BaseAdapter {
    private Person person;
    private LayoutInflater inflater;

    public StatsAdapter(LayoutInflater inflater, Person person) {
        this.inflater = inflater;
        this.person = person;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_girl_stats_cell, null);
        }
        TextView name = (TextView) view.findViewById(R.id.fragment_girl_stats_cell_name);
        TextView value = (TextView) view.findViewById(R.id.fragment_girl_stats_cell_value);
        TextView diff = (TextView) view.findViewById(R.id.fragment_girl_stats_cell_diff);
        name.setText("имя");
        value.setText(getItem(position).toString());
        diff.setText("(+500)");
        diff.setTextColor(Color.parseColor("#FFFFFF"));
        return view;
    }

    @Override
    public Object getItem(int position) {
        switch (position) {
            case 0:
                return person.getStats().basic_strength;
            case 1:
                return person.getStats().basic_dexterity;
            case 2:
                return person.getStats().basic_constitution;
            case 3:
                return person.getStats().basic_intelligence;
            case 4:
                return person.getStats().basic_wisdom;
            case 5:
                return person.getStats().basic_charisma;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 6;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}

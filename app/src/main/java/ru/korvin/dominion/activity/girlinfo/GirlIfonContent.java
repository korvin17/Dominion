package ru.korvin.dominion.activity.girlinfo;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import ru.korvin.dominion.mechanic.baseObject.creature.Person;
import ru.korvin.dominion.mechanic.baseObject.creature.skill.Skill;

public class GirlIfonContent extends BaseAdapter {

    private Person mPerson;
    private Context mContext;

    public GirlIfonContent(Person mPerson, Context mContext) {
        this.mPerson = mPerson;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mPerson.getSkillList().getActiveSkills().size();
    }

    @Override
    public Object getItem(int position) {
        return mPerson.getSkillList().getActiveSkills().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Skill skill = (Skill) getItem(position);
        TextView textView;
        if (convertView == null) {  // if it's not recycled, initialize some attributes
            textView = new TextView(mContext);
            textView.setLayoutParams(new GridView.LayoutParams(85, 85));
            textView.setPadding(8, 8, 8, 8);
        } else {
            textView = (TextView) convertView;
        }

        textView.setText(skill.getNameId());
        return textView;
    }
}

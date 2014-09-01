package ru.korvin.dominion.activity.introduction;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import ru.korvin.dominion.R;
import ru.korvin.dominion.activity.main.MainTabActivity;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.mechanic.baseObject.creature.race.Sex;

public class IntroductionCreateAvatarActivity extends Activity implements View.OnClickListener {

    EditText editText;
    RadioButton radioButtonMale;
    RadioButton radioButtonFemale;
    RadioButton radioButtonFutanari;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introduction_create_avatar_activity);
        Button start = (Button) findViewById(R.id.introduction_create_avatar_button_start);
        start.setOnClickListener(this);


        editText = (EditText) findViewById(R.id.introduction_create_avatar_edit_name);
        radioButtonMale = (RadioButton) findViewById(R.id.introduction_create_avatar_radio_button_male);
        radioButtonFemale = (RadioButton) findViewById(R.id.introduction_create_avatar_radio_button_female);
        radioButtonFutanari = (RadioButton) findViewById(R.id.introduction_create_avatar_radio_button_futanari);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.introduction_create_avatar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.introduction_history_button_start) {
            String name = editText.getText().toString();
            Sex sex = Sex.futanari;
            if (radioButtonMale.isChecked()) sex = Sex.male;
            if (radioButtonFemale.isChecked()) sex = Sex.female;
            if (radioButtonFutanari.isChecked()) sex = Sex.futanari;
            ((GameApplication) getApplication()).getGameFacade().init(name, sex);
            Intent intent = new Intent(this, MainTabActivity.class);
            this.startActivity(intent);
        }
    }
}

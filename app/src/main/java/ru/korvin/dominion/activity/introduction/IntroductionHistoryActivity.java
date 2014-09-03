package ru.korvin.dominion.activity.introduction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import ru.korvin.dominion.R;
import ru.korvin.dominion.activity.main.MainTabActivity;
import ru.korvin.dominion.dao.GameApplication;

public class IntroductionHistoryActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!((GameApplication) getApplication()).isFirstLaunch()) {
            Intent mainTab = new Intent(this, MainTabActivity.class);
            mainTab.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            this.startActivity(mainTab);
            return;
        }
        setContentView(R.layout.introduction_history_activity);
        Button start = (Button) findViewById(R.id.introduction_history_button_start);
        start.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.introduction_history, menu);
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
            Intent intent = new Intent(this, IntroductionCreateAvatarActivity.class);
            this.startActivity(intent);
        }
    }
}

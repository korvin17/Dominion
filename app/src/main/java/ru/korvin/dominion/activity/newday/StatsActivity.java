package ru.korvin.dominion.activity.newday;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.server.Server;
import ru.korvin.dominion.mechanic.server.event.type.TotalEvent;

public class StatsActivity extends NextDayActivity<TotalEvent> implements View.OnClickListener {
    private TextView mMoneyTextView;
    private TextView mMoneyProfitTextView;
    private Button mEndButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_day_stats);
        mMoneyTextView = (TextView) findViewById(R.id.money);
        mMoneyProfitTextView = (TextView) findViewById(R.id.money_profit);
        mEndButton= (Button) findViewById(R.id.button_end);

        mMoneyTextView.setText(Long.toString(Server.getDefault().getPlayer().getMoney()));
        mMoneyProfitTextView.setText(Long.toString(mEvent.moneyDiff));
        mEndButton.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stats, menu);
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
        super.showNextEvent();
    }
}

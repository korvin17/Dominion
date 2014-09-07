package ru.korvin.dominion.activity.castle.market;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import ru.korvin.dominion.R;
import ru.korvin.dominion.activity.castle.stats.GirlStatsInfoFragment;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;

/**
 * A fragment representing a single MarketSlave detail screen.
 * This fragment is either contained in a {@link MarketSlaveListActivity}
 * in two-pane mode (on tablets) or a {@link MarketSlaveDetailActivity}
 * on handsets.
 */
public class MarketSlaveDetailFragment extends Fragment implements View.OnClickListener {
    public static final String ARG_SLAVE_ID = "slave_id";
    private Person slave;
    private Button mBuyButton;
    private ImageView mImageView;
    private Callback callback;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MarketSlaveDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_SLAVE_ID)) {
            slave = GameApplication.getDefaultGameApplication().getServer().getPersonWithID(getArguments().getInt(ARG_SLAVE_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_marketslave_detail, container, false);
        mBuyButton = (Button) rootView.findViewById(R.id.market_slave_buy_button);
        mBuyButton.setOnClickListener(this);
        mImageView= (ImageView) rootView.findViewById(R.id.market_slave_image_view);

        if (slave != null) {
            GirlStatsInfoFragment statsInfoFragment = GirlStatsInfoFragment.newInstance(slave);
            getFragmentManager().beginTransaction()
                    .replace(R.id.market_slave_girl_stats_container, statsInfoFragment)
                    .commit();
            mImageView.setImageResource(slave.getImageMarketSlave());
        }

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.callback= (Callback) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.callback=null;
    }

    private void buyPerson(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.room_market_buy_title);
        String message = String.format(getResources().getString(R.string.room_market_buy_message),getResources().getString(slave.getNameId()), slave.cost);

        builder.setMessage(message ); // сообщение
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {
                boolean buy=GameApplication.getDefaultServer().buyPerson(slave);
                mBuyButton.setEnabled(!buy);
                if(buy)
                    callback.buyPerson(slave);
            }
        });
        builder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int arg1) {

            }
        });
        builder.setCancelable(false);
        builder.show();
    }


    @Override
    public void onClick(View v) {
        if (v == mBuyButton) {
           buyPerson();
        }
    }
    public interface Callback {
        public boolean buyPerson(Person person);
    }
}

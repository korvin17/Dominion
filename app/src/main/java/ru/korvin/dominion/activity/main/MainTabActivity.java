package ru.korvin.dominion.activity.main;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Locale;

import ru.korvin.dominion.R;
import ru.korvin.dominion.activity.castle.CastleFragment;
import ru.korvin.dominion.activity.castle.market.MarketSlaveListActivity;
import ru.korvin.dominion.activity.castle.room.WorkRoomMainFragment;
import ru.korvin.dominion.activity.girlinfo.GirlInfoListMainFragment;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.dao.storage.DB;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;


public class MainTabActivity extends Activity implements ActionBar.TabListener, CastleFragment.OnRoomFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v13.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;
    DB mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab_activity);

        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        mDataBase = new DB(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_tab, menu);
        return true;
    }

    public boolean save() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.main_tab_save_title);

        Cursor cursor = mDataBase.getAllState();
        ArrayList<String> items = new ArrayList<>(cursor.getCount() + 1);
        final ArrayList<Long> ids = new ArrayList<>(cursor.getCount() + 1);

        items.add(getString(R.string.new_save));
        if (cursor.moveToFirst()) {
            do {
                items.add(cursor.getString(cursor.getColumnIndex(DB.COLUMN_NAME_NAME)));
                ids.add(cursor.getLong(cursor.getColumnIndex(DB.COLUMN_NAME_ID)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        final Activity activity = this;
        final String[] names = items.toArray(new String[0]);
        builder.setItems(names, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                    builder.setTitle(R.string.main_tab_save_edit_name_title);
                    final EditText input = new EditText(activity);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    input.setLayoutParams(lp);
                    builder.setView(input);
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            GameApplication.getDefaultGameApplication().saveState(input.getText().toString());
                        }
                    });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.show();
                } else {
                    String name = names[which];
                    long id = ids.get(which - 1);
                    GameApplication.getDefaultGameApplication().saveState(name, id);
                }
            }
        });

        builder.setCancelable(true);
        builder.show();
        return true;
    }


    public boolean load() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.main_tab_load_title);
        final Cursor cursor = mDataBase.getAllState();
        builder.setCursor(cursor, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cursor.moveToPosition(which);
                long id = cursor.getLong(cursor.getColumnIndex(DB.COLUMN_NAME_ID));
                GameApplication.getDefaultGameApplication().loadSave(id);
                cursor.close();
                recreate();
            }
        }, DB.COLUMN_NAME_NAME);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                cursor.close();
            }
        });
        builder.setCancelable(true);
        builder.show();
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.main_tab_settings:
                return true;
            case R.id.main_tab_save:
                return save();
            case R.id.main_tab_load:
                return load();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    public static final String EXTRA_ROOM_ID = "room id";

    @Override
    public void onRoomFragmentInteraction(Room room) {
        switch (room.getNameId()) {
            case R.string.room_market_name:
                Intent intent = new Intent(this, MarketSlaveListActivity.class);
                intent.putExtra(EXTRA_ROOM_ID, room.id);
                this.startActivity(intent);
            default:

        }
    }

    @Override
    public void recreate() {
        mDataBase.close();
        super.recreate();
    }

    private Fragment active_fragment = null;

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position) {
                case 0:
                    return GeneralStatsFragment.newInstance();
                case 1:
                    return WorkRoomMainFragment.newInstance();
                case 2:
                    return GirlInfoListMainFragment.newInstance();
                case 3:
                    return CastleFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.tab_stats_name).toUpperCase(l);
                case 1:
                    return getString(R.string.tab_castle_name).toUpperCase(l);
                case 2:
                    return getString(R.string.tab_girls_name).toUpperCase(l);
                case 3:
                    return getString(R.string.tab_world_name).toUpperCase(l);
            }
            return null;
        }

    }
}

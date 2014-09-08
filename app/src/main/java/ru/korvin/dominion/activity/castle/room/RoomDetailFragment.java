package ru.korvin.dominion.activity.castle.room;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import ru.korvin.dominion.R;
import ru.korvin.dominion.dao.GameApplication;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;
import ru.korvin.dominion.mechanic.baseObject.creature.Person;


/**
 * A fragment representing a single Room detail screen.
 * This fragment is either contained in a {@link WorkRoomMainFragment}
 * in two-pane mode (on tablets) or a {@link RoomDetailActivity}
 * on handsets.
 */
public class RoomDetailFragment extends Fragment implements AdapterView.OnItemClickListener {
    public static final String ARG_ROOM_ID = "room_id";
    private Room workRoom;
    private Room restRoom;
    private AbsListView mRestListView;
    private AbsListView mWorkListView;

    private ArrayAdapter<Person> mRestAdapter;
    private ArrayAdapter<Person> mWorkAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments().containsKey(ARG_ROOM_ID)) {
            restRoom = GameApplication.getDefaultGameApplication().getServer().getRest();
            workRoom = GameApplication.getDefaultGameApplication().getServer().getRoomWithID(getArguments().getInt(ARG_ROOM_ID));
            mRestAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, restRoom.getPersons());
            mWorkAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, workRoom.getPersons());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_work_room_detail, container, false);
        mRestListView = (AbsListView) rootView.findViewById(R.id.work_room_girl_rest_list_view);
        mWorkListView = (AbsListView) rootView.findViewById(R.id.work_room_girl_work_list_view);
        mRestListView.setAdapter(mRestAdapter);
        mWorkListView.setAdapter(mWorkAdapter);
        mRestListView.setOnItemClickListener(this);
        mWorkListView.setOnItemClickListener(this);
        return rootView;
    }

    public RoomDetailFragment() {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent == mRestListView) {
            Person person = mRestAdapter.getItem(position);
            mRestAdapter.remove(person);
            mWorkAdapter.add(person);
        } else {
            Person person = mWorkAdapter.getItem(position);
            mWorkAdapter.remove(person);
            mRestAdapter.add(person);
        }
    }
}

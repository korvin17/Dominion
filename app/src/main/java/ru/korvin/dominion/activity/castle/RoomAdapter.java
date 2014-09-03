package ru.korvin.dominion.activity.castle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ru.korvin.dominion.R;
import ru.korvin.dominion.mechanic.baseObject.castle.room.Room;

public class RoomAdapter extends ArrayAdapter<Room> {
    protected LayoutInflater layoutInflater;

    public RoomAdapter(Context context, Room[] objects) {
        super(context, R.layout.fragment_room, objects);
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        if (convertView == null)
            rowView = layoutInflater.inflate(R.layout.fragment_room, null, true);
        else
            rowView = convertView;
        TextView txtTitle = (TextView) rowView.findViewById(R.id.fragment_room_text);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.fragment_room_image);
        txtTitle.setText(this.getItem(position).getNameId());
        imageView.setImageResource(this.getItem(position).getImageId());
        return rowView;
    }
}

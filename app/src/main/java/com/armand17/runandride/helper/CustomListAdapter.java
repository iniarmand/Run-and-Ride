package com.armand17.runandride.helper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.armand17.runandride.R;
import com.armand17.runandride.Session;

import java.util.List;

/**
 * Created by armand17 on 11/01/16.
 */
public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Session> sessionsItems;
    ImageView iconImage;

    public CustomListAdapter(Activity activity, List<Session> sessionsItems){
        this.activity = activity;
        this.sessionsItems = sessionsItems;
    }

    @Override
    public int getCount() {
        return sessionsItems.size();
    }

    @Override
    public Object getItem(int position) {
        return sessionsItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.saved_list_item, null);
        iconImage.setImageResource(R.mipmap.medal);
        TextView textTittle = (TextView)convertView.findViewById(R.id.tittleList);
        TextView textTime = (TextView)convertView.findViewById(R.id.dateList);
        TextView textCallorie = (TextView)convertView.findViewById(R.id.callorieList);

        Session session = sessionsItems.get(position);

        textTittle.setText(session.get_sessionName());
        textTime.setText(session.get_sessionTime().toString());
        textCallorie.setText(session.get_callorie().toString());

        if (session.get_sessionType().equals("run")){
            iconImage.setImageResource(R.mipmap.run);
        } else if (session.get_sessionType().equals("bike")){
            iconImage.setImageResource(R.mipmap.bike);
        }

        return convertView;
    }
}

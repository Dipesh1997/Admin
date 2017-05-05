package dev.edmt.firebasedemo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.List;
public class FareView extends BaseAdapter {
    Activity activity;
    List<FareData> lstUsers;
    LayoutInflater inflater;
    public FareView(Activity activity, List<FareData> lstUsers) {
        this.activity = activity;
        this.lstUsers = lstUsers;
    }
    @Override
    public int getCount() {
        return lstUsers.size();
    }
    @Override
    public Object getItem(int i) {
        return lstUsers.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        inflater = (LayoutInflater)activity.getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.activity_fare_view,null);
        TextView Fare=(TextView)itemView.findViewById(R.id.fare);
        Fare.setText("Your Fare Is :"+lstUsers.get(i).getFare());
        return itemView;
    }
}

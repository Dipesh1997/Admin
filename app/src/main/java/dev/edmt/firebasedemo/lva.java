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
public class lva extends BaseAdapter {
    Activity activity;
    List<UserData> lstUsers;
    LayoutInflater inflater;
    public lva(Activity activity, List<UserData> lstUsers) {
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
        View itemView = inflater.inflate(R.layout.recordlist,null);
        TextView Name = (TextView)itemView.findViewById(R.id.nameS);
        TextView Contact=(TextView)itemView.findViewById(R.id.contactS);
        TextView Date=(TextView)itemView.findViewById(R.id.dateS);
        TextView Time=(TextView)itemView.findViewById(R.id.timeS);
        TextView Bus=(TextView)itemView.findViewById(R.id.busS);
        TextView From=(TextView)itemView.findViewById(R.id.fromS);
        TextView Destination=(TextView)itemView.findViewById(R.id.desS);
        TextView Nop=(TextView)itemView.findViewById(R.id.nopS);
        TextView Service=(TextView)itemView.findViewById(R.id.serS);
        TextView Seat=(TextView)itemView.findViewById(R.id.seatS);
        TextView Status=(TextView)itemView.findViewById(R.id.statusS);
        TextView Email=(TextView)itemView.findViewById(R.id.emailS);
        TextView Fare=(TextView)itemView.findViewById(R.id.fareS);
        Name.setText("Name:"+lstUsers.get(i).getName());
        Contact.setText("Contact :"+lstUsers.get(i).getContact());
        Date.setText("Journey Date :"+lstUsers.get(i).getDate());
        Time.setText("Journey Time :"+lstUsers.get(i).getTime());
        Bus.setText("Bus Name :"+lstUsers.get(i).getBus());
        From.setText("From :"+lstUsers.get(i).getFrom());
        Destination.setText("Destination :"+lstUsers.get(i).getDestination());
        Nop.setText("Person :"+lstUsers.get(i).getNop());
        Service.setText("Service :"+lstUsers.get(i).getService());
        Seat.setText("Seat :"+lstUsers.get(i).getSeat());
        Status.setText("Status :"+lstUsers.get(i).getStatus());
        Email.setText("Email :"+lstUsers.get(i).getEmail());
        Fare.setText("Fare :"+lstUsers.get(i).getFare());

        return itemView;
    }
}

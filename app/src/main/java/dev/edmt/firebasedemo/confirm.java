package dev.edmt.firebasedemo;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.UUID;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class confirm extends ActionBarActivity {
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Email = "emailKey";
    public static final String NameP = "nameKey";
    public static final String ContactP = "contactKey";
    public static final String DateP = "dateKey";
    public static final String TimeP = "timeKey";
    public static final String BusP = "busKey";
    public static final String FromP = "fromKey";
    public static final String DesP = "desKey";
    public static final String nopP = "nopKey";
    public static final String SerP = "serKey";
    public static final String SeatP = "seatKey";
    public static final String StatusP = "statusKey";
    public static final String fareP="fareKey";
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    public String name,contact,date,time,bus,from,destination,nop,service,seat,status,email,farecheck,token;
    public SharedPreferences sharedpreferences;
    public TextView farevalue;
    public String FareSend;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        TextView tEmail = (TextView) findViewById(R.id.email2);
        TextView tName = (TextView) findViewById(R.id.name2);
        TextView tContact = (TextView) findViewById(R.id.contact2);
        TextView tDate = (TextView) findViewById(R.id.date2);
        TextView tTime = (TextView) findViewById(R.id.time2);
        TextView tBus = (TextView) findViewById(R.id.bus2);
        TextView tFrom = (TextView) findViewById(R.id.from2);
        TextView tDes = (TextView) findViewById(R.id.des2);
        TextView tNop = (TextView) findViewById(R.id.nop2);
        TextView tSer = (TextView) findViewById(R.id.ser2);
        TextView tSeat = (TextView) findViewById(R.id.seat2);
        TextView tStatus = (TextView) findViewById(R.id.status2);
        farevalue=(TextView)findViewById(R.id.faretxt);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        email = sharedpreferences.getString(Email, "dipeshkataria3@gmail.com");
        name = sharedpreferences.getString(NameP, "No name Defined");
        contact = sharedpreferences.getString(ContactP, null);
        date = sharedpreferences.getString(DateP, null);
        time = sharedpreferences.getString(TimeP, null);
        bus = sharedpreferences.getString(BusP, null);
        from = sharedpreferences.getString(FromP, null);
        destination = sharedpreferences.getString(DesP, null);
        nop = sharedpreferences.getString(nopP, null);
        service = sharedpreferences.getString(SerP, null);
        seat = sharedpreferences.getString(SeatP, null);
        status = sharedpreferences.getString(StatusP, null);
        farecheck=sharedpreferences.getString(fareP,null);
        tEmail.setText(email);
        tName.setText(name);
        tContact.setText(contact);
        tDate.setText(date);
        tTime.setText(time);
        tBus.setText(bus);
        tFrom.setText(from);
        tDes.setText(destination);
        tNop.setText(nop);
        tSer.setText(service);
        tSeat.setText(seat);
        tStatus.setText(status);
        initFirebase();
        mDatabaseReference.child("FareData/"+farecheck).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String value=dataSnapshot.getValue(String.class);
                farevalue.setText("Rs "+value);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        subscribeToPushService();
    }
    private void initFirebase()
    {
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference  = mFirebaseDatabase.getReference();
    }
    public void onSave(View view)
    {
        FareSend=farevalue.getText().toString();
        String unavailable = "Rs null";
        String noInternet="";
        String rs="Rs";
        if (FareSend.equals(unavailable)||FareSend.equals(noInternet)||FareSend.equals(rs)) {
            Toast.makeText(confirm.this, "The Following Seat is Unavailable Try again Later", Toast.LENGTH_LONG).show();
        }
//        if (FareSend.equals(noInternet)) {
//            Toast.makeText(confirm.this, "Data Connection Not availble try again later", Toast.LENGTH_LONG).show();
//        }
        else {
            UserData userData = new UserData(UUID.randomUUID().toString(), name, contact, date, time, bus, from, destination, nop, service, seat, status, email, FareSend, token);
            mDatabaseReference.child("UserData").child(userData.getUid()).setValue(userData);
            startActivity(new Intent(confirm.this, MainActivity.class));
        }
    }
    public void onRewrite(View view)
    {
        startActivity(new Intent(confirm.this,form.class));
    }
    private void subscribeToPushService() {
        FirebaseMessaging.getInstance().subscribeToTopic("news");

        Log.d("AndroidBash", "Subscribed");
        //Toast.makeText(confirm.this, "Subscribed", Toast.LENGTH_SHORT).show();

        token = FirebaseInstanceId.getInstance().getToken();

        // Log and toast
        Log.d("AndroidBash", token);
        //Toast.makeText(confirm.this, token, Toast.LENGTH_SHORT).show();
    }
}
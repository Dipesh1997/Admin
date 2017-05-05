package dev.edmt.firebasedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class Fare extends AppCompatActivity {
    Spinner spinner1;
    ArrayAdapter<CharSequence> adapter1;
    Spinner spinner2;
    ArrayAdapter<CharSequence> adapter2;
    Spinner spinner3;
    ArrayAdapter<CharSequence> adapter3;
    Spinner spinner4;
    ArrayAdapter<CharSequence> adapter4;
    Spinner spinner5;
    ArrayAdapter<CharSequence> adapter5;
    Spinner spinner6;
    ArrayAdapter<CharSequence> adapter6;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private ListView list_data;
    private List<FareData> list_users = new ArrayList<>();
    String busSp,fromSp,destinationSp,nopSp,acSp,seatSp;
    public Query query;
    public TextView farevalue;
    public EditText updateFare;
    public String newfare;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fare);
        onDrop();
        //list_data = (ListView)findViewById(R.id.list_data1);
        initFirebase();
        farevalue=(TextView)findViewById(R.id.faretxt);
        autoFareView();

    }
    public void onDrop()
    {
        spinner1=(Spinner)findViewById(R.id.busDrop);
        adapter1=ArrayAdapter.createFromResource(this,R.array.buses,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);
        spinner2=(Spinner)findViewById(R.id.fDrop);
        adapter2=ArrayAdapter.createFromResource(this,R.array.states,android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner2.setAdapter(adapter2);
        spinner3=(Spinner)findViewById(R.id.desDrop);
        adapter3=ArrayAdapter.createFromResource(this,R.array.states,android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner3.setAdapter(adapter3);
        spinner4=(Spinner)findViewById(R.id.pDrop);
        adapter4=ArrayAdapter.createFromResource(this,R.array.persons,android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner4.setAdapter(adapter4);
        spinner5=(Spinner)findViewById(R.id.acDrop);
        adapter5=ArrayAdapter.createFromResource(this,R.array.service,android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner5.setAdapter(adapter5);
        spinner6=(Spinner)findViewById(R.id.seatDrop);
        adapter6=ArrayAdapter.createFromResource(this,R.array.seat_type,android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner6.setAdapter(adapter6);
    }

    private void initFirebase()
    {
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference  = mFirebaseDatabase.getReference();
    }
    public void onCheck(View view)
    {
        initFirebase();
        String buspos=String.valueOf(spinner1.getSelectedItemPosition());
        String frompos=String.valueOf(spinner2.getSelectedItemPosition());
        String despos=String.valueOf(spinner3.getSelectedItemPosition());
        String noppos=String.valueOf(spinner4.getSelectedItemPosition());
        String serpos=String.valueOf(spinner5.getSelectedItemPosition());
        String seatpos=String.valueOf(spinner6.getSelectedItemPosition());

        String farecheck=buspos+frompos+despos+noppos+serpos+seatpos;
        /*
        //Progressing
        list_data.setVisibility(View.INVISIBLE);
        query=mDatabaseReference.child("FareData").orderByChild("bus").equalTo(farecheck);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(list_users.size() > 0)
                    list_users.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    FareData fareData = postSnapshot.getValue(FareData.class);
                    list_users.add(fareData);

                }
                FareView adapter = new FareView(Fare.this,list_users);
                list_data.setAdapter(adapter);
                list_data.setVisibility(View.VISIBLE);
                //farevalue.setText(adapter.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        */
        Toast.makeText(Fare.this,farecheck,Toast.LENGTH_SHORT).show();
        mDatabaseReference.child("FareData/"+farecheck).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String value=dataSnapshot.getValue(String.class);
                farevalue.setText("Current Fare is Rs "+value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void autoFareView()
    {
        initFirebase();
        String buspos=String.valueOf(spinner1.getSelectedItemPosition());
        String frompos=String.valueOf(spinner2.getSelectedItemPosition());
        String despos=String.valueOf(spinner3.getSelectedItemPosition());
        String noppos=String.valueOf(spinner4.getSelectedItemPosition());
        String serpos=String.valueOf(spinner5.getSelectedItemPosition());
        String seatpos=String.valueOf(spinner6.getSelectedItemPosition());
        String farecheck=buspos+frompos+despos+noppos+serpos+seatpos;
        //Toast.makeText(Fare.this,farecheck,Toast.LENGTH_SHORT).show();
        mDatabaseReference.child("FareData/"+farecheck).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String value=dataSnapshot.getValue(String.class);
                farevalue.setText("Current Fare is Rs "+value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void onUpdate(View view)
    {
        initFirebase();
        String buspos=String.valueOf(spinner1.getSelectedItemPosition());
        String frompos=String.valueOf(spinner2.getSelectedItemPosition());
        String despos=String.valueOf(spinner3.getSelectedItemPosition());
        String noppos=String.valueOf(spinner4.getSelectedItemPosition());
        String serpos=String.valueOf(spinner5.getSelectedItemPosition());
        String seatpos=String.valueOf(spinner6.getSelectedItemPosition());
        String farecheck=buspos+frompos+despos+noppos+serpos+seatpos;
        updateFare=(EditText)findViewById(R.id.updateFare);
        newfare=updateFare.getText().toString();
        mDatabaseReference.child("FareData/"+farecheck).setValue(newfare);

    }
}

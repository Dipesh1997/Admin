package dev.edmt.firebasedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
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

public class Search extends AppCompatActivity {
    private ListView list_data;
    private ProgressBar circular_progress;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Email1 = "emailKey";
    public SharedPreferences sharedpreferences;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private List<UserData> list_users = new ArrayList<>();
    public Button cancelbtn,confirmbtn,completebtn;

    private UserData selectedUser;// hold user when we select item in listview
    public String name,contact,date,time,bus,from,destination,nop,service,seat,status,email;
    public String tosta;
    public String email1;
    public EditText searchbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        circular_progress = (ProgressBar)findViewById(R.id.circular_progress);
        list_data = (ListView)findViewById(R.id.list_data);

        list_data.setOnItemClickListener
                (new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        UserData userData = (UserData) adapterView.getItemAtPosition(i);
                        selectedUser = userData;
                        //UserData userData=new UserData();
                        tosta="Now click cancel button above to Cancel";
                        Toast.makeText(Search.this,tosta,Toast.LENGTH_LONG).show();
                        cancelbtn.setVisibility(View.VISIBLE);
                        confirmbtn.setVisibility(View.VISIBLE);
                        completebtn.setVisibility(View.VISIBLE);
                    }
                });


        //Firebase
        initFirebase();
        //addEventFirebaseListener();
        cancelbtn=(Button)findViewById(R.id.cancelbtn);
        cancelbtn.setVisibility(View.GONE);
        confirmbtn=(Button)findViewById(R.id.confirmbtn);
        confirmbtn.setVisibility(View.GONE);
        completebtn=(Button)findViewById(R.id.completebtn);
        completebtn.setVisibility(View.GONE);

    }
    private void addEventFirebaseListener() {
        //Progressing
        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        email1 = sharedpreferences.getString(Email1, "dipeshkataria3@gmail.com");
        circular_progress.setVisibility(View.VISIBLE);
        list_data.setVisibility(View.INVISIBLE);
        Query query=mDatabaseReference.child("UserData").orderByChild("name");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(list_users.size() > 0)
                    list_users.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    UserData userData = postSnapshot.getValue(UserData.class);
                    list_users.add(userData);
                }
                lva adapter = new lva(Search.this,list_users);
                list_data.setAdapter(adapter);

                circular_progress.setVisibility(View.INVISIBLE);
                list_data.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    public void onSearch(View view)
    {   searchbar=(EditText)findViewById(R.id.searchBar);
        String name=searchbar.getText().toString();
        circular_progress.setVisibility(View.VISIBLE);
        list_data.setVisibility(View.INVISIBLE);
        Query query=mDatabaseReference.child("UserData").orderByChild("name").equalTo(name);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(list_users.size() > 0)
                    list_users.clear();
                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    UserData userData = postSnapshot.getValue(UserData.class);
                    list_users.add(userData);
                }
                lva adapter = new lva(Search.this,list_users);
                list_data.setAdapter(adapter);

                circular_progress.setVisibility(View.INVISIBLE);
                list_data.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference  = mFirebaseDatabase.getReference();
    }

    public void onCan(View view)
    {
        UserData userData = new UserData(selectedUser.getUid(),selectedUser.getName(),selectedUser.getContact(),selectedUser.getDate(),selectedUser.getTime(),selectedUser.getBus(),selectedUser.getFrom(),selectedUser.getDestination(),selectedUser.getNop(),selectedUser.getService(),selectedUser.getSeat(),selectedUser.getStatus(),selectedUser.getEmail(),selectedUser.getFare(),selectedUser.getToken());
        updateUser(userData);
        startActivity(new Intent(Search.this,Search.class));
    }

    private void updateUser(UserData userData) {

        mDatabaseReference.child("UserData").child(userData.getUid()).child("status").setValue("Cancelled");
    }
    public void onConfirm(View view)
    {
        UserData userData1 = new UserData(selectedUser.getUid(),selectedUser.getName(),selectedUser.getContact(),selectedUser.getDate(),selectedUser.getTime(),selectedUser.getBus(),selectedUser.getFrom(),selectedUser.getDestination(),selectedUser.getNop(),selectedUser.getService(),selectedUser.getSeat(),selectedUser.getStatus(),selectedUser.getEmail(),selectedUser.getFare(),selectedUser.getToken());
        updateUser1(userData1);
        startActivity(new Intent(Search.this,Search.class));
    }

    private void updateUser1(UserData userData1) {

        mDatabaseReference.child("UserData").child(userData1.getUid()).child("status").setValue("Confirmed");
    }
    public void onComplete(View view)
    {
        UserData userData2 = new UserData(selectedUser.getUid(),selectedUser.getName(),selectedUser.getContact(),selectedUser.getDate(),selectedUser.getTime(),selectedUser.getBus(),selectedUser.getFrom(),selectedUser.getDestination(),selectedUser.getNop(),selectedUser.getService(),selectedUser.getSeat(),selectedUser.getStatus(),selectedUser.getEmail(),selectedUser.getFare(),selectedUser.getToken());
        updateUser2(userData2);
        startActivity(new Intent(Search.this,Search.class));
    }

    private void updateUser2(UserData userData2) {

        mDatabaseReference.child("UserData").child(userData2.getUid()).child("status").setValue("Completed");
    }
}

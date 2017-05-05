package dev.edmt.firebasedemo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
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
import android.view.View.OnClickListener;


public class payment extends Fragment implements OnClickListener
{
    private ListView list_data;
    private ProgressBar circular_progress;
    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Email1 = "emailKey";
    public SharedPreferences sharedpreferences;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private List<UserData> list_users = new ArrayList<>();
    public Button confirmbtn;

    private UserData selectedUser;// hold user when we select item in listview
    public String name,contact,date,time,bus,from,destination,nop,service,seat,status,email;
    public String tosta;
    public String email1;
    LinearLayout linearLayout;
    Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v=inflater.inflate(R.layout.payment, container, false);
        circular_progress = (ProgressBar)v.findViewById(R.id.circular_progress);
        list_data = (ListView)v.findViewById(R.id.list_data);
        list_data.setOnItemClickListener
                (new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        UserData userData = (UserData) adapterView.getItemAtPosition(i);
                        selectedUser = userData;
                        //UserData userData=new UserData();
                        tosta="Now click confirmed Button";
                        Toast.makeText(getActivity(),tosta,Toast.LENGTH_LONG).show();

                        confirmbtn.setVisibility(View.VISIBLE);

                    }
                });


        //Firebase
        initFirebase();
        addEventFirebaseListener();

        confirmbtn=(Button)v.findViewById(R.id.confirmbtn);
        confirmbtn.setVisibility(View.GONE);


        // Inflate the layout for this fragment


        return v;
    }
    private void addEventFirebaseListener() {
        circular_progress.setVisibility(View.VISIBLE);
        list_data.setVisibility(View.INVISIBLE);
        Query query=mDatabaseReference.child("UserData").orderByChild("status").equalTo("Payment Stage");
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
                lva adapter = new lva(getActivity(),list_users);
                list_data.setAdapter(adapter);

                circular_progress.setVisibility(View.INVISIBLE);
                list_data.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button confirmButton = (Button) getActivity().findViewById(
                R.id.confirmbtn);
        confirmButton.setOnClickListener((OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
        UserData userData = new UserData(selectedUser.getUid(),selectedUser.getName(),selectedUser.getContact(),selectedUser.getDate(),selectedUser.getTime(),selectedUser.getBus(),selectedUser.getFrom(),selectedUser.getDestination(),selectedUser.getNop(),selectedUser.getService(),selectedUser.getSeat(),selectedUser.getStatus(),selectedUser.getEmail(),selectedUser.getFare(),selectedUser.getToken());
        updateUser(userData);

    }


    private void initFirebase() {
        FirebaseApp.initializeApp(getActivity());
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference  = mFirebaseDatabase.getReference();
    }

    private void updateUser(UserData userData) {

        mDatabaseReference.child("UserData").child(userData.getUid()).child("status").setValue("Confirmed");
    }

}

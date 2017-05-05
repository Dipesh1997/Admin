package dev.edmt.firebasedemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Email1 = "emailKey";
    public SharedPreferences sharedpreferences;
    public String email1;
    private ListView list_data;
    private ProgressBar circular_progress;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;

    private List<UserData> list_users = new ArrayList<>();

    private UserData selectedUser; // hold user when we select item in listview
    DrawerLayout dLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Book2Me Admin");
        setSupportActionBar(toolbar);

        //Control
       // circular_progress = (ProgressBar)findViewById(R.id.circular_progress);
      //  list_data = (ListView)findViewById(R.id.list_data);
    /*  list_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                User user = (User)adapterView.getItemAtPosition(i);
                selectedUser = user;

                input_name.setText(user.getName());
                input_email.setText(user.getEmail());
                input_field3.setText(user.getField3());
            }
        });
    */

        //Firebase
        initFirebase();


    }



    private void initFirebase() {
        FirebaseApp.initializeApp(this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference  = mFirebaseDatabase.getReference();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /*
        if(item.getItemId() == R.id.menu_add)
        {
            createUser();
        }
        else if(item.getItemId() == R.id.menu_save)
        {
            User user = new User(selectedUser.getUid(),input_name.getText().toString(),input_email.getText().toString(),input_field3.getText().toString());
            updateUser(user);
        }
        else if(item.getItemId() == R.id.menu_remove){
            deleteUser(selectedUser);
        }

        */
         if(item.getItemId()==R.id.addpage)
        {
            onForm();
        }
         else if(item.getItemId()==R.id.form)
         {
             onAdd();
         }
         else if(item.getItemId()==R.id.read)
         {
             onRead();
         }

        return true;
    }


    public void onRead()
    {
        startActivity(new Intent(MainActivity.this,read.class));
    }
    public void onView(View view)
    {
        startActivity(new Intent(MainActivity.this,ViewCategory.class));
    }
    public void onSearch(View view)
    {
        startActivity(new Intent(MainActivity.this,Search.class));
    }

    public void onAdd()
    {
        startActivity(new Intent(MainActivity.this,SignInActivity.class));
    }
    public void onForm()
    {
        startActivity(new Intent(MainActivity.this,form.class));
    }
}

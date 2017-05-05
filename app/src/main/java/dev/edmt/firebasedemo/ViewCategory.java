package dev.edmt.firebasedemo;


        import android.os.Bundle;
        import android.support.design.widget.NavigationView;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v4.widget.DrawerLayout;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.Toolbar;
        import android.view.MenuItem;
        import android.widget.Toast;
        import android.support.v7.app.ActionBar;
public class ViewCategory extends AppCompatActivity {
    DrawerLayout dLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_category);
        setNavigationDrawer(); // call method
        Toast.makeText(this,"Slide From Left To get Options",Toast.LENGTH_SHORT).show();

    }

    private void setNavigationDrawer()
    {
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);


        NavigationView navView = (NavigationView) findViewById(R.id.navigation);


        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem)
            {

                Fragment frag = null;
                int itemId = menuItem.getItemId();


                if (itemId == R.id.payment) {
                    frag = new payment();
                } else if (itemId == R.id.canceled) {
                    frag = new canceled();
                } else if (itemId == R.id.confirmed) {
                    frag = new confirmed();
                }else if (itemId == R.id.completed)
                {
                    frag= new completed();
                }

                if (frag != null) {
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.frame, frag);
                    transaction.commit();
                    dLayout.closeDrawers();
                    return true;
                }

                return false;
            }
        });
    }
}

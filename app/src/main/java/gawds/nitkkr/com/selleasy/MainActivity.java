package gawds.nitkkr.com.selleasy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ListView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.TextView;


import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;


public class MainActivity extends AppCompatActivity  {


    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
public static GoogleApiClient object;
    public Button buy, sell,manage;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    public void init() {
        buy = (Button) findViewById(R.id.buy_button);
        sell = (Button) findViewById(R.id.sell_button);
        manage=(Button)findViewById(R.id.manage);

        buy.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        Buy.class);
                startActivity(myIntent);

            }
        });
        manage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent2 = new Intent(MainActivity.this,
                        Manage.class);

                startActivity(myIntent2);

            }
        });
        sell.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent1 = new Intent(MainActivity.this,
                        Sell.class);

                startActivity(myIntent1);

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
//        String message = bundle.getString("message");
//        String image = bundle.getString("image");
        String message=getSharedPreferences("username",MODE_PRIVATE).getString("displayname"," ");
        TextView txtView = (TextView) findViewById(R.id.textView9);
       // txtView.setText("You are logged in as " + message);
        init();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       // client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        setupToolbar();

        DataModel[] drawerItem = new DataModel[7];
        drawerItem[0]=new DataModel(R.drawable.login_icon,message);
        drawerItem[1] = new DataModel(R.drawable.home, "Home");
        drawerItem[2] = new DataModel(R.drawable.buy_icon, "Buy");
        drawerItem[3] = new DataModel(R.drawable.sell_icon, "Sell");
        drawerItem[4] = new DataModel(R.drawable.manage, "Manage");
        drawerItem[5] = new DataModel(R.drawable.about_us, "About us");
        drawerItem[6] = new DataModel(R.drawable.logout_icon, "Logout");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }
    //GoogleApiClient mGoogleApiClient;
    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 1:
                Intent i = new Intent(this ,MainActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.activity_push_up_in,R.anim.activity_push_up_out);
                break;
            case 2:
                Intent i1 = new Intent(getApplicationContext(),Buy.class);
                startActivity(i1);
                overridePendingTransition(R.anim.activity_push_up_in, R.anim.activity_push_up_out);
                break;
            case 3:
                Intent i2 = new Intent(getApplicationContext(),Sell.class);
                startActivity(i2);
                overridePendingTransition(R.anim.activity_push_up_in, R.anim.activity_push_up_out);
                break;
            case 4:
                Intent i3 = new Intent(getApplicationContext(),Manage.class);
                startActivity(i3);
                overridePendingTransition(R.anim.activity_push_up_in, R.anim.activity_push_up_out);
                break;
            case 5:
                Intent i4 = new Intent(getApplicationContext(),About_us.class);
                startActivity(i4);
                overridePendingTransition(R.anim.activity_push_up_in, R.anim.activity_push_up_out);
                break;
            case 6:
                getSharedPreferences("username",MODE_PRIVATE).edit().putInt("loggedIn",0).commit();
                getSharedPreferences("username",MODE_PRIVATE).edit().putString("email","").commit();
                getSharedPreferences("username",MODE_PRIVATE).edit().putString("displayname","").commit();
                final Intent i5 = new Intent(this, login.class);
                startActivity(i5);
                //finish();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
       toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       /* client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.vijay.apppp/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);*/
    }
   /* @Override
    public  void onPause()
    {
        super.onPause();
        overridePendingTransition(R.anim.activity_open_scale,R.anim.activity_close_translate);
    }*/
    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        /*Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.vijay.apppp/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }*/
}}

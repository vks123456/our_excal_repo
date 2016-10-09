package gawds.nitkkr.com.selleasy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Buy extends AppCompatActivity {
    //Defining android ListView
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    Toolbar toolbar;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    ListView mListView;

    //Elements that will be displayed in android ListView
    String[] Items = new String[]{"Electronics ", "Furniture", "Books/Notes",
            "Others"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);


        mListView = (ListView) findViewById(R.id.list);
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, Items);

        mListView.setAdapter(itemAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //The position where the list item is clicked is obtained from the
                //the parameter position of the android listview
                int itemPosition = position;

                //Get the String value of the item where the user clicked
                String itemValue = (String) mListView.getItemAtPosition(position);
                Intent intent= new Intent(Buy.this,productList.class);
                switch (itemPosition) {
                    case 0:
                        intent.putExtra("category","Electronics");
                        startActivity(intent);
                        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
                        break;
                    case 1:
                        intent.putExtra("category","furniture");
                        startActivity(intent);
                        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
                        break;
                    case 2:
                        intent.putExtra("category","Books/Notes");
                        startActivity(intent);
                        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
                        break;
                    case 3:
                        intent.putExtra("category","Others");
                        startActivity(intent);
                        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_scale);
                        break;

                }
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


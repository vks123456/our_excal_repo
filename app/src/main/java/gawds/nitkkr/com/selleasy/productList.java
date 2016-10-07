package gawds.nitkkr.com.selleasy;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class productList extends AppCompatActivity {
    String id;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);
        recyclerView = (RecyclerView) findViewById(R.id.buy_products_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        id = getIntent().getStringExtra("category");
        Log.d("id ",id);
        JSON();
    }
public void JSON()
{
    final Context c;
    c=this;
    class JSONTask extends AsyncTask<String, String, String> {

        String JSONString;

        @Override
        protected String doInBackground(String... params) {
            httpRequest http = new httpRequest();
            try {
                JSONString = http.SendGetRequest(params[0]);
                Log.d("JSON",JSONString);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                return JSONString;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            ArrayList<models> arr;

            arr = new ArrayList<>();
            try {
                JSONObject parent = new JSONObject(JSONString);
                JSONArray parentarr = parent.getJSONArray("product");

                for (int i = 0; i < parentarr.length(); i++) {
                    JSONObject finalobject = parentarr.getJSONObject(i);
                    models m = new models();
                    m.setPno(finalobject.getInt("pno"));
                    m.setPname(finalobject.getString("pname"));
                    m.setPrice(finalobject.getInt("price"));
                    m.setCategory(finalobject.getString("category"));
                    m.setContact(finalobject.getInt("contact"));
                    m.setImage(finalobject.getString("image"));
                    m.setUsername(finalobject.getString("username"));
                    m.setName(finalobject.getString("name"));
                    arr.add(m);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                buyAdapter buyAdapter = new buyAdapter(c, arr);
                recyclerView.setAdapter(buyAdapter);
            }
        }
    }
    new JSONTask().execute("http://www.almerston.com/excalibur/product.php?category='"+id+"'");
}
}
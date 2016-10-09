package gawds.nitkkr.com.selleasy;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class productList extends AppCompatActivity {
    String id;
    RecyclerView recyclerView;
    public TextView item_msg;
    EditText searchq;
    Button searchb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list);
         item_msg=(TextView)findViewById(R.id.no_item_msg);
        recyclerView = (RecyclerView) findViewById(R.id.buy_products_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        searchq=(EditText) findViewById(R.id.search_query);
        searchb=(Button) findViewById(R.id.search_b);
        searchb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchClicked();
            }
        });
        id = getIntent().getStringExtra("category");
        Log.d("id ",id);
        JSON("http://www.almerston.com/excalibur/product.php?category='"+id+"'");
    }
    public void searchClicked()
    {
        String query=searchq.getText().toString().toLowerCase();
        JSON("http://www.almerston.com/excalibur/search.php?pname="+query+"&category="+id);
    }
public void JSON(String q)
{
    final Context c;
    c=this;
    class JSONTask extends AsyncTask<String, String, String> {

        String JSONString;
        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            pd=new ProgressDialog(productList.this);
            pd.setMessage("Loading...");
            pd.show();
        }

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
                    m.setContact(finalobject.getLong("contact"));
                    m.setImage(finalobject.getString("image"));
                    m.setUsername(finalobject.getString("username"));
                    m.setName(finalobject.getString("name"));
                    arr.add(m);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                pd.dismiss();
                if(arr.size()==0)
                {
                    recyclerView.setVisibility(View.INVISIBLE);
                }
                else{
                buyAdapter buyAdapter = new buyAdapter(c, arr);
                recyclerView.setAdapter(buyAdapter);
                    item_msg.setVisibility(View.INVISIBLE);
            }
        }
    }}
    new JSONTask().execute(q);
}
}
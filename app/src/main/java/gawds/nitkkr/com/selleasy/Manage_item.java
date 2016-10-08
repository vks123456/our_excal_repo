package gawds.nitkkr.com.selleasy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Manage_item extends AppCompatActivity {

    public static models detailedObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_item);
        final EditText change_price=(EditText)findViewById(R.id.edit_price);
        final EditText change_phone=(EditText)findViewById(R.id.edit_phone);
        TextView sname=(TextView)findViewById(R.id.seller_name);
        Button update=(Button)findViewById(R.id.save_data);
        Button delete=(Button)findViewById(R.id.delete_product);
        change_price.setText(""+detailedObject.getPrice());
        change_phone.setText(""+detailedObject.getContact());
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_price.setText(""+detailedObject.getPrice());
                change_phone.setText(""+detailedObject.getContact());
                change_price.getText();
                change_phone.getText();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpRequest request=new httpRequest();
                request.SendGetRequest("http://www.almerston.com/excalibur/delete_product.php");
            }
        });
    }
}

package gawds.nitkkr.com.selleasy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class Manage_item extends AppCompatActivity {

    public static models detailedObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_item);
        final EditText change_price=(EditText)findViewById(R.id.edit_price);
        final EditText change_phone=(EditText)findViewById(R.id.edit_phone);
        TextView sname=(TextView)findViewById(R.id.seller_name);
        ImageView manage=(ImageView) findViewById(R.id.manage_item);
        Button update=(Button)findViewById(R.id.save_data);
        Button delete=(Button)findViewById(R.id.delete_product);
        change_price.setText(""+detailedObject.getPrice());
        change_phone.setText(""+detailedObject.getContact());
        sname.setText(detailedObject.getPname());
        Glide.with(Manage_item.this).load("http://www.almerston.com/excalibur/images/"+detailedObject.getImage()+".jpeg").diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(manage);
        update.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if(change_phone.getText()!=null && change_phone.getText().toString()!="" &&change_price.getText()!=null && change_price.getText().toString()!="") {
                                              if(change_phone.length()==10) {
                                                  final ProgressDialog pd = new ProgressDialog(Manage_item.this, ProgressDialog.STYLE_SPINNER);
                                                  pd.setMessage("Loading...");

                                                  pd.show();
                                                  final httpRequest request = new httpRequest();
                                                  Thread thread = new Thread(new Runnable() {
                                                      @Override
                                                      public void run() {

                                                          request.SendGetRequest("http://www.almerston.com/excalibur/update_product.php?pno=" + detailedObject.getPno() + "&price=" + change_price.getText() + "&contact=" + change_phone.getText());
                                                          pd.dismiss();
                                                      }
                                                  });
                                                  thread.start();
                                              }
                                          }
                                      }

                                  }
        );

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final httpRequest request=new httpRequest();
                final ProgressDialog pd = new ProgressDialog(Manage_item.this, ProgressDialog.STYLE_SPINNER);
                pd.setMessage("Loading...");
                pd.show();
                Thread thread=new Thread(new Runnable() {

                    @Override
                    public void run() {
                        request.SendGetRequest("http://www.almerston.com/excalibur/delete_product.php?pno="+detailedObject.getPno());
pd.dismiss();
                        startActivity(new Intent(Manage_item.this,Manage.class));
                    }
                });
        thread.start();
            }
        });

    }
}

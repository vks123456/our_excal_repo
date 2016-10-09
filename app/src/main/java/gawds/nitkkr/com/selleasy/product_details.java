package gawds.nitkkr.com.selleasy;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class product_details extends AppCompatActivity {

    TextView pname, price, seller, username;
    ImageView image;
    Button call, addFav;

    public static models detailedObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        image = (ImageView) findViewById(R.id.detail_image);
        pname = (TextView) findViewById(R.id.detail_name);
        price = (TextView) findViewById(R.id.detail_price);
        seller = (TextView) findViewById(R.id.detail_seller_name);
        username = (TextView) findViewById(R.id.detail_username);
        call = (Button) findViewById(R.id.call);
        addFav = (Button) findViewById(R.id.fav);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+detailedObject.getContact()));
                startActivity(intent);
            }
        });
//        getActionBar().setHomeButtonEnabled(true);
        pname.setText(""+detailedObject.getPname());
        price.setText(""+detailedObject.getPrice());
        seller.setText(""+detailedObject.getName());
        username.setText(""+detailedObject.getUsername());
        Glide.with(this).load("http://www.almerston.com/excalibur/images/"+detailedObject.getImage()+".jpeg").placeholder(R.drawable.buy_icon).crossFade().into(image);

    }
}

package gawds.nitkkr.com.selleasy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class About_us extends AppCompatActivity {
    private ImageView imageViewRound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        imageViewRound= (ImageView) findViewById(R.id.imageView_round);
        Bitmap icon = BitmapFactory.decodeResource(getResources(), R.drawable.vijay);

        imageViewRound.setImageBitmap(icon);
    }
}

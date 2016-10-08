package gawds.nitkkr.com.selleasy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.HashMap;

public class otpVerify extends AppCompatActivity {
    public static HashMap<String, String> map;
    public static String  selectedphoto;
    static int checkOtp;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        final EditText otp = (EditText) findViewById(R.id.get_otp);
        Button verify_button = (Button) findViewById(R.id.verifyotp);

            verify_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (otp.getText().toString() != "" && otp.getText() != null) {
                        if (checkOtp == Integer.parseInt("0" + otp.getText().toString())) {
                            try {


                                Bitmap bitmap = ImageLoader.init().from(selectedphoto).requestSize(100, 100).getBitmap();
                                String encodedImage = ImageBase64.encode(bitmap);
                                Log.d("Data ", map.get("pname") + " " + map.get("price") + " " + map.get("name") + " " + map.get("contact") + " " + map.get("category") + " ");
                                map.put("image", encodedImage);
                                PostResponseAsyncTask task = new PostResponseAsyncTask(otpVerify.this, map, new AsyncResponse() {
                                    @Override
                                    public void processFinish(String s) {
                                        if (true) {
                                            Log.d("Error ", s);
                                            Toast.makeText(otpVerify.this, "Success " + s, Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(otpVerify.this, Manage.class);
                                            startActivity(i);
                                        } else {
                                            // faliiure
                                        }
                                    }
                                });
                                try {
                                    task.execute("http://www.almerston.com/excalibur/upload_image.php");
                                } catch (Exception e) {
                                    task.execute("http://www.almerston.com/excalibur/upload_image.php");

                                }
                                task.setEachExceptionsHandler(new EachExceptionsHandler() {
                                    @Override
                                    public void handleIOException(IOException e) {
                                        Toast.makeText(getApplicationContext(), "Server Connection Faliure", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void handleMalformedURLException(MalformedURLException e) {


                                    }

                                    @Override
                                    public void handleProtocolException(ProtocolException e) {

                                    }

                                    @Override
                                    public void handleUnsupportedEncodingException(UnsupportedEncodingException e) {

                                    }
                                });
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(otpVerify.this, "Invalid Otp!!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("otpVerify Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}

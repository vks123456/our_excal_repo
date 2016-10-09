package gawds.nitkkr.com.selleasy;

import android.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;

import android.content.Intent;

import android.content.SharedPreferences;
import android.database.Cursor;

import android.graphics.Bitmap;

import android.graphics.BitmapFactory;

import android.graphics.Matrix;
import android.net.Uri;

import android.os.Bundle;

import android.app.Activity;

import android.os.Environment;

import android.provider.MediaStore;

import android.util.Log;

import android.view.Menu;

import android.view.MotionEvent;
import android.view.View;

import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Spinner;
import android.widget.Toast;

import com.kosalgeek.android.photoutil.CameraPhoto;
import com.kosalgeek.android.photoutil.GalleryPhoto;
import com.kosalgeek.android.photoutil.ImageBase64;
import com.kosalgeek.android.photoutil.ImageLoader;
import com.kosalgeek.genasync12.AsyncResponse;
import com.kosalgeek.genasync12.EachExceptionsHandler;
import com.kosalgeek.genasync12.PostResponseAsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static gawds.nitkkr.com.selleasy.login.personEmail;


public class Sell extends Activity {

    EditText spname,spprice,sphone,sseller;
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                activity.getCurrentFocus().getWindowToken(), 0);
    }
    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(Sell.this);
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }
    ImageView viewImage;
    String selected_category;
    Button b,button;


    private ImageView imgView;
    private Button upload,cancel;
    CameraPhoto cameraPhoto;
    GalleryPhoto galleryPhoto;
    final  int CAMERA_REQUEST=1;
    final int GALLERY_REQUEST=2;
    String email,displayname;
    String selectedphoto;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        final Context context = this;
        setContentView(R.layout.activity_sell);

        super.onCreate(savedInstanceState);
        SharedPreferences username = getSharedPreferences("username", MODE_PRIVATE);
        email= username.getString("email", "sahil071");
        displayname=username.getString("displayname",null);
        imgView=(ImageView) findViewById(R.id.imgView);
        spname= (EditText) findViewById(R.id.spname);
        spprice=(EditText) findViewById(R.id.spprice);
        sphone= (EditText) findViewById(R.id.sphone);
        sseller=(EditText) findViewById(R.id.ssellername);
        setupUI(findViewById(R.id.parent));
        final int pos;
        final Spinner spin_cat=(Spinner)findViewById(R.id.spin_cat);
        cameraPhoto=new CameraPhoto(getApplicationContext());
        galleryPhoto=new GalleryPhoto(getApplicationContext());
        final List<String> categoies = new ArrayList<String>();
        categoies.add("Electronics");
        categoies.add("furniture");
        categoies.add("Books/Notes");
        categoies.add("Others");
        spin_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected_category = categoies.get(position);
//                Toast.makeText(Sell.this,selected_category,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> data =new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,categoies);
        data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_cat.setAdapter(data);
        b=(Button)findViewById(R.id.btnSelectPhoto);

       // viewImage=(ImageView)findViewById(R.id.viewImage);


        b.setOnClickListener(new OnClickListener() {

            @Override

            public void onClick(View v) {

                selectImage();

            }

        });

                final HashMap<String,String>map=new HashMap<String, String>();

//                imgView = (ImageView) findViewById(R.id.btnSelectPhoto);
                upload = (Button) findViewById(R.id.buttonAlert);
//                cancel = (Button) findViewById(R.id.imgcancelbtn);

                upload.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        map.put("pname", spname.getText().toString());
                        map.put("price", spprice.getText().toString());
                        map.put("name", sseller.getText().toString());
                        map.put("contact", sphone.getText().toString());
                        map.put("category", spin_cat.getSelectedItem().toString());
                        map.put("username", email);
                        if (selectedphoto == "" || selectedphoto == null || map.get("pname") == "" || map.get("price") == "" || map.get("name") == "" || map.get("contact") == "" || map.get("username") == "" || map.get("contact").length() != 10) {
                            Toast.makeText(Sell.this, "Invalid Details", Toast.LENGTH_SHORT).show();
                        } else {

                            final ProgressDialog pd = new ProgressDialog(Sell.this ,ProgressDialog.STYLE_SPINNER);
                            pd.setMessage("Loading...");

                            pd.show();
                            final httpRequest request = new httpRequest();
                            final int otp =1000; //new Random().nextInt(9000) + 1000;
                            Thread thread = new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    String json = request.SendGetRequest("http://www.almerston.com/excalibur/otp.php?otp=" + otp + "&contact=" + map.get("contact"));
                                    //Error handling for message sent or not 9pm
                                    try

                                    {
                                        JSONObject ob = null;
                                        ob = new JSONObject(json);
                                        JSONArray array = ob.getJSONArray("errors");
                                        JSONObject newob = array.getJSONObject(0);
                                        String res = newob.getString("status");
                                        if (!res.contains("faliure")) {
                                            otpVerify.checkOtp = otp;
                                            otpVerify.map = map;
                                            otpVerify.selectedphoto = selectedphoto;
                                            pd.dismiss();
                                            startActivity(new Intent(Sell.this, otpVerify.class));

                                        } else {
                                            pd.dismiss();
                                            Toast.makeText(Sell.this,"Unable to upload! Try Again Later!",Toast.LENGTH_SHORT).show();
                                            Log.d("dfsnfj", res);
                                            //Unable to send otp// Try again later!
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }


                                }

                            });
                            thread.start();
//
                        }
                    }});
    }


    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds options to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }



    private void selectImage() {



        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };



        AlertDialog.Builder builder = new AlertDialog.Builder(Sell.this);

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {

                    try {
                        startActivityForResult(cameraPhoto.takePhotoIntent(),CAMERA_REQUEST);
                        cameraPhoto.addToGallery();
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(),"error in camera",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }

                }

                else if (options[item].equals("Choose from Gallery"))

                {

                    startActivityForResult(galleryPhoto.openGalleryIntent(),GALLERY_REQUEST);


                }

                else if (options[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();

    }



    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Uri selectedImageUri ;
        Bitmap bitmap;
        switch (requestCode) {
            case GALLERY_REQUEST:
                if (resultCode == Activity.RESULT_OK) {
                    selectedImageUri = data.getData();
                    galleryPhoto.setPhotoUri(selectedImageUri);
                    try {
                        selectedphoto=galleryPhoto.getPath();
                        bitmap= ImageLoader.init().from(galleryPhoto.getPath()).requestSize(256,256).getBitmap();
                        imgView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CAMERA_REQUEST:
                if (resultCode == RESULT_OK) {

                    try {
                        selectedphoto=cameraPhoto.getPhotoPath();
                        bitmap= ImageLoader.init().from(cameraPhoto.getPhotoPath()).requestSize(256,256).getBitmap();
                        imgView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(this, "Picture was not taken", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    private Bitmap rotate(Bitmap source, float angle)
    {
        Matrix matrix=new Matrix();
        matrix.postRotate(angle);
        Bitmap bitmap=Bitmap.createBitmap(source,0,0,source.getWidth(),source.getHeight(),matrix,true);
        return bitmap;
    }

}


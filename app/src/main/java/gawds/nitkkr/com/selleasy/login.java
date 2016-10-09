package gawds.nitkkr.com.selleasy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import gawds.nitkkr.com.selleasy.R;

public class login extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    @Override
    protected void onResume() {
        super.onResume();
        int status = getSharedPreferences("username", MODE_PRIVATE).getInt("loggedIn", 0);
        if (status == 1) {
            startActivity(new Intent(login.this,MainActivity.class));
        }
    }
     @Override
    protected void onRestart() {
        super.onRestart();
        int status = getSharedPreferences("username", MODE_PRIVATE).getInt("loggedIn", 0);
        if (status == 1) {

        }
    }

    TextView tv_username;
    GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 9001;
    public static  String personEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        int status=getSharedPreferences("username",MODE_PRIVATE).getInt("loggedIn",0);
        if(status==1)
        {
            startActivity(new Intent(login.this,MainActivity.class));

        }

        tv_username = (TextView) findViewById(R.id.tv_username);

        //Register both button and add click listener
        findViewById(R.id.xyz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signIn();

            }
        });
        //findViewById(R.id.btn_logout).setOnClickListener(this);
        Bundle bundle = getIntent().getExtras();
//        String message = bundle.getString("message");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {
                        tv_username.setText("");
                    }
                });
    }
    public  void logoff()
    {
        signOut();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            tv_username.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            //Log.d("kjdkjxkf",acct.getDisplayName());
             personEmail = acct.getEmail();
                String name=acct.getDisplayName();
            SharedPreferences.Editor editor = getSharedPreferences("username", MODE_PRIVATE).edit();
            editor.putString("email", personEmail);
            editor.putString("displayname",name);
            editor.putInt("loggedIn",1);
            editor.commit();
            //login.gapi= mGoogleApiClient;
//            mjnn
            Intent intent = new Intent(login.this,MainActivity.class);
//
            intent.putExtra("message", acct.getDisplayName());
            //here
            MainActivity.object=mGoogleApiClient;
            //intent.putExtra("image", personPhoto);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_open_translate,R.anim.activity_close_scale);

        }
    }
}
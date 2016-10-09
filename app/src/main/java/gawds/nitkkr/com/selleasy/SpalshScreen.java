package gawds.nitkkr.com.selleasy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
/**
 * Created by vijay on 26/9/16.
 */
public class SpalshScreen extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent intent = new Intent(SpalshScreen.this,login.class);
                    intent.putExtra("message","Message");
                    startActivity(intent);
                }
            }
        };
        timerThread.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}

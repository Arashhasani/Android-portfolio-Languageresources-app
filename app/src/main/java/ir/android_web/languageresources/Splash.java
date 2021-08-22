package ir.android_web.languageresources;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final Thread thread = new Thread(){
          public void run(){

              try {
                  sleep(3000);
              }catch (Exception e){

              }finally {

                  Intent intent = new Intent(Splash.this,Main.class);
                  startActivity(intent);
                  finish();
              }


          }


        };
        thread.start();
    }
}

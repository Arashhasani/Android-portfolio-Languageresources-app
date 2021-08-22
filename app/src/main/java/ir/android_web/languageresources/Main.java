package ir.android_web.languageresources;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.lang.reflect.Method;

public class Main extends AppCompatActivity {

    DrawerLayout drawer;
    NavigationView navigation ;
    ImageView wifiimg;
    Toolbar toolbar;
    TextView connectiontext;
    CardView storycard , studybookcard ,listeningbookcard , grammarbookcard, vocabbookcard , exambookcard ;
    Button connectionerrorbtn;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home){
            drawer.openDrawer(Gravity.LEFT);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        cast();
        toolbar.setTitle("Categories");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        storycard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this , BookList.class);
                intent.putExtra("array" , "story");
                startActivity(intent);
            }
        });



        studybookcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this , BookList.class);
                intent.putExtra("array" , "study");
                startActivity(intent);
            }
        });



        listeningbookcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this , BookList.class);
                intent.putExtra("array" , "listening");
                startActivity(intent);
            }
        });




        grammarbookcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this , BookList.class);
                intent.putExtra("array" , "grammar");
                startActivity(intent);
            }
        });




        vocabbookcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this , BookList.class);
                intent.putExtra("array" , "word");
                startActivity(intent);
            }
        });




        exambookcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this , BookList.class);
                intent.putExtra("array" , "exam");
                startActivity(intent);
            }
        });

        storycard.setVisibility(View.GONE);
        studybookcard.setVisibility(View.GONE);
        listeningbookcard.setVisibility(View.GONE);
        grammarbookcard.setVisibility(View.GONE);
        vocabbookcard.setVisibility(View.GONE);
        exambookcard.setVisibility(View.GONE);
        connectionerrorbtn.setVisibility(View.GONE);
        wifiimg.setVisibility(View.GONE);
        connectiontext.setVisibility(View.GONE);

        getdata();
        connectionerrorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getdata();
            }
        });


        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id==R.id.about){
                    Intent intent = new Intent(Main.this,About.class);
                    startActivity(intent);
                }else if (id==R.id.contact){
                    Intent intent = new Intent(Main.this,Contact.class);
                    startActivity(intent);
                }else if (id==R.id.exit){
                    ProgressDialog.Builder builder  = new AlertDialog.Builder(Main.this);
                    builder.setMessage("Are you Sure To Exit  ? ");
                    builder.setCancelable(false);

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create().show();
                }
                drawer.closeDrawer(Gravity.LEFT);


                return false;
            }
        });
    }


    public void cast(){
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        navigation=(NavigationView)findViewById(R.id.navigation);
        toolbar=(Toolbar)findViewById(R.id.toobar);
        storycard=(CardView)findViewById(R.id.storycard);
        studybookcard=(CardView)findViewById(R.id.studybookcard);
        listeningbookcard=(CardView)findViewById(R.id.listeningbookcard);
        grammarbookcard=(CardView)findViewById(R.id.grammarbookcard);
        vocabbookcard=(CardView)findViewById(R.id.vocabbookcard);
        exambookcard=(CardView)findViewById(R.id.exambookcard);
        connectionerrorbtn=(Button)findViewById(R.id.connectionerrorbtn);
        wifiimg=(ImageView)findViewById(R.id.wifiimg);
        connectiontext=(TextView)findViewById(R.id.connectiontext);

    }

    @Override
    public void onBackPressed() {


        if (drawer.isDrawerOpen(Gravity.LEFT)){
            drawer.closeDrawer(Gravity.LEFT);
        }else {
            ProgressDialog.Builder builder  = new AlertDialog.Builder(Main.this);
            builder.setMessage("Are you Sure To Exit  ? ");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
        }

    }

    public void getdata(){

        String url = "http://android-web.ir/resume/languageresources.json";
        final ProgressDialog progressDialog  = new ProgressDialog(Main.this);
        progressDialog.setMessage("Pleae Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Response.Listener<JSONObject> jsonObjectListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                storycard.setVisibility(View.VISIBLE);
                studybookcard.setVisibility(View.VISIBLE);
                listeningbookcard.setVisibility(View.VISIBLE);
                grammarbookcard.setVisibility(View.VISIBLE);
                vocabbookcard.setVisibility(View.VISIBLE);
                exambookcard.setVisibility(View.VISIBLE);
                wifiimg.setVisibility(View.GONE);
                connectionerrorbtn.setVisibility(View.GONE);
                connectiontext.setVisibility(View.GONE);
                progressDialog.dismiss();
            }

        };


        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                storycard.setVisibility(View.GONE);
                studybookcard.setVisibility(View.GONE);
                listeningbookcard.setVisibility(View.GONE);
                grammarbookcard.setVisibility(View.GONE);
                vocabbookcard.setVisibility(View.GONE);
                exambookcard.setVisibility(View.GONE);
                wifiimg.setVisibility(View.VISIBLE);
                connectiontext.setVisibility(View.VISIBLE);
                connectionerrorbtn.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
            }
        };


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,jsonObjectListener,errorListener);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        AppController.getInstance().getRequestQueue().getCache().clear();

    }
}

package ir.android_web.languageresources;

import android.app.ProgressDialog;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ir.android_web.languageresources.Adapter.BookAdapter;

public class BookList extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView bookrecycle;
    ArrayList<BookItem>bookItems  = new ArrayList<>();
    BookAdapter bookAdapter;
    ImageView wifiimg;
    Button connectionerrorbtn;
    TextView connectiontext ;
    SwipeRefreshLayout swipe;
    CardView noitemtext;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        cast();
        wifiimg.setVisibility(View.GONE);
        connectiontext.setVisibility(View.GONE);
        connectionerrorbtn.setVisibility(View.GONE);
        Bundle bundle = getIntent().getExtras();
//        Toast.makeText(getApplicationContext(),""+bundle.getString("array"), Toast.LENGTH_SHORT).show();
        final String arrayname = bundle.getString("array");
        connectionerrorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata(arrayname);
            }
        });
        noitemtext.setVisibility(View.GONE);

        toolbar.setTitle("Books List");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookAdapter = new BookAdapter(bookItems,BookList.this);
        bookrecycle.setLayoutManager(new LinearLayoutManager(BookList.this));
        bookrecycle.setAdapter(bookAdapter);
        getdata(arrayname);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                bookItems.clear();
                getdata(arrayname);
                swipe.setRefreshing(false);
            }
        });

    }


    public void cast(){

        toolbar=(Toolbar)findViewById(R.id.toobar);
        bookrecycle=(RecyclerView)findViewById(R.id.bookrecycle);
        wifiimg=(ImageView)findViewById(R.id.wifiimg);
        connectiontext=(TextView)findViewById(R.id.connectiontext);
        connectionerrorbtn=(Button)findViewById(R.id.connectionerrorbtn);
        swipe=(SwipeRefreshLayout)findViewById(R.id.swipe);
        noitemtext=(CardView) findViewById(R.id.noitemtext);
    }

    public void  getdata(final String arrayname){



        String url = "http://android-web.ir/resume/languageresources.json";
        final ProgressDialog progressDialog  = new ProgressDialog(BookList.this);
        progressDialog.setMessage("Pleae Wait");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Response.Listener <JSONObject> jsonObjectListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                JSONArray jsonArray = new JSONArray();
                try {
                    jsonArray = response.getJSONArray(arrayname);
                    if (jsonArray.length()==0) {

                        noitemtext.setVisibility(View.VISIBLE);
                    } else {


                        for (int i = 0; i < jsonArray.length(); i++) {

                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String name = jsonObject.getString("name");
                            String pik = jsonObject.getString("pic");
                            String link = jsonObject.getString("link");
                            bookItems.add(new BookItem(name, link, pik));
                            bookAdapter.notifyDataSetChanged();


                        }
                    }


                    } catch(JSONException e){
                        e.printStackTrace();
                    }


                connectionerrorbtn.setVisibility(View.GONE);
                wifiimg.setVisibility(View.GONE);
                connectiontext.setVisibility(View.GONE);

                progressDialog.dismiss();
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                wifiimg.setVisibility(View.VISIBLE);
                connectionerrorbtn.setVisibility(View.VISIBLE);
                connectiontext.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
            }
        };

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null,jsonObjectListener,errorListener);
        AppController.getInstance().addToRequestQueue(jsonObjectRequest);
        AppController.getInstance().getRequestQueue().getCache().clear();









//        bookItems.add(new BookItem("American English 2" , "Link Is" , "Pik link"));
//        bookItems.add(new BookItem("American English 2" , "Link Is" , "Pik link"));
//        bookItems.add(new BookItem("American English 2" , "Link Is" , "Pik link"));
//        bookItems.add(new BookItem("American English 2" , "Link Is" , "Pik link"));
//        bookAdapter.notifyDataSetChanged();


    }
}

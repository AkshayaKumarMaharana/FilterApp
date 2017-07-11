package com.a16gmail.maharana.akshaya.filterdemo;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    ListView lvBikeList;
    ArrayAdapter<String> arrayAdapter;
    ArrayList<String> stringArrayList;
    ProgressDialog pdia;
    String SERVER_URL = "https://2andfour.000webhostapp.com/test.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        stringArrayList = new ArrayList<>();
        lvBikeList = (ListView)findViewById(R.id.lvBikelist);

        new DisplayAsyncTask().execute(SERVER_URL);

        arrayAdapter = new ArrayAdapter<String>(DisplayActivity.this,android.R.layout.simple_list_item_1,stringArrayList);
        lvBikeList.setAdapter(arrayAdapter);
    }

    class DisplayAsyncTask extends AsyncTask<String,Void,Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pdia = new ProgressDialog(DisplayActivity.this);
            pdia.setMessage("Loading...");
            pdia.show();
        }

        @Override
        protected Boolean doInBackground(String... params) {
            try{
                Bundle bundle = getIntent().getExtras();
                String startdate = bundle.getString("startdate");
                String enddate = bundle.getString("enddate");

                String link = "https://2andfour.000webhostapp.com/test.php?stdate="+startdate+"&eddate="+enddate;

                URL url = new URL(link);
                HttpClient client = new DefaultHttpClient();
                HttpGet request = new HttpGet();
                request.setURI(new URI(link));
                HttpResponse response = client.execute(request);
                int status = response.getStatusLine().getStatusCode();
                if(status==200){
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        String title = object.getString("title");
                        String desc = object.getString("desc");
                        stringArrayList.add(title+"\n"+desc);
                    }
                }
            }
            catch (Exception e){
                Toast.makeText(DisplayActivity.this,"Error",Toast.LENGTH_LONG).show();
            }

            return false;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            arrayAdapter.notifyDataSetChanged();
            super.onPostExecute(aBoolean);
            pdia.dismiss();
        }
    }
}
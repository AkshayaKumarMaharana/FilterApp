package com.a16gmail.maharana.akshaya.filterdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final EditText sday = (EditText)findViewById(R.id.sday);
        final EditText smonth = (EditText)findViewById(R.id.smonth);
        final EditText syear = (EditText)findViewById(R.id.syear);
        final EditText stime = (EditText)findViewById(R.id.stime);

        final EditText eday = (EditText)findViewById(R.id.eday);
        final EditText emonth = (EditText)findViewById(R.id.emonth);
        final EditText eyear = (EditText)findViewById(R.id.eyear);
        final EditText etime = (EditText)findViewById(R.id.etime);

        Button btnShow = (Button)findViewById(R.id.btnShow);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startdate = ""+syear.getText().toString()+""+smonth.getText().toString()+""+sday.getText().toString()+""+stime.getText().toString();
                String enddate = eyear.getText().toString()+""+emonth.getText().toString()+""+eday.getText().toString()+""+etime.getText().toString();

                Intent i = new Intent(StartActivity.this,DisplayActivity.class);

                i.putExtra("startdate",startdate);
                i.putExtra("enddate",enddate);
                startActivity(i);
            }
        });

    }
}
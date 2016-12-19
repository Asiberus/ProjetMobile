package org.esiea.beekmann_bensimhon.projetmobile;

import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompatBase;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private DatePickerDialog dpd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_hw = (TextView)findViewById(R.id.tv_hello_world);
        String now = DateUtils.formatDateTime(getApplicationContext(), (new Date()).getTime(), DateFormat.FULL);
        tv_hw.setText(now+" " + getString(R.string.text));

        Button btn_hw = (Button)findViewById(R.id.btn_hello_world);
        Button btn_it = (Button)findViewById(R.id.btn_intent);
        MenuItem mItem  = (MenuItem)findViewById(R.id.item_toast_me) ;


        btn_hw.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Toast.makeText(getApplicationContext(), "Choisir la date de commande", Toast.LENGTH_LONG).show();
                dpd.show();
            }
        });

        btn_it.setOnClickListener(new View.OnClickListener() {

            public void runIntent(){
                Intent i = new Intent(getApplicationContext(), SecondeActivity.class);
                startActivity(i);
            }
            @Override
            public void onClick(View view) {
                runIntent();

                //startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=Londre")));
            }
        });

        DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                TextView tv_hw = (TextView)findViewById(R.id.tv_hello_world);
                tv_hw.setText(i2 + "/" + i1 + "/" + i+ " \n" + getString(R.string.text));
            }
        };

        this.dpd = new DatePickerDialog(this, odsl,1,1,1995);



    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_toast_me){
            Toast.makeText(getApplicationContext(), "Ceci est un toast!", Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }




}

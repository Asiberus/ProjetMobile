package org.esiea.beekmann_bensimhon.projetmobile;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


public class SecondeActivity extends AppCompatActivity implements onListener{

    public static final String BIERS_UPDATE = "com.octip.cours.inf4042_11.BIERS_UPDATE";
    public BiersAdapter ba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seconde);

        IntentFilter intentFilter = new IntentFilter(SecondeActivity.BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(), intentFilter);

        GetBiersServices.startActionBiers(this);

        final RecyclerView rcv = (RecyclerView)findViewById(R.id.rv_biere);
        rcv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


        ba = new BiersAdapter(getBiersFromFile());

        rcv.setAdapter(ba);

    }

    public JSONArray getBiersFromFile(){
        try{
            InputStream is = new FileInputStream(getCacheDir() + "/" + "bieres.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer,"UTF-8"));
        } catch (IOException e){
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e){
            e.printStackTrace();
            return new JSONArray();
        }
    }

    public void onFinish(){
        ba.setNewBiere(getBiersFromFile());
    }

    public class BierUpdate extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent){

            NotificationCompat.Builder ncb1 = (NotificationCompat.Builder)new NotificationCompat.Builder(getApplicationContext()).setContentTitle("Download finish").setContentText("The downloading of bieres.json is finished.").setSmallIcon(R.drawable.main_icon);
            NotificationManager mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            mNM.notify(1, ncb1.build());
        }
    }
}

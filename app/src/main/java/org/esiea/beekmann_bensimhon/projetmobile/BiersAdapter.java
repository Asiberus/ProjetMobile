package org.esiea.beekmann_bensimhon.projetmobile;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder> {

    private JSONArray biers;

    public BiersAdapter(JSONArray biers){

        this.biers = biers;
    }

    public BierHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater lf = LayoutInflater.from(parent.getContext());
        View v = lf.inflate(R.layout.rv_biere_element, null);

        BierHolder bh = new BierHolder(v);
        return bh;

    }

    public void onBindViewHolder(BierHolder holder, int position){
        try {
            JSONObject jsonObject = biers.getJSONObject(position);
            holder.name.setText(jsonObject.getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getItemCount(){
     return biers.length();
    }

    public void setNewBiere(JSONArray jsonArray){
        this.biers = jsonArray;
        notifyDataSetChanged();
    }


    public class BierHolder extends RecyclerView.ViewHolder {

        public TextView name;

            public BierHolder(View v){
                super(v);
                name = (TextView) v.findViewById(R.id.rv_biere_name);
            }
    }



}
package com.geodeveloper.nigeriaussdcodes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AllBankAdapter extends ArrayAdapter<AllBankModel> {

    public AllBankAdapter(Context context, ArrayList<AllBankModel> arrayList) {
        super(context, 0,arrayList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView = convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.all_bank_custom_views,parent,false);
        }

        AllBankModel allBankModel = getItem(position);

        ImageView mainIcon = listView.findViewById(R.id.all_bank_main_logo);
        mainIcon.setImageResource(allBankModel.getmImageResourceId());

        TextView title = listView.findViewById(R.id.all_bank_title);
        title.setText(allBankModel.getmTitle());




        return listView;
    }
}


package com.geodeveloper.nigeriaussdcodes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GeneralCodeAdapter extends ArrayAdapter<GeneralCodeModel> {

    public GeneralCodeAdapter(Context context, ArrayList<GeneralCodeModel> arrayList) {
        super(context, 0,arrayList);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getPosition(GeneralCodeModel item) {
        return super.getPosition(item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listView =  convertView;
        if(listView == null){
            listView = LayoutInflater.from(getContext()).inflate(R.layout.general_codes_custom_view,parent,false);
        }

        GeneralCodeModel generalCodeModel = getItem(position);

        TextView codeView = listView.findViewById(R.id.general_codes_text_codes);
        codeView.setText(generalCodeModel.getmCodeView());

        TextView descView =  listView.findViewById(R.id.general_codes_title_text_view);
        descView.setText(generalCodeModel.getmDescriptionView());

        ImageView iconCall = listView.findViewById(R.id.general_codes_call_icon);
        iconCall.setImageResource(R.drawable.icon_calls);


        return listView;
    }
}

package com.geodeveloper.nigeriaussdcodes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.startapp.android.publish.adsCommon.StartAppAd;

import java.util.ArrayList;

public class allBankActivity extends AppCompatActivity {
    private ArrayList<AllBankModel> arrayList;
    private ListView lisView;
    private AllBankAdapter allBankAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_banks);
        arrayList = new ArrayList<>();
        lisView = findViewById(R.id.all_bank_list_view);




        arrayList.add(new AllBankModel(R.drawable.access_logo,"Access Bank",AccessBank.class));
        arrayList.add(new AllBankModel(R.drawable.diamond_bank_logo,"Diamond Bank",DiamondBank.class));
        arrayList.add(new AllBankModel(R.drawable.ecobank_logo,"Ecobank",EcoBank.class));
        arrayList.add(new AllBankModel(R.drawable.fidelity_bank_logo,"Fidelity Bank", FidelityBank.class));
        arrayList.add(new AllBankModel(R.drawable.first_bank_logo,"First Bank", FirstBankActivity.class));
        arrayList.add(new AllBankModel(R.drawable.fcmb_logo,"FCMB", Fcmb.class));
        arrayList.add(new AllBankModel(R.drawable.gtb_logo,"Guaranty trust bank", GtbBank.class));
        arrayList.add(new AllBankModel(R.drawable.heritage_bank_logo,"Heritage bank", HeritageBank.class));

        lisView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AllBankModel allBankModel = arrayList.get(i);
                Intent intent = new Intent(allBankActivity.this,allBankModel.getmContext());
                startActivity(intent);
            }
        });


        allBankAdapter = new AllBankAdapter(this,arrayList);
        lisView.setAdapter(allBankAdapter);
    }

    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

}

package com.geodeveloper.nigeriaussdcodes;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.startapp.android.publish.adsCommon.StartAppAd;

import java.util.ArrayList;

import static android.Manifest.permission.CALL_PHONE;
import static android.view.View.GONE;

public class MtnActivity extends AppCompatActivity {
    private ArrayList<GeneralCodeModel> mtnCodes;
    private ListView listView;
    GeneralCodeAdapter generalCodeAdapter;
    private Toolbar toolbar;
    private ImageView mainLogo;
    private LayoutInflater inflater;
    private EditText numberEditText, pinEditText, amountEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uba_bank);
        mainLogo = findViewById(R.id.main_logo);
        mtnCodes = new ArrayList<>();
        toolbar = findViewById(R.id.uba_bank_tool_bar);
        listView = findViewById(R.id.uba_bank_list_view);


        amountEditText = findViewById(R.id.amount_edit_text);
        numberEditText = findViewById(R.id.number_edit_text);
        pinEditText = findViewById(R.id.pin_edit_text);
        toolbar.setTitle("MTN");
        toolbar.setTitleTextColor(getResources().getColor(R.color.tool_bar_text_color));
        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }
            }
        });

        mainLogo.setImageResource(R.drawable.mtn_logo);


        mtnCodes.add(new GeneralCodeModel("*556#", "Check Airtime balance"));        //0
        mtnCodes.add(new GeneralCodeModel("*123*1*3#", "Check Airtime balance 2"));        //1
        mtnCodes.add(new GeneralCodeModel("*131*4#", "Check data balance"));        //2
        mtnCodes.add(new GeneralCodeModel("*559#", "Check data balance 2"));        //3
        mtnCodes.add(new GeneralCodeModel("*555*", "Recharge Airtime"));        //4
        mtnCodes.add(new GeneralCodeModel("*131#", "Buy data"));        //5
        mtnCodes.add(new GeneralCodeModel("*904#", "Check Airtime/data from bank"));        //6
        mtnCodes.add(new GeneralCodeModel("*606#", "Borrow airtime"));        //7
        mtnCodes.add(new GeneralCodeModel("180", "Call customer care"));        //8
        mtnCodes.add(new GeneralCodeModel("*123*1*1#", "Know my phone number"));        //9
        mtnCodes.add(new GeneralCodeModel("*600*", "Transfer Airtime (default pin:0000"));        //10
        mtnCodes.add(new GeneralCodeModel("*123*5#", "Cancel data auto renewal"));        //11
        mtnCodes.add(new GeneralCodeModel("*133*1*", "Please call me back"));        //12
        mtnCodes.add(new GeneralCodeModel("*123*1*2#", "Change my tariff plan"));        //13
        mtnCodes.add(new GeneralCodeModel("*133*2*", "Please send me credit"));        //14
        mtnCodes.add(new GeneralCodeModel("*159*4#", "Check extra call bonus"));        //15
        mtnCodes.add(new GeneralCodeModel("*159*3#", "Check SMS bonus"));        //16
        mtnCodes.add(new GeneralCodeModel("*410#", "Buy callertunez"));        //17
        mtnCodes.add(new GeneralCodeModel("*406#", "MTN Pulse Night Plan: (N25)"));        //18
        mtnCodes.add(new GeneralCodeModel("*131*104#", "Daily data plan Plan: 50mb +25mb (Night)(N100) 1days"));        //19
        mtnCodes.add(new GeneralCodeModel("*131*113#", "Daily data plan Plan: 150mb +75mb (Night)(N200) 1days"));        //20
        mtnCodes.add(new GeneralCodeModel("*131*104#", "Weekly data plan Plan: 150 (N300) 7days"));        //21
        mtnCodes.add(new GeneralCodeModel("*131*103#", "Weekly data plan Plan: 500mb +250mb (N300) 7days"));        //22
        mtnCodes.add(new GeneralCodeModel("*131*106#", "Monthly data plan Plan: 1gb (N1,000) 30days"));        //23
        mtnCodes.add(new GeneralCodeModel("*131*130#", "Monthly data plan Plan: 1.5gb (N1,200) 30days"));        //24
        mtnCodes.add(new GeneralCodeModel("*131*110#", "Monthly data plan Plan: 2.5gb +1gb (Night) (N2,000) 30days"));        //25
        mtnCodes.add(new GeneralCodeModel("*131*107#", "Monthly data plan Plan: 5gb (N3,500) 30days"));        //26
        mtnCodes.add(new GeneralCodeModel("*131*116#", "Monthly data plan Plan: 10gb (N5,000) 30days"));        //27
        mtnCodes.add(new GeneralCodeModel("*131*117#", "Monthly data plan Plan: 22gb (N10,000) 30days"));        //28
        mtnCodes.add(new GeneralCodeModel("*131*118#", "Monthly data plan Plan: 50gb (N20,000) 30days"));        //29
        mtnCodes.add(new GeneralCodeModel("*131*133#", "Monthly data plan Plan: 85gb (N50,000) 30days"));        //30
        mtnCodes.add(new GeneralCodeModel("*131*5#", "Purchase international bundles"));        //31
        mtnCodes.add(new GeneralCodeModel("*559*7#", "check international bundles balance"));        //32
        mtnCodes.add(new GeneralCodeModel("*344*1#", "Migrate to MTN mPulse 15kb/sec"));        //33
        mtnCodes.add(new GeneralCodeModel("*408#", " MTN xTraSpecial 15kb/sec"));        //34
        mtnCodes.add(new GeneralCodeModel("*123*2*6#", "Migrate to MTN Beta Talk 42kb/sec"));        //35
        mtnCodes.add(new GeneralCodeModel("*406#", "Migrate to MTN pulse 11kb/sec"));        //36
        mtnCodes.add(new GeneralCodeModel("*888*", " MTN AWUFU 45kb/sec"));        //37
        mtnCodes.add(new GeneralCodeModel("*131*2#", " MTN Xtravalue 45kb/sec"));        //38
        mtnCodes.add(new GeneralCodeModel("*555*", " MTN Yafun yafun 45kb/sec"));        //39


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GeneralCodeModel generalCodeModel = mtnCodes.get(i);
                final String codes = generalCodeModel.getmCodeView();
                final long getId = generalCodeAdapter.getItemId(i);

                inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.custom_dialogue, null);
                pinEditText = alertLayout.findViewById(R.id.pin_edit_text);
                amountEditText = alertLayout.findViewById(R.id.amount_edit_text);
                numberEditText = alertLayout.findViewById(R.id.number_edit_text);


                if (getId == 4 || getId == 10 || getId == 12 || getId == 14||getId==37||getId==39) {
                    //*code*PIN#
                    if (getId == 4||getId==37) {
                        numberEditText.setVisibility(GONE);
                        amountEditText.setVisibility(GONE);
                    }
                    //*code*number*amount*pin#
                    if (getId == 10) {

                    }
                    //*codes*number#
                    if (getId == 12 || getId == 14||getId==39) {
                        amountEditText.setVisibility(GONE);
                        pinEditText.setVisibility(GONE);
                    }


                    AlertDialog.Builder alert = new AlertDialog.Builder(MtnActivity.this);
                    alert.setTitle(generalCodeModel.getmDescriptionView());
                    alert.setView(alertLayout);
                    alert.setCancelable(true);

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getBaseContext(), "Canceled", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alert.setPositiveButton("Send", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String data = "";
                            if (getId == 4||getId==37) {
                                data = codes + pinEditText.getText().toString();
                            }
                            if (getId == 10) {
                                data = codes + numberEditText.getText().toString() + "*" + amountEditText.getText().toString() + "*" + pinEditText.getText().toString();
                            }
                            if (getId == 12 || getId == 14||getId==39) {
                                data = codes + numberEditText.getText().toString();
                            }

                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + data + Uri.encode("#")));
                            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(MtnActivity.this, data + "#", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                            } else {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestPermissions(new String[]{CALL_PHONE}, 1);
                                }
                            }
                        }
                    });
                    AlertDialog dialog = alert.create();
                    dialog.show();


                } else {

                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", codes, null));
                    startActivity(intent);
                }


            }
        });


        generalCodeAdapter = new GeneralCodeAdapter(this, mtnCodes);
        listView.setAdapter(generalCodeAdapter);


    }
    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

}

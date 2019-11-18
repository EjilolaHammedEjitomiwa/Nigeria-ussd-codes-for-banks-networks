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

public class NineMobileActivity extends AppCompatActivity {
    private ArrayList<GeneralCodeModel> nineMobileCodes;
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
        nineMobileCodes = new ArrayList<>();
        toolbar = findViewById(R.id.uba_bank_tool_bar);
        listView = findViewById(R.id.uba_bank_list_view);


        amountEditText = findViewById(R.id.amount_edit_text);
        numberEditText = findViewById(R.id.number_edit_text);
        pinEditText = findViewById(R.id.pin_edit_text);
        toolbar.setTitle("9Mobile");
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

        mainLogo.setImageResource(R.drawable.nine_mobile_logo);


        nineMobileCodes.add(new GeneralCodeModel("*232#", "Check Airtime balance"));        //0
        nineMobileCodes.add(new GeneralCodeModel("*228#", "Check data balance"));           //1
        nineMobileCodes.add(new GeneralCodeModel("*200#", "Self service"));                 //2
        nineMobileCodes.add(new GeneralCodeModel("*248#", "Know my phone number"));         //3
        nineMobileCodes.add(new GeneralCodeModel("*222*", "Recharge Airtime"));             //4
        nineMobileCodes.add(new GeneralCodeModel("*665#", "Borrow Airtime"));               //5
        nineMobileCodes.add(new GeneralCodeModel("*266*1*", "Please call me back"));        //6
        nineMobileCodes.add(new GeneralCodeModel("*229*0#", "Disable data auto Renewal"));  //7
        nineMobileCodes.add(new GeneralCodeModel("200", "Call customer care"));             //8
        nineMobileCodes.add(new GeneralCodeModel("*223*", "Transfer Airtime (default pin 0000)"));  //9
        nineMobileCodes.add(new GeneralCodeModel("*229*3*8#", "Daily data plan 10mb(#50)"));  //10
        nineMobileCodes.add(new GeneralCodeModel("*229*3*1#", "Daily data plan 40mb(#100)"));  //11
        nineMobileCodes.add(new GeneralCodeModel("*229*3*10#", "Weekly data plan 150mb(#200)"));  //12
        nineMobileCodes.add(new GeneralCodeModel("*229*3*11#", "Daily night data plan 1gb (#200)"));  //13
        nineMobileCodes.add(new GeneralCodeModel("*229*3*12#", "Monthly data plan 500mb(#500)"));  //14
        nineMobileCodes.add(new GeneralCodeModel("*229*2*7#", "Monthly data plan 1gb(#1,000)"));  //15
        nineMobileCodes.add(new GeneralCodeModel("*229*2*25#", "Monthly data plan 1.5gb(#1,200)"));  //16
        nineMobileCodes.add(new GeneralCodeModel("*229*2*8#", "Monthly data plan 2.5gb(#2,000)"));  //17
        nineMobileCodes.add(new GeneralCodeModel("*229*2*35#", "Monthly data plan 4gb(#3,000)"));  //18
        nineMobileCodes.add(new GeneralCodeModel("*229*2*36#", "Monthly data plan 5.5gb(#4,000)"));  //19
        nineMobileCodes.add(new GeneralCodeModel("*229*2*5#", "Monthly data plan 11.5gb(#8,000)"));  //20
        nineMobileCodes.add(new GeneralCodeModel("*229*4*1#", "Monthly data plan 15gb(#10,000)"));  //21
        nineMobileCodes.add(new GeneralCodeModel("*229*4*3#", "Monthly data plan 27.5gb(#18,000)"));  //22
        nineMobileCodes.add(new GeneralCodeModel("*229*4*5#", "Monthly data plan 100gb(#85,000)"));  //23
        nineMobileCodes.add(new GeneralCodeModel("*229*5*1#", "Quaterly data plan 30gb(#27,500)"));  //24
        nineMobileCodes.add(new GeneralCodeModel("*229*5*2#", "Bi-Annual data plan 60gb(#55,000)"));  //25
        nineMobileCodes.add(new GeneralCodeModel("*229*5*3#", "Yearly data plan 120gb(#110,000)"));  //26
        nineMobileCodes.add(new GeneralCodeModel("*229*3*11#", "Night data plan 1gb(#200) 15am- 5am"));  //27
        nineMobileCodes.add(new GeneralCodeModel("*229*3*12#", "Weekend/Evening data plan 2gb(#1,000) 7pm- 7am"));  //28
        nineMobileCodes.add(new GeneralCodeModel("*229*3*13#", "Weekend/Evening data plan 5gb(#2,000) 7pm- 7am"));  //29
        nineMobileCodes.add(new GeneralCodeModel("*343*5*5#", "Daily chatpaks (Whatsapp,BBM,Wechat) (#50)"));  //30
        nineMobileCodes.add(new GeneralCodeModel("*343*5*6#", "Weekly chatpaks (Whatsapp,BBM,Wechat) 7days (#150)"));  //30
        nineMobileCodes.add(new GeneralCodeModel("*343*5*7#", "Monthly chatpaks (Whatsapp,BBM,Wechat) 30days (#400)"));  //31
        nineMobileCodes.add(new GeneralCodeModel("*343*6*7#", "Daily Socialmepacks (Whatsapp,BBM,Wechat,Instagram,fb,Twitter & Eskimi) 1day (#100)"));  //32
        nineMobileCodes.add(new GeneralCodeModel("*343*6*8#", "Weekly Socialmepacks (Whatsapp,BBM,Wechat,Instagram,fb,Twitter & Eskimi) 7days (#300)"));  //33
        nineMobileCodes.add(new GeneralCodeModel("*343*6*9#", "Monthly Socialmepacks (Whatsapp,BBM,Wechat,Instagram,fb,Twitter & Eskimi) 30days (#700)"));  //34
        nineMobileCodes.add(new GeneralCodeModel("*244*2#", "9Mobile moretalk  25kb/sec"));
        nineMobileCodes.add(new GeneralCodeModel("*244*1#", "9Mobile morecliq  15kb/sec"));
        nineMobileCodes.add(new GeneralCodeModel("*420*1#", "9Mobile morelife 4.0  15kb/sec"));
        nineMobileCodes.add(new GeneralCodeModel("*320#", "9Mobile moreflex  50kb/sec"));
        nineMobileCodes.add(new GeneralCodeModel("*244*8#", "9Mobile talkzone  12kb/sec"));
        nineMobileCodes.add(new GeneralCodeModel("*244*1#", "9Mobile morecliq break free  40kb/sec"));
        nineMobileCodes.add(new GeneralCodeModel("*246*4*24#", "9Mobile MoreBusiness 2.0 N1000/70Mins"));



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GeneralCodeModel generalCodeModel = nineMobileCodes.get(i);
                final String codes = generalCodeModel.getmCodeView();
                final long getId = generalCodeAdapter.getItemId(i);

                inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.custom_dialogue, null);
                pinEditText = alertLayout.findViewById(R.id.pin_edit_text);
                amountEditText = alertLayout.findViewById(R.id.amount_edit_text);
                numberEditText = alertLayout.findViewById(R.id.number_edit_text);

                if (getId == 4 || getId == 6 || getId == 9) {
                    //*codes*pin#
                    if (getId == 4) {
                        numberEditText.setVisibility(View.GONE);
                        amountEditText.setVisibility(View.GONE);
                    }
                    //*codes*phoneNumber#
                    if (getId == 6) {
                        amountEditText.setVisibility(View.GONE);
                        pinEditText.setVisibility(View.GONE);
                    }
                    //*codes*pin*amount*num#
                    if (getId == 9) {

                    }

                    AlertDialog.Builder alert = new AlertDialog.Builder(NineMobileActivity.this);
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
                            //*numer*pin#
                            if (getId == 4) {
                                data = codes + pinEditText.getText().toString();
                            }
                            //*codes*phoneNumber#
                            if (getId == 6) {
                                data = codes + numberEditText.getText().toString();
                            }
                            //*codes*pin*amount*num#
                            if (getId == 9) {
                                data = codes + pinEditText.getText().toString() + "*" + amountEditText.getText().toString() + "*" + numberEditText.getText().toString();
                            }

                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + data + Uri.encode("#")));
                            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(NineMobileActivity.this, data + "#", Toast.LENGTH_SHORT).show();
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


        generalCodeAdapter = new GeneralCodeAdapter(this, nineMobileCodes);
        listView.setAdapter(generalCodeAdapter);


    }
    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

}

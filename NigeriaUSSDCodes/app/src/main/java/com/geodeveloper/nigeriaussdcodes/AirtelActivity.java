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

public class AirtelActivity extends AppCompatActivity {
    private ArrayList<GeneralCodeModel> airtelCodes;
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
        airtelCodes = new ArrayList<>();
        toolbar = findViewById(R.id.uba_bank_tool_bar);
        listView = findViewById(R.id.uba_bank_list_view);


        amountEditText = findViewById(R.id.amount_edit_text);
        numberEditText = findViewById(R.id.number_edit_text);
        pinEditText = findViewById(R.id.pin_edit_text);
        toolbar.setTitle("Airtel");
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

        mainLogo.setImageResource(R.drawable.airtel_logo);


        airtelCodes.add(new GeneralCodeModel("*123#", "Check Airtime balance"));        //0
        airtelCodes.add(new GeneralCodeModel("*140#", "Check data balance"));        //1
        airtelCodes.add(new GeneralCodeModel("*141*712*0#", "Check data balance 2"));        //2
        airtelCodes.add(new GeneralCodeModel("*121*3*4#", "Know my phone number"));        //3
        airtelCodes.add(new GeneralCodeModel("111", "Call customer care"));        //4
        airtelCodes.add(new GeneralCodeModel("*121#", "Self service"));        //5
        airtelCodes.add(new GeneralCodeModel("*126*", "Recharge Airtime"));        //6
        airtelCodes.add(new GeneralCodeModel("*444#", "Buy airtime/data from bank"));        //7
        airtelCodes.add(new GeneralCodeModel("*555*", "Get 6x Data bundles"));        //8
        airtelCodes.add(new GeneralCodeModel("*500#", "Borrow airtime/data"));        //9
        airtelCodes.add(new GeneralCodeModel("*140*", "Call me back"));        //10
        airtelCodes.add(new GeneralCodeModel("*791#", "Airtel Hello caller tunes"));        //11
        airtelCodes.add(new GeneralCodeModel("*141#", "Buy data plan"));        //12
        airtelCodes.add(new GeneralCodeModel("*312#", "SmartTrybe Night Data plan (12am - 5am)"));        //13
        airtelCodes.add(new GeneralCodeModel("*141*50#", "Daily data plan: 20mb (N50) - 1day"));        //14
        airtelCodes.add(new GeneralCodeModel("*141*100#", "Daily data plan: 75mb (N100) - 1day"));        //15
        airtelCodes.add(new GeneralCodeModel("*141*200#", " data plan: 200mb (N200) - 3days"));        //16
        airtelCodes.add(new GeneralCodeModel("*141*300#", " data plan: 350mb (N300) - 7days"));        //17
        airtelCodes.add(new GeneralCodeModel("*141*500#", " data plan: 750mb (N500) - 14days"));        //18
        airtelCodes.add(new GeneralCodeModel("*141*1000#", " data plan: 1.5gb (N1,000) - 30days"));        //19
        airtelCodes.add(new GeneralCodeModel("*141*1500#", " data plan: 2.5gb (N1,500) - 30days"));        //20
        airtelCodes.add(new GeneralCodeModel("*141*2000#", " data plan: 3.5gb (N2,000) - 30days"));        //21
        airtelCodes.add(new GeneralCodeModel("*141*2500#", " data plan: 4.5gb (N2,500) - 30days"));        //22
        airtelCodes.add(new GeneralCodeModel("*141*3000#", " data plan: 5.5gb (N3,000) - 30days"));        //23
        airtelCodes.add(new GeneralCodeModel("*141*4000#", " data plan: 7.5gb (N4,000) - 30days"));        //24
        airtelCodes.add(new GeneralCodeModel("*141*5000#", " data plan: 12gb (N5,000) - 30days"));        //25
        airtelCodes.add(new GeneralCodeModel("*141*10000#", " data plan: 25gb (N10,000) - 30days"));        //26
        airtelCodes.add(new GeneralCodeModel("*141*15000#", " data plan: 40gb (N15,000) - 30days"));        //27
        airtelCodes.add(new GeneralCodeModel("*141*20000#", " data plan: 60gb (N20,000) - 30days"));        //28
        airtelCodes.add(new GeneralCodeModel("*141*9999#", " 4G data plan: 30gb (N9,999) - 30days"));        //29
        airtelCodes.add(new GeneralCodeModel("*141*19999#", " 4G data plan: 80gb (N19,999) - 30days"));        //30
        airtelCodes.add(new GeneralCodeModel("*948*4#", "Social pack (Whatsapp Only) 10mb (N25) 1day"));        //31
        airtelCodes.add(new GeneralCodeModel("*991*4#", "Social pack (Whatsapp,Facebook,Twitter) 40mb (N50) 1day"));        //32
        airtelCodes.add(new GeneralCodeModel("*688*3#", "Social pack (Whatsapp,Facebook,Twitter) 80mb (N100) 5days"));        //33
        airtelCodes.add(new GeneralCodeModel("*688*1#", "Social pack (Whatsapp,Facebook,Twitter) 600mb (N300) 25days"));        //34
        airtelCodes.add(new GeneralCodeModel("*439*3#", "30mins Unlimited plans (N300)"));        //35
        airtelCodes.add(new GeneralCodeModel("*439*4#", "60mins Unlimited plans (N500)"));        //36
        airtelCodes.add(new GeneralCodeModel("*318#", "Smart premier 11kb/sec"));
        airtelCodes.add(new GeneralCodeModel("*312#", "Smart trybe 11kb/sec"));
        airtelCodes.add(new GeneralCodeModel("*315#", "Smart talk 11kb/sec"));
        airtelCodes.add(new GeneralCodeModel("*317#", "Smart trybe junior 40kb/sec"));
        airtelCodes.add(new GeneralCodeModel("*314#", "Smart value  15kb/sec"));
        airtelCodes.add(new GeneralCodeModel("*243#", "Value plan 11kb/sec"));
        airtelCodes.add(new GeneralCodeModel("*234#", "Talk more bundles 60kb/sec"));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GeneralCodeModel generalCodeModel = airtelCodes.get(i);
                final String codes = generalCodeModel.getmCodeView();
                final long getId = generalCodeAdapter.getItemId(i);

                inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.custom_dialogue, null);
                pinEditText = alertLayout.findViewById(R.id.pin_edit_text);
                amountEditText = alertLayout.findViewById(R.id.amount_edit_text);
                numberEditText = alertLayout.findViewById(R.id.number_edit_text);

                if (getId == 6 || getId == 8 || getId == 10) {
                    //*code*pin#
                    if (getId == 6 || getId == 8) {
                        numberEditText.setVisibility(GONE);
                        amountEditText.setVisibility(GONE);
                    }
                    //*code*number#
                    if (getId == 10) {
                        pinEditText.setVisibility(GONE);
                        amountEditText.setVisibility(GONE);
                    }


                    AlertDialog.Builder alert = new AlertDialog.Builder(AirtelActivity.this);
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
                            //*code*pin#
                            if (getId == 6 || getId == 8) {
                                data = codes + pinEditText.getText().toString();
                            }
                            //*code*number#
                            if (getId == 10) {
                                data = codes + numberEditText.getText().toString();
                            }

                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + data + Uri.encode("#")));
                            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(AirtelActivity.this, data + "#", Toast.LENGTH_SHORT).show();
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


        generalCodeAdapter = new GeneralCodeAdapter(this, airtelCodes);
        listView.setAdapter(generalCodeAdapter);


    }

    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

}

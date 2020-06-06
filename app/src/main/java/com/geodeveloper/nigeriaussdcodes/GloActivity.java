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

public class GloActivity extends AppCompatActivity {
    private ArrayList<GeneralCodeModel> gloCodes;
    private ListView listView,tarrifPlanListView;
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
        gloCodes = new ArrayList<>();
        toolbar = findViewById(R.id.uba_bank_tool_bar);
        listView = findViewById(R.id.uba_bank_list_view);


        amountEditText = findViewById(R.id.amount_edit_text);
        numberEditText = findViewById(R.id.number_edit_text);
        pinEditText = findViewById(R.id.pin_edit_text);
        toolbar.setTitle("Glo Ngr");
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

        mainLogo.setImageResource(R.drawable.glo_logo);


        gloCodes.add(new GeneralCodeModel("#124#", "Check Airtime balance"));       //0
        gloCodes.add(new GeneralCodeModel("*127*0#", "Check data balance"));        //1
        gloCodes.add(new GeneralCodeModel("*123*", "Recharge Airtime"));            //2
        gloCodes.add(new GeneralCodeModel("*135*8#", "Know my number"));            //3
        gloCodes.add(new GeneralCodeModel("121", "Call customer care"));            //4
        gloCodes.add(new GeneralCodeModel("*321#", "Borrow airtime"));              //5
        gloCodes.add(new GeneralCodeModel("*125*", "Please call me back"));         //6
        gloCodes.add(new GeneralCodeModel("*127*01*", "Share data"));               //7
        gloCodes.add(new GeneralCodeModel("*131*", "Transfer Airtime (default pin:0000)"));    //8
        gloCodes.add(new GeneralCodeModel("*777#", "Subscription data plan"));      //9
        gloCodes.add(new GeneralCodeModel("*127*32#", "10mb (#25) - 1 day"));      //10
        gloCodes.add(new GeneralCodeModel("*127*14#", "30mb (#50) - 1 day"));      //11
        gloCodes.add(new GeneralCodeModel("*127*51#", "60mb (#100) - 1 day"));      //12
        gloCodes.add(new GeneralCodeModel("*127*61#", "Night/weekend data plan: 3gb (#5000) - 7days"));      //13
        gloCodes.add(new GeneralCodeModel("*127*56#", "Data plan 200mb (#200) - 5days")); //14
        gloCodes.add(new GeneralCodeModel("*127*57#", "Data plan 800mb (#500) - 14days"));//15
        gloCodes.add(new GeneralCodeModel("*127*53#", "Data plan 1.6gb (#1,000) - 30days"));//16
        gloCodes.add(new GeneralCodeModel("*127*55#", "Data plan 3.65gb (#2,000) - 30days"));//17
        gloCodes.add(new GeneralCodeModel("*127*54#", "Data plan 7gb (#3,000) - 30days"));//18
        gloCodes.add(new GeneralCodeModel("*127*59#", "Data plan 10gb (#4,000) - 30days"));//19
        gloCodes.add(new GeneralCodeModel("*127*2#", "Data plan 12.5gb (#5,000) - 30days"));//20
        gloCodes.add(new GeneralCodeModel("*127*1#", "Data plan 20gb (#8,000) - 30days"));//21
        gloCodes.add(new GeneralCodeModel("*127*11#", "Data plan 26gb (#10,000) - 30days"));//22
        gloCodes.add(new GeneralCodeModel("*127*12#", "Data plan 42gb (#15,000) - 30days"));//23
        gloCodes.add(new GeneralCodeModel("*127*13#", "Data plan 50gb (#18,000) - 30days"));//24
        gloCodes.add(new GeneralCodeModel("*127*33#", "Data plan 63gb (#20,000) - 30days"));//25
        gloCodes.add(new GeneralCodeModel("*170*4#", "Glo bounce (15kb/sec)"));//26
        gloCodes.add(new GeneralCodeModel("*177#", "Glo IDD packs (11kb/sec)"));//27
        gloCodes.add(new GeneralCodeModel("*100*9*2", "Glo Infinito (20kb/sec)"));//28
        gloCodes.add(new GeneralCodeModel("*211#", "Glo 11k (11kb/sec)"));//29
        gloCodes.add(new GeneralCodeModel("*10*5*1#", "Glo G-BAM (11kb/sec)"));//30
        gloCodes.add(new GeneralCodeModel("*123*", "Glo jollific8 (67kb/sec)"));//31
        gloCodes.add(new GeneralCodeModel("*300#", "Glo free tomorrow (28kb/sec)"));//32
        gloCodes.add(new GeneralCodeModel("100*10*1#", "Glo Bumpa (50kb/sec)"));//33
        gloCodes.add(new GeneralCodeModel("*323*", "Glo formular (50kb/sec)"));//34






        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GeneralCodeModel generalCodeModel = gloCodes.get(i);
                final String codes = generalCodeModel.getmCodeView();
                final long getId = generalCodeAdapter.getItemId(i);

                inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.custom_dialogue, null);
                pinEditText = alertLayout.findViewById(R.id.pin_edit_text);
                amountEditText = alertLayout.findViewById(R.id.amount_edit_text);
                numberEditText = alertLayout.findViewById(R.id.number_edit_text);

                if(getId==2||getId==6||getId==7||getId==8||getId==31||getId==34){
                    if(getId==6||getId==7){
                        pinEditText.setVisibility(View.GONE);
                        amountEditText.setVisibility(View.GONE);
                    }else if(getId==8){

                    }else if(getId==31||getId==34){
                        pinEditText.setVisibility(View.VISIBLE);
                        amountEditText.setVisibility(View.GONE);
                        numberEditText.setVisibility(View.GONE);
                    }
                    else{
                        numberEditText.setVisibility(View.GONE);
                        amountEditText.setVisibility(View.GONE);
                    }
                    AlertDialog.Builder alert = new AlertDialog.Builder(GloActivity.this);
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
                            String data;
                            if(getId==6||getId==7){
                                data =codes+numberEditText.getText().toString();
                            }else if(getId==8){
                                data =codes+numberEditText.getText().toString()+"*"+amountEditText.getText().toString()+"*"+pinEditText.getText().toString();

                            } else{
                                data = codes + pinEditText.getText().toString();
                            }


                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + data + Uri.encode("#")));
                            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(GloActivity.this, data + "#", Toast.LENGTH_SHORT).show();
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


                }else
                    {

                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", codes, null));
                        startActivity(intent);
                    }




            }
        });


        generalCodeAdapter = new GeneralCodeAdapter(this, gloCodes);
        listView.setAdapter(generalCodeAdapter);


    }
    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

}

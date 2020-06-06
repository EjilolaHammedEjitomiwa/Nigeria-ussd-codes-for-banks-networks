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
import android.widget.ListView;
import android.widget.Toast;

import com.startapp.android.publish.adsCommon.StartAppAd;

import java.util.ArrayList;

import static android.Manifest.permission.CALL_PHONE;

public class UbaBankActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ArrayList<GeneralCodeModel> ubaCodes;
    private GeneralCodeAdapter generalCodeAdapter;
    private ListView listView;
    private EditText amountEditText, numberEditText, pinEditText;
    private LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uba_bank);


        toolbar = findViewById(R.id.uba_bank_tool_bar);
        listView = findViewById(R.id.uba_bank_list_view);

        toolbar.setNavigationIcon(R.drawable.ic_action_name);
        toolbar.setTitle("UBA Plc");
        toolbar.setTitleTextColor(getResources().getColor(R.color.tool_bar_text_color));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                }
            }
        });


        ubaCodes = new ArrayList<>();

        ubaCodes.add(new GeneralCodeModel("*919*00#", "Check balance"));             //0
        ubaCodes.add(new GeneralCodeModel("*919*", "Top-Up for self"));              //1
        ubaCodes.add(new GeneralCodeModel("*919*", "Top-up for others"));            //2
        ubaCodes.add(new GeneralCodeModel("*919*3*", "Transfer to UBA account"));    //3
        ubaCodes.add(new GeneralCodeModel("*919*4*", "Transfer to other banks"));    //4
        ubaCodes.add(new GeneralCodeModel("*919*32#", "Load UBA prepaid card"));     //5
        ubaCodes.add(new GeneralCodeModel("*919*5#", "Pay bills"));                  //6
        ubaCodes.add(new GeneralCodeModel("*919*30*", "ATM Cardless Withdrawal"));   //7
        ubaCodes.add(new GeneralCodeModel("*919*12#", "Airline ticket menu"));       //8
        ubaCodes.add(new GeneralCodeModel("*919*12*394#", "Africa World Airline"));  //9
        ubaCodes.add(new GeneralCodeModel("*919#", "General Magic banking code"));


        generalCodeAdapter = new GeneralCodeAdapter(this, ubaCodes);
        listView.setAdapter(generalCodeAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GeneralCodeModel generalCodeModel = ubaCodes.get(i);
                final String codes = generalCodeModel.getmCodeView();
                long getId = generalCodeAdapter.getItemId(i);

                inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.custom_dialogue, null);
                pinEditText = alertLayout.findViewById(R.id.pin_edit_text);
                amountEditText = alertLayout.findViewById(R.id.amount_edit_text);
                numberEditText = alertLayout.findViewById(R.id.number_edit_text);

                if (getId == 1||getId==7) {
                    numberEditText.setVisibility(View.GONE);
                    pinEditText.setVisibility(View.GONE);
                    AlertDialog.Builder alert = new AlertDialog.Builder(UbaBankActivity.this);
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
                            String data = codes + amountEditText.getText().toString();

                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + data + Uri.encode("#")));
                            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(UbaBankActivity.this, data + "#", Toast.LENGTH_SHORT).show();
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


                }else if(getId==2||getId==3||getId==4){
                    pinEditText.setVisibility(View.GONE);
                    AlertDialog.Builder alert = new AlertDialog.Builder(UbaBankActivity.this);
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
                            String data = codes + numberEditText.getText().toString()+"*"+amountEditText.getText().toString();

                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + data + Uri.encode("#")));
                            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(UbaBankActivity.this, data + "#", Toast.LENGTH_SHORT).show();
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




                }
                else{
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", codes, null));
                    startActivity(intent);
                }


            }
        });


    }

    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

}

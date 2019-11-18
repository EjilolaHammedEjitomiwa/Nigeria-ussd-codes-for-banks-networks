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

public class Fcmb extends AppCompatActivity {
    private ArrayList<GeneralCodeModel> fcmbCodes;
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
        fcmbCodes = new ArrayList<>();
        toolbar = findViewById(R.id.uba_bank_tool_bar);
        listView = findViewById(R.id.uba_bank_list_view);


        amountEditText = findViewById(R.id.amount_edit_text);
        numberEditText = findViewById(R.id.number_edit_text);
        pinEditText = findViewById(R.id.pin_edit_text);
        toolbar.setTitle("FCMB");
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

        mainLogo.setImageResource(R.drawable.fcmb_logo);


        fcmbCodes.add(new GeneralCodeModel("*329#", "Magic banking code"));        //0
        fcmbCodes.add(new GeneralCodeModel("*329*", "Top-up your mobile phone"));        //1
        fcmbCodes.add(new GeneralCodeModel("*329*", "Top-up for friends"));        //2
        fcmbCodes.add(new GeneralCodeModel("*329*", "Transfer funds"));        //3
        fcmbCodes.add(new GeneralCodeModel("*329*00#", "To check balance"));        //4
        fcmbCodes.add(new GeneralCodeModel("*329*0#", "Reset pin or create a transaction code"));        //5
        fcmbCodes.add(new GeneralCodeModel("*329*1*", "Buy data on any phone"));        //6
        fcmbCodes.add(new GeneralCodeModel("*329*2*", "Pay for DSTV or GOTV"));        //7



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GeneralCodeModel generalCodeModel = fcmbCodes.get(i);
                final String codes = generalCodeModel.getmCodeView();
                final long getId = generalCodeAdapter.getItemId(i);

                inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.custom_dialogue, null);
                pinEditText = alertLayout.findViewById(R.id.pin_edit_text);
                amountEditText = alertLayout.findViewById(R.id.amount_edit_text);
                numberEditText = alertLayout.findViewById(R.id.number_edit_text);


                if (getId == 1 || getId == 2 || getId == 3 || getId == 6||getId==7) {

                    //*codes*amount#
                    if (getId == 1) {
                        pinEditText.setVisibility(GONE);
                        numberEditText.setVisibility(GONE);
                    }
                    //*codes*amount*number
                    if (getId == 2 || getId == 3||getId==7) {
                        pinEditText.setVisibility(GONE);
                    }
                    //*codes*number#
                    if (getId == 6) {
                        amountEditText.setVisibility(GONE);
                        pinEditText.setVisibility(GONE);
                    }
                    AlertDialog.Builder alert = new AlertDialog.Builder(Fcmb.this);
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
                            //*codes*amount#
                            if (getId == 1) {
                                data = codes + amountEditText.getText().toString();
                            }
                            //*codes*amount*number
                            if (getId == 2 || getId == 3||getId==7) {
                                data = codes + amountEditText.getText().toString() + "*" + numberEditText.getText().toString();
                            }

                            //*codes*number#
                            if (getId == 6) {
                               data = codes+numberEditText.getText().toString();
                            }

                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + data + Uri.encode("#")));
                            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(Fcmb.this, data + "#", Toast.LENGTH_SHORT).show();
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


        generalCodeAdapter = new GeneralCodeAdapter(this, fcmbCodes);
        listView.setAdapter(generalCodeAdapter);


    }

    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

}

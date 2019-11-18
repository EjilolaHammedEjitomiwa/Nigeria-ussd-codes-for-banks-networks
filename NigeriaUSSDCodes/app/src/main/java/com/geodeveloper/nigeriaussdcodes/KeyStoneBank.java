

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

public class KeyStoneBank extends AppCompatActivity {
    private ArrayList<GeneralCodeModel> keyStoneLogo;
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
        keyStoneLogo = new ArrayList<>();
        toolbar = findViewById(R.id.uba_bank_tool_bar);
        listView = findViewById(R.id.uba_bank_list_view);


        amountEditText = findViewById(R.id.amount_edit_text);
        numberEditText = findViewById(R.id.number_edit_text);
        pinEditText = findViewById(R.id.pin_edit_text);
        toolbar.setTitle("KeyStone Bank");
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

        mainLogo.setImageResource(R.drawable.keyStone_logo);


        keyStoneLogo.add(new GeneralCodeModel("*737*0#", "Open an account"));        //0



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GeneralCodeModel generalCodeModel = keyStoneLogo.get(i);
                final String codes = generalCodeModel.getmCodeView();
                final long getId = generalCodeAdapter.getItemId(i);

                inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.custom_dialogue, null);
                pinEditText = alertLayout.findViewById(R.id.pin_edit_text);
                amountEditText = alertLayout.findViewById(R.id.amount_edit_text);
                numberEditText = alertLayout.findViewById(R.id.number_edit_text);


                if (getId == 2||getId==3||getId==4||getId==5||getId==9||getId==10||getId==11||getId==12||getId==13||getId==15) {
                    //*code*amount*number#
                    if (getId == 2||getId==3||getId==5||getId==13) {
                        pinEditText.setVisibility(GONE);
                        if(getId==13){
                        }
                        numberEditText.setHint("DECODER NUMBER");
                    }
                    //*code*amount#
                    if(getId==4||getId==9||getId==10||getId==12||getId==15){
                        pinEditText.setVisibility(GONE);
                        numberEditText.setVisibility(GONE);
                    }

                    //codes*amount*merchantcode#
                    if(getId== 11){
                        pinEditText.setVisibility(GONE);
                        numberEditText.setHint("MERCHANT CODE");
                    }


                    AlertDialog.Builder alert = new AlertDialog.Builder(KeyStoneBank.this);
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
                            //*code*amount*number#
                            if (getId == 2||getId==3||getId==5||getId==13) {
                                data = codes+amountEditText.getText().toString()+"*"+numberEditText.getText().toString();

                            }

                            //*code*amount#
                            if(getId==4||getId==15){
                                data = codes+amountEditText.getText().toString();
                            }

                            //*code*amount*50#
                            if(getId==9){
                                data = codes+amountEditText.getText().toString()+"*50";
                            }
                            //*code*amount*108#
                            if(getId==10){
                                data = codes+amountEditText.getText().toString()+"*108";
                            }
                            //*code*amount*4#
                            if(getId==12){
                                data = codes+amountEditText.getText().toString()+"*4";
                            }
                            //codes*amount*merchantcode#
                            if(getId== 11){
                                data = codes+amountEditText.getText().toString()+"*"+numberEditText.getText().toString();
                            }


                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + data + Uri.encode("#")));
                            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(KeyStoneBank.this, data + "#", Toast.LENGTH_SHORT).show();
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


        generalCodeAdapter = new GeneralCodeAdapter(this, keyStoneLogo);
        listView.setAdapter(generalCodeAdapter);


    }

    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

}


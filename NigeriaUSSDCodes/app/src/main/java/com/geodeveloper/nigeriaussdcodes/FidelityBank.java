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

public class FidelityBank extends AppCompatActivity {
    private ArrayList<GeneralCodeModel> fidelityBankCode;
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
        fidelityBankCode = new ArrayList<>();
        toolbar = findViewById(R.id.uba_bank_tool_bar);
        listView = findViewById(R.id.uba_bank_list_view);


        amountEditText = findViewById(R.id.amount_edit_text);
        numberEditText = findViewById(R.id.number_edit_text);
        pinEditText = findViewById(R.id.pin_edit_text);
        toolbar.setTitle("Fidelity Bank");
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

        mainLogo.setImageResource(R.drawable.fidelity_bank_logo);


        fidelityBankCode.add(new GeneralCodeModel("*770#", "Fidelity instant banking"));        //0
        fidelityBankCode.add(new GeneralCodeModel("*770*", "Fidelity self recharge"));        //1
        fidelityBankCode.add(new GeneralCodeModel("*770*", "Fidelity recharge for others"));        //2
        fidelityBankCode.add(new GeneralCodeModel("*770*", "Transfer money to banks"));        //3
        fidelityBankCode.add(new GeneralCodeModel("*770*8*", "Get instant cash from ATM without your card"));        //4
        fidelityBankCode.add(new GeneralCodeModel("*770*1099*", "Pay Bills (DSTv)"));        //5
        fidelityBankCode.add(new GeneralCodeModel("*770*1098*", "Pay Bills (DSTv Box Office)"));        //6*****
        fidelityBankCode.add(new GeneralCodeModel("*770*1077*", "Pay Bills (Startimes)"));        //7
        fidelityBankCode.add(new GeneralCodeModel("*770*1088*", "Pay Bills (GOTV)"));        //8
        fidelityBankCode.add(new GeneralCodeModel("*770*1033*", "Pay Bills (Swift 4G Subscription)"));        //9
        fidelityBankCode.add(new GeneralCodeModel("*770*1099*", "Pay Bills (LCC toll payments)"));        //10
        fidelityBankCode.add(new GeneralCodeModel("*770*00#", "Change your pin"));        //11
        fidelityBankCode.add(new GeneralCodeModel("*770*0#", "How to check your account balance"));        //12
        fidelityBankCode.add(new GeneralCodeModel("*770*02#", "Update your BVN"));        //13
        fidelityBankCode.add(new GeneralCodeModel("*770*911#", "How to block your card"));        //14
        fidelityBankCode.add(new GeneralCodeModel("*770*911*", "Disable your phone from Instant Banking Services"));        //15
        fidelityBankCode.add(new GeneralCodeModel("*770*", "How to block your account"));        //16
        fidelityBankCode.add(new GeneralCodeModel("*770*02#", "How to choose alerts you want to recieve"));        //16


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GeneralCodeModel generalCodeModel = fidelityBankCode.get(i);
                final String codes = generalCodeModel.getmCodeView();
                final long getId = generalCodeAdapter.getItemId(i);

                inflater = getLayoutInflater();
                final View alertLayout = inflater.inflate(R.layout.custom_dialogue, null);
                pinEditText = alertLayout.findViewById(R.id.pin_edit_text);
                amountEditText = alertLayout.findViewById(R.id.amount_edit_text);
                numberEditText = alertLayout.findViewById(R.id.number_edit_text);

                if (getId == 1 || getId == 2 || getId == 3 || getId == 5 || getId == 6 || getId == 7 || getId == 8 || getId == 9 || getId == 10||getId==15||getId==16) {

                    //*code*amount#
                    if (getId == 1 || getId == 4) {
                        numberEditText.setVisibility(View.GONE);
                        pinEditText.setVisibility(View.GONE);
                    }
                    //*codes*number*amount#
                    if (getId == 2 || getId == 3 || getId == 5 || getId == 6 || getId == 7 || getId == 8 || getId == 9 || getId == 10) {
                        pinEditText.setVisibility(View.GONE);
                    }
                    //*codes*number#
                    if(getId==15||getId==16){
                        pinEditText.setVisibility(View.GONE);
                        amountEditText.setVisibility(View.GONE);
                    }

                    AlertDialog.Builder alert = new AlertDialog.Builder(FidelityBank.this);
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
                            //*code*amount#
                            if (getId == 1 || getId == 4) {
                                data = codes + amountEditText.getText().toString();
                            }

                            //*codes*number*amount#
                            if (getId == 2 || getId == 3 || getId == 5 || getId == 6 || getId == 7 || getId == 8 || getId == 9 || getId == 10) {
                                data = codes + numberEditText.getText().toString() + "*" + amountEditText.getText().toString();
                            }

                            //*codes*number#
                            if(getId==15||getId==16){
                              data = codes+numberEditText.getText().toString();
                            }



                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + data + Uri.encode("#")));
                            if (ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                Toast.makeText(FidelityBank.this, data + "#", Toast.LENGTH_SHORT).show();
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


        generalCodeAdapter = new GeneralCodeAdapter(this, fidelityBankCode);
        listView.setAdapter(generalCodeAdapter);


    }

    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }

}

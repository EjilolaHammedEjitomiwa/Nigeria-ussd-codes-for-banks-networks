package com.geodeveloper.nigeriaussdcodes;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.startapp.android.publish.adsCommon.StartAppAd;
import com.startapp.android.publish.adsCommon.StartAppSDK;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private LinearLayout generalCodeLinearLayout;
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private Toolbar toolbar;
    private LinearLayout ubaLinearLayout, gloLinearLayout, nineMobileLinearLayout, airtelLinearLayout, mtnLinearLayout,
            accesBankLinearLayout, GtbBankLinearLayout, firstBankLinearLayout, unionBankLinearLayout;

    private TextView allClassTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StartAppSDK.init(this, "207260293", true);
        StartAppAd.disableSplash();
        setContentView(R.layout.activity_main);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mNavigationView = findViewById(R.id.navigationView_layout);

        toolbar = findViewById(R.id.tool_bar);
        generalCodeLinearLayout = findViewById(R.id.general_codes_card_view);
        ubaLinearLayout = findViewById(R.id.uba_linear_layout);
        gloLinearLayout = findViewById(R.id.glo_linear_layout);
        nineMobileLinearLayout = findViewById(R.id.nine_mobile_linear_layout);
        airtelLinearLayout = findViewById(R.id.airtel_linear_layout);
        mtnLinearLayout = findViewById(R.id.mtn_linear_layout);
        accesBankLinearLayout = findViewById(R.id.access_linear_layout);
        GtbBankLinearLayout = findViewById(R.id.gtb_bank_linear_layout);
        firstBankLinearLayout = findViewById(R.id.first_bank_linear_layout);
        unionBankLinearLayout = findViewById(R.id.union_bank_linear_layout);

        allClassTextView = findViewById(R.id.all_class_text_view);

        mNavigationView.setNavigationItemSelectedListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // toolbar.setTitle("USSD CODES");
            //toolbar.setTitleTextColor(getResources().getColor(R.color.title_text_color));
            // toolbar.setNavigationIcon(R.drawable.ic_launcher_foreground);
        }

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        onClickItems();


    }


    @Override
    public void onBackPressed() {
        StartAppAd.onBackPressed(this);
        super.onBackPressed();
    }


    public void onClickItems() {

        // OnClickListener for GENERAL CODES SECTION
        generalCodeLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGeneralCodes();
            }
        });

        //OnClickListener for UBA BANK CODES section
        ubaLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUba();
            }
        });

//OnClickListener for Glo Activity
        gloLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGlo();
            }
        });
//onClickListener for Nine Mobile Activity
        nineMobileLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNineMobile();
            }
        });

        //onClickListener for Airtel Activity
        airtelLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAirtel();
            }
        });

        //on click Listener for MTN Activity
        mtnLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMtn();
            }
        });

        accesBankLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAccess();
            }
        });
        GtbBankLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openBtb();
            }
        });
        firstBankLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFirst();
            }
        });
        unionBankLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUnion();
            }
        });

        allClassTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openAllBanksActiviy = new Intent(MainActivity.this, allBankActivity.class);
                startActivity(openAllBanksActiviy);
            }
        });


    }

    public void openUba() {
        Intent openUbaActivity = new Intent(MainActivity.this, UbaBankActivity.class);
        startActivity(openUbaActivity);
    }

    public void openAccess() {
        Intent openAccessBankActivity = new Intent(MainActivity.this, AccessBank.class);
        startActivity(openAccessBankActivity);
    }

    public void openBtb() {
        Intent openGtbBankActivity = new Intent(MainActivity.this, GtbBank.class);
        startActivity(openGtbBankActivity);
    }

    public void openFirst() {
        Intent openFirstbankActivity = new Intent(MainActivity.this, FirstBankActivity.class);
        startActivity(openFirstbankActivity);
    }

    public void openUnion() {
        Intent openUnionBankActivity = new Intent(MainActivity.this, UnionBankActivity.class);
        startActivity(openUnionBankActivity);

    }

    public void openDiamond() {
        Intent openDiamond = new Intent(MainActivity.this, DiamondBank.class);
        startActivity(openDiamond);
    }

    public void openEco() {
        Intent openEco = new Intent(MainActivity.this, EcoBank.class);
        startActivity(openEco);
    }

    public void openFidelity() {
        Intent openFidelity = new Intent(MainActivity.this, FidelityBank.class);
        startActivity(openFidelity);
    }

    public void openFcmb() {
        Intent openFcmb = new Intent(MainActivity.this, Fcmb.class);
        startActivity(openFcmb);
    }

    public void openGeneralCodes() {
        Intent openGeneralCodeActivity = new Intent(MainActivity.this, GeneralCodesActivity.class);
        startActivity(openGeneralCodeActivity);

    }

    public void openGlo() {
        Intent openGloActivity = new Intent(MainActivity.this, GloActivity.class);
        startActivity(openGloActivity);
    }

    public void openNineMobile() {
        Intent openNineMobile = new Intent(MainActivity.this, NineMobileActivity.class);
        startActivity(openNineMobile);
    }

    public void openAirtel() {
        Intent openAirtelActivity = new Intent(MainActivity.this, AirtelActivity.class);
        startActivity(openAirtelActivity);
    }

    public void openMtn() {
        Intent openMtnActivity = new Intent(MainActivity.this, MtnActivity.class);
        startActivity(openMtnActivity);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        String menuName = menuItem.getTitle().toString();

        switch (menuName) {
            case "UBA Bank Plc":
                openUba();
                closeDrawer();
                break;
            case "Access Bank":
                openAccess();
                closeDrawer();
                break;
            case "Guarantee Trust Bank":
                openBtb();
                closeDrawer();
                break;
            case "First Bank":
                openFirst();
                closeDrawer();
                break;
            case "Union Bank":
                openUnion();
                closeDrawer();
                break;
            case "Diamond Bank":
                openDiamond();
                closeDrawer();
                break;
            case "Ecobank":
                openEco();
                closeDrawer();
                break;
            case "Fidelty Bank":
                openFidelity();
                closeDrawer();
                break;
            case "FCMB":
                openFcmb();
                closeDrawer();
                break;
            case "GLO":
                openGlo();
                closeDrawer();
            case "9Mobile":
                openNineMobile();
                closeDrawer();
            case "Airtel":
                openAirtel();
                closeDrawer();
            case "MTN":
                openMtn();
                closeDrawer();
        }

        closeDrawer();

        return true;
    }

    public void closeDrawer() {
        mDrawerLayout.closeDrawer(GravityCompat.START);
    }
}
//BIT.LY/DEVFEST19ADO       bit.ly/devfest19adovolunteer
package com.geodeveloper.nigeriaussdcodes;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

public class GeneralCodesActivity extends AppCompatActivity {
    private ArrayList<GeneralCodeModel> generalCodes;
    private ListView listView;
    private Toolbar mToolBar;
    private GeneralCodeAdapter generalCodeAdapter;
    private ImageView mainLogo;

//    public static final String ACTION_DIAL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uba_bank);

        mainLogo = findViewById(R.id.main_logo);
        mToolBar = findViewById(R.id.uba_bank_tool_bar);
        listView = findViewById(R.id.uba_bank_list_view);
        generalCodes = new ArrayList<>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolBar.setNavigationIcon(R.drawable.ic_action_name);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finishAfterTransition();
                }
            });
        }
        mainLogo.setImageResource(R.drawable.more_codes);
        mToolBar.setTitle("General codes");
        mToolBar.setTitleTextColor(getResources().getColor(R.color.tool_bar_text_color));


        generalCodes.add(new GeneralCodeModel("*565*0#", "Check BVN Number"));
        generalCodes.add(new GeneralCodeModel("112", "Nigeria Emergency Number"));
        generalCodes.add(new GeneralCodeModel("*6820#", "Report domestic violence and child abuse"));
        generalCodes.add(new GeneralCodeModel("*242#", "Paga service code"));
        generalCodes.add(new GeneralCodeModel("*288#", "Dstv Self service"));


        generalCodeAdapter = new GeneralCodeAdapter(this, generalCodes);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                GeneralCodeModel generalCodeModel = generalCodes.get(i);

                Long itemId = generalCodeAdapter.getItemId(i);
                String codes = generalCodeModel.getmCodeView();


                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", codes, null));
                startActivity(intent);
            }
        });

        listView.setAdapter(generalCodeAdapter);
    }
}

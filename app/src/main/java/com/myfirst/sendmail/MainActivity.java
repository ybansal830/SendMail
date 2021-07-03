package com.myfirst.sendmail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button mBtOne;
    private EditText mEtOne,mEtTwo,mEtThree;
    String[] ccArray,emailArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtOne = findViewById(R.id.btOne);
        mEtOne = findViewById(R.id.etOne);
        mEtTwo = findViewById(R.id.etTwo);
        mEtThree = findViewById(R.id.etThree);
        mBtOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("text/plain");
                emailArray = mEtOne.getText().toString().split(",");
                ccArray = mEtTwo.getText().toString().split(",");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, emailArray);
                emailIntent.putExtra(Intent.EXTRA_CC, ccArray);
                emailIntent.putExtra(Intent.EXTRA_TEXT, mEtThree.getText().toString());
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activitiesList = packageManager.queryIntentActivities(emailIntent, 0);
                if (activitiesList.size() >= 1) {
                    startActivity(emailIntent);
                } else {
                    // No activity found to handle email intent
                }
            }
        });
    }
}
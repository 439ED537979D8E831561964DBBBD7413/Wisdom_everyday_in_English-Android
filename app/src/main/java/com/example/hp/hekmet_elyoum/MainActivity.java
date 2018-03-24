package com.example.hp.hekmet_elyoum;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    String[] mTestArray;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    Date date = new Date();
    TextView textView;
    SharedPreferences preference_shared, text_shared;
    SharedPreferences.Editor edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTestArray = getResources().getStringArray(R.array.HKM_ALYOM);
        textView = (TextView) findViewById(R.id.TextView);
        preference_shared = this.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        text_shared = this.getSharedPreferences("TEXT", MODE_PRIVATE);
        if (preference_shared.getBoolean("isFirstRun", true)) {
            textView.setText(mTestArray[(0) % (mTestArray.length)]);
            text_shared.edit().putString("Text", textView.getText().toString()).apply();
            Save_Date();
        } else {
            if (!Objects.equals(preference_shared.getString("Date", ""), dateFormat.format(date)))
            {
                int idx = new Random().nextInt(mTestArray.length);
                textView.setText(mTestArray[idx]);
                text_shared.edit().putString("Text", textView.getText().toString()).apply();
                Save_Date();
            } else {
                textView.setText(text_shared.getString("Text", ""));
            }
        }
        preference_shared.edit().putBoolean("isFirstRun", false).apply();
    }
    public void Save_Date() {
        preference_shared = this.getSharedPreferences("PREFERENCE", MODE_PRIVATE);
        edit = preference_shared.edit();
        edit.clear();
        edit.putString("Date", dateFormat.format(date));
        edit.apply();
    }
}

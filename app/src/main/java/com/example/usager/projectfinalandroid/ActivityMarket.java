package com.example.usager.projectfinalandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityMarket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);
    }

    public void retour(View view)
    {
        finishFromChild (this);
    }

    //achat des article avec sql lite
}

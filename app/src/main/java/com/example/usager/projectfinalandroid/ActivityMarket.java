package com.example.usager.projectfinalandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityMarket extends AppCompatActivity {
    JoueursBDD JoueurBd =new JoueursBDD(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        JoueurBd.open();
        initializeMarket();
    }

    public void retour(View view)
    {
        finishFromChild (this);
    }
    public void initializeMarket()
    {
        classJoueur Joueur=JoueurBd.getJoueurById(1);

        TextView txt1=(TextView) findViewById(R.id.txtCash);
        txt1.setText(String.valueOf(Joueur.getScore()));

        TextView txt2=(TextView) findViewById(R.id.txtManual);
        txt2.setText(String.valueOf(Joueur.getManuel()));

        TextView txt3=(TextView) findViewById(R.id.txtAutomatic);
        txt3.setText(String.valueOf(Joueur.getAutomatic()));


    }
    public void addManuel(View view)
    {

    }
    public void addAutomatic(View view)
    {

    }

    //achat des article avec sql lite
}

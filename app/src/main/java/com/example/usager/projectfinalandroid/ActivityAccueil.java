package com.example.usager.projectfinalandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ActivityAccueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
    }

    public void newGame(View view)
    {
        JoueursBDD JoueurBd =new JoueursBDD(this);
        classJoueur Joueur =new classJoueur(1,0,0,0);
        JoueurBd.open();
        JoueurBd.removeJoueur();
        JoueurBd.insertJoueur(Joueur);
        startGame();
    }
    public void loadGame(View view)
    {
        startGame();
    }
    public void startGame()
    {
        Intent appel =new Intent(getBaseContext(), ActivityMarket.class);
        startActivity(appel);
    }
}

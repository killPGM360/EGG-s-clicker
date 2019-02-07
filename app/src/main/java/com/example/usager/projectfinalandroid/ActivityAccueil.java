package com.example.usager.projectfinalandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class ActivityAccueil extends AppCompatActivity {
    JoueursBDD JoueurBd =new JoueursBDD(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        JoueurBd.open();
        checkIfGameExist();

    }

    public void checkIfGameExist()
    {
        Object o3=JoueurBd.getAllJoueurs();
        if(o3==null)
        {
            Button btn = (Button)findViewById(R.id.btnLoadGame);
            btn.setVisibility(View.INVISIBLE);
        }
    }

    public void newGame(View view)
    {

        classJoueur Joueur =new classJoueur(1,0,0,0);


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
        Intent appel =new Intent(getBaseContext(), MainActivity.class);
        startActivity(appel);
    }
}

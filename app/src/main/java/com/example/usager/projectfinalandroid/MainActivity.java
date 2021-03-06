package com.example.usager.projectfinalandroid;


import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Random;
import android.widget.Button;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.content.Context;
import android.widget.VideoView;





public class MainActivity extends AppCompatActivity {

    int m_compteur=0;
    int m_multipleManuel=1;
    int m_multipleAuto;

    MediaPlayer mp;
    Context m_context = this;

    Handler m_handler = new Handler();
    int m_delay = 1000; //milliseconds


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp = MediaPlayer.create(m_context, R.raw.pop);
        InitializeJoueur();




    }


    public void afficherPuns()
    {
        Random rand = new Random();
        int nb=rand.nextInt(6);
        String phrase="";
        switch (nb)
        {
            case 1:
                phrase=classPuns.phrase1();
                break;
            case 2:
                phrase=classPuns.phrase2();
                break;
            case 3:
                phrase=classPuns.phrase3();
                break;
            case 4:
                phrase=classPuns.phrase4();
                break;
            case 5:
                phrase=classPuns.phrase5();
                break;
            case 6:
                phrase=classPuns.phrase6();
                break;
            case -1:
                phrase=String.valueOf(nb);
                break;
        }
        classFonction.afficheMsg(phrase,this);

    }
    public void rollDiceForPuns()
    {
        Random rand = new Random();
        int nb=rand.nextInt(6);
        if(nb ==3)
            afficherPuns();
    }



    public void onSave(View view)
    {
        save();
        classFonction.afficheMsg("Enregistrement effectué",this);
    }
    public void save()
    {
        JoueursBDD JoueurBd =new JoueursBDD(this);

        JoueurBd.open();
        classJoueur JoueurCurrent = JoueurBd.getJoueurById(1);
        if(JoueurCurrent!=null)
        {
            JoueurCurrent.setScore(m_compteur);
            JoueurBd.updateJoueur(1,JoueurCurrent);
            classJoueur test= JoueurBd.getJoueurById(1);

        }
    }
    public void InitializeJoueur()
    {
        JoueursBDD JoueurBd =new JoueursBDD(this);

        JoueurBd.open();


        classJoueur JoueurCurrent = JoueurBd.getJoueurById(1);
        if(JoueurCurrent!=null)
        {
            m_compteur=JoueurCurrent.getScore();
            m_multipleManuel=JoueurCurrent.getManuel();
            m_multipleAuto=JoueurCurrent.getAutomatic();
            incrementCompteur(0);
            classFonction.afficheMsg("What's up master ",this);
        }
        else
            {
                classFonction.afficheMsg("Bienvenue jeune recrut",this);
            }
            //timer pour les auto click
        m_handler.postDelayed(new Runnable(){
            public void run(){
                incrementCompteur(m_multipleAuto);

                m_handler.postDelayed(this, m_delay);
            }
        }, m_delay);


    }




    public void drink(View view)
    {


       VideoView vv = (VideoView)findViewById(R.id.videoView2);
        vv.setVisibility(View.VISIBLE);
       vv.setVideoPath("android.resource://" + getPackageName() +"/raw/" + "drink");
       vv.start();

        Button btn = (Button)findViewById(R.id.btnRetour);
        btn.setVisibility(View.VISIBLE);
        Button btn2 = (Button)findViewById(R.id.btnDrink);
        btn2.setVisibility(View.INVISIBLE);












    }
    public void arretVideo(View view)
    {
        VideoView vv = (VideoView)findViewById(R.id.videoView2);


        vv.stopPlayback();
        vv.setVisibility(View.INVISIBLE);

        Button btn = (Button)findViewById(R.id.btnRetour);
        btn.setVisibility(View.INVISIBLE);
    }


    public void onEgg(View view)
    {

        incrementCompteur(m_multipleManuel+1);
        try {
            mp.start();
        } catch(Exception e) {  }
    }
    public void incrementCompteur(int multiple)
    {


        int bonus=multiple/10;
        bonus++;
        multiple=multiple*bonus*bonus;

        m_compteur=m_compteur+multiple;
        TextView compteur=(TextView) findViewById(R.id.txtCompteur);
        compteur.setText(String.valueOf(m_compteur));

        if(m_compteur%10==0)
        {
            rollDiceForPuns();
        }
        if(m_compteur>100)
        {
            Button btn = (Button)findViewById(R.id.btnDrink);
            btn.setVisibility(View.VISIBLE);
        }
        if(m_compteur>2000000000)
        {
            classFonction.afficheModal("GG you win the game, the game will eventually crash or bug",this);
        }
    }

    public void goMarket(View view)
    {
        save();
        Intent appel =new Intent(getBaseContext(), ActivityMarket.class);
        startActivity(appel);
        finish();
    }
    public void photo(View view)
    {
        classFonction.afficheModal("Cette fonction n'est pas encore implémenté",this);
    }

    public void onGoal(View view){ classFonction.afficheModal("L'objectif du jeu est d'atteindre 2 000 000 000 d'oeufs",this);}


}



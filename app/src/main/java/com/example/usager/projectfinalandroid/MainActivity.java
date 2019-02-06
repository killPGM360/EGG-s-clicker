package com.example.usager.projectfinalandroid;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;


import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.content.Context;
import android.widget.VideoView;





public class MainActivity extends AppCompatActivity {

    int m_compteur=0;
    int m_multipleManuel=1;
    MediaPlayer mp;
    Context m_context = this;


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
        }

        Toast toast = Toast.makeText(getApplicationContext(), phrase, Toast.LENGTH_LONG);
        toast.show();
    }

    public void afficherPoulet()
    {

    }

    public void Save(View view)
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
        //classJoueur Joueur =new classJoueur(0,0,0);
        JoueurBd.open();
        //JoueurBd.insertJoueur(Joueur);

        classJoueur JoueurCurrent = JoueurBd.getJoueurById(1);
        if(JoueurCurrent!=null)
        {
            m_compteur=JoueurCurrent.getScore();
            incrementCompteur(0);
        }
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

        incrementCompteur(m_multipleManuel);
        try {
            mp.start();
        } catch(Exception e) {  }
    }
    public void incrementCompteur(int multiple)
    {
        m_compteur=m_compteur+multiple;
        TextView compteur=(TextView) findViewById(R.id.txtCompteur);
        compteur.setText(String.valueOf(m_compteur));

        if(m_compteur%14==0)
        {
            afficherPuns();
        }
        if(m_compteur%100==0)
        {
            Button btn = (Button)findViewById(R.id.btnDrink);
            btn.setVisibility(View.VISIBLE);
        }
    }

    public void goMarket(View view)
    {
        Intent appel =new Intent(getBaseContext(), ActivityMarket.class);
        startActivity(appel);
    }
}



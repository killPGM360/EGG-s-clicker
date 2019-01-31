package com.example.usager.projectfinalandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.Random;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import android.content.Context;




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
            afficherPoulet();
        }
    }
}



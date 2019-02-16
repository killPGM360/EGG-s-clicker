package com.example.usager.projectfinalandroid;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityMarket extends AppCompatActivity {
    JoueursBDD JoueurBd =new JoueursBDD(this);
    int m_wallet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        JoueurBd.open();
        initializeMarket();
    }

    public void retour(View view)
    {
        Intent appel =new Intent(getBaseContext(), MainActivity.class);
        startActivity(appel);
        finishFromChild (this);
    }
    public void initializeMarket()
    {
        classJoueur Joueur=JoueurBd.getJoueurById(1);

        m_wallet=Joueur.getScore();


        refresh(Joueur);



    }
    public void addManuel(View view)
    {
       addOneManual();
    }
    public void addOneManual()
    {
        Button btn = (Button)findViewById(R.id.btnManual);
        int prix = Integer.valueOf(btn.getText().toString()) ;

        classJoueur Joueur=JoueurBd.getJoueurById(1);


        if(m_wallet>=prix)
        {
            m_wallet=m_wallet- prix;
            Joueur.setScore(m_wallet);
            int manuel = Joueur.getManuel();
            manuel++;
            Joueur.setManuel(manuel);

            JoueurBd.updateJoueur(1,Joueur);
        }
        else {
            afficheInsulte();
        }
        refresh(Joueur);
    }
    public void addOneAutomatic()
    {
        Button btn = (Button)findViewById(R.id.btnAutomatic);
        int prix = Integer.valueOf(btn.getText().toString()) ;

        classJoueur Joueur=JoueurBd.getJoueurById(1);


        if(m_wallet>=prix)
        {
            m_wallet=m_wallet- prix;
            Joueur.setScore(m_wallet);
            int automatic = Joueur.getAutomatic();
            automatic++;
            Joueur.setAutomatic(automatic);

            JoueurBd.updateJoueur(1,Joueur);
        }
        else {
            afficheInsulte();
        }
        refresh(Joueur);
    }
    public void addAutomatic(View view)
    {
        addOneAutomatic();

    }
    public void refresh(classJoueur Joueur)
    {
        TextView txt1=(TextView) findViewById(R.id.txtCash);
        txt1.setText(String.valueOf(Joueur.getScore()));

        TextView txt2=(TextView) findViewById(R.id.txtManual);
        txt2.setText(String.valueOf(Joueur.getManuel()));

        TextView txt3=(TextView) findViewById(R.id.txtAutomatic);
        txt3.setText(String.valueOf(Joueur.getAutomatic()));


        Button btnM=(Button) findViewById(R.id.btnManual);
        Button btnA =(Button) findViewById(R.id.btnAutomatic);
        int prixM=Integer.valueOf(btnM.getText().toString()) ;
        int prixA=Integer.valueOf(btnA.getText().toString()) ;
        //prixM=30*Joueur.getManuel()+100;
        //prixA=30*Joueur.getAutomatic()+100;
        prixM=(int)formuleScalePrice(15,1.15,Joueur.getManuel())+100;
        prixA=(int)formuleScalePrice(15,1.15,Joueur.getAutomatic())+100;
        btnM.setText(String.valueOf(prixM));
        btnA.setText(String.valueOf(prixA));

    }
    public long formuleScalePrice(int a, double r, int n)
    {
        long ex=(int)java.lang.Math.pow(r,(int)n);
        double price=a*(ex - 1)/(r - 1);
        return (long) price;
    }
    public void afficheInsulte()
    {
        Toast toast = Toast.makeText(getApplicationContext(), "Trop pauvre, retourne travailler hahaha ", Toast.LENGTH_LONG);
        toast.show();
    }
    public void dixManuel(View view)
    {
        for(int i =0;i<10;i++)
        {
            addOneManual();
        }
    }
    public void dixAutomatic(View view)
    {
        for(int i =0;i<10;i++)
        {
            addOneAutomatic();
        }
    }
    public void onInfo(View view)
    {
        afficheModal("Pour chaque tranche de 25 achetées, un multiplicateur sera appliqué");
    }
    public void afficheModal(String msg)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        //alert.setTitle("Do you want to logout?");
        alert.setMessage(msg);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here
            }
        });



        alert.show();
    }


    //achat des article avec sql lite
}

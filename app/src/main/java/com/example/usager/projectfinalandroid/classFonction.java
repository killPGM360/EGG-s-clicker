package com.example.usager.projectfinalandroid;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

public  class classFonction
{
    public static void afficheMsg(String msg, Context context)
    {
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        toast.show();
    }
    public static void afficheModal(String msg,Context context)
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        //alert.setTitle("Do you want to logout?");
        alert.setMessage(msg);

        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //Your action here
            }
        });



        alert.show();
    }
}

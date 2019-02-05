package com.example.usager.projectfinalandroid;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class JoueursBDD
{
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "eleves.db";
    private static final String TABLE_JOUEUR = "table_joueurs";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_SCORE = "Score";
    private static final int NUM_COL_SCORE = 1;
    private static final String COL_AUTOMATIC = "Automatic";
    private static final int NUM_COL_AUTOMATIC = 2;
    private static final String COL_MANUEL = "Manuel";
    private static final int NUM_COL_MANUEL = 3;
    private SQLiteDatabase bdd;
    private classSqlLite maBaseSQLite;
    public JoueursBDD(Context context){
        maBaseSQLite = new classSqlLite(context, NOM_BDD, null, VERSION_BDD);
    }
    public void open(){
        bdd = maBaseSQLite.getWritableDatabase();
    }
    public void close(){
        bdd.close();
    }
    public SQLiteDatabase getBDD(){
        return bdd;
    }
    public long insertJoueur(classJoueur joueur){
        ContentValues values = new ContentValues();
        values.put(COL_SCORE, joueur.getScore());
        values.put(COL_AUTOMATIC, joueur.getAutomatic());
        values.put(COL_MANUEL, joueur.getManuel());
        return bdd.insert(TABLE_JOUEUR, null, values);
    }
    public int updateJoueur(int id, classJoueur joueur){
        ContentValues values = new ContentValues();
        values.put(COL_SCORE, joueur.getScore());
        values.put(COL_AUTOMATIC, joueur.getAutomatic());
        values.put(COL_MANUEL, joueur.getManuel());
        return bdd.update(TABLE_JOUEUR, values, COL_ID + " = " +id, null);
    }
    public int removeJoueurById(int id){
        return bdd.delete(TABLE_JOUEUR, COL_ID + " = " +id, null);
    }

    public classJoueur getJoueurById(int id){
        String iid =String.valueOf(id);
        Cursor c = bdd.query(TABLE_JOUEUR, new String[] {COL_ID, COL_SCORE, COL_AUTOMATIC,COL_MANUEL},
        "ID=?", new String[]{iid}, null, null, null);
        return cursorToJoueur(c);
    }
    private classJoueur cursorToJoueur(Cursor c){
        if (c.getCount() == 0)
            return null;
        c.moveToFirst();
        classJoueur joueur = new classJoueur();
        joueur.setId(c.getInt(NUM_COL_ID));
        joueur.setScore(c.getInt(NUM_COL_SCORE));
        joueur.setAutomatic(c.getInt(NUM_COL_AUTOMATIC));
        joueur.setManuel(c.getInt(NUM_COL_MANUEL));
        c.close();
        return joueur;
    }


}

package com.example.usager.projectfinalandroid;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class classSqlLite extends SQLiteOpenHelper {
    private static final String TABLE_JOUEUR = "table_joueurs";
    private static final String COL_ID = "ID";
    private static final String COL_SCORE = "Score";
    private static final String COL_MANUEL = "Manuel";
    private static final String COL_AUTOMATIC = "Automatic";
    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_JOUEUR + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_SCORE + " TEXT NOT NULL, "+ COL_AUTOMATIC + " TEXT NOT NULL, "
            + COL_MANUEL + " TEXT NOT NULL);";
    public classSqlLite(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_BDD);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE " + TABLE_JOUEUR + ";");
        onCreate(db);
    }
    public void efface(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE " + TABLE_JOUEUR + ";");
        onCreate(db);
    }
}


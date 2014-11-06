package com.example.student_agenda.app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by senhoury on 26/03/14.
 */
public class SalleBdd {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Agenda.db";
    public static final String TABLE_Salle = "Salle";

    public static final String KEY_ID_SALLE = "idSalle";
    private static final int NUM_COL_ID = 0;

    public static final String KEY_Batiment= "Batiment";
    private static final int NUM_COL_bat = 1;
    public static final String KEY_Effect= "Effectif";
    private static final int NUM_COL_effect = 2;
    public static final String KEY_Clim= "Climatidation";
    private static final int NUM_COL_clim = 3;
    public static final String KEY_project= "Projecteur";
    private static final int NUM_COL_proj = 4;


    public static ArrayList<Salle> salles = new ArrayList<Salle>();



    private static DataBaseHandler maBaseSQLite;

    public SalleBdd(Context context) {
        //On crée la BDD et sa table
        maBaseSQLite = new DataBaseHandler(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    // CRUD

    public  boolean insertSalle(Salle s){
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_Batiment, s.getBatiment());
        values.put(KEY_Effect, s.getEffectif());
        values.put(KEY_Clim, s.getAvecClim());
        values.put(KEY_project, s.getAvecProject());



        db.insert(TABLE_Salle,null,values);
        db.close();

  return true;  }

    static  Salle getSalleById(int id){
        Salle m = new Salle();
        SQLiteDatabase db = maBaseSQLite.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Salle,
                new String[]{ KEY_ID_SALLE,KEY_Batiment,KEY_Effect,KEY_Clim,KEY_project},
                KEY_ID_SALLE + "=?",new String[]{String.valueOf(id)},
                null,null,null);

        if ( cursor != null ){
            // Positioneer sur le Premier Elemet
            cursor.moveToFirst();
            //   m .setId(cursor.getInt(NUM_COL_ID));
            m .setBatiment(cursor.getString(NUM_COL_bat));
            m .setEffectif(cursor.getInt(NUM_COL_effect));
           m .setAvecClim(cursor.getString(NUM_COL_clim));
            m .setAvecProject(cursor.getString(NUM_COL_clim));


            // matieres.add(m);
            cursor.close();
            db.close();


        }
        return m ;
    }
    public static   ArrayList<Salle> getSalle(){
        try {
            salles.clear();
            String sql = "SELECT * FROM " + TABLE_Salle  ;

            SQLiteDatabase db = maBaseSQLite.getReadableDatabase();
            Cursor c =db.rawQuery(sql,null);

            if(c != null){c.moveToFirst();
                do {
                    Salle m = new Salle();
                    m .setNum(c.getInt(NUM_COL_ID));
                    m .setBatiment(c.getString(NUM_COL_bat));
                    m .setEffectif(c.getInt(NUM_COL_effect));
                    m .setAvecClim(c.getString(NUM_COL_clim));
                    m .setAvecProject(c.getString(NUM_COL_proj));



                    salles.add(m);
                }while (c.moveToNext());
            }
            c.close();
            db.close();

        }catch ( Exception e){
            Log.v("MONPROJET", e.getMessage());
        }
        return salles;
    }
    public int upateSalle(Salle e){
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Batiment,e.getBatiment());
        values.put(KEY_Effect,e.getEffectif());
        values.put(KEY_Clim,e.getAvecClim());
        values.put(KEY_project,e.getAvecProject());


        return db.update(TABLE_Salle,values,KEY_ID_SALLE + "= ?",
                new String[]{String.valueOf(e.getNum())});

    }


    public static void deleteSalle(int id){
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
        db.delete(TABLE_Salle,  KEY_ID_SALLE + "= ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

}

package com.example.student_agenda.app;

/**
 * Created by senhoury on 20/03/14.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class DataBaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Agenda.db";

   /*******Table Matière **********/
    public static final String TABLE_MATIERE = "Matieres";
    public static final String KEY_ID = "id";
    public static final String KEY_ID_SALLE = "idSalle";
    public static final String KEY_NAME_MAT= "nomMatiere";
    public static final String KEY_NAME_PROF= "NomProf";
    public static final String KEY_DATE_DEBUT= "DateDebut";
    public static final String KEY_DATE_FIN= "DateFin";
    public static final String KEY_DUREE= "DureeCours";
    public static final String KEY_HEURE_DEBUT= "HeureDebut";

    //requete de création de la table Matiere

    String CREATE_TABLE_Mat = " CREATE TABLE " + TABLE_MATIERE + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
    + KEY_ID_SALLE+ " INTEGER NOT NULL , "
            +KEY_NAME_MAT + " TEXT NOT NULL , " + KEY_NAME_PROF + " TEXT NOT NULL , "
            +KEY_DATE_DEBUT+ " TEXT NOT null , " + KEY_DATE_FIN + " TEXT NOT NULL , "
            +KEY_DUREE + " TEXT NOT NULL , "
            + KEY_HEURE_DEBUT + " TEXT NOT NULL ) ;";

    /************************Table Salle *****************************/

    public static final String TABLE_Salle = "Salle";
    public static final String KEY_Batiment= "Batiment";
    public static final String KEY_Effect= "Effectif";
    public static final String KEY_Clim= "Climatidation";
    public static final String KEY_project= "Projecteur";

    //requete de création de la table Salle
   String CREATE_TABLE_Salle = " CREATE TABLE " + TABLE_Salle + " ( " + KEY_ID_SALLE + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            +KEY_Batiment + " TEXT NOT NULL , " + KEY_Effect + " INTEGER NOT NULL , "
            +KEY_Clim+ " TEXT NOT null , " + KEY_project + " TEXT NOT NULL ) ;";

    /*************************************************************************************************/
    String CREATE_TABLES_DB=" CREATE TABLE " + TABLE_MATIERE + " ( " + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
            + KEY_ID_SALLE+ " INTEGER NOT NULL , "
            +KEY_NAME_MAT + " TEXT NOT NULL , " + KEY_NAME_PROF + " TEXT NOT NULL , "
            +KEY_DATE_DEBUT+ " TEXT NOT null , " + KEY_DATE_FIN + " TEXT NOT NULL , "
            +KEY_DUREE + " TEXT NOT NULL , "
            + KEY_HEURE_DEBUT + " TEXT NOT NULL ) ;    CREATE TABLE " + TABLE_Salle + " ( " + KEY_ID_SALLE + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

            +KEY_Batiment + " TEXT NOT NULL , " + KEY_Effect + " INTEGER NOT NULL , "
            +KEY_Clim+ " TEXT NOT null , " + KEY_project + " TEXT NOT NULL ) ;";



    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {

        super(context, name, factory, version);


    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
               sqLiteDatabase.execSQL(CREATE_TABLES_DB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MATIERE+","+TABLE_Salle );
        onCreate(sqLiteDatabase);

    }


}

package com.example.student_agenda.app;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Created by senhoury on 26/03/14.
 */
public class MatiereBdd {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Agenda.db";
    public static final String TABLE_MATIERE = "Matieres";

    public static final String KEY_ID = "id";
    private static final int NUM_COL_ID = 0;
    public static final String KEY_ID_SALLE = "idSalle";
    private static final int NUM_COL_IDSalle = 1;
    public static final String KEY_NAME_MAT= "nomMatiere";
    private static final int NUM_COL_NameMat = 2;
    public static final String KEY_NAME_PROF= "NomProf";
    private static final int NUM_COL_NomProf = 3;
    public static final String KEY_DATE_DEBUT= "DateDebut";
    private static final int NUM_COL_DateDebut = 4;
    public static final String KEY_DATE_FIN= "DateFin";
    private static final int NUM_COL_DateFin = 5;
    public static final String KEY_DUREE= "DureeCours";
    private static final int NUM_COL_DureeCour = 6;
    public static final String KEY_HEURE_DEBUT= "HeureDebut";
    private static final int NUM_COL_HeureDebut = 7;

    public static  ArrayList<Mat> matieres = new ArrayList<Mat>();
    public static  ArrayList<Mat> matieres2 = new ArrayList<Mat>();
   public static  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
     private static DataBaseHandler maBaseSQLite;

    public MatiereBdd(Context context) {
        //On crée la BDD et sa table
        maBaseSQLite = new DataBaseHandler(context, DATABASE_NAME, null, DATABASE_VERSION);
    }



    // CRUD

    public void insertMatiere(Mat mat){
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(KEY_NAME_MAT, mat.getNm());
        values.put(KEY_NAME_PROF, mat.getNp());
        values.put(KEY_DATE_DEBUT, mat.getDd());
        values.put(KEY_DATE_FIN, mat.getDf());
        values.put(KEY_HEURE_DEBUT, mat.getHd());
        values.put(KEY_DUREE, mat.getDc());
        values.put(KEY_ID_SALLE, mat.getNumSalle());


        db.insert(TABLE_MATIERE,null,values);
        db.close();

    }

   static  Mat getMatiereById(int id){
        Mat m = new Mat();
        SQLiteDatabase db = maBaseSQLite.getReadableDatabase();

        Cursor cursor = db.query(TABLE_MATIERE,
                new String[]{ KEY_ID,KEY_NAME_MAT,KEY_NAME_PROF,KEY_DATE_DEBUT,KEY_DATE_FIN,KEY_DUREE,KEY_HEURE_DEBUT,KEY_ID_SALLE},
                KEY_ID + "=?",new String[]{String.valueOf(id)},
                null,null,null);

        if ( cursor != null ){
            // Positioneer sur le Premier Elemet
            cursor.moveToFirst();
         //   m .setId(cursor.getInt(NUM_COL_ID));
            m .setNm(cursor.getString(NUM_COL_NameMat));
            m .setNp(cursor.getString(NUM_COL_NomProf));
            m .setDf(cursor.getString(NUM_COL_DateFin));
            m .setDd(cursor.getString(NUM_COL_DateDebut));
            m .setHd(cursor.getString(NUM_COL_HeureDebut));
            m .setDc(cursor.getString(NUM_COL_DureeCour));
            m .setNumSalle(cursor.getInt(NUM_COL_IDSalle));

           // matieres.add(m);
            cursor.close();
            db.close();


        }
        return m ;
    }
/**************************************************************************************************************************/
public static   ArrayList<Mat> getMatieres(){

    try {
        matieres.clear();

        String sql = "SELECT * FROM " + TABLE_MATIERE  ;

        SQLiteDatabase db = maBaseSQLite.getReadableDatabase();

        Cursor c =db.rawQuery(sql,null);

        if(c!=null){ //c.moveToFirst();

            while (c.moveToNext()) {


                Mat m = new Mat();

                m.setId(c.getInt(NUM_COL_ID));
                m.setNm(c.getString(NUM_COL_NameMat));
                m.setNp(c.getString(NUM_COL_NomProf));
                m.setDf(c.getString(NUM_COL_DateFin));
                m.setDd(c.getString(NUM_COL_DateDebut));
                m.setHd(c.getString(NUM_COL_HeureDebut));
                m.setDc(c.getString(NUM_COL_DureeCour));
                m.setNumSalle(c.getInt(NUM_COL_IDSalle));



                    matieres.add(m);


            }




        }
        c.close();
        db.close();

    }catch ( Exception e){
        Log.v("MONPROJET", e.getMessage());
    }
    return matieres;
}





    /*****************************************************************************************************************/

    public static   ArrayList<Mat> getMatieresEmploie(){

        try {
            matieres2.clear();

            String sql = "SELECT * FROM " + TABLE_MATIERE +" order by "+NUM_COL_HeureDebut ;

            SQLiteDatabase db = maBaseSQLite.getReadableDatabase();

            Cursor c =db.rawQuery(sql,null);

            if(c!=null){ //c.moveToFirst();

                while (c.moveToNext()) {

                    Date dateActuelle= dateDuJour();

                    Mat m = new Mat();

                    m.setId(c.getInt(NUM_COL_ID));
                    m.setNm(c.getString(NUM_COL_NameMat));
                    m.setNp(c.getString(NUM_COL_NomProf));
                    m.setDf(c.getString(NUM_COL_DateFin));
                    m.setDd(c.getString(NUM_COL_DateDebut));
                    m.setHd(c.getString(NUM_COL_HeureDebut));
                    m.setDc(c.getString(NUM_COL_DureeCour));
                    m.setNumSalle(c.getInt(NUM_COL_IDSalle));


                    boolean d1= dateActuelle.after(formatter.parse(m.getDf()));
                    boolean d2= dateActuelle.after(formatter.parse(m.getDd()));


                  if(d1==false && d2==true)

                    matieres2.add(m);


                }




            }
            c.close();
            db.close();

        }catch ( Exception e){
            Log.v("MONPROJET", e.getMessage());
        }
        return matieres2;}


        /*********************************************************************************/





    public int upateMatiere(Mat e){
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME_MAT,e.getNm());
        values.put(KEY_NAME_PROF,e.getNp());
        values.put(KEY_DATE_DEBUT,e.getDd());
        values.put(KEY_DATE_FIN,e.getDf());
        values.put(KEY_DUREE,e.getDc());
        values.put(KEY_HEURE_DEBUT,e.getHd());
        values.put(KEY_ID_SALLE,e.getNumSalle());

        return db.update(TABLE_MATIERE,values,KEY_ID + "= ?",
                new String[]{String.valueOf(e.getId())});

    }
    public static void deleteMatiere(int id){
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
        db.delete(TABLE_MATIERE,  KEY_ID + "= ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public void crererSalle(){
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
        db.execSQL(" CREATE TABLE " + DataBaseHandler.TABLE_Salle + " ( " + KEY_ID_SALLE + " INTEGER PRIMARY KEY AUTOINCREMENT ,"

                        +DataBaseHandler.KEY_Batiment + " TEXT NOT NULL , " + DataBaseHandler.KEY_Effect + " INTEGER NOT NULL , "
                        +DataBaseHandler.KEY_Clim+ " TEXT NOT null , " + DataBaseHandler.KEY_project + " TEXT NOT NULL ) ;"
        );
        db.close();//TRUNCATE TABLE Customer;
    }

    public static  void truncateTab(){
        SQLiteDatabase db = maBaseSQLite.getWritableDatabase();
       db.delete(TABLE_MATIERE, null, null);
        db.close();
    }



      public static  String convertDate(String date){String s="";
          StringTokenizer st = new StringTokenizer(date,"/");
          while (st.hasMoreTokens()) {
             s+=st.nextToken();
          }
          return s;
      }



    public static Date  dateDuJour(){
        Date z=null;
        Calendar cal = new GregorianCalendar();
        int jour = cal.get(Calendar.DAY_OF_MONTH);
        int moi=cal.get(Calendar.MONTH)+1;
         int annee= cal.get(Calendar.YEAR);
String dd=jour+"/"+moi+"/"+annee;
        try {  z=formatter.parse(dd);



        } catch (ParseException e) {
            e.printStackTrace();
        }
        return z;

    }
}

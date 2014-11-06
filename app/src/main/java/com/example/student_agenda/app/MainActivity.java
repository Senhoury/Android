package com.example.student_agenda.app;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MatiereBdd db = new MatiereBdd(this);
        //Récupération de la liste des Matieres

        ArrayList<Mat> listM2 = MatiereBdd.getMatieresEmploie();


        /****************************************************************************/


        //Création et initialisation de l'Adapter pour les personnes
        MatiereAdapter adapter = new MatiereAdapter(this, listM2);


        //Récupération du composant ListView
        ListView list = (ListView) findViewById(R.id.ListView01);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);
//MatiereBdd.truncateTab();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.Matiere_settings) {

            Intent intent = new Intent(this, Matiere_Activity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.Salle_settings) {

            Intent intent = new Intent(this, Salle_Activity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.A_propos) {

            Intent intent = new Intent(this, AproposActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
/*****************************************************************************************/
}
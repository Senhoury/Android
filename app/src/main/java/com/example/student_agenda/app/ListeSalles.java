package com.example.student_agenda.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListeSalles extends ActionBarActivity implements SalleAdapt.SalleAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_salles);
        //Récupération de la liste des Matieres
        ArrayList<Salle> listM = SalleBdd.getSalle();


        //Création et initialisation de l'Adapter pour les personnes
        SalleAdapt adapter = new SalleAdapt(this, listM);
        adapter.addListener(this);
        //Récupération du composant ListView
        ListView list = (ListView)findViewById(R.id.listView);
     //Initialisation de la liste avec les données
        list.setAdapter(adapter);
    }

    public void onClickSupSalle(Salle item, int position) {


       SalleBdd.deleteSalle(item.getNum());
        Toast.makeText(this, " Suppression  effectuée !!", Toast.LENGTH_LONG).show();
        Intent i=new Intent(getBaseContext(),ListeSalles.class);
        startActivity(i);


    }

    int identifiantMat;
    public void onClickUpdateSalle(Salle item, int position) {

        identifiantMat=  item.getNum();
        Intent i=new Intent(getBaseContext(),Salle_Activity.class);
        startActivity(i);


    }

  /*  @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("id",identifiantMat);
    }*/



}

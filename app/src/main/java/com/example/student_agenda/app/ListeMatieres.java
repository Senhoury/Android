package com.example.student_agenda.app;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ListeMatieres extends ActionBarActivity  implements ListeMatAdapter.MatiereAdapterListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_matieres);



        //Récupération de la liste des Matieres
        ArrayList<Mat> listM =MatiereBdd.getMatieres();


        //Création et initialisation de l'Adapter pour les personnes
     ListeMatAdapter adapter = new ListeMatAdapter(this, listM);
        adapter.addListener(this);
        //Récupération du composant ListView
        ListView list = (ListView)findViewById(R.id.listView05);

        //Initialisation de la liste avec les données
        list.setAdapter(adapter);


    }





    public void onClickSup(Mat item, int position) {


        MatiereBdd.deleteMatiere(item.getId());
        Toast.makeText(this, " Suppression  effectuée !!", Toast.LENGTH_LONG).show();
        Intent i=new Intent(getBaseContext(),ListeMatieres.class);
          startActivity(i);

        /* AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Matière");

        builder.setMessage("Vous avez cliqué sur : " + item.getId());
        builder.setPositiveButton("Oui", null);
        builder.setNegativeButton("Non", null);
        builder.show();*/
    }
int identifiantMat;
    public void onClickUpdate(Mat item, int position) {

       // Toast.makeText(this, " Ok!!", Toast.LENGTH_LONG).show();
      identifiantMat=  item.getId();
       Intent i=new Intent(getBaseContext(),Matiere_Activity.class);
       startActivity(i);


    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("id",identifiantMat);
    }

}

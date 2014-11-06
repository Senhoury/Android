package com.example.student_agenda.app;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class Salle_Activity extends ActionBarActivity implements View.OnClickListener
        {boolean t=false;
    SalleBdd sbd = new SalleBdd(this);
            Button b1,b2,b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salle_);

        b1 = (Button)findViewById(R.id.button);
        b2 = (Button)findViewById(R.id.button2);
        b4 = (Button)findViewById(R.id.button4);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b4.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        EditText numS =(EditText)this.findViewById(R.id.et_numS);
        EditText batiment =(EditText)this.findViewById(R.id.et_Bat);
        EditText ef =(EditText)this.findViewById(R.id.et_ef);
        CheckBox clim =(CheckBox)this.findViewById(R.id.checkBox);
        CheckBox proj =(CheckBox)this.findViewById(R.id.checkBox2);

        // On récupère l'identifiant de la vue, et en fonction de cet identifiant…
        switch(v.getId()) {
// Si l'identifiant de la vue est celui du premier bouton
            case R.id.button:
/* Agir pour bouton 1 */
                if(batiment.getText().toString()==null || batiment.getText().toString().equals(" ")// ||


                        )
                { Toast.makeText(this, " Erruer de saisie !!", Toast.LENGTH_LONG).show();}
                else

                {
                      sbd.insertSalle(new Salle(Integer.parseInt(ef.getText().toString()), batiment.getText().toString(),
                       clim.getText().toString(), proj.getText().toString()));

              //On affiche les infos du livre dans un Toast
                Toast.makeText(this, " Enregistrement effectué !!", Toast.LENGTH_LONG).show();}


                break;
// Si l'identifiant de la vue est celui du deuxième bouton
           case R.id.button2:
/* Agir pour bouton 2 */
               if(batiment.getText().toString()==null || batiment.getText().toString().equals(" ") ||
                       clim.getText().toString()==null || clim.getText().toString().equals(" ") ||
                       proj.getText().toString()==null ||proj.getText().toString().equals(" ") ||
                       numS.getText().toString()== null || numS.getText().toString().equals(" ") ||
                       Integer.parseInt(numS.getText().toString())==0
                       )
               { Toast.makeText(this, " Erruer de saisie !!", Toast.LENGTH_LONG).show();}
               else
               { sbd.upateSalle(new Salle(Integer.parseInt(numS.getText().toString()),Integer.parseInt(ef.getText().toString()), batiment.getText().toString(),
                       clim.getText().toString(), proj.getText().toString()));
               Toast.makeText(this, " Mis à jour  effectuée !!", Toast.LENGTH_LONG).show();}
               break;


            case R.id.button4:
                Intent i=new Intent(getBaseContext(),ListeSalles.class);
                startActivity(i);
                break;
        }

    }




}

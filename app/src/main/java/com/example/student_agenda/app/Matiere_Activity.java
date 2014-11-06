package com.example.student_agenda.app;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;


public class Matiere_Activity extends ActionBarActivity implements View.OnClickListener
{
    private boolean b;
   // private ImageButton ib1,ib2;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText dd;
    private EditText df;
    Button b1,b3,b4;
    ImageButton ib1,ib2;

MatiereBdd mbd = new MatiereBdd(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null && 	savedInstanceState.containsKey("id"))
        { int ma_valeur = savedInstanceState.getInt("id");
           EditText n= (EditText)findViewById(R.id.et_numM);
        n.setText(ma_valeur);
            Toast.makeText(this, " " + ma_valeur, Toast.LENGTH_LONG).show();
        }

        setContentView(R.layout.activity_matiere);
        b1 = (Button)findViewById(R.id.button);
        b3 = (Button)findViewById(R.id.button3);
        b4 = (Button)findViewById(R.id.button4);
        b1.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);


/************************************************************************************/
      //  Date aujourdhui = SystemClockFactory.getDatetime();
      ib1=(ImageButton)findViewById(R.id.imageButton);
       ib1 .setOnClickListener(this);

     ib2=(ImageButton)findViewById(R.id.imageButton2);
       ib2.setOnClickListener(this);

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {

        return new DatePickerDialog(this, datePickerListener1, year, month, day);


    }
    DatePickerDialog.OnDateSetListener datePickerListener1 = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth ;
            day = selectedDay;
            editerDate();

        }
    };

    void  editerDate(){
        if(b==true)
            dd.setText(day + "/" + (month + 1) + "/"
                    + year);
        else
            df.setText(day + "/" + (month + 1) + "/"
                    + year);



    }


    @Override
    public void onClick(View v) {
        EditText numS =(EditText)this.findViewById(R.id.et_NumSalle);
        EditText nomM =(EditText)this.findViewById(R.id.et_nomM);
        EditText nomProf =(EditText)this.findViewById(R.id.et_prof);
       dd =(EditText)this.findViewById(R.id.et_dd);
        df =(EditText)this.findViewById(R.id.et_df);
        EditText hd =(EditText)this.findViewById(R.id.et_hd);
        EditText numM =(EditText)this.findViewById(R.id.et_numM);
        EditText dc =(EditText)this.findViewById(R.id.et_dc);



        // On récupère l'identifiant de la vue, et en fonction de cet identifiant…
        switch(v.getId()) {
// Si l'identifiant de la vue est celui du premier bouton
             case R.id.button:
/* Agir pour bouton 1 */
//Mat(String nm,String np,String dd,String df,String dc,String hd, int s)
    if(nomM.getText().toString()==null || nomM.getText().toString().equals(" ") ||
                nomProf.getText().toString()==null || nomProf.getText().toString().equals(" ") ||
                    dd.getText().toString()==null ||dd.getText().toString().equals(" ") ||
                  df.getText().toString()==null || df.getText().toString().equals(" ") ||
            dc.getText().toString()==null || dc.getText().toString().equals(" ") ||
            hd.getText().toString()==null || hd.getText().toString().equals(" ") ||
             numS.getText().toString()== null || numS.getText().toString().equals(" ") ||
            Integer.parseInt(numS.getText().toString())==0
            )
    {  Toast.makeText(this, " Erruer de saisie !!", Toast.LENGTH_LONG).show();}
 else {
                mbd.insertMatiere(new Mat(nomM.getText().toString(),nomProf.getText().toString(),
                        dd.getText().toString(), df.getText().toString(),
                        dc.getText().toString(),   hd.getText().toString(),
                        Integer.parseInt(numS.getText().toString())));

                //On affiche les infos du livre dans un Toast
                Toast.makeText(this, " Enregistrement effectué !!", Toast.LENGTH_LONG).show();}


                break;
// Si l'identifiant de la vue est celui du deuxième bouton
            case R.id.button3:
/* Agir pour bouton 2 */
                if(nomM.getText().toString()==null || nomM.getText().toString().equals(" ") ||
                        nomProf.getText().toString()==null || nomProf.getText().toString().equals(" ") ||
                        dd.getText().toString()==null ||dd.getText().toString().equals(" ") ||
                        df.getText().toString()==null || df.getText().toString().equals(" ") ||
                        dc.getText().toString()==null || dc.getText().toString().equals(" ") ||
                        hd.getText().toString()==null || hd.getText().toString().equals(" ") ||
                        numS.getText().toString()== null || numS.getText().toString().equals(" ") ||
                        Integer.parseInt(numS.getText().toString())==0
                        )
                {   Toast.makeText(this, " Errr de saisie !!", Toast.LENGTH_LONG).show();}
                else
                {  mbd.upateMatiere(new Mat(Integer.parseInt(numM.getText().toString()),
                        nomM.getText().toString(),nomProf.getText().toString(),
                        dd.getText().toString(), df.getText().toString(),
                        dc.getText().toString(),   hd.getText().toString(),
                        Integer.parseInt(numS.getText().toString())));
                Toast.makeText(this, " Mis à jour  effectuée !!", Toast.LENGTH_LONG).show();}
                break;


            case R.id.button4:
                Intent i=new Intent(getBaseContext(),ListeMatieres.class);
                startActivity(i);
                break;
            case R.id.imageButton2:
                b=true;
                showDialog(0);
                break;
            case R.id.imageButton:
                b=false;
                showDialog(0);
                break;

        }

    }




}

package com.example.student_agenda.app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by senhoury on 20/03/14.
 */
public class MatiereAdapter extends BaseAdapter {


    // Une liste de Matieres
    private List<Mat> mListP;


    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public MatiereAdapter(Context context, List<Mat> aListP) {
        mContext = context;
        mListP = aListP;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public int getCount() {
        return mListP.size();
    }

    @Override
    public Object getItem(int i) {
        return mListP.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        LinearLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "matiere_layout.xml"
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.matiere_layout, viewGroup, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

//(2) : Récupération des TextView de notre layout
        TextView tv_NomMat = (TextView)layoutItem.findViewById(R.id.TV_NomMat);
        TextView tv_NomProf = (TextView)layoutItem.findViewById(R.id.TV_NomProf);
        TextView tv_NumSalle = (TextView)layoutItem.findViewById(R.id.TV_NumSalle);
        TextView tv_HeureeDebut = (TextView)layoutItem.findViewById(R.id.TV_HeureDebut);
        TextView tv_Duree = (TextView)layoutItem.findViewById(R.id.TV_Duree);


        //(3) : Renseignement des valeurs
        tv_NomMat.setText("Mat: "+mListP.get(position).getNm());
        tv_NomProf.setText("Prof: "+mListP.get(position).getNp());
        tv_NumSalle.setText("Salle: "+String.valueOf (mListP.get(position).getNumSalle()));
        tv_HeureeDebut.setText("Heure début: "+mListP.get(position).getHd());
        tv_Duree.setText("Durée:"+mListP.get(position).getDc());

        layoutItem.setBackgroundColor(Color.CYAN);



        //On retourne l'item créé.
        return layoutItem;
    }
}

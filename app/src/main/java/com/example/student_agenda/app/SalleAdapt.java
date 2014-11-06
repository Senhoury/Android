package com.example.student_agenda.app;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senhoury on 26/03/14.
 */
public class SalleAdapt extends BaseAdapter {


    // Une liste de Matieres
    private List<Salle> mListP;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public SalleAdapt(Context context, List<Salle> aListP) {
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
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.salle_layout, viewGroup, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

//(2) : Récupération des TextView de notre layout
        TextView tv_NSal = (TextView)layoutItem.findViewById(R.id.tv_numSalle);
        TextView tv_bat = (TextView)layoutItem.findViewById(R.id.tv_Batim);
        TextView tv_effect = (TextView)layoutItem.findViewById(R.id.tv_effect);
        TextView tv_climat = (TextView)layoutItem.findViewById(R.id.tv_clim);
        TextView tv_projection = (TextView)layoutItem.findViewById(R.id.tv_project);

        ImageButton im_sup = (ImageButton)layoutItem.findViewById(R.id.imageButton4);
        ImageButton im_edit = (ImageButton)layoutItem.findViewById(R.id.imageButton3);

        //(3) : Renseignement des valeurs
       tv_NSal.setText("Num Salle: "+String.valueOf(mListP.get(position).getNum()));
        tv_bat.setText("Batiment: "+mListP.get(position).getBatiment());
        tv_effect.setText("Effectif: "+String.valueOf(mListP.get(position).getEffectif()));
        tv_climat.setText("Clim: "+mListP.get(position).getAvecClim());
        tv_projection.setText("proj: "+mListP.get(position).getAvecProject());

        layoutItem.setBackgroundColor(Color.GRAY);

        im_sup.setTag(position);
//On ajoute un listener
        im_sup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Lorsque l'on clique sur le nom, on récupère la position de la "Personne"
                Integer position = (Integer)v.getTag();

                //On prévient les listeners qu'il y a eu un clic sur le TextView "TV_Nom".
                sendListener(mListP.get(position), position);

            }

        });

        /**************************************************************************************/



        im_edit.setTag(position);
//On ajoute un listener
        im_edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Lorsque l'on clique sur le nom, on récupère la position de la "Personne"
                Integer position = (Integer)v.getTag();

                //On prévient les listeners qu'il y a eu un clic sur le TextView "TV_Nom".
                sendListener2(mListP.get(position), position);

            }

        });


        //On retourne l'item créé.
        return layoutItem;
    }


    /**
     * Interface pour écouter les évènements sur le nom d'une personne
     */
    public interface SalleAdapterListener {
        public void  onClickSupSalle(Salle item, int position);
        public void onClickUpdateSalle(Salle item, int position);
    }

    //Contient la liste des listeners
    private ArrayList<SalleAdapterListener> mListListener = new ArrayList<SalleAdapterListener>();
    /**
     * Pour ajouter un listener sur notre adapter
     */
    public void addListener(SalleAdapterListener aListener) {
        mListListener.add(aListener);
    }

    private void sendListener(Salle item, int position) {
        for(int i = mListListener.size()-1; i >= 0; i--) {

            mListListener.get(i).onClickSupSalle(item, position);

        }
    }

    private void sendListener2(Salle item, int position) {
        for(int i = mListListener.size()-1; i >= 0; i--) {

            mListListener.get(i).onClickUpdateSalle(item, position);
        }
    }


}

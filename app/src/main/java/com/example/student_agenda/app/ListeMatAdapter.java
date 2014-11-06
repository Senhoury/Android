package com.example.student_agenda.app;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by senhoury on 29/03/14.
 */
public class ListeMatAdapter  extends BaseAdapter  {


    // Une liste de Matieres
    private List<Mat> mListP;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;

    public ListeMatAdapter(Context context, List<Mat> aListP) {
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
            layoutItem = (LinearLayout) mInflater.inflate(R.layout.liste_layout, viewGroup, false);
        } else {
            layoutItem = (LinearLayout) convertView;
        }

//(2) : Récupération des TextView de notre layout
        TextView tv_NomMat = (TextView)layoutItem.findViewById(R.id.TV_NomMat);
        TextView tv_NomProf = (TextView)layoutItem.findViewById(R.id.TV_Nprof);
        TextView tv_IdMat = (TextView)layoutItem.findViewById(R.id.TV_IdMat);
        TextView tv_DateDebut = (TextView)layoutItem.findViewById(R.id.TV_DateDebut);
        TextView tv_DateFin = (TextView)layoutItem.findViewById(R.id.TV_DateFin);

        ImageButton im_sup = (ImageButton)layoutItem.findViewById(R.id.imageButton2);
        ImageButton im_edit = (ImageButton)layoutItem.findViewById(R.id.imageButton);

        //(3) : Renseignement des valeurs
        tv_NomMat.setText(mListP.get(position).getNm());
        tv_NomProf.setText(mListP.get(position).getNp());
        tv_IdMat.setText(String.valueOf (mListP.get(position).getId()));
        tv_DateDebut.setText(mListP.get(position).getDd());
        tv_DateFin.setText(mListP.get(position).getDf());

        layoutItem.setBackgroundColor(Color.CYAN);

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
    public interface MatiereAdapterListener {
        public void  onClickSup(Mat item, int position);
        public void onClickUpdate(Mat item, int position);
    }

    //Contient la liste des listeners
    private ArrayList<MatiereAdapterListener> mListListener = new ArrayList<MatiereAdapterListener>();
    /**
     * Pour ajouter un listener sur notre adapter
     */
    public void addListener(MatiereAdapterListener aListener) {
        mListListener.add(aListener);
    }

    private void sendListener(Mat item, int position) {
        for(int i = mListListener.size()-1; i >= 0; i--) {

                mListListener.get(i).onClickSup(item, position);

        }
    }

   private void sendListener2(Mat item, int position) {
        for(int i = mListListener.size()-1; i >= 0; i--) {

            mListListener.get(i).onClickUpdate(item, position);
        }
    }





}

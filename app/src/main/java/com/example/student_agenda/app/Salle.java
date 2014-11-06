package com.example.student_agenda.app;

import java.util.ArrayList;

/**
 * Created by senhoury on 18/03/14.
 */
public class Salle {
    private int num,effectif;
    private String batiment, avecClim,avecProject;

    public Salle() {
    }

    public Salle(int effectif,String batiment,String avecClim,String avecProject) {

        this.avecClim=avecClim;
        this.avecProject=avecProject;
        this.batiment=batiment;
        this.effectif=effectif;
    }

    public Salle(int numS,int effectif,String batiment,String avecClim,String avecProject) {
        this.num=numS;
        this.avecClim=avecClim;
        this.avecProject=avecProject;
        this.batiment=batiment;
        this.effectif=effectif;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getBatiment() {
        return batiment;
    }

    public void setBatiment(String batiment) {
        this.batiment = batiment;
    }

    public String getAvecProject() {
        return avecProject;
    }

    static ArrayList<Salle> getL()
    {
        ArrayList<Salle> listM = new ArrayList<Salle>();

        listM.add(new Salle(45,"l","l","l"));
        listM.add(new  Salle(55,"l","l","l"));
        return listM;
    }

    public void setAvecProject(String avecProject) {
        this.avecProject = avecProject;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    public String getAvecClim() {
        return avecClim;
    }

    public void setAvecClim(String avecClim) {
        this.avecClim = avecClim;
    }
}

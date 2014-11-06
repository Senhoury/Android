package com.example.student_agenda.app;

import java.util.ArrayList;

/**
 * Created by senhoury on 18/03/14.
 */
public class Mat {
    private int id,numSalle;
    private String nm, np,dd,df,dc,hd;

    public Mat() {
    }

    public Mat(String nm,String np,String dd,String df,String dc,String hd, int s) {

        this.nm=nm;
        this.np=np;
        this.dd=dd;
        this.df=df;
        this.dc=dc;
        this.hd=hd;
        this.numSalle=s;
    }

    public Mat(int id,String nm,String np,String dd,String df,String dc,String hd, int s) {
        this.id=id;
        this.nm=nm;
        this.np=np;
        this.dd=dd;
        this.df=df;
        this.dc=dc;
        this.hd=hd;
        this.numSalle=s;
    }

    public String getNp() {
        return np;
    }

    public void setNp(String np) {
        this.np = np;
    }

    public String getDf() {
        return df;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public String getHd() {
        return hd;
    }

static  ArrayList<Mat> getL()
{
    ArrayList<Mat> listM = new ArrayList<Mat>();

    listM.add(new Mat("l","l","l","l","l","l",2));
    listM.add(new  Mat("l","l","l","l","l","l",3));
    return listM;
}
    public void setHd(String hd) {
        this.hd = hd;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public void setDf(String df) {
        this.df = df;
    }

    public String getDd() {
        return dd;
    }

    public void setDd(String dd) {
        this.dd = dd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumSalle() {
        return numSalle;
    }

    public void setNumSalle(int numSalle) {
        this.numSalle = numSalle;
    }
}

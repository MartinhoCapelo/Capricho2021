package br.com.caprichoatoa.utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;

public class ComparaDatas {

    public ComparaDatas() {
        super();
        // TODO Auto-generated constructor stub
    }

    @SuppressLint("SimpleDateFormat")
    public int d1Maior(String sD1, String sD2) {
        int retorno = 0;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

        try {
            Date d1 = format.parse(sD1);
            Date d2 = format.parse(sD2);
            retorno = d1.compareTo(d2);
        } catch (ParseException e) {

        }
        return retorno;

    }

}


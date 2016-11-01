/*
 * Created by Roger G. Coscojuela on 1/11/16 3:40
 * Copyright (c) 2016.. All rights reserved.
 *
 * Background images courtesy of MysticArtDesign:
 * https://pixabay.com/es/users/Mysticsartdesign-322497/
 *
 * Klingon translations thanks to:
 * http://tradukka.com/translate/en/tlh?hl=ca
 */

package com.rogergili.deathtelling;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.rogergili.deathtelling.Model.calculadoraMuerte;


public class MostrarMort extends AppCompatActivity {

    private int klingon;
    private int genero;
    private int fumas;
    private int bebes;
    private String nombre="";
    private int edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarmort);


        //Si no existeix una instancia guardada, significa que acabem d'accedir a l'activity.
        //Carreguem, per tant, les variables pasades en l'intent.
        if (savedInstanceState == null) {
            Intent extras = getIntent();
            this.klingon = extras.getIntExtra("klingon",0);
            this.genero = extras.getIntExtra("genero",0);
            this.fumas = extras.getIntExtra("fumas",0);
            this.bebes = extras.getIntExtra("bebe",0);
            this.nombre= extras.getStringExtra("nom");
            try {
                this.edad= Integer.parseInt(extras.getStringExtra("edad"));
            }
            catch (NumberFormatException e){

            }

        }
        //Si hi ha una instancia guardada, recuperem els valors, y fem les asignacions corresponents.
        //Es guarda una instancia, per exemple, al fer una rotació de pantalla.
        else{
            this.genero = savedInstanceState.getInt("sexo");
            this.bebes = savedInstanceState.getInt("bebes");
            this.fumas = savedInstanceState.getInt("fumas");
            this.edad= savedInstanceState.getInt("edad");
            this.nombre= savedInstanceState.getString("nom");
            this.klingon= savedInstanceState.getInt("klingon");
        }
        //ambdues layouts tenen els mateixos elements amb els mateixos id.
        TextView txViewMuerte = (TextView) findViewById(R.id.textVResultadoMuerteV);
        TextView txViewNom = (TextView) findViewById(R.id.textVNombreResMuerteV);

        //Instanciem un nou objecte de la clase calculadora, y obtenim l'id de recurs asociat a la causa de la mort (String muerteID)
        calculadoraMuerte calculadora = new calculadoraMuerte(klingon,genero,fumas,bebes);
        String muerteID = calculadora.getMuerte();

        //TODO No estic segur que obtindre un recurs d'String en temps d'execucio sigui una bona praxis de programació.
        //Mitjançant l'ID obtinguda, recuperem el resourceStringId que correspon a la causa de la mort.
        int resourceStringId = getApplicationContext().getResources().getIdentifier(muerteID, "string", "com.rogergili.deathtelling");

        //Un valor 0 significara que no existeix aquest recurs en el fitxer d'strings, per tant, enviem un error a consola.
        if (resourceStringId > 0) {
            txViewMuerte.setText(resourceStringId);
            txViewNom.setText(nombre);
        } else {
            Log.e("Calculo muerte",getString(R.string.resource_not_found) + muerteID);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("sexo", this.genero);
        outState.putInt("bebes", this.bebes);
        outState.putInt("fumas", this.fumas);
        outState.putString("nom", this.nombre);
        outState.putInt("edad", this.edad);
        outState.putInt("klingon", this.klingon);

    }

}
/*
 * Created by Roger G. Coscojuela on 31/10/16 4:23
 * Copyright (c) 2016.. All rights reserved.
 *
 * Background images courtesy of MysticArtDesign
 * https://pixabay.com/es/users/Mysticsartdesign-322497/
 */

package com.rogergili.deathtelling;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.rogergili.deathtelling.Model.calculadoraMuerte;


public class MostrarMort extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrarmort);

        TextView textView = (TextView) findViewById(R.id.ResultadoMuerte);
        String muerteID = "C0000";
        calculadoraMuerte calculadora = new calculadoraMuerte(1, 3, 1, 1);
        muerteID = calculadora.getMuerte();
        //TODO Arreglar aquesta barbarie de codi.

        int resourceStringId = getApplicationContext().getResources().getIdentifier(muerteID, "string", "com.rogergili.deathtelling");
        if (resourceStringId > 0) {
            textView.setVisibility(View.VISIBLE);
            textView.setText(resourceStringId);
        } else {
            Log.e("Calculo muerte","Recurso no encontrado");
        }
    }
}

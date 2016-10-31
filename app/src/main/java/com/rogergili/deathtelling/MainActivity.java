/*
 * Created by Roger G. Coscojuela on 31/10/16 2:55
 * Copyright (c) 2016.. All rights reserved.
 *
 * Background images courtesy of MysticArtDesign
 * https://pixabay.com/es/users/Mysticsartdesign-322497/
 */

package com.rogergili.deathtelling;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int selectedSpinnerBebes = 0;
    private int selectedSpinnerSexo = 0;
    private int selectedRadioButton = 0;
    private int selectedKlingon = 0;

    private String orientacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        //Si hi ha una instancia guardada en recuperem les variables.
        if (savedInstanceState != null) {
            this.selectedSpinnerSexo = savedInstanceState.getInt("sexo");
            this.selectedSpinnerBebes = savedInstanceState.getInt("bebes");
            this.selectedRadioButton = savedInstanceState.getInt("fumas");
        } else {
            //Missatge per invitar a trobar un dels ous de pasqua del programa ;)
            Toast.makeText(this, "Los aliens no existen...¿Verdad?", Toast.LENGTH_SHORT).show();
        }
        //Carreguem el metode cargaPantalla en vertical o horitzontal depenen de la orientacio de la pantalla.
        if (display.getRotation() == Surface.ROTATION_0 || display.getRotation() == Surface.ROTATION_180) {
            this.orientacio = "vertical";
            cargaPantalla(orientacio);
        } else {
            this.orientacio = "horitzontal";
            cargaPantalla(orientacio);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Introduim les dades que volem guardar en cas que es giri la pantalla.
        outState.putInt("sexo", this.selectedSpinnerSexo);
        outState.putInt("bebes", this.selectedSpinnerBebes);
        outState.putInt("fumas", this.selectedRadioButton);
    }

    public void cargaPantalla(String orientacio) {
        Spinner sexo;
        Spinner bebes;
        RadioButton RBn;
        RadioButton RBy;

        //inicialitzem els identificadors de recursos adecuats a la orientacio de la pantalla.
        if (orientacio == "vertical") {
            sexo = (Spinner) findViewById(R.id.spinnerSexoV);
            bebes = (Spinner) findViewById(R.id.spinnerBebesV);
            RBn = (RadioButton) findViewById(R.id.radioButtonNV);
            RBy = (RadioButton) findViewById(R.id.radioButtonYV);
        } else {
            sexo = (Spinner) findViewById(R.id.spinnerSexoH);
            bebes = (Spinner) findViewById(R.id.spinnerBebesH);
            RBn = (RadioButton) findViewById(R.id.radioButtonNH);
            RBy = (RadioButton) findViewById(R.id.radioButtonYH);
        }
        //Creem els listeners als dos Spinners
        sexo.setOnItemSelectedListener(this);
        bebes.setOnItemSelectedListener(this);

        //Creem un adaptador per als Spinners.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.yesNo, R.layout.spinner_right);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.genero, R.layout.spinner_right);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Apliquem l'adaptador als Spinners.
        sexo.setAdapter(adapter2);
        bebes.setAdapter(adapter);

        //Seleccionem la posicio adequada al spinner de seleccio de genere
        if (selectedSpinnerSexo == 0 || selectedSpinnerSexo == 1) {
            sexo.setSelection(selectedSpinnerSexo);
        }
        if (selectedSpinnerSexo == 2) {
            //Un petit ou de pasqua que nomes entendran els fans d'Star Trek ;)
            showKlingon(orientacio, true);
            sexo.setSelection(selectedSpinnerSexo);
        }
        //Seleccionem la posicio adequada al spinner de beguda
        bebes.setSelection(selectedSpinnerBebes);

        //Seleccionem la opcio corresponent al radioButton
        if (this.selectedRadioButton == 0) {
            RBn.setChecked(true);
            RBy.setChecked(false);
        } else {
            RBn.setChecked(false);
            RBy.setChecked(true);
        }

        //Marquem la checkbox del llenguatge klingon en cas que sigui necesari
        if (selectedKlingon == 1) {
            CheckBox checkKlingon;
            if (orientacio == "horitzontal") {
                checkKlingon = (CheckBox) findViewById(R.id.checkBoxKlingonH);
            } else {
                checkKlingon = (CheckBox) findViewById(R.id.checkBoxKlingonV);
            }
            checkKlingon.setChecked(true);
        }
    }


    public void showKlingon(String orientacio, boolean Mostrar) {
        TextView textKlingon;
        CheckBox checkKlingon;
        if (orientacio == "vertical") {
            textKlingon = (TextView) findViewById(R.id.tViewKlingonV);
            checkKlingon = (CheckBox) findViewById(R.id.checkBoxKlingonV);
        } else {
            textKlingon = (TextView) findViewById(R.id.tViewKlingonH);
            checkKlingon = (CheckBox) findViewById(R.id.checkBoxKlingonH);
        }
        if (Mostrar) {
            textKlingon.setVisibility(View.VISIBLE);
            checkKlingon.setVisibility(View.VISIBLE);
        } else {
            textKlingon.setVisibility(View.INVISIBLE);
            checkKlingon.setChecked(false);
            checkKlingon.setVisibility(View.INVISIBLE);
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        //Comprobem quin RadioButton esta seleccionat i actuem en consecuencia.
        switch (view.getId()) {
            case R.id.radioButtonNH:
                if (checked) {
                    this.selectedRadioButton = 0;
                }
                break;
            case R.id.radioButtonYH:
                if (checked) {
                    this.selectedRadioButton = 1;
                }
                break;
            case R.id.radioButtonNV:
                if (checked) {
                    this.selectedRadioButton = 0;
                }
                break;
            case R.id.radioButtonYV:
                if (checked) {
                    this.selectedRadioButton = 1;
                }
                break;
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerSexoH:
                this.selectedSpinnerSexo = position;
                //la opcio 2 activa el checkbox ocult Klingon
                if (position == 2) {
                    showKlingon("horizontal", true);
                } else {
                    showKlingon("horizontal", false);
                }
                break;
            case R.id.spinnerSexoV:
                this.selectedSpinnerSexo = position;
                if (position == 2) {
                    showKlingon("vertical", true);
                } else {
                    showKlingon("vertical", false);
                }
                break;
            case R.id.spinnerBebesH:
                this.selectedSpinnerBebes = position;
                break;
            case R.id.spinnerBebesV:
                this.selectedSpinnerBebes = position;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

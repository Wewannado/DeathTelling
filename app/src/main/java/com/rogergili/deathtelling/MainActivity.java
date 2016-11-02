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

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {
    private int selectedSpinnerBebes = 0;
    private int selectedSpinnerSexo = 0;
    private int selectedRadioButton = 0;
    private int selectedKlingon = 0;
    private int anys = 0;
    private String nom = "";
    private EditText eTnom;
    private EditText eTedad;
    private Spinner sexo;
    private Spinner bebes;
    private RadioButton botoRadioButtonNo;
    private RadioButton botoRadioButtonSi;
    private Button calcula;
    private CheckBox checkKlingon;
    private TextView textKlingon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Si hi ha una instancia guardada en recuperem les variables.
        if (savedInstanceState != null) {
            this.selectedSpinnerSexo = savedInstanceState.getInt("sexo");
            this.selectedSpinnerBebes = savedInstanceState.getInt("bebes");
            this.selectedRadioButton = savedInstanceState.getInt("fumas");
            this.anys = savedInstanceState.getInt("edad");
            this.nom = savedInstanceState.getString("nom");
            this.selectedKlingon = savedInstanceState.getInt("klingon");
        } else {
            //Missatge per invitar a trobar un dels ous de pasqua del programa ;)
            Toast.makeText(this, R.string.aliens, Toast.LENGTH_SHORT).show();
        }
        cargaPantalla();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("klingon", this.selectedKlingon);
        outState.putInt("sexo", this.selectedSpinnerSexo);
        outState.putInt("bebes", this.selectedSpinnerBebes);
        outState.putInt("fumas", this.selectedRadioButton);
        outState.putString("nom", eTnom.getText().toString());
        try {
            outState.putInt("edad", Integer.parseInt(eTedad.getText().toString()));
        } catch (NumberFormatException e) {

        }
    }

    public void cargaPantalla() {
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();

        //inicialitzem els identificadors de recursos adecuats a la orientacio de la pantalla.
        if (display.getRotation() == Surface.ROTATION_0 || display.getRotation() == Surface.ROTATION_180) {
            sexo = (Spinner) findViewById(R.id.spinnerSexoV);
            bebes = (Spinner) findViewById(R.id.spinnerBebesV);
            botoRadioButtonNo = (RadioButton) findViewById(R.id.radioButtonNV);
            botoRadioButtonSi = (RadioButton) findViewById(R.id.radioButtonYV);
            calcula = (Button) findViewById(R.id.calculaV);
            eTedad = (EditText) findViewById(R.id.eTextEdadV);
            eTnom = (EditText) findViewById(R.id.eTextNombreV);
            textKlingon = (TextView) findViewById(R.id.tViewKlingonV);
            checkKlingon = (CheckBox) findViewById(R.id.checkBoxKlingonV);
        } else {
            sexo = (Spinner) findViewById(R.id.spinnerSexoH);
            bebes = (Spinner) findViewById(R.id.spinnerBebesH);
            botoRadioButtonNo = (RadioButton) findViewById(R.id.radioButtonNH);
            botoRadioButtonSi = (RadioButton) findViewById(R.id.radioButtonYH);
            calcula = (Button) findViewById(R.id.calculaH);
            eTedad = (EditText) findViewById(R.id.eTextEdadH);
            eTnom = (EditText) findViewById(R.id.eTextNombreH);
            textKlingon = (TextView) findViewById(R.id.tViewKlingonH);
            checkKlingon = (CheckBox) findViewById(R.id.checkBoxKlingonH);
        }


        //Creem els listeners als dos Spinners i al boto de calcular.
        sexo.setOnItemSelectedListener(this);
        bebes.setOnItemSelectedListener(this);
        calcula.setOnClickListener(this);
        checkKlingon.setOnClickListener(this);

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
            showKlingon(true);
            sexo.setSelection(selectedSpinnerSexo);
        }
        //Seleccionem la posicio adequada al spinner de beguda
        bebes.setSelection(selectedSpinnerBebes);
        //Seleccionem la opcio corresponent al radioButton
        if (this.selectedRadioButton == 0) {
            botoRadioButtonNo.setChecked(true);
            botoRadioButtonSi.setChecked(false);
        } else {
            botoRadioButtonNo.setChecked(false);
            botoRadioButtonSi.setChecked(true);
        }

        //Marquem la checkbox del llenguatge klingon en cas que sigui necesari
        if (selectedKlingon == 1) {
            checkKlingon.setChecked(true);
        } else {
            checkKlingon.setChecked(false);
        }

        //Inicialitzem el text adecuat als EditText.

        eTnom.setText(nom);
        if (anys != 0) {
            eTedad.setText(String.valueOf(anys));
        }

    }

    public void showKlingon(boolean Mostrar) {
        if (Mostrar) {
            textKlingon.setVisibility(View.VISIBLE);
            checkKlingon.setVisibility(View.VISIBLE);
        } else {
            textKlingon.setVisibility(View.INVISIBLE);
            checkKlingon.setChecked(false);
            checkKlingon.setVisibility(View.INVISIBLE);
            selectedKlingon = 0;
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
            case R.id.spinnerSexoV:
                this.selectedSpinnerSexo = position;
                //la opcio 2 activa el checkbox ocult Klingon
                if (position == 2) {
                    showKlingon(true);
                } else {
                    showKlingon(false);
                }
                break;
            case R.id.spinnerBebesH:
            case R.id.spinnerBebesV:
                this.selectedSpinnerBebes = position;
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View view) {
        if ((view.getId() == R.id.calculaH) || (view.getId() == R.id.calculaV)) {
            if (eTnom.getText().toString().equals("")) {
                Toast.makeText(this, R.string.sinNombre, Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, MostrarMort.class);
                intent.putExtra("klingon", selectedKlingon);
                intent.putExtra("genero", selectedSpinnerSexo);
                intent.putExtra("fumas", selectedRadioButton);
                intent.putExtra("bebe", selectedSpinnerBebes);
                intent.putExtra("nom", eTnom.getText().toString());
                intent.putExtra("edad", eTedad.getText().toString());
                startActivity(intent);
            }
        }

        if ((view.getId() == R.id.checkBoxKlingonH) || (view.getId() == R.id.checkBoxKlingonV)) {
            if (checkKlingon.isChecked()) {
                this.selectedKlingon = 1;
            } else {
                this.selectedKlingon = 0;
            }
        }
    }
}

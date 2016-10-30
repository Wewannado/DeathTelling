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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private int selectedSpinnerBebes = -1;
    private int selectedSpinnerSexo = -1;
    private int selectedRadioButton = -1;
    private String orientacio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        if (savedInstanceState != null) {
            //TODO recuperar la informacion que necesitemos si hay una instancia guardada.
            this.selectedSpinnerSexo = savedInstanceState.getInt("sexo");
            this.selectedSpinnerBebes = savedInstanceState.getInt("bebes");
            this.selectedRadioButton = savedInstanceState.getInt("fumas");
        }
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
        //TODO introducir la informacion que necesitemos guardar al girar esta activity
        outState.putInt("sexo", this.selectedSpinnerSexo);
        outState.putInt("bebes", this.selectedSpinnerBebes);
        outState.putInt("fumas", this.selectedRadioButton);
    }

    public void cargaPantalla(String orientacion) {
        Spinner sexo;
        Spinner bebes;
        RadioButton RBn;
        RadioButton RBy;
        if (orientacion == "vertical") {
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
        sexo.setOnItemSelectedListener(this);
        bebes.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.yesNo, R.layout.spinner_right);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sexo.setAdapter(adapter);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.yesNo, R.layout.spinner_right);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bebes.setAdapter(adapter);


        if (this.selectedSpinnerSexo != -1) {
            sexo.setSelection(selectedSpinnerSexo);
        }
        if (this.selectedSpinnerBebes != -1) {
            bebes.setSelection(selectedSpinnerBebes);
        }
        Toast.makeText(this, String.valueOf(selectedRadioButton), Toast.LENGTH_SHORT).show();
        if (this.selectedRadioButton != -1) {
            Toast.makeText(this, String.valueOf(selectedRadioButton), Toast.LENGTH_SHORT).show();
            if (this.selectedRadioButton == 0) {
                RBn.setChecked(true);
                RBy.setChecked(false);
            } else {
                RBn.setChecked(false);
                RBy.setChecked(true);
            }

        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioButtonNH:
                if (checked){
                this.selectedRadioButton = 0;}
                break;
            case R.id.radioButtonYH:
                if (checked){
                this.selectedRadioButton = 1;}
                break;
            case R.id.radioButtonNV:
                if (checked){
                this.selectedRadioButton = 0;}
                break;
            case R.id.radioButtonYV:
                if (checked){
                this.selectedRadioButton = 1;}
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.spinnerSexoH:
                this.selectedSpinnerSexo = position;
                break;
            case R.id.spinnerSexoV:
                this.selectedSpinnerSexo = position;
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

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

package com.rogergili.deathtelling.Model;

/**
 * Created by IES on 31/10/2016.
 */

public class calculadoraMuerte {
    private int klingon;
    private int genero;
    private int fuma;
    private int bebe;


    public calculadoraMuerte(int klingon, int genero, int fuma, int bebe) {
        this.klingon = klingon;
        this.genero = genero;
        this.fuma = fuma;
        this.bebe = bebe;
    }

    public String getMuerte() {
        return "C"+String.valueOf(klingon) + String.valueOf(genero) + String.valueOf(fuma) + String.valueOf(bebe);
    }
}

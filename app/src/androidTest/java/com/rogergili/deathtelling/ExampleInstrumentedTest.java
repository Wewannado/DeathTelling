/*
 * Created by Roger G. Coscojuela on 31/10/16 2:55
 * Copyright (c) 2016.. All rights reserved.
 *
 * Background images courtesy of MysticArtDesign
 * https://pixabay.com/es/users/Mysticsartdesign-322497/
 */

package com.rogergili.deathtelling;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.rogergili.deathtelling", appContext.getPackageName());
    }
}

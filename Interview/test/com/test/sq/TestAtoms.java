package com.test.sq;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import sq.NumberOfAtoms;

public class TestAtoms {

    NumberOfAtoms numAtoms = null;

    @Before
    public void setUp() {
        numAtoms = new NumberOfAtoms();
    }

    @Test
    public void atomEdgeCasesTest() {
        String[] formulas = new String[] { "", null, "H", "H2", "Be32" };
        String[] expected = new String[] { "", "", "H", "H2", "Be32" };
        for (int j = 0; j < formulas.length; j++)
            assertEquals(numAtoms.count(formulas[j]), expected[j]);
    }

    @Test
    public void atomCorrectnessTest() {
        String[] formulas = new String[] { "H2O", "H2O2", "H2O2He3Mg4", "(H2O2)3", "Mg(OH)2", "K4(ON(SO3)2)2",
                "H11He49NO35B7N46Li20", "(B2O39He17BeBe49)39" };

        String[] expected = new String[] { "H2O", "H2O2", "H2He3Mg4O2", "H6O6", "H2MgO2", "K4N2O14S4",
                "B7H11He49Li20N47O35", "B78Be1950He663O1521" };

        for (int j = 0; j < formulas.length; j++)
            assertEquals(numAtoms.count(formulas[j]), expected[j]);
    }
}

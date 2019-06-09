package com.Remigiusz.MacronutrientsApiREST.Utility;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.IsEqual.*;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.jupiter.api.Assertions.assertAll;

public class MacrosCalculatorTest {

    @Test
    public void shouldReturnAppropriateDoubleValue() {

        //given
        double result_1 = MacrosCalculator.calculate(160,100);
        double result_2 = MacrosCalculator.calculate(221,96);
        double result_3 = MacrosCalculator.calculate(11,140);


        //then
        assertAll(
                () -> assertThat(result_1,is(160.0)),
                () -> assertThat(result_2,is(212.2)),
                () -> assertThat(result_3,is(15.4)));

    }





}
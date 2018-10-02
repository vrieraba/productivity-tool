package com.vriera.productivity;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class MonthTest {

    @DataProvider
    private Object[][] monthNumberDataProvider() {
        return new Object[][] {
                {Month.ENERO, 1},
                {Month.FEBRERO, 2},
                {Month.MARZO, 3},
                {Month.ABRIL, 4},
                {Month.MAYO, 5},
                {Month.JUNIO, 6},
                {Month.JULIO, 7},
                {Month.AGOSTO, 8},
                {Month.SEPTIEMBRE, 9},
                {Month.OCTUBRE, 10},
                {Month.NOVIEMBRE, 11},
                {Month.DICIEMBRE, 12}
        };
    }

    @Test(dataProvider = "monthNumberDataProvider")
    public void testGetMonthNumber(Month actualMonth, int expectedMonthNumber) {
        //When
        int actual = actualMonth.getMonthNumber();

        //Then
        Assert.assertEquals(actual, expectedMonthNumber);
    }

    @Test
    public void testGetPreviousMonth() {
        //When
        Month actual = Month.ENERO.getPreviousMonth();

        //Then
        Assert.assertEquals(actual, Month.DICIEMBRE);
    }

    @Test
    public void testFindByMonth() {
        //When
        Month actual = Month.findMonthBy(1);

        //Then
        Assert.assertEquals(actual, Month.ENERO);
    }

    @Test(expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Could not find month for monthNumber: 13")
    public void testFindByMonthWhenMonthNumberDoesNotExists() {
        //When
        Month.findMonthBy(13);
    }
}
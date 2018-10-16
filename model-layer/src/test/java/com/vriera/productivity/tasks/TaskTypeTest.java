package com.vriera.productivity.tasks;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TaskTypeTest {

    @DataProvider
    private Object[][] displayTextDataProvider() {
        return new Object[][] {
                {"Evolutivo (ENP)", TaskType.EVOLUTIVO},
                {"Correctivo (COR)", TaskType.CORRECTIVO},
                {"Consulta (CON)", TaskType.SOPORTE},
                {"Corrección Incidencias", TaskType.CORRECCION_INCIDENCIA}
        };
    }

    @Test(dataProvider = "displayTextDataProvider")
    public void testFindByDisplayText(String actualText, TaskType expectedTaskType) {
        //When
        TaskType actual = TaskType.findByDisplayText(actualText);

        //Then
        Assert.assertEquals(actual, expectedTaskType);

    }

    @Test(expectedExceptions = {IllegalArgumentException.class}, expectedExceptionsMessageRegExp = "Could not find Type for displayText: 'Hola'")
    public void testFindByDisplayTextWhenTextDoesNotExists() {
        //When
        TaskType.findByDisplayText("Hola");
    }
}
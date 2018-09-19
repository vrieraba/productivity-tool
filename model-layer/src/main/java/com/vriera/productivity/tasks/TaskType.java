package com.vriera.productivity.tasks;

public enum TaskType {
    EVOLUTIVO("Evolutivo (ENP)"),
    CORRECTIVO("CORRECTIVO (CNP)"),
    SOPORTE("Consulta (CON)"),
    CORRECCION_INCIDENCIA("Corrección Incidencias");

    private String displayText;

    TaskType(String displayText) {
        this.displayText = displayText;
    }

    public static TaskType findByDisplayText(String text) {
        for (TaskType taskType : TaskType.values()) {
            if (taskType.displayText.equals(text)) {
                return taskType;
            }
        }
        throw new IllegalArgumentException("Could not find Type for displayText: '" + text + "'");
    }
}

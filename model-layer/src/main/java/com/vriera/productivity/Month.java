package com.vriera.productivity;

public enum Month {
    ENERO(1),
    FEBRERO(2),
    MARZO(3),
    ABRIL(4),
    MAYO(5),
    JUNIO(6),
    JULIO(7),
    AGOSTO(8),
    SEPTIEMBRE(9),
    OCTUBRE(10),
    NOVIEMBRE(11),
    DICIEMBRE(12);

    private int monthNumber;

    Month(int monthNumber) {
        this.monthNumber = monthNumber;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public Month getPreviousMonth() {
        int previousMonthNumber = getMonthNumber() - 1;
        if (previousMonthNumber < 1) {
            previousMonthNumber = 12;
        }
        return Month.findMonthBy(previousMonthNumber);
    }

    public static Month findMonthBy(int monthNumber) {
        for (Month month : Month.values()) {
            if (month.monthNumber == monthNumber) {
                return month;
            }
        }
        throw new IllegalArgumentException("Could not find month for monthNumber: " + monthNumber);
    }

}

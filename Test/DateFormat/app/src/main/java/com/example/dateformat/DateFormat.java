package com.example.dateformat;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormat {
    private Date date;

    public DateFormat(Date date) {
        this.date = date;
    }

    public String MyFormat() {
        SimpleDateFormat s = new SimpleDateFormat("EEEE, MMMM,M,MM,MMM");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(s.format(date));

        return stringBuilder.toString();
    }

}

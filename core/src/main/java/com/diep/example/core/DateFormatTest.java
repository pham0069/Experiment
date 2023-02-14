package com.diep.example.core;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateFormatTest {
    private static final DateFormat FORMAT = new SimpleDateFormat("MM/dd/yy");
    static {
        //FORMAT.setTimeZone(TimeZone.getTimeZone("GMT+2"));
    }

    public static void main(String[] args) throws Exception {
        Date date = FORMAT.parse("11/06/20");
        System.out.println(date.getTimezoneOffset() + " " +
                date.toInstant().toEpochMilli() + " " +
                date.getMonth() + " " +
                        date.getDate() + " " +
                        date.getHours() + " " +
                date.getMinutes()

                );
    }
}

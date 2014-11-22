package com.correios.edwinos.consultacorreios.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by edwinos on 21/11/14.
 */
public class DateParser {

    public static String fullDataToString(Date date){
        return date.getDate()+" de "+DateParser.getMouthName(date)+" de "+getYear(date);
    }

    public static int getYear(Date date){
        return date.getYear()+1900;
    }

    public static String getMouthName(Date date){
        String mouth = "Indefinido";
        switch (date.getMonth()){
            case Calendar.JANUARY:  mouth = "Janeiro";      break;
            case Calendar.FEBRUARY: mouth = "Fevereiro";    break;
            case Calendar.MARCH:    mouth = "Março";        break;
            case Calendar.APRIL:    mouth = "Abril";        break;
            case Calendar.MAY:      mouth = "Maio";         break;
            case Calendar.JUNE:     mouth = "Junho";        break;
            case Calendar.JULY:     mouth = "Julho";        break;
            case Calendar.AUGUST:   mouth = "Agosto";       break;
            case Calendar.SEPTEMBER:mouth = "Setembro";     break;
            case Calendar.OCTOBER:  mouth = "Outubro";      break;
            case Calendar.NOVEMBER: mouth = "Novembro";     break;
            case Calendar.DECEMBER: mouth = "Dezembro";     break;
        }

        return mouth;
    }

    public static String getWeekDayName(Date date){
        String weekDay = "Indefinido";

        switch (date.getDay() + 1){
            case Calendar.SUNDAY:   weekDay = "Domingo";        break;
            case Calendar.MONDAY:   weekDay = "Segunda";  break;
            case Calendar.TUESDAY:  weekDay = "Terça";    break;
            case Calendar.WEDNESDAY:weekDay = "Quarta";   break;
            case Calendar.THURSDAY: weekDay = "Quinta";   break;
            case Calendar.FRIDAY:   weekDay = "Sexta";    break;
            case Calendar.SATURDAY: weekDay = "Sábado";         break;
        }

        return weekDay;
    }

    public static String getHour(Date date){
        return date.getHours()+":"+date.getMinutes();
    }
}

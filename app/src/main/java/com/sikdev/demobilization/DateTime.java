package com.sikdev.demobilization;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.Calendar;

public class DateTime {
    private Calendar callDate;
    private Calendar demobilizationDate;
    private Calendar newDate;

    public DateTime(Context context) {
        callDate = Calendar.getInstance();
        callDate.setTimeInMillis(readDate(context));
        // Вычисление кол-во дней до дембелизации
        calculatingDemobilization();
        // Получение новой даты
        newDate();
    }

    // Сохранение даты
    public void saveDate(long millis, Context context) {
        // Запись даты
        SharedPreferences sharedPreferences = context
                .getSharedPreferences("Settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putLong("callDateMillis", millis).commit();
    }

    // Считывание даты
    public long readDate(Context context) {
        // Считывание даты
        SharedPreferences sharedPreferences = context
                .getSharedPreferences("Settings", Context.MODE_PRIVATE);
        long millis = sharedPreferences.getLong("callDateMillis", 0);
        // Проверка на существовании записи
        if (millis == 0) {
            // Получени текущей даты
            Calendar newCalendar = Calendar.getInstance();
            millis = newCalendar.getTimeInMillis();
            // Сохранение даты
            saveDate(millis, context);
        }
        // Возращение даты в милесикундах
        return millis;
    }

    // Вычисление даты дембеля
    private void calculatingDemobilization() {
        demobilizationDate = Calendar.getInstance();
        demobilizationDate.add(Calendar.DAY_OF_MONTH, 365);
    }

    // Получение новой даты
    public void newDate() {
        newDate = Calendar.getInstance();
    }

    // Общая разница в днях
    public int differenceDay() {
        long millis = demobilizationDate.getTimeInMillis() - newDate.getTimeInMillis();
        return Math.round(millis / 1000 / 60 / 60 / 24);
    }

    // Получение даты
    public int month() {
        int day = differenceDay();
        int month = 0;
        Calendar calendar = Calendar.getInstance();
        calendar = callDate;
        while (day >= 0) {
            // Прошло дней в месяце и общее кол-во дней в месяце
            int dayOfMonth = 0;
            if (month == 0)
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int dayMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            // Отнимаем дни
            day = day - (dayMonth - dayOfMonth);
            calendar.add(Calendar.MONTH, 1);
            // Прибавление кол-во месяцев
            if (day > 0) {
                month++;
            }
        }
        return month - 1;
    }

    public int week() {
        return 0;
    }

    public int day() {
        return 0;
    }

    // Получение времени
    public int second() {
        return newDate.get(Calendar.SECOND);
    }

    public int minute() {
        return newDate.get(Calendar.MINUTE);
    }

    public int hour() {
        return newDate.get(Calendar.HOUR);
    }
}

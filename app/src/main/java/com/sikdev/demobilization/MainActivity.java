package com.sikdev.demobilization;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    // Класс с обработкой дат
    DateTime dateTime;
    // Компоненты интерфеса
    TextView txt_sumDay;
    // Время
    TextView txt_hour;
    TextView txt_minute;
    TextView txt_seconds;
    // Дата
    TextView txt_month;
    TextView txt_week;
    TextView txt_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = MainActivity.this;
        // Обьявление компонентов
        txt_sumDay = findViewById(R.id.txt_sumDay);
        // Время
        txt_hour = findViewById(R.id.txt_hour);
        txt_minute = findViewById(R.id.txt_minute);
        txt_seconds = findViewById(R.id.txt_seconds);
        // Дни
        txt_month = findViewById(R.id.txt_month);
        txt_week = findViewById(R.id.txt_week);
        txt_day = findViewById(R.id.txt_day);
        // Обьявляем экземпляр класса
        dateTime = new DateTime(context);
        // Запуск таймера
        tickTime(context);
    }

    // Установка часов
    private void setTime() {

    }

    // Таймер обновления
    private void tickTime(Context context) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // Обновление даты
                dateTime.newDate();
                // Обработка с высодом на экран
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txt_sumDay.setText(String.valueOf(dateTime.differenceDay()));
                        // Часы, Минуты, Секунды
                        txt_hour.setText(String.valueOf(dateTime.hour()));
                        txt_minute.setText(String.valueOf(dateTime.minute()));
                        txt_seconds.setText(String.valueOf(dateTime.second()));
                        // Месяцы, недели, дни
                        txt_month.setText(String.valueOf(dateTime.month()));
                        txt_week.setText(String.valueOf(dateTime.week()));
                        txt_day.setText(String.valueOf(dateTime.day()));
                    }
                });
            }
        }, 0, 1000);
    }
}
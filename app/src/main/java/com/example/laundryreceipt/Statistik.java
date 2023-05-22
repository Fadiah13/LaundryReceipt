package com.example.laundryreceipt;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Statistik extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistik);

        BarChart barChart = findViewById(R.id.barChart);
        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(1, 45 ));
        visitors.add(new BarEntry(2, 110 ));
        visitors.add(new BarEntry(3, 150 ));
        visitors.add(new BarEntry(4, 200 ));

        BarDataSet barDataSet = new BarDataSet(visitors, "visitors");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Bar Chart Example");
        barChart.animateY(2000);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setSelectedItemId(R.id.bottomStatistik);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.bottomHome:
                    startActivity(new Intent(Statistik.this, Utama.class));
                    break;
                case R.id.bottomStatistik:
                    break;
                case R.id.bottomPemesanan:
                    startActivity(new Intent(Statistik.this, Pemesanan.class));
                    break;
                case R.id.bottomSettings:
                    startActivity(new Intent(Statistik.this, Settings.class));
                    break;
            }
            return true;
        });
    }
}
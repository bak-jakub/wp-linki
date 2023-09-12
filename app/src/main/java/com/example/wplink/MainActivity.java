package com.example.wplink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    RecyclerView wynik;
    ArrayList<String> link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wynik = findViewById(R.id.wyniki);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        wynik.setLayoutManager(new LinearLayoutManager(this));


        executor.execute(() -> {

            try {
                link = pobierz_linki();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            handler.post(() -> {
                Log.i("ASD", "FOUND" + link.size() + "LINKS");
                wynik.setAdapter( new Adapter(this, link));
            });
        });

    }

    public ArrayList<String> pobierz_linki() throws IOException {
        ArrayList<String> link= new ArrayList<>();
        Document doc = Jsoup.connect("https://www.wp.pl/").get();
        Elements newsHeadlines = doc.select("[data-testid=\"native-link\"]");
        for (Element headline : newsHeadlines) {
            link.add( headline.text());
            link.add( headline.attr("href"));
        }
        return link;
    }
}
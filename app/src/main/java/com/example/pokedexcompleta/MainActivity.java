package com.example.pokedexcompleta;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.ValueIterator;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
 static    ArrayList<String> urlIma = new ArrayList<>();
    ArrayList<String> arrayList = new ArrayList<>();
    TextView nombre;
    ArrayList<Pokemon> pokemons = new ArrayList<>();
    ListView listView;
//Jsoup --> Json
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombre = findViewById(R.id.textNombre);

        listView = findViewById(R.id.listView);
        //---------------Pre-Ejecucion------------//
        new Thread(new Runnable() {
            @Override
            public void run()
            {
                //-----------Postejecucion---------------//
                try {
               Document resCompleto = Jsoup.connect("https://www.pokemon.com/es/pokedex/").get();
    arrayList = (ArrayList<String>) resCompleto.select("[href^=/es/pokedex/]").eachText();
                    //El elemento 0 no nos interesa
    arrayList.remove(0);
                    for (int i = 0; i < arrayList.size(); i++)
                    {
                        String numPkm = String.format("%03d",i + 1);
                        //Conformar lista de URlS
                        urlIma.add("https://assets.pokemon.com/assets/cms2/img/pokedex/full/" + numPkm + ".png");
                        //Conformar la lista de nombres
                        pokemons.add((new Pokemon(arrayList.get(i))));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //---------------Post Ejecucion_________________//
                runOnUiThread(new Runnable() {
                    @Override
                    public void run()
                    {
CustomAdapter adapter = new CustomAdapter(pokemons,MainActivity.this);
listView.setAdapter(adapter);
                    }
                });
            }
        }).start();

    }
}
//zip
package com.example.pokedexcompleta;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter
{

    ArrayList<Pokemon>pokemons;
    Context context;

    @Override
    public int getCount() {
        return pokemons.size();
    }

    @Override
    public Pokemon getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
   //Genera un view nuevo a partir del layout de de'item' generado
        View viewInflado = LayoutInflater.from(context).inflate(R.layout.item,null);
   TextView txtNombre =  viewInflado.findViewById(R.id.textNombre);

    ImageView imageView = viewInflado.findViewById(R.id.imageView);
    txtNombre.setText(pokemons.get(position).getNombre());
        Picasso.get().load(MainActivity.urlIma.get(position)).into(imageView);

    return viewInflado;
    }

    public CustomAdapter(ArrayList<Pokemon> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }
}

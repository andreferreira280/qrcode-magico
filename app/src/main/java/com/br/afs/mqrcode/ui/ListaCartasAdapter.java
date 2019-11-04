package com.br.afs.mqrcode.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.br.afs.mqrcode.R;
import com.br.afs.mqrcode.model.Carta;

import java.util.ArrayList;
import java.util.List;

 class ListaCartasAdapter extends BaseAdapter {
     private final List<Carta> cartas = new ArrayList<>();
     private final Context context;

     public ListaCartasAdapter(Context context) {
         this.context = context;
     }

     @Override
     public int getCount() {
         return cartas.size();
     }

     @Override
     public Carta getItem(int posicao) {
         return cartas.get(posicao);
     }

     @Override
     public long getItemId(int posicao) {
         return cartas.get(posicao).getId();
     }

     @Override
     public View getView(int posicao, View view, ViewGroup viewGroup) {
         View viewCriada = criaView(viewGroup);
         Carta cartaDevolvida = cartas.get(posicao);
         vinculaView(viewCriada, cartaDevolvida);
         return viewCriada;
     }

     private void vinculaView(View viewCriada, Carta cartaDevolvida) {
         TextView tvNome = viewCriada.findViewById(R.id.tvNome);
         tvNome.setText(cartaDevolvida.getNome());
//         TextView tvMana = viewCriada.findViewById(R.id.tvMana);
//         tvMana.setText(cartaDevolvida.getMana());
     }

     private View criaView(ViewGroup viewGroup) {
         return LayoutInflater
                 .from(context)
                 .inflate(R.layout.item_carta, viewGroup, false);
     }

     private void clear() {
         cartas.clear();
     }

     private void addAll(List<Carta> cartas) {
         this.cartas.addAll(cartas);
     }

     public void remove(Carta c) {
         cartas.remove(c);
         notifyDataSetChanged();
     }

     public void atualiza(List<Carta> cartas) {
         this.cartas.clear();
         this.cartas.addAll(cartas);
         notifyDataSetChanged();
     }

 }

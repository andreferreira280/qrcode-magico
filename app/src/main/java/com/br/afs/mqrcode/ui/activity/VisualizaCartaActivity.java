package com.br.afs.mqrcode.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.br.afs.mqrcode.R;
import com.br.afs.mqrcode.model.Carta;

public class VisualizaCartaActivity extends AppCompatActivity {
    TextView tvNome, tvMana, tvCusto, tvTipo, tvText, tvFlavor, tvPt, tvRegras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_carta);
        iniciaComponentes();
        carrega();
    }

    private void iniciaComponentes() {
        tvNome = findViewById(R.id.tvNome);
        tvMana = findViewById(R.id.tvMana);
        tvCusto = findViewById(R.id.tvCusto);
        tvTipo = findViewById(R.id.tvTipo);
        tvText = findViewById(R.id.tvText);
        tvFlavor = findViewById(R.id.tvFlavor);
        tvPt = findViewById(R.id.tvPt);
        tvRegras = findViewById(R.id.tvRegras);
    }

    private void carrega() {
        Intent dados = getIntent();
        if (dados.hasExtra("carta")) {
            setTitle("Informações da carta");
            Carta c = (Carta) dados.getSerializableExtra("carta");
            exibe(c);
        } else {
        }

    }

    private void exibe(Carta c) {
        tvNome.setText("Nome da carta: " + c.getNome().toString());
        tvMana.setText("Custo de mana: " + c.getMana().toString());
        tvCusto.setText(String.format("Custo total: %s", String.valueOf(c.getCusto())));
        tvTipo.setText("Tipo da carta: " + c.getTipo().toString());
        tvText.setText("Texto da carta: " + c.getText().toString());
        tvFlavor.setText("FlavorText da carta: " + c.getFlavor().toString());
        tvPt.setText(String.format("P/t: %s/%s", String.valueOf(c.getAtaque()), String.valueOf(c.getDefesa())));
        tvRegras.setText("Regras: " + c.getRegras().toString());
    }


}


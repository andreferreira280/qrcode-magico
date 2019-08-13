package com.br.afs.mqrcode.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.br.afs.mqrcode.R;

public class MainActivity extends AppCompatActivity {
    Button btLeitor, btCadastrar, btDecks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Bem vindo");
        inicializaBotoes();
        configuraBtLeitor();
configuraBtCadastrar();
        configuraBtDecks();
    }

    private void inicializaBotoes() {
        btLeitor = findViewById(R.id.btLeitor);
        btCadastrar = findViewById(R.id.btCadastrar);
        btDecks = findViewById(R.id.btDecks);
    }

    private void configuraBtLeitor() {
        btLeitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
startActivity(new Intent(MainActivity.this, LeitorActivity.class));
finish();
            }
        });
}

private void configuraBtCadastrar() {
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FormCartasActivity.class));
            }
        });
}

private void configuraBtDecks() {
        btDecks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DecksActivity.class));
            }
        });
}
}


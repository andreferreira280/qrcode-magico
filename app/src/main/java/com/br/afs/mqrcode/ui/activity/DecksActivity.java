package com.br.afs.mqrcode.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.br.afs.mqrcode.R;
import com.br.afs.mqrcode.model.Carta;
import com.br.afs.mqrcode.ui.ListaCartasView;

public class DecksActivity extends AppCompatActivity {
private ListaCartasView lcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decks);
        setTitle("Lista de cartas");
        lcv = new ListaCartasView(this);
        configuraLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        lcv.atualizaCartas();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater()
                .inflate(R.menu.activity_lista_cartas_menu, menu);
    }

    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.mnRemover) {
            lcv.confirmaRemocao(item);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraLista() {
        ListView lvLista = findViewById(R.id.lvLeitor);
        lcv.configuraAdapter(lvLista);
        configuraCliqueDeItem(lvLista);
        registerForContextMenu(lvLista);
    }

    private void configuraCliqueDeItem(ListView lvLista) {
        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Carta cartaSelecionada = (Carta) adapterView.getItemAtPosition(posicao);
                iniciaForumularioEmModoEditaCarta(cartaSelecionada);
            }
        });
    }

    private void iniciaForumularioEmModoEditaCarta(Carta cartaSelecionada) {
        Intent iniciaFormulario = new Intent(DecksActivity.this, FormCartasActivity.class);
        iniciaFormulario.putExtra("carta", cartaSelecionada);
        startActivity(iniciaFormulario);
                finish();
//        Log.i("idAluno", String.valueOf(alunoSelecionado.getId()));
    }

                    }

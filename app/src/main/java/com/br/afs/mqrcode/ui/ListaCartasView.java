package com.br.afs.mqrcode.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.br.afs.mqrcode.database.MQRCodeDatabase;
import com.br.afs.mqrcode.database.dao.CartaDao;
import com.br.afs.mqrcode.model.Carta;

import java.util.List;

public class ListaCartasView {
    private final ListaCartasAdapter adapter;
    private final CartaDao cd;
    private final Context context;
private Carta carta;

    public ListaCartasView(Context context) {
        this.context = context;
        this.adapter = new ListaCartasAdapter(this.context);
        cd = MQRCodeDatabase.getInstance(context)
                .getCartaDao();
    }

    public void confirmaRemocao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Remover carta?")
                .setMessage("Deseja realmente remover a carta?")
                .setPositiveButton("Confirmar", new DialogInterface
                        .OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                Carta cartaEscolhida = adapter.getItem(menuInfo.position);
                remove(cartaEscolhida, 1);
            }
        })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    public void atualizaCartas() {
        adapter.atualiza(cd.todas());
    }

    public void atualizaMao(List<Carta> mao) {
        adapter.atualiza(mao);
    }

    public void  confirmaRemocaoMao(final MenuItem item) {
        new AlertDialog
                .Builder(context)
                .setTitle("Remover carta?")
                .setMessage("Deseja realmente remover a carta?")
                .setPositiveButton("Confirmar", new DialogInterface
                        .OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Carta cartaEscolhida = adapter.getItem(menuInfo.position);
                        remove(cartaEscolhida, 0);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }


    public Carta consultarNome(String nome) {
        return cd.porNome(nome);
    }

    private void remove(Carta carta, int i) {
        if(i!=0) {
            cd.remove(carta);
        }
        adapter.remove(carta);
        this.carta = carta;
    }


    public void configuraAdapter(ListView lv) {
        lv.setAdapter(adapter);
    }

public Carta getCarta() {
        return carta;
}
}

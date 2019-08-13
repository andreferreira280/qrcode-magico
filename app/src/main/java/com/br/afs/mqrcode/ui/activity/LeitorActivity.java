package com.br.afs.mqrcode.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.br.afs.mqrcode.R;
import com.br.afs.mqrcode.database.MQRCodeDatabase;
import com.br.afs.mqrcode.database.dao.CartaDao;
import com.br.afs.mqrcode.model.Carta;
import com.br.afs.mqrcode.ui.ListaCartasView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;
import static com.br.afs.mqrcode.R.layout.activity_leitor;

public class LeitorActivity extends AppCompatActivity {
    private ListaCartasView lcv;
    Button btScan;
    final Activity activity = this;
private Carta carta;
    private CartaDao cd;
private List<Carta> mao = new ArrayList<Carta>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_leitor);
        setTitle("Leitor");
        lcv = new ListaCartasView(this);
        MQRCodeDatabase database = MQRCodeDatabase.getInstance(this);
        cd = database.getCartaDao();
        iniciaComponentes();
        configuraLista();
        configuraBotao();
    }

    private void iniciaComponentes() {
        btScan = findViewById(R.id.btScan);
    }

        @Override
    protected void onResume() {
        super.onResume();
        lcv.atualizaMao(this.mao);
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
        ListView lvLeitor = findViewById(R.id.lvLeitor);
        lcv.configuraAdapter(lvLeitor);
        configuraCliqueDeItem(lvLeitor);
        registerForContextMenu(lvLeitor);
    }

    private void configuraCliqueDeItem(ListView lvLeitor) {
        lvLeitor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Carta cartaSelecionada = (Carta) adapterView.getItemAtPosition(posicao);
                iniciaVisualizarCarta(cartaSelecionada);
            }
        });
    }

    private void iniciaVisualizarCarta(Carta cartaSelecionada) {
        Intent iniciaVisualizaCaoDeCarta = new Intent(LeitorActivity.this, VisualizaCartaActivity.class);
        iniciaVisualizaCaoDeCarta.putExtra("carta", cartaSelecionada);
        startActivity(iniciaVisualizaCaoDeCarta);
    }

    private void configuraBotao() {
        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentIntegrator ii = new IntentIntegrator(activity);
                ii.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                ii.setPrompt("scan");
                ii.setCameraId(0);
                ii.initiateScan();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult ir = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (ir != null)
        {
            if (ir.getContents() != null)
            {
                alert(ir.getContents().toString());
                carta = lcv.consultarNome(ir.getContents().toString());
                confereSeResultadoEncontradoCorrespondeAUmaCarta(carta);
            }
            else
            {
                alert("Scan cancelado");
            }
        }
        else
        {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void confereSeResultadoEncontradoCorrespondeAUmaCarta(Carta c) {
        if(c != null)
        {
            adicionaCartaAMao(c);
            alert("adicionando a carta " + c.getNome());
        }
        else
        {
            alert("Carta não foi encontrada");
        }
    }

    private void adicionaCartaAMao(Carta c) {
        alert("O tamanho da mão agora é: " + mao.size() + " e a carta a ser adicionada é: " + c.getNome());
        this.mao.add(c);
        alert("O tamanho da mão agora é: " + mao.size() + " e a carta adicionada foi: " + mao.get(0).getNome());
        lcv.atualizaMao(this.mao);
    }

    private void alert(String msg) {
        makeText(getApplicationContext(), msg, LENGTH_LONG).show();
    }


}


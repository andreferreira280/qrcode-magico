package com.br.afs.mqrcode.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.br.afs.mqrcode.R;
import com.br.afs.mqrcode.database.MQRCodeDatabase;
import com.br.afs.mqrcode.database.dao.CartaDao;
import com.br.afs.mqrcode.model.Carta;


public class FormCartasActivity extends AppCompatActivity {
private static final String TITLE_APP_BAR_CADASTRA = "Adicionar nova carta";
private static final String TITLE_APP_BAR_EDITAR = "Editar carta";
private CartaDao cd;
private Carta c;
private EditText edNome, edMana, edCusto, edTipo, edText, edFlavor, edAtaque, edDefesa, edRegras;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_cartas);
        setTitle(TITLE_APP_BAR_EDITAR);
        MQRCodeDatabase database = MQRCodeDatabase.getInstance(this);
        cd = database.getCartaDao();
        inicializaCampos();
        carrega();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_carta_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.mnSalvar) {
            finalizaFormulario();
        }
        return super.onOptionsItemSelected(item);
    }

    private void inicializaCampos() {
        edNome = findViewById(R.id.edNome);
        edMana = findViewById(R.id.edMana);
        edCusto = findViewById(R.id.edCusto);
        edTipo = findViewById(R.id.edTipo);
        edText = findViewById(R.id.edText);
        edFlavor = findViewById(R.id.edFlavor);
        edAtaque = findViewById(R.id.edAtaque);
        edDefesa = findViewById(R.id.edDefesa);
        edRegras = findViewById(R.id.edRegras);
    }

    private void carrega() {
        Intent dados = getIntent();
        if (dados.hasExtra("carta")) {
            setTitle(TITLE_APP_BAR_EDITAR);
            c = (Carta) dados.getSerializableExtra("carta");
            preencheCampos();
        }
        else {
            setTitle(TITLE_APP_BAR_CADASTRA);
            c = new Carta();
        }
    }

    private void preencheCampos() {
            edNome.setText(c.getNome());
            edMana.setText(c.getMana());
            if(c.getCusto() != 0) {
                edCusto.setText(String.valueOf(c.getCusto()));
            }
            edTipo.setText(c.getTipo());
            edText.setText(c.getText());
            edFlavor.setText(c.getFlavor());
            if (c.getAtaque() != 0) {
                edAtaque.setText(String.valueOf(c.getAtaque()));
            }
            if (c.getDefesa() != 0) {
                edDefesa.setText(String.valueOf(c.getDefesa()));
            }
            edRegras.setText(c.getRegras());
        }

    private void finalizaFormulario() {
        preencheCarta();
        if (c.possuiIdValido()) {
            cd.edita(c);
        } else {
            cd.salva(c);
        }
        startActivity(new Intent(FormCartasActivity.this, DecksActivity.class));
        finish();
    }

    private void preencheCarta() {
        String nome = edNome.getText().toString();
        String mana = edMana.getText().toString();
        String custo = edCusto.getText().toString();
        String tipo = edTipo.getText().toString();
        String text = edText.getText().toString();
        String flavor = edFlavor.getText().toString();
        String ataque = edAtaque.getText().toString();
        String defesa = edDefesa.getText().toString();
        String regras = edRegras.getText().toString();
        c.setNome(nome);
        c.setMana(mana);
        if (!custo.isEmpty()) {
            c.setCusto(Integer.parseInt(custo));
        }
        c.setTipo(tipo);
        c.setText(text);
        c.setFlavor(flavor);
        if (!ataque.isEmpty()) {
            c.setAtaque(Integer.parseInt(ataque));
        }
        if (!defesa.isEmpty()) {
            c.setDefesa(Integer.parseInt(defesa));
        }
        c.setRegras(regras);
    }

}


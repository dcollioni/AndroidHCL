package com.collioni.douglas.cores;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;


public class AvaliarActivity extends ActionBarActivity {

    final static String CHAVE_AVALIACAO = "AVALIACAO_SELECIONADA";

    Spinner spAvaliacoes;
    Button btEnviar;
    LinearLayout layoutAvaliar;

    private void carregarElementos() {
        spAvaliacoes = (Spinner) findViewById(R.id.sp_avaliacoes);
        btEnviar = (Button) findViewById(R.id.bt_enviar);
        layoutAvaliar = (LinearLayout) findViewById(R.id.layout_avaliar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avaliar);

        carregarElementos();
        configurarSpAvaliacoes();
        configurarBtEnviar();

        Intent i = getIntent();
        String cor = i.getStringExtra(SelecionarActivity.CHAVE_COR);

        if (cor != null) {
            alterarCor(cor);
        }
    }

    private void configurarBtEnviar() {
        btEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String avaliacao = spAvaliacoes.getSelectedItem().toString();

                Intent i = new Intent();
                i.putExtra(CHAVE_AVALIACAO, avaliacao);

                setResult(1, i);
                finish();
            }
        });
    }

    private void alterarCor(String cor) {
        if (cor.equals(getString(R.string.azul))) {

            layoutAvaliar.setBackgroundResource(R.color.azul);

        } else if (cor.equals(getString(R.string.amarelo))) {

            layoutAvaliar.setBackgroundResource(R.color.amarelo);

        } else if (cor.equals(getString(R.string.vermelho))) {

            layoutAvaliar.setBackgroundResource(R.color.vermelho);
        }
    }

    private void configurarSpAvaliacoes() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                                                getBaseContext(),
                                                R.array.avaliacoes,
                                                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spAvaliacoes.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_avaliar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

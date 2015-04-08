package com.collioni.douglas.cores;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


public class SelecionarActivity extends ActionBarActivity {

    final static String CHAVE_COR = "COR_SELECIONADA";

    Spinner spCores;
    Button btAvancar;

    private void carregarElementos() {
        spCores = (Spinner) findViewById(R.id.sp_cores);
        btAvancar = (Button) findViewById(R.id.bt_avancar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionar);

        carregarElementos();
        configurarSpCores();
        configurarBtAvancar();

        Log.d("CICLO_VIDA", "onCreate SelecionarActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CICLO_VIDA", "onResume SelecionarActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CICLO_VIDA", "onPause SelecionarActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CICLO_VIDA", "onStop SelecionarActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CICLO_VIDA", "onDestroy SelecionarActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("CICLO_VIDA", "onRestart SelecionarActivity");
    }

    private void configurarBtAvancar() {
        btAvancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SelecionarActivity.this, AvaliarActivity.class);

                String cor = spCores.getSelectedItem().toString();

                i.putExtra(CHAVE_COR, cor);

                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String avaliacao = data.getStringExtra(AvaliarActivity.CHAVE_AVALIACAO);

        if (avaliacao != null) {
            Toast.makeText(getBaseContext(), avaliacao, Toast.LENGTH_SHORT).show();
        }
    }

    private void configurarSpCores() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                                                getBaseContext(),
                                                R.array.cores,
                                                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCores.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selecionar, menu);
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

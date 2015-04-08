package com.collioni.douglas.cores;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    Button btEntrar;

    private void carregarElementos() {
        btEntrar = (Button) findViewById(R.id.bt_entrar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarElementos();
        configurarBtEntrar();

        Log.d("CICLO_VIDA", "onCreate MainActivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("CICLO_VIDA", "onResume MainActivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("CICLO_VIDA", "onPause MainActivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("CICLO_VIDA", "onStop MainActivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("CICLO_VIDA", "onDestroy MainActivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("CICLO_VIDA", "onRestart MainActivity");
    }

    private void configurarBtEntrar() {
        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SelecionarActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

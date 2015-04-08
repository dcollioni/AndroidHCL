package com.collioni.douglas.estados;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Collections;
import java.util.Comparator;


public class MainActivity extends ActionBarActivity {

    ListView lvEstados;

    private void carregarElementos() {
        lvEstados = (ListView) findViewById(R.id.lv_estados);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarElementos();
        configurarLvEstadosMultipleChoice3();
    }

    // seleção múltipla com vezinhos
    private void configurarLvEstadosMultipleChoice3() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getBaseContext(),
                R.array.estados,
                android.R.layout.simple_list_item_checked);

        lvEstados.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvEstados.setAdapter(adapter);
    }

    // seleção múltipla com checkbox
    private void configurarLvEstadosMultipleChoice2() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getBaseContext(),
                R.array.estados,
                android.R.layout.simple_list_item_multiple_choice);

        lvEstados.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvEstados.setAdapter(adapter);
    }

    // seleção múltipla com itens destacados em azul
    private void configurarLvEstadosMultipleChoice1() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getBaseContext(),
                R.array.estados,
                android.R.layout.simple_list_item_activated_1);

        lvEstados.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lvEstados.setAdapter(adapter);
    }

    // seleção simples com item destacado em azul
    private void configurarLvEstadosSingleChoice2() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getBaseContext(),
                R.array.estados,
                android.R.layout.simple_list_item_activated_1);

        lvEstados.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvEstados.setAdapter(adapter);
    }

    // seleção simples com radio buttons
    private void configurarLvEstadosSingleChoice1() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                                                getBaseContext(),
                                                R.array.estados,
                                                android.R.layout.simple_list_item_single_choice);

        lvEstados.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvEstados.setAdapter(adapter);
    }

    private void configurarLvEstadosNoChoice() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                                                getBaseContext(),
                                                R.array.estados,
                                                android.R.layout.simple_list_item_1);

        adapter.sort(new Comparator<CharSequence>() {
            @Override
            public int compare(CharSequence lhs, CharSequence rhs) {
                return lhs.toString().compareTo(rhs.toString());
            }
        });

        lvEstados.setChoiceMode(ListView.CHOICE_MODE_NONE);
        lvEstados.setAdapter(adapter);
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

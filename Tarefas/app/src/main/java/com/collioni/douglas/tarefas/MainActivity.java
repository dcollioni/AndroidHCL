package com.collioni.douglas.tarefas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    EditText etDescricao;
    Button btAdicionar, btRemover;
    ListView lvTarefas;

    ArrayAdapter<Tarefa> adapter;
    ArrayList<Tarefa> tarefas;

    private void carregarElementos() {
        etDescricao = (EditText) findViewById(R.id.et_descricao);
        btAdicionar = (Button) findViewById(R.id.bt_adicionar);
        btRemover = (Button) findViewById(R.id.bt_remover);
        lvTarefas = (ListView) findViewById(R.id.lv_tarefas);
    }

    private void configurarLvTarefas() {
        tarefas = new ArrayList<>();

        adapter = new ArrayAdapter<Tarefa>(
                            getBaseContext(),
                            android.R.layout.simple_list_item_activated_1,
                            tarefas);

        lvTarefas.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lvTarefas.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarElementos();
        configurarLvTarefas();
        configurarBtAdicionar();
        configurarBtRemover();
        configurarEventoLvTarefas();
    }

    private void configurarBtRemover() {
        btRemover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = lvTarefas.getCheckedItemPosition();

                tarefas.remove(position);

                adapter.notifyDataSetChanged();
                lvTarefas.setItemChecked(-1, true);
                btRemover.setEnabled(false);
            }
        });
    }

    private void configurarEventoLvTarefas() {
        lvTarefas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                btRemover.setEnabled(true);
            }
        });
    }

    private void configurarBtAdicionar() {
        btAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descricao = etDescricao.getText().toString();

                if (descricao.isEmpty()) {
                    return;
                }

                Tarefa t = new Tarefa(descricao);

                tarefas.add(t);

                adapter.notifyDataSetChanged();

                etDescricao.setText(null);
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

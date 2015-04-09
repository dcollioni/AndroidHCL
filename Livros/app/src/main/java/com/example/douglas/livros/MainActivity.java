package com.example.douglas.livros;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.douglas.livros.db.Db4oHelper;
import com.example.douglas.livros.db.LivroDao;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends ActionBarActivity {

    final static String CHAVE_LIVRO_ID = "livro_id";

    Db4oHelper db4oHelper;
    LivroDao livroDao;

    EditText etFiltro;
    ListView lvLivros;

    LivroAdapter adapter;
    ArrayList<Livro> livros;

    private void carregarElementos() {
        etFiltro = (EditText) findViewById(R.id.et_filtro);
        lvLivros = (ListView) findViewById(R.id.lv_livros);
    }

    private void configurarDb4o() {
        String dataDir = getDir("data", 0) + "/";
        db4oHelper = new Db4oHelper(dataDir);
        livroDao = new LivroDao(db4oHelper);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarElementos();
        configurarDb4o();
        configurarLvLivros();
        configurarEventoLvLivros();
        configurarLvLivrosMenu();
        configurarEventoEtFiltro();
    }

    private void configurarEventoEtFiltro() {
        etFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO: carregar livros
            }
        });
    }

    private void configurarLvLivrosMenu() {
        lvLivros.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            ArrayList<Livro> livrosSelecionados;

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                /* TODO
                *    1. pegar livro marcado/desmarcado
                *    2. adicionar ou remover do array de selecionados
                *    3. atualizar o título do menu com o total selecionado
                 */

                adapter.setSelectedItems(livrosSelecionados);
                adapter.notifyDataSetChanged();
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // TODO: inflar o menu_lv_livros

                livrosSelecionados = new ArrayList<>();
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                int id = item.getItemId();

                switch (id) {

                    case R.id.menu_excluir:
                        // TODO: excluir todos os livros e finalizar o action mode

                        return true;

                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                livrosSelecionados.clear();
            }
        });
    }

    private void configurarLvLivros() {
        livros = new ArrayList<>();
        adapter = new LivroAdapter(MainActivity.this, livros);
        lvLivros.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvLivros.setAdapter(adapter);
    }

    private void configurarEventoLvLivros() {
        lvLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: pegar id do livro selecionado e abrir detalhes
            }
        });
    }

    private void carregarLivros() {
        // TODO: limpar array de livros e carregar novamente do banco
    }

    private void excluirLivros(ArrayList<Livro> livrosSelecionados) {
        // TODO: excluir todos os itens do array recebido e recarregar lista
    }

    private void excluirTodosLivros() {
        // TODO: excluir todos os itens do array livros e recarregar lista
    }

    @Override
    protected void onResume() {
        super.onResume();
        db4oHelper.abrirConexao();
        carregarLivros();
    }

    @Override
    protected void onPause() {
        super.onPause();
        db4oHelper.fecharConexao();
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

        switch (id) {

            // TODO: tratar o item clicado pelo id

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
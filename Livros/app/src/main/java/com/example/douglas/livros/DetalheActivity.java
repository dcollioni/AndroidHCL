package com.example.douglas.livros;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.douglas.livros.db.Db4oHelper;
import com.example.douglas.livros.db.LivroDao;

import java.util.ArrayList;


public class DetalheActivity extends ActionBarActivity {

    Db4oHelper db4oHelper;
    LivroDao livroDao;

    EditText etTitulo, etAutor, etIsbn;
    Spinner spGenero;
    Button btSalvar, btCancelar;

    ArrayAdapter<CharSequence> adapter;

    long livroId;

    private void carregarElementos() {
        etTitulo = (EditText) findViewById(R.id.et_titulo);
        etAutor = (EditText) findViewById(R.id.et_autor);
        etIsbn = (EditText) findViewById(R.id.et_isbn);
        spGenero = (Spinner) findViewById(R.id.sp_genero);
        btSalvar = (Button) findViewById(R.id.bt_salvar);
        btCancelar = (Button) findViewById(R.id.bt_cancelar);
    }

    private void configurarDb4o() {
        String dataDir = getDir("data", 0) + "/";
        db4oHelper = new Db4oHelper(dataDir);
        livroDao = new LivroDao(db4oHelper);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        carregarElementos();
        configurarDb4o();
        configurarSpGenero();
        configurarBtSalvar();
        configurarBtCancelar();

        // TODO: pegar o id do livro recebido (se houver)
    }

    private void configurarBtCancelar() {
        btCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        db4oHelper.abrirConexao();

        // TODO: carregar informações do livro (se for edição)
        // TODO: alterar título para Novo Título (se for inclusão)
    }

    @Override
    protected void onPause() {
        super.onPause();
        db4oHelper.fecharConexao();
    }

    private void carregarInformacoesLivro() {
        // TODO: pegar livro pelo id e carregar campos
    }

    private void configurarBtSalvar() {
        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titulo = etTitulo.getText().toString();
                String autor = etAutor.getText().toString();
                String genero = spGenero.getSelectedItem().toString();
                String isbn = etIsbn.getText().toString();

                if (titulo.isEmpty() || autor.isEmpty()) {
                    trace(getString(R.string.titulo_autor_obrigatorios));
                    return;
                }

                Livro l = new Livro(titulo, autor, genero, isbn);

                // TODO: inserir ou atualizar livro e finalizar activity
            }
        });
    }

    private void trace(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void configurarSpGenero() {
        adapter = ArrayAdapter.createFromResource(
                                                getBaseContext(),
                                                R.array.generos,
                                                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spGenero.setAdapter(adapter);
    }
}

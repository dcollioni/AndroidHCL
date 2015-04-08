package com.collioni.douglas.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private final String EMAIL_CORRETO = "dcollioni@gmail.com";
    private final String SENHA_CORRETA = "123456";

    // declara os elementos da tela (xml)
    EditText etEmail, etSenha;
    Button btEntrar, btLimpar;

    // método que carrega os elementos pelos ids
    private void carregarElementos() {
        etEmail = (EditText) findViewById(R.id.et_email);
        etSenha = (EditText) findViewById(R.id.et_senha);
        btEntrar = (Button) findViewById(R.id.bt_entrar);
        btLimpar = (Button) findViewById(R.id.bt_limpar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarElementos();

        btEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // pega os valores dos campos
                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                // testa se os campos estão vazios
                if (email.isEmpty() || senha.isEmpty()) {

                    // mostra msg de alerta
                    trace(getString(R.string.preencha_info));

                    // se o campo email está vazio
                    if (email.isEmpty()) {
                        // coloca o foco no campo email
                        etEmail.requestFocus();
                        etEmail.setBackgroundResource(R.color.vermelho);
                    } else {
                        // coloca o foco no campo senha
                        etSenha.requestFocus();
                        etSenha.setBackgroundResource(R.color.vermelho);
                    }

                    return;
                }

                // se o email digitado é igual ao email correto
                // e a senha digitada é igual à senha correta
                if (email.equals(EMAIL_CORRETO) && senha.equals(SENHA_CORRETA)) {

                    trace(getString(R.string.seja_bem_vindo));

                } else {

                    trace(getString(R.string.info_incorretas));

                }
            }
        });

        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = etEmail.getText().toString();
                String senha = etSenha.getText().toString();

                // se os dois campos estão vazios, encerra o método
                if (email.isEmpty() && senha.isEmpty()) {
                    return;
                }

                AlertDialog alert = new AlertDialog
                        .Builder(MainActivity.this)
                        .setTitle(R.string.confirmacao)
                        .setMessage(R.string.deseja_limpar_campos)
                        .setNegativeButton(R.string.nao,
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setPositiveButton(R.string.sim,
                                new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                etEmail.setText(null);
                                etSenha.setText(null);
                                etEmail.requestFocus();
                            }
                        })
                        .create();

                alert.show();
            }
        });

        etEmail.addTextChangedListener(new TW());
        etSenha.addTextChangedListener(new TW());
    }

    private class TW implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            String email = etEmail.getText().toString();
            String senha = etSenha.getText().toString();

            etEmail.setBackgroundResource(android.R.color.white);
            etSenha.setBackgroundResource(android.R.color.white);

            if (email.isEmpty() && senha.isEmpty()) {
                btLimpar.setEnabled(false);
            } else {
                btLimpar.setEnabled(true);
            }
        }
    }

    // mostra uma mensagem em um Toast
    private void trace(CharSequence msg) {
        Toast.makeText(
                getBaseContext(),
                msg,
                Toast.LENGTH_SHORT
        ).show();
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

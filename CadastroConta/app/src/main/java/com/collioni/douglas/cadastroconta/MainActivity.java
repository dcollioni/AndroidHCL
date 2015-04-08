package com.collioni.douglas.cadastroconta;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    RadioGroup rgTipoPessoa;
    EditText etCpfCnpj;
    CheckBox cbTalaoCheque, cbCartaoCredito, cbSeguroCartao;
    Button btCadastrar;

    private void carregarElementos() {
        rgTipoPessoa = (RadioGroup) findViewById(R.id.rg_tipo_pessoa);
        etCpfCnpj = (EditText) findViewById(R.id.et_cpf_cnpj);
        cbTalaoCheque = (CheckBox) findViewById(R.id.cb_talao_cheques);
        cbCartaoCredito = (CheckBox) findViewById(R.id.cb_cartao_credito);
        cbSeguroCartao = (CheckBox) findViewById(R.id.cb_seguro_cartao);
        btCadastrar = (Button) findViewById(R.id.bt_cadastrar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarElementos();
        configurarRgTipoPessoa();
        configurarCbCartaoCredito();
        configurarBtCadastrar();
    }

    private void configurarBtCadastrar() {
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rbId = rgTipoPessoa.getCheckedRadioButtonId();

                String tipoPessoa = (rbId == R.id.rb_pf) ?
                                        getString(R.string.pf) :
                                        getString(R.string.pj);

                String labelCpfCnpj = etCpfCnpj.getHint().toString();
                String cpfCnpj = etCpfCnpj.getText().toString();

                String talaoCheques = cbTalaoCheque.isChecked() ?
                                        getString(R.string.sim) :
                                        getString(R.string.nao);

                String cartaoCredito = cbCartaoCredito.isChecked() ?
                                        getString(R.string.sim) :
                                        getString(R.string.nao);

                String seguroCartao = cbSeguroCartao.isChecked() ?
                                        getString(R.string.sim) :
                                        getString(R.string.nao);

                String msg = getString(R.string.tipo_pessoa) + ": " + tipoPessoa;
                msg += "\n" + labelCpfCnpj + ": " + cpfCnpj;
                msg += "\n" + getString(R.string.talao_cheques) + ": " + talaoCheques;
                msg += "\n" + getString(R.string.cartao_credito) + ": " + cartaoCredito;
                msg += "\n" + getString(R.string.seguro_cartao) + ": " + seguroCartao;

                Toast.makeText(
                        getBaseContext(),
                        msg,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configurarCbCartaoCredito() {
        cbCartaoCredito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                cbSeguroCartao.setEnabled(isChecked);
                cbSeguroCartao.setChecked(false);
            }
        });
    }

    private void configurarRgTipoPessoa() {
        rgTipoPessoa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String hint;

                switch (checkedId) {
                    case R.id.rb_pf:
                        hint = getString(R.string.cpf);
                        break;

                    case R.id.rb_pj:
                        hint = getString(R.string.cnpj);
                        break;

                    default:
                        hint = "";
                }

                etCpfCnpj.setHint(hint);
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

package com.collioni.douglas.calculadoraluz;

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
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    EditText etKwh, etPercIcms;
    RadioGroup rgTipoConta;
    CheckBox cbIncideIcms;
    Button btCalcular;
    TextView tvResultado;

    private void carregarElementos() {
        etKwh = (EditText) findViewById(R.id.et_kwh);
        etPercIcms = (EditText) findViewById(R.id.et_perc_icms);
        rgTipoConta = (RadioGroup) findViewById(R.id.rg_tipo_conta);
        cbIncideIcms = (CheckBox) findViewById(R.id.cb_incide_icms);
        btCalcular = (Button) findViewById(R.id.bt_calcular);
        tvResultado = (TextView) findViewById(R.id.tv_resultado);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarElementos();
        configurarCbIncideIcms();
        configurarBtCalcular();
    }

    private void configurarCbIncideIcms() {
        cbIncideIcms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                etPercIcms.setEnabled(isChecked);
                etPercIcms.setText(null);
            }
        });
    }

    private void configurarBtCalcular() {
        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kwhStr = etKwh.getText().toString();

                if (kwhStr.isEmpty()) {
                    trace(R.string.preencha_kwh);
                    return;
                }

                double kwh = Double.parseDouble(kwhStr);
                double tarifa = getTarifa();
                double percIcms = getPercIcms();

                double resultado = kwh * tarifa;
                resultado += resultado * (percIcms / 100);

                String resultadoFormatado = formatResultado(resultado);
                tvResultado.setText(resultadoFormatado);
            }
        });
    }

    private String formatResultado(double resultado) {
        NumberFormat numberFormat = NumberFormat.getNumberInstance();
        numberFormat.setMaximumFractionDigits(2);
        numberFormat.setMinimumFractionDigits(2);

        Currency currency = Currency.getInstance("BRL");

        return currency.getSymbol() + " " + numberFormat.format(resultado);
    }

    private double getTarifa() {
        int rbId = rgTipoConta.getCheckedRadioButtonId();
        double tarifa;

        switch (rbId) {
            case R.id.rb_conta_residencial:
                tarifa = .25;
                break;

            case R.id.rb_conta_comercial:
                tarifa = .29;
                break;

            case R.id.rb_conta_rural:
                tarifa = .18;
                break;

            default:
                tarifa = 0;
        }

        return tarifa;
    }

    private double getPercIcms() {
        String percIcmsStr = etPercIcms.getText().toString();
        double percIcms = 0;

        if (!percIcmsStr.isEmpty()) {
            percIcms = Double.parseDouble(percIcmsStr);
        }

        return percIcms;
    }

    private void trace(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    private void trace(int msgId) {
        Toast.makeText(getBaseContext(), getString(msgId), Toast.LENGTH_SHORT).show();
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

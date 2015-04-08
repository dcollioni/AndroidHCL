package com.collioni.douglas.carros;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    Spinner spPais, spMontadora, spCarro;
    TextView tvCarroNome, tvCarroCv;
    ImageView ivCarroFoto;

    private void carregarElementos() {
        spPais = (Spinner) findViewById(R.id.sp_pais);
        spMontadora = (Spinner) findViewById(R.id.sp_montadora);
        spCarro = (Spinner) findViewById(R.id.sp_carro);
        tvCarroNome = (TextView) findViewById(R.id.tv_carro_nome);
        tvCarroCv = (TextView) findViewById(R.id.tv_carro_cv);
        ivCarroFoto = (ImageView) findViewById(R.id.iv_carro_foto);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carregarElementos();

        configurarSpPais();
        configurarSpMontadora();
        configurarSpCarro();

        configurarEventoSpPais();
        configurarEventoSpMontadora();
        configurarEventoSpCarro();
    }

    private void configurarEventoSpCarro() {
        spCarro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Carro c = (Carro) parent.getItemAtPosition(position);

                tvCarroNome.setText(c.getNome());
                tvCarroCv.setText(Integer.toString(c.getCv()));
                ivCarroFoto.setImageResource(c.getFotoId());

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void configurarEventoSpMontadora() {
        spMontadora.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CharSequence montadora = (CharSequence) parent.getItemAtPosition(position);

                ArrayList<Carro> carros = new ArrayList<>();

                if (montadora.equals(getString(R.string.bmw))) {

                    carros.add(new Carro("X5", 300, R.drawable.bmw_x5));
                    carros.add(new Carro("Roadster", 350, R.drawable.bmw_roadster));

                } else if (montadora.equals(getString(R.string.volkswagen))) {

                    carros.add(new Carro("Fusca", 50, R.drawable.vw_fusca));
                    carros.add(new Carro("Passat", 79, R.drawable.vw_passat));

                } else if (montadora.equals(getString(R.string.gm))) {

                    carros.add(new Carro("Camaro", 290, R.drawable.gm_camaro));
                    carros.add(new Carro("Agile", 90, R.drawable.gm_agile));

                } else if (montadora.equals(getString(R.string.ford))) {

                    carros.add(new Carro("Fusion", 190, R.drawable.ford_fusion));
                    carros.add(new Carro("Focus", 120, R.drawable.ford_focus));

                } else if (montadora.equals(getString(R.string.honda))) {

                    carros.add(new Carro("Civic", 135, R.drawable.honda_civic));
                    carros.add(new Carro("Fit", 80, R.drawable.honda_fit));

                } else if (montadora.equals(getString(R.string.nissan))) {

                    carros.add(new Carro("March", 85, R.drawable.nissan_march));
                    carros.add(new Carro("Versa", 100, R.drawable.nissan_versa));

                } else if (montadora.equals(getString(R.string.ferrari))) {

                    carros.add(new Carro("F350", 650, R.drawable.ferrari_f350));
                    carros.add(new Carro("California", 580, R.drawable.ferrari_california));

                } else if (montadora.equals(getString(R.string.fiat))) {

                    carros.add(new Carro("147", 47, R.drawable.fiat_147));
                    carros.add(new Carro("Prêmio", 60, R.drawable.fiat_premio));

                }

                ArrayAdapter<Carro> adapter = (ArrayAdapter<Carro>)
                                               spCarro.getAdapter();

                adapter.clear();
                adapter.addAll(carros);

                spCarro.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void configurarSpCarro() {
        ArrayAdapter<Carro> adapter = new ArrayAdapter<Carro>(
                                            getBaseContext(),
                                            android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spCarro.setAdapter(adapter);
    }

    private void configurarEventoSpPais() {
        spPais.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CharSequence pais = (CharSequence) parent.getItemAtPosition(position);

                ArrayList<CharSequence> montadoras = new ArrayList<>();

                if (pais.equals(getString(R.string.alemanha))) {
                    montadoras.add(getString(R.string.bmw));
                    montadoras.add(getString(R.string.volkswagen));

                } else if (pais.equals(getString(R.string.italia))) {
                    montadoras.add(getString(R.string.ferrari));
                    montadoras.add(getString(R.string.fiat));

                } else if (pais.equals(getString(R.string.japao))) {
                    montadoras.add(getString(R.string.honda));
                    montadoras.add(getString(R.string.nissan));

                } else if (pais.equals(getString(R.string.usa))) {
                    montadoras.add(getString(R.string.ford));
                    montadoras.add(getString(R.string.gm));

                }

                ArrayAdapter<CharSequence> adapter = (ArrayAdapter<CharSequence>)
                                                      spMontadora.getAdapter();

                adapter.clear();
                adapter.addAll(montadoras);

                spMontadora.setAdapter(adapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void configurarSpMontadora() {

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(
                                                getBaseContext(),
                                                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spMontadora.setAdapter(adapter);
    }

    private void configurarSpPais() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                                                getBaseContext(),
                                                R.array.paises,
                                                android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spPais.setAdapter(adapter);
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

package br.com.caprichoatoa.capricho2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Connection;

import br.com.caprichoatoa.bancodados.Cliente;
import br.com.caprichoatoa.bancodados.ConexaoServidor;
import br.com.caprichoatoa.utilidades.FormataTexto;


public class CadastroClientesActivity extends AppCompatActivity {

    Context contextoCadastro;
    ConexaoServidor cs;
    Cliente cliente;
    EditText edtCPF;
    Spinner spnGenero;

    static final int FEMININO = 1;
    static final int MASCULINO = 2;
    static final int INDEFINIDO = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_clientes);

        contextoCadastro = this;
        cs = new ConexaoServidor(contextoCadastro);

        cliente = new Cliente(0, "", 0);

        edtCPF = findViewById(R.id.edtCPF);
        edtCPF.addTextChangedListener(FormataTexto.Formata(edtCPF, FormataTexto.FORMAT_CPF));

        spnGenero = findViewById(R.id.spnGenero);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.generos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGenero.setAdapter(adapter);

        Button btnVerificar = findViewById(R.id.btnVerificar);
        btnVerificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long documento = Long.parseLong(edtCPF.getText().toString().replace(".", "").replace("-", "").replace("/", ""));
                cliente.setDocumento(documento);
                RecuperaDadosCliente tarefa = new RecuperaDadosCliente();
                tarefa.execute();

            }
        });

    }

    private class RecuperaDadosCliente extends AsyncTask<String, Void, Cliente> {

        Cliente mCliente = new Cliente(0, "", 0);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mCliente = cliente;

        }

        @Override
        protected Cliente doInBackground(String... params) {

            try {

                Connection DbConn = cs.open();
                mCliente.BuscaCadastroCliente(DbConn);

                cs.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return mCliente;
        }

        @Override
        protected void onPostExecute(Cliente mCliente) {
            cliente = mCliente;

            EditText edtNome = findViewById(R.id.edtNome);
            Spinner spnGenero = findViewById(R.id.spnGenero);
            EditText edtFone1 = findViewById(R.id.edtFone1);
            EditText edtFone2 = findViewById(R.id.edtFone2);
            EditText edtEmail = findViewById(R.id.edtEmail);
            EditText edtEndereco = findViewById(R.id.edtEndereco);
            EditText edtNumero = findViewById(R.id.edtNumero);
            EditText edtComplemento = findViewById(R.id.edtComplemento);
            EditText edtCidade = findViewById(R.id.edtCidade);
            EditText edtUF = findViewById(R.id.edtUF);
            EditText edtBairro = findViewById(R.id.edtBairro);
            EditText edtFacebook = findViewById(R.id.edtFacebook);
            EditText edtInstagram = findViewById(R.id.edtInstagram);
            EditText edtPinterest = findViewById(R.id.edtPinTerest);
            EditText edtTamCamiseta = findViewById(R.id.edtTamCamiseta);
            EditText edtTamCalca = findViewById(R.id.edtTamCalca);
            EditText edtTamCalcado = findViewById(R.id.edtTamCalcado);

            edtNome.setText(cliente.getNome());

            int indice;
            switch (cliente.getSexo().toUpperCase()) {
                case "FEMININO":
                default:
                    indice = FEMININO;
                    break;
                case "MASCULINO":
                    indice = MASCULINO;
                    break;
                case "INDEFINIDO":
                    indice = INDEFINIDO;
                    break;
            }

            spnGenero.setSelection(indice);
            edtFone1.setText(cliente.getCelularFormatado());
            edtFone2.setText(cliente.getFixoFormatado());
            edtEmail.setText(cliente.getEmail());
            edtEndereco.setText(cliente.getEndereco());
            edtNumero.setText(cliente.getNumero());
            edtComplemento.setText(cliente.getComplemento());
            edtCidade.setText(cliente.getCidade());
            edtUF.setText(cliente.getUF());
            edtBairro.setText(cliente.getBairro());

            edtFacebook.setText(cliente.getFacebook());
            edtInstagram.setText(cliente.getInstagram());
            edtPinterest.setText(cliente.getPinterest());
            edtTamCamiseta.setText(cliente.getTamCamiseta());
            edtTamCalca.setText(cliente.getTamCalca());
            edtTamCalcado.setText(cliente.getTamCalcado());

        }
    }
}

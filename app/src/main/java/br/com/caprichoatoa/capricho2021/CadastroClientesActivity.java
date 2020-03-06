package br.com.caprichoatoa.capricho2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.sql.Connection;

import br.com.caprichoatoa.bancodados.Cliente;
import br.com.caprichoatoa.bancodados.ConexaoServidor;
import br.com.caprichoatoa.utilidades.FormataTexto;
import br.com.caprichoatoa.utilidades.ValidaCPF;


public class CadastroClientesActivity extends AppCompatActivity {

    Context contextoCadastro;
    ConexaoServidor cs;
    Cliente cliente;

    EditText edtCPF;
    EditText edtNome;
    Spinner spnGenero;
    EditText edtFone1;
    EditText edtFone2;
    EditText edtEmail;
    EditText edtEndereco;
    EditText edtNumero;
    EditText edtComplemento;
    EditText edtCidade;
    EditText edtUF;
    EditText edtBairro;
    EditText edtFacebook;
    EditText edtInstagram;
    EditText edtPinterest;
    EditText edtTamCamiseta;
    EditText edtTamCalca;
    EditText edtTamCalcado;

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
        edtCPF.addTextChangedListener(VerificaCPF());

        edtNome = findViewById(R.id.edtNome);
        spnGenero = findViewById(R.id.spnGenero);
        edtFone1 = findViewById(R.id.edtFone1);
        edtFone2 = findViewById(R.id.edtFone2);
        edtEmail = findViewById(R.id.edtEmail);
        edtEndereco = findViewById(R.id.edtEndereco);
        edtNumero = findViewById(R.id.edtNumero);
        edtComplemento = findViewById(R.id.edtComplemento);
        edtCidade = findViewById(R.id.edtCidade);
        edtUF = findViewById(R.id.edtUF);
        edtBairro = findViewById(R.id.edtBairro);
        edtFacebook = findViewById(R.id.edtFacebook);
        edtInstagram = findViewById(R.id.edtInstagram);
        edtPinterest = findViewById(R.id.edtPinTerest);
        edtTamCamiseta = findViewById(R.id.edtTamCamiseta);
        edtTamCalca = findViewById(R.id.edtTamCalca);
        edtTamCalcado = findViewById(R.id.edtTamCalcado);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.generos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGenero.setAdapter(adapter);

        Button btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AtualizaDadosCliente();
            }
        });

    }


    private TextWatcher VerificaCPF() {

        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (ValidaCPF.ValidaCPF(edtCPF.getText().toString())) {
                    VerificaDadosCliente();
                }
            }
        };
    }

    private void VerificaDadosCliente() {

        long documento = Long.parseLong(edtCPF.getText().toString().replace(".", "").replace("-", "").replace("/", ""));
        cliente.setDocumento(documento);
        RecuperaDadosCliente tarefa = new RecuperaDadosCliente();
        tarefa.execute();

    }

    private void AtualizaDadosCliente() {
        UpsertCliente tarefa = new UpsertCliente();
        tarefa.execute();

    }

    private class UpsertCliente extends AsyncTask<String, Void, Cliente> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            cliente.setNome(edtNome.getText().toString());
            cliente.setSexo(spnGenero.getSelectedItem().toString());


            // todo -- modificar o objeto cliente para receber string e separar a informação em ddd e numero
            cliente.setCelular(Long.parseLong(edtFone1.getText().toString()));
            // todo -- modificar o objeto cliente para receber string e separar a informação em ddd e numero
            cliente.setTelefoneFixo(Long.parseLong(edtFone2.getText().toString()));
            cliente.setEmail(edtEmail.getText().toString());
            cliente.setEndereco(edtEndereco.getText().toString());
            cliente.setNumero(edtNumero.getText().toString());
            cliente.setComplemento(edtComplemento.getText().toString());
            cliente.setBairro(edtBairro.getText().toString());
            cliente.setCidade(edtCidade.getText().toString());
            cliente.setUF(edtUF.getText().toString());
            cliente.setFacebook(edtFacebook.getText().toString());
            cliente.setInstagram(edtInstagram.getText().toString());
            cliente.setPinterest(edtPinterest.getText().toString());
            cliente.setTamCamiseta(edtTamCamiseta.getText().toString());
            cliente.setTamCalca(edtTamCalca.getText().toString());
            cliente.setTamCalcado(edtTamCalcado.getText().toString());

        }

        @Override
        protected Cliente doInBackground(String... params) {

            try {

                Connection DbConn = cs.open();
                cliente.UpsertCliente(DbConn);

                cs.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return cliente;
        }

        @Override
        protected void onPostExecute(Cliente mCliente) {
        }
    }

    private class RecuperaDadosCliente extends AsyncTask<String, Void, Cliente> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Cliente doInBackground(String... params) {

            try {

                Connection DbConn = cs.open();
                cliente.BuscaCadastroCliente(DbConn);

                cs.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            return cliente;
        }

        @Override
        protected void onPostExecute(Cliente mCliente) {

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

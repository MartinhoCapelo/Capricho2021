package br.com.caprichoatoa.capricho2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import br.com.caprichoatoa.bancodados.Cliente;
import br.com.caprichoatoa.utilidades.FormataTexto;

public class CadastroClientesActivity extends AppCompatActivity {

    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_clientes);

        final EditText txtCPF = findViewById(R.id.txtCPF);

        txtCPF.addTextChangedListener(FormataTexto.Formata(txtCPF, FormataTexto.FORMAT_CPF));

        long documento = Long.parseLong("3309007812");
//        cliente=new Cliente(documento, "", 0);
//        cliente.BuscaCadastroCliente();

//        txtCPF.setText(cliente.getNome());
        txtCPF.setText("  " + documento +   "  ");
    }

}

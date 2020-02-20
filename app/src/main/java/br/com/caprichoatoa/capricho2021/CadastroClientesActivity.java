package br.com.caprichoatoa.capricho2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;


import br.com.caprichoatoa.utilidades.FormataTexto;

public class CadastroClientesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_clientes);

        final EditText txtCPF = findViewById(R.id.txtCPF);

        txtCPF.addTextChangedListener(FormataTexto.mask(txtCPF, FormataTexto.FORMAT_CPF));
    }

}

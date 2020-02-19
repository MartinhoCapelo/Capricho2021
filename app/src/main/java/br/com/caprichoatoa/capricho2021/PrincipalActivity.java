package br.com.caprichoatoa.capricho2021;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import android.content.pm.PackageManager;
import android.os.AsyncTask;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Locale;

import br.com.caprichoatoa.bancodados.Conexao;

public class PrincipalActivity extends AppCompatActivity {

    EditText edtUsuario;
    EditText edtSenha;
    TextView txtResultado;
    Button btnLogin;
    Context uiContext;

    public int contadorConfiguracao = 0;

    SharedPreferences prefs;
    String servidorConexao;
    String bancoConexao;
    String usuarioConexao;
    String senhaConexao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        uiContext = this;
        prefs = uiContext.getSharedPreferences(".config", Context.MODE_PRIVATE);

        servidorConexao = prefs.getString("servidor", "");
        bancoConexao = prefs.getString("bancoDados", "");
        usuarioConexao = prefs.getString("usuario", "");
        senhaConexao = prefs.getString("senha", "");

        edtUsuario = findViewById(R.id.edtUsuario);
        edtSenha = findViewById(R.id.edtSenha);
        txtResultado = findViewById(R.id.txtResultado);

        btnLogin = findViewById(R.id.btnOK);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ValidaUsuario tarefa = new ValidaUsuario();
                tarefa.execute();

            }
        });

        ImageView imagem = findViewById(R.id.logo_LoginActivity);
        imagem.setOnClickListener(new View.OnClickListener() {

            EditText edtServidor = findViewById(R.id.edtServidor);
            EditText edtBancoDados = findViewById(R.id.edtBancoDados);
            EditText edtUsuario = findViewById(R.id.edtUsuarioConexao);
            EditText edtSenha = findViewById(R.id.edtSenhaConexao);

            @Override
            public void onClick(View v) {
                contadorConfiguracao += 1;
                if (contadorConfiguracao > 10) {
                    // solicitar informações de conexao ao banco de dados.

                    final ConstraintLayout dadosConexao = findViewById(R.id.layout_dados_servidor);
                    dadosConexao.setVisibility(View.VISIBLE);

                    try {

                        edtServidor.setText(prefs.getString("servidor", ""));
                        edtBancoDados.setText(prefs.getString("bancoDados", ""));
                        edtUsuario.setText(prefs.getString("usuario", ""));
                        edtSenha.setText(prefs.getString("senha", ""));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    Button btnSalvar = findViewById(R.id.btnSalvarConexao);
                    btnSalvar.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            try {

                                SharedPreferences.Editor prefsEditor = prefs.edit();
                                prefsEditor.putString("servidor", edtServidor.getText().toString());
                                prefsEditor.putString("bancoDados", edtBancoDados.getText().toString());
                                prefsEditor.putString("usuario", edtUsuario.getText().toString());
                                prefsEditor.putString("senha", edtSenha.getText().toString());
                                prefsEditor.apply();

                                dadosConexao.setVisibility(View.INVISIBLE);

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                }
            }
        });

        String versao = "";
        try {
            versao = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            if (e.getMessage() != null) {
                Log.e("Entrada", e.getMessage());
            }
        }
        this.setTitle(this.getTitle() + " versão " + versao);
    }

    protected void onResume() {
        super.onResume();
        edtUsuario.setText("");
        edtSenha.setText("");

        if (prefs.getString("servidor", "").equals("")) {
            txtResultado.setText(R.string.servidor_nao_encontrado);
            txtResultado.setTextColor(Color.RED);
        } else {
            txtResultado.setText("");
        }
    }

    private class ValidaUsuario extends AsyncTask<String, Void, Integer> {

        String usuario;
        String senha;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            usuario = edtUsuario.getText().toString();
            senha = edtSenha.getText().toString();
        }

        @Override
        protected Integer doInBackground(String... params) {

            int retorno = 0;
            double versao;

            try {

                versao = Double.parseDouble(getPackageManager().getPackageInfo(getPackageName(), 0).versionName);
                String strSQL = " EXEC app_ValidaUsuario '" + usuario + "', '" + senha + "', " + versao + " \n";

                Connection DbConn = Conexao.open(servidorConexao, bancoConexao, usuarioConexao, senhaConexao);
                Statement stmt = DbConn.createStatement();
                ResultSet reset = stmt.executeQuery(strSQL);
                while (reset.next()) {
                    retorno = Integer.parseInt(reset.getString("coduser"));
                }

                Conexao.close(DbConn);

            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            return retorno;
        }

        @Override
        protected void onPostExecute(Integer codUser) {

            if (codUser == 0) {
                txtResultado.setText(R.string.informacoes_incorretas);
            } else if (codUser == -1) {
                txtResultado.setText(R.string.atualize_versão_aplicativo);
            } else {
                txtResultado.setText(R.string.acesso_autorizado);
                String usuario = edtUsuario.getText().toString().trim().toUpperCase(Locale.getDefault());

                Toast.makeText(uiContext, "Acesso autorizado", Toast.LENGTH_LONG).show();


//                //TODO
//                //IMPLEMENTAR A TELA DE MENU INICIAL
                Intent i = new Intent(uiContext, MenuActivity.class);
                i.putExtra("usuario", usuario);
                i.putExtra("coduser", codUser);
                startActivity(i);
            }
        }
    }
}
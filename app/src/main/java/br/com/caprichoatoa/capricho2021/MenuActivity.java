package br.com.caprichoatoa.capricho2021;

import br.com.caprichoatoa.bancodados.Conexao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class MenuActivity extends AppCompatActivity {

    Context uiContext;

    static int codUser;
    String usuario;
    ListView lista;

    SharedPreferences prefs;
    static String servidorConexao;
    static String bancoConexao;
    static String usuarioConexao;
    static String senhaConexao;
    static List<String> opcoes = new ArrayList<>();
    static ArrayAdapter<String> adapter;

    Button btnMotivacional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        uiContext = this;

        Intent i = getIntent();
        usuario = i.getStringExtra("usuario");
        codUser = i.getIntExtra("coduser", 0);

        // TODO - RETIRAR... SOMENTE PARA DESENVOLVIMENTO
        // *********************************
        usuario="MRTINHO";
        codUser=98;
        // *********************************

        btnMotivacional = findViewById(R.id.btnMotivacional);

        prefs = uiContext.getSharedPreferences(".config", Context.MODE_PRIVATE);

        servidorConexao = prefs.getString("servidor", "");
        bancoConexao = prefs.getString("bancoDados", "");
        usuarioConexao = prefs.getString("usuario", "");
        senhaConexao = prefs.getString("senha", "");

        adapter = new ArrayAdapter<>(this, R.layout.list_adapter, android.R.id.text1, opcoes);

        lista = findViewById(R.id.lst_menu);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ViewGroup layout = (ViewGroup) view;
                TextView t = (TextView) layout.getChildAt(0);
              String opcao = t.getText().toString();
                Intent i = null;

                switch (opcao) {
                    case "Presencial":
                    case "Futura":
                        Toast.makeText(uiContext, "FUTURA", Toast.LENGTH_LONG).show();
//                        i = new Intent(uiContext, SelecionaLoteActivity.class);
                        break;

                    case "Classificação":
                        Toast.makeText(uiContext, "PRESENCIAL", Toast.LENGTH_LONG).show();
//                        i = new Intent(uiContext, SelecionaLoteClassificacaoActivity.class);
                        break;
                    case "Cadastro de Clientes":
                        Toast.makeText(uiContext, "CADASTRO DE CLIENTES", Toast.LENGTH_LONG).show();
                        i = new Intent(uiContext, CadastroClientesActivity.class);
                        break;

                }
                if (i != null) {
                    i.putExtra("usuario", usuario);
                    i.putExtra("coduser", codUser);
                    i.putExtra("tipo", opcao);
                    i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        RecuperaFraseMotivacional tarefa = new RecuperaFraseMotivacional();
        tarefa.execute();

        RecuperaOpcoesMenu tarefa2 = new RecuperaOpcoesMenu();
        tarefa2.execute();

    }

    private class RecuperaOpcoesMenu extends AsyncTask<String, Void, List<String>> {

        @Override
        protected List<String> doInBackground(String... strings) {

            List<String> retorno = new ArrayList<>();

            try {

                String strSQL = " EXEC app_OpcoesMenu " + codUser;

                Connection DbConn = Conexao.open(servidorConexao, bancoConexao, usuarioConexao, senhaConexao);
                Statement stmt = DbConn.createStatement();
                ResultSet resultSet = stmt.executeQuery(strSQL);

                while (resultSet.next()) {
                    retorno.add(resultSet.getString("Descricao"));
                }
                Conexao.close(DbConn);

            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
            return retorno;
        }

        @Override
        protected void onPostExecute(List<String> strings) {
            opcoes.clear();
            opcoes.addAll(strings);

            lista.setAdapter(adapter);

            super.onPostExecute(strings);

        }
    }

    private class RecuperaFraseMotivacional extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            String retorno = "";

            try {

                String strSQL = " EXEC FraseMotivacional ";

                Connection DbConn = Conexao.open(servidorConexao, bancoConexao, usuarioConexao, senhaConexao);
                Statement stmt = DbConn.createStatement();
                ResultSet reset = stmt.executeQuery(strSQL);
                while (reset.next()) {
                    retorno = reset.getString("frase");
                }

                Conexao.close(DbConn);

            } catch (Exception e) {
                System.out.print(e.getMessage());
            }

            return retorno;
        }

        @Override
        protected void onPostExecute(String frase) {
            btnMotivacional.setText(frase);
        }
    }

}
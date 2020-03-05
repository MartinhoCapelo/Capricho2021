package br.com.caprichoatoa.capricho2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;

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

import br.com.caprichoatoa.bancodados.ConexaoServidor;


public class MenuActivity extends AppCompatActivity {

    Context contextoMenu;
    ConexaoServidor cs;

    static int codUser;
    String usuario;
    ListView lista;

    static List<String> opcoes = new ArrayList<>();
    static ArrayAdapter<String> adapter;

    Button btnMotivacional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        contextoMenu = this;
        cs = new ConexaoServidor(contextoMenu);

        Intent i = getIntent();
        usuario = i.getStringExtra("usuario");
        codUser = i.getIntExtra("coduser", 0);

//        // TODO - RETIRAR... SOMENTE PARA DESENVOLVIMENTO
//        // *********************************
        usuario="MRTINHO";
        codUser=98;
//        // *********************************

        btnMotivacional = findViewById(R.id.btnMotivacional);

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
                        Toast.makeText(contextoMenu, "FUTURA", Toast.LENGTH_LONG).show();
//                        i = new Intent(uiContext, SelecionaLoteActivity.class);
                        break;

                    case "Classificação":
                        Toast.makeText(contextoMenu, "PRESENCIAL", Toast.LENGTH_LONG).show();
//                        i = new Intent(uiContext, SelecionaLoteClassificacaoActivity.class);
                        break;
                    case "Cadastro de Clientes":
                        Toast.makeText(contextoMenu, "CADASTRO DE CLIENTES", Toast.LENGTH_LONG).show();
                        i = new Intent(contextoMenu, CadastroClientesActivity.class);
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


                Connection DbConn = cs.open();

                Statement stmt = DbConn.createStatement();
                ResultSet resultado = stmt.executeQuery(strSQL);
                while (resultado.next()) {
                    retorno.add(resultado.getString("Descricao"));
                }

                cs.close();

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
                Connection DbConn = cs.open();
                Statement stmt = DbConn.createStatement();
                ResultSet resultado = stmt.executeQuery(strSQL);
                while (resultado.next()) {
                    retorno = resultado.getString("frase");
                }
                cs.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


            return retorno;
        }

        @Override
        protected void onPostExecute(String frase) {
            btnMotivacional.setText(frase);
        }
    }
}
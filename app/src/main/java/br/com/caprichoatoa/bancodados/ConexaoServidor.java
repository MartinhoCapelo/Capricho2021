package br.com.caprichoatoa.bancodados;

import android.content.Context;
import android.content.SharedPreferences;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoServidor {

    private String servidor;
    private String banco;
    private String usuario;
    private String senha;
    private Connection conexao;

    public ConexaoServidor(Context contexto) {

        SharedPreferences prefs = contexto.getSharedPreferences(".config", Context.MODE_PRIVATE);

        servidor = prefs.getString("servidor", "");
        banco = prefs.getString("bancoDados", "");
        usuario = prefs.getString("usuario", "");
        senha = prefs.getString("senha", "");

    }

    public Connection open() {

        conexao = null;

        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            conexao = DriverManager.getConnection("jdbc:jtds:sqlserver://" + servidor + ":1433/" + banco, usuario, senha);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return conexao;
    }

    public void close() {
        try {
            conexao.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

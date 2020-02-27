package br.com.caprichoatoa.bancodados;

import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoServidor {

    public static Connection open(String server, String database, String username, String password) {

        Connection retorno = null;

        try {

            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
            retorno = DriverManager.getConnection("jdbc:jtds:sqlserver://" + server + ":1433/" + database, username, password);

        } catch (Exception e) {
            Log.e("Erro", "Erro: " + e.getMessage());
        }
        return retorno;
    }

    public static void close(Connection c) {
        try {
            c.close();
        } catch (Exception e) {
            Log.e("Erro", "Erro: " + e.getMessage());
        }
    }
}

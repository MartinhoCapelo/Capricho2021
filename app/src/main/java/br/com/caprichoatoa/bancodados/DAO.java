package br.com.caprichoatoa.bancodados;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

public class DAO {

    private static final String[] CLN_PUBLICO = { "publicoID", "descricao" };
    private static final String[] CLN_TIPO = { "publicoID", "tipoID", "descricao" };
    private static final String[] CLN_COR = { "corID", "descricaoCor" };
    private static final String[] CLN_PRECO = { "Preco" };
    private static final String[] CLN_PREITEMLOTE = { "loteID", "sequencia", "publicoID", "tipoID", "corID", "preco", "defeito", "jogo", "dtCadastro" };

    public SQLiteDatabase db;
    private static DAO instance = new DAO();

    Calendar calendar = Calendar.getInstance();
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

//    public static DAO getInstance(Context ctx) {
//        if (instance.db == null || !instance.db.isOpen()) {
//            instance.db = new DB(ctx).getWritableDatabase();
//        }
//
//        return instance;
//    }

//    // ---------- PRE ITEM LOTE
//    public void incluir(PreItemLote c) {
//
//        int iSequencia = 0;
//
//        db.beginTransaction();
//        try {
//
//            if (c.getSequencia() > 0) {
//                iSequencia = c.getSequencia();
//            } else {
//                iSequencia = getProximaSequencia(c.getLoteID());
//            }
//
//            String data = c.getDtCadastro();
//            if (TextUtils.isEmpty(data)) {
//                data = format.format(calendar.getTime());
//            }
//
//            ContentValues cv = new ContentValues();
//            cv.put(CLN_PREITEMLOTE[0], c.getLoteID());
//            cv.put(CLN_PREITEMLOTE[1], iSequencia);
//            cv.put(CLN_PREITEMLOTE[2], c.getPublicoID());
//            cv.put(CLN_PREITEMLOTE[3], c.getTipoID());
//            cv.put(CLN_PREITEMLOTE[4], c.getCorID());
//            cv.put(CLN_PREITEMLOTE[5], c.getPreco());
//            cv.put(CLN_PREITEMLOTE[6], c.getDefeito());
//            cv.put(CLN_PREITEMLOTE[7], c.getJogo());
//            cv.put(CLN_PREITEMLOTE[8], data);
//
//            db.insert(DB.TBL_PREITEMLOTE, null, cv);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }

//    public void excluir(PreItemLote c) {
//        db.beginTransaction();
//        try {
//            db.delete(DB.TBL_PREITEMLOTE, CLN_PREITEMLOTE[0] + "=" + c.getLoteID() + " and " + CLN_PREITEMLOTE[1] + "=" + c.getSequencia(), null);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    private PreItemLote carregarPreItemLote(Cursor c) {
//        int loteID = c.getInt(c.getColumnIndex(CLN_PREITEMLOTE[0]));
//        int sequencia = c.getInt(c.getColumnIndex(CLN_PREITEMLOTE[1]));
//        int publicoID = c.getInt(c.getColumnIndex(CLN_PREITEMLOTE[2]));
//        int tipoID = c.getInt(c.getColumnIndex(CLN_PREITEMLOTE[3]));
//        int corID = c.getInt(c.getColumnIndex(CLN_PREITEMLOTE[4]));
//        int preco = c.getInt(c.getColumnIndex(CLN_PREITEMLOTE[5]));
//        int defeito = c.getInt(c.getColumnIndex(CLN_PREITEMLOTE[6]));
//        int jogo = c.getInt(c.getColumnIndex(CLN_PREITEMLOTE[7]));
//        String dtCadastro = c.getString(c.getColumnIndex(CLN_PREITEMLOTE[8]));
//
//        PreItemLote item = new PreItemLote(loteID, sequencia, publicoID, tipoID, corID, preco, defeito, jogo, dtCadastro);
//        return item;
//    }
//
//    public PreItemLote BuscarPreItemLote(int loteID, int sequencia) {
//        String sTemp = "SELECT * FROM tblPreItemLote where loteID = " + loteID + " and sequencia = " + sequencia;
//
//        Cursor c = db.rawQuery(sTemp, null);
//        if (c.getCount() > 0) {
//            c.moveToFirst();
//            return carregarPreItemLote(c);
//        }
//        return null;
//    }
//
//    public void ExcluirPreItensLote(int loteID) {
//
//        db.beginTransaction();
//        try {
//            db.delete(DB.TBL_PREITEMLOTE, CLN_PREITEMLOTE[0] + "=" + loteID, null);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    public List<PreItemLote> listarPreItensLote(int loteID) {
//        List<PreItemLote> lista = new ArrayList<PreItemLote>();
//
//        Cursor c = db.query(DB.TBL_PREITEMLOTE, null, CLN_PREITEMLOTE[0] + "=?", new String[] { String.valueOf(loteID) }, null, null, CLN_PREITEMLOTE[1] + " DESC");
//        c.moveToFirst();
//
//        while (!c.isAfterLast()) {
//            PreItemLote item = carregarPreItemLote(c);
//            lista.add(item);
//            c.moveToNext();
//        }
//        return lista;
//    }

//    private int getProximaSequencia(int loteid) {
//        int retorno = 0;
//
//        String sTemp = "SELECT ifnull(MAX(SEQUENCIA),0) FROM tblPreItemLote where loteID = " + loteid;
//        Cursor c = db.rawQuery(sTemp, null);
//        c.moveToFirst();
//        if (!c.isAfterLast()) {
//            retorno = c.getInt(0);
//            retorno += 1;
//        }
//
//        return retorno;
//    }
//
//    public ContentValues getTotais(int loteid) {
//        ContentValues cv = new ContentValues();
//
//        String strSQL = "SELECT COUNT(SEQUENCIA) PECAS, SUM(PRECO) VALOR FROM tblPreItemLote WHERE LoteID = " + loteid;
//        Cursor c = db.rawQuery(strSQL, null);
//        c.moveToFirst();
//        if (!c.isAfterLast()) {
//            cv.put("pecas", c.getInt(0));
//            cv.put("valor", c.getInt(1));
//        } else {
//            cv.put("pecas", c.getInt(0));
//            cv.put("valor", c.getInt(1));
//        }
//
//        return cv;
//    }
//
    // ---------- TIPO --------------------------------------------------------
//    public void incluir(Tipo c) {
//
//        db.beginTransaction();
//        try {
//            ContentValues cv = new ContentValues();
//            cv.put(CLN_TIPO[0], c.getPublicoID());
//            cv.put(CLN_TIPO[1], c.getTipoID());
//            cv.put(CLN_TIPO[2], c.getDescricao());
//
//            db.insert(DB.TBL_TIPO, null, cv);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    public void excluirTudoTipo() {
//        db.beginTransaction();
//        try {
//            db.delete(DB.TBL_TIPO, null, null);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    public Tipo BuscarTipo(int id) {
//        Cursor c = db.query(DB.TBL_TIPO, null, CLN_TIPO[1] + "=" + id, null, null, null, null);
//        if (c.getCount() > 0) {
//            c.moveToFirst();
//            return carregarTipo(c);
//        }
//        return null;
//    }
//
//    private Tipo carregarTipo(Cursor c) {
//        int idPub = c.getInt(c.getColumnIndex(CLN_TIPO[0]));
//        int idTip = c.getInt(c.getColumnIndex(CLN_TIPO[1]));
//        String des = c.getString(c.getColumnIndex(CLN_TIPO[2]));
//        Tipo tipo = new Tipo(idPub, idTip, des);
//        return tipo;
//    }
//
//    public List<Tipo> listarTipos(int nomeTipo) {
//        List<Tipo> lista = new ArrayList<Tipo>();
//        Cursor c = db.query(DB.TBL_TIPO, null, CLN_TIPO[0] + "=?", new String[] { String.valueOf(nomeTipo) }, null, null, CLN_TIPO[2]);
//        c.moveToFirst();
//
//        while (!c.isAfterLast()) {
//            Tipo tipo = carregarTipo(c);
//            lista.add(tipo);
//            c.moveToNext();
//        }
//
//        return lista;
//    }
//
//    // ---------- COR --------------------------------------------------------
//    public void incluir(Cor c) {
//
//        db.beginTransaction();
//        try {
//            ContentValues cv = new ContentValues();
//            cv.put(CLN_COR[0], c.getCorID());
//            cv.put(CLN_COR[1], c.getDescricaoCor());
//
//            db.insert(DB.TBL_COR, null, cv);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    public void excluirTudoCor() {
//        db.beginTransaction();
//        try {
//            db.delete(DB.TBL_COR, null, null);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    public Cor BuscarCor(int id) {
//        Cursor c = db.query(DB.TBL_COR, null, CLN_COR[0] + "=" + id, null, null, null, null);
//        if (c.getCount() > 0) {
//            c.moveToFirst();
//            return carregarCor(c);
//        }
//        return null;
//    }
//
//    private Cor carregarCor(Cursor c) {
//        int corID = c.getInt(c.getColumnIndex(CLN_COR[0]));
//        String sCor = c.getString(c.getColumnIndex(CLN_COR[1]));
//        Cor cor = new Cor(corID, sCor);
//        return cor;
//    }
//
//    public List<Cor> listarCores() {
//        List<Cor> lista = new ArrayList<Cor>();
//        Cursor c = db.query(DB.TBL_COR, null, null, null, null, null, CLN_COR[1]);
//        c.moveToFirst();
//
//        while (!c.isAfterLast()) {
//            Cor tipo = carregarCor(c);
//            lista.add(tipo);
//            c.moveToNext();
//        }
//        return lista;
//    }
//
//    // ---------- PRECO --------------------------------------------------------
//    public void incluir(Preco c) {
//
//        db.beginTransaction();
//        try {
//            ContentValues cv = new ContentValues();
//            cv.put(CLN_PRECO[0], c.getPreco());
//
//            db.insert(DB.TBL_PRECO, null, cv);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    public void excluirTudoPreco() {
//        db.beginTransaction();
//        try {
//            db.delete(DB.TBL_PRECO, null, null);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    private Preco carregarPreco(Cursor c) {
//        int iPreco = c.getInt(0);
//        Preco preco = new Preco(iPreco);
//        return preco;
//    }
//
//    public Preco buscaProximoPrecoMaior(int preco) {
//
//        Cursor c = db.rawQuery("SELECT MIN(PRECO) FROM tblPreco where PRECO> " + preco, null);
//        if (c.getCount() > 0) {
//            c.moveToFirst();
//            return carregarPreco(c);
//        }
//        return null;
//    }
//
//    public Preco buscaProximoPrecoMenor(int preco) {
//
//        Cursor c = db.rawQuery("SELECT MAX(PRECO) FROM tblPreco where PRECO < " + preco, null);
//        if (c.getCount() > 0) {
//            c.moveToFirst();
//            return carregarPreco(c);
//        }
//        return null;
//    }
//
//    public int AtualizaPreco(PreItemLote item, int preco) {
//
//        String[] args = new String[] { String.valueOf(item.getLoteID()), String.valueOf(item.getSequencia()) };
//        String data = format.format(calendar.getTime());
//
//        ContentValues values = new ContentValues();
//        values.put(CLN_PREITEMLOTE[5], preco);
//        values.put(CLN_PREITEMLOTE[8], data);
//
//        return db.update(DB.TBL_PREITEMLOTE, values, "loteID=? AND sequencia=?", args);
//
//    }
//
//    public List<Preco> listarPreco() {
//        List<Preco> lista = new ArrayList<Preco>();
////		Cursor c = db.query(DB.TBL_PRECO, null, CLN_PRECO[0], null, null, null, CLN_PRECO[0]);
//        Cursor c = db.rawQuery("SELECT PRECO FROM tblPreco order by PRECO ", null);
//        c.moveToFirst();
//
//        while (!c.isAfterLast()) {
//            Preco preco = carregarPreco(c);
//            lista.add(preco);
//            c.moveToNext();
//        }
//        return lista;
//    }
//
//    // -------------- PUBLICO --------------------------------------------------
//    public void incluir(Publico c) {
//
//        db.beginTransaction();
//        try {
//            ContentValues cv = new ContentValues();
//            cv.put(CLN_PUBLICO[0], c.getPublicoID());
//            cv.put(CLN_PUBLICO[1], c.getDescricao());
//
//            db.insert(DB.TBL_PUBLICO, null, cv);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    public void excluirTudoPublico() {
//        db.beginTransaction();
//        try {
//            db.delete(DB.TBL_PUBLICO, null, null);
//            db.setTransactionSuccessful();
//        } finally {
//            db.endTransaction();
//        }
//    }
//
//    public Publico BuscarPublico(int id) {
//        Cursor c = db.query(DB.TBL_PUBLICO, null, CLN_PUBLICO[0] + "=" + id, null, null, null, null);
//        if (c.getCount() > 0) {
//            c.moveToFirst();
//            return carregarPublico(c);
//        }
//        return null;
//    }
//
//    private Publico carregarPublico(Cursor c) {
//        int idPub = c.getInt(c.getColumnIndex(CLN_PUBLICO[0]));
//        String des = c.getString(c.getColumnIndex(CLN_PUBLICO[1]));
//        Publico publico = new Publico(idPub, des);
//        return publico;
//    }
//
//    public List<Publico> listarPublicos() {
//        List<Publico> lista = new ArrayList<Publico>();
//        Cursor c = db.query(DB.TBL_TIPO, null, null, null, null, null, null, null);
//        c.moveToFirst();
//
//        while (!c.isAfterLast()) {
//            Publico publico = carregarPublico(c);
//            lista.add(publico);
//            c.moveToNext();
//        }
//
//        return lista;
//    }

}

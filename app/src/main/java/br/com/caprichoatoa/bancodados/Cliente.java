package br.com.caprichoatoa.bancodados;

import android.content.Context;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

public class Cliente {

//    private String Nome;                    //[Nome] [nchar] (80) NOT NULL,
//    private long Documento;                //[CNPJCPF] [numeric] (15, 0) NOT NULL,
//    private int Tipo;                    //[TipoCliente] [numeric] (4, 0) NOT NULL,
//    private String Endereco;                //[Endereco] [nchar] (80) NOT NULL,
//    private int Numero;                  //[Numero] [nchar] (10) NULL,
//    private String Complemento;             //[Complemento] [nchar] (20) NULL,
//    private String Bairro;                  //[Bairro] [varchar] (100) NULL,
//    private String Cidade;                  //[Cidade] [nchar] (50) NOT NULL,
//    private String UF;                      //[UF] [char](2) NOT NULL,
//    private String CEP;                     //[CEP] [numeric] (8, 0) NULL,
//    private String Email;                   //[Email] [nchar] (80) NOT NULL,
//    private String Sexo;                    //[Sexo] [nchar] (1) NULL,
//    private String FlagFornecedor;        //[FlagFornecedor] [nchar] (1) NULL,
//    private String FisicaJuridica;          //[FisicaJuridica] [nchar] (1) NULL,
//    private Date Nascimento;              //[Nascimento] [datetime] NULL,
//    private Timestamp Cadastro;                //[Cadastro] [datetime] NULL,
//    private Timestamp UltAlteracao;            //[UltAlteracao] [datetime] NULL,
//    private String MeioCadastro;            //[MeioCadastro] [nchar] (10) NULL,
//    private String InscEstadual;            //[InscEstadual] [nchar] (15) NULL,
//    private int DDDCel;                  //[DDDCel] [numeric] (3, 0) NULL,
//    private long Celular;                 //[Celular] [numeric] (12, 0) NULL,
//    private int DDDFixo;                 //[DDDFixo] [numeric] (3, 0) NULL,
//    private long TelefoneFixo;            //[TelefoneFixo] [numeric] (12, 0) NULL,
//    private String ConheceuLoja;            //[ConheceuLoja] [nchar] (50) NULL,
//    private String Atendente;               //[Atendente] [nchar] (20) NULL,
//    private long CodVitrine;              //[CodVitrine] [numeric] (6, 0) NULL,
//    private String Pergunta;                //[Pergunta] [nchar] (50) NULL,
//    private String Facebook;                //[Facebook] [varchar] (100) NULL,
//    private String Instagram;               //[Instagram] [varchar] (100) NULL,
//    private String Pinterest;               //[Pinterest] [varchar] (100) NULL,
//    private String TamCamiseta;             //[TamCamiseta] [varchar] (100) NULL,
//    private String TamCalca;                //[TamCalca] [varchar] (100) NULL,
//    private String TamCalcado;              //[TamCalcado] [varchar] (100) NULL,
//    private String ComunicacaoAutorizada;   //[ComunicacaoAutorizada] [varchar] (10) NULL,
//    private String SituacaoCadastro;
//
//
//    public Cliente(long documento, String nome, int tipo) {
//        Nome = nome;
//        Documento = documento;
//        Tipo = tipo;
//    }
//
//    public void BuscaCadastroCliente() {
//
//        try {
//            String strSQL = " EXEC sp_BuscaCadastroCliente 03309007812";
//
////            ConectaSQL.getInstance();
////            ResultSet resultado = DAO.ExecutaQuery(strSQL);// , ConectaSQL.getConnection());
////            while (resultado.next()) {
////                setNome(resultado.getString("Nome"));
////            }
//
////            String strSQL = " EXEC sp_BuscaCadastroCliente " + getDocumento();
////
////            Connection conexao = Conexao.open(contexto);
////            ResultSet resultado = DAO.ExecutaQuery(strSQL, conexao);
////
////            if (resultado.isAfterLast()){
////                // não encontrou...?
////                ///                    }
////                SituacaoCadastro = "novo";
////            }
////            while (resultado.next()) {
////                //alimentar o objeto
////
////                setNome(resultado.getString("nome"));
////                setTipo(resultado.getInt("TipoCliente"));
////                setEndereco(resultado.getString("Endereco"));
////                setNumero(resultado.getInt("Numero"));
////
////                setComplemento(resultado.getString("Complemento"));
////                setBairro(resultado.getString("Bairro"));
////                setCidade(resultado.getString("Cidade"));
////                setUF(resultado.getString("UF"));
////                setCEP(resultado.getString("CEP"));
////                setEmail(resultado.getString("Email"));
////                setSexo(resultado.getString("Sexo"));
////                setFlagFornecedor(resultado.getString("FlagFornecedor"));
////                setFisicaJuridica(resultado.getString("FisicaJuridica"));
////
////                Date data = null;
////                try {
////                    data = resultado.getDate("nascimento");
////                } catch (Exception ex) {
////                    ex.printStackTrace();
////                }
////                setNascimento(data);
////
////                Timestamp dataHora = null;
////                try {
////                    dataHora = resultado.getTimestamp("cadastro");
////                } catch (Exception ex) {
////                    ex.printStackTrace();
////                }
////                setCadastro(dataHora);
////
////                try {
////                    dataHora = resultado.getTimestamp("ultAlteracao");
////                } catch (Exception ex) {
////                    ex.printStackTrace();
////                }
////                setUltAlteracao(dataHora);
////
////
////                setMeioCadastro(resultado.getString("MeioCadastro"));
////                setInscEstadual(resultado.getString("InscEstadual"));
////
////                setDDDCel(resultado.getInt("DDDCel"));
////                setCelular(resultado.getLong("Celular"));
////
////                setDDDFixo(resultado.getInt("DDDFixo"));
////                setTelefoneFixo(resultado.getLong("TelefoneFixo"));
////
////
////                setConheceuLoja(resultado.getString("ConheceuLoja"));
////                setAtendente(resultado.getString("Atendente"));
////                setCodVitrine(0);
////                setPergunta(resultado.getString("Pergunta"));
////                setFacebook(resultado.getString("Facebook"));
////                setInstagram(resultado.getString("Instagram"));
////                setPinterest(resultado.getString("Pinterest"));
////                setTamCamiseta(resultado.getString("TamCamiseta"));
////                setTamCalca(resultado.getString("TamCalca"));
////                setTamCalcado(resultado.getString("TamCalcado"));
////                setComunicacaoAutorizada(resultado.getString("ComunicacaoAutorizada"));
////
////                SituacaoCadastro = "cadastrado";
////            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//
//    public String getNome() {
//        return Nome;
//    }
//
//    public void setNome(String nome) {
//        Nome = nome;
//    }
//
//    public long getDocumento() {
//        return Documento;
//    }
//
//    public void setDocumento(long documento) {
//        Documento = documento;
//    }
//
//    public int getTipo() {
//        return Tipo;
//    }
//
//    public void setTipo(int tipo) {
//        Tipo = tipo;
//    }
//
//    public String getEndereco() {
//        return Endereco;
//    }
//
//    public void setEndereco(String endereco) {
//        Endereco = endereco;
//    }
//
//    public int getNumero() {
//        return Numero;
//    }
//
//    public void setNumero(int numero) {
//        Numero = numero;
//    }
//
//    public String getComplemento() {
//        return Complemento;
//    }
//
//    public void setComplemento(String complemento) {
//        Complemento = complemento;
//    }
//
//    public String getBairro() {
//        return Bairro;
//    }
//
//    public void setBairro(String bairro) {
//        Bairro = bairro;
//    }
//
//    public String getCidade() {
//        return Cidade;
//    }
//
//    public void setCidade(String cidade) {
//        Cidade = cidade;
//    }
//
//    public String getUF() {
//        return UF;
//    }
//
//    public void setUF(String UF) {
//        this.UF = UF;
//    }
//
//    public String getCEP() {
//        return CEP;
//    }
//
//    public void setCEP(String CEP) {
//        this.CEP = CEP;
//    }
//
//    public String getEmail() {
//        return Email;
//    }
//
//    public void setEmail(String email) {
//        Email = email;
//    }
//
//    public String getSexo() {
//        return Sexo;
//    }
//
//    public void setSexo(String sexo) {
//        Sexo = sexo;
//    }
//
//    public String getFlagFornecedor() {
//        return FlagFornecedor;
//    }
//
//    public void setFlagFornecedor(String flagFornecedor) {
//        FlagFornecedor = flagFornecedor;
//    }
//
//    public String getFisicaJuridica() {
//        return FisicaJuridica;
//    }
//
//    public void setFisicaJuridica(String fisicaJuridica) {
//        FisicaJuridica = fisicaJuridica;
//    }
//
//    public Date getNascimento() {
//        return Nascimento;
//    }
//
//    public void setNascimento(Date nascimento) {
//        Nascimento = nascimento;
//    }
//
//    public Timestamp getCadastro() {
//        return Cadastro;
//    }
//
//    public void setCadastro(Timestamp cadastro) {
//        Cadastro = cadastro;
//    }
//
//    public Timestamp getUltAlteracao() {
//        return UltAlteracao;
//    }
//
//    public void setUltAlteracao(Timestamp ultAlteracao) {
//        UltAlteracao = ultAlteracao;
//    }
//
//    public String getMeioCadastro() {
//        return MeioCadastro;
//    }
//
//    public void setMeioCadastro(String meioCadastro) {
//        MeioCadastro = meioCadastro;
//    }
//
//    public String getInscEstadual() {
//        return InscEstadual;
//    }
//
//    public void setInscEstadual(String inscEstadual) {
//        InscEstadual = inscEstadual;
//    }
//
//    public int getDDDCel() {
//        return DDDCel;
//    }
//
//    public void setDDDCel(int DDDCel) {
//        this.DDDCel = DDDCel;
//    }
//
//    public long getCelular() {
//        return Celular;
//    }
//
//    public void setCelular(long celular) {
//        Celular = celular;
//    }
//
//    public int getDDDFixo() {
//        return DDDFixo;
//    }
//
//    public void setDDDFixo(int DDDFixo) {
//        this.DDDFixo = DDDFixo;
//    }
//
//    public long getTelefoneFixo() {
//        return TelefoneFixo;
//    }
//
//    public void setTelefoneFixo(long telefoneFixo) {
//        TelefoneFixo = telefoneFixo;
//    }
//
//    public String getConheceuLoja() {
//        return ConheceuLoja;
//    }
//
//    public void setConheceuLoja(String conheceuLoja) {
//        ConheceuLoja = conheceuLoja;
//    }
//
//    public String getAtendente() {
//        return Atendente;
//    }
//
//    public void setAtendente(String atendente) {
//        Atendente = atendente;
//    }
//
//    public long getCodVitrine() {
//        return CodVitrine;
//    }
//
//    public void setCodVitrine(long codVitrine) {
//        CodVitrine = codVitrine;
//    }
//
//    public String getPergunta() {
//        return Pergunta;
//    }
//
//    public void setPergunta(String pergunta) {
//        Pergunta = pergunta;
//    }
//
//    public String getFacebook() {
//        return Facebook;
//    }
//
//    public void setFacebook(String facebook) {
//        Facebook = facebook;
//    }
//
//    public String getInstagram() {
//        return Instagram;
//    }
//
//    public void setInstagram(String instagram) {
//        Instagram = instagram;
//    }
//
//    public String getPinterest() {
//        return Pinterest;
//    }
//
//    public void setPinterest(String pinterest) {
//        Pinterest = pinterest;
//    }
//
//    public String getTamCamiseta() {
//        return TamCamiseta;
//    }
//
//    public void setTamCamiseta(String tamCamiseta) {
//        TamCamiseta = tamCamiseta;
//    }
//
//    public String getTamCalca() {
//        return TamCalca;
//    }
//
//    public void setTamCalca(String tamCalca) {
//        TamCalca = tamCalca;
//    }
//
//    public String getTamCalcado() {
//        return TamCalcado;
//    }
//
//    public void setTamCalcado(String tamCalcado) {
//        TamCalcado = tamCalcado;
//    }
//
//    public String getComunicacaoAutorizada() {
//        return ComunicacaoAutorizada;
//    }
//
//    public void setComunicacaoAutorizada(String comunicacaoAutorizada) {
//        ComunicacaoAutorizada = comunicacaoAutorizada;
//    }
//
//    public String getSituacaoCadastro() {
//        return SituacaoCadastro;
//    }
//
//    public void setSituacaoCadastro(String situacaoCadastro) {
//        SituacaoCadastro = situacaoCadastro;
//    }
}


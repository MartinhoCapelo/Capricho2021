package br.com.caprichoatoa.bancodados;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

public class Cliente {

    private String Nome;                    //[Nome] [nchar] (80) NOT NULL,
    private long Documento;                //[CNPJCPF] [numeric] (15, 0) NOT NULL,
    private int Tipo;                    //[TipoCliente] [numeric] (4, 0) NOT NULL,
    private String Endereco;                //[Endereco] [nchar] (80) NOT NULL,
    private String Numero;                  //[Numero] [nchar] (10) NULL,
    private String Complemento;             //[Complemento] [nchar] (20) NULL,
    private String Bairro;                  //[Bairro] [varchar] (100) NULL,
    private String Cidade;                  //[Cidade] [nchar] (50) NOT NULL,
    private String UF;                      //[UF] [char](2) NOT NULL,
    private String CEP;                     //[CEP] [numeric] (8, 0) NULL,
    private String Email;                   //[Email] [nchar] (80) NOT NULL,
    private String Sexo;                    //[Sexo] [nchar] (1) NULL,
    private String FlagFornecedor;        //[FlagFornecedor] [nchar] (1) NULL,
    private String FisicaJuridica;          //[FisicaJuridica] [nchar] (1) NULL,
    private Date Nascimento;              //[Nascimento] [datetime] NULL,
    private Timestamp Cadastro;                //[Cadastro] [datetime] NULL,
    private Timestamp UltAlteracao;            //[UltAlteracao] [datetime] NULL,
    private String MeioCadastro;            //[MeioCadastro] [nchar] (10) NULL,
    private String InscEstadual;            //[InscEstadual] [nchar] (15) NULL,
    private int DDDCel;                  //[DDDCel] [numeric] (3, 0) NULL,
    private long Celular;                 //[Celular] [numeric] (12, 0) NULL,
    private int DDDFixo;                 //[DDDFixo] [numeric] (3, 0) NULL,
    private long TelefoneFixo;            //[TelefoneFixo] [numeric] (12, 0) NULL,
    private String ConheceuLoja;            //[ConheceuLoja] [nchar] (50) NULL,
    private String Atendente;               //[Atendente] [nchar] (20) NULL,
    private long CodVitrine;              //[CodVitrine] [numeric] (6, 0) NULL,
    private String Pergunta;                //[Pergunta] [nchar] (50) NULL,
    private String Facebook;                //[Facebook] [varchar] (100) NULL,
    private String Instagram;               //[Instagram] [varchar] (100) NULL,
    private String Pinterest;               //[Pinterest] [varchar] (100) NULL,
    private String TamCamiseta;             //[TamCamiseta] [varchar] (100) NULL,
    private String TamCalca;                //[TamCalca] [varchar] (100) NULL,
    private String TamCalcado;              //[TamCalcado] [varchar] (100) NULL,
    private String ComunicacaoAutorizada;   //[ComunicacaoAutorizada] [varchar] (10) NULL,
    private String SituacaoCadastro;

    public Cliente(long documento, String nome, int tipo) {
        Nome = nome;
        Documento = documento;
        Tipo = tipo;
    }

    public void BuscaCadastroCliente(Connection dbConn) {

        try {
            String strSQL = " EXEC sp_BuscaCadastroCliente " + getDocumento();
            Statement stmt = dbConn.createStatement();
            ResultSet resultado = stmt.executeQuery(strSQL);

            if (resultado.isAfterLast()) {
                SituacaoCadastro = "novo";
            }

            while (resultado.next()) {
                //alimentar o objeto

                setNome(resultado.getString("nome").trim());
                setTipo(resultado.getInt("TipoCliente"));
                setEndereco(resultado.getString("Endereco").trim());
                setNumero(resultado.getString("Numero").trim());
                setComplemento(resultado.getString("Complemento").trim());
                setBairro(resultado.getString("Bairro").trim());
                setCidade(resultado.getString("Cidade").trim());
                setUF(resultado.getString("UF").trim());
                setCEP(resultado.getString("CEP").trim());
                setEmail(resultado.getString("Email").trim().toLowerCase());
                setSexo(resultado.getString("Sexo").trim());
                setFlagFornecedor(resultado.getString("FlagFornecedor").trim());
                setFisicaJuridica(resultado.getString("FisicaJuridica").trim());

                Date data = null;
                try {
                    data = resultado.getDate("nascimento");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                setNascimento(data);

                Timestamp dataHora = null;
                try {
                    dataHora = resultado.getTimestamp("cadastro");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                setCadastro(dataHora);

                try {
                    dataHora = resultado.getTimestamp("ultAlteracao");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                setUltAlteracao(dataHora);


                setMeioCadastro(resultado.getString("MeioCadastro").trim());
                setInscEstadual(resultado.getString("InscEstadual").trim());

                setDDDCel(resultado.getInt("DDDCel"));
                setCelular(resultado.getLong("Celular"));

                setDDDFixo(resultado.getInt("DDDFixo"));
                setTelefoneFixo(resultado.getLong("TelefoneFixo"));

                setConheceuLoja(resultado.getString("ConheceuLoja").trim());
                setAtendente(resultado.getString("Atendente").trim());
                setCodVitrine(0);
                setPergunta(resultado.getString("Pergunta").trim());
                setFacebook(resultado.getString("Facebook").trim());
                setInstagram(resultado.getString("Instagram").trim());
                setPinterest(resultado.getString("Pinterest").trim());
                setTamCamiseta(resultado.getString("TamCamiseta").trim());
                setTamCalca(resultado.getString("TamCalca").trim());
                setTamCalcado(resultado.getString("TamCalcado").trim());
                setComunicacaoAutorizada(resultado.getString("ComunicacaoAutorizada").trim());

                SituacaoCadastro = "cadastrado";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean UpsertCliente(Connection dbConn) {
        // Insert ou Update no cadastro do cliente conforme o caso

        StringBuilder strSql = new StringBuilder("EXEC sp_UpsertCliente ");
        strSql.append(getDocumento());
        strSql.append(",");
        strSql.append(getNome());
        strSql.append(",");
        strSql.append(getEndereco());
        strSql.append(",");
        strSql.append(getNumero());
        strSql.append(",");
        strSql.append(getComplemento());
        strSql.append(",");
        strSql.append(getBairro());
        strSql.append(",");
        strSql.append(getCidade());
        strSql.append(",");
        strSql.append(getUF());
        strSql.append(",");
        strSql.append(getCEP());
        strSql.append(",");
        strSql.append(getEmail());
        strSql.append(",");
        strSql.append(getSexo());
        strSql.append(",");
        strSql.append(getTipo());
        strSql.append(",");
        strSql.append(getFlagFornecedor());
        strSql.append(",");
        strSql.append(getFisicaJuridica());
        strSql.append(",");
        strSql.append(getNascimento());
        strSql.append(",");
        strSql.append(getCadastro());
        strSql.append(",");
        strSql.append(getMeioCadastro());
        strSql.append(",");
        strSql.append(getInscEstadual());
        strSql.append(",");
        strSql.append(getDDDCel());
        strSql.append(",");
        strSql.append(getCelular());
        strSql.append(",");
        strSql.append(getDDDFixo());
        strSql.append(",");
        strSql.append(getTelefoneFixo());
        strSql.append(",");
        strSql.append(getConheceuLoja());
        strSql.append(",");
        strSql.append(getAtendente());
        strSql.append(",");
        strSql.append(getCodVitrine());
        strSql.append(",");
        strSql.append(getPergunta());
        strSql.append(",");
        strSql.append(getFacebook());
        strSql.append(",");
        strSql.append(getInstagram());
        strSql.append(",");
        strSql.append(getPinterest());
        strSql.append(",");
        strSql.append(getTamCamiseta());
        strSql.append(",");
        strSql.append(getTamCalca());
        strSql.append(",");
        strSql.append(getTamCalcado());
        strSql.append(",");

        String SituacaoCadastro;     // controle do app

        try {

            Statement stmt = dbConn.createStatement();
            ResultSet resultado = stmt.executeQuery(strSql.toString());

            while (resultado.next()) {
                //alimentar o objeto

                String retorno = resultado.getString(0).trim();

                if (Long.toString(getDocumento()).equals(retorno)){
                  return true;
                } else {
                    return false;
                }
            }
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return false;

    }


    public String getCelularFormatado() {
        String prefixo = Integer.toString(getDDDCel());
        if (getDDDCel() < 10) {
            prefixo = "";
        }
        String numero = Long.toString(getCelular());

        return formataCelular(prefixo, numero);
    }

    public String getFixoFormatado() {
        String prefixo = Integer.toString(getDDDFixo());
        if (getDDDFixo() < 10) {
            prefixo = "";
        }
        String numero = Long.toString(getTelefoneFixo());
        return formataCelular(prefixo, numero);
    }

    public String formataCelular(String prefixo, String numero) {
        String retorno;

        if (prefixo.length() != 2) {
            prefixo = "11";
        }
        retorno = "(" + prefixo + ") ";

        switch (numero.length()) {
            default:
                retorno = "";
                break;
            case 8:
                retorno = retorno + numero.substring(0, 4) + "-" + numero.substring(4, 8);
                break;
            case 9:
                retorno = retorno + numero.substring(0, 5) + "-" + numero.substring(5, 9);
                break;
        }

        return retorno;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public long getDocumento() {
        return Documento;
    }

    public void setDocumento(long documento) {
        Documento = documento;
    }

    public int getTipo() {
        return Tipo;
    }

    public void setTipo(int tipo) {
        Tipo = tipo;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getComplemento() {
        return Complemento;
    }

    public void setComplemento(String complemento) {
        Complemento = complemento;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String sexo) {
        Sexo = sexo;
    }

    public String getFlagFornecedor() {
        return FlagFornecedor;
    }

    public void setFlagFornecedor(String flagFornecedor) {
        FlagFornecedor = flagFornecedor;
    }

    public String getFisicaJuridica() {
        return FisicaJuridica;
    }

    public void setFisicaJuridica(String fisicaJuridica) {
        FisicaJuridica = fisicaJuridica;
    }

    public Date getNascimento() {
        return Nascimento;
    }

    public void setNascimento(Date nascimento) {
        Nascimento = nascimento;
    }

    public Timestamp getCadastro() {
        return Cadastro;
    }

    public void setCadastro(Timestamp cadastro) {
        Cadastro = cadastro;
    }

    public Timestamp getUltAlteracao() {
        return UltAlteracao;
    }

    public void setUltAlteracao(Timestamp ultAlteracao) {
        UltAlteracao = ultAlteracao;
    }

    public String getMeioCadastro() {
        return MeioCadastro;
    }

    public void setMeioCadastro(String meioCadastro) {
        MeioCadastro = meioCadastro;
    }

    public String getInscEstadual() {
        return InscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
        InscEstadual = inscEstadual;
    }

    public int getDDDCel() {
        return DDDCel;
    }

    public void setDDDCel(int DDDCel) {
        this.DDDCel = DDDCel;
    }

    public long getCelular() {
        return Celular;
    }

    public void setCelular(long celular) {
        Celular = celular;
    }

    public int getDDDFixo() {
        return DDDFixo;
    }

    public void setDDDFixo(int DDDFixo) {
        this.DDDFixo = DDDFixo;
    }

    public long getTelefoneFixo() {
        return TelefoneFixo;
    }

    public void setTelefoneFixo(long telefoneFixo) {
        TelefoneFixo = telefoneFixo;
    }

    public String getConheceuLoja() {
        return ConheceuLoja;
    }

    public void setConheceuLoja(String conheceuLoja) {
        ConheceuLoja = conheceuLoja;
    }

    public String getAtendente() {
        return Atendente;
    }

    public void setAtendente(String atendente) {
        Atendente = atendente;
    }

    public long getCodVitrine() {
        return CodVitrine;
    }

    public void setCodVitrine(long codVitrine) {
        CodVitrine = codVitrine;
    }

    public String getPergunta() {
        return Pergunta;
    }

    public void setPergunta(String pergunta) {
        Pergunta = pergunta;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String instagram) {
        Instagram = instagram;
    }

    public String getPinterest() {
        return Pinterest;
    }

    public void setPinterest(String pinterest) {
        Pinterest = pinterest;
    }

    public String getTamCamiseta() {
        return TamCamiseta;
    }

    public void setTamCamiseta(String tamCamiseta) {
        TamCamiseta = tamCamiseta;
    }

    public String getTamCalca() {
        return TamCalca;
    }

    public void setTamCalca(String tamCalca) {
        TamCalca = tamCalca;
    }

    public String getTamCalcado() {
        return TamCalcado;
    }

    public void setTamCalcado(String tamCalcado) {
        TamCalcado = tamCalcado;
    }

    public String getComunicacaoAutorizada() {
        return ComunicacaoAutorizada;
    }

    public void setComunicacaoAutorizada(String comunicacaoAutorizada) {
        ComunicacaoAutorizada = comunicacaoAutorizada;
    }

    public String getSituacaoCadastro() {
        return SituacaoCadastro;
    }

    public void setSituacaoCadastro(String situacaoCadastro) {
        SituacaoCadastro = situacaoCadastro;
    }


}




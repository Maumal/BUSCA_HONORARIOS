/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Honorarios_Custas;

//import java.util.Date;

/**
 *
 * @author f7057419
 */
public class Honorario {  
    
    private String evento;
   // private Date dataHonorario;
    private String dataHonorario;
    private String npj;
    private String Contratado ;
    private String ModoDeRemuneracao ;
   // private double valor;
    private String valor;
    private String situacao;

    public String getContratado() {
        return Contratado;
    }

    public void setContratado(String Contratado) {
        this.Contratado = Contratado;
    }

    public String getModoDeRemuneracao() {
        return ModoDeRemuneracao;
    }

    public void setModoDeRemuneracao(String ModoDeRemuneracao) {
        this.ModoDeRemuneracao = ModoDeRemuneracao;
    }

    public String getDataHonorario() {
        return dataHonorario;
    }

    public void setDataHonorario(String dataHonorario) {
        this.dataHonorario = dataHonorario;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getNpj() {
        return npj;
    }

    public void setNpj(String npj) {
        this.npj = npj;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
    
    
    
    
    

}
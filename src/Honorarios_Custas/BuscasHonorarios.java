/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.


 */
package Honorarios_Custas;

import DAO.Conexao;
import Formularios.FmrHonorarios;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Component;
import java.sql.SQLException;
import java.text.ParseException;
import com.thoughtworks.selenium.Selenium;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author f7057419
 */


public class BuscasHonorarios {
    
    /**
     * @param args the command line arguments
     */
    
    private final String usuario;
    private final String senha;
    private Component rootPane;
    

    public BuscasHonorarios(String usuario, String senha) {
        this.usuario = usuario.toUpperCase();
        this.senha = senha;
    }
    

    @SuppressWarnings("null")
    public void capturaInfos(FmrHonorarios fr) throws IOException, InterruptedException, ParseException, SQLException {
        Integer contaProcessoInseridosNoBanco;
        contaProcessoInseridosNoBanco = 0;

        //CONFIGURAR PROXY
        Proxy proxy = new Proxy();

        proxy.setProxyAutoconfigUrl("http://intranet.bb.com.br/firefox_proxy.pac");
        //  proxy.setProxyAutoconfigUrl("cache.bb.com.br");
        proxy.setSocksUsername(usuario);
        proxy.setSocksPassword(senha);

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(CapabilityType.PROXY, proxy);

        //Abre o firefox
        WebDriver driver = new FirefoxDriver(capabilities);
        //Arquivo será gravado na temp
        //String entrada = "C:\\TEMP\\teste1.TXT";
        //String saida = "C:\\Temp\\teste.TXT";
        //BufferedWriter out = new BufferedWriter(new FileWriter(saida)));
        //String phrase = "";

        //FileWriter fw = new FileWriter("c:/teste.txt");       
        //FileWriter fw = new FileWriter("C:/Temp/teste.TXT");
        //BufferedWriter bw = new BufferedWriter(fw);             
        //bw.write("outra coisa");  
        //bw.flush();  
        //bw.close();      
        //CARREGAR LISTA COM AS SITUAÇOES DO BANCO
        //situacoes = new SituacaoDAO().consultaTodas();
        //WebDriver driver = new FirefoxDriver();
        //String url = "https://www.consignacoes.prefeitura.sp.gov.br";
        //String url = "http://intranet.bb.com.br";
        String url = "http://juridico.intranet.bb.com.br/paj";

        driver.get(url);
        Selenium selenium = new WebDriverBackedSelenium(driver, url);
        selenium.open(url);

        //PROCURAR OS ELEMENTOS PARA ENTRAR NO PORTAL
        //WebElement userName = driver.findElement(By.name("username"));
        //WebElement passWord = driver.findElement(By.name("senha"));
        //WebElement codigo = driver.findElement(By.id("captcha"));
        //PROCURAR OS ELEMENTOS PARA ENTRAR NA INTRANET
        WebElement userName = driver.findElement(By.name("chave"));
        WebElement passWord = driver.findElement(By.name("senha"));
        WebElement entrar = driver.findElement(By.id("entrar"));

        userName.sendKeys(usuario);
        passWord.sendKeys(senha);

        //String cod = JOptionPane.showInputDialog(null, "Digite o codigo captcha para entrar");
        //codigo.sendKeys(cod);
        entrar.click();

        String Aguarde = "";
        
        
        while ("".equals(Aguarde)) {
            try {
                Aguarde = driver.findElement(By.id("Processos")).getText();
            } catch (Exception e) {
            }
        }   
        
        
       
        
        
        
        
        driver.findElement(By.id("Terceirização")).click();
        driver.findElement(By.id("Pagamentos a efetuar")).click();
        driver.findElement(By.id("Conferência de eventos para liberação de pagamentos")).click();
        driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu")).click();
        driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu")).sendKeys("e");
        Thread.sleep(500);
        driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu")).sendKeys("e");
        Thread.sleep(500);
        driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu")).click();
        Thread.sleep(500);
     /*   new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
        new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
        new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
        new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
        new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).se
        Thread.sleep(2000);
        

        //Coferir se o item foi selecionado
       String Edital = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu")).getText();

       while (!"EDITAL 2008/0425".equals(Edital)) {
            new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
           new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
           Thread.sleep(1000);
            Edital = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu")).getText();
        }

        new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
        new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
        new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
        new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
        new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
        new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
        Thread.sleep(2000);*/
      
        
        
        
        driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:btPesquisar")).click();
       
        Thread.sleep(5000);

        String testaSeHaDadosNaTabela = "";//Variável que confere se há dados na tabela
        try {
            testaSeHaDadosNaTabela = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:0:evento1Decorate:evento1Output")).getText();
        } catch (Exception e) {
        }

        System.out.println(testaSeHaDadosNaTabela);

        if ("".equals(testaSeHaDadosNaTabela)) {//Caso não haja dados na Tabela
            driver.close();
            JOptionPane.showMessageDialog(null, "Não existem dados para Carregar. ");
            System.exit(0);
        } else {//Caso haja dados na Tabela           
            int paginas;

            //NÚMERO DE linhas
            //String linhas = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:divDataTabletableContratosPrimeiroNivel")).getText();
            String linha = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:j_id160")).getText();
            linha = linha.replaceAll("Registros encontrados:", "").trim();
            //--System.out.println(linha);
            int Linhas;
            int Linhainicio = 0;
            Linhas = Integer.parseInt(linha);
            //--System.out.println("Linhas " + Linhas);

            if (Linhas <= 10) {
                paginas = 1;
            } else {
                //Dados da tabela
                //NÚMERO DE PÁGINAS

                String numeroPaginas = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:j_id351")).getText();

                numeroPaginas = numeroPaginas.replaceAll("páginas", "");
                //SEPARA XX/XX  TOTAL DE PÁGINAS      
                String s[] = numeroPaginas.split("/");
                //System.out.println(s[0]);  
                //System.out.println(s[1]);   
                String totaldePaginas = (s[1]).trim();

                paginas = Integer.parseInt(totaldePaginas);
                //--System.out.println("páginas " + paginas);

            }

         //   List<Honorario> lista = new ArrayList<>();
            //List<Honorario> lista = new ArrayList<Honorario>();

            Date dataHoje = new Date();
            SimpleDateFormat formataData2 = new SimpleDateFormat("dd/MM/yyyy");
            String data = formataData2.format(dataHoje);

            //Gerar txt
//               FileOutputStream arquivo;
//               arquivo = new FileOutputStream("c://temp/HONORARIOS.txt");
//               PrintStream printStream = new PrintStream(arquivo);
//Pega o total de páginas e pega os dados da tabela n vezes
            for (int i = 0; i < paginas; i++) {
                //--System.out.println("Página :" + i);
                for (int e = 0; e < 10; e++) {
                    Honorario honorario = new Honorario();
                    //--System.out.println("Linha: " + Linhainicio + " de : " + Linhas);                
                    //Campo EVENTO da tabela 
                    // String eventoHonorarios = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:" + Linhainicio + ":evento1Decorate:evento1Output")).getText();

                    String eventoHonorarios = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:" + Linhainicio + ":evento1Decorate:evento1Output")).getText();
                    //--System.out.println("O evento da linha " + Linhainicio + " é: " + eventoHonorarios);                
                    honorario.setEvento(eventoHonorarios);

                    //Campo DATA da tabela
                    String dataHonorarios="";
                    try {
                        dataHonorarios = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:" + Linhainicio + ":data1Decorate:data1Output")).getText();
                        honorario.setDataHonorario(dataHonorarios);
                    } catch (Exception ex) {                                             
                    }
                    
                    
                    
                    
                    

                    //honorario.setDataHonorario(formato2.format(formato1.parse(dataHonorarios)));
                    //Campo NPJ da tabela
                    String npjHonorarios = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:" + Linhainicio + ":cmdDetalharProcesso1")).getText();
                    //--System.out.println("O npj da linha " + Linhainicio + " é: " + npjHonorarios);
                    honorario.setNpj((npjHonorarios));
                    //Campo CONTRATADO da tabela
                    String contratadoHonorarios = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:" + Linhainicio + ":contratado1Decorate")).getText();
                    //--System.out.println("O contratado da linha " + Linhainicio + " é: " + contratadoHonorarios);
                    contratadoHonorarios = contratadoHonorarios.replaceAll("\\.", "").replaceAll("_", "").replaceAll(",", "").replaceAll("'", "").replaceAll("º", "").replaceAll("ª", "").replaceAll("  ", " ").replaceAll("   ", " ").replaceAll("&", "E").replaceAll("    ", " ");
                    honorario.setContratado((contratadoHonorarios));
                    //Campo MODO_de_REMUNERAÇÂO da tabela 
                    //----String modoDeRemuneracaoHonorarios = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:" + Linhainicio + ":modelo1Decorate:modelo1Output")).getText();
                    //System.out.println(modoDeRemuneracaoHonorarios);
                    //---honorario.setModoDeRemuneracao(modoDeRemuneracaoHonorarios);
                    //Campo VALOR da tabela 
                    String valorHonorarios = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:" + Linhainicio + ":valor1Decorate:valor1Output")).getText();
                    //--System.out.println("O valor da linha " + Linhainicio + " é: " + valorHonorarios);

                    honorario.setValor(valorHonorarios);         //Campo SITUAÇÂO da tabela 
                    String situacaoHonorarios = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:" + Linhainicio + ":situacao1Decorate:situacao1Output")).getText();
                    //--System.out.println("A  Situação da linha " + Linhainicio + " é: " + situacaoHonorarios);
                    honorario.setSituacao((situacaoHonorarios));
                    //--System.out.println("");  
                    //System.out.println(Linhainicio);
                    //System.out.println(npjHonorarios);
                    Linhainicio = Linhainicio + 1;

                 //   lista.add(honorario);
                    //printStream.println(honorario.getDataHonorario() + " ; " + honorario.getNpj() + " ; " + honorario.getContratado() + " ; " + honorario.getModoDeRemuneracao() + " ; " + honorario.getModoDeRemuneracao() + " ; " + honorario.getSituacao());
                    //printStream.println(honorario.getDataHonorario() + " ; " + honorario.getEvento() + " ; " + honorario.getNpj() + " ; " + honorario.getContratado() + " ; " + honorario.getValor());

                    //Grava no Banco de Dados
                    //tratamento das váriaveis para entar no Banco                 
                    String dataHonorariosBd="";
                    if ("".equals(dataHonorarios)){
                        System.out.println(data);
                        
                    String dia = data.substring(0, 2);
                    String mes = data.substring(3, 5);
                    String ano = data.substring(6, 10);
                    dataHonorariosBd = "'" + ano + "-" + mes + "-" + dia + "'";
                        
                        
                        System.out.println(dataHonorariosBd);    
                        
                        
                     //   dataHonorariosBd="0000-00-00";
                        
                        
                    }else{        
                    String dia = dataHonorarios.substring(0, 2);
                    String mes = dataHonorarios.substring(3, 5);
                    String ano = dataHonorarios.substring(6, 10);
                    dataHonorariosBd = "'" + ano + "-" + mes + "-" + dia + "'";
                   }                  
                    String npjHonorariosBD = npjHonorarios;
                    npjHonorariosBD = "'" + npjHonorariosBD + "'";
                    //String eventoHonorariosBD = "'" + eventoHonorarios + "'";
                    Integer buscaEnventos = buscaEvt(eventoHonorarios);

                    if (buscaEnventos == 0) {
                        buscaEnventos = buscaEvt(eventoHonorarios);
                    }

                    String buscaEnventosBD = ("'" + buscaEnventos + "'");

                    //String eventoHonorariosBD = "'0000'";
                    Integer buscaEnvolvidos = buscaAdv(contratadoHonorarios);

                    if (buscaEnvolvidos == 0) {
                        buscaEnvolvidos = buscaAdv(contratadoHonorarios);
                    }
                    //String buscaEnvolvidosBD = ("'" + buscaEnvolvidos + "'");                
                    String contratadoHonorariosBD = "'" + buscaEnvolvidos + "'";
                    String valorHonorariosBD = "'" + valorHonorarios.replace(".", "").replace(",", ".") + "'";

                    //String  modoDeRemuneracaoHonorariosBD= "'"+modoDeRemuneracaoHonorarios+"'";           
                    //String situacaoHonorariosBD =   "'"+situacaoHonorarios+"'";
                    String usuarioBD = "'" + usuario + "'";

                    String confereDadosT = confereDados(dataHonorariosBd, npjHonorariosBD, buscaEnventosBD, contratadoHonorariosBD, valorHonorariosBD).toString().trim();

                   // System.out.println(buscaEnventos);
                    if ((buscaEnventos == 1017)) { //SE ENVENTO = 1017 NÃO GRAVA
                        confereDadosT = "true";
                    }
            //        System.out.println(buscaEnvolvidos);
                    if ((buscaEnvolvidos == 0)) {
                        confereDadosT = "true";
                    }

                    //  System.out.println(confereDadosT);
                    //CONFERE SE OS DADOS JÁ ESTÃO CADASTRADOS NO BD E CASO TUDO VERDADEIRO NÃO INSERE NOVAMENTE
                    if ("true".equals(confereDadosT)) {
                        //       System.out.println("TUDO VERDADEIRO");
                    } else {
                        try (Connection cn = (Connection) new Conexao().conectar()) {
//Mudou a ordem dos campos para FUNCI_DEF DATA_DEF FUNCI_CAD DATA_CAD                           
// String sqlGrava = "INSERT INTO hc_honor VALUES (null," + dataHonorariosBd + "," + npjHonorariosBD + "," + buscaEnventosBD + "," + contratadoHonorariosBD + "," + valorHonorariosBD + "," + "'0'" + "," + "'0'" + "," + "''" + ", " + usuarioBD + ",now(),'0000-00-0 00:00:00');";
// 22/10/2014 String sqlGrava = "INSERT INTO hc_honor VALUES (null," + dataHonorariosBd + "," + npjHonorariosBD + "," + buscaEnventosBD + "," + contratadoHonorariosBD + "," + valorHonorariosBD + "," + "'0'" + "," + "'0'" + "," + "''" + ",'0000-00-0 00:00:00' ," + usuarioBD + ",now());";         
String sqlGrava = "INSERT INTO hc_honor (ID_H,DATA_EVT,NPJ,EVT,ADV,VALOR,DEF,MOTIVO,FUNCI_DEF,DATA_DEF,FUNCI_CAD,DATA_CAD,DATA_INI)VALUES (NULL,"+ dataHonorariosBd + "," + npjHonorariosBD + "," + buscaEnventosBD + "," + contratadoHonorariosBD + "," + valorHonorariosBD + "," + "'0'" + "," + "'0'" + "," + "''" + ",   '0000-00-00 00:00:00' ," + usuarioBD + ",now(),NULL);";         
 
                            Statement stm = null;
                            try {
                                stm = (Statement) cn.createStatement();
                            } catch (SQLException ex) {
                            }
                            int rs = 0;
                            try {
                                rs = stm.executeUpdate(sqlGrava);                         
                                contaProcessoInseridosNoBanco = contaProcessoInseridosNoBanco + 1;
                                System.out.println(npjHonorariosBD);
                                //cn.close();
                            } catch (SQLException ex) {
                            }

                            if (rs != 0) {
                                /*  JOptionPane.showMessageDialog(rootPane,
                                 "Dados gravados com sucesso!",
                                 "Arrecadação",
                                 JOptionPane.INFORMATION_MESSAGE);
                                 } else {
                                 JOptionPane.showMessageDialog(rootPane,
                                 "Falha",
                                 "Arrecadação",
                                 JOptionPane.INFORMATION_MESSAGE);
                                 }*/
                            }
                        }
                    }

                    /*if ("true".equals(confereDataT) && "true".equals(confeNpjT) && "true".equals(confereEvtT) && "true".equals(confereEnvolvidosT) && "true".equals(confereValorT)) {
                     System.out.println("TUDO VERDADEIRO");
                     } else {
                     try (Connection cn = (Connection) new Conexao().conectar()) {
                     String sqlGrava = "INSERT INTO hc_honor VALUES (null," + dataHonorariosBd + "," + npjHonorariosBD + "," + buscaEnventosBD + "," + contratadoHonorariosBD + "," + valorHonorariosBD + "," + "'0'" + "," + "'0'" + "," + "''" + ", " + usuarioBD + ",now(),'0000-00-0 00:00:00', '0000-00-0 00:00:00');";
                     //
                     Statement stm = null;
                     try {
                     stm = (Statement) cn.createStatement();
                     } catch (SQLException ex) {
                     }
                     int rs = 0;
                     try {
                     rs = stm.executeUpdate(sqlGrava);
                     //cn.close();
                     } catch (SQLException ex) {
                     }

                     if (rs != 0) {
                     /*  JOptionPane.showMessageDialog(rootPane,
                     "Dados gravados com sucesso!",
                     "Arrecadação",
                     JOptionPane.INFORMATION_MESSAGE);
                     } else {
                     JOptionPane.showMessageDialog(rootPane,
                     "Falha",
                     "Arrecadação",
                     JOptionPane.INFORMATION_MESSAGE);
                     }
                     }
                     }
                     */
                   // System.out.println(Linhainicio + " de  " + Linhas + " Processos");

                    fr.mudaTextoTeste(" Pesquisando :  " + Linhainicio + " de  " + Linhas + " Processos");//exibe a evolução das linhas no label txtProgressão no formulário

                    while (Linhainicio >= Linhas) {
                        //printStream.println("O total de processos é: " + lista.size());
                        e = 10;//Para sair do for -acabou
                        break;                   //printStream.println("O total de processos é: " + lista.size());
                    }
                }
                //tratamento para não clicar em próxima página na última página
                if (i < paginas - 1) {
                    //Variável para aguardar o carregamento da página 
                    String numeroPaginas = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:j_id351")).getText();

                    //CLICAR PARA ADIANTAR PARA AS PRÓXIMAS TELAS DA TABELA               
                    driver.findElement(By.xpath("//td[@onclick=\"Event.fire(this, 'rich:datascroller:onscroll', {'page': 'next'});\"]")).click();

                    //Aguarda o carregamento da página
                    String AguardesnumeroPaginas = numeroPaginas;
                    while (numeroPaginas.equals(AguardesnumeroPaginas)) {
                        try {
                            AguardesnumeroPaginas = driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:j_id351")).getText();
                        } catch (Exception e) {
                        }
                    }

                    //  Thread.sleep(10000);                  
                }
            }
            //driver.close();
            //Gerar excel
//            new GerarExcelHonorarios().Importar("C:/TEMP/honorarios.txt");
//             driver.close();
//             JOptionPane.showMessageDialog(null, "ARQUIVO COM OS DADOS GRAVADOS EM:   C:/Temp/Honorários.xls ");      
//             JOptionPane.showMessageDialog(null, "ARQUIVO COM OS DADOS GRAVADOS EM:   C:/TEMP/HONORARIOS.txt ");
//             JOptionPane.showMessageDialog(null, "Realizado com sucesso");
//            
             //System.exit(0); */
             //JOptionPane.showMessageDialog(null, "Dados Carregados."); 
            fr.mudaTextoTeste1(" " + contaProcessoInseridosNoBanco + " Processos Gravados  ");//exibe a evolução das linhas no label txtProgressão no formulário
            driver.close();
            //System.exit(0);

        }
    }

    private Integer buscaAdv(String contratado) throws SQLException {//Busca o código do contratado para inserir no banco
        Integer ID_ADV = 0;
        //Alterado em 29/07/2014-pois será feita a busca para todo o brasil e será feitos para todos advogados
     /*     switch (contratado) {
         case "AVALLONE E JANZON ADVOGADOS ASSOCIADOS":
         ID_ADV = 1;
         break;
         case "ROCHA, CALDERON E ADVOGADOS ASSOCIADOS":
         ID_ADV = 2;
         break;
         case "KAWASAKI ADVOGADOS ASSOCIADOS":
         ID_ADV = 3;
         break;
         case "M L GOMES ADVOGADOS ASSOCIADOS":
         ID_ADV = 4;
         break;
         case "NELSON PASCHOALOTTO ADVOGADOS ASSOCIADOS":
         ID_ADV = 5;
         break;
         case "OLIMPIO DE AZEVEDO ADVOGADOS":
         ID_ADV = 6;
         break;
         case "ARNOR SERAFIM JR ADVOGADOS ASSOCIADOS":
         ID_ADV = 7;
         break;
         case "VALENTE ADVOGADOS ASSOCIADOS":
         ID_ADV = 8;
         break;
         case "PAULO ROBERTO JOAQUIM DOS REIS ADVOGADOS ASSOCIADOS S C":
         ID_ADV = 9;
         break;
         case "MANDALITI ADVOGADOS":
         ID_ADV = 10;
         break;
         case "LIMA JUNIOR ADVOGADOS E CONSULTORES ASSOCIADOS":
         ID_ADV = 11;
         break;
         case "OUTROS":
         ID_ADV = 22;
         break;
         case "TESTE":
         ID_ADV = 23;
         break;
         }*/

        try (Connection cnenv = (Connection) new Conexao().conectar()) {

            String sqlBuscaAdv = "Select hc_envolv.ID_ENV from hc_envolv where hc_envolv.NOME_ENV= " + "'" + contratado + "'";

            java.sql.Statement stm = cnenv.createStatement();
            try {
                ResultSet rs = stm.executeQuery(sqlBuscaAdv);
                //cn.close();
                if (rs.next()) {
                    //Processar, do jeito que você já fez                    
                    ID_ADV = Integer.parseInt(rs.getString("ID_ENV"));
             //       System.out.println(ID_ADV + "eeeeeeeeeeeeeee");
                    //cn.close();                    
                } else {
                    //Não achou o evento vai inserir e buscar novamente
                    //rs.close();
                    System.out.println("Não achou");
                    //Connection cn = (Connection) new Conexao().conectar();
                    //Connection cni = (Connection) new ConexaoLocal().conectar();
                    String sqlGrava = "INSERT INTO hc_envolv (NOME_ENV,DT_CAD_ENV) VALUES  ('" + contratado + "',now());";//
                    Statement stmi = null;
                    stmi = (Statement) cnenv.createStatement();
                    int rsi = 0;
                    try {
                        rsi = stm.executeUpdate(sqlGrava);
                        //cn.close();
                    } catch (SQLException ex) {
                    }
                }
            } catch (SQLException e) {
            }
        }

        return ID_ADV;

    }

    private Integer buscaEvt(String eventos) throws SQLException {
        Integer ID_EVTr = 0;
        try (Connection cn = (Connection) new Conexao().conectar()) {
            String sqlBuscaEvt2 = "Select hc_evt.ID_EVT from hc_evt where hc_evt.NOME_EVT= " + "'" + eventos + "'";
            java.sql.Statement stm = cn.createStatement();
            try {
                ResultSet rs = stm.executeQuery(sqlBuscaEvt2);
                //cn.close();
                if (rs.next()) {
                    //Processar, do jeito que você já fez                    
                    ID_EVTr = Integer.parseInt(rs.getString("ID_EVT"));
                  //  System.out.println(ID_EVTr + "eeeeeeeeeeeeeee");
                    //cn.close();                    
                } else {
                    //Não achou o evento vai inserir e buscar novamente
                    //rs.close();
                    System.out.println("Não achou");
                    //Connection cn = (Connection) new Conexao().conectar();
                    //Connection cni = (Connection) new ConexaoLocal().conectar();
                    String sqlGrava = "INSERT INTO hc_evt (NOME_EVT) VALUES  ('" + eventos + "');";//
                    Statement stmi = null;
                    try {
                        stmi = (Statement) cn.createStatement();
                    } catch (SQLException ex) {
                    }
                    int rsi = 0;
                    try {
                        rsi = stm.executeUpdate(sqlGrava);
                        //cn.close();
                    } catch (SQLException ex) {
                    }
                }
            } catch (SQLException e) {
            }
        }
        return ID_EVTr;
    }

    private Boolean confereDados(String dataHonorariosBd, String npjHonorariosBD, String buscaEnventosBD, String contratadoHonorariosBD, String valorHonorariosBD) throws SQLException {//Confere se ha linha dá tabela já foi inserida

        Boolean haDados = true;
        try (Connection cn = (Connection) new Conexao().conectar()) {
            // String sqlBuscaEvtc = "Select hc_honor.ID_H  from hc_honor where hc_honor.DATA_EVT= " + dataHonorariosBd + " and  hc_honor.NPJ = " + npjHonorariosBD + "";
            String sqlBuscaDados = "Select *  from hc_honor where hc_honor.DATA_EVT= " + dataHonorariosBd + " and  hc_honor.NPJ = " + npjHonorariosBD + "  and  hc_honor.EVT = " + buscaEnventosBD + "  and  hc_honor.ADV = " + contratadoHonorariosBD + " and  hc_honor.VALOR = " + valorHonorariosBD + "             ";

            java.sql.Statement stm = cn.createStatement();
            ResultSet rs = stm.executeQuery(sqlBuscaDados);

            int linhas = 0;//Contador registros no banco para a data pesquisada            
            while (rs.next()) {
                linhas++;
            }
            if (linhas == 0) {
                haDados = false;
            } else {
            }
        }
        return haDados;
    }

    /*   private Boolean confereData(String dataHonorariosBd, String npjHonorariosBD) throws SQLException {//Confere se ha linha dá tabela já foi inserida

     Boolean haData = true;
     try (Connection cn = (Connection) new Conexao().conectar()) {
     String sqlBuscaEvtc = "Select hc_honor.ID_H  from hc_honor where hc_honor.DATA_EVT= " + dataHonorariosBd + " and  hc_honor.NPJ = " + npjHonorariosBD + "";
     //String sqlVai = "select *  from bdjudicial.Ajure where pleco = '1' and DT_GRAV = " + "'" + dataHoje + "'";
     java.sql.Statement stm = cn.createStatement();
     ResultSet rs = stm.executeQuery(sqlBuscaEvtc);

     int linhas = 0;//Contador registros no banco para a data pesquisada            
     while (rs.next()) {
     linhas++;
     }
     if (linhas == 0) {
     haData = false;
     } else {
     }
     }
     return haData;
     }

     private Boolean confereNpj(String npjHonorariosBD) throws SQLException {//Confere se ha linha dá tabela já foi inserida
     Boolean haNpj = true;
     try (Connection cn = (Connection) new Conexao().conectar()) {
     String sqlBuscaEvtc = "Select hc_honor.ID_H  from hc_honor where hc_honor.NPJ= " + npjHonorariosBD + "";
     //String sqlVai = "select *  from bdjudicial.Ajure where pleco = '1' and DT_GRAV = " + "'" + dataHoje + "'";
     java.sql.Statement stm = cn.createStatement();
     ResultSet rs = stm.executeQuery(sqlBuscaEvtc);

     int linhas = 0;//Contador registros no banco para a data pesquisada

     while (rs.next()) {
     linhas++;
     }

     if (linhas == 0) {
     haNpj = false;
     } else {
     }
     }
     return haNpj;
     }

     private Boolean confereEvt(String buscaEnventos, String npjHonorariosBD) throws SQLException {//Confere se ha linha dá tabela já foi inserida
     Boolean haEvt = true;
     try (Connection cn = (Connection) new Conexao().conectar()) {
     String sqlBuscaEvtc = "Select hc_honor.ID_H  from hc_honor where hc_honor.EVT= " + buscaEnventos + " and  hc_honor.NPJ = " + npjHonorariosBD + "";
     //String sqlVai = "select *  from bdjudicial.Ajure where pleco = '1' and DT_GRAV = " + "'" + dataHoje + "'";
     java.sql.Statement stm = cn.createStatement();
     ResultSet rs = stm.executeQuery(sqlBuscaEvtc);

     int linhas = 0;//Contador registros no banco para a data pesquisada

     while (rs.next()) {
     linhas++;
     }

     if (linhas == 0) {
     haEvt = false;
     } else {
     }
     }
     return haEvt;
     }

     //buscaEnvolvidos
     //valorHonorariosBD
     private Boolean confereEnvolvidos(String buscaEnvolvidos, String npjHonorariosBD) throws SQLException {//Confere se ha linha dá tabela já foi inserida

     Boolean haEnvolvidos = true;
     try (Connection cn = (Connection) new Conexao().conectar()) {
     String sqlBuscaEvtc = "Select hc_honor.ID_H  from hc_honor where hc_honor.ADV= " + buscaEnvolvidos + " and  hc_honor.NPJ = " + npjHonorariosBD + "";
     //String sqlVai = "select *  from bdjudicial.Ajure where pleco = '1' and DT_GRAV = " + "'" + dataHoje + "'";
     java.sql.Statement stm = cn.createStatement();
     ResultSet rs = stm.executeQuery(sqlBuscaEvtc);

     int linhas = 0;//Contador registros no banco para a data pesquisada
     while (rs.next()) {
     linhas++;
     }
     if (linhas == 0) {
     haEnvolvidos = false;
     } else {
     }
     }
     return haEnvolvidos;
     }

     private Boolean confereValor(String valorHonorariosBD, String npjHonorariosBD) throws SQLException {//Confere se ha linha dá tabela já foi inserida
     Boolean haValor = true;
     try (Connection cn = (Connection) new Conexao().conectar()) {
     String sqlBuscaEvtc = "Select hc_honor.ID_H  from hc_honor where hc_honor.VALOR= " + valorHonorariosBD + " and  hc_honor.NPJ = " + npjHonorariosBD + "";
     //String sqlVai = "select *  from bdjudicial.Ajure where pleco = '1' and DT_GRAV = " + "'" + dataHoje + "'";
     java.sql.Statement stm = cn.createStatement();
     ResultSet rs = stm.executeQuery(sqlBuscaEvtc);

     int linhas = 0;//Contador registros no banco para a data pesquisada            
     while (rs.next()) {
     linhas++;
     }
     if (linhas == 0) {
     haValor = false;
     } else {
     }
     }
     return haValor;
     }*/
}

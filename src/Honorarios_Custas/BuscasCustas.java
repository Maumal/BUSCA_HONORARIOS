/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Honorarios_Custas;

import DAO.ConexaoLocal;
import java.text.ParseException;
import java.util.ArrayList;
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
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.util.Date;

/**
 *
 * @author f7057419
 */
public class BuscasCustas {

    /**
     * @param args the command line arguments
     */
    private final String usuario;
    private final String senha;

    public BuscasCustas(String usuario, String senha) {
        this.usuario = usuario.toUpperCase();
        this.senha = senha;
    }

    @SuppressWarnings("null")
    public void capturaInfos() throws IOException, InterruptedException, ParseException, SQLException {

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
        //  Arquivo será gravado na temp
        //String entrada = "C:\\TEMP\\teste1.TXT";
        //String saida = "C:\\Temp\\teste.TXT";
        //BufferedWriter out = new BufferedWriter(new FileWriter(saida)));
        //    String phrase = "";

        //   FileWriter fw = new FileWriter("c:/teste.txt");       
        // FileWriter fw = new FileWriter("C:/Temp/teste.TXT");
        // BufferedWriter bw = new BufferedWriter(fw);             
        // bw.write("outra coisa");  
        // bw.flush();  
        // bw.close();      
        //CARREGAR LISTA COM AS SITUAÇOES DO BANCO
        //   situacoes = new SituacaoDAO().consultaTodas();
        //WebDriver driver = new FirefoxDriver();
        //String url = "https://www.consignacoes.prefeitura.sp.gov.br";
        //String url = "http://intranet.bb.com.br";
        String url = "http://juridico.intranet.bb.com.br/paj";

        driver.get(url);
        Selenium selenium = new WebDriverBackedSelenium(driver, url);
        selenium.open(url);

        //PROCURAR OS ELEMENTOS PARA ENTRAR NO PORTAL
        // WebElement userName = driver.findElement(By.name("username"));
        // WebElement passWord = driver.findElement(By.name("senha"));
        // WebElement codigo = driver.findElement(By.id("captcha"));
        //PROCURAR OS ELEMENTOS PARA ENTRAR NA INTRANET
        WebElement userName = driver.findElement(By.name("chave"));
        WebElement passWord = driver.findElement(By.name("senha"));
        WebElement entrar = driver.findElement(By.id("entrar"));

        userName.sendKeys(usuario);
        passWord.sendKeys(senha);

        //String cod = JOptionPane.showInputDialog(null, "Digite o codigo captcha para entrar");
        //codigo.sendKeys(cod);
        entrar.click();
        /* String Aguarde = "";
         while("".equals(Aguarde)) {
         try {
         Aguarde = driver.findElement(By.id("Processos")).getText();
         }catch (Exception e) {
         }
         }*/

        JOptionPane.showMessageDialog(null, "AGUARDE A PÁGINA CARREGAR E ---DEPOIS--- CLIQUE EM OK. ");

        String testeEncontarTexto = "Você está aqui:";
        //selenium.isTextPresent(testeEncontarTexto);   
        selenium.isElementPresent(testeEncontarTexto);
        //selenium.getTable(id="pesquisarConferenciaLiberacaoForm:divDataTabletableContratosPrimeiroNivel")*/
        //Acessar Custas    
        driver.findElement(By.id("Financeiro")).click();
        driver.findElement(By.id("Custos")).click();
        driver.findElement(By.id("Consultas")).click();
        driver.findElement(By.id("custosForm:pesquisarCustoDecorate:pesquisarCustoRadio:1")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("custosForm:periodoDecorate:periodoInputInputDate")).clear();
        driver.findElement(By.id("custosForm:periodoDecorate:periodoInputInputDate")).sendKeys("01/01/2014");
        Thread.sleep(1000);
        new Select(driver.findElement(By.id("custosForm:situacaoDecorate:situacaoListMenu"))).selectByVisibleText("Aguardando Despacho da Efetivacao");
        driver.findElement(By.id("custosForm:pesquisarDependenciaOperadoraDecorate:pesquisarDependenciaOperadoraDecorate:pesquisarDependenciaOperadoraInput")).clear();
        driver.findElement(By.id("custosForm:pesquisarDependenciaOperadoraDecorate:pesquisarDependenciaOperadoraDecorate:pesquisarDependenciaOperadoraInput")).sendKeys("1981");
        driver.findElement(By.id("custosForm:btPesquisar")).click();
        Thread.sleep(3000);
        String testaSeHaDadosNaTabela = "";
        try {
            testaSeHaDadosNaTabela = driver.findElement(By.id("custosForm:resultadoDataTable:0:colBBJUR")).getText();
        } catch (Exception e) {
        }
        System.out.println(testaSeHaDadosNaTabela);
        if ("".equals(testaSeHaDadosNaTabela)) {//Caso não haja dados na tabela
            driver.close();
            JOptionPane.showMessageDialog(null, "Não existem dados para Carregar. ");
        } else {//Caso haja dados na tabela
            //Dados da tabela
            //NÚMERO DE PÁGINAS
            String numeroPaginas = driver.findElement(By.id("custosForm:resultadoDataTable:j_id596")).getText();
            numeroPaginas = numeroPaginas.replaceAll("páginas", "");
            //SEPARA XX/XX  TOTAL DE PÁGINAS      
            String s[] = numeroPaginas.split("/");
            //System.out.println(s[0]);  
            //System.out.println(s[1]);   
            String totaldePaginas = (s[1]).trim();
            int paginas;
            paginas = Integer.parseInt(totaldePaginas);
            //--System.out.println("páginas " + paginas);
            //NÚMERO DE linhas
            String linhas = driver.findElement(By.id("custosForm:divContador")).getText();
            System.out.println(linhas);
            //String linha = driver.findElement(By.id("custosForm:j_id524")).getText();
            String L[] = linhas.split("de");
            System.out.println(L[0]);
            System.out.println(L[1]);

            int Linhas;
            int Linhainicio = 0;
            Linhas = Integer.parseInt(L[1]);
            //--System.out.println("Linhas " + Linhas);
            List<Custas> lista = new ArrayList<>();
            // List<Honorario> lista = new ArrayList<Honorario>();
            FileOutputStream arquivo;
            arquivo = new FileOutputStream("c://temp/CUSTAS.txt");
            PrintStream printStream = new PrintStream(arquivo);
            //Pega o total de páginas e pega os dados da tabela n vezes
            for (int i = 0; i < paginas; i++) {
                //--System.out.println("Página :" + i);
                for (int e = 0; e < 10; e++) {
                    Custas custas = new Custas();
                    //--System.out.println("Linha: " + Linhainicio + " de : " + Linhas);                
                    //Campo EVENTO da tabela 
                    String npj = driver.findElement(By.id("custosForm:resultadoDataTable:" + Linhainicio + ":colBBJUR")).getText();
                    //--System.out.println("O evento da linha " + Linhainicio + " é: " + eventoHonorarios);                
                    custas.setNpj(npj);

                    //Campo Envolvido da tabela
                    String envolvido = driver.findElement(By.id("custosForm:resultadoDataTable:" + Linhainicio + ":j_id548")).getText();
                    custas.setEnvolvido(envolvido);

                    //honorario.setDataHonorario(formato2.format(formato1.parse(dataHonorarios)));
                    //Campo NPJ da tabela
                    String especificacao = driver.findElement(By.id("custosForm:resultadoDataTable:" + Linhainicio + ":j_id550")).getText();
                    //--System.out.println("O npj da linha " + Linhainicio + " é: " + npjHonorarios);
                    custas.setEspecificacao((especificacao));
                    //Campo CONTRATADO da tabela
                    String solicitante = driver.findElement(By.id("custosForm:resultadoDataTable:" + Linhainicio + ":j_id553")).getText();
                    //--System.out.println("O contratado da linha " + Linhainicio + " é: " + contratadoHonorarios);
                    custas.setSolicitante((solicitante));
                    //Campo MODO_de_REMUNERAÇÂO da tabela 
                    String situacao = driver.findElement(By.id("custosForm:resultadoDataTable:" + Linhainicio + ":j_id585")).getText();
                    //System.out.println(modoDeRemuneracaoHonorarios);
                    custas.setSituacao(situacao);
                    //Campo VALOR da tabela 
                    String valor = driver.findElement(By.id("custosForm:resultadoDataTable:" + Linhainicio + ":j_id587")).getText();
                    //--System.out.println("O valor da linha " + Linhainicio + " é: " + valorHonorarios);
                    custas.setValor(valor);         //Campo SITUAÇÂO da tabela               

                    Linhainicio = Linhainicio + 1;
                    lista.add(custas);
                    printStream.println(custas.getNpj() + " ; " + custas.getEnvolvido() + " ; " + custas.getEspecificacao() + " ; " + custas.getSolicitante() + " ; " + custas.getSituacao() + " ; " + custas.getValor());

                    //Grava no Banco de Dados
                    //tratamento das váriaveis para entar no Banco 
                    String npjCustasBD = npj;
                    npjCustasBD = "'" + npjCustasBD + "'";
                    String SolicitanteCustasBD = solicitante;
                    SolicitanteCustasBD = "'" + SolicitanteCustasBD + "'";
                    String valorcustasBD = "'" + valor.replace(".", "").replace(",", ".") + "'";
                    String usuarioBD = "'" + usuario + "'";

                    Integer buscaEnvolvidosBD = buscaEnvolvido(envolvido.replaceAll("&", "e").replaceAll(",", "").replaceAll("'", "").replaceAll("  ", " "));//Busca o Envolvido e se não tiver insere e retorna o novo ID
                    if (buscaEnvolvidosBD == 0) {
                        buscaEnvolvidosBD = buscaEnvolvido(envolvido);
                    }
                    String buscaEnvolvidosBDStr = ("'" + buscaEnvolvidosBD + "'");
                    Integer buscaEspecificacaoBD = buscaEspecificacao(especificacao.replaceAll("&", "e").replaceAll(",", "").replaceAll("'", "").replaceAll("  ", " "));//Busca o Especificacao e se não tiver insere e retorna o novo ID
                    if (buscaEspecificacaoBD == 0) {
                        buscaEspecificacaoBD = buscaEspecificacao(especificacao);
                    }
                    String buscaEspecificacaoBDtr = ("'" + buscaEspecificacaoBD + "'");
                    //Insert
                    //Connection cn = (Connection) new Conexao().conectar();
                    Connection cn = (Connection) new ConexaoLocal().conectar();
                    //String sqlGrava = "INSERT INTO hc_honor_antiga VALUES (null,"+dataHonorariosBd+","+npjHonorariosBD+","+eventoHonorariosBD+","+contratadoHonorariosBD+","+valorHonorariosBD+","+modoDeRemuneracaoHonorariosBD+","+situacaoHonorariosBD+","+"''"+","+"''"+",'0000-00-0,',"+'0'+",now(),"+usuarioBD+");";             
                    System.out.println(npjCustasBD);
                    System.out.println(buscaEnvolvidosBDStr);
                    System.out.println(buscaEspecificacaoBDtr);
                    System.out.println(SolicitanteCustasBD);
                    System.out.println(valorcustasBD);
                    System.out.println(usuarioBD);

                    // String sqlGrava = "INSERT INTO hc_honor_antiga VALUES (null," + dataHonorariosBd + "," + npjHonorariosBD + "," + eventoHonorariosBD + "," + contratadoHonorariosBD + "," + valorHonorariosBD + "," + "''" + "," + "''" + ",'0000-00-0,'," + '0' + ",now()," + usuarioBD + ");";
                    String sqlGravaCustas = "INSERT INTO hc_custas VALUES (null," + npjCustasBD + "," + buscaEnvolvidosBDStr + "," + buscaEspecificacaoBDtr + "," + SolicitanteCustasBD + "," + valorcustasBD + "," + "'0'" + "," + "'0'" + "," + "''" + ", " + usuarioBD + ",now(),'0000-00-0 00:00:00', '0000-00-0 00:00:00');";

                    Statement stmC = null;
                    try {
                        stmC = (Statement) cn.createStatement();
                    } catch (SQLException ex) {
                    }
                    int rs = 0;
                    try {
                        rs = stmC.executeUpdate(sqlGravaCustas);
                        cn.close();
                    } catch (SQLException ex) {
                    }
                    if (rs != 0) {
                        //JOptionPane.showMessageDialog(null,
                        //"Dados gravados com sucesso!",
                        //"Arrecadação",
                        //JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Falha",
                                "Arrecadação",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                     //stmC.close();
                    //cn.close();//Fechar a conexão
                    //fim do Insert 
                    //*****Fim parte de Gravar no Banco
                    while (Linhainicio >= Linhas) {
                        printStream.println("O total de processos é: " + lista.size());
                        e = 10;//Para sair do for -acabou
                        break;
                    }
                }
                //tratamento para não clicar em próxima página na última página
                if (i < paginas - 1) {
                    //CLICAR PARA ADIANTAR PARA AS PRÓXIMAS TELAS DA TABELA               
                    driver.findElement(By.xpath("//td[@onclick=\"Event.fire(this, 'rich:datascroller:onscroll', {'page': 'fastforward'});\"]")).click();
                    Thread.sleep(5000);

                    //String testeEncontarTexto2 = "Novidades da Intranet";
                    //selenium.isElementPresent(testeEncontarTexto2);             
                    //driver.manage().timeouts().pageLoadTimeout( 100,SECONDS);
                    //Espera o elemento na tela
                    //WebDriverWait wait = new WebDriverWait(driver, 60000);
                    //WebElement o_element = wait.until(ExpectedConditions.elementToBeClickable(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:j_id317")));
                    //String testeEncontarTexto2 = "Você está aqui:";
                    //selenium.isTextPresent(testeEncontarTexto);   
                    //selenium.isElementPresent(testeEncontarTexto2);
                }
            }
            driver.close();
            JOptionPane.showMessageDialog(null, "ARQUIVO COM OS DADOS GRAVADOS EM:   C:/TEMP/CUSTAS.txt ");
            //System.exit(0);
        }
    }

    private Integer buscaEnvolvido(String envolvidos) throws SQLException {
        Integer ID_Envolvido = 0;
        //Integer ID_EVTs;
        //Connection cne = (Connection) new Conexao().conectar();
        Connection cne = (Connection) new ConexaoLocal().conectar();

        String sqlBuscaEnvolvidos = "Select hc_envolv.ID_ENV from hc_envolv where hc_envolv.NOME_ENV= " + "'" + envolvidos + "'";
        java.sql.Statement stm = cne.createStatement();
        try {
            ResultSet rs = stm.executeQuery(sqlBuscaEnvolvidos);
            if (rs.next()) {
                //Processar, do jeito que você já fez      

                ID_Envolvido = Integer.parseInt(rs.getString("ID_ENV"));
                //System.out.println(ID_Envolvido + "eeeeeeeeeeeeeee"+envolvidos);
                rs.close();

            } else {
                //Não achou o evento vai inserir e buscar novamente 
                rs.close();
                System.out.println("Não achou");
                //try (Connection cn = (Connection) new Conexao().conectar()) {
                try (Connection cn = (Connection) new ConexaoLocal().conectar()) {
                    String sqlGrava = "INSERT INTO hc_envolv  VALUES  (null,'" + envolvidos.toUpperCase() + "'," + "'0'" + ");";//
                    Statement stmi = null;
                    try {
                        stmi = (Statement) cn.createStatement();
                    } catch (SQLException ex) {
                    }
                    int rsEnv=0;
                    try {
                        rsEnv = stm.executeUpdate(sqlGrava);
                    } catch (SQLException ex) {
                    }
                }
            }
        } catch (SQLException e) {
        }
        return ID_Envolvido;
    }

    private Integer buscaEspecificacao(String especificacao) throws SQLException {
        Integer ID_Especificacao = 0;
        //Integer ID_EVTs;
        //Connection cn = (Connection) new Conexao().conectar();
        Connection cn = (Connection) new ConexaoLocal().conectar();
        String sqlBuscaEspecificacao = "Select hc_especif.ID_ESP from hc_especif where hc_especif.NOME_ESP= " + "'" + especificacao + "'";
        java.sql.Statement stm = cn.createStatement();
        try {
            ResultSet rs = stm.executeQuery(sqlBuscaEspecificacao);
            if (rs.next()) {
                //Processar, do jeito que você já fez      
                ID_Especificacao = Integer.parseInt(rs.getString("ID_ESP"));
                //System.out.println(ID_Especificacao + "eeeeeeeeeeeeeee");
                cn.close();
            } else {
                //Não achou o evento vai inserir e buscar novamente 
                rs.close();
                System.out.println("Não achou");
                //try (Connection cnEsp = (Connection) new Conexao().conectar()) {
                try (Connection cnEsp = (Connection) new ConexaoLocal().conectar()) {
                    String sqlGrava = "INSERT INTO hc_especif  VALUES  (null,'" +especificacao.toUpperCase() + "');";//
                    Statement stmi = null;
                    try {
                        stmi = (Statement) cnEsp.createStatement();
                    } catch (SQLException ex) {
                    }
                    int rsESP = 0;
                    try {
                        rsESP = stm.executeUpdate(sqlGrava);
                    } catch (SQLException ex) {
                    }
                }
            }
        } catch (SQLException e) {
        }
        return ID_Especificacao;
    }
}

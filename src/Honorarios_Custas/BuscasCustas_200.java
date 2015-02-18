/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Honorarios_Custas;

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
import java.util.Date;

/**
 *
 * @author f7057419
 */
public class BuscasCustas_200 {

    /**
     * @param args the command line arguments
     */
    private String usuario;
    private String senha;

    public BuscasCustas_200(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    public void capturaInfos() throws IOException, InterruptedException, ParseException {

        //CONFIGURAR PROXY
        Proxy proxy = new Proxy();

        proxy.setProxyAutoconfigUrl("http://intranet.bb.com.br/firefox_proxy.pac");
        //  proxy.setProxyAutoconfigUrl("cache.bb.com.br");
        proxy.setSocksUsername(usuario);
        proxy.setSocksPassword(senha);

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
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
        //  WebElement codigo = driver.findElement(By.id("entrar"));
        WebElement entrar = driver.findElement(By.id("entrar"));

        userName.sendKeys(usuario);
        passWord.sendKeys(senha);

        //     String cod = JOptionPane.showInputDialog(null, "Digite o codigo captcha para entrar");
        //  codigo.sendKeys(cod);
        entrar.click();

        String testeEncontarTexto = "Você está aqui:";
//selenium.isTextPresent(testeEncontarTexto);   
        selenium.isElementPresent(testeEncontarTexto);
//selenium.getTable(id="pesquisarConferenciaLiberacaoForm:divDataTabletableContratosPrimeiroNivel")

    //Acessar Custas_200   
    driver.findElement(By.id("Financeiro")).click();
    driver.findElement(By.id("Custos")).click();
    driver.findElement(By.id("Despacho da Efetivação")).click();
    driver.findElement(By.id("custoForm:categoriaCustoDecorate:categoriaCustoListMenu")).click();     
   // System.out.println(driver.findElement(By.id("custoForm:categoriaCustoDecorate:categoriaCustoListMenu")).getText());
    new Select(driver.findElement(By.id("custoForm:categoriaCustoDecorate:categoriaCustoListMenu"))).selectByVisibleText("Custas de Condução");
 //  Thread.sleep(3000);
    
    
       
  driver.findElement(By.id("custoForm:btPesquisar")).click();
  new Select(driver.findElement(By.id("custoForm:categoriaCustoDecorate:categoriaCustoListMenu"))).selectByVisibleText("Custas de Condução");
  driver.findElement(By.id("custoForm:btPesquisar")).click();
    
    Thread.sleep(1000);

//  Dados da tabela
        //NÚMERO DE PÁGINAS
        String numeroPaginas = driver.findElement(By.id("custoForm:resultadoDataTable:j_id414")).getText();
        numeroPaginas = numeroPaginas.replaceAll("páginas", "");
        //SEPARA XX/XX  TOTAL DE PÁGINAS      
        String s[] = numeroPaginas.split("/");
        //System.out.println(s[0]);  
        //System.out.println(s[1]);   
        String totaldePaginas = (s[1]).trim();
        int paginas;
        paginas = Integer.parseInt(totaldePaginas);
        //--      System.out.println("páginas " + paginas);
        //NÚMERO DE linhas
       String linhas = driver.findElement(By.id("custoForm:divContador")).getText();
       System.out.println(linhas);
     //   String linha = driver.findElement(By.id("custosForm:j_id524")).getText();
         String L[] = linhas.split("de");   
       System.out.println(L[1]);  
        int Linhas;
        int Linhainicio = 0;
        Linhas = Integer.parseInt(L[1]);
        //--       System.out.println("Linhas " + Linhas);

        List<Custas_200> lista = new ArrayList<>();
        // List<Honorario> lista = new ArrayList<Honorario>();

        FileOutputStream arquivo;
        arquivo = new FileOutputStream("c://temp/CUSTAS_200.txt");

        PrintStream printStream = new PrintStream(arquivo);


//Pega o total de páginas e pega os dados da tabela n vezes
        for (int i = 0; i < paginas; i++) {
            //--      System.out.println("Página :" + i);
            for (int e = 0; e < 10; e++) {
                Custas_200 custas = new Custas_200();
                //--  System.out.println("Linha: " + Linhainicio + " de : " + Linhas);                
                //Campo EVENTO da tabela 
                String npj = driver.findElement(By.id("custoForm:resultadoDataTable:"+ Linhainicio + ":colBBJUR")).getText();
                //--     System.out.println("O evento da linha " + Linhainicio + " é: " + eventoHonorarios);                
                custas.setNpj(npj);
                //Campo DATA da tabela
                String envolvido = driver.findElement(By.id("custoForm:resultadoDataTable:"+ Linhainicio + ":j_id332" )).getText();
                custas.setEnvolvido(envolvido);
                //  honorario.setDataHonorario(formato2.format(formato1.parse(dataHonorarios)));
                //Campo NPJ da tabela
                String especificacao = driver.findElement(By.id("custoForm:resultadoDataTable:"+ Linhainicio + ":j_id334" )).getText();
                //--        System.out.println("O npj da linha " + Linhainicio + " é: " + npjHonorarios);
                custas.setEspecificacao((especificacao));
                //Campo CONTRATADO da tabela
                String estado = driver.findElement(By.id("custoForm:resultadoDataTable:"+ Linhainicio + ":j_id336" )).getText();
                //--       System.out.println("O contratado da linha " + Linhainicio + " é: " + contratadoHonorarios);
                custas.setEstado((estado));
                //Campo MODO_de_REMUNERAÇÂO da tabela 
                String operadora = driver.findElement(By.id("custoForm:resultadoDataTable:"+ Linhainicio + ":j_id370" )).getText();
                //System.out.println(modoDeRemuneracaoHonorarios);
                custas.setOperadora(operadora);
                //Campo VALOR da tabela 
                String liberadora = driver.findElement(By.id("custoForm:resultadoDataTable:"+ Linhainicio + ":colDependenciaLiberadoraDecorate:j_id381" )).getText();
                //System.out.println(modoDeRemuneracaoHonorarios);
                custas.setLiberadora(liberadora);     
                
                String valor = driver.findElement(By.id("custoForm:resultadoDataTable:"+ Linhainicio + ":j_id402")).getText();
                //--       System.out.println("O valor da linha " + Linhainicio + " é: " + valorHonorarios);

                custas.setValor(valor);         //Campo SITUAÇÂO da tabela 
             
                Linhainicio = Linhainicio + 1;

                lista.add(custas);
                printStream.println(custas.getNpj() + " ; " + custas.getNpj() + " ; " + custas.getEnvolvido() + " ; " + custas.getEspecificacao() + " ; " + custas.getEstado() + " ; " + " ; " + custas.getOperadora() + " ; "+ " ; " + custas.getLiberadora() + " ; "  + custas.getValor());


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
                Thread.sleep(3000);

                //  String testeEncontarTexto2 = "Novidades da Intranet";
                //  selenium.isElementPresent(testeEncontarTexto2);             
                //driver.manage().timeouts().pageLoadTimeout( 100,SECONDS);
                //Espera o elemento na tela
                //         WebDriverWait wait = new WebDriverWait(driver, 60000);
                //        WebElement o_element = wait.until(ExpectedConditions.elementToBeClickable(By.id("pesquisarConferenciaLiberacaoForm:dataTabletableContratosPrimeiroNivel:j_id317")));

                //       String testeEncontarTexto2 = "Você está aqui:";
//selenium.isTextPresent(testeEncontarTexto);   
                //       selenium.isElementPresent(testeEncontarTexto2);
            }
        }

        driver.close();
        JOptionPane.showMessageDialog(null, "ARQUIVO COM OS DADOS GRAVADOS EM:   C:/TEMP/CUSTAS_200.txt ");
       // System.exit(0);
    }
}

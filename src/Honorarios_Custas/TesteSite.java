package Honorarios_Custas;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class TesteSite {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  //@Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
   baseUrl = "https://login.intranet.bb.com.br/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

private String usuario;
private String senha; 

public TesteSite(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

  @Test
  public void testEJudi() {//throws Exception {
       
      
      /*/CONFIGURAR PROXY

        Proxy proxy = new Proxy();

        proxy.setProxyAutoconfigUrl("http://intranet.bb.com.br/firefox_proxy.pac");
      //  proxy.setProxyAutoconfigUrl("cache.bb.com.br");
        proxy.setSocksUsername(usuario);
        proxy.setSocksPassword(senha);

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setCapability(CapabilityType.PROXY, proxy);
        
        //Abre o firefox
        WebDriver driver = new FirefoxDriver(capabilities);*/

   
      
      
    driver.get(baseUrl + "/distAuth/UI/Login?ForceAuth=true&goto=http://portal.intranet.bb.com.br/wps/myportal/intranet");
    driver.findElement(By.id("IDToken1")).clear();
    driver.findElement(By.id("IDToken1")).sendKeys(usuario);
    driver.findElement(By.id("IDToken2")).clear();
    driver.findElement(By.id("IDToken2")).sendKeys(senha);
    driver.findElement(By.id("entrar")).click();
    
    driver.findElement(By.xpath("(//a[contains(text(),'Portal Jurídico')])[2]")).click();
    
    
    
    driver.findElement(By.id("chave")).clear();
    driver.findElement(By.id("chave")).sendKeys(usuario);
    driver.findElement(By.id("senha")).clear();
    driver.findElement(By.id("senha")).sendKeys(senha);
    driver.findElement(By.id("entrar")).click();
    driver.findElement(By.id("Terceirização")).click();
    driver.findElement(By.id("Pagamentos a efetuar")).click();
    driver.findElement(By.id("Conferência de eventos para liberação de pagamentos")).click();
    driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu")).click();
    new Select(driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:modeloContratoDecorate:modeloContratoListMenu"))).selectByVisibleText("EDITAL 2008/0425");
    driver.findElement(By.id("pesquisarConferenciaLiberacaoForm:btPesquisar")).click();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}

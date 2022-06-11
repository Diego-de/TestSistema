import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SystemTestSelenium {

    WebDriver web;

    @BeforeEach
    public void instanciando(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        web = new ChromeDriver();
    }

    @Test
    public void findElementbyIDTeste(){
        web.get("https://www.amazon.com.br/ref=nav_logo");
        WebElement botao = web.findElement(
                By.id("nav-search-submit-button"));
        botao.click();
        Assertions.assertEquals("https://www.amazon.com.br/",web.getCurrentUrl());
    }

    @Test
    public void findElementByXpathTeste(){
        web.get("https://www.amazon.com.br/ref=nav_logo");
        WebElement botao = web.findElement(
                By.xpath("//*[@id=\"nav-search-submit-button\"]"));
        botao.click();
        Assertions.assertEquals("https://www.amazon.com.br/",web.getCurrentUrl());
    }

    @Test
    public void sendKeysTeste(){
        web.get("https://www.amazon.com.br/ref=nav_logo");
        WebElement pesquisa = web.findElement(By.id("twotabsearchtextbox"));
        pesquisa.sendKeys("espelho");
        pesquisa.submit();
        Assertions.assertEquals("https://www.amazon.com.br/s?k=espelho&__mk_pt_BR=%C3%85M%C3%85%C5%BD%C3%95%C3%91&ref=nb_sb_noss",web.getCurrentUrl());
    }

    @Test
    public void moveToElementTestTrue(){
        web.get("https://www.amazon.com.br/ref=nav_logo");
        Actions action = new Actions(web);
        WebElement cursor = web.findElement(By.id("nav-search-submit-button"));
        action.moveToElement(cursor).build().perform();
        Assertions.assertTrue(web.getPageSource().contains(cursor.getAttribute("type")));
    }

    @Test
    public void moveToElementFalse(){
        web.get("https://www.amazon.com.br/ref=nav_logo");
        Actions action = new Actions(web);
        WebElement cursor = web.findElement(By.id("nav-logo-sprites"));
        action.moveToElement(cursor).build().perform();
        Assertions.assertFalse(web.getPageSource().contains(cursor.getAttribute("href")));
    }

    @Test
    public void findElementsByXpathTeste(){

        web.get("https://www.amazon.com.br/ref=nav_logo");

        WebElement lista = web.findElement(By.xpath("//*[@id=\"a-page\"]/div[2]/div[2]/div[1]"));
        List<WebElement> elements = lista.findElements(By.xpath("./div"));

        Assertions.assertEquals(8, elements.size());
    }

    @Test
    public void findElementsByXpathWithSelectTeste(){
        web.get("https://www.amazon.com.br/ref=nav_logo");

        WebElement lista = web.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]"));
        Select select = new Select(lista);
        select.selectByIndex(1);
        List<WebElement> elements = lista.findElements(By.xpath("./option"));
        Assertions.assertEquals(36, elements.size());
    }



    @AfterEach
    public void fechandoPaginas(){
        web.close();
    }

}

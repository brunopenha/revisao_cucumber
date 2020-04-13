package br.nom.penha.bruno.runners;

import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/resources/features/",
				glue = {"br.nom.penha.bruno.steps","br.nom.penha.bruno.config"}, 
		tags = {"@funcional"},
		plugin = {"pretty", "html:target/report-html", "json:target/report-json"},
		monochrome = true,
		snippets = SnippetType.CAMELCASE,
		dryRun = false,
		strict = false
		)
public class RunnerFuncionaisTest {

	@AfterClass
	public static void encerraDados() {

		System.setProperty("webdriver.chrome.driver", "/opt/selenium_drivers/chromedriver_linux64/chromedriver");
		System.setProperty("webdriver.gecko.driver", "/opt/selenium_drivers/geckodriver-v0.26.0-linux64/geckodriver");

		WebDriver chrome = new ChromeDriver();
		chrome.get("https://srbarriga.herokuapp.com");

		WebDriver firefox = new FirefoxDriver();
		firefox.get("https://srbarriga.herokuapp.com");

		chrome.findElement(By.id("email")).sendKeys("yokpok@yok.pok");
		firefox.findElement(By.id("email")).sendKeys("yokpok@yok.pok");
		chrome.findElement(By.id("senha")).sendKeys("yokpok");
		firefox.findElement(By.id("senha")).sendKeys("yokpok");
		chrome.findElement(By.tagName("button")).click();
		firefox.findElement(By.tagName("button")).click();

		// Elimino a conta inserida
		chrome.findElement(By.linkText("Contas")).click();
		chrome.findElement(By.linkText("Listar")).click();
		chrome.findElement(By.xpath("//*[@id=\"tabelaContas\"]/tbody/tr[1]/td[2]/a[2]/span")).click();
		firefox.findElement(By.linkText("Contas")).click();
		firefox.findElement(By.linkText("Listar")).click();
		firefox.findElement(By.xpath("//*[@id=\"tabelaContas\"]/tbody/tr[1]/td[2]/a[2]/span")).click();

		chrome.quit();
		firefox.quit();

	}

}

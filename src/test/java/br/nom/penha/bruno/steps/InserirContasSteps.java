package br.nom.penha.bruno.steps;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.Quando;


public class InserirContasSteps {

	WebDriver chrome;
	WebDriver firefox;
	
	@Dado("^que desejo adicionar uma conta$")
	public void queDesejoAdicionarUmaConta() throws Throwable {
		queEstouAcessandoAAplicação();
		informarOUsuário("yokpok@yok.pok");
		aSenha("yokpok");
		selecionarEntrar();
		visualizoAPáginaInicial();
		selecionoContas();
		selecionoAdicionar();
		
	}

	@Quando("^adiciono a conta \"([^\"]*)\"$")
	public void adicionoAConta(String arg1) throws Throwable {
		informoAConta(arg1);
		selecionoSalvar();
	}

	@Dado("^que estou acessando a aplicação$")
	public void queEstouAcessandoAAplicação() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "/opt/selenium_drivers/chromedriver_linux64/chromedriver");
		System.setProperty("webdriver.gecko.driver", "/opt/selenium_drivers/geckodriver-v0.26.0-linux64/geckodriver");

		chrome = new ChromeDriver();
		chrome.get("https://srbarriga.herokuapp.com");

		firefox = new FirefoxDriver();
		firefox.get("https://srbarriga.herokuapp.com");
	}

	@Quando("^informar o usuário \"([^\"]*)\"$")
	public void informarOUsuário(String arg1) throws Throwable {
		chrome.findElement(By.id("email")).sendKeys(arg1);
		firefox.findElement(By.id("email")).sendKeys(arg1);
	}

	@Quando("^a senha \"([^\"]*)\"$")
	public void aSenha(String arg1) throws Throwable {
		chrome.findElement(By.id("senha")).sendKeys(arg1);
		firefox.findElement(By.id("senha")).sendKeys(arg1);
	}

	@Quando("^selecionar entrar$")
	public void selecionarEntrar() throws Throwable {
		chrome.findElement(By.tagName("button")).click();
		firefox.findElement(By.tagName("button")).click();
	}

	@Então("^visualizo a página inicial$")
	public void visualizoAPáginaInicial() throws Throwable {
		// div[@class='alert alert-success']
		String textoChrome = chrome.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String textoFF = firefox.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Bem vindo, yokpok!", textoChrome);
		Assert.assertEquals("Bem vindo, yokpok!", textoFF);
	}

	@Quando("^seleciono Contas$")
	public void selecionoContas() throws Throwable {
		chrome.findElement(By.linkText("Contas")).click();
		firefox.findElement(By.linkText("Contas")).click();
	}

	@Quando("^seleciono Adicionar$")
	public void selecionoAdicionar() throws Throwable {
		chrome.findElement(By.linkText("Adicionar")).click();
		firefox.findElement(By.linkText("Adicionar")).click();
	}

	@Quando("^informo a conta \"([^\"]*)\"$")
	public void informoAConta(String arg1) throws Throwable {
		chrome.findElement(By.id("nome")).sendKeys(arg1);
		if(!arg1.isEmpty()) {
			firefox.findElement(By.id("nome")).sendKeys(arg1 + "_firefox");
		}
	}

	@Então("^recebo a mensagem \"([^\"]*)\"$")
	public void receboAMensagem(String arg1) throws Throwable {
		String textoChrome = chrome.findElement(By.xpath("//div[starts-with(@class,'alert alert-')]")).getText();
		Assert.assertEquals(arg1, textoChrome);

		String textoFF = firefox.findElement(By.xpath("//div[starts-with(@class,'alert alert-')]")).getText();
		Assert.assertEquals(arg1, textoFF);
		
	}

	@Quando("^seleciono Salvar$")
	public void selecionoSalvar() throws Throwable {
		chrome.findElement(By.tagName("button")).click();
		firefox.findElement(By.tagName("button")).click();
	}

	@Então("^a conta é inserida com sucesso$")
	public void aContaÉInseridaComSucesso() throws Throwable {
		String textoChrome = chrome.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Conta adicionada com sucesso!", textoChrome);

		String textoFF = firefox.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		Assert.assertEquals("Conta adicionada com sucesso!", textoFF);

		// Elimino a conta inserida
		chrome.findElement(By.linkText("Contas")).click();
		chrome.findElement(By.linkText("Listar")).click();
		chrome.findElement(By.xpath("//*[@id=\"tabelaContas\"]/tbody/tr[1]/td[2]/a[2]/span")).click();
		firefox.findElement(By.linkText("Contas")).click();
		firefox.findElement(By.linkText("Listar")).click();
		firefox.findElement(By.xpath("//*[@id=\"tabelaContas\"]/tbody/tr[1]/td[2]/a[2]/span")).click();

	}

	@Então("^sou notificado de que o nome da conta é obrigatório$")
	public void souNotificadoDeQueONomeDaContaÉObrigatório() throws Throwable {
		String textoChrome = chrome.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		Assert.assertEquals("Informe o nome da conta", textoChrome);

		String textoFF = firefox.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		Assert.assertEquals("Informe o nome da conta", textoFF);
	}

	@Então("^sou notificado de que já existe uma conta com esse nome$")
	public void souNotificadoDeQueJáExisteUmaContaComEsseNome() throws Throwable {
		String textoChrome = chrome.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		Assert.assertEquals("Já existe uma conta com esse nome!", textoChrome);

		String textoFF = firefox.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		Assert.assertEquals("Já existe uma conta com esse nome!", textoFF);

		// Elimino a conta inserida
		chrome.findElement(By.linkText("Contas")).click();
		chrome.findElement(By.linkText("Listar")).click();
		chrome.findElement(By.xpath("//*[@id=\"tabelaContas\"]/tbody/tr[1]/td[2]/a[2]/span")).click();
		firefox.findElement(By.linkText("Contas")).click();
		firefox.findElement(By.linkText("Listar")).click();
		firefox.findElement(By.xpath("//*[@id=\"tabelaContas\"]/tbody/tr[1]/td[2]/a[2]/span")).click();

	}

	@Before
	public void inicia() {
		System.out.println("Iniciando cenário");
	}
	
	@After(order = 1, value = "@funcional")
	public void tiraFotoTela(Scenario cenario) {
		File arquivo = ((TakesScreenshot)chrome).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(arquivo, new File("target/"+ cenario.getName() + ".jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@After(order = 0, value = "@funcional")
	/**
	 * Não faz parte do cenário Encerra o navegador após o teste
	 */
	public void fecharNavegador(Scenario cenario) {
		if (chrome != null) {
			chrome.quit();
		}
		if (firefox != null) {
			firefox.quit();
		}

	}

}

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAmbiente {

	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "/opt/selenium_drivers/chromedriver_linux64/chromedriver");
		System.setProperty("webdriver.gecko.driver", "/opt/selenium_drivers/geckodriver-v0.26.0-linux64/geckodriver");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://srbarriga.herokuapp.com");
		
		WebDriver mozilla = new FirefoxDriver();
		mozilla.get("https://srbarriga.herokuapp.com");
		
	}
}

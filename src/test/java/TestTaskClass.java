import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestTaskClass {

	@Test
	public void findSmth() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssendazhy\\testTaskGUI\\drivers\\chromedriver.exe");

		ChromeDriver driver = new ChromeDriver();
		driver.get("https://yandex.ru/");
		driver.findElement(By.xpath("//input[@id = 'text']"))
				.sendKeys("lalala");
		driver.findElement(By.xpath("//div[@class='search2__button']/button")).click();

		List<WebElement> elements = driver
				.findElements(By.xpath("//div[@id = 'search-result-aside']//div[contains(text(), 'результат')]"));

		if (elements.size() != 0) {
			String resultString = elements.get(0).getText();
			List<String> list = Arrays.asList(resultString.split(" "));

			//рез-т запроса, если он больше хотя бы 1, может быть двух видов:
			// "нашлось n тыс./млн(и тд) результатов" или "Нашёлся n результат"
			// проверяем только 1й, тк со вторым все гуд
			if (list.size() < 4) {
				int amount = Integer.parseInt(list.get(1));
				Assert.assertTrue("Запрос выдал меньше 1000 результатов", amount >= 1000);
			}
		} else {
			System.out.println("Запрос выдал меньше 1000 результатов");
		}
		driver.quit();
	}

}

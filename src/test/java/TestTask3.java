package otustest.automation.webdriver;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.junit.Assert.assertTrue;

public class TestTask3 {

    private static final Logger logger = LogManager.getLogger(TestTask3.class);
    private static WebDriver driver;

    @BeforeClass
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver"); // Укажи путь к драйверу

        // Настраиваем браузер в полноэкранном режиме
        ChromeOptions options = new ChromeOptions().addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testDynamicMessage() {
        try {
            driver.get("https://otus.home.kartushin.su/training.html");

            // Заполняем форму
            driver.findElement(By.id("nameField")).sendKeys("Игорь Иванов");
            driver.findElement(By.id("emailField")).sendKeys("igor@example.com");
            driver.findElement(By.id("submitFormButton")).click();

            // Ждем появления элемента и проверяем сообщение
            String dynamicMessage = driver.findElement(By.id("dynamicMessage")).getText();
            boolean isCorrect = dynamicMessage.contains("Имя: Игорь Иванов") &&
                    dynamicMessage.contains("Email: igor@example.com");

            if (!isCorrect) {
                throw new AssertionError("Динамическое сообщение неправильное.");
            }
            logger.info("Тест пройден успешно");
        } catch (NoSuchElementException e) {
            logger.error("Элемент не найден.", e);
        } catch (AssertionError e) {
            logger.error(e.getMessage(), e);
        }
    }

    @AfterClass
    public static void tearDown() throws Exception {
        if (driver != null) {
            driver.quit(); // Закрываем браузер после завершения теста
        }
    }
}
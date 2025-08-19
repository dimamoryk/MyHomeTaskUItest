package otustest.automation.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestTask2 {

    private static final Logger logger = LogManager.getLogger(TestTask2.class);
    private static WebDriver driver;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup(); // Менеджер драйверов настроит chrome-drive автоматически
        ChromeOptions options = new ChromeOptions().addArguments("--kiosk"); // Полноэкранный режим
        driver = new ChromeDriver(options);
    }

    @Test
    public void testOpenModalWindow() {
        driver.get("https://otus.home.kartushin.su/training.html");
        driver.findElement(By.id("openModalButton")).click();

        // Проверяем наличие модального окна
        boolean modalDisplayed = driver.findElement(By.cssSelector(".modal.show")).isDisplayed();
        if (!modalDisplayed) throw new AssertionError("Модальное окно не открыто");
        logger.info("Тест пройден успешно");
    }
}




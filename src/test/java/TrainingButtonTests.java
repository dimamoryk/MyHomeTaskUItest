package otustest.automation.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TrainingButtonTests {

    private static final Logger logger = LogManager.getLogger(otustest.automation.webdriver.TrainingButtonTests.class);
    private WebDriver driver;

    @BeforeEach
    void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions().addArguments("--kiosk"); // Полноэкранный режим
        driver = new ChromeDriver(options);
    }

    @Test
    public void testOpenModalWindow() {
        driver.get(System.getProperty("app.url", "https://otus.home.kartushin.su/training.html"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".modal.show")));
        driver.findElement(By.id("openModalButton")).click();

        // Проверяем наличие модального окна
        boolean modalDisplayed = driver.findElement(By.cssSelector(".modal.show")).isDisplayed();
        if (!modalDisplayed) throw new AssertionError("Модальное окно не открыто");
        logger.info("Тест пройден успешно");
    }

@AfterEach
void tearDown() {
    if (driver != null) {
        driver.quit();
    }
}




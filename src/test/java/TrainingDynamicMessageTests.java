package otustest.automation.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TrainingDynamicMessageTests {

    private static final Logger logger = LogManager.getLogger(otustest.automation.webdriver.TrainingDynamicMessageTests.class);
    private WebDriver driver;

    @BeforeAll
    void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "/Users/yourusername/bin/chromedriver");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Настраиваем браузер в полноэкранном режиме
        ChromeOptions options = new ChromeOptions().addArguments("--start-maximized");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testDynamicMessage() {

        driver.get(System.getProperty("app.url", "https://otus.home.kartushin.su/training.html"));


        // Заполняем форму
        driver.findElement(By.id("nameField")).sendKeys("Игорь Иванов");
        driver.findElement(By.id("emailField")).sendKeys("igor@example.com");
        driver.findElement(By.id("submitFormButton")).click();

        // Используем ожидание элемента
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("dynamicMessage")));

        // Проверяем сообщение
        String dynamicMessage = driver.findElement(By.id("dynamicMessage")).getText();
        Assertions.assertTrue(dynamicMessage.contains("Имя: Игорь Иванов"), "Проверка имени провалилась");
        Assertions.assertTrue(dynamicMessage.contains("Email: igor@example.com"), "Проверка email провалилась");
        logger.info("Тест пройден успешно");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }


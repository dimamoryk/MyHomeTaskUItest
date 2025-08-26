package otustest.automation.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TrainingPageTests {

    private static final Logger logger = LogManager.getLogger(otustest.automation.webdriver.TrainingPageTests.class);
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions().addArguments("--headless=new");
        this.driver = new ChromeDriver(options);
    }

    @Test
    public void testInputField() throws NoSuchElementException {
        driver.get(System.getProperty("app.url", "https://otus.home.kartushin.su/training.html"));

        driver.findElement(By.id("inputText")).sendKeys("ОТУС");

        String actualValue = driver.findElement(By.id("inputText")).getAttribute("value");

        Assertions.assertEquals("ОТУС", actualValue, "Текст не совпадает!");
        logger.info("Тест пройден успешно");
    }
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

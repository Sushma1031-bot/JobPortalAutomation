


package tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class JobPortalTest {

    WebDriver driver;
    WebDriverWait wait;

    String websiteUrl =
        "http://127.0.0.1:5500/index.html";

    @BeforeEach
    public void setUp() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(
            driver, Duration.ofSeconds(20)
        );
    }

    @AfterEach
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }

    // TEST 1: Homepage Verification
    @Test
    public void verifyHomepageTitle() {

        driver.get(websiteUrl);

        String title = driver.getTitle();

        assertTrue(
            title.contains("CareerConnect"),
            "Homepage title verification failed"
        );

        System.out.println(
            "Homepage test passed!"
        );
    }

    // TEST 2: Job Search Functionality
    
    @Test
    public void verifyJobSearch() {

    driver.get(websiteUrl);

    WebElement searchBox = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.id("jobSearch")
        )
    );

    // Wait until the initial job cards load
    wait.until(d ->
        d.findElements(
            By.cssSelector("[data-testid='job-card']")
        ).size() > 0
    );

    // Search for a job that does not exist
    searchBox.sendKeys("zzzz-no-match-12345");

    // Wait for the no-results message
    wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.id("noJobs")
        )
    );

    // Verify that no job cards are displayed
    int noResults = driver.findElements(
        By.cssSelector("[data-testid='job-card']")
    ).size();

    assertEquals(
        0,
        noResults,
        "Invalid search should return zero jobs"
    );

    // Clear the search and trigger the input event
    ((JavascriptExecutor) driver).executeScript(
        "arguments[0].value = ''; " +
        "arguments[0].dispatchEvent(" +
        "new Event('input', {bubbles:true}));",
        searchBox
    );

    // Wait until job cards return
    wait.until(d ->
        d.findElements(
            By.cssSelector("[data-testid='job-card']")
        ).size() > 0
    );

    int jobsAfterClear = driver.findElements(
        By.cssSelector("[data-testid='job-card']")
    ).size();

    assertTrue(
        jobsAfterClear > 0,
        "Jobs should return after clearing search"
    );

    System.out.println(
        "Job search test passed! Jobs found: "
        + jobsAfterClear
    );
   }
 
    // TEST 3: User Registration
    @Test
    public void verifyUserRegistration() {

        driver.get(websiteUrl);

        // Clear old registration data
        ((JavascriptExecutor) driver).executeScript(
            "window.localStorage.clear();"
        );

        driver.navigate().refresh();

        // Open account page
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("nav-account")
            )
        ).click();

        // Generate unique email
        String testEmail =
            "student" + System.currentTimeMillis()
            + "@example.com";

        // Enter registration details
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("registerName")
            )
        ).sendKeys("Test Student");

        driver.findElement(
            By.id("registerEmail")
        ).sendKeys(testEmail);

        driver.findElement(
            By.id("registerPassword")
        ).sendKeys("TestPass123");

        // Submit registration
        driver.findElement(
            By.id("registerSubmit")
        ).click();

        // Verify successful registration
        wait.until(
            ExpectedConditions.textToBePresentInElementLocated(
                By.id("welcomeMessage"),
                "Test Student"
            )
        );

        assertTrue(
            driver.findElement(
                By.id("welcomeMessage")
            ).getText().contains("Test Student"),
            "User registration failed"
        );

        System.out.println(
            "User registration test passed!"
        );
    }

    // TEST 4: User Login
    @Test
    public void verifyUserLogin() {

        driver.get(websiteUrl);

        // Clear old accounts
        ((JavascriptExecutor) driver).executeScript(
            "window.localStorage.clear();"
        );

        driver.navigate().refresh();

        // Open account page
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("nav-account")
            )
        ).click();

        // Create a test account
        String testEmail =
            "login" + System.currentTimeMillis()
            + "@example.com";

        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("registerName")
            )
        ).sendKeys("Login Student");

        driver.findElement(
            By.id("registerEmail")
        ).sendKeys(testEmail);

        driver.findElement(
            By.id("registerPassword")
        ).sendKeys("TestPass123");

        driver.findElement(
            By.id("registerSubmit")
        ).click();

        // Verify registration
        wait.until(
            ExpectedConditions.textToBePresentInElementLocated(
                By.id("welcomeMessage"),
                "Login Student"
            )
        );

        // Remove only current login session
        // Keep the registered user saved
        ((JavascriptExecutor) driver).executeScript(
            "window.localStorage.removeItem('jobportal_currentUser');"
        );

        driver.navigate().refresh();

        // Open account page again
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("nav-account")
            )
        ).click();

        // Wait for login form
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("loginEmail")
            )
        );

        // Enter login credentials
        driver.findElement(
            By.id("loginEmail")
        ).sendKeys(testEmail);

        driver.findElement(
            By.id("loginPassword")
        ).sendKeys("TestPass123");

        // Submit login
        driver.findElement(
            By.id("loginSubmit")
        ).click();

        // Verify successful login
        wait.until(
            ExpectedConditions.textToBePresentInElementLocated(
                By.id("welcomeMessage"),
                "Login Student"
            )
        );

        assertTrue(
            driver.findElement(
                By.id("welcomeMessage")
            ).getText().contains("Login Student"),
            "User login failed"
        );

        System.out.println(
            "User login test passed!"
        );
    }

    // TEST 5: Job Application
    @Test
    public void verifyJobApplication() {

        driver.get(websiteUrl);

        // Clear old demo data
        ((JavascriptExecutor) driver).executeScript(
            "window.localStorage.clear();"
        );

        driver.navigate().refresh();

        // Open account section
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("nav-account")
            )
        ).click();

        // Generate unique email
        String email =
            "applicant" + System.currentTimeMillis()
            + "@example.com";

        // Register test user
        wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("registerName")
            )
        ).sendKeys("Application Student");

        driver.findElement(
            By.id("registerEmail")
        ).sendKeys(email);

        driver.findElement(
            By.id("registerPassword")
        ).sendKeys("TestPass123");

        driver.findElement(
            By.id("registerSubmit")
        ).click();

        // Wait for registration to complete
        wait.until(
            ExpectedConditions.textToBePresentInElementLocated(
                By.id("welcomeMessage"),
                "Application Student"
            )
        );

        // Navigate to Jobs section
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.id("nav-jobs")
            )
        ).click();

        // Click Apply button for first job
        wait.until(
            ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-apply-job='JOB001']")
            )
        ).click();

        // Wait for application form
        WebElement nameField = wait.until(
            ExpectedConditions.visibilityOfElementLocated(
                By.id("applicantName")
            )
        );

        // Clear automatically filled details
        nameField.clear();
        nameField.sendKeys("Application Student");

        WebElement emailField = driver.findElement(
            By.id("applicantEmail")
        );

        emailField.clear();
        emailField.sendKeys(email);

        // Enter phone number
        driver.findElement(
            By.id("applicantPhone")
        ).sendKeys("9000000000");

        // Submit application
        driver.findElement(
            By.id("submitApplication")
        ).click();

        // Verify application was created
        wait.until(
            ExpectedConditions.numberOfElementsToBeMoreThan(
                By.cssSelector("[data-testid='application-card']"),
                0
            )
        );

        assertTrue(
            driver.findElements(
                By.cssSelector("[data-testid='application-card']")
            ).size() > 0,
            "Job application was not submitted"
        );

        System.out.println(
            "Job application test passed!"
        );
    }

// TEST 6: Application Status Verification
    @Test
    public void verifyApplicationStatus() {

    driver.get(websiteUrl);

    // Clear previous demo data
    ((JavascriptExecutor) driver).executeScript(
        "window.localStorage.clear();"
    );

    driver.navigate().refresh();

    // Open account section
    wait.until(ExpectedConditions.elementToBeClickable(
        By.id("nav-account")
    )).click();

    // Register a demo user
    String email = "status" + System.currentTimeMillis()
        + "@example.com";

    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.id("registerName")
    )).sendKeys("Status Student");

    driver.findElement(By.id("registerEmail"))
        .sendKeys(email);

    driver.findElement(By.id("registerPassword"))
        .sendKeys("TestPass123");

    driver.findElement(By.id("registerSubmit")).click();

    // Wait for successful registration
    wait.until(ExpectedConditions.textToBePresentInElementLocated(
        By.id("welcomeMessage"),
        "Status Student"
    ));

    // Navigate to Jobs section
    wait.until(ExpectedConditions.elementToBeClickable(
        By.id("nav-jobs")
    )).click();

    // Click Apply button
    wait.until(ExpectedConditions.elementToBeClickable(
        By.cssSelector("[data-apply-job='JOB001']")
    )).click();

    // Wait for application form
    WebElement nameField = wait.until(
        ExpectedConditions.visibilityOfElementLocated(
            By.id("applicantName")
        )
    );

    // Enter applicant details
    nameField.clear();
    nameField.sendKeys("Status Student");

    WebElement emailField = driver.findElement(
        By.id("applicantEmail")
    );

    emailField.clear();
    emailField.sendKeys(email);

    driver.findElement(By.id("applicantPhone"))
        .sendKeys("9000000000");

    // Submit application
    driver.findElement(By.id("submitApplication")).click();

    // Verify application card appears
    wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
        By.cssSelector("[data-testid='application-card']"),
        0
    ));

    WebElement applicationCard = driver.findElement(
        By.cssSelector("[data-testid='application-card']")
    );

    assertTrue(
        applicationCard.isDisplayed(),
        "Application status was not displayed!"
    );

    System.out.println(
        "Application status verification test passed!"
    );
   }

}

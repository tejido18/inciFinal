package steps;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginWrongPassSteps {
  private WebDriver driver;
  //private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @When(value = "I try to login with wrong password")
  public void testUntitledTestCase() throws Exception {
    driver.get("http://192.168.99.100:8081/agentform");
    driver.findElement(By.id("authUsername")).click();
    driver.findElement(By.id("authUsername")).clear();
    driver.findElement(By.id("authUsername")).sendKeys("pacoo");
    driver.findElement(By.id("authPassword")).clear();
    driver.findElement(By.id("authPassword")).sendKeys("WrongPasswrod");
    driver.findElement(By.xpath("//button[@type='submit']")).click();
  }
  
  @Then(value = "I should see the error page")
  public void testUntitledTestCase2() throws Exception {
    if(driver.getCurrentUrl().equalsIgnoreCase("http://192.168.99.100:8081/agentform?error=true"))
    	System.out.println("Test Passed");
    else
    	System.out.println("Test Not Passed");
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}

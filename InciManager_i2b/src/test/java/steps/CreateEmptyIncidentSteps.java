package steps;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CreateEmptyIncidentSteps {
  private WebDriver driver;
  //private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Given(value = "I am a correct agent")
  public void testUntitledTestCase() throws Exception {
    driver.get("http://192.168.99.100:8081/agentform");
    driver.findElement(By.id("authUsername")).click();
    driver.findElement(By.id("authUsername")).clear();
    driver.findElement(By.id("authUsername")).sendKeys("sonny");
  }

  @When(value = "I login in the Incidence Manager")
  public void loggin()
  {
	  driver.findElement(By.id("authPassword")).clear();
	    driver.findElement(By.id("authPassword")).sendKeys("pass123");
	    driver.findElement(By.id("authPassword")).sendKeys(Keys.ENTER);
  }
  
  @Then(value = "I click on create an incident")
  public void click()
  {
	  driver.findElement(By.linkText("Create incident")).click();
  }
  
  @Then(value = "Then I dont fill the incident description")
  public void dontFill()
  {
	  driver.findElement(By.id("submitBtn")).click();
  }
  
  @Then(value = "I get an error in the form")
  public void getError()
  {
	  if(driver.getCurrentUrl().equalsIgnoreCase("http://192.168.99.100:8081/incident/create"))
		  System.out.println("Test Passed");
	  else
		  System.out.println("Test not Passed");
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
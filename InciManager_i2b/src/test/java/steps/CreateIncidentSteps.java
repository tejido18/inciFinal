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

public class CreateIncidentSteps {
  private WebDriver driver;
  //private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  
  
  @Given(value = "I am a correct agent")
  public void correct()
  {
	  driver.get("http://192.168.99.100:8081/agentform");
	  driver.findElement(By.id("authUsername")).click();
	  driver.findElement(By.id("authUsername")).clear();
	  driver.findElement(By.id("authUsername")).sendKeys("pacoo");
  }
  
  @When(value = "I login in the Incidence Manager")
  public void loggin()
  {
	  driver.findElement(By.id("authPassword")).clear();
	  driver.findElement(By.id("authPassword")).sendKeys("123456");
	  driver.findElement(By.id("authPassword")).sendKeys(Keys.ENTER);
  }
  
  @Then(value = "I click on create an incident")
  public void click()
  {
	  driver.findElement(By.linkText("Create incident")).click();
  }
  
  @Then(value = "I fill the incident description")
  public void fillInciForm() throws Exception 
  {    
    driver.findElement(By.id("inciName")).clear();
    driver.findElement(By.id("inciName")).sendKeys("New Incident");
    driver.findElement(By.id("latitude")).click();
    driver.findElement(By.id("latitude")).clear();
    driver.findElement(By.id("latitude")).sendKeys("45");
    driver.findElement(By.id("longitude")).click();
    driver.findElement(By.id("longitude")).clear();
    driver.findElement(By.id("longitude")).sendKeys("54");
    driver.findElement(By.id("tag0")).click();
    driver.findElement(By.id("tag0")).clear();
    driver.findElement(By.id("tag0")).sendKeys("Emergency");
    driver.findElement(By.id("propertyName0")).click();
    driver.findElement(By.id("propertyName0")).clear();
    driver.findElement(By.id("propertyName0")).sendKeys("Height");
    driver.findElement(By.id("propertyValue0")).clear();
    driver.findElement(By.id("propertyValue0")).sendKeys("1000 metres");
    driver.findElement(By.id("moreInfo")).click();
    driver.findElement(By.id("moreInfo")).clear();
    driver.findElement(By.id("moreInfo")).sendKeys("This is a test");
  }
  
  @Then(value = "I create the incident")
  public void createInci()
  {
	  driver.findElement(By.id("submitBtn")).click();
	  if(driver.getCurrentUrl().equalsIgnoreCase("http://192.168.99.100:8081/incidents"))
		  System.out.println("Test passed");
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

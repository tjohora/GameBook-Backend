///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package StepDefinitions;
//
//import io.cucumber.java.en.And;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.Select;
//
///**
// *
// * @author Tom
// */
//public class LoginSteps {
//    
//    private String baseUrl;
//    private WebDriver driver;
//    
//    @Given("^the user has already registered$")
//    public void openWebPageOnLoginForm(){
//        
//        
//        
//    }
//    
//    @When("^inputting their <username>$")
//    public String inputUsername(){
//       System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tom\\Desktop\\College_work\\SoftwareTesting\\chromedriver.exe");
//        driver = new ChromeDriver();
//        
//        baseUrl = "http://localhost:8080/Year3Project/test-resbeans.html";
//        
//        
//        driver.get(baseUrl);
//        driver.findElement(By.id("I1/user_11")).click();
//        driver.findElement(By.linkText("login")).click();
//        
//        driver.findElement(By.id("blobParam")).clear();
//        
//        driver.findElement(By.id("blobParam")).sendKeys("{\"userName\":\"Tom\",\"password\":\"password\"}");
//        
//        
//        driver.findElement(By.linkText("Test")).click();
//        String result = driver.findElement(By.id("rawContent")).getText();
//        
//        driver.quit();
//        return result;
//    }
//    
//    @And("^inputting their <password>$")
//    public void inputPassword(){
//        
//    }
//    
//    @Then("^user is recieves theyre user details$")
//    public void recieveUserDetails(){
//        
//    }
//}

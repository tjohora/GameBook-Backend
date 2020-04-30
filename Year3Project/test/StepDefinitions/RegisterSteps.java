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
//import static org.junit.Assert.assertEquals;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
///**
// *
// * @author Tom
// */
//public class RegisterSteps {
//    
//    private String baseUrl;
//    private WebDriver driver;
//    
//    @Given("^we are on the register page$")
//    public void openPage(){
//        
//    }
//    
//    @When("^inputting a <username> <password> <email> into text box$")
//    public String inputDetails(){
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tom\\Desktop\\College_work\\SoftwareTesting\\chromedriver.exe");
//        driver = new ChromeDriver();
//        
//        baseUrl = "http://localhost:8080/Year3Project/test-resbeans.html";
//        
//        
//        driver.get(baseUrl);
//        driver.findElement(By.id("I1/user_11")).click();
//        driver.findElement(By.linkText("register")).click();
//        
//        driver.findElement(By.id("blobParam")).clear();
//        
//        driver.findElement(By.id("blobParam")).sendKeys("{\"userName\":\"Tom\",\"password\":\"password\",\"email\":\"Tom@gmail.com\"}");
//        
//        
//        driver.findElement(By.linkText("Test")).click();
//        String result = driver.findElement(By.id("rawContent")).getText();
//        
//        driver.quit();
//        return result;
//        
//        
//        
//        
//    }
//    @And("^<password> $")
//    public void andMethod(){
//        
//    }
//    
//    @And("^<email> into text box$")
//    public void andMethod2(){
//        
//    }
//    
//    @Then("^i recieve a response from server$")
//    public String recieveDetails(){
//        return driver.findElement(By.id("rawContent")).getText();
//        
//    }
//}

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package StepDefinitions;
//
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
//public class PostSteps {
//    private String baseUrl;
//    private WebDriver driver;
//    
//    @Given("^we are on the Post page$")
//    public void onPostPage(){
//        
//    }
//    
//    @When("^inputting a post$")
//    public String inputPost(){
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Tom\\Desktop\\College_work\\SoftwareTesting\\chromedriver.exe");
//        driver = new ChromeDriver();
//        
//        baseUrl = "http://localhost:8080/Year3Project/test-resbeans.html";
//        
//        
//        driver.get(baseUrl);
//        driver.findElement(By.linkText("post")).click();
//        WebElement dropDownElement = driver.findElement(By.id("methodSel"));
//        Select dropDown = new Select(dropDownElement);
//        dropDown.selectByValue("POST(text/plain)[0]");
//        
//        
//        driver.findElement(By.id("blobParam")).clear();
//        
//        driver.findElement(By.id("blobParam")).sendKeys("{\"userId\":25,\"postHeader\":\"I posted this\",\"postContent\":\"Fly you fools\",\"media\":1}");
//        
//        
//        driver.findElement(By.linkText("Test")).click();
//        String result = driver.findElement(By.id("rawContent")).getText();
//        
//        driver.quit();
//        return result;
//    }
//    
//    @Then("^i recieve a response from the server$")
//    public void serverResponse(){
//    
//    }
//    
//}

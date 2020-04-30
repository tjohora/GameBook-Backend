///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package JUnit;
//
//import StepDefinitions.LoginSteps;
//import StepDefinitions.PostSteps;
//import StepDefinitions.RegisterSteps;
//import java.util.concurrent.TimeUnit;
//import org.junit.After;
//import org.junit.AfterClass;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Test;
//import static org.junit.Assert.*;
//import org.junit.Ignore;
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
//public class SeleniumJUnitTest {
//    
////    
//    public SeleniumJUnitTest() {
//    }
//    
//
//   
//    @Test
//    public void Register() {
//        RegisterSteps instance = new RegisterSteps();
//        
//        
//        String expectedResults = "false";
//        assertEquals(expectedResults,instance.inputDetails());
//    }
//
//    @Test
//    public void Login() {
//        LoginSteps instance = new LoginSteps();
//        
//        
//        String expectedResults = "{\"profileId\":25,\"userType\":1,\"userName\":\"Tom\",\"error\":\"\",\"userId\":27}";
//        assertEquals(expectedResults,instance.inputUsername());
//    }
//    
//    @Test
//    public void Post() {
//        PostSteps instance = new PostSteps();
//        
//        
//        String expectedResults = "true";
//        assertEquals(expectedResults,instance.inputPost());
//    }
//
//}

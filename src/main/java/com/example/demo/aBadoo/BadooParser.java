package com.example.demo.aBadoo;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BadooParser {

    public static void main(String[] args) {



        WebDriver driver = new ChromeDriver();
        driver.get("https://badoo.com/signin/");
        WebElement email = driver.findElement(By.name("email"));
        System.out.println(email.getText());
        System.out.println(email.getTagName());

       /* WebElement pass = driver.findElement(By.name("password"));
        WebElement post = driver.findElement(By.name("post"));
        email.sendKeys("a.j.lapin1@gmail.com");
        pass.sendKeys("team4rever00000");
        post.submit();
        System.out.println("-----------");
        for (int i = 0; i < 20; i++) {
            System.out.println("[]");
        }
        System.out.println("title = " +driver.getTitle().toString());
        for (int i = 0; i < 20; i++) {
            System.out.println("[]");
        }*/
        driver.quit();


    }

}

import com.beust.ah.A;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TestClass {
    ChromeDriver chromeDriver;
    EdgeDriver edgeDriver;
    @BeforeMethod
    public void Setup(){
        WebDriverManager.chromedriver().setup();
//        chromeDriver = new ChromeDriver();
//        chromeDriver.get("https://www.google.com.vn");
            WebDriverManager.edgedriver().setup();
            edgeDriver = new EdgeDriver();
            edgeDriver.manage().window().maximize();
            edgeDriver.get("https://gearvn.com/account/login");
    }
    @Test
    public void Run1(){
        edgeDriver.get("https://gearvn.com/account/login");
        sleep(5000);
        edgeDriver.findElement(By.id("moe-dontallow_button")).click();
//        sleep(100);
        edgeDriver.findElement(By.id("customer_email")).sendKeys("gnoud9901");
        edgeDriver.findElement(By.id("customer_password")).sendKeys("xM5BzUJWtaH@5hr");
        sleep(1000);
        edgeDriver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[3]/input")).click();
        sleep(3000);
        String eptURL = "https://gearvn.com/account";
        String actURL = edgeDriver.getCurrentUrl();
        Assert.assertEquals(actURL,eptURL);
    }
    /*@AfterMethod
    public void Cleanup(){
        //chromeDriver.quit();
        System.out.print("Aftermethod");
    }*/
    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }


    }

}

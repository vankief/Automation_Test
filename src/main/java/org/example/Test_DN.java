package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.*;

public class Test_DN {
    EdgeDriver edgeDriver;
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    XSSFWorkbook wb;
    XSSFSheet    sheet1;
    String email, pass;
    String ketquamongdoi;
    String status,actURL;
    int i;
    WebElement txt_email,txt_pass,btn_submit;
    @BeforeTest
    public void InitTest() throws IOException{
        System.out.println("Bat dau");
        WebDriverManager.edgedriver().setup();
        edgeDriver = new EdgeDriver();
        edgeDriver.get("https://gearvn.com");
        edgeDriver.manage().window().maximize();
        fileInputStream = new FileInputStream("D:\\Installed\\dangnhap.xlsx");
        wb = new XSSFWorkbook(fileInputStream);
        sheet1= wb.getSheetAt(0);
        i=3;
    }
    @BeforeMethod
    public void BeforeMethod(){
        //actURL="khong co gi";
        edgeDriver.get("https://gearvn.com/account/login");
        System.out.println("------------------------------");
        System.out.println("BeforeMethod");
        email = sheet1.getRow(i).getCell(5).getStringCellValue();
        System.out.println(email);
        pass = sheet1.getRow(i).getCell(6).getStringCellValue();
        System.out.println(pass);
        ketquamongdoi = sheet1.getRow(i).getCell(7).getStringCellValue();
        System.out.println(ketquamongdoi);
        status ="Fail";
    }
    @AfterMethod
    public void AfterMethod() throws IOException{
        System.out.println("AfterMothod "+status);
            XSSFCell cell = sheet1.getRow(i).getCell(9);
           // System.out.println("check cell: " + cell);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(status);
            XSSFCell cell1 =sheet1.getRow(i).getCell(8);
            //System.out.println("check cell1: " + cell1);
            cell1.setCellType(CellType.STRING);
            cell1.setCellValue(actURL);
            fileOutputStream = new FileOutputStream("D:\\Installed\\dangnhap.xlsx");
            wb.write(fileOutputStream);
            i+=3;
    }
    @Test
    public void Test1() throws InterruptedException{
        System.out.println("Test1");
        sleep(5000);
        edgeDriver.findElement(By.id("moe-dontallow_button")).click();
//        Alert alert = edgeDriver.switchTo().alert();
//        alert.accept();
        Find_element();
        txt_email.sendKeys(email);
        txt_pass.sendKeys(pass);
        sleep(500);
        btn_submit.click();
        actURL = edgeDriver.getCurrentUrl();
        Assert.assertEquals(ketquamongdoi,actURL);
        status = "Pass";

    }
    @Test
    public void Test2() throws InterruptedException{
        edgeDriver.navigate().back();
        edgeDriver.navigate().refresh();
        System.out.println("Test2");
        sleep(1000);
        Find_element();
        txt_email.clear();
        txt_pass.clear();
        sleep(300);
        txt_email.sendKeys(email);
        txt_pass.sendKeys(pass);
        sleep(500);
        btn_submit.click();
        actURL=edgeDriver.getCurrentUrl();
        Assert.assertEquals(ketquamongdoi,actURL);
        status = "Pass";

    }
    @Test
    public void Test3() throws InterruptedException{
        //edgeDriver.navigate().refresh();
        System.out.println("Test3");
        sleep(1000);
        Find_element();
        txt_email.clear();
        txt_pass.clear();
        txt_email.sendKeys(email);
        txt_pass.sendKeys(pass);
        sleep(100);
        btn_submit.click();
        actURL=edgeDriver.getCurrentUrl();
        Assert.assertEquals(ketquamongdoi,actURL);
        status = "Pass";
    }
    @Test
    public void Test4()throws InterruptedException{
        //edgeDriver.navigate().refresh();
        System.out.println("Test4");
        sleep(1000);
        Find_element();
        txt_email.clear();
        txt_pass.clear();
        txt_email.sendKeys(email);
        txt_pass.sendKeys(pass);
        sleep(300);
        btn_submit.click();
        sleep(5000);
        actURL = edgeDriver.getCurrentUrl();
        Assert.assertEquals(ketquamongdoi,actURL);
        status = "Pass";
    }
    @Test
    public void Test5() throws InterruptedException{
        //edgeDriver.navigate().refresh();
        System.out.println("Test5");
        sleep(1000);
        Find_element();
        txt_email.clear();
        txt_pass.clear();
        txt_email.sendKeys(email);
        txt_pass.sendKeys(pass);
        sleep(300);
        btn_submit.click();
        sleep(1000);
        actURL = edgeDriver.getCurrentUrl();
        Assert.assertEquals(ketquamongdoi,actURL);
        status = "Pass";
    }
    @Test
    public void Test6()throws InterruptedException{
        //edgeDriver.navigate().refresh();
        System.out.println("Test6");
        sleep(1000);
        Find_element();
        txt_email.clear();
        txt_pass.clear();
        txt_email.sendKeys(email);
        txt_pass.sendKeys(pass);
        sleep(300);
        btn_submit.click();
        actURL = edgeDriver.getCurrentUrl();
        Assert.assertEquals(ketquamongdoi,actURL);
        status = "Pass";
    }

    @AfterTest
    public void Close(){
        edgeDriver.close();
    }
    private void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void Find_element(){
         txt_email = edgeDriver.findElement(By.xpath("//*[@id=\"customer_email\"]"));
         txt_pass = edgeDriver.findElement(By.xpath("//*[@id=\"customer_password\"]"));
         btn_submit = edgeDriver.findElement(By.xpath("//*[@id=\"customer_login\"]/div[3]/input"));
    }
}

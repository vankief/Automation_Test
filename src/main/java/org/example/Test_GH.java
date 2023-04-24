package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test_GH {
    EdgeDriver edgeDriver;
    FileInputStream fileInputStream;
    FileOutputStream fileOutputStream;
    XSSFWorkbook wb;
    XSSFSheet sheet1;
    String ketqua1, ketqua2, text;
    String ketquamongdoi;
    String status,actURL;
    int i;
    WebElement note, update ,btn_add,number, text1, text2, pay;
    Boolean st = true ;
    @BeforeTest
    public void InitTest() throws IOException {
        System.out.println("Bat dau");
        WebDriverManager.edgedriver().setup();
        edgeDriver = new EdgeDriver();
        edgeDriver.get("https://gearvn.com/");
        edgeDriver.manage().window().maximize();
        fileInputStream = new FileInputStream("D:\\Installed\\giohang.xlsx");
        wb = new XSSFWorkbook(fileInputStream);
        sheet1= wb.getSheetAt(0);
        i=3;
    }
    @BeforeMethod
    public void BeforeMethod(){
        //actURL="khong co gi";
        System.out.println("------------------------------");
        System.out.println("BeforeMethod");
        text = sheet1.getRow(i).getCell(5).getStringCellValue();
        System.out.println(text);
        ketquamongdoi = sheet1.getRow(i).getCell(6).getStringCellValue();
        System.out.println(ketquamongdoi);
        status ="Fail";
    }
    @AfterMethod
    public void AfterMethod() throws IOException{
        System.out.println("AfterMothod "+status);
        XSSFCell cell = sheet1.getRow(i).getCell(8);
        // System.out.println("check cell: " + cell);
        cell.setCellType(CellType.STRING);
        cell.setCellValue(status);
        XSSFCell cell1 =sheet1.getRow(i).getCell(7);
        //System.out.println("check cell1: " + cell1);
        cell1.setCellType(CellType.STRING);
        cell1.setCellValue(actURL);
        fileOutputStream = new FileOutputStream("D:\\Installed\\giohang.xlsx");
        wb.write(fileOutputStream);
        i+=3;
    }
    @Test
    public void Test1() throws InterruptedException{
        System.out.println("Test1");
        edgeDriver.get("https://gearvn.com/products/laptop-msi-modern-14-c11m-011vn");
        sleep(100);
        btn_add = edgeDriver.findElement(By.xpath("//*[@id=\"allowAdd2Cart\"]"));
        text1 = edgeDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[2]/h1"));
        ketqua1 = text1.getText();
        System.out.println(ketqua1);
        btn_add.click();
        sleep(1000);
        text2 = edgeDriver.findElement(By.xpath("//*[@id=\"cartformpage\"]/table/tbody/tr[1]/td[2]/a/strong"));
        ketqua2 = text2.getText();
        System.out.println(ketqua2);
        if(ketqua1.equals(ketqua2)){
            actURL= "Them dung san pham";
            status = "Pass";
        }else {
            actURL = "Them sai san pham";
        }

    }
    @Test
    public void Test2() throws InterruptedException{
        sleep(2000);
        //edgeDriver.findElement(By.id("moe-dontallow_button")).click();
        System.out.println("Test2");
        sleep(2000);
        note = edgeDriver.findElement(By.xpath("//*[@id=\"note\"]"));
        note.sendKeys(text);
        sleep(1000);
        update = edgeDriver.findElement(By.xpath("//*[@id=\"update-cart\"]"));
        update.click();
        note = edgeDriver.findElement(By.xpath("//*[@id=\"note\"]"));
        String a = note.getText();
        System.out.println(a);
        if(a!= ""){
            if(text.equals(a)){
                status = "Pass";
                actURL = note.getText();
            }
        }else {
            actURL = "khong hien thi";
        }
        actURL = "khong hien thi";
    }
    @Test
    public void Test3() throws InterruptedException{
        sleep(2000);
        System.out.println("Test3");
        sleep(3000);
        number = edgeDriver.findElement(By.xpath("//*[@id=\"updates_1098588623\"]"));
        number.sendKeys("10000");
        sleep(1000);
        update = edgeDriver.findElement(By.xpath("//*[@id=\"update-cart\"]"));
        update.click();
        number = edgeDriver.findElement(By.xpath("//*[@id=\"updates_1098588623\"]"));
        if(number!=null){
            if(number.getText().equals("1")){
                status = "Pass";
                actURL = "khong cho cap nhat";
            }
        }else {
            actURL = "Loi";
        }

    }
    @Test
    public void Test4() throws InterruptedException{
        sleep(2000);
        System.out.println("Test4");
        edgeDriver.get("https://gearvn.com/products/laptop-msi-modern-14-c11m-011vn");
        sleep(100);
        btn_add = edgeDriver.findElement(By.xpath("//*[@id=\"allowAdd2Cart\"]"));
        btn_add.click();
        sleep(300);
        number = edgeDriver.findElement(By.xpath("//*[@id=\"updates_1098588623\"]"));
        sleep(500);
        number.sendKeys(text);
        update = edgeDriver.findElement(By.xpath("//*[@id=\"update-cart\"]"));
        update.click();
        sleep(5000);
        number = edgeDriver.findElement(By.xpath("//*[@id=\"updates_1098588623\"]"));
        String  a = number.getText();
        if(text.equals(a)){
            actURL= "nhap duoc chu";
        }else {
            status = "Pass";
            actURL = "khong nhap dc chu";
        }
    }
    @Test
    public void Test5() throws InterruptedException{
        sleep(200);
        text1 = edgeDriver.findElement(By.xpath("//*[@id=\"cartformpage\"]/table/tbody/tr[1]/td[2]/a/strong"));
        ketqua1 = text1.getText();
        sleep(300);
        pay = edgeDriver.findElement(By.xpath("//*[@id=\"checkout\"]"));
        pay.click();
        sleep(300);
        text2 = edgeDriver.findElement(By.xpath("/html/body/div[3]/div/div[1]/div/div/div/div[1]/table/tbody/tr/td[2]/span[1]"));
        ketqua2 = text2.getText();
        sleep(300);
        if(ketqua1.equals(ketqua2)){
            status = "Pass";
            actURL = "Thanh toan dung san pham";
        }else {
            actURL = "Sai san pham";
        }


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

}

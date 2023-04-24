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

public class Test_TK {
        EdgeDriver edgeDriver;
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        XSSFWorkbook wb;
        XSSFSheet sheet1;
        String search;
        String ketquamongdoi;
        String status,actURL;
        int i;
        WebElement input,btn_search, pagination, text;
        Boolean st = true ;
        @BeforeTest
        public void InitTest() throws IOException {
            System.out.println("Bat dau");
            WebDriverManager.edgedriver().setup();
            edgeDriver = new EdgeDriver();
            edgeDriver.get("https://gearvn.com/");
            edgeDriver.manage().window().maximize();
            fileInputStream = new FileInputStream("D:\\Installed\\timkiem.xlsx");
            wb = new XSSFWorkbook(fileInputStream);
            sheet1= wb.getSheetAt(0);
            i=3;
        }
        @BeforeMethod
        public void BeforeMethod(){
            //actURL="khong co gi";
            System.out.println("------------------------------");
            System.out.println("BeforeMethod");
            search = sheet1.getRow(i).getCell(5).getStringCellValue();
            System.out.println(search);
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
            fileOutputStream = new FileOutputStream("D:\\Installed\\timkiem.xlsx");
            wb.write(fileOutputStream);
            i+=3;
        }
        @Test
        public void Test1() throws InterruptedException{
            System.out.println("Test1");
            sleep(100);
           // edgeDriver.findElement(By.id("moe-dontallow_button")).click();
            Find_element();
           // input.sendKeys(search);
           // sleep(100);
            btn_search.click();
            sleep(1000);
            text = edgeDriver.findElement(By.xpath("//*[@id=\"search\"]/div/span/strong"));
            actURL = text.getText();
            System.out.println(actURL);
            if(ketquamongdoi.equals(actURL)){
                status = "Pass";
            }
        }
        @Test
        public void Test2() throws InterruptedException{
            sleep(2000);
            edgeDriver.findElement(By.id("moe-dontallow_button")).click();
            System.out.println("Test2");
            sleep(2000);
            pagination = edgeDriver.findElement(By.xpath("//*[@id=\"layout-page-search\"]/div[3]/div"));
            Find_element();
            input.sendKeys(search);
            sleep(500);
            btn_search.click();

           // WebElement a = edgeDriver.findElement(By.xpath("//*[@id=\"layout-page-search\"]/div[3]"));
            if(pagination!=null){
                for (int j = 1; j <= 20; j++) {
                    String xpath = "//*[@id=\"layout-page-search\"]/div[2]/div[" + j + "]";
                    if (edgeDriver.findElements(By.xpath(xpath)).size() == 0) {
                        st = false;
                        break;
                    }
                }
            }
            if(st) {
                status = "Pass";
                actURL = "Hien thi toan bo san pham";
            }else {
                actURL = "Khong hien thi toan bo";
            }
            System.out.println(actURL);
        }
        @Test
        public void Test3() throws InterruptedException{
            System.out.println("Test3");
            sleep(3000);
            Find_element();
            input.sendKeys(search);
            sleep(500);
            btn_search.click();
            String xpath = "//*[@id=\"layout-page-search\"]/div[2]/div[1]";
                    if (edgeDriver.findElements(By.xpath(xpath)).size() == 0) {
                        st = false;
                    }
            if(st){
                status = "Pass";
                actURL = "Hien thi san pham lien quan";
            }else {
                actURL = "Khong hien thi san pham";
            }
            System.out.println(actURL);
        }
        @Test
    public void Test4() throws InterruptedException{
        System.out.println("Test4");
        sleep(3000);
        Find_element();
        input.sendKeys(search);
        sleep(500);
        btn_search.click();
            String xpath = "//*[@id=\"layout-page-search\"]/div[2]/div[1]";
            if (edgeDriver.findElements(By.xpath(xpath)).size() == 0) {
                st = false;
            }
        if(st){
            status = "Pass";
            actURL = "Hien thi san pham lien quan";
        }else {
            actURL = "Khong hien thi san pham";
        }
            System.out.println(actURL);
    }
    @Test
    public void Test5() throws InterruptedException{
        System.out.println("Test5");
        sleep(3000);
        Find_element();
        input.sendKeys(search);
        sleep(500);
        btn_search.click();
        String xpath = "//*[@id=\"layout-page-search\"]/div[2]/div[1]";
        if (edgeDriver.findElements(By.xpath(xpath)).size() == 0) {
            st = true;
        }
        if(st){
            status = "Pass";
            actURL = "Khong co ket qua";
        }else {
            actURL = "Hien thi ket qua";
        }
        System.out.println(actURL);
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
            input = edgeDriver.findElement(By.xpath("//*[@id=\"searchbox\"]/input[1]"));
            btn_search = edgeDriver.findElement(By.xpath("//*[@id=\"searchbox\"]/button"));

        }
    }

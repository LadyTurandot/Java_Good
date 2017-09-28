package ru.stqa.pft.addressbook;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.io.File;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.*;
import static org.openqa.selenium.OutputType.*;

public class ContactDeletionTests extends TestBase{

    @Test
    public void testContactDeletion() {
        selectContact();
        wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
        acceptAlertSwitch();
        returnToHome();
    }

}

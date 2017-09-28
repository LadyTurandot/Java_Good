package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        gotoAddNewPage();
        fillContactForm(new ContactData("Contact3", "LastNameContact3", "1234567980", "contact3@gmail.com"));
        wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
        returnToHomePage();
    }

}


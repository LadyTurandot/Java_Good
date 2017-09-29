package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model1.ContactData;

public class ContactHelper extends HelperBase{

    public ContactHelper(FirefoxDriver wd) {
        super (wd);
    }


    public void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void fillContactForm(ContactData contactData) {
        type (By.name("firstname"), contactData.getFirstname());
        type (By.name("lastname"), contactData.getLastname());
        type (By.name("mobile"),contactData.getMobile());
        type (By.name("email"), contactData.getEmail());
    }

    public void acceptAlertSwitch() {

        wd.switchTo().alert().accept();
    }

    public void selectContact() {
        if (!wd.findElement(By.name("selected[]")).isSelected()) {
            click (By.name("selected[]"));
        }
    }

    public void returnToHome() {

        click (By.linkText("home"));
    }

    public void scriptDeletingPath() {

        click (By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void initContactModification() {

        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));

    }

    public void submitContactModification() {
        click(By.name("update"));
    }
}

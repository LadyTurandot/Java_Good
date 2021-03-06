package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model1.ContactData;
import ru.stqa.pft.addressbook.model1.Contacts;
import ru.stqa.pft.addressbook.model1.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void returnToHomePage() {
        wd.findElement(By.linkText("home page")).click();
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("email"), contactData.getEmail());
        //attach(By.name("photo"), contactData.getPhoto());

        /*if (creation) {
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() ==1);
            }
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));*/
    }


    public void acceptAlertSwitch() {

        wd.switchTo().alert().accept();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }


    public void returnToHome() {

        click(By.linkText("home"));
    }

    public void scriptDeletingPath() {

        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }


    public void initContactModificationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector("input[value='" + id + "']"));
        WebElement contactRow = checkbox.findElement(By.xpath("./../../."));
        contactRow.findElement(By.xpath(".//img[@title='Edit']")).click();
    }


    public void scriptSubmitionPath() {

        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact, boolean b) {
        fillContactForm(contact, b);
        scriptSubmitionPath();
        contactCache = null;
        returnToHomePage();

    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        scriptDeletingPath();
        acceptAlertSwitch();
        contactCache = null;
        returnToHome();
    }

    public void addContactToGroup() {
        //selectContactById(contact.getId());
        selectGroupForAdding();
        submitAddContact();
    }

    public void removeContactFromGroup() {
        selectGroupFromList();
        //selectContactById(contact.getId());
        removeContact();
    }

    private void removeContact() {
        click(By.name("remove"));
    }

    private void selectGroupFromList() {
        click(By.xpath("//form[@id='right']/select//option[3]"));
    }


    private void submitAddContact() {
        click(By.name("add"));
    }

    private void selectGroupForAdding() {
        click(By.cssSelector(".right > select:nth-child(2)"));

    }

    public boolean isThereAContact() {

        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement element : elements) {
            List<WebElement> contactEntries = element.findElements(By.cssSelector("td"));
            String firstname = contactEntries.get(2).getText();
            String lastname = contactEntries.get(1).getText();
            String allPhones = contactEntries.get(5).getText();
            String allEmails = contactEntries.get(4).getText();
            String address = contactEntries.get(3).getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            contactCache.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
                    .withAllPhones(allPhones).withAllEmails(allEmails).withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId())
                .withFirstname(firstname).withLastname(lastname).withHomePhone(home).withMobile(mobile).withWorkPhone(work)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }
}



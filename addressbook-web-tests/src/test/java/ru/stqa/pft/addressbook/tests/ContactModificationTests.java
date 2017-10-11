package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification (){
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().gotoHome();
        if (!app.getContactHelper().isThereAContact()) {
            app.getNavigationHelper().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("Contact3", "LastNameContact3", "1234567980", "contact3@gmail.com", "test3"), true);
        }
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Contact3", "LastNameContact3", "1234567980", "contact3@gmail.com", null), false);
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before);

    }

}

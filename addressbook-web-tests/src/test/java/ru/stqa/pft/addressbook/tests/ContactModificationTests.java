package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification (){
        app.getContactHelper().selectContact();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("Contact3", "LastNameContact3", "1234567980", "contact3@gmail.com"));
        app.getContactHelper().submitContactModification();
        app.getContactHelper().returnToHomePage();
    }

}

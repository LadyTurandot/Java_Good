package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {

        app.gotoAddNewPage();
        app.getContactHelper().fillContactForm(new ContactData("Contact3", "LastNameContact3", "1234567980", "contact3@gmail.com"));
        app.getGroupHelper().scriptSubmitionPath();
        app.getContactHelper().returnToHomePage();
    }

}


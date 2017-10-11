package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().gotoAddNewPage();
        app.getContactHelper().createContact(new ContactData("Contact3", "LastNameContact3", "1234567980", "contact3@gmail.com", "test3"), true);
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1);

    }

}


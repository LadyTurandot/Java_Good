package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion() {
        app.goTo().gotoHome();
        if (!app.getContactHelper().isThereAContact()) {
            app.goTo().gotoAddNewPage();
            app.getContactHelper().createContact(new ContactData("Contact3", "LastNameContact3", "1234567980", "contact3@gmail.com", "test3"), true);
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() -1);
        app.getContactHelper().scriptDeletingPath();
        app.getContactHelper().acceptAlertSwitch();
        app.getContactHelper().returnToHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(before.size() -1);
            Assert.assertEquals(before, after);

        }
    }



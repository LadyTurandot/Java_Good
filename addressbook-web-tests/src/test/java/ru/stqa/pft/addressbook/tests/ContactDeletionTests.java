package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().Home();
        if (app.contact().list().size() == 0) {
            app.goTo().addNewPage();
            app.contact().create(new ContactData("Contact3", "LastNameContact3", "1234567980", "contact3@gmail.com", "test3"), true);
        }
    }

    @Test
    public void testContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() -1;
        app.contact().delete(index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
            Assert.assertEquals(before, after);
        }
}



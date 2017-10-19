package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().Home();
        if (app.contact().list().size() == 0) {
            app.goTo().addNewPage();
            app.contact().create(new ContactData("Contact3", "LastNameContact3", "1234567980", "contact3@gmail.com", "test3"), true);
        }
    }

    @Test
    public void testContactModification() {
        List<ContactData> before = app.contact().list();
        int index =  before.size() - 1;
        ContactData contact = new ContactData(before.get(index).getId(), "Contact3", "LastNameContact3", "1234567980", "contact3@gmail.com", null);
        app.contact().modify(index, contact);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> ById = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(ById);
        after.sort(ById);
        Assert.assertEquals(before, after);
    }


}

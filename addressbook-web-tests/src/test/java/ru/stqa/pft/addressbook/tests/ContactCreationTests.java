package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Set<ContactData> before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData().withFirstname("Contact3").withLastname("LastNameContact3").withMobile("1234567980").withEmail("contact3@gmail.com").withGroup("test3");
        app.contact().create(contact, true);
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size() + 1);

        //contact.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);

    }

}


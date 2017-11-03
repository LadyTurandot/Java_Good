package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;
import ru.stqa.pft.addressbook.model1.Contacts;
import ru.stqa.pft.addressbook.model1.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class RemovingContactFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForContacts() {
        app.goTo().Home();
        if (app.db().contacts().size() == 0) {
            app.goTo().addNewPage();
            app.contact().create(new ContactData()
                    .withFirstname("Contact3").withLastname("LastNameContact3").withMobile("1234567980").withEmail("contact3@gmail.com")/*.withGroup("test3")*/, true);
        }
    }


    @BeforeMethod
    public void ensurePreconditionsForGroups() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test3"));
            Contacts before = app.db().contacts();
            app.goTo().Home();
            ContactData addedContact = before.iterator().next();
            app.contact().addContactToGroup(addedContact);
        } else {
            Contacts before = app.db().contacts();
            app.goTo().Home();
            ContactData addedContact = before.iterator().next();
            app.contact().addContactToGroup(addedContact);
        }
    }

    @Test
    public void testRemovingContactFromGroup() {
        Contacts before = app.db().contacts();
        app.goTo().Home();
        ContactData removedContact = before.iterator().next();
        app.contact().removeContactFromGroup(removedContact);
        Contacts after = app.db().contacts();
        assertThat(after, equalTo(before));
        verifyContactListInUI();
    }
}



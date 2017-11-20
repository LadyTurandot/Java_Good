package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;
import ru.stqa.pft.addressbook.model1.Contacts;
import ru.stqa.pft.addressbook.model1.GroupData;
import ru.stqa.pft.addressbook.model1.Groups;

import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AddingContactToGroupTests extends TestBase {


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
        }
    }


    @Test
    public void testAddingContactToGroup () {
        Contacts contactListBefore = app.db().contacts();
        Groups groupListBefore = app.db().groups();
        ContactData selectContact = contactListBefore.iterator().next();
        Groups groupSelectedForContact = selectContact.getGroups();
        GroupData selectGroup = groupListBefore.iterator().next();
        app.goTo().Home();
        app.contact().selectContactById(selectContact.getId());
        app.contact().addContactToGroup();
        ContactData contactListAfter = app.db().contacts().iterator().next();
        Groups groupListAfter = contactListAfter.getGroups();
        assertThat(groupListAfter, equalTo(groupSelectedForContact.withAdded(selectGroup)));
        //verifyContactListInUI();
    }
}

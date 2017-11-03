package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;
import ru.stqa.pft.addressbook.model1.Contacts;
import ru.stqa.pft.addressbook.model1.GroupData;
import ru.stqa.pft.addressbook.model1.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class RemovingContactFromGroupTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditionsForContacts() {
        app.goTo().Home();
        if (app.db().contacts().size() == 0) {
            app.goTo().addNewPage();
            app.contact().create(new ContactData()
                    .withFirstname("Contact3").withLastname("LastNameContact3").withMobile("1234567980").withEmail("contact3@gmail.com"), true);
        }
    }

    @BeforeMethod
    public void ensurePreconditionsForGroups() {
        app.goTo().groupPage();
        if (app.db().groups().size() == 0) {
            app.group().create(new GroupData().withName("test3"));
            app.goTo().Home();
            Contacts contactListBefore = app.db().contacts();
            ContactData selectContact = contactListBefore.iterator().next();
            app.contact().selectContactById(selectContact.getId());
            app.contact().addContactToGroup();
        } else {
            app.goTo().Home();
            Contacts contactListBefore = app.db().contacts();
            ContactData selectContact = contactListBefore.iterator().next();
            app.contact().selectContactById(selectContact.getId());
            app.contact().addContactToGroup();
        }
    }

    @Test
    public void testRemovingContactFromGroup() {
        Contacts contactListBefore = app.db().contacts();
        Groups groupListBefore = app.db().groups();
        ContactData selectContact = contactListBefore.iterator().next();
        Groups groupSelectedForContact = selectContact.getGroups();
        GroupData selectGroup = groupListBefore.iterator().next();
        app.goTo().Home();
        app.contact().selectContactById(selectContact.getId());
        app.contact().removeContactFromGroup();
        ContactData contactListAfter = app.db().contacts().iterator().next();
        Groups groupListAfter = contactListAfter.getGroups();
        assertThat(groupListAfter, equalTo(groupSelectedForContact.withAdded(selectGroup)));
        //verifyContactListInUI();
    }
}



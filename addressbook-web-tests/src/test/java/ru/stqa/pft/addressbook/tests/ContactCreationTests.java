package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;
import ru.stqa.pft.addressbook.model1.Contacts;

import javax.swing.plaf.PanelUI;
import java.io.File;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreation() {
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        File photo = new File("src/test/resources/stru.png");
        ContactData contact = new ContactData().withFirstname("Contact3").withLastname("LastNameContact3")
                .withMobile("1234567980").withEmail("contact3@gmail.com").withGroup("test3").withPhoto(photo);
        app.contact().create(contact, true);
        assertThat(app.contact().count(), equalTo(before.size() + 1));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt()))));

    }

    @Test
    public void testBadContactCreation() {
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        ContactData contact = new ContactData().withFirstname("Contact3'").withLastname("LastNameContact3").withMobile("1234567980").withEmail("contact3@gmail.com").withGroup("test3");
        app.contact().create(contact, true);
        assertThat(app. contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));

    }


}


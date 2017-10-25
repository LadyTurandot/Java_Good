package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;
import ru.stqa.pft.addressbook.model1.Contacts;

import javax.swing.plaf.PanelUI;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new ContactData().withFirstname("Contact1"). withLastname("LastNameContact1").withMobile("mobile1").withEmail("email1").withGroup( "test1")});
        list.add(new Object[]{new ContactData().withFirstname("Contact2"). withLastname("LastNameContact2").withMobile("mobile2").withEmail("email2").withGroup( "test2")});
        list.add(new Object[]{new ContactData().withFirstname("Contact3"). withLastname("LastNameContact3").withMobile("mobile3").withEmail("email3").withGroup( "test3")});
        return list.iterator();

    }

    @Test(dataProvider = "validContacts")
    public void testContactCreation (ContactData contact) {
        Contacts before = app.contact().all();
        app.goTo().addNewPage();
        //File photo = new File("src/test/resources/stru.png");
        //ContactData contact = new ContactData().withFirstname(firstName).withLastname(name)
                //.withMobile(mobile).withEmail(email). withGroup(group); //.withPhoto(photo);
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
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));

    }


}


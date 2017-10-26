package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;
import ru.stqa.pft.addressbook.model1.Contacts;
import ru.stqa.pft.addressbook.model1.GroupData;

import javax.swing.plaf.PanelUI;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson ();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((g)-> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }


    /*@DataProvider
    public Iterator<Object[]> validContacts() {
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[]{new ContactData().withFirstname("Contact1"). withLastname("LastNameContact1").withMobile("mobile1").withEmail("email1").withGroup( "test1")});
        list.add(new Object[]{new ContactData().withFirstname("Contact2"). withLastname("LastNameContact2").withMobile("mobile2").withEmail("email2").withGroup( "test2")});
        list.add(new Object[]{new ContactData().withFirstname("Contact3"). withLastname("LastNameContact3").withMobile("mobile3").withEmail("email3").withGroup( "test3")});
        return list.iterator();
    }*/

    @Test(dataProvider = "validContactsFromJson")
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


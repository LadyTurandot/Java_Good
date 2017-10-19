package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model1.ContactData;

public class contactPhoneTests extends TestBase {

    @Test
    public void testContactPhones(){
        app.goTo().Home();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);
    }
}

package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

public class TestBase {


    protected final ApplicationManager app = new ApplicationManager(BrowserType.CHROME);

    @BeforeMethod
    public void setUp() throws Exception {
        app.init();
    }

    @AfterMethod
    public void tearDown() {
        app.stop();
    }

}

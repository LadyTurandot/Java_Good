package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ResetPasswordHelper extends HelperBase {
    public ResetPasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void adminStart() {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), "administrator");
        type(By.name("password"), "root");
        click(By.cssSelector("input[value='Login']"));
    }


    public void openListOfUsers() {
        click(By.linkText("Manage Users"));
    }

    public void selectUser(String user) {
        type(By.name("username"), user);
        click(By.cssSelector("input[value='Manage User']"));
    }

    public void resetPassword() {
        click(By.cssSelector("[value = 'Reset Password']"));
    }


    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("input[value='Update User']"));
    }

}



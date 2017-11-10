package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public class ResetPasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {

        app.mail().start();
    }


    @Test
    public void testResetPassword() throws MessagingException, IOException {
        List<UserData> users = app.db().users();
        UserData selectedUser = users.iterator().next();
        String email = selectedUser.getEmail();
        String user = selectedUser.getUsername();
        String password = "newpassword";
        app.passwordHelper().adminStart();
        app.passwordHelper().openListOfUsers();
        app.passwordHelper().selectUser(user);
        app.passwordHelper().resetPassword();
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(mailMessages, email);
        app.passwordHelper().finish(confirmationLink, password);
        Assert.assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {

        app.mail().stop();
    }
}


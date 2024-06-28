package pages;

import base.PageBase;
import enums.TabName;
import models.User;
import org.openqa.selenium.By;
import org.testng.Assert;
import utils.Action;

public class LoginPage extends PageBase {
    By emailTxb = By.xpath("//form//input[@id='username']");
    By pwdtxb = By.xpath("//form//input[@id='password']");
    By loginBtn = By.xpath("//form//input[@type='submit']");

    public void submitLoginForm(User user) {
        Action.enter(emailTxb, user.getEmail());
        Action.enter(pwdtxb, user.getPassword());
        Action.click(loginBtn);
    }

    public void login(User user) {
        selectTab(TabName.LOGIN);
        submitLoginForm(user);
    }

    public void verifyErrorMes(String expectedResult) {
        String actualResult = Action.getText(messageProblemAccount);
        Assert.assertEquals(actualResult, expectedResult);
    }

    public void loopVerifyErrorMes(int n_loop, String expectedResult) {
        for (int i = 0; i <= n_loop; i++) {
            verifyErrorMes(expectedResult);
        }
    }

    public void verifyWelcomeMes(String expectedResult) {
        String actualResult = Action.getText(welcomeUserMessage);

        Assert.assertEquals(actualResult, expectedResult);
    }
}

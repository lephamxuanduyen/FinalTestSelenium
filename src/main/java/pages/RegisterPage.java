package pages;

import base.PageBase;
import enums.TabName;
import models.User;
import org.openqa.selenium.By;
import utils.Action;

public class RegisterPage extends PageBase {
    By emailTxb = By.xpath("//input[@id='email']");
    By passwwordTxb = By.xpath("//input[@id='password']");
    By confirmPwdTxb = By.xpath("//input[@id='confirmPassword']");
    By pidTxb = By.xpath("//input[@id='pid']");
    By registerBtn = By.xpath("//input[@type='submit']");
    public By messageInvalidPwd = By.xpath("//form//label[preceding-sibling::input[@id='password']]");
    public By messageInvalidPid = By.xpath("//form//label[preceding-sibling::input[@id='pid']]");
    public By messageRegisterSucc = By.xpath("//div[@id='content']/p");

    public void submitRegisterForm(User user) {
        Action.enter(emailTxb, user.getEmail());
        Action.enter(passwwordTxb, user.getPassword());
        Action.enter(confirmPwdTxb, user.getPassword());
        Action.enter(pidTxb, user.getPid());
        Action.click(registerBtn);
    }

    public void register(User user) {
        selectTab(TabName.REGISTER);
        submitRegisterForm(user);
    }
}

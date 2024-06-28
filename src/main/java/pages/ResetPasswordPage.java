package pages;

import base.PageBase;
import enums.TabName;
import org.openqa.selenium.By;
import utils.Action;

public class ResetPasswordPage extends PageBase {
    By resetPwdLink = By.xpath("//div[@id=\"content\"]//a[text()='Forgot Password page']");
    By mailTxb = By.xpath("//div[@id='content']//form//input[@id='email']");
    By submitFormBtn = By.xpath("//div[@id='content']//form//input[@type='submit']");
    By newPwdTxb = By.xpath("//div[@id='content']//form//input[@id='newPassword']");
    By confirmPwdTxb = By.xpath("//div[@id='content']//form//input[@id='confirmPassword']");
    By resetPwdBtn = By.xpath("//div[@id='content']//form//input[@type='submit']");
    public By message = By.xpath("//div[@id='content']/p[following-sibling::form]");
    public By mesErrorConfirmPwd = By.xpath("//div[@id='content']//label[preceding-sibling::input[@id='confirmPassword']]");
    public By resetTokenTxb = By.xpath("//div[@id='content']//form//input[@id='resetToken']");

    public void submitPwdResetForm(String email) {
        selectTab(TabName.LOGIN);
        Action.click(resetPwdLink);

        Action.enter(mailTxb, email);
        Action.scrollToElement(submitFormBtn);
        Action.click(submitFormBtn);
    }

    public void resetPwd(String newPwd, String confirmPwd) {
        Action.enter(newPwdTxb, newPwd);
        Action.enter(confirmPwdTxb, confirmPwd);
        Action.scrollToElement(resetPwdBtn);
        Action.click(resetPwdBtn);
    }
}

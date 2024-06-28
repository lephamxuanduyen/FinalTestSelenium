import base.PageBase;
import org.testng.annotations.*;
import pages.BookTicketsPage;
import pages.LoginPage;
import pages.MailPage;
import pages.RegisterPage;
import utils.DriverManagement;

public class TestBase {
    protected MailPage mailPage = new MailPage();
    protected PageBase pageBase = new PageBase();
    protected RegisterPage registerPage = new RegisterPage();
    protected LoginPage loginPage = new LoginPage();
    protected BookTicketsPage bookTicketsPage = new  BookTicketsPage();
    protected String mailName = "duyen";
    protected String mailDomain = "grr.la";
    protected String validPwd = "123456789";
    protected String validPid = "12345678";
    protected String emailConfirmInstruction = "thanhletraining03@gmail.com ";

    @BeforeClass
    void setup() {
        DriverManagement.setup();
    }

    @AfterClass
    void clean() {
        DriverManagement.quitBrowser();
    }
}

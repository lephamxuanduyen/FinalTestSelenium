import base.PageBase;
import models.User;
import org.testng.annotations.*;
import pages.*;
import utils.DriverManagement;

public class TestBase {
    protected MailPage mailPage = new MailPage();
    protected PageBase pageBase = new PageBase();
    protected RegisterPage registerPage = new RegisterPage();
    protected LoginPage loginPage = new LoginPage();
    protected BookTicketsPage bookTicketsPage = new  BookTicketsPage();
    protected MyTicketPage myTicketPage = new MyTicketPage();
    protected TicketPricePage ticketPricePage = new TicketPricePage();
    protected TimeTablePage timeTablePage = new TimeTablePage();
    protected String mailName = "duyen";
    protected String mailDomain = "grr.la";
    protected String email = "duyen@grr.la";
    protected String validPwd = "123456789";
    protected String validPid = "12345678";
    protected User user = new User(email, validPwd);
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

import base.DataSets;
import base.PageBase;
import enums.TabName;
import models.Ticket;
import models.User;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.Action;
import utils.listeners.ReportListener;

@Listeners(ReportListener.class)
public class E2E extends TestBase {
    PageBase pageBase = new PageBase();
    RegisterPage registerPage = new RegisterPage();
    MailPage mailPage = new MailPage();
    LoginPage loginPage = new LoginPage();
    TimeTablePage timeTablePage = new TimeTablePage();
    TicketPricePage ticketPricePage = new TicketPricePage();
    BookTicketsPage bookTicketsPage = new BookTicketsPage();
    MyTicketPage myTicketPage = new MyTicketPage();

    String password = "123456789";
    String pid = "12345678";
    String expectedBookTicketSucMes = "Ticket booked successfully!";

    @Test(description = "An e2e test", dataProvider = "TicketsDataProvider", dataProviderClass = DataSets.class)
    void e2e(Ticket ticket){
        SoftAssert softAssertion = new SoftAssert();
        mailPage.openMailPage();
        String email = mailPage.getFreeMail();
        User newUser = new User(email, password, pid);

        pageBase.switchToRailway();
        registerPage.selectTab(TabName.REGISTER);
        registerPage.register(newUser);

        mailPage.switchToMailPage();
        mailPage.confirmAccount(emailConfirmInstruction);

        Action.switchOtherTab(mailPage, pageBase);
        loginPage.selectTab(TabName.LOGIN);
        User user = new User(email, password);
        loginPage.login(user);
        pageBase.selectTab(TabName.TIMETABLE);
        timeTablePage.checkTicket(ticket.getDepartStation(), ticket.getArriveStation());
        Integer price = Integer.parseInt(ticketPricePage.getPriceBySeatType(ticket.getSeatType()));
        Integer amount = Integer.parseInt(ticket.getTicketAmount());
        Integer expectedTotal = price*amount;
        ticketPricePage.bookTicket(ticket.getSeatType());
        bookTicketsPage.selectDepartDate(ticket.getDepartDate());
        bookTicketsPage.selectTicketAmount(ticket.getTicketAmount());
        bookTicketsPage.submitForm();
        bookTicketsPage.verifyMesBookSuc(softAssertion, expectedBookTicketSucMes);
        bookTicketsPage.verifyInfoTicket(softAssertion, ticket);
        myTicketPage.selectTab(TabName.MYTICKET);
        softAssertion.assertEquals(myTicketPage.getTotalPrice(ticket), expectedTotal.toString());
        myTicketPage.cancelTicket(ticket);
        myTicketPage.verifyTicketNoDisplay(softAssertion, ticket);
        pageBase.selectTab(TabName.LOGOUT);
        Boolean isDisplay = Action.isDisplay(By.xpath(String.format(loginPage.tabMenu, "Log out")));
        if (isDisplay==true){
            softAssertion.fail("Failed to Log out!");
        }
        softAssertion.assertAll();
    }
}

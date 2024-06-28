import base.DataSets;
import enums.SeatType;
import enums.Station;
import enums.TabName;
import models.Ticket;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.Action;

public class FilterTicket extends TestBase{
    String password = "123456789";
    String pid = "12345678";
    User newUser;
    Ticket ticket = new Ticket("7/1/2024", Station.DANANG, Station.NHATRANG, SeatType.SSC, "1");

    @BeforeClass
    void register(){
        mailPage.openMailPage();
        String email = mailPage.getFreeMail();
        newUser = new User(email, password, pid);

        pageBase.switchToRailway();
        registerPage.selectTab(TabName.REGISTER);
        registerPage.register(newUser);

        mailPage.switchToMailPage();
        mailPage.confirmAccount(emailConfirmInstruction);

        Action.switchOtherTab(mailPage, pageBase);
    }

//    @Test(description = "User can filter \"Manage ticket\" table with both Arrive station and Depart date", dataProvider = "TicketsDataProvider", dataProviderClass = DataSets.class)
    @Test
    void filterTicket(){
        loginPage.login(newUser);
        bookTicketsPage.selectTab(TabName.BOOKTICKET);
        bookTicketsPage.bookTicket(ticket);
    }
}

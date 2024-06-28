import base.ReadJson;
import enums.Station;
import enums.TabName;
import models.Ticket;
import models.User;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Action;

import java.util.List;

public class FilterTicket extends TestBase{
    String password = "123456789";
    String pid = "12345678";
    User newUser;
    List<Ticket> ticketsSameStation = ReadJson.getTickets("ticketSameStation");
    Station FilterDepartStation = Station.DANANG;
    String FilterDepartDate = "7/1/2024";

    @BeforeClass(dependsOnMethods = {"setup"})
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

    @Test(description = "User can filter \"Manage ticket\" table with both Arrive station and Depart date")
    void filterTicket(){
        loginPage.login(newUser);
        bookTicketsPage.bookTicketFromJson(ticketsSameStation);
        myTicketPage.selectTab(TabName.MYTICKET);
        myTicketPage.filterTicket(FilterDepartStation, FilterDepartDate);
        myTicketPage.isCorrectFilteredTicket(FilterDepartStation, FilterDepartDate);
    }
}

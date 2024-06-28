import enums.SeatType;
import enums.TabName;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookTicket extends TestBase {
    String ticketStation = "Huế to Quảng Ngãi";
    String seatType = "Hard Seat";
    String TicketAmount = "2";

    @BeforeClass(dependsOnMethods = {"setup"})
    void login(){
        loginPage.login(user);
    }

    @Test
    void BookTicketWithKnownPrice(){
        pageBase.selectTab(TabName.TICKETPRICE);
        ticketPricePage.checkTicket(ticketStation);
        Integer price = Integer.parseInt(ticketPricePage.getPriceBySeatType(SeatType.fromStringtoSeatType(seatType)));
        Integer amount = Integer.parseInt(TicketAmount);
        Integer expectedTotal = price*amount;
        ticketPricePage.bookTicket(SeatType.fromStringtoSeatType(seatType));
        bookTicketsPage.selectTicketAmount(TicketAmount);
        bookTicketsPage.submitForm();
        Integer actualTotal = Integer.parseInt(bookTicketsPage.getTotalPrice());
        Assert.assertEquals(actualTotal, expectedTotal);
    }
}

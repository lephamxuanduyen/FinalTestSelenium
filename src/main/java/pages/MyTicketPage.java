package pages;

import base.PageBase;
import enums.TabName;
import models.Ticket;
import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;
import utils.DriverManagement;
import utils.Action;

public class MyTicketPage extends PageBase {
    String cancelBtn = "//table[@class='MyTable']//tr[td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s']]]]]]//input[contains(@onclick, 'Delete')]";
    String total = "//table[@class='MyTable']//tr[td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s']]]]]]//td[count(//tr/th[text()='Total Price']/preceding-sibling::th)+1]";

    public void cancelTicket(Ticket ticket) {
        By xpathCancelBtn = By.xpath(String.format(cancelBtn, ticket.getDepartStation().getStationValue(), ticket.getArriveStation().getStationValue(), ticket.getSeatType().getSeatTypeValue(), ticket.getDepartDate(), ticket.getTicketAmount()));
        selectTab(TabName.MYTICKET);
        Action.scrollToElement(xpathCancelBtn);
        Action.click(xpathCancelBtn);
        DriverManagement.driver.get().switchTo().alert().accept();
    }

    public String getTotalPrice(Ticket ticket){
         return Action.getText(By.xpath(String.format(total, ticket.getDepartStation().getStationValue(), ticket.getArriveStation().getStationValue(), ticket.getSeatType().getSeatTypeValue(), ticket.getDepartDate(), ticket.getTicketAmount())));
    }

    public void verifyTicketNoDisplay(SoftAssert softAssertion, Ticket ticket) {
        By xpathCancelBtn = By.xpath(String.format(cancelBtn, ticket.getDepartStation().getStationValue(), ticket.getArriveStation().getStationValue(), ticket.getSeatType().getSeatTypeValue(), ticket.getDepartDate(), ticket.getTicketAmount()));
        Boolean isDisplay = Action.isDisplay(xpathCancelBtn);
        if (isDisplay==true){
            softAssertion.fail("Canceling Ticket is Failed!");
        }
    }
}

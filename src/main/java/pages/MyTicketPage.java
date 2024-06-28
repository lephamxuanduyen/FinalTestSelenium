package pages;

import base.PageBase;
import enums.Station;
import enums.TabName;
import models.Ticket;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;
import utils.DriverManagement;
import utils.Action;

import java.util.List;

public class MyTicketPage extends PageBase {
    String cancelBtn = "//table[@class='MyTable']//tr[td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s']]]]]]//input[contains(@onclick, 'Delete')]";
    String total = "//table[@class='MyTable']//tr[td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s' and following-sibling::td[text()='%s']]]]]]//td[count(//tr/th[text()='Total Price']/preceding-sibling::th)+1]";
    By filterDepartStation = By.xpath("//select[@name='FilterDpStation']");
    By filterDate = By.xpath("//input[@name='FilterDpDate']");
    By filterApplyBtn = By.xpath("//input[@type='submit' and @value='Apply filter']");
    By departs = By.xpath("//table[@class='MyTable']//td[count(//table[@class='MyTable']//tr/th[text()='Depart Station']/preceding-sibling::th) + 1]");
    By date =    By.xpath("//table[@class='MyTable']//td[count(//table[@class='MyTable']//tr/th[text()='Depart Date']/preceding-sibling::th) + 1]");

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

    public void filterTicket(Station depart, String date){
        Action.select(filterDepartStation, depart.getStationValue());
        Action.enter(filterDate, date);
        Action.click(filterApplyBtn);
    }

    public void isCorrectFilteredTicket(Station expectedDepart, String expectedDate){
        SoftAssert softAssertion = new SoftAssert();
        List<WebElement> departStationList = Action.findManyEle(departs);
        List<WebElement> arriveStationList = Action.findManyEle(date);
        for (WebElement depart: departStationList){
            softAssertion.assertEquals(depart.getText(), expectedDepart.getStationValue());
        }
        for (WebElement arrive: arriveStationList){
            softAssertion.assertEquals(arrive.getText(), expectedDate);
        }
        softAssertion.assertAll();
    }
}

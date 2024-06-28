package pages;

import base.PageBase;
import enums.Station;
import enums.TabName;
import org.openqa.selenium.By;
import utils.Action;

public class TimeTablePage extends PageBase {
    String Btn = "//table//tr[td[text()='%s' and following-sibling::td[text()='%s']]]//a[(text()='%s')]";

    public void checkTicket(Station departStation, Station arriveStation) {
        By checkPriceBtn = By.xpath(String.format(Btn, departStation.getStationValue(), arriveStation.getStationValue(), "check price"));
        selectTab(TabName.TIMETABLE);
        Action.scrollToElement(checkPriceBtn);
        Action.click(checkPriceBtn);
    }

    public void bookTicket(String departStation, String arriveStation) {
        By bookTicketBtn = By.xpath(String.format(Btn, departStation, arriveStation, "book ticket"));
        selectTab(TabName.TIMETABLE);
        Action.scrollToElement(bookTicketBtn);
        Action.click(bookTicketBtn);
    }
}

package pages;

import base.PageBase;
import enums.SeatType;
import org.openqa.selenium.By;
import utils.Action;

public class TicketPricePage extends PageBase {
    public By header = By.xpath("//div[@id='content']//h1");
    public By tableHeader = By.xpath("//div[@id='content']//table//tr[@class='TableSmallHeader']/th");
    String infoPriceBySeatType = "//div[@id='content']//table//tr/th[contains(text(), 'Price (VND)')]/following-sibling::td[count(//td[text()='%s']/preceding-sibling::td)+1]";
    String bookTiketBtn = "//table[@class='NoBorder']//tr[td[text()='%s']]//a[text()='Book ticket']";

    public String getPriceBySeatType(SeatType info) {
        return Action.getText(By.xpath(String.format(infoPriceBySeatType, info.toString())));
    }

    public void bookTicket(SeatType info){
        Action.click(By.xpath(String.format(bookTiketBtn, info.getSeatTypeValue())));
    }
}

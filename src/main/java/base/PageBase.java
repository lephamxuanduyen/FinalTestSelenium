package base;

import enums.TabName;
import org.openqa.selenium.By;
import org.openqa.selenium.WindowType;
import utils.DriverManagement;

public class PageBase {
    public static String tabMenu = "//div[@id=\"menu\"]//li//a[span[text()='%s']]";
    public static String RailwayWindow;
    public String tempMailWindow;

    public By welcomeUserMessage = By.xpath("//div[@id='banner']//strong");
    public By messageProblemAccount = By.xpath("//div[@id='content']/p[contains(@class, 'message error') and following-sibling::form]");
    public String paragraph = "//p[contains(text(),'%s')]";

    public static void openRailway() {
        int retryCount = 5; // Số lần thử tải lại trang tối đa
        int retry = 0;
        while (retry < retryCount) {
            try {
                DriverManagement.driver.get().get(ReadConfig.getProperty("railway.url")); // Truy cập trang web
                if (DriverManagement.driver.get().getTitle().contains("Safe Railway")) {
                    break; // Trang đã tải xong, thoát khỏi vòng lặp
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to load.");
            }
            retry++;
        }
        RailwayWindow = DriverManagement.driver.get().getWindowHandle();
    }

    public void openMailPage() {
        DriverManagement.driver.get().switchTo().newWindow(WindowType.TAB);
        DriverManagement.driver.get().get(ReadConfig.getProperty("tempmail.url"));
        tempMailWindow = DriverManagement.driver.get().getWindowHandle();
    }

    public static void switchToRailway() {
        DriverManagement.driver.get().switchTo().window(RailwayWindow);
    }

    public void switchToMailPage() {
        DriverManagement.driver.get().switchTo().window(tempMailWindow);
    }

    public void selectTab(TabName tabname) {
        DriverManagement.driver.get().findElement(By.xpath(String.format(tabMenu, tabname.getTabNameValue()))).click();
    }
}

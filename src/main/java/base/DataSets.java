package base;

import org.testng.annotations.DataProvider;

public class DataSets {
    @DataProvider(name = "TicketsDataProvider")
    public Object[][] TicketsData() {
        Object[][] data = new Object[ReadJson.getTickets().size()][1];
        for (int i = 0; i < ReadJson.getTickets().size(); i++) {
            data[i][0] = ReadJson.getTickets().get(i);
        }
        return data;
    }
}

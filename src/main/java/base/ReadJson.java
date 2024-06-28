package base;

import com.google.gson.Gson;
import enums.SeatType;
import enums.Station;
import models.Ticket;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadJson {
    private static Map<?, ?> data;

    static {
        loadJsonData();
    }

    private static void loadJsonData() {
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/data.json"));
            data = gson.fromJson(reader, Map.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static List<Ticket> getTickets(String key) {
        List<Map<String, String>> infoTickets = (List<Map<String, String>>) data.get(key);
        List<Ticket> tickets = new ArrayList<>();
        for (Map<String, String> ticket : infoTickets) {
            String departDate = ticket.get("DepartDate");
            Station departStation = Station.fromStringtoStation(ticket.get("DepartStaion"));
            Station arriveStation = Station.fromStringtoStation(ticket.get("ArriveStation"));
            SeatType seatType = SeatType.fromStringtoSeatType(ticket.get("SeatType"));
            String ticketAmount = ticket.get("TicketAmount");
            Ticket tempUser = new Ticket(departDate, departStation, arriveStation, seatType, ticketAmount);
            tickets.add(tempUser);
        }
        return tickets;
    }
}
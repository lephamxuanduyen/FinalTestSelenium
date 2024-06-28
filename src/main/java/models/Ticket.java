package models;

import enums.SeatType;
import enums.Station;

public class Ticket {
    String departDate;
    Station departStation;
    Station arriveStation;
    SeatType seatType;
    String ticketAmount;

    public Ticket(String departDate, Station departStation, Station arriveStation, SeatType seatType, String ticketAmount) {
        this.departDate = departDate;
        this.departStation = departStation;
        this.arriveStation = arriveStation;
        this.seatType = seatType;
        this.ticketAmount = ticketAmount;
    }

    public String getDepartDate() {
        return departDate;
    }

    public Station getDepartStation() {
        return departStation;
    }

    public Station getArriveStation() {
        return arriveStation;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public String getTicketAmount() {
        return ticketAmount;
    }
}

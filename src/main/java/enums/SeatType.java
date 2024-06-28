package enums;

public enum SeatType {
    HS("Hard seat"),
    SS("Soft seat"),
    SSC("Soft seat with air conditioner"),
    HB("Hard bed"),
    SB("Soft bed"),
    SBC("Soft bed with air conditioner");

    private String value;

    SeatType(String value) {
        this.value = value;
    }

    public String getSeatTypeValue() {
        return value;
    }

    public static SeatType fromStringtoStation(String value) {
        for (SeatType seatType : SeatType.values()) {
            if (seatType.getSeatTypeValue().equalsIgnoreCase(value)) {
                return seatType;
            }
        }
        return null;
    }
}

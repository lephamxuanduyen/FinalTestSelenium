package enums;

public enum TabName {
    HOME("Home"),
    FAQ("FAQ"),
    CONTACT("Contact"),
    TIMETABLE("Timetable"),
    TICKETPRICE("Ticket price"),
    BOOKTICKET("Book ticket"),
    REGISTER("Register"),
    LOGIN("Login"),
    MYTICKET("My ticket"),
    CHANGEPASSWORD("Change password"),
    LOGOUT("Log out");

    private String value;

    TabName(String value) {
        this.value = value;
    }

    public String getTabNameValue() {
        return value;
    }
}
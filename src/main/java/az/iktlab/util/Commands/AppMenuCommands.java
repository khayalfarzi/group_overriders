package az.iktlab.util.Commands;

public enum AppMenuCommands {
    ONLINE_BOARD("1"),
    SHOW_FLIGHT_INFO("2"),
    SEARCH_AND_BOOKING("3"),
    CANCEL_BOOKING("4"),
    MY_FLIGHTS("5"),
    LOGOUT("6");

    private final String commandNumber;

    AppMenuCommands(String commandNumber) {
        this.commandNumber = commandNumber;
    }

    public String getCommandNumber() {
        return commandNumber;
    }
}

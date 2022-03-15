package az.iktlab.util.Commands;

public enum SearchBookingCommands {
    SEARCH("1"),
    BOOKING("2");

    private final String commandNumber;

    SearchBookingCommands(String commandNumber) {
        this.commandNumber = commandNumber;
    }

    public String getCommandNumber() {
        return commandNumber;
    }
}

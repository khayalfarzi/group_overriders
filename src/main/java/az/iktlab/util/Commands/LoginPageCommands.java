package az.iktlab.util.Commands;

public enum LoginPageCommands {
    LOGIN("1"),
    REGISTRATION("2"),
    EXIT("3");

    private final String commandNumber;

    LoginPageCommands(String commandNumber) {
        this.commandNumber = commandNumber;
    }

    public String getCommandNumber() {
        return commandNumber;
    }
}


package scooterdelta.challenge.bot.common.lookup;

public enum StateLookup {

    COMMAND("command.txt"),
    PLACE_SHIP("place.txt"),
    STATE("state.json");

    private final String location;

    StateLookup(final String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }
}

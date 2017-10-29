package scooterdelta.challenge.bot.common.lookup

enum class StateLookup (val location : String) {

    COMMAND("command.txt"),
    PLACE_SHIP("place.txt"),
    STATE("state.json"),
    USER_STATE("user_state.json");

}
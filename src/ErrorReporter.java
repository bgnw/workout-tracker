import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorReporter {

    public static void logError(String warningLevel, String message) {
        logError(warningLevel, message, "");
    }

    public static void logError(String warningLevel, String message, String dataDump) {
        System.out.println(
                ZonedDateTime.now(ZoneOffset.UTC).format(DateTimeFormatter.ISO_INSTANT) +
                        ": *** " + warningLevel.toUpperCase() + ": " + message +
                        (dataDump.isEmpty() ? "" : "\n\tData dump/tech msg: " + dataDump));
    }
}

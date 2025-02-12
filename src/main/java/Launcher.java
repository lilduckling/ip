import javafx.application.Application;
// import static your.package.Constants.*; // Adjust the package name accordingly

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        validateArgs(args);
        launchApplication(args);
    }

    private static void validateArgs(String[] args) {
        assert args != null : "args should not be null";
        assert Main.class != null : "Main class should not be null";
    }

    private static void launchApplication(String[] args) {
        Application.launch(Main.class, args);
    }
}


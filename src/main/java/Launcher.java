import javafx.application.Application;
// import jdk.javadoc.internal.tool.Main;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        assert args != null: "args should not be null";

        assert Main.class != null: "Main class should not be null";
        Application.launch(Main.class, args);
    }
}


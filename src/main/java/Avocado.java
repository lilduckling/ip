import java.util.Scanner;

public class Avocado {
    public static void main(String[] args) {
        String logo = "     _             \n"
                    + "    / \\__    ____ \n"
                    + "   / _ \\ \\  / /  \\ \n"
                    + "  / ___ \\ \\/ / |  |\n"
                    + " /_/   \\_\\__/\\___/ \n";
            
        System.out.println("____________________________________________________________");
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Avocado");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) { 
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break; // Exit loop
            }
            System.out.println("____________________________________________________________");
            System.out.println("Ok i will now " + input + " like an avocado!"); 
            System.out.println("____________________________________________________________");
        }

        scanner.close(); 
    }
}

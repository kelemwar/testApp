package testApp;
import java.util.List;
import java.util.Scanner;

public class App
{

    private static Services services;
    private static Scanner sc;
    public static void main( String[] args )
    {

        services = new Services();
        sc = new Scanner(System.in);

        services.addEntry("Joshua", 65531215);
        services.addEntry("German", 6553665);
        services.addEntry("Andrew", 65512335);

        System.out.println("Welcome in phone book.");
        do {
            System.out.println("Actions:\nget entry - g,\nadd entry - a,\nremove entry - r,\nlist all entries - l:");
            Actions action = getActions();
            selectAction(action);

            System.out.println("Can I help you more? Type y if yes");
        }
        while (isMoreAction(sc.nextLine()));
        System.exit(0);
    }

    private static Actions getActions() {
        String inputValue = sc.nextLine();
        Actions action;

        try {
            action = Actions.getEnumByName(inputValue);
        } catch (IllegalArgumentException e) {
            action = Actions.ERROR;
        }
        return action;
    }

    private static void selectAction(Actions action) {

        switch (action) {
            case GET:
                get();
                break;
            case ADD:
                addContact();
                break;
            case REMOVE:
                removeContact();
                break;
            case LIST:
                list();
                break;
            case ERROR:
            default:
                error();
        }
    }

    private static void addContact() {
        String name;
        boolean isNameValid = false;
        do {
            name = getName();

            try {
                services.validateName(name);
                isNameValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!isNameValid);

        String phoneNumber;
        boolean isPhoneNameValid = false;

        do {
            phoneNumber = Integer.toString(getPhoneNumber());

            try {
                services.validatePhoneNumber(phoneNumber);
                isPhoneNameValid = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!isPhoneNameValid);

        services.addEntry(name, Integer.parseInt(phoneNumber));
    }

    private static void get() {
        System.out.println("Set name to get a phone number:");
        String name = sc.nextLine();
        try {
            int phoneNumber = services.getNumberByName(name);
            System.out.println(phoneNumber);
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private static int getPhoneNumber() {
        System.out.println("Type phone number:");
        return Integer.parseInt(sc.nextLine());
    }

    private static String getName() {
        System.out.println("Choose a name of entry. Name has to be unique:");
        return sc.nextLine();
    }

    private static void removeContact() {
        System.out.println("Choose a name to remove");
        String name = sc.nextLine();
        if (services.removeEntry(name)) {
            System.out.println("Contact " + name + " removed");
        } else {
            System.out.println("Entry not found");
        }
    }

    private static void list() {
        try {
            List<Contact> list = services.listEntries();
            System.out.println("Your phone book: ");
            list.forEach(entry -> System.out.println(entry.getName() + " " + entry.getNumber()));

        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            addFirstEntry();
        }
    }

    private static void addFirstEntry() {
        System.out.print("Do you want to set your first empty? y - yes");
        String inputValue = sc.nextLine();
        if (isMoreAction(inputValue)) {
            addContact();
        }
    }

    private static void error() {
        System.out.println("Command not found.");
    }

    private static boolean isMoreAction(String value) {
        return value.toLowerCase().equals("y");
    }
}

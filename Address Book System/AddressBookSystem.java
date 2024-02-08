import java.io.*;
import java.util.*;

class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone Number: " + phoneNumber + ", Email Address: " + emailAddress;
    }
}

class AddressBook implements Serializable {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(contacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            contacts = (List<Contact>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

public class AddressBookSystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();

        while (true) {
            System.out.println("\nAddress Book System");
            System.out.println("1. Add a new contact");
            System.out.println("2. Remove a contact");
            System.out.println("3. Search for a contact");
            System.out.println("4. Display all contacts");
            System.out.println("5. Save contacts to file");
            System.out.println("6. Load contacts from file");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addContact(addressBook);
                    break;
                case 2:
                    removeContact(addressBook);
                    break;
                case 3:
                    searchContact(addressBook);
                    break;
                case 4:
                    displayAllContacts(addressBook);
                    break;
                case 5:
                    saveToFile(addressBook);
                    break;
                case 6:
                    loadFromFile(addressBook);
                    break;
                case 7:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }

    private static void addContact(AddressBook addressBook) {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email address: ");
        String emailAddress = scanner.nextLine();

        Contact contact = new Contact(name, phoneNumber, emailAddress);
        addressBook.addContact(contact);
        System.out.println("Contact added successfully.");
    }

    private static void removeContact(AddressBook addressBook) {
        System.out.print("Enter name of contact to remove: ");
        String name = scanner.nextLine();
        Contact contact = addressBook.searchContact(name);
        if (contact != null) {
            addressBook.removeContact(contact);
            System.out.println("Contact removed successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void searchContact(AddressBook addressBook) {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        Contact contact = addressBook.searchContact(name);
        if (contact != null) {
            System.out.println("Contact found:");
            System.out.println(contact);
        } else {
            System.out.println("Contact not found.");
        }
    }

    private static void displayAllContacts(AddressBook addressBook) {
        System.out.println("\nAll Contacts:");
        addressBook.displayAllContacts();
    }

    private static void saveToFile(AddressBook addressBook) {
        System.out.print("Enter file name to save contacts: ");
        String filename = scanner.nextLine();
        addressBook.saveToFile(filename);
        System.out.println("Contacts saved to file successfully.");
    }

    private static void loadFromFile(AddressBook addressBook) {
        System.out.print("Enter file name to load contacts from: ");
        String filename = scanner.nextLine();
        addressBook.loadFromFile(filename);
        System.out.println("Contacts loaded from file successfully.");
    }
}
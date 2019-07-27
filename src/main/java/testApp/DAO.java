package testApp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DAO {

    private List<Contact> contacts;

    public DAO() {
        this.contacts = new ArrayList<>();
     }

    public void addContact(String name, int phoneNumber) {
        contacts.add(new Contact(name, phoneNumber));
    }

    public void remove(String name) {
        contacts.removeIf(s -> s.name.equals(name));

    }

    public List<Contact> get() {
        return contacts;
    }

    public List<Contact> get(String value) {
        return contacts.stream().filter(contact -> contact.name.equals(value)).collect(Collectors.toList());
    }


    public Contact getContactByName(String value) {
        List<Contact> contacts = get(value);
        if (contacts.size() == 1) {
            return contacts.get(0);
        }
        return null;
    }



}

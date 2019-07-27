package testApp;

import java.util.ArrayList;
import java.util.List;

public class Services {

    private DAO phoneBook;

    public Services() {
        this.phoneBook = new DAO();
    }

    public int getNumberByName(String name) {
        Contact contact = phoneBook.getContactByName(name);
        if (contact == null) {
            throw new NullPointerException("Entity not found");
        }
        return contact.getNumber();
    }

    public boolean removeEntry(String name) {
        int foundEntries = phoneBook.get(name).size();
        if (foundEntries == 1) {
            phoneBook.remove(name);
            return true;
        }
        return false;
    }

    public List<Contact> listEntries() {
        List<Contact> list = new ArrayList<>(phoneBook.get());

        if (list.size() == 0) {
            throw new IndexOutOfBoundsException("Your phone book is empty.");
        }
        return list;
    }

    public void addEntry(String name, int phoneNumber) {
        phoneBook.addContact(name, phoneNumber);
    }

    public void validatePhoneNumber(String phoneNumber) {
        if (!isNumberValid(phoneNumber)) {
            throw new IllegalArgumentException("Phone number could have 15 characters and cannot be empty.");
        }
    }

    public void validateName(String name) {
        if (phoneBook.get(name).size() > 0) {
            throw new IllegalArgumentException("Name already in use");

        } else if (!isLengthOfNameValid(name)) {
            throw new IllegalArgumentException("Name could have 50 characters and cannot be empty.");
        }
    }

    private boolean isLengthValid(String value, int length) {
        return value.length() > 0 && value.length() <= length;
    }

    private boolean isLengthOfNameValid(String name) {
        return isLengthValid(name, 50);
    }

    private boolean isNumberValid(String value) {
        return isLengthValid(value, 15);
    }
}

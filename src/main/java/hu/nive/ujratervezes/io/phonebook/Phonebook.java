package hu.nive.ujratervezes.io.phonebook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Phonebook {

    public void exportPhonebook(Map<String, String> contacts, String output) {
        if (contacts == null || output == null) {
            throw new IllegalArgumentException();
        }
        List<String> fullContacts = getListOfStringsToWrite(contacts);
        StringBuilder sb = new StringBuilder();
        concatFullContacts(fullContacts, sb);
        writeToFile(output, sb);
    }

    private void writeToFile(String output, StringBuilder sb) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(output))) {
            bw.write(sb.toString());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void concatFullContacts(List<String> fullContacts, StringBuilder sb) {
        for (String contact : fullContacts) {
            sb.append(contact).append(System.lineSeparator());
        }
    }

    private List<String> getListOfStringsToWrite(Map<String, String> contacts) {
        List<String> fullContacts = new ArrayList<>();
        for (Map.Entry<String, String> contact : contacts.entrySet()) {
            fullContacts.add(contact.getKey() + ": " + contact.getValue());
        }
        return fullContacts;
    }
}
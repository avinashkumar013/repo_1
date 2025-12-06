package com.contactmanager;

import java.util.*;
import javax.servlet.http.HttpSession;

public class ContactDAO {

    private static final String SESSION_KEY = "contacts";

    @SuppressWarnings("unchecked")
    public static Map<String, Contact> getContacts(HttpSession session) {
        Map<String, Contact> contacts = (Map<String, Contact>) session.getAttribute(SESSION_KEY);
        if (contacts == null) {
            contacts = new LinkedHashMap<>();
            session.setAttribute(SESSION_KEY, contacts);
        }
        return contacts;
    }

    public static String addContact(HttpSession session, Contact contact) {
        String id = UUID.randomUUID().toString();
        contact.setId(id);
        Map<String, Contact> contacts = getContacts(session);
        contacts.put(id, contact);
        return id;
    }

    public static Contact getContact(HttpSession session, String id) {
        return getContacts(session).get(id);
    }

    public static boolean updateContact(HttpSession session, String id, Contact contact) {
        Map<String, Contact> contacts = getContacts(session);
        if (contacts.containsKey(id)) {
            contact.setId(id);
            contacts.put(id, contact);
            return true;
        }
        return false;
    }

    public static boolean deleteContact(HttpSession session, String id) {
        return getContacts(session).remove(id) != null;
    }

    public static List<Contact> getAllContacts(HttpSession session) {
        return new ArrayList<>(getContacts(session).values());
    }
}

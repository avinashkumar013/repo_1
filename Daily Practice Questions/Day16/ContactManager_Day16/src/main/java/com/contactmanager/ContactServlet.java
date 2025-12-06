package com.contactmanager;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        try {
            if ("add".equals(action)) {
                handleAdd(request, session);
                request.setAttribute("message", "Contact added successfully!");
                request.setAttribute("messageType", "success");
            } else if ("update".equals(action)) {
                handleUpdate(request, session);
                request.setAttribute("message", "Contact updated successfully!");
                request.setAttribute("messageType", "success");
            } else if ("delete".equals(action)) {
                handleDelete(request, session);
                request.setAttribute("message", "Contact deleted successfully!");
                request.setAttribute("messageType", "success");
            }
        } catch (Exception e) {
            request.setAttribute("message", "Error: " + e.getMessage());
            request.setAttribute("messageType", "error");
        }

        request.getRequestDispatcher("contacts.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        if ("edit".equals(action)) {
            String id = request.getParameter("id");
            Contact contact = ContactDAO.getContact(session, id);
            request.setAttribute("editContact", contact);
        }

        request.getRequestDispatcher("contacts.jsp").forward(request, response);
    }

    private void handleAdd(HttpServletRequest request, HttpSession session) throws Exception {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Name is required");
        }

        Contact contact = new Contact();
        contact.setName(name.trim());
        contact.setEmail(email != null ? email.trim() : "");
        contact.setPhone(phone != null ? phone.trim() : "");
        contact.setAddress(address != null ? address.trim() : "");

        ContactDAO.addContact(session, contact);
    }

    private void handleUpdate(HttpServletRequest request, HttpSession session) throws Exception {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");

        if (name == null || name.trim().isEmpty()) {
            throw new Exception("Name is required");
        }

        Contact contact = new Contact();
        contact.setName(name.trim());
        contact.setEmail(email != null ? email.trim() : "");
        contact.setPhone(phone != null ? phone.trim() : "");
        contact.setAddress(address != null ? address.trim() : "");

        boolean updated = ContactDAO.updateContact(session, id, contact);
        if (!updated) {
            throw new Exception("Contact not found");
        }
    }

    private void handleDelete(HttpServletRequest request, HttpSession session) throws Exception {
        String id = request.getParameter("id");
        boolean deleted = ContactDAO.deleteContact(session, id);
        if (!deleted) {
            throw new Exception("Contact not found");
        }
    }
}

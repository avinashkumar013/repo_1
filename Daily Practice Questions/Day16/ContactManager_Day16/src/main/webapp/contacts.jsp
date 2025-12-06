<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.contactmanager.*, com.google.gson.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Manager - Sprint 2</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
</head>
<body>
    <div class="container">
        <header>
            <h1><i class="fas fa-address-book"></i> Contact Manager</h1>
            <p>Manage your contacts efficiently</p>
        </header>

        <!-- Success/Error Messages -->
        <%
            String message = request.getParameter("message");
            String error = request.getParameter("error");
            if (message != null && !message.isEmpty()) {
        %>
            <div class="alert alert-success">
                <i class="fas fa-check-circle"></i> <%= message %>
            </div>
        <%
            }
            if (error != null && !error.isEmpty()) {
        %>
            <div class="alert alert-error">
                <i class="fas fa-exclamation-circle"></i> <%= error %>
            </div>
        <%
            }
        %>

        <!-- Add Contact Button -->
        <div class="button-container">
            <button id="addContactBtn" class="btn btn-primary">
                <i class="fas fa-plus"></i> Add Contact
            </button>
        </div>

        <!-- Contact Form -->
        <div id="contactForm" class="form-container" style="display: none;">
            <form action="ContactServlet" method="post" id="contactFormElement">
                <h2 id="formTitle">Add New Contact</h2>
                
                <input type="hidden" name="action" id="formAction" value="add">
                <input type="hidden" name="id" id="contactId" value="">

                <div class="form-group">
                    <label for="name"><i class="fas fa-user"></i> Name *</label>
                    <input type="text" id="name" name="name" required 
                           placeholder="Enter full name">
                </div>

                <div class="form-group">
                    <label for="email"><i class="fas fa-envelope"></i> Email</label>
                    <input type="email" id="email" name="email" 
                           placeholder="Enter email address">
                </div>

                <div class="form-group">
                    <label for="phone"><i class="fas fa-phone"></i> Phone</label>
                    <input type="tel" id="phone" name="phone" 
                           placeholder="Enter phone number">
                </div>

                <div class="form-group">
                    <label for="address"><i class="fas fa-map-marker-alt"></i> Address</label>
                    <textarea id="address" name="address" rows="3" 
                              placeholder="Enter address"></textarea>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-success">
                        <i class="fas fa-save"></i> Save Contact
                    </button>
                    <button type="button" class="btn btn-secondary" id="cancelBtn">
                        <i class="fas fa-times"></i> Cancel
                    </button>
                </div>
            </form>
        </div>

        <!-- Contacts Display -->
        <div class="contacts-section">
            <%
                ContactDAO dao = new ContactDAO();
                List<Contact> contacts = dao.getAllContacts(session);
                
                if (contacts == null || contacts.isEmpty()) {
            %>
                <div class="no-contacts">
                    <i class="fas fa-address-book"></i>
                    <p>No contacts yet. Click "Add Contact" to get started!</p>
                </div>
            <%
                } else {
            %>
                <h2>My Contacts <span class="contact-count">(<%=contacts.size()%>)</span></h2>
                <div class="contacts-grid">
                <%
                    for (Contact contact : contacts) {
                        String initials = ContactHelper.getInitials(contact.getName());
                        String avatarColor = ContactHelper.getAvatarColor(contact.getName());
                %>
                    <div class="contact-card">
                        <div class="contact-avatar" style="background-color: <%= avatarColor %>;">
                            <%= initials %>
                        </div>
                        <div class="contact-info">
                            <h3><%= ContactHelper.escapeHtml(contact.getName()) %></h3>
                            <%
                                if (contact.getEmail() != null && !contact.getEmail().isEmpty()) {
                            %>
                                <p><i class="fas fa-envelope"></i> <%= ContactHelper.escapeHtml(contact.getEmail()) %></p>
                            <%
                                }
                                if (contact.getPhone() != null && !contact.getPhone().isEmpty()) {
                            %>
                                <p><i class="fas fa-phone"></i> <%= ContactHelper.formatPhone(contact.getPhone()) %></p>
                            <%
                                }
                                if (contact.getAddress() != null && !contact.getAddress().isEmpty()) {
                            %>
                                <p><i class="fas fa-map-marker-alt"></i> <%= ContactHelper.escapeHtml(contact.getAddress()) %></p>
                            <%
                                }
                            %>
                        </div>
                        <div class="contact-actions">
                            <button class="btn-icon btn-edit" onclick="editContact('<%= contact.getId() %>')">
                                <i class="fas fa-edit"></i>
                            </button>
                            <button class="btn-icon btn-delete" onclick="deleteContact('<%= contact.getId() %>', '<%= ContactHelper.escapeHtml(contact.getName()) %>')">
                                <i class="fas fa-trash"></i>
                            </button>
                        </div>
                    </div>
                <%
                    }
                %>
                </div>
            <%
                }
            %>
        </div>
    </div>

    <script>
        // Show form
        document.getElementById('addContactBtn').addEventListener('click', function() {
            document.getElementById('contactForm').style.display = 'block';
            document.getElementById('formTitle').textContent = 'Add New Contact';
            document.getElementById('formAction').value = 'add';
            document.getElementById('contactId').value = '';
            document.getElementById('contactFormElement').reset();
        });

        // Hide form
        document.getElementById('cancelBtn').addEventListener('click', function() {
            document.getElementById('contactForm').style.display = 'none';
            document.getElementById('contactFormElement').reset();
        });

        // Edit contact
        function editContact(id) {
            <%
                Gson gson = new Gson();
                String contactsJson = gson.toJson(contacts);
            %>
            const contacts = <%= contactsJson %>;
            const contact = contacts.find(c => c.id === id);
            
            if (contact) {
                document.getElementById('contactForm').style.display = 'block';
                document.getElementById('formTitle').textContent = 'Edit Contact';
                document.getElementById('formAction').value = 'update';
                document.getElementById('contactId').value = contact.id;
                document.getElementById('name').value = contact.name;
                document.getElementById('email').value = contact.email || '';
                document.getElementById('phone').value = contact.phone || '';
                document.getElementById('address').value = contact.address || '';
                
                window.scrollTo({ top: 0, behavior: 'smooth' });
            }
        }

        // Delete contact
        function deleteContact(id, name) {
            if (confirm('Are you sure you want to delete ' + name + '?')) {
                const form = document.createElement('form');
                form.method = 'POST';
                form.action = 'ContactServlet';
                
                const actionInput = document.createElement('input');
                actionInput.type = 'hidden';
                actionInput.name = 'action';
                actionInput.value = 'delete';
                
                const idInput = document.createElement('input');
                idInput.type = 'hidden';
                idInput.name = 'id';
                idInput.value = id;
                
                form.appendChild(actionInput);
                form.appendChild(idInput);
                document.body.appendChild(form);
                form.submit();
            }
        }

        // Auto-hide messages after 5 seconds
        window.addEventListener('load', function() {
            const alerts = document.querySelectorAll('.alert');
            alerts.forEach(alert => {
                setTimeout(() => {
                    alert.style.display = 'none';
                }, 5000);
            });
        });
    </script>
</body>
</html>

package com.contactmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContactHelper {
    
    /**
     * Get initials from a name
     * Example: "John Doe" -> "JD"
     */
    public static String getInitials(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "?";
        }
        
        String[] words = name.trim().split("\\s+");
        StringBuilder initials = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                initials.append(word.charAt(0));
            }
        }
        
        return initials.toString().toUpperCase().substring(0, Math.min(2, initials.length()));
    }
    
    /**
     * Format phone number
     * Example: "1234567890" -> "(123) 456-7890"
     */
    public static String formatPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return "";
        }
        
        // Remove all non-digit characters
        String digits = phone.replaceAll("\\D", "");
        
        if (digits.length() == 10) {
            return String.format("(%s) %s-%s", 
                digits.substring(0, 3), 
                digits.substring(3, 6), 
                digits.substring(6));
        }
        
        // Return original if not 10 digits
        return phone;
    }
    
    /**
     * Escape HTML to prevent XSS attacks
     * Example: "<script>" -> "&lt;script&gt;"
     */
    public static String escapeHtml(String text) {
        if (text == null) {
            return "";
        }
        
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#x27;");
    }
    
    /**
     * Check if email is valid (basic validation)
     */
    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }
    
    /**
     * Check if phone is valid (basic validation)
     */
    public static boolean isValidPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            return false;
        }
        
        // Remove all non-digit characters
        String digits = phone.replaceAll("\\D", "");
        
        // Check if 10 digits
        return digits.length() == 10;
    }
    
    /**
     * Truncate text to specified length
     * Example: truncate("Hello World", 5) -> "Hello..."
     */
    public static String truncate(String text, int maxLength) {
        if (text == null) {
            return "";
        }
        
        if (text.length() <= maxLength) {
            return text;
        }
        
        return text.substring(0, maxLength) + "...";
    }
    
    /**
     * Capitalize first letter of each word
     * Example: "john doe" -> "John Doe"
     */
    public static String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        
        String[] words = text.split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                result.append(Character.toUpperCase(word.charAt(0)))
                      .append(word.substring(1).toLowerCase())
                      .append(" ");
            }
        }
        
        return result.toString().trim();
    }
    
    /**
     * Get current timestamp formatted
     */
    public static String getCurrentTimestamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
    
    /**
     * Generate a random avatar color based on name
     */
    public static String getAvatarColor(String name) {
        if (name == null || name.isEmpty()) {
            return "#667eea";
        }
        
        String[] colors = {
            "#667eea", "#764ba2", "#f093fb", "#4facfe",
            "#43e97b", "#fa709a", "#fee140", "#30cfd0"
        };
        
        int hash = name.hashCode();
        int index = Math.abs(hash) % colors.length;
        return colors[index];
    }
    
    /**
     * Check if string is null or empty
     */
    public static boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }
    
    /**
     * Get safe string (never null)
     */
    public static String safeString(String text) {
        return text == null ? "" : text;
    }
}

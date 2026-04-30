package models;

public class User {
    private int userId;
    private String name;
    private String email;
    private String phone;

    public User(int userId, String name, String email, String phone) {
        this.userId = userId;
        setName(name);
        setEmail(email);
        setPhone(phone);
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            System.out.println("  [WARNING] User name cannot be empty. Using 'Unknown User'.");
            this.name = "Unknown User";
        }
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@") && email.contains(".")) {
            this.email = email;
        } else {
            System.out.println("  [WARNING] Invalid email '" + email + "'. Using default@example.com");
            this.email = "default@example.com";
        }
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.isEmpty()) {
            this.phone = phone;
        } else {
            System.out.println("  [WARNING] Phone cannot be empty. Using 000-000-0000");
            this.phone = "000-000-0000";
        }
    }
}
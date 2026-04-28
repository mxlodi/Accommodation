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

    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }

    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown User";
        }
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        } else {
            this.email = "default@example.com";
        }
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.isEmpty()) {
            this.phone = phone;
        } else {
            this.phone = "000-000-0000";
        }
    }
}

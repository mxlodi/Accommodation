public class User {
    int userId;     
    String name;    
    String email;   
    String phone;   


      
    // CONSTRUCTOR
    public User(int userId, String name, String email, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    
    // GETTERS 
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
    
    // SETTERS with validation
    public void setName(String name) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        }
    }
    
    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }
    
    public void setPhone(String phone) {
        if (phone != null && !phone.isEmpty()) {
            this.phone = phone;
        }
    }
}


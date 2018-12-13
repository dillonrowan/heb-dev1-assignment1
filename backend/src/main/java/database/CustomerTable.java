package database;

public class CustomerTable {
    private String lastName;
    private String firstName;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private java.util.Date lstEmailSent;
    private int custID;

    //Class Getters
    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public java.util.Date getLstEmailSent() {
        return lstEmailSent;
    }

    public int getCustID() {
        return custID;
    }

    //Class Setters
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setLstEmailSent(java.util.Date lstEmailSent) {
        this.lstEmailSent = lstEmailSent;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }
}

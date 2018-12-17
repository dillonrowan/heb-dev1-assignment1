package database;


import java.util.Objects;
import java.text.SimpleDateFormat;

public class CustomerTable {

    private String lastName;
    private String firstName;
    private String email;
    private String address;
    private String city;
    private String state;
    private String zip;
    private java.sql.Timestamp lstEmailSent;
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

    public java.sql.Timestamp getLstEmailSent() {
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

    public void setLstEmailSent(java.sql.Timestamp lstEmailSent) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sdf.format(lstEmailSent);
        this.lstEmailSent = lstEmailSent;
    }

    public void setCustID(int custID) {
        this.custID = custID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerTable that = (CustomerTable) o;
        return custID == that.custID &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(address, that.address) &&
                Objects.equals(city, that.city) &&
                Objects.equals(state, that.state) &&
                Objects.equals(zip, that.zip) &&
                Objects.equals(lstEmailSent, that.lstEmailSent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, email, address, city, state, zip, lstEmailSent, custID);
    }

    @Override
    public String toString() {

        return "Customer #" + getCustID() +'\n' +
                getLastName() + " " + getFirstName() + '\n' +
                getEmail() + '\n' +
                getAddress() + '\n' +
                getCity() + ", " + getState() + " " + getZip() + '\n';
    }
}

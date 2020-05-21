/**
 * @author Sebastian Kazun
 */
import java.util.Date;


/**
 * Data that represents a unique person
 */
public class PersonInfo {

    private String firstName;
    private String lastName;
    private String homePhone;
    private String mobilePhone;
    private String streetAddress;
    private String city;
    private String state;
    private int zip;
    private Date dob;

    public PersonInfo(){}

    public PersonInfo(String firstName, String lastName, String homePhone, String mobilePhone,
                      String streetAddress, String city, String state, int zip, Date dob)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.dob = dob;
    }

    //getters
    public String getFirstName()
    {
        return firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public String getHomePhone()
    {
        return homePhone;
    }

    public String getMobilePhone()
    {
        return mobilePhone;
    }

    public String getStreetAddress()
    {
        return streetAddress;
    }

    public String getCity()
    {
        return city;
    }

    public String getState()
    {
        return state;
    }

    public int getZip()
    {
        return zip;
    }

    public Date getDOB()
    {
        return dob;
    }

    //setters
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setHomePhone(String homePhone)
    {
        this.homePhone = homePhone;
    }

    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }

    public void setStreetAddress(String streetAddress)
    {
        this.streetAddress = streetAddress;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public void setState(String state)
    {
        this.state = state;
    }

    public void setZip(int zip)
    {
        this.zip = zip;
    }

    public void setDOB(Date dob)
    {
        this.dob = dob;
    }


}

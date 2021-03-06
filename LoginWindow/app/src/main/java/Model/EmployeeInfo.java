package Model;

// -------------------------------------------------------------------------
/**
 * Write a one-sentence summary of your class here. Follow it with additional
 * details about its purpose, what abstraction it represents, and how to use it.
 *
 * @author Emma Manchester, Updated- Meghan April 22nd
 * @version Apr 19, 2016
 */
public class EmployeeInfo
{
    private int employeeID;  // id for employee
    private int manager;
    private int inactive;
    private String SSN;         // social security number for employee
    private String phone;       // phone number
    private String address;     // address
    private String firstName;   // first name of employee
    private String lastName;    // last name for employee
    private double  salary;      // salary (per hour)
    private double  hours;       // number of hours worked
    private String password;    // password for employee profile


    // ----------------------------------------------------------
    /**
     * Create a new EmployeeInfo object.
     * @param employeeID id for employee
     * @param SSN social security number
     * @param phone phone number
     * @param address address
     * @param firstName employee first name
     * @param lastName employee last name
     * @param salary employee salary
     * @param hours number of hours worked
     * @param password password of employee
     */
    public EmployeeInfo(
        int employeeID,
        int manager,
        int inactive,
        String SSN,
        String phone,
        String address,
        String firstName,
        String lastName,
        double salary,
        double hours,
        String password)
    {
        this.manager = manager;
        this.inactive = inactive;
        this.employeeID = employeeID;
        this.SSN = SSN;
        this.phone = phone;
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.hours = hours;
        this.password = password;
    }

    public int getManager() { return manager; }

    public void setManager(int state) { manager = state; }

    public int getInactive() { return inactive; }

    public void setInactive(int state) { inactive = state; }

    public int getEmployeeID()
    {
        return employeeID;
    }

    public void setEmployeeID(int id)
    {
       this.employeeID = id;
    }

    public String getSSN()
    {
        return SSN;
    }

    public void setSSN(String ssn)
    {
        this.SSN = ssn;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String name)
    {
        this.firstName = name;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String name)
    {
        this.lastName = name;
    }

    public double getSalary()
    {
        return salary;
    }

    public void setSalary(double salary)
    {
        this.salary = salary;
    }

    public double getHours()
    {
        return hours;
    }

    public void setHours(double hrs)
    {
        this.hours = hrs;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String pass)
    {
        this.password = pass;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String addr)
    {
        this.address = addr;
    }
}

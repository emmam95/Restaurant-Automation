/**
 * The Employee class has an object of the EmployeeInfo class.
 * It creates and updates the employee profile.
 * It also calculates the paycheck for the employees.
 */
public class Employee {

    private EmployeeInfo employeeInfo; //an object of the EmployeeInfo class

    /**
     * Create a new Employee object.
     */
    public Employee(int employeeID,
        String ssn, String phone, String address,
        String firstname, String lastname, double salary,
        double hours, String password)
    {
        employeeInfo = new EmployeeInfo(employeeID, ssn, phone,
            address, firstname, lastname, salary, hours, password);
    }

    /**
     * This method returns the employeeInfo object.
     */
    public EmployeeInfo getEmployeeInfo(){
        return employeeInfo;
    }


    /**
     * This method updates the Employee profile.
     */
    public void updateEmployeeInfo(int employeeID,
            String ssn, String phone, String address,
            String firstname, String lastname, float salary,
            float hours, String password){

        employeeInfo.setEmployeeID(employeeID);
        employeeInfo.setSSN(ssn);
        employeeInfo.setPhone(phone);
        employeeInfo.setAddress(address);
        employeeInfo.setFirstName(firstname);
        employeeInfo.setLastName(lastname);
        employeeInfo.setSalary(salary);
        employeeInfo.setHours(hours);
        employeeInfo.setPassword(password);

    }

    /**
     * This method calculates the employee's salary.
     */
    public double calculateSalary(EmployeeInfo emp)
    {
        double num_of_hours = emp.getHours();
        double emp_salary;
        emp_salary = num_of_hours * emp.getSalary(); //hourly wage is $10
        emp.setHours(0);
        emp.setSalary(emp_salary);
        return emp_salary;
    }
}
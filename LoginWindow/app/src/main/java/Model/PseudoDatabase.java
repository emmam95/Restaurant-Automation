package Model;
import java.util.*;

public class PseudoDatabase
{
    private Map<Integer, Employee> Map_ID_Employee;

    int idCounter = 0;
    public PseudoDatabase()
    {
        Map_ID_Employee = new HashMap<Integer, Employee>();
    }

    public int generateID()
    {
        idCounter++;
        return idCounter;
    }

    public void addEmployee(Employee employee)
    {
        assert (checkForUniqueID(employee.getEmployeeInfo().getEmployeeID()));
        Map_ID_Employee.put(
            employee.getEmployeeInfo().getEmployeeID(),
            employee);
    }


    public void removeEmployee(Employee employee)
    {
        if (!Map_ID_Employee.containsValue(employee))
        {
            // display to user that error occurred
            System.out.println("Error, cannot remove this employee");
            return;
        }
        Map_ID_Employee.remove(employee.getEmployeeInfo().getEmployeeID());
    }


    public void removeEmployeeById(int id)
    {
        if (!Map_ID_Employee.containsKey(id))
        {
            // display to user that error occurred
            System.out.println("Error, cannot remove this employee");
            return;
        }
        Map_ID_Employee.remove(id);
    }


    // Will return the employee or NULL if there is no employee with that id
    public Employee getEmployee(int id)
    {
        if (Map_ID_Employee.get(id) == null)
        {
            System.out.println("Error, cannot retrieve an employee by this ID");
        }
        return Map_ID_Employee.get(id);
    }


    /**
     * This method makes sure an ID entered is truly unique To be used for error
     * checking in the pseudo interface Returns true if the ID is unique
     */
    public boolean checkForUniqueID(int id)
    {
        return !Map_ID_Employee.containsKey(id);
    }


    public Map<Integer, Employee> getMap()
    {
        return Map_ID_Employee;
    }

}

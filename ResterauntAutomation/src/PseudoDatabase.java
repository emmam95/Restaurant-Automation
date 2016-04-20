import java.util.*;

public class PseudoDatabase
{
    private Map<Integer, Employee> Map_ID_Employee;

    public PseudoDatabase()
    {
        Map_ID_Employee = new HashMap<Integer, Employee>();
      //does anything need to be here? idk
    }

    public void addEmployee(Employee bob){
      Map_ID_Employee.put(bob.getEmployeeInfo().getEmployeeID(), bob);
    }

    public void removeEmployee(Employee bob){
      Map_ID_Employee.remove(bob.getEmployeeInfo().getEmployeeID());
    }

    public void removeEmployeeById(int id){
      Map_ID_Employee.remove(id);
    }

    //Will return the employee or NULL if there is no employee with that id
    public Employee getEmployee(int id)
    {
      return Map_ID_Employee.get(id);
    }

}
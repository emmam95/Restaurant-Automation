import java.util.*

public class PseudoDatabase
{
    private HashMap<Integer, Employee> Map_ID_Employee;
    
    public PseudoDatabase(){
      //does anything need to be here? idk
    }
    
    public void addEmployee(Employee bob){
      Map_ID_Employee.put(bob.getEmpolyeeInfo().getEmployeeID(), bob);  
    }
    
    public void removeEmployee(Employee bob){
      Map_ID_Employee.remove(bob.getEmployeeInfo().getEmployeeID());
    }
    
    public void removeEmployeeById(int id){
      Map_ID_Employee.remove(id);
    }
    
    //Will return the employee or NULL if there is no employee with that id
    public Employee getEmployee(int id){
      return Map_ID_Employee.get(id);
    }
    
    public void printEmployees(){
      for (key in Map_ID_Employee.keySet()){
        EmployeeInfo info = Map_ID_Employee.get(key).getEmployeeInfo();
        StringBuilder s = new StringBuilder(info.getEmployeeID());
        s.append(" - ");
        s.append(temp.getFirstName())
        s.append(temp.getLastName());
        System.out.println(s);
      }
    }
}

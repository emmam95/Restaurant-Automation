import junit.framework.*;


public class EmployeeInfoTest extends TestCase
{
    private EmployeeInfo emp;

    public EmployeeInfoTest()
    {
        // don't initialize anything
    }

    public void setUp()
    {
        emp = new EmployeeInfo(12345, "123-45-6789", "540-123-4567",
            "123 Address Dr, Blacksburg VA, 24060", "Bob", "Jones",
            7.25, 40.0, "password");
    }

    public void testPassword()
    {
        assertEquals("password", emp.getPassword());
        emp.setPassword("qwerty");
        assertEquals("qwerty", emp.getPassword());
    }

    public void testHours()
    {
        assertEquals(40.0, emp.getHours(), 0.01);
        emp.setHours(0.00);
        assertEquals(0.00 , emp.getHours(), 0.01);
    }

    public void testSalary()
    {
        assertEquals(7.25, emp.getSalary(), 0.01);
        emp.setSalary(8.15);
        assertEquals(8.15, emp.getSalary(), 0.01);
    }

    public void testLastName()
    {
        assertEquals("Jones", emp.getLastName());
        emp.setLastName("Smith");
        assertEquals("Smith", emp.getLastName());
    }

    public void testFirstName()
    {
        assertEquals("Bob", emp.getFirstName());
        emp.setFirstName("Bill");
        assertEquals("Bill", emp.getFirstName());
    }

    public void testAddress()
    {
        assertEquals("123 Address Dr, Blacksburg VA, 24060", emp.getAddress());
        emp.setAddress("234 Address Dr, Blacksburg VA, 24060");
        assertEquals("234 Address Dr, Blacksburg VA, 24060", emp.getAddress());
    }
    public void testID()
    {
        assertEquals(12345, emp.getEmployeeID());
        emp.setEmployeeID(1111);
        assertEquals(1111, emp.getEmployeeID());
    }

    public void testSSN()
    {
        assertEquals("123-45-6789", emp.getSSN());
        emp.setSSN("111-22-3333");
        assertEquals("111-22-3333", emp.getSSN());
    }

    public void testPhone()
    {
        assertEquals("540-123-4567", emp.getPhone());
        emp.setPhone("111-222-3333");
        assertEquals("111-222-3333", emp.getPhone());
    }

}

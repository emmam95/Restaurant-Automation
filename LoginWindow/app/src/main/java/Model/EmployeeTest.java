package Model;

import junit.framework.*;

public class EmployeeTest
    extends TestCase
{
    private Employee bob;

    public EmployeeTest()
    {
        // nothing to initialize
    }

    public void setUp()
    {
        bob =
            new Employee(
                123,
                0,
                0,
                "123-45-6789",
                "540-123-4567",
                "123 Address Dr, Blacksburg VA, 24060",
                "Bob",
                "Smith",
                10.00,
                34.75,
                "password");
    }

    public void testUpdate()
    {
        bob.updateEmployeeInfo(
            123,
            "123-45-6789",
            "540-123-4567",
            "123 Address Dr, Blacksburg VA, 24060",
            "Bob",
            "Smith",
            11.50, // gave bob a raise
            34.75,
            "password");
        assertEquals(123, bob.getEmployeeInfo().getEmployeeID());
        assertEquals("123-45-6789", bob.getEmployeeInfo().getSSN());
        assertEquals("540-123-4567", bob.getEmployeeInfo().getPhone());
        assertEquals("123 Address Dr, Blacksburg VA, 24060", bob
            .getEmployeeInfo().getAddress());
        assertEquals("Bob", bob.getEmployeeInfo().getFirstName());
        assertEquals("Smith", bob.getEmployeeInfo().getLastName());
        assertEquals(11.50, bob.getEmployeeInfo().getSalary(), 0.01);
        assertEquals(34.75, bob.getEmployeeInfo().getHours(), 0.01);
        assertEquals("password", bob.getEmployeeInfo().getPassword());
    }

    public void testSalaryCalculator()
    {
        assertEquals(347.5, bob.calculateSalary(), 0.01);
    }
}

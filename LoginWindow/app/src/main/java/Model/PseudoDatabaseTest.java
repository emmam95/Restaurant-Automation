package Model;

import junit.framework.*;

public class PseudoDatabaseTest extends TestCase
{
    private PseudoDatabase dbase;

    public PseudoDatabaseTest()
    {
        // nothing to initialize
    }

    public void setUp()
    {
        dbase = new PseudoDatabase();
    }

    public void testAdd()
    {
        Employee bob =
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
        assertTrue(dbase.checkForUniqueID(123));
        dbase.addEmployee(bob);
        assertTrue(dbase.getMap().containsValue(bob));
        assertTrue(dbase.getMap().containsKey(123));
        assertFalse(dbase.checkForUniqueID(123));
    }

    public void testRemove()
    {
        Employee bob =
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
        Employee bill =
            new Employee(
                111,
                0,
                0,
                "111-22-6789",
                "540-123-4567",
                "123 Address Dr, Blacksburg VA, 24060",
                "Bill",
                "Smith",
                10.00,
                34.75,
                "password");
        // try to remove, but can't
        dbase.removeEmployee(bob);
        assertTrue(dbase.checkForUniqueID(123)); // ID should be unique still
        assertEquals(0, dbase.getMap().size()); // map size should be 0
        // test successful removal
        dbase.addEmployee(bob);
        dbase.addEmployee(bill);
        dbase.removeEmployee(bob);
        assertEquals(1, dbase.getMap().size());

    }

    public void testRemoveByID()
    {
        Employee bob =
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
        Employee bill =
            new Employee(
                111,
                0,
                0,
                "111-22-6789",
                "540-123-4567",
                "123 Address Dr, Blacksburg VA, 24060",
                "Bill",
                "Smith",
                10.00,
                34.75,
                "password");
        // try to remove, but can't
        dbase.removeEmployeeById(123);
        assertTrue(dbase.checkForUniqueID(123)); // ID should be unique still
        assertEquals(0, dbase.getMap().size()); // map size should be 0
        // test successful removal
        dbase.addEmployee(bob);
        dbase.addEmployee(bill);
        dbase.removeEmployeeById(123);
        assertEquals(1, dbase.getMap().size());
    }

    public void testGetEmployee()
    {
        Employee bob =
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
        assertNull(dbase.getEmployee(123));
        dbase.addEmployee(bob);
        assertEquals(bob, dbase.getEmployee(123));
    }
}

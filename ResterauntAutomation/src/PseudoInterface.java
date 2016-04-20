
// -------------------------------------------------------------------------
/**
 *  Simulate an interface
 *  Simulates user pressing buttons, getting data, etc.
 *
 *  @author Emma Manchester
 *  @version Apr 20, 2016
 */
public class PseudoInterface
{
    private PseudoDatabase dbase;
    public PseudoInterface()
    {
        dbase = new PseudoDatabase();
    }

    // chain of events for editing an employee profile
    // 1. User pushes edit employee profile
    // 2. User searches for an employee to edit by ID
    // 3. Editing screen displays for user, user presses save to store info
    public Employee searchByID()
    {
        // use the hash map to search by ID, get Employee from database
        // MUST have a valid ID (not null) to continue
        return null;
    }
    public void saveChanges(/* all the info (old and updated*/ /* employee to change */)
    {
        // save changes to database
        // gets info from text fields (parameters in this case)
        // must do error checking, no fields can be blank
    }

    // the main success scenario for editing a profile
    public void main_editing()
    {
        // search
        // save changes
    }

    // chain of events for adding employee profile
    // 1. User selects add employee
    // 2. User enters all text fields
    // 3. Save data to database

    // the main success scenario for adding a profile
    public void main_adding()
    {
        // probably use the same save changes/error checking
        // create a new employee
        // add to database
    }

    // chain of events for deleting employee profile
    // 1. User selects option to delete profile
    // 2. User searches for profile to delete
    // 3. User chooses to delete profile

    public boolean areYouSureYouWantToDelete(boolean option)
    {
        return option;
    }

    // main success scenario for deleting profile
    public void main_deleting()
    {
        // use same search by id
        // if option is true,
        // remove from database
        // otherwise don't do anything
    }

    // process calculating the paycheck for a specific employee
    public void main_pay()
    {
        // use same search by ID to find employee to pay
        // calculate payment
        // display payment to user
    }
}

package Model;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import meghankh.loginwindow.R;

/**
 * Created by Meghan on 4/22/2016.
 */
public class FetchData {

    Employee[] employeeArr;
    int numberOfEmployee;

    public FetchData()
    {
        employeeArr = new Employee[128];
        numberOfEmployee = 0;
    }

    public int parseData(Context context) {
        File path = context.getFilesDir();
        File file = new File(path, "employee.txt");
        if (file.length() == 0) { //if empty create new file
            try {
                file.createNewFile();
            } catch (IOException e) {
                return -1;
            }
            int length = (int) file.length();
            //stores whatever is in the text file
            byte[] bytes = new byte[length];
            try {
                FileInputStream in = new FileInputStream(file);
                try {
                    in.read(bytes);
                    //for debugging
                    String result = "";
                    for (int i = 0; i < length; i++) {
                        result += (char) bytes[i];
                    }
                    Log.d("TEST", "RESULT IS: " + result);
                }
                catch (IOException e) {
                    //
                }
                finally {
                    in.close();
                }
            }
            catch (IOException e) {
                //
            }
        }
        else {
            int length = (int) file.length();
            byte[] bytes = new byte[length];
            try {
                FileInputStream in = new FileInputStream(file);
                try {
                    in.read(bytes); //stores text file contents in array
                    String result = "";
                    for (int i = 0; i < length; i++) {
                        result += (char) bytes[i];
                    }
                    Log.d("TEST", "RESULT IS: " + result);
                }
                catch (IOException e) {
                    //
                }
                finally {
                    in.close();
                }
            }
            catch (IOException e) {
                //
            }
            Log.d("TEST", "Initialize fields");
            int numRecords = 0;
            int index = 0;
            int employeeID = 0;
            int manager = 0;
            int inactive = 0;
            String ssn = "none";
            String phone = "none";
            String address = "none";
            String firstName = "none";
            String lastName = "none";
            double salary = 0.0;
            double hours = 0.0;
            String password = "none";
            int column = 0;
            String currentCell = "";
            Log.d("TEST", "Traverse Array of " + length);
            while (index < length) {
                Log.d("TEST", index + " " + (char) bytes[index]);
                if (bytes[index] == 36) { //if it is a money sign indicating a new employee
                    String value = currentCell;
                    currentCell = "";
                    password = value; //password is last cell before money sign
                    Log.d("TEST", "STORED AT" + employeeID);
                    //create the employee object and store it in the array
                    employeeArr[employeeID] = new Employee(employeeID, manager, inactive, ssn, phone, address, firstName, lastName, salary, hours, password);
                    //clear data for new object
                    employeeID = 0;
                    manager = 0;
                    inactive = 0;
                    ssn = "none";
                    phone = "none";
                    address = "none";
                    firstName = "none";
                    lastName = "none";
                    salary = 0.0;
                    hours = 0.0;
                    password = "none";
                    numRecords++;
                    column = 0;
                }
                else if (bytes[index] == 124) { //if | indicates new cell
                    String value = currentCell;
                    Log.d("TEST", "Value of column: " + column + " is " + value);
                    switch (column) {
                        case 0:
                            employeeID = new Integer(value).intValue();
                            break;
                        case 1:
                            manager = new Integer(value).intValue();
                            break;
                        case 2:
                            inactive = new Integer(value).intValue();
                            break;
                        case 3:
                            ssn = value;
                            break;
                        case 4:
                            phone = value;
                            break;
                        case 5:
                            address = value;
                            break;
                        case 6:
                            firstName = value;
                            break;
                        case 7:
                            lastName = value;
                            break;
                        case 8:
                            salary = new Double(value).doubleValue();
                            break;
                        case 9:
                            hours = new Double(value).doubleValue();
                            break;
                        default:
                            Log.d("TEST", "Something BAD Happened.");
                            break;
                    }
                    // Log.d("Test", "Clear Cell inc column to: " + column);
                    currentCell = "";
                    column++;
                    Log.d("Test", "Clear Cell inc column to: " + column);
                }
                else {
                    currentCell += (char) bytes[index];
                }
                index++;
            }
            numberOfEmployee = numRecords;
        }
        Log.d("TEST", "Finish Fetch");
        return 1;
    }

    public Employee[] getEmployeeArray()
    {
        return employeeArr;
    }

    public int getNumberOfEmployee()
    {
        return numberOfEmployee;
    }

    public Employee getEmployee(int employeeID)
    {
        return employeeArr[employeeID];
    }

    public int lookUpEmployeeID(String firstName, String lastName)
    {
        Log.d("TEST", "Looking up " + firstName + " " + lastName);
        for (int i = 1; i < numberOfEmployee + 1; i++)
        {
            Log.d("TEST", "Checking: " + i);
            Employee CurrentEmployee = employeeArr[i];
            Log.d("TEST", "Got " + CurrentEmployee.getEmployeeInfo().getFirstName());
            if (CurrentEmployee.getEmployeeInfo().getFirstName().equals(firstName)
                && CurrentEmployee.getEmployeeInfo().getLastName().equals(lastName))
            {
                return i;
            }
        }
        return -1;
    }

    public void updateEmployee(Employee updatedEmployee)
    {
        employeeArr[updatedEmployee.getEmployeeInfo().getEmployeeID()] = updatedEmployee;
    }
}

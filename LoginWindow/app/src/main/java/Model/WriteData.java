package Model;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Meghan on 4/23/2016.
 */
public class WriteData {

public WriteData() {
    // Nothing.
}

public void updateDatabase(Context context, Employee[] EmployeeArray, int numberOfEmployees)
{
    Log.d("TEST", "Update Database");
    File path = context.getFilesDir();
    File file = new File(path, "employee.txt");
    Log.d("TEST", "Writing to employee.txt");
    try {
        FileOutputStream stream = new FileOutputStream(file);
        try {
            stream.flush(); //guarantee nothing inside buffer
            for (int i = 1; i < numberOfEmployees + 1; i ++)
            {
                Employee currentEmployee  = EmployeeArray[i];
                EmployeeInfo employeeInfo = currentEmployee.getEmployeeInfo();
                stream.write(Integer.toString(employeeInfo.getEmployeeID()).getBytes());
                stream.write("|".getBytes());
                stream.write(Integer.toString(employeeInfo.getManager()).getBytes());
                stream.write("|".getBytes());
                stream.write(Integer.toString(employeeInfo.getInactive()).getBytes());
                stream.write("|".getBytes());
                stream.write(employeeInfo.getSSN().getBytes());
                stream.write("|".getBytes());
                stream.write(employeeInfo.getPhone().getBytes());
                stream.write("|".getBytes());
                stream.write(employeeInfo.getAddress().getBytes());
                stream.write("|".getBytes());
                stream.write(employeeInfo.getFirstName().getBytes());
                stream.write("|".getBytes());
                stream.write(employeeInfo.getLastName().getBytes());
                stream.write("|".getBytes());
                stream.write(Double.toString(employeeInfo.getSalary()).getBytes());
                stream.write("|".getBytes());
                stream.write(Double.toString(employeeInfo.getHours()).getBytes());
                stream.write("|".getBytes());
                stream.write(employeeInfo.getPassword().getBytes());
                stream.write("$".getBytes());
            }
        }
        catch (IOException e) {
            // Nothing.
        }
        finally {
            stream.close();
        }
    }
    catch (IOException e) {
        //
    }
}

public void addEmployee(Context context, Employee employee)
{
    Log.d("TEST", "Entered write data class");
    File path = context.getFilesDir();
    File file = new File(path, "employee.txt");
    Log.d("TEST", "Writing to employee.txt");
    try {
        FileOutputStream stream = new FileOutputStream(file, true);
        Log.d("TEST", "Open FileStream.");
        try {
            stream.flush();
            EmployeeInfo employeeInfo = employee.getEmployeeInfo();
            Log.d("TEST", "SSN is :" + employeeInfo.getSSN());
            stream.write(Integer.toString(employeeInfo.getEmployeeID()).getBytes());
            stream.write("|".getBytes());
            stream.write(Integer.toString(employeeInfo.getManager()).getBytes());
            stream.write("|".getBytes());
            stream.write(Integer.toString(employeeInfo.getInactive()).getBytes());
            stream.write("|".getBytes());
            stream.write(employeeInfo.getSSN().getBytes());
            stream.write("|".getBytes());
            stream.write(employeeInfo.getPhone().getBytes());
            stream.write("|".getBytes());
            stream.write(employeeInfo.getAddress().getBytes());
            stream.write("|".getBytes());
            stream.write(employeeInfo.getFirstName().getBytes());
            stream.write("|".getBytes());
            stream.write(employeeInfo.getLastName().getBytes());
            stream.write("|".getBytes());
            stream.write(Double.toString(employeeInfo.getSalary()).getBytes());
            stream.write("|".getBytes());
            stream.write(Double.toString(employeeInfo.getHours()).getBytes());
            stream.write("|".getBytes());
            stream.write(employeeInfo.getPassword().getBytes());
            stream.write("$".getBytes());
        }
        catch (IOException e) {
            // Nothing.
        }
        finally {
            try {
                stream.close();
            }
            catch (IOException e) {
                // Nothing
            }
        }
    }
    catch (FileNotFoundException e) {
        // Nothing
    }
}

}

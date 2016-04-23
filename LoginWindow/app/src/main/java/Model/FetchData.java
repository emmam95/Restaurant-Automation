package Model;

import android.content.Context;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import meghankh.loginwindow.R;

/**
 * Created by Meghan on 4/22/2016.
 */
public class FetchData {

    Employee[] employeeArr;

    public FetchData()
    {
        employeeArr = new Employee[1024];
    }

    public int parseData(Context context)
    {
        File path = context.getFilesDir();
        File file = new File(path, "employee.txt");
        int length = (int) file.length();
        byte[] bytes = new byte[length];
        try {
            FileInputStream in = new FileInputStream(file);
            try{
                in.read(bytes);
            }
            catch (IOException e){
            }
            finally {
                in.close();
            }
        }
        catch (IOException e){
        }
        int numRecords = 0;
        int index = 0;
        int employeeID;
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
        byte[] currentCell = new byte[64];
        int cellIndex = 0;
        while (index < length)
        {
            if (bytes[index] == 36)
            {
                employeeID = numRecords;
                employeeArr[numRecords] = new Employee(employeeID, inactive, ssn, phone, address, firstName, lastName, salary, hours, password);
                numRecords++;
            }
            else if (bytes[index] == 124)
            {
                String value = new String(currentCell);
                switch (column)
                {
                    case 0: employeeID = new Integer(value).intValue();
                    case 1: inactive = new Integer(value).intValue();
                    case 2: ssn = value;
                    case 3: phone = value;
                    case 4: address = value;
                    case 5: firstName = value;
                    case 6: lastName = value;
                    case 7: salary = new Double(value).doubleValue();
                    case 8: hours = new Double(value).doubleValue();
                    case 9: password = value;
                }
                cellIndex = 0;
                currentCell = new byte[64];
                column++;
                if (column > 9)
                {
                    column = 0;
                }
            }
            else
            {
                currentCell[cellIndex] = bytes[index];
            }
        }
        return 1;
    }
}

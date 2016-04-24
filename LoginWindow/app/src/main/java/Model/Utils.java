package Model;

/**
 * Created by Meghan on 4/24/2016.
 */
public class Utils {
    public Utils()
    {
        // Do nothing.
    }

    public char[] converToCharArray(byte[] byteArray, int byteArrayLength)
    {
        char[] Chars = new char[byteArrayLength];
        for (int i = 0; i < byteArrayLength; i++)
        {
            Chars[i] = (char) byteArray[i];
        }
        return Chars;
    }
}

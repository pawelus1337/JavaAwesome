import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @author Paweł
 */
public class Main {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
        /* Trick 1 */
        Integer a = 1;
        Integer b = 1;
        System.out.println(a == b);

        Integer c = 128;
        Integer d = 128;
        System.out.println(c == d);
//======================================================================================================================
        /* Trick 2 */
        String immutable = "lupa";
        System.out.println(immutable);
        Field f = String.class.getDeclaredField("value");
        f.setAccessible(true);
        char[] string = (char[]) f.get(immutable);
        string[0] = 'd';
        System.out.println("lupa"); //this prints dupa
        System.out.println(immutable); //this also
//======================================================================================================================
        /* Trick 3 */
        if ((0.1 + 0.1) * 10 == 2)
            System.out.println("(0.1 + 0.1) * 10 == 2 of course is true");

        if ((0.1 + 0.1 + 0.1) * 10 != 3)
            System.out.println("(0.1 + 0.1 + 0.1) * 10 != 3, why?");
//======================================================================================================================
        /* Trick 4 */
        // \u000d System.out.println("This Comment Executed!");
//======================================================================================================================
        /* Trick 5 */
        //This method throw checked exception so we need to use try...catch
        try {
            throwException();
        } catch (IOException e) { }

        //We cant cast checked exception to unchecked
        //Error:(31, 34) java: incompatible types:
        // java.io.IOException cannot be converted to java.lang.RuntimeException
        //throw (RuntimeException) new IOException();

        //This method also throw checked exception and we don't have to handle the exception
        throwException2(new IOException());

    }

    static void throwException() throws IOException {
        throw new IOException();
    }

    static <T extends Throwable> void throwException2(Exception exception) throws T {
        throw (T) exception;
    }
}

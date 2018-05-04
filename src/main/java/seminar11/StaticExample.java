package seminar11;

/**
 * Created by Reaper on 22-04-2017.
 */
public class StaticExample {

    public static void main(String[] args) {
        System.out.println("Main");

        try {
            Class.forName("com.freeuni.oop.seminar11.MySQLDriver");
            new MySQLDriver();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class MySQLDriver {
    static {
        System.out.println("Driver has been initialized");
    }

    static {
        System.out.println("Another one");
    }
}

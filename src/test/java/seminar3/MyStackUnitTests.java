package seminar3;

import org.junit.*;
import seminar2.Fraction;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Unit Tests for MyStack class.
 * 100% statement coverage.
 */
public class MyStackUnitTests {

    // Used solely for demonstrating @Before and @After annotations
    private static int TEST_NUM = 0;

    @BeforeClass
    public static void init() {
        // Initialize shared data here, is not generally recommended
        System.out.println("Unit testing has started...");
    }

    @AfterClass
    public static void cleanup() {
        // Do any cleanup here if init() was used
        System.out.println("Unit testing has ended...");
    }

    @Before
    public void setUp() {
        // Used for setting up anything required for tests
        // This method is run BEFORE every unit test
        System.out.println("Unit test " + (++TEST_NUM) + " has started");
    }

    @After
    public void tearDown() {
        // This method is run AFTER every unit test
        System.out.println("Unit test " + TEST_NUM + " has ended");
    }

    @Test
    public void testSizeBasic() {
        MyStack<Fraction> stack = new MyStack<>();

        stack.push(new Fraction(2));
        stack.push(new Fraction(4, 3));

        int expectedSize = 2;
        assertEquals(expectedSize, stack.size());
    }

    @Test
    public void testSizeAdvanced() {
        MyStack<Integer> stack = new MyStack<>();

        for (int i = 0; i < 100; ++i) {
            stack.push(i);
        }

        for (int i = 0; i < 100; ++i) {
            stack.pop();
            assertEquals(99 - i, stack.size());
        }
    }

    @Test
    public void testSizeEdge1() {
        MyStack<Integer> stack = new MyStack<>();

        assertEquals(0, stack.size());
    }

    // This way we test exceptions
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testSizeEdge2() {
        MyStack<Integer> stack = new MyStack<>();

        stack.pop();

        assertEquals(0, stack.size());
    }

    @Test
    public void testPushPopBasic() {
        MyStack<Fraction> stack = new MyStack<>();

        Fraction frac1 = new Fraction(2);

        stack.push(frac1);

        int expectedSize = 1;
        assertEquals(expectedSize, stack.size());
        assertEquals(frac1, stack.peek());
        assertEquals(frac1, stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    public void testPushPopAdvanced1() {
        MyStack<Fraction> stack = new MyStack<>();

        Fraction frac1 = new Fraction(2);
        Fraction frac2 = new Fraction(3);
        Fraction frac3 = new Fraction(4);

        stack.push(frac1);
        stack.push(frac2);
        stack.push(frac3);

        int expectedSize = 3;
        assertEquals(3, stack.size());

        assertEquals(frac3, stack.peek());
        assertEquals(frac3, stack.pop());
        assertEquals(--expectedSize, stack.size());

        assertEquals(frac2, stack.peek());
        assertEquals(frac2, stack.pop());
        assertEquals(--expectedSize, stack.size());

        assertEquals(frac1, stack.peek());
        assertEquals(frac1, stack.pop());
        assertEquals(--expectedSize, stack.size());
    }

    @Test
    public void testPushPopAdvanced2() {
        MyStack<Integer> stack = new MyStack<>();

        for (int i = 0; i < 100; ++i) {
            stack.push(i);
        }

        for (int i = 99; i >= 0; --i) {
            assertEquals(i, stack.pop().intValue());
            assertEquals(i, stack.size());
        }

        assertEquals(0, stack.size());
    }

    @Test
    public void testAddAll1() {
        Set<Fraction> fractions = new HashSet<>();

        Fraction frac1 = new Fraction(2);
        Fraction frac2 = new Fraction(3);
        Fraction frac3 = new Fraction(4);

        fractions.add(frac1);
        fractions.add(frac2);
        fractions.add(frac3);

        MyStack<Fraction> stack = new MyStack<>();
        stack.addAll(fractions);

        assertEquals(fractions.size(), stack.size());

        // Checking if values are intact via adding back to the Set
        // the size should not be changed

        fractions.add(stack.pop());
        fractions.add(stack.pop());
        fractions.add(stack.pop());

        assertEquals(3, fractions.size());
    }

    @Test
    public void testAddAll2() {
        Set<Fraction> fractions = new HashSet<>();

        MyStack<Fraction> stack = new MyStack<>();
        stack.addAll(fractions);

        assertEquals(0, stack.size());
    }

    @SuppressWarnings("AccessStaticViaInstance")
    @Test
    public void testSeminarGroupId() {
        MyStack<String> stack1 = new MyStack<>();
        MyStack<String> stack2 = new MyStack<>();

        assertEquals(3, MyStack.seminarGroupId);

        // this field can also be accessed via the object itself
        // but is strongly not recommended
        assertEquals(3, stack1.seminarGroupId);
        assertEquals(3, stack2.seminarGroupId);

        // OBSERVE: since <seminarGroupId> is a static variable
        // it is changed across all MyStack objects as it is not
        // bound by any object
        int newId = 12324234;
        MyStack.seminarGroupId = newId;

        assertEquals(newId, stack1.seminarGroupId);
        assertEquals(newId, stack2.seminarGroupId);
    }

}

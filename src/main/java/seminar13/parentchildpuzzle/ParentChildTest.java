package seminar13.parentchildpuzzle;

public class ParentChildTest {

    public static class A {
        public static int k = 5;

        public int n = 18;

        public int getK() {
            return 1;
        }

        public void doA() {
            System.out.println("doA " + this.getK() + " " + this.n);
        }
    }

    public static class B extends A {

        public static int k = 6;

        public int n = 19;

        public int getK() {
            return 2;
        }

        public void doA() {
            super.doA();
            System.out.println("doB " + this.getK() + " " + this.n);
        }
    }

    public static void main(String[] args) {
//        A aa = new A();
//        aa.doA();
//        System.out.println();

        A ab = new B();
        ab.doA();
        System.out.println();
//
//        B bb = new B();
//        bb.doA();
//        System.out.println();
    }

}

package finals;

public class Child extends Parent {
    public int n = 3;

    public Child() {
        init();
    }

    public void init() {
        n = 4;
    }

    public void foo() {
        super.foo();
        System.out.println(this.bar() + " " + this.n++);
    }

    public int bar() {
        return n++;
    }

    public static void main(String args[]) {
        ((Parent) new Child()).foo();
        new Child().foo();
    }
}

class Parent {
    public int n = 2;

    public Parent() {
        init();
    }

    public void init() {
        n = 1;
    }

    public void foo() {
        System.out.println(this.bar() + " " + ++this.n);
    }

    public int bar() {
        return ++n;
    }
}
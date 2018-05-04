package seminar5.parentchild;

public class Demo {

    private static void invoke(Parent parent) {
        parent.go();
    }

    private static void invoke(Child ch) {
        ch.go();
    }

    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();
        Parent parentRefChildObj = new Child();
        // Child childRefParentObj = new Parent(); // incorrect

        invoke(parent);
        invoke(child);
        invoke(parentRefChildObj);
    }

}

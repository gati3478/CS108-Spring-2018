package seminar5.device;

public abstract class Phone implements Electronic {

    public abstract void makeCall(String number);

    public boolean testCall(String number) {
        return true;
    }

}

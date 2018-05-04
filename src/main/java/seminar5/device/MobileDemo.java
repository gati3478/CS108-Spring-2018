package seminar5.device;

public class MobileDemo {

    public static void main(String[] args) {
        Mobile mobile = new Mobile();
        String number = "+11231234123";

        if (mobile.testCall(number)) {
            mobile.makeCall(number);
        }

        System.out.println(Mobile.WHAT_A_VARIABLE);

        mobile.installOS();

        Device device = mobile;
        device.on();

        //device.installOS() - compiler error
    }

}

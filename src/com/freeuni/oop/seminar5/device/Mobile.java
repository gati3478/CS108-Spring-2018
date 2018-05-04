package com.freeuni.oop.seminar5.device;

public class Mobile extends Phone {
    @Override
    public void on() {
        System.out.println("Mobile phone is ON");
    }

    @Override
    public void off() {
        System.out.println("Mobile phone is OFF");
    }

    @Override
    public void plugin() {
        System.out.println("Plugin");
    }

    @Override
    public void makeCall(String number) {
        System.out.println("Making call to " + number);
    }

    public void installOS() {
        System.out.println("Installing SmartOS...");
    }

}

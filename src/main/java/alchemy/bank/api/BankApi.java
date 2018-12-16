package alchemy.bank.api;

import alchemy.bank.service.BankInterface;
import alchemy.bank.service.BankInterfaceImpl;

public class BankApi {

    private static BankInterface api = new BankInterfaceImpl();

    public static boolean hasData(String name) {
        return api.hasData(name);
    }

    public static boolean hasEnoughMoney(String name, int amount) {
        return api.hasEnoughPoint(name, amount);
    }

    public static void removePoint(String name, int amount) {
        api.removePoint(name, amount);
    }

    public static void setPoint(String name, int amount) {
        api.setPoint(name, amount);
    }

    public static void addPoint(String name, int amount) {
        api.setPoint(name, getPoint(name) + amount);
    }

    public static int getPoint(String name) {
        return api.getPoint(name);
    }

}

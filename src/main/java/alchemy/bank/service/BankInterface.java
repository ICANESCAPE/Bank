package alchemy.bank.service;

public interface BankInterface {
    boolean hasData(String name);
    void initData(String name);
    boolean hasEnoughPoint(String name, int amount);
    int getPoint(String name);
    void setPoint(String name, int amount);
    void removePoint(String name, int amount);
}

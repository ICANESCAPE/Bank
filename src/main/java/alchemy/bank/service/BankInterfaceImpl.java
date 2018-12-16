package alchemy.bank.service;

import alchemy.bank.Bank;
import alchemy.bank.dao.BankDao;

public class BankInterfaceImpl implements BankInterface {

    @Override
    public boolean hasData(String name) {
        if (name == null || name.equalsIgnoreCase("")) {
            return false;
        }
        if(BankDao.hasData(name)) {
            return true;
        }
        return false;
    }

    @Override
    public void initData(String name) {
        if (name == null || name.equalsIgnoreCase("")) {
            return;
        }
        if (!hasData(name)) {
            BankDao.initData(name);
        }
    }

    @Override
    public boolean hasEnoughPoint(String name, int amount) {
        if (name == null || name.equalsIgnoreCase("")) {
            return false;
        }
        if (!hasData(name)) {
            BankDao.initData(name);
            return false;
        }
        double money = 0.0D;
        money = BankDao.getPoint(name);
        return money >= amount;
    }

    @Override
    public int getPoint(String name) {
        if (name == null || name.equalsIgnoreCase("")) {
            return 0;
        }
        if (!hasData(name)) {
            BankDao.initData(name);
            return 0;
        }
        return BankDao.getPoint(name);
    }

    @Override
    public void setPoint(String name, int amount) {
        if (name == null || name.equalsIgnoreCase("")) {
            return;
        }
        if (!hasData(name)) {
            BankDao.initData(name);
            return;
        }
        BankDao.setPoint(name, amount);
    }

    @Override
    public void removePoint(String name, int amount) {
        if (name == null || name.equalsIgnoreCase("")) {
            return;
        }
        if (!hasData(name)) {
            BankDao.initData(name);
            return;
        }
        BankDao.removePoint(name, amount);
    }
}

package alchemy.bank.dao;

import alchemy.bank.Bank;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BankDao {

    public static boolean hasData(String name) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT Name FROM Bank WHERE Name = '" + name + "'";

        try {
            connection = BasicDao.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return false;
    }

    public static void initData(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO point VALUES(null, '" + name + "', '0')";

        try {
            conn = BasicDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
    }

    public static int getPoint(String name) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int result = 0;
        String sql = "SELECT point FROM Bank WHERE name = '" + name + "'";

        try {
            conn = BasicDao.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            if(rs.next()){
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
        return result;
    }

    public static void setPoint(String name, int amount) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE Bank SET point = " + amount + " WHERE name = '" + name + "'";
        try {
            conn = BasicDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
    }

    public static void removePoint(String name, int amount) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "UPDATE playerMoney SET money = money - " + amount + " WHERE playerName = '" + name + "'";
        try {
            conn = BasicDao.getConnection();
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
        }
    }
}

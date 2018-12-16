package alchemy.bank;

import alchemy.bank.dao.BasicDao;
import alchemy.bank.file.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import alchemy.bank.util.BasicUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bank extends JavaPlugin {

    private static Bank instance;

    @Override
    public void onEnable() {

        instance = this;
        try {
            //指定连接类型
            Class.forName("com.mysql.jdbc.Driver");
            String host = getConfig().getString("Mysql.Host");
            String port = getConfig().getString("Mysql.Port");
            String dbname = getConfig().getString("Mysql.DataBase");
            String username = getConfig().getString("Mysql.User");
            String password = getConfig().getString("Mysql.PassWord");
            String ip = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?useUnicode=true&characterEncoding=utf8&autoReconnect=true";
            String s = "CREATE TABLE `Bank` (\n" +
                    "  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` text,\n" +
                    "  `point` int(11) DEFAULT '0',\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
            String sql = "CREATE TABLE IF NOT EXISTS PlayerPoint (\n" +
                    "  id int(4) NOT NULL AUTO_INCREMENT,\n" +
                    "  name text NOT NULL,\n" +
                    "  point double(12,2) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (id)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1;";
            Connection conn = DriverManager.getConnection(ip, username, password);
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            BasicDao.setConnection(conn);
            Bukkit.getConsoleSender().sendMessage("§f[§e黄金§f] §f数据库连接成功!");
        } catch (Exception e) {
            System.out.println("错误: " + e.getMessage());
            Bukkit.getConsoleSender().sendMessage("§f[§e黄金§f] §f数据库连接失败!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        Config.reload();
        Bukkit.getConsoleSender().sendMessage(BasicUtil.msg("&f[&3&l物资银行&f] &6>> &a加载成功"));
    }

    @Override
    public void onDisable() {
        if (!BasicDao.isClose()) {
            BasicDao.close();
        }
        Bukkit.getConsoleSender().sendMessage(BasicUtil.msg("&f[&3&l物资银行&f] &6>> &a卸载完成"));
    }

    public static Bank getInstance() { return instance; }
}

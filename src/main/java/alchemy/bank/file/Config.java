package alchemy.bank.file;

import alchemy.bank.Bank;
import alchemy.bank.dto.BankData;
import alchemy.bank.util.FileUtil;
import lombok.Getter;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class Config extends FileUtil {

    private static Config config;

    public Config() { super(Bank.getInstance(), "config.yml"); }

    public static void reload() { config = new Config(); }

    @Getter
    private static List<BankData> dataList = new ArrayList<>();

    @Override
    public void check() {
        ConfigurationSection cs = this.getConfigurationSection("Item");
        for (String key : cs.getKeys(false)) {
            dataList.add(new BankData(
                    cs.getInt(key + "slot"),
                    cs.getInt(key + ".value"),
                    cs.getString(key + ".type")
            ));
        }
    }

    public static BankData getData(int slot) {
        for (BankData data : getDataList()) {
            if (data.getSlot() == slot) {
                return data;
            }
        }
        return null;
    }
}

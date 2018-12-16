package alchemy.bank.gui;

import alchemy.bank.file.Config;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * @author SCT_Alchemy
 * @date 2018/11/13 10:43 AM
 */

public class GuiBuilder {

    private static Inventory inventory;

    private static void add() {
        for (int i = 0; i < Config.getDataList().size(); i++) {
            int slot = Config.getDataList().get(i).getSlot();
        }
    }
}

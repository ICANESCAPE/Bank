package alchemy.bank.Listener;

import alchemy.bank.util.DataUtil;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.PlayerInventory;

/**
 * @author SCT_Alchemy
 * @date 2018/11/13 10:43 AM
 */

public class InvClickListener implements Listener {

    @EventHandler
    void onClick(InventoryClickEvent e) {
        int slot = e.getSlot();
        Player player;
        int value;

        if (e.getWhoClicked() instanceof Player) {
            player = (Player) e.getWhoClicked();
        } else { return; }

        if (e.getInventory().getName().contains("银行")) {
            if (e.getCurrentItem() != null) {
                e.setCancelled(true);
                PlayerInventory inventory = player.getInventory();
                for (int i = 0; i < inventory.getSize(); i++) {
                    if (e.getCurrentItem().isSimilar(inventory.getItem(i))) {
                        if (e.getClick().equals(ClickType.LEFT)) {
                            inventory.getItem(i).setAmount(inventory.getItem(i).getAmount() - 1);
                            value = DataUtil.getPlayerInfoToInt(player.getName(), e.getCurrentItem().getType().toString());
                            DataUtil.setPlayerInfo(player.getName(), e.getCurrentItem().getType().toString(), value + 1);
                        } else if (e.getClick().equals(ClickType.RIGHT)) {
                            if (inventory.getItem(i).getAmount() < 64) {
                                player.sendMessage("shuliangbugou");
                            } else {
                                inventory.getItem(i).setAmount(0);
                                value = DataUtil.getPlayerInfoToInt(player.getName(), e.getCurrentItem().getType().toString());
                                DataUtil.setPlayerInfo(player.getName(), e.getCurrentItem().getType().toString(), value + 64);
                            }
                        }
                    }
                }
            }
        }
    }
}

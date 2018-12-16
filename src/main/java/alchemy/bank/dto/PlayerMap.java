package alchemy.bank.dto;

import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SCT_Alchemy
 * @date 2018/12/16 10:12 AM
 */

public class PlayerMap {

    @Getter
    static Map<Player, Integer> playerMap = new HashMap<>();

}


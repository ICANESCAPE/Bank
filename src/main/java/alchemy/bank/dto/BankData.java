package alchemy.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author SCT_Alchemy
 * @date 2018/12/16 9:52 AM
 */

@Data
@AllArgsConstructor
public class BankData {
    int slot;
    int value;
    String type;

}

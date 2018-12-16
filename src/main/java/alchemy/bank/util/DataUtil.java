package alchemy.bank.util;

import alchemy.bank.Bank;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author SCT_Alchemy
 * @date 2018/12/16 9:48 AM
 */

public class DataUtil {

    private static final String PLAYERS_FOLDER_NAME = "Players";

    private static File getDataFolder() {
        File dataFolder = Bank.getInstance().getDataFolder();
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        return dataFolder;
    }


    private static File getDataFolderFile(String fileName) {
        try {
            File dataFile = new File(getDataFolder(), fileName);
            if (!dataFile.exists()) {
                dataFile.createNewFile();
                return dataFile;
            }
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static File[] getDataFolderFiles(String fileLocation) {
        try {
            File fileLoc = new File(getDataFolder(), fileLocation);
            if (!fileLoc.exists()) {
                fileLoc.mkdir();
            }
            return fileLoc.listFiles();
        } catch (SecurityException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static YamlConfiguration getFileConfig(File file) {
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
        return config;
    }

    private static String getString(File file) throws IOException {
        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String s = "";
        while ((s = bReader.readLine()) != null) {
            sb.append(s.substring(0, s.indexOf('#') >= 0 ? s.indexOf('#') : s.length()));
        }
        bReader.close();
        return sb.toString();
    }

    private static String getString(File file, char addition) throws IOException {
        FileReader reader = new FileReader(file);
        BufferedReader bReader = new BufferedReader(reader);
        StringBuilder sb = new StringBuilder();
        String s = "";
        while ((s = bReader.readLine()) != null) {
            if (s.trim().startsWith("*")) {
                sb.append(s.substring(0, s.indexOf('#') >= 0 ? s.indexOf('#') : s.length()));
            } else {
                sb.append(";" + s.substring(0, s.indexOf('#') >= 0 ? s.indexOf('#') : s.length()));
            }
            //将读取的字符串累加存放在缓存中
        }
        bReader.close();
        return sb.toString();
    }

    private static File getPlayerFolder() {
        File gameFolder = new File(getDataFolder(), PLAYERS_FOLDER_NAME);
        if (!gameFolder.exists()) {
            gameFolder.mkdirs();
        }
        return gameFolder;
    }

    private static File getPlayerFile(String playerName) {
        for (File playerFile : getDataFolderFiles(PLAYERS_FOLDER_NAME)) {
            if (playerFile.getName().equalsIgnoreCase(playerName + ".yml")) {
                return playerFile;
            }
        }
        return null;
    }

    public static boolean createPlayerFile(String playerName) {
        File playerFile = new File(getPlayerFolder(), playerName + ".yml");
        try {
            return playerFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Object getPlayerInfo(String playerName, String key) {
        File file = getPlayerFile(playerName);
        if (file == null) {
            createPlayerFile(playerName);
            file = getPlayerFile(playerName);
        }
        YamlConfiguration config = getFileConfig(file);
        return config.get(key);
    }

    public static int getPlayerInfoToInt(String playerName, String key) {
        Object obj = getPlayerInfo(playerName, key);
        if (obj == null) {
            return 0;
        } else {
            try {
                return Integer.parseInt(obj.toString());
            } catch (Exception e) {
                return 0;
            }
        }
    }

    public static void setPlayerInfo(String playerName, String key, Object value) {
        File file = getPlayerFile(playerName);
        if (file == null) {
            createPlayerFile(playerName);
            file = getPlayerFile(playerName);
        }
        YamlConfiguration config = getFileConfig(file);
        config.set(key, value);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Object getFileInfo(String folder, String key) {
        return getFileConfig(getDataFolderFile(folder)).get(key);
    }

    private static void setFileInfo(String folder, String key, Object value) {
        YamlConfiguration config = getFileConfig(getDataFolderFile(folder));
        config.set(key, value);
        try {
            config.save(getDataFolderFile(folder));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  /*  public static String getChallengeIndex()
    {
        return (String) getFileInfo(CHALLENGES_FILE_NAME, "挑战名称");
    }*/

}


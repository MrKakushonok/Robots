package gui;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class MenuBarGenerator {
    private JMenuBar m_menu = new JMenuBar();
    private Map<String, JMenu> m_menus = new HashMap<String, JMenu>();

    public void createMenu(String name, String description, int mnemonic){
        JMenu resMenu = new JMenu();
        if(mnemonic != 0) { resMenu.setMnemonic(mnemonic); };
        resMenu.getAccessibleContext().setAccessibleDescription(description);
        m_menus.put(name, resMenu);
    };

    public void createMenu(String name, String description){
        createMenu(name, description, 0);
    };

    public void createMenuItem(String menuName, String itemName, int mnemonic){
        JMenuItem resItem = new JMenuItem(itemName, mnemonic);
    }

    public void createMenuItem(String menuName, String itemName){
        createMenuItem(menuName, itemName, 0);
    }
}

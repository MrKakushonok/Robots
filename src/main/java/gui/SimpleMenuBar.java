package gui;

import javax.accessibility.Accessible;
import javax.swing.*;

/**
 * An extension of a <see>JMenuBar</see>
 * that simplifies generating Menu bars given a list of Menus
 */
public class SimpleMenuBar extends JMenuBar implements Accessible, MenuElement {
    public SimpleMenuBar(){
        super();
    }

    public void generateMenuBar(SimpleMenu[] menus){
        for(SimpleMenu menu : menus){
            this.add(menu);
        }
    }
}

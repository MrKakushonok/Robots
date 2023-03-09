package gui;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.util.Objects;

/**
 * An extension of a <see>JMenu</see>
 * that simplifies creating menus restricted to:
 * -name
 * -description
 * -<see>mnemonic</see>
 * -MenuItems - given a list of items
 */
public class SimpleMenu extends JMenu implements Accessible, MenuElement{

    private void init(String description, int mnemonic){
        if(mnemonic != 0) { super.setMnemonic(mnemonic); }
        if(!Objects.equals(description, "none")){ super.getAccessibleContext().setAccessibleDescription(description); }
    }

    public SimpleMenu(){
        super();
        init("none", 0);
    }

    public SimpleMenu(String name){
        super(name);
        init("none", 0);
    }

    public SimpleMenu(String name, String description){
        super(name);
        init(description, 0);
    }

    public SimpleMenu(String name, int mnemonic){
        super(name);
        init("none", mnemonic);
    }

    public SimpleMenu(String name, String description, int mnemonic){
        super(name);
        init(description, mnemonic);
    }

    public SimpleMenu generateMenu(SimpleMenuItem[] items){
        for(SimpleMenuItem item : items) {
            this.add(item);
        }
        return this;
    }
}

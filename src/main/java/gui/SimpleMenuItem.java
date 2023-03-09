package gui;

import javax.accessibility.Accessible;
import javax.swing.*;
import java.awt.event.ActionListener;

/**
 * An extension of a <see>JMenuItem</see>
 * that simplifies creating MenuItems restricted to:
 * -name
 * -<see>mnemonic</see>
 * -<see>ActionListener</see>
 */
public class SimpleMenuItem extends JMenuItem implements Accessible,MenuElement{

    private void init(int mnemonic, ActionListener listener){
        if(mnemonic != 0) { super.setMnemonic(mnemonic); }
        super.addActionListener(listener);
    }

    public SimpleMenuItem(){
        super();
        init(0, (event) -> {});
    }

    public SimpleMenuItem(String name){
        super(name);
        init(0, (event) -> {});
    }

    public SimpleMenuItem(String name, ActionListener listener){
        super(name);
        init(0, listener);
    }

    public SimpleMenuItem(String name, int mnemonic, ActionListener listener){
        super(name);
        init(mnemonic, listener);
    }
}

package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.JOptionPane;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;

import log.Logger;

/**
 * Что требуется сделать:
 * 1. Метод создания меню перегружен функционалом и трудно читается. 
 * Следует разделить его на серию более простых методов (или вообще выделить отдельный класс).
 *
 */
public class MainApplicationFrame extends JFrame
{
    private final JDesktopPane desktopPane = new JDesktopPane();
    
    public MainApplicationFrame() {
        //Make the big window be indented 50 pixels from each edge
        //of the screen.
        int inset = 50;        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
            screenSize.width  - inset*2,
            screenSize.height - inset*2);

        setContentPane(desktopPane);
        
        
        LogWindow logWindow = createLogWindow();
        addWindow(logWindow);

        GameWindow gameWindow = new GameWindow();
        gameWindow.setSize(400,  400);
        addWindow(gameWindow);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApp();
            }
        });

        setJMenuBar(generateMenuBar());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    protected LogWindow createLogWindow()
    {
        LogWindow logWindow = new LogWindow(Logger.getDefaultLogSource());
        logWindow.setLocation(10,10);
        logWindow.setSize(300, 800);
        setMinimumSize(logWindow.getSize());
        logWindow.pack();
        Logger.debug("Протокол работает");
        return logWindow;
    }
    
    protected void addWindow(JInternalFrame frame)
    {
        desktopPane.add(frame);
        frame.setVisible(true);
    }
    
//    protected JMenuBar createMenuBar() {
//        JMenuBar menuBar = new JMenuBar();
// 
//        //Set up the lone menu.
//        JMenu menu = new JMenu("Document");
//        menu.setMnemonic(KeyEvent.VK_D);
//        menuBar.add(menu);
// 
//        //Set up the first menu item.
//        JMenuItem menuItem = new JMenuItem("New");
//        menuItem.setMnemonic(KeyEvent.VK_N);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_N, ActionEvent.ALT_MASK));
//        menuItem.setActionCommand("new");
////        menuItem.addActionListener(this);
//        menu.add(menuItem);
// 
//        //Set up the second menu item.
//        menuItem = new JMenuItem("Quit");
//        menuItem.setMnemonic(KeyEvent.VK_Q);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(
//                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
//        menuItem.setActionCommand("quit");
////        menuItem.addActionListener(this);
//        menu.add(menuItem);
// 
//        return menuBar;
//    }

    public SimpleMenuBar generateMenuBar(){
        SimpleMenuBar menuBar = new SimpleMenuBar();

        menuBar.generateMenuBar(
                new SimpleMenu[]{
                        new SimpleMenu("Режим отображения",
                                "Управление режимом отображения приложения",
                                KeyEvent.VK_V
                        ).generateMenu(new SimpleMenuItem[]{
                                new SimpleMenuItem("Системная схема",
                                        KeyEvent.VK_S,
                                        (event) ->{
                                            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                                            this.invalidate();
                                        }),
                                new SimpleMenuItem("Универсальная схема",
                                        KeyEvent.VK_S,
                                        (event) ->{
                                            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                                            this.invalidate();
                                        })
                        }),
                        new SimpleMenu("Тесты",
                                "Тестовые команды",
                                KeyEvent.VK_T
                        ).generateMenu(new SimpleMenuItem[]{
                                new SimpleMenuItem("Сообщение в лог",
                                        KeyEvent.VK_S,
                                        (event) -> {
                                            Logger.debug("Новая строка");
                                        })
                        }),
                        new SimpleMenu("Закрыть",
                                "Закрытие приложения",
                                KeyEvent.VK_E
                        ).generateMenu(new SimpleMenuItem[]{
                                new SimpleMenuItem("Закрыть приложение",
                                        KeyEvent.VK_ESCAPE,
                                        (event) -> {
                                            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(
                                                    new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                                        })
                        })
                });
        return menuBar;
    }
    
    private void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(this);
        }
        catch (ClassNotFoundException | InstantiationException
            | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }

    private void exitApp(){
        String[] options = {"Да", "Нет"};
        int response = JOptionPane.showOptionDialog(this,
                "Закрыть приложение?",
                "Закрытие",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        if(response == JOptionPane.YES_OPTION){
            System.exit(0);
        }
    }
}

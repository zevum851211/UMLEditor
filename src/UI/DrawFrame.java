package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawFrame extends JFrame {
    private MenuBar _menuBar;
    private Menu _file;
    private Menu _edit;
    private MenuItem _groupItem;
    private MenuItem _unGroupItem;
    private MenuItem _changeNameItem;

    private ToolPanel _toolPanel;

    public Canvas canvas;
    public DrawFrame() {
        canvas = Canvas.getInstance();
        initMenuBar();
        initToolPanel();
    }

    private void initMenuBar(){
        _menuBar = new MenuBar();
        _file = new Menu("File");
        _edit = new Menu("Edit");
        _groupItem = new MenuItem("Group");
        _unGroupItem = new MenuItem("Ungroup");
        _changeNameItem = new MenuItem("ChangeName");

        _groupItem.addActionListener(new groupListener());
        _unGroupItem.addActionListener(new unGroupListener());
        _changeNameItem.addActionListener(new changeNameListener());

        _edit.add(_groupItem);
        _edit.add(_unGroupItem);
        _edit.add(_changeNameItem);

        _menuBar.add(_file);
        _menuBar.add(_edit);
        this.setMenuBar(_menuBar);
    }

    class groupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.groupShape();
        }
    }

    class unGroupListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas.removeGroup();
        }
    }

    class changeNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            changeNameFrame();
        }
    }

    private void initToolPanel(){
        _toolPanel = new ToolPanel();
        this.setContentPane(_toolPanel);
    }

    private void changeNameFrame(){
        JFrame frame = new JFrame("Change Name");
        frame.setSize(400, 100);
        frame.setLayout(new BorderLayout());

        JTextField inputText = new JTextField("Object Name");
        frame.add(inputText, BorderLayout.NORTH);

        JButton confirm = new JButton("OK");
        frame.add(confirm, BorderLayout.WEST);

        JButton cancel = new JButton("Cancel");
        frame.add(cancel, BorderLayout.CENTER);

        frame.setVisible(true);

        confirm.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                canvas.changeName(inputText.getText());
                frame.dispose();
            }
        });

        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }
} 

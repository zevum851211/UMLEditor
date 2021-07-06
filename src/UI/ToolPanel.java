package UI;

import Modes.Mode;
import Modes.SelectMode;
import Modes.createLineMode;
import Modes.createObjMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToolPanel extends JPanel {
    private ButtonPanel _btnPanel;
    private Canvas _canvas;
    private JButton _holdBtn = null;
    
    public ToolPanel(){
        initComponents();
        initLayout();
    }

    public void initComponents() {
        _canvas = Canvas.getInstance();
        _btnPanel = new ButtonPanel();
    }

    public void initLayout(){
        GridBagLayout gridBagLayout = new GridBagLayout();

        Insets insets = new Insets(0, 0, 0, 0);
        int anchor = GridBagConstraints.CENTER;
        int fill = GridBagConstraints.BOTH;

        GridBagConstraints bagButtonPanel = new GridBagConstraints(0, 0, 1, 6, 0, 0, anchor, fill, insets, 1, 1);

        GridBagConstraints bagCanvas = new GridBagConstraints(1, 0, 5, 5, 1, 1, anchor, fill, insets, 1, 1);

        this.setLayout(gridBagLayout);
        this.add(_btnPanel, bagButtonPanel);
        this.add(_canvas, bagCanvas);
    }




    class ModeButton extends JButton{
        Mode mode = null;
        public ModeButton(Icon icon, Mode mode){
            super(icon);
            this.mode = mode;
            addActionListener(new btnListener());
        }

        class btnListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(_holdBtn != null){
                    _holdBtn.setBackground(null);
                }
                _holdBtn = (JButton) e.getSource();
                _holdBtn.setBackground(new Color(200, 30, 150));
                _canvas.currentMode = mode;
                _canvas.setCurrentMode();
                _canvas.reset();
                _canvas.repaint();
            }
        }
    }
    class ButtonPanel extends JPanel{
        private final int MODE_SELECT = 0;
        private final int MODE_ASSOCIATION = 1;
        private final int MODE_GENERALIZATION = 2;
        private final int MODE_COMPOSITION = 3;
        private final int MODE_CLASS = 4;
        private final int MODE_USE_CASE = 5;
        private final int MODE_NUMBERS = 6;

        private ModeButton[] _modeBtn = new ModeButton[MODE_NUMBERS];

        public ButtonPanel(){
            initComponents();
            initGridBagLayout();
        }

        public void initComponents(){

            ImageIcon selectIcon = new ImageIcon("img/select.png");
            ImageIcon associationIcon = new ImageIcon("img/associationLine.png");
            ImageIcon generalizationIcon = new ImageIcon("img/generalizationLine.png");
            ImageIcon compositionIcon = new ImageIcon("img/compositionLine.png");
            ImageIcon classObj = new ImageIcon("img/class.png");
            ImageIcon useCaseObj = new ImageIcon("img/useCase.png");



            _modeBtn[MODE_SELECT] = new ModeButton(selectIcon, new SelectMode());
            _modeBtn[MODE_ASSOCIATION] = new ModeButton(associationIcon, new createLineMode("AssociationLine"));
            _modeBtn[MODE_GENERALIZATION] = new ModeButton(generalizationIcon, new createLineMode("GeneralizationLine"));
            _modeBtn[MODE_COMPOSITION] = new ModeButton(compositionIcon, new createLineMode("CompositionLine"));
            _modeBtn[MODE_CLASS] = new ModeButton(classObj, new createObjMode("Class"));
            _modeBtn[MODE_USE_CASE] = new ModeButton(useCaseObj, new createObjMode("UseCase"));
        }

        public void initGridBagLayout(){

            int anchor = GridBagConstraints.CENTER;
            int fill = GridBagConstraints.BOTH;

            Insets insets = new Insets(0, 0, 0, 0);

            GridBagLayout gridBagLayout = new GridBagLayout();
            this.setLayout(gridBagLayout);

            GridBagConstraints[] gridBags = new GridBagConstraints[MODE_NUMBERS];

            for(int i = 0; i < MODE_NUMBERS; i++){
                gridBags[i] = new GridBagConstraints(0, i,
                        1,1,
                        0,1,
                        anchor, fill, insets,
                        1,1);
                this.add(_modeBtn[i], gridBags[i]);
            }
        }

    }
}

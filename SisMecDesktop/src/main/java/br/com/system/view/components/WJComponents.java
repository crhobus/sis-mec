package br.com.system.view.components;

import br.com.system.controller.action.comboBox.ComboBoxOption;
import br.com.system.view.components.comboBox.WComboBoxCellRenderer;
import br.com.system.view.components.comboBox.WComboBoxModel;
import br.com.system.view.components.events.WChangedListener;
import br.com.system.view.components.events.WComponentType;
import br.com.system.view.components.events.WEvent;
import br.com.system.view.components.events.WFilterTypeListener;
import br.com.system.view.components.events.WHandlerListener;
import br.com.system.view.components.events.WShowRecordListener;
import br.com.system.view.components.events.WType;
import br.com.system.view.components.table.WJTableLineNumber;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.EventListenerList;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.text.Document;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author crhobus
 */
public class WJComponents {

    private final EventListenerList listenerList;

    public WJComponents() {
        this.listenerList = new EventListenerList();
    }

    private JPanel getJPanelAux(JPanel panel, LayoutManager layoutManager, int x, int y, int width, int height) {
        panel.setLayout(layoutManager);
        panel.setBounds(x, y, width, height);
        panel.setBackground(new Color(248, 248, 248));
        panel.setBorder(BorderFactory.createEtchedBorder());
        return panel;
    }

    protected JPanel getJPanel(int x, int y, int width, int height) {
        return getJPanelAux(new JPanel(), null, x, y, width, height);
    }

    public JPanel getJPanelImage(JPanel pnImagem, int x, int y, int width, int height) {
        JPanel panel = getJPanelAux(pnImagem, new FlowLayout(FlowLayout.CENTER), x, y, width, height);
        panel.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        return panel;
    }

    public JLabel getJLabel(String text, int x, int y, int width) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, 14);
        return label;
    }

    private JTextField getJTextFieldAux(boolean required, Document document, int x, int y, int width) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, 28);
        if (required) {
            textField.setBackground(new Color(178, 223, 238));
        }
        textField.setBorder(new LineBorder(Color.GRAY));
        if (document != null) {
            textField.setDocument(document);
        }
        textField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent evt) {
                dispatcherListenerChangedValue(WComponentType.TF);
            }

            @Override
            public void removeUpdate(DocumentEvent evt) {
                dispatcherListenerChangedValue(WComponentType.TF);
            }

            @Override
            public void changedUpdate(DocumentEvent evt) {
                dispatcherListenerChangedValue(WComponentType.TF);
            }
        });
        return textField;
    }

    public JTextField getJTextField(boolean required, int x, int y, int width) {
        return getJTextFieldAux(required, null, x, y, width);
    }

    public JTextField getJTextFieldInputNumber(boolean required, int x, int y, int width) {
        return getJTextFieldAux(required, new WTextInputNumber(), x, y, width);
    }

    public JFormattedTextField getJFormattedTextField(boolean required, String mask, int x, int y, int width) {
        try {
            MaskFormatter maskFormatter = new MaskFormatter(mask);
            maskFormatter.setValueContainsLiteralCharacters(false);
            maskFormatter.setOverwriteMode(true);
            maskFormatter.setValidCharacters("0123456789");

            final JFormattedTextField formattedTextField = new JFormattedTextField(maskFormatter);
            formattedTextField.setBounds(x, y, width, 28);
            if (required) {
                formattedTextField.setBackground(new Color(178, 223, 238));
            }
            formattedTextField.setBorder(new LineBorder(Color.GRAY));
            formattedTextField.setFocusLostBehavior(JFormattedTextField.COMMIT);
            final TextValue value = new TextValue();
            formattedTextField.addFocusListener(new FocusListener() {

                @Override
                public void focusGained(FocusEvent evt) {
                    value.setValue(formattedTextField.getText());
                }

                @Override
                public void focusLost(FocusEvent evt) {
                    if (formattedTextField.getText().split(" ").length > 1 || formattedTextField.getText().trim().length() < formattedTextField.getText().length()) {
                        formattedTextField.setText("");
                    }
                }
            });
            formattedTextField.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent evt) {
                    if (!formattedTextField.getText().equals(value.getValue())
                            && !"".equals(formattedTextField.getText().replaceAll("[^0123456789]", ""))) {
                        dispatcherListenerChangedValue(WComponentType.FTF);
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent evt) {
                    if (!formattedTextField.getText().equals(value.getValue())
                            && !"".equals(formattedTextField.getText().replaceAll("[^0123456789]", ""))) {
                        dispatcherListenerChangedValue(WComponentType.FTF);
                    }
                }

                @Override
                public void changedUpdate(DocumentEvent evt) {
                    if (!formattedTextField.getText().equals(value.getValue())
                            && !"".equals(formattedTextField.getText().replaceAll("[^0123456789]", ""))) {
                        dispatcherListenerChangedValue(WComponentType.FTF);
                    }
                }
            });
            return formattedTextField;
        } catch (ParseException ex) {
            return null;
        }
    }

    public JPasswordField getJPasswordField(boolean required, int x, int y, int width) {
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(x, y, width, 28);
        if (required) {
            passwordField.setBackground(new Color(178, 223, 238));
        }
        passwordField.setBorder(new LineBorder(Color.GRAY));
        passwordField.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent evt) {
                dispatcherListenerChangedValue(WComponentType.PF);
            }

            @Override
            public void removeUpdate(DocumentEvent evt) {
                dispatcherListenerChangedValue(WComponentType.PF);
            }

            @Override
            public void changedUpdate(DocumentEvent evt) {
                dispatcherListenerChangedValue(WComponentType.PF);
            }
        });
        passwordField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                for (WHandlerListener listener : listenerList.getListeners(WHandlerListener.class)) {
                    WEvent event = new WEvent(WType.OK, WComponentType.BT);
                    listener.onHandler(event);
                }
            }
        });
        return passwordField;
    }

    public JTextArea getJTextArea(boolean required, JPanel componentFrame, int x, int y, int width, int height) {
        JTextArea textArea = new JTextArea();
        if (required) {
            textArea.setBackground(new Color(178, 223, 238));
        }
        textArea.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent evt) {
                dispatcherListenerChangedValue(WComponentType.TA);
            }

            @Override
            public void removeUpdate(DocumentEvent evt) {
                dispatcherListenerChangedValue(WComponentType.TA);
            }

            @Override
            public void changedUpdate(DocumentEvent evt) {
                dispatcherListenerChangedValue(WComponentType.TA);
            }
        });
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(x, y, width, height);
        scrollPane.setBorder(new LineBorder(Color.GRAY));
        componentFrame.add(scrollPane);
        return textArea;
    }

    protected JButton getJButton(WType type, String caption, String imageIcon, String hint, int x, int y) {
        Icon ic = new ImageIcon(imageIcon);
        JButton button = new JButton(caption, ic);
        button.setBounds(x, y, 90, 30);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setCursor(new Cursor(java.awt.Cursor.HAND_CURSOR));
        button.setToolTipText(hint);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                for (WHandlerListener listener : listenerList.getListeners(WHandlerListener.class)) {
                    WEvent event = new WEvent(type, WComponentType.BT);
                    listener.onHandler(event);
                }
            }
        });
        return button;
    }

    public JComboBox getJComboBox(boolean required, List<ComboBoxOption> options, int x, int y, int width) {
        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(x, y, width, 28);
        comboBox.setBorder(new LineBorder(Color.GRAY));
        comboBox.setModel(new WComboBoxModel(options));
        comboBox.setRenderer(new WComboBoxCellRenderer(required));
        comboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                dispatcherListenerChangedValue(WComponentType.CB);
            }
        });
        return comboBox;
    }

    public JCheckBox getJCheckBox(String text, boolean required, int x, int y, int width) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setBounds(x, y, width, 28);
        checkBox.setBackground(new Color(248, 248, 248));
        checkBox.setIcon(new WCheckBoxIcon(required));
        checkBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent evt) {
                dispatcherListenerChangedValue(WComponentType.CBX);
            }
        });
        return checkBox;
    }

    public JSeparator getJSeparator(int y, int width) {
        JSeparator separator = new JSeparator();
        separator.setBounds(1, y, width, 3);
        return separator;
    }

    public JTable getJTable(TableModel tableModel, int[] columnMinWidth) {
        JTable table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setMinWidth(columnMinWidth[i]);
        }
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    for (WShowRecordListener listener : listenerList.getListeners(WShowRecordListener.class)) {
                        listener.onShowRecord(table.getSelectedRow());
                    }
                }
            }
        });
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                int column = table.columnAtPoint(evt.getPoint());
                for (WFilterTypeListener listener : listenerList.getListeners(WFilterTypeListener.class)) {
                    listener.onFilterType(table.getColumnName(column), column);
                }
            }
        });
        if (table.getRowCount() > 0) {
            table.addRowSelectionInterval(0, 0);
        }
        return table;
    }

    public JScrollPane getJScrollPaneTable(JTable table, int x, int y, int width, int height) {
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(x, y, width, height);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JTable tableLineNumber = new WJTableLineNumber(table);
        scroll.setRowHeaderView(tableLineNumber);
        return scroll;
    }

    public ButtonGroup getJRadioButton(String text, String[] options, int defaultSelected, JPanel componentFrame, int x, int y, int width, int height) {
        JPanel panelRadioButton = new JPanel();
        panelRadioButton.setLayout(new FlowLayout());
        panelRadioButton.setBounds(x, y, width, height);
        panelRadioButton.setBackground(new Color(248, 248, 248));
        panelRadioButton.setBorder(BorderFactory.createTitledBorder(text));

        ButtonGroup group = new ButtonGroup();

        JRadioButton radioButton;
        for (int i = 0; i < options.length; i++) {
            radioButton = new JRadioButton(options[i]);
            radioButton.setBackground(new Color(248, 248, 248));
            group.add(radioButton);
            if (i == defaultSelected) {
                group.setSelected(radioButton.getModel(), true);
            }
            radioButton.addItemListener(new ItemListener() {

                @Override
                public void itemStateChanged(ItemEvent evt) {
                    dispatcherListenerChangedValue(WComponentType.RB);
                }
            });
            panelRadioButton.add(radioButton);
        }

        componentFrame.add(panelRadioButton);

        return group;
    }

    private void dispatcherListenerChangedValue(WComponentType componentType) {
        for (WChangedListener listener : listenerList.getListeners(WChangedListener.class)) {
            WEvent event = new WEvent(WType.CHANGED, componentType);
            listener.onChanged(event);
        }
    }

    protected void addWChangedListener(WChangedListener listener) {
        listenerList.add(WChangedListener.class, listener);
    }

    protected void removeWChangedListener(WChangedListener listener) {
        listenerList.remove(WChangedListener.class, listener);
    }

    protected void addWHandlerListener(WHandlerListener listener) {
        listenerList.add(WHandlerListener.class, listener);
    }

    protected void removeWHandlerListener(WHandlerListener listener) {
        listenerList.remove(WHandlerListener.class, listener);
    }

    public void addWShowRecordListener(WShowRecordListener listener) {
        listenerList.add(WShowRecordListener.class, listener);
    }

    public void removeWShowRecordListener(WShowRecordListener listener) {
        listenerList.remove(WShowRecordListener.class, listener);
    }

    public void addWFilterTypeListener(WFilterTypeListener listener) {
        listenerList.add(WFilterTypeListener.class, listener);
    }

    public void removeWFilterTypeListener(WFilterTypeListener listener) {
        listenerList.remove(WFilterTypeListener.class, listener);
    }
}

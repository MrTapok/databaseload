package org.spbu.window;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.spbu.datageneration.DatabaseFiller;
import org.spbu.provider.DataProvider;
import org.spbu.service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppForm extends JFrame {
    private JPanel rootPanel;
    private JButton clearDataButton;
    private JButton errorDataButton;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField textField5;
    private JButton randomDataButton;
    private JTextField textField7;
    private JTextField patronymicField;
    private JTextField textField3;
    private JTextField textField4;
    private JPasswordField passwordField1;
    private JButton connectButton;
    private JTextField textField8;
    private JRadioButton mRadioButton;
    private JRadioButton wRadioButton;
    private JButton addUserButton;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField12;
    private JButton jaroButton;
    private JButton jaroWinklerButton;
    private JButton wagnerFisherLButton;
    private JButton wagnerFisherLTRButton;
    private JButton wagnerFisherDLButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextField textField1;
    private JButton jaroBDButton;
    private JButton jaroWinklerBDButton;
    private JButton wagnerFisherLBDButton;
    private JButton wagnerFisherLTRBDButton;
    private JButton wagnerFisherDLBDButton;
    private JButton clearValidButton;
    private JButton compareButton;
    private JTextField textField2;
    private JTextField textField13;

    private DatabaseFiller databaseFiller = new DatabaseFiller();
    private DataProvider dataProvider = new DataProvider();
    private UserService userService = new UserService();
    private ArrayList<String> m_names;
    private ArrayList<String> f_names;
    private ArrayList<String> m_surnames;
    private ArrayList<String> f_surnames;
    private ArrayList<String> m_patronymics;
    private ArrayList<String> f_patronymics;

    public AppForm() {

        super("PassportWork");
        setSize(400, 500);
        dataProvider.getConnection();
        try {
            m_names = dataProvider.getNames(true);
            f_names = dataProvider.getNames(false);
            m_surnames = dataProvider.getSurnames(true);
            f_surnames = dataProvider.getSurnames(false);
            m_patronymics = dataProvider.getPatronymics(true);
            f_patronymics = dataProvider.getPatronymics(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


        clearDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int temp = -1;

                try {
                    temp = Integer.valueOf(textField5.getText());
                } catch (Exception ee) {

                }

                if (temp < 0) {
                    textField10.setText("Неправильный формат");
                } else {
                    databaseFiller.generateData(temp);
                    textField10.setText("Данные без ошибок созданы");
                }
            }
        });

        errorDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    databaseFiller.generateErrorData();
                } catch (SQLException e1) {
                    //e1.printStackTrace();
                }
            }
        });

        jaroWinklerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long st = System.nanoTime();
                ArrayList<String> a_name = new ArrayList<String>();
                ArrayList<String> a_surname = new ArrayList<String>();
                ArrayList<String> a_patronymic = new ArrayList<String>();
                String a_names = "";
                String a_surnames = "";
                String a_patronymics = "";
                char[] name = Convertor.convertStringToChar(nameField.getText());
                char[] surname = Convertor.convertStringToChar(surnameField.getText());
                char[] patronymic = Convertor.convertStringToChar(patronymicField.getText());
                if (mRadioButton.isSelected()) {
                    a_name = userService.checkWriting(2, name, m_names);
                    a_surname = userService.checkWriting(2, surname, m_surnames);
                    a_patronymic = userService.checkWriting(2, patronymic, m_patronymics);
                } else {
                    a_name = userService.checkWriting(2, name, f_names);
                    a_surname = userService.checkWriting(2, surname, f_surnames);
                    a_patronymic = userService.checkWriting(2, patronymic, f_patronymics);
                }
                for (int i = 0; i < a_name.size(); i++) {
                    a_names = a_names + a_name.get(i) + "\n";
                }
                for (int i = 0; i < a_surname.size(); i++) {
                    a_surnames = a_surnames + a_surname.get(i) + "\n";
                }
                for (int i = 0; i < a_patronymic.size(); i++) {
                    a_patronymics = a_patronymics + a_patronymic.get(i) + "\n";
                }
                textArea1.setText(a_names);
                textArea2.setText(a_surnames);
                textArea3.setText(a_patronymics);
                long en = System.nanoTime();
                //textField1.setText(Long.toString(en - st));
            }
        });
        wagnerFisherLButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long st = System.nanoTime();
                ArrayList<String> a_name = new ArrayList<String>();
                ArrayList<String> a_surname = new ArrayList<String>();
                ArrayList<String> a_patronymic = new ArrayList<String>();
                String a_names = "";
                String a_surnames = "";
                String a_patronymics = "";
                char[] name = Convertor.convertStringToChar(nameField.getText());
                char[] surname = Convertor.convertStringToChar(surnameField.getText());
                char[] patronymic = Convertor.convertStringToChar(patronymicField.getText());
                if (mRadioButton.isSelected()) {
                    a_name = userService.checkWriting(3, name, m_names);
                    a_surname = userService.checkWriting(3, surname, m_surnames);
                    a_patronymic = userService.checkWriting(3, patronymic, m_patronymics);
                } else {
                    a_name = userService.checkWriting(3, name, f_names);
                    a_surname = userService.checkWriting(3, surname, f_surnames);
                    a_patronymic = userService.checkWriting(3, patronymic, f_patronymics);
                }
                for (int i = 0; i < a_name.size(); i++) {
                    a_names = a_names + a_name.get(i) + "\n";
                }
                for (int i = 0; i < a_surname.size(); i++) {
                    a_surnames = a_surnames + a_surname.get(i) + "\n";
                }
                for (int i = 0; i < a_patronymic.size(); i++) {
                    a_patronymics = a_patronymics + a_patronymic.get(i) + "\n";
                }
                textArea1.setText(a_names);
                textArea2.setText(a_surnames);
                textArea3.setText(a_patronymics);
                long en = System.nanoTime();
                //textField1.setText(Long.toString(en - st));
            }
        });
        wagnerFisherDLButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long st = System.nanoTime();
                ArrayList<String> a_name = new ArrayList<String>();
                ArrayList<String> a_surname = new ArrayList<String>();
                ArrayList<String> a_patronymic = new ArrayList<String>();
                String a_names = "";
                String a_surnames = "";
                String a_patronymics = "";
                char[] name = Convertor.convertStringToChar(nameField.getText());
                char[] surname = Convertor.convertStringToChar(surnameField.getText());
                char[] patronymic = Convertor.convertStringToChar(patronymicField.getText());
                if (mRadioButton.isSelected()) {
                    a_name = userService.checkWriting(5, name, m_names);
                    a_surname = userService.checkWriting(5, surname, m_surnames);
                    a_patronymic = userService.checkWriting(5, patronymic, m_patronymics);
                } else {
                    a_name = userService.checkWriting(5, name, f_names);
                    a_surname = userService.checkWriting(5, surname, f_surnames);
                    a_patronymic = userService.checkWriting(5, patronymic, f_patronymics);
                }
                for (int i = 0; i < a_name.size(); i++) {
                    a_names = a_names + a_name.get(i) + "\n";
                }
                for (int i = 0; i < a_surname.size(); i++) {
                    a_surnames = a_surnames + a_surname.get(i) + "\n";
                }
                for (int i = 0; i < a_patronymic.size(); i++) {
                    a_patronymics = a_patronymics + a_patronymic.get(i) + "\n";
                }
                textArea1.setText(a_names);
                textArea2.setText(a_surnames);
                textArea3.setText(a_patronymics);
                long en = System.nanoTime();
                //textField1.setText(Long.toString(en - st));
            }
        });
        clearValidButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dataProvider.truncateValidUsers();
            }
        });
        jaroWinklerBDButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    userService.processDB(2, m_names, m_surnames, m_patronymics, f_names, f_surnames, f_patronymics);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        wagnerFisherLBDButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    userService.processDB(3, m_names, m_surnames, m_patronymics, f_names, f_surnames, f_patronymics);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        wagnerFisherDLBDButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    userService.processDB(5, m_names, m_surnames, m_patronymics, f_names, f_surnames, f_patronymics);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        compareButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double temp;
                try {
                    temp = userService.compareDB();
                    textField1.setText(Double.toString(temp));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(22, 7, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.setBackground(new Color(-1379853));
        rootPanel.setEnabled(true);
        rootPanel.setForeground(new Color(-1379853));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(21, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        clearDataButton = new JButton();
        clearDataButton.setText("ClearData");
        rootPanel.add(clearDataButton, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        errorDataButton = new JButton();
        errorDataButton.setText("ErrorData");
        rootPanel.add(errorDataButton, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        nameField.setEditable(true);
        rootPanel.add(nameField, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        surnameField = new JTextField();
        surnameField.setEditable(true);
        rootPanel.add(surnameField, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField5 = new JTextField();
        rootPanel.add(textField5, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Введите количество:");
        rootPanel.add(label1, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText(" - Проверки -");
        rootPanel.add(label2, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        patronymicField = new JTextField();
        patronymicField.setEditable(true);
        rootPanel.add(patronymicField, new GridConstraints(11, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("- Ручное добавление пользователя -");
        rootPanel.add(label3, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Имя");
        rootPanel.add(label4, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Фамилия");
        rootPanel.add(label5, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Отчество");
        rootPanel.add(label6, new GridConstraints(10, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("- Параметры подключения к базе - -");
        rootPanel.add(label7, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        rootPanel.add(textField3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField4 = new JTextField();
        rootPanel.add(textField4, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordField1 = new JPasswordField();
        rootPanel.add(passwordField1, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("URL");
        rootPanel.add(label8, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Имя БД:");
        rootPanel.add(label9, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Пароль:");
        rootPanel.add(label10, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        connectButton = new JButton();
        connectButton.setText("Connect");
        rootPanel.add(connectButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField8 = new JTextField();
        textField8.setEditable(false);
        rootPanel.add(textField8, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Создание \"правильных\" данных");
        rootPanel.add(label11, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("Создание данных с ошибками");
        rootPanel.add(label12, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mRadioButton = new JRadioButton();
        mRadioButton.setText("M");
        rootPanel.add(mRadioButton, new GridConstraints(11, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wRadioButton = new JRadioButton();
        wRadioButton.setText("W");
        rootPanel.add(wRadioButton, new GridConstraints(11, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        rootPanel.add(spacer3, new GridConstraints(11, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        addUserButton = new JButton();
        addUserButton.setText("Add User");
        rootPanel.add(addUserButton, new GridConstraints(12, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField9 = new JTextField();
        textField9.setEditable(false);
        rootPanel.add(textField9, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textArea1 = new JTextArea();
        rootPanel.add(textArea1, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        textArea2 = new JTextArea();
        rootPanel.add(textArea2, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        textArea3 = new JTextArea();
        rootPanel.add(textArea3, new GridConstraints(13, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("- Обработка БД -");
        rootPanel.add(label13, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("- Генерация тестовых данных -");
        rootPanel.add(label14, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField10 = new JTextField();
        textField10.setEditable(false);
        rootPanel.add(textField10, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        jaroWinklerButton = new JButton();
        jaroWinklerButton.setText("JaroWinkler");
        rootPanel.add(jaroWinklerButton, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wagnerFisherLButton = new JButton();
        wagnerFisherLButton.setText("WagnerFisherL");
        rootPanel.add(wagnerFisherLButton, new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wagnerFisherDLButton = new JButton();
        wagnerFisherDLButton.setText("WagnerFisherDL");
        rootPanel.add(wagnerFisherDLButton, new GridConstraints(15, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        jaroWinklerBDButton = new JButton();
        jaroWinklerBDButton.setText("JaroWinklerBD");
        rootPanel.add(jaroWinklerBDButton, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wagnerFisherLBDButton = new JButton();
        wagnerFisherLBDButton.setText("WagnerFisherLBD");
        rootPanel.add(wagnerFisherLBDButton, new GridConstraints(17, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wagnerFisherDLBDButton = new JButton();
        wagnerFisherDLBDButton.setText("WagnerFisherDLBD");
        rootPanel.add(wagnerFisherDLBDButton, new GridConstraints(17, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clearValidButton = new JButton();
        clearValidButton.setText("ClearValid");
        rootPanel.add(clearValidButton, new GridConstraints(18, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label15 = new JLabel();
        label15.setText("- Соотношение классов -");
        rootPanel.add(label15, new GridConstraints(19, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        compareButton = new JButton();
        compareButton.setText("Compare");
        rootPanel.add(compareButton, new GridConstraints(20, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        textField2.setEditable(false);
        textField2.setText("");
        rootPanel.add(textField2, new GridConstraints(20, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}

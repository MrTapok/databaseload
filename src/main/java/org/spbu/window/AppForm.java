package org.spbu.window;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.spbu.datageneration.DatabaseFiller;
import org.spbu.service.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AppForm extends JFrame {
    private JPanel rootPanel;
    private JButton button1;
    private JButton AVGParamCountButton;
    private JTextField textField1;
    private JTextField textField2;
    private JButton clearDataButton;
    private JButton errorDataButton;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField textField5;
    private JTextField textField6;
    private JButton randomDataButton;
    private JTextField textField7;
    private JTextField patronymicField;
    private JButton countBoundsButton;
    private JButton showBoundsButton;
    private JButton outputSuspiciosDataButton;
    private JTextArea boundArea;
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
    private JTextField textField11;
    private JTextField textField12;
    private JButton countOutlinersButton;

    private DatabaseStatisticCounter databaseStatisticCounter = new DatabaseStatisticCounter();
    private DatabaseFiller databaseFiller = new DatabaseFiller();
    private UserService userService = new UserService();
    private Convertor convertor = new Convertor();
    private DecisionAnalysis decisionAnalysis = new DecisionAnalysis();


    public AppForm() {

        super("PassportWork");
        setSize(400, 500);
        setContentPane(rootPanel);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    databaseStatisticCounter.allDatabaseStatisticCounting(false);
                    textField1.setText("Statistic up to date");

                } catch (SQLException e1) {
                    e1.printStackTrace();
                    textField1.setText("Something wrong");
                }
            }
        });

        AVGParamCountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                databaseStatisticCounter.averageMetricCounting(false);
                textField2.setText("AVG Param up to date");
            }
        });

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
                int temp = -1;

                try {
                    temp = Integer.valueOf(textField6.getText());
                } catch (Exception ee) {

                }

                if (temp < 0) {
                    textField11.setText("Неправильный формат");
                } else {
                    databaseFiller.generateErrorData(temp);
                    textField11.setText("Данные с опечатками созданы");
                }

            }
        });

        randomDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int temp = -1;

                try {
                    temp = Integer.valueOf(textField7.getText());
                } catch (Exception ee) {

                }

                if (temp < 0) {
                    textField12.setText("Неправильный формат");
                } else {
                    databaseFiller.generateRandomData(temp);
                    textField12.setText("Случайные данные созданы");
                }
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                boolean flag = true;

                String name = nameField.getText();
                String surname = surnameField.getText();
                String patronymic = patronymicField.getText();
                boolean sex;

                if (mRadioButton.isSelected()) {
                    sex = true;
                } else {
                    sex = false;
                }

                if (!(BasicAnalysis.notInAlphabet(name) & BasicAnalysis.notInAlphabet(surname) & BasicAnalysis.notInAlphabet(patronymic))) {
                    textField9.setText("Неверный формат");
                } else {
                    userService.addUser(name, surname, patronymic, sex);
                }

            }
        });

        outputSuspiciosDataButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    decisionAnalysis.consistencyCount(decisionAnalysis.boundsCounting());
                } catch (SQLException e1) {
                    //e1.printStackTrace();
                }
                try {
                    Content content = new Content(convertor.convertRsToStr(userService.getAllUsers()));
                } catch (SQLException e1) {
                    //e1.printStackTrace();
                }
            }
        });
        showBoundsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String string = "";
                    double[][] temp = decisionAnalysis.boundsCounting();

                    for (int i = 0; i < temp.length; i++) {
                        string = string + temp[i][0] + " " + temp[i][1] + "\n";
                    }

                    boundArea.setText(string);
                } catch (SQLException e1) {
                    //e1.printStackTrace();
                }
            }
        });
        countOutlinersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    decisionAnalysis.consistencyCount(decisionAnalysis.boundsCounting());
                } catch (SQLException e1) {
                    //e1.printStackTrace();
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
        button1 = new JButton();
        button1.setText("Statistic Count");
        rootPanel.add(button1, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AVGParamCountButton = new JButton();
        AVGParamCountButton.setText("AVG Param Count");
        rootPanel.add(AVGParamCountButton, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        textField1.setEditable(false);
        rootPanel.add(textField1, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField2 = new JTextField();
        textField2.setEditable(false);
        rootPanel.add(textField2, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(21, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("- Подсчет параметров по базе -");
        rootPanel.add(label1, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("- Генерация данных -");
        rootPanel.add(label2, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        clearDataButton = new JButton();
        clearDataButton.setText("ClearData");
        rootPanel.add(clearDataButton, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        errorDataButton = new JButton();
        errorDataButton.setText("ErrorData");
        rootPanel.add(errorDataButton, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        nameField = new JTextField();
        nameField.setEditable(true);
        rootPanel.add(nameField, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        surnameField = new JTextField();
        surnameField.setEditable(true);
        rootPanel.add(surnameField, new GridConstraints(16, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField5 = new JTextField();
        rootPanel.add(textField5, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField6 = new JTextField();
        rootPanel.add(textField6, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Введите количество:");
        rootPanel.add(label3, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Введите количество:");
        rootPanel.add(label4, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("- Метод IQR -");
        rootPanel.add(label5, new GridConstraints(18, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        randomDataButton = new JButton();
        randomDataButton.setText("RandomData");
        rootPanel.add(randomDataButton, new GridConstraints(10, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Введите количество:");
        rootPanel.add(label6, new GridConstraints(11, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField7 = new JTextField();
        rootPanel.add(textField7, new GridConstraints(12, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        patronymicField = new JTextField();
        patronymicField.setEditable(true);
        rootPanel.add(patronymicField, new GridConstraints(16, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        countBoundsButton = new JButton();
        countBoundsButton.setText("Count bounds");
        rootPanel.add(countBoundsButton, new GridConstraints(19, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        showBoundsButton = new JButton();
        showBoundsButton.setText("Show bounds");
        rootPanel.add(showBoundsButton, new GridConstraints(19, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        outputSuspiciosDataButton = new JButton();
        outputSuspiciosDataButton.setText("Output Suspicios Data");
        rootPanel.add(outputSuspiciosDataButton, new GridConstraints(19, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("- Ручное добавление пользователя -");
        rootPanel.add(label7, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Имя");
        rootPanel.add(label8, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Фамилия");
        rootPanel.add(label9, new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Отчество");
        rootPanel.add(label10, new GridConstraints(15, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        boundArea = new JTextArea();
        boundArea.setEditable(false);
        rootPanel.add(boundArea, new GridConstraints(20, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 49), null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("- Параметры подключения к базе - -");
        rootPanel.add(label11, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        rootPanel.add(textField3, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField4 = new JTextField();
        rootPanel.add(textField4, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        passwordField1 = new JPasswordField();
        rootPanel.add(passwordField1, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("URL");
        rootPanel.add(label12, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setText("Имя БД:");
        rootPanel.add(label13, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setText("Пароль:");
        rootPanel.add(label14, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        connectButton = new JButton();
        connectButton.setText("Connect");
        rootPanel.add(connectButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField8 = new JTextField();
        textField8.setEditable(false);
        rootPanel.add(textField8, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label15 = new JLabel();
        label15.setText("Подсчет по каждой записи");
        rootPanel.add(label15, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label16 = new JLabel();
        label16.setText("Подсчет средних значений");
        rootPanel.add(label16, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label17 = new JLabel();
        label17.setText("Создание \"правильных\" данных");
        rootPanel.add(label17, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label18 = new JLabel();
        label18.setText("Создание данных с ошибками");
        rootPanel.add(label18, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label19 = new JLabel();
        label19.setText("Случайное наполнение");
        rootPanel.add(label19, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mRadioButton = new JRadioButton();
        mRadioButton.setText("M");
        rootPanel.add(mRadioButton, new GridConstraints(16, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        wRadioButton = new JRadioButton();
        wRadioButton.setText("W");
        rootPanel.add(wRadioButton, new GridConstraints(16, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        rootPanel.add(spacer3, new GridConstraints(16, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        addUserButton = new JButton();
        addUserButton.setText("Add User");
        rootPanel.add(addUserButton, new GridConstraints(17, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField9 = new JTextField();
        textField9.setEditable(false);
        rootPanel.add(textField9, new GridConstraints(17, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField10 = new JTextField();
        textField10.setEditable(false);
        rootPanel.add(textField10, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField11 = new JTextField();
        textField11.setEditable(false);
        rootPanel.add(textField11, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField12 = new JTextField();
        textField12.setEditable(false);
        rootPanel.add(textField12, new GridConstraints(13, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        countOutlinersButton = new JButton();
        countOutlinersButton.setText("CountOutliners");
        rootPanel.add(countOutlinersButton, new GridConstraints(20, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}

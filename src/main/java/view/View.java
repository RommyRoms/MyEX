package view;

import model.downloader.AbstractDownloader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JFrame {
    private JLabel topLabel;
    private JButton confirmButton;
    private JTextField pathTextField;
    private JLabel pathLabel;
    private Container container;
    private JPanel flowPanel;
    private JPanel gridPanel;
    private JPanel allPanel;
    private JPanel topPanel;

    private JPanel midPanel;
    private JTextField urlField;
    private JButton clearUrlButton;
    private JComboBox<String> box;
    private JButton findButton;
    private JPanel mid2Panel;
    private  JPanel midGrid;

    private JPanel textAreaPanel;
    private JTextArea textArea;
    private JLabel textLabel;

    private JPanel gridBotPanel;
    private JPanel botPanel;
    private JLabel conditionLabel;
    private JLabel listSize;
    private JButton downloadAllBotton;

    private Dimension littleDim;

    public static String conditionString = "";

    public View(){
        super("ex.ua downloader");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);
        littleDim = new Dimension(400, 700);
        this.setSize(littleDim);

        this.setResizable(false);
        container = this.getContentPane();

        makeTopPanel();
        makeMidPanel();
        makeTextAreaPanel();
        makeBottomPanel();
        init();
        this.setVisible(true);
    }




    private void makeTopPanel() {
        flowPanel = new JPanel(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(2,1,5,5));
        topPanel = new JPanel(new GridLayout(1,1,5,5));
        topLabel = new JLabel();
        pathLabel = new JLabel("DIR:   ");
        pathTextField = new JTextField(AbstractDownloader.directory+"   ");
        pathTextField.setEditable(false);
        pathTextField.setSize(450, 20);
        confirmButton = new JButton("Get path");

        flowPanel.add(pathLabel, BorderLayout.LINE_START);
        flowPanel.add(pathTextField, BorderLayout.CENTER);
        flowPanel.add(confirmButton, BorderLayout.LINE_END);

        gridPanel.setBorder(BorderFactory.createEtchedBorder(Color.lightGray, Color.darkGray));
        gridPanel.add(topLabel);
        gridPanel.add(flowPanel);
        topPanel.add(gridPanel);


    }

    private void makeMidPanel() {
        midPanel = new JPanel(new BorderLayout());
        urlField = new JTextField("Your URL");
        clearUrlButton = new JButton("Clear URL");
        box = new JComboBox<String>(AbstractDownloader.LIST_OF_FORMATS);
        findButton = new JButton("Search files");

        midPanel.add(urlField, BorderLayout.CENTER);
        midPanel.add(clearUrlButton, BorderLayout.LINE_END);
        midGrid = new JPanel(new GridLayout(2,1,5,5));
        midGrid.add(midPanel);
        mid2Panel= new JPanel(new BorderLayout());
        mid2Panel.add(box, BorderLayout.CENTER);
        mid2Panel.add(findButton, BorderLayout.LINE_END);
        midGrid.add(mid2Panel);
    }

    private void makeTextAreaPanel() {
        textAreaPanel = new JPanel(new BorderLayout());
        textLabel = new JLabel("Files", JLabel.CENTER);
        textArea = new JTextArea();
        textArea.setEditable(false);
        textAreaPanel.add(textLabel,BorderLayout.NORTH);
        textAreaPanel.add(textArea, BorderLayout.CENTER);
    }

    private void makeBottomPanel() {
        gridBotPanel = new JPanel(new GridLayout(2,1,5,5));
        botPanel = new JPanel(new BorderLayout());

        listSize = new JLabel("Files Quantity: ");
        conditionLabel = new JLabel("Welcome!");
        downloadAllBotton = new JButton("Save all files from list");

        botPanel.add(listSize, BorderLayout.WEST);
        botPanel.add(downloadAllBotton, BorderLayout.EAST);

        gridBotPanel.add(botPanel);
        gridBotPanel.add(conditionLabel);
    }
    /**
     * init() - метод, в котором:
     *            1.Определается реакция на нажатие кнопки openButton;
     *            2.Определается реакция на нажатие кнопки confirmButton;
     *            3.происходит сборка GUI
     */
    public void init() {
        //собираем весь GUI
        JPanel mainPanel = new JPanel(new BorderLayout());
        allPanel = new JPanel(new GridLayout(3,1,5,5));
        allPanel.setSize(this.getSize());
        allPanel.add(topPanel);
        allPanel.add(new JLabel());
        allPanel.add(midGrid);
        mainPanel.add(allPanel,BorderLayout.NORTH);
        mainPanel.add(textAreaPanel);
        mainPanel.add(gridBotPanel,BorderLayout.SOUTH);
        container.add(mainPanel);
    }
    public void setConfirmButtonListener(ActionListener actionListener){
        confirmButton.addActionListener(actionListener);
    }
    public void setClearUrlButtonListener(ActionListener actionListener){
        clearUrlButton.addActionListener(actionListener);
    }

    public void setFindButtonListener(ActionListener actionListener){
        findButton.addActionListener(actionListener);
    }

    public void setSaveAllButtonListener(ActionListener actionListener){
        downloadAllBotton.addActionListener(actionListener);
    }

    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }

    public JTextField getPathTextField() {
        return pathTextField;
    }

    public JTextField getUrlField() {
        return urlField;
    }

    public JComboBox<String> getBox() {
        return box;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public JLabel getConditionLabel() {
        return conditionLabel;
    }

    public JLabel getListSize() {
        return listSize;
    }
}

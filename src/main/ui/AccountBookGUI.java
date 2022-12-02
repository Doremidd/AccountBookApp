package ui;

import model.AccountBook;
import model.Cost;
import model.Event;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;



//Source:
// https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
// https://www.youtube.com/watch?v=1q7VzBiEchk

//A Graphical user interface of Accountbook
public class AccountBookGUI extends JFrame implements ActionListener {
    private JButton button1;
    private JButton button2;
    private JButton saveButton;
    private JButton loadButton;
    private AccountBook accountBook = new AccountBook("Selina's AccountBook");
    private JTextArea textArea;
    private JTextField datetext;
    private JTextField amounttext;
    private JTextField usagetext;
    private JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
    private JsonReader jsonReader = new JsonReader(JSON_STORE);
    private JPanel buttonPane;
    private JPanel labelPane;
    private JPanel textPane;
    private static final String JSON_STORE = "./data/acountbookGUI.json";
    private ImageIcon dateimage;
    private ImageIcon moneyimage;
    private ImageIcon reasonimage;


    //MODIFIES:this
    //EFFECTS: build the constructor for GUI and set up all labels,buttons and text
    public  AccountBookGUI() {
        super("AccountBook");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setPreferredSize(new Dimension(450, 550));
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                EventLog el = EventLog.getInstance();
                for (Event next:el) {
                    System.out.println(next.toString());
                }
                System.exit(0);
            }
        });
        this.setuplabelsandText();
        this.setupbuttons();
        Container contentPane = getContentPane();
        contentPane.add(labelPane, BorderLayout.NORTH);
        contentPane.add(textPane,BorderLayout.CENTER);
        contentPane.add(buttonPane, BorderLayout.SOUTH);
        pack();
        setVisible(true);

    }


    //MODIFIES;this
    //EFFECTS:set up all buttons needed
    public void setupbuttons() {
        buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));
        buttonPane.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        buttonPane.add(Box.createHorizontalGlue());
        button1 = new JButton("Add");
        button1.setActionCommand("Add");
        button1.addActionListener(this);
        buttonPane.add(button1);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        button2 = new JButton("Clear");
        button2.setActionCommand("Clear");
        button2.addActionListener(this);
        buttonPane.add(button2);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        saveButton = new JButton("Save");
        saveButton.setActionCommand("Save");
        saveButton.addActionListener(this);
        buttonPane.add(saveButton);
        buttonPane.add(Box.createRigidArea(new Dimension(10, 0)));
        loadButton = new JButton("Load");
        loadButton.setActionCommand("Load");
        loadButton.addActionListener(this);
        buttonPane.add(loadButton);
    }



    //MODIFIES:this
    //EFFECTS:set up all labels and textfield needed
    public void setuplabelsandText() {
        labelPane = new JPanel();
        labelPane.setLayout(new BoxLayout(labelPane, BoxLayout.PAGE_AXIS));
        datetext = new JTextField();
        datetext.setPreferredSize(new Dimension(100,30));
        amounttext = new JTextField();
        amounttext.setPreferredSize(new Dimension(100,30));
        usagetext = new JTextField();
        usagetext.setPreferredSize(new Dimension(100,30));

        loaddateimage();
        labelPane.add(datetext);
        loadmoneyimage();
        labelPane.add(amounttext);
        loadreasonimage();
        labelPane.add(usagetext);
        labelPane.setVisible(true);

        settextpane();

    }


    //EFFECTS: implement actionlisteners for the buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == button1) {
            String date = datetext.getText();
            Double amount = Double.parseDouble(amounttext.getText());
            String usage = usagetext.getText();
            Cost newcost = new Cost(date, amount, usage);
            accountBook.addcost(newcost);
        } else if (o == button2) {
            String givendate = datetext.getText();
            accountBook.clearCost(givendate);
        } else if (o == saveButton) {
            save();
        } else if (o == loadButton) {
            load();
        }
        ArrayList<String>  costlist = accountBook.showCost();
        textArea.setText(String.join("",costlist));
    }


    //EFFECTS:save the costs added to the accountbook
    public void save() {
        try {
            jsonWriter.open();
            jsonWriter.write(accountBook);
            jsonWriter.close();
        } catch (FileNotFoundException exception) {
            // all good
        }
    }

    //EFFECTS: load the content saved last time
    public void load() {
        try {
            accountBook = jsonReader.read();
        } catch (IOException exception) {
            // all good
        }
    }

    //EFFECTS: load the date image
    //Source of image:https://www.vecteezy.com/vector-art/4698190-calendar-business-date-
    // time-icon-symbol-hand-drawn-cartoon-art-illustration
    public void loaddateimage() {
        dateimage = new ImageIcon("./data/date.png");
        Image img = dateimage.getImage();
        Image imgScale = img.getScaledInstance(70,70,Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        JLabel date = new JLabel("Date",scaledIcon,SwingConstants.RIGHT);
        date.setSize(200,80);
        labelPane.add(date);
    }

    //EFFECTS: load the money image
    //Source of image:https://www.vectorstock.com/royalty-free-vector/money-banknote-and-
    // coin-stack-cash-cartoon-vector-30854137
    public void loadmoneyimage() {
        moneyimage = new ImageIcon("./data/money.png");
        Image img = moneyimage.getImage();
        Image imgScale = img.getScaledInstance(70,70,Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        JLabel amount = new JLabel("Amount",scaledIcon,SwingConstants.RIGHT);
        amount.setSize(200,80);
        labelPane.add(amount);

    }

    //EFFECTS: load the reason image
    //Source of image:https://www.citypng.com/photo/28557/cartoon-question-
    // mark-yellow-character-hd-png
    public void loadreasonimage() {
        reasonimage = new ImageIcon("./data/reason.png");
        Image img = reasonimage.getImage();
        Image imgScale = img.getScaledInstance(70,70,Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(imgScale);
        JLabel usage = new JLabel("Usage",scaledIcon,SwingConstants.RIGHT);
        usage.setSize(200,80);
        labelPane.add(usage);

    }

    public void settextpane() {
        textPane = new JPanel();
        textPane.setLayout(new BoxLayout(textPane, BoxLayout.PAGE_AXIS));
        JLabel title = new JLabel("RECORDS",SwingConstants.CENTER);
        textArea = new JTextArea();
        JScrollPane areaScrollPane = new JScrollPane(textArea);
        areaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        areaScrollPane.setPreferredSize(new Dimension(300, 200));
        areaScrollPane.setAlignmentX(LEFT_ALIGNMENT);
        textPane.add(title);
        textPane.add(Box.createRigidArea(new Dimension(0,5)));
        textPane.add(textArea);
    }


    public static void main(String[] args) {
        new AccountBookGUI();
    }


}

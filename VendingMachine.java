import java.util.HashMap;
import java.util.Map;

import java.io.*;
import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class VendingMachine extends JFrame{
    DecimalFormat frmt = new DecimalFormat("#.##");
    JButton[] button = new JButton[6];
    JTextArea textArea = new JTextArea();

    Frame frame;
    Dialog dialog;
    float money = 0.0f;
    String[] item = new String[8];
    int[] itemcount = new int[8];

    JButton buttonformoney;
    JButton itemButton;
    JButton dwJButton;

    JButton[] moneyButtons = new JButton[7];
    JButton[] choiseButtons = new JButton[8];
    JButton[] dontwantitemButtons = new JButton[8];

    JButton butforerror;
    JButton butforreset;

    JTextArea textmoneyArea;
    public VendingMachine(String title){
        super(title);
        this.setLocation(150,250);
        this.setSize(500, 350);
        Container contentpane = this.getContentPane();
        contentpane.setBackground(Color.pink);
        contentpane.setLayout(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(1,5));
        
        MyActionListener actionListener = new MyActionListener();
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        contentpane.add(scrollPane,BorderLayout.CENTER);
        button[1] = new JButton();
        button[1].setText("投錢");
        button[2] = new JButton();
        button[2].setText("選擇商品");
        button[3] = new JButton();
        button[3].setText("取消商品");
        button[4] = new JButton();
        button[4].setText("結帳");
        button[5] = new JButton();
        button[5].setText("歸零");
        for(int i=1;i<6;i++){
            button[i].addActionListener(actionListener);
            panel.add(button[i]);
        }
        contentpane.add(panel,BorderLayout.SOUTH);
        
        item[0]="Coca Cola";
        item[1]="Minute Maid Orange Juice";
        item[2]="Evian Mineral Water";
        item[3]="M&M's Chocolate";
        item[4]="Hershey's Chocolate Bar";
        item[5]="Oreo Cookies";
        item[6]="Doritos Tortilla Chips";
        item[7]="Pringles Potato Chips";
        textArea.append("money : "+frmt.format(money)+"\n");
        for(int i=0;i<8;i++){
            itemcount[i] = 0;
            textArea.append(item[i]+" : "+itemcount[i]+"\n");
        }
        textArea.append("total price from item : "+frmt.format(count())+"\n");
        this.setVisible(true);
    }
    public float count(){
        float count = 0.0f;
        count+=itemcount[0]*1.65f;
        count+=itemcount[1]*3.5f;
        count+=itemcount[2]*2.8f;
        count+=itemcount[3]*1.5f;
        count+=itemcount[4]*1.85f;
        count+=itemcount[5]*1;
        count+=itemcount[6]*3.25f;
        count+=itemcount[7]*3.4f;
        return count;
    }

    class MyActionListener implements ActionListener{
        public void actionPerformed(ActionEvent e){



            if(e.getSource() == button[1]){
                dialog = new Dialog(frame,"輸入金額");
                dialog.setSize(300, 200);
                Container cont = new Container();
                cont.setLayout(new GridLayout(4, 1));
                textmoneyArea = new JTextArea();
                textmoneyArea.setEditable(false);
                textmoneyArea.setText(frmt.format(money));
                cont.add(textmoneyArea);
                

                JPanel panel1 = new JPanel(new GridLayout(1, 5));
                JTextArea textArea1 = new JTextArea("選擇大鈔");
                textArea1.setEditable(false);
                panel1.add(textArea1);

                moneyButtons[0] = new JButton("1");
                moneyButtons[1] = new JButton("5");
                moneyButtons[2] = new JButton("10");
                moneyButtons[3] = new JButton("20");
                for(int i=0;i<4;i++){
                    moneyButtons[i].addActionListener(this);
                    panel1.add(moneyButtons[i]);
                }
                
                cont.add(panel1);
                
                JPanel panel2 = new JPanel(new GridLayout(1,4));
                JTextArea textArea2 = new JTextArea("選擇硬幣");
                textArea2.setEditable(false);
                panel2.add(textArea2);

                moneyButtons[4] = new JButton("5");
                moneyButtons[5] = new JButton("10");
                moneyButtons[6] = new JButton("25");
                for(int i=4;i<7;i++){
                    moneyButtons[i].addActionListener(this);
                    panel2.add(moneyButtons[i]);
                }
                cont.add(panel2);

                buttonformoney = new JButton("完成");
                buttonformoney.addActionListener(this);
                cont.add(buttonformoney);


                dialog.add(cont);
                dialog.setVisible(true);
            }
            else if(e.getSource() == button[2]){
                dialog = new Dialog(frame,"商品項目");
                dialog.setSize(300, 500);
                Container cont = new Container();
                cont.setLayout(new GridLayout(5, 1));
                JTextArea textfornum1 = new JTextArea();
                textfornum1.setText("A: Coca Cola, unit price US $1.65\r\n" + //
                        "B: Minute Maid Orange Juice, unit price US $3.50\r\n" + //
                        "C: Evian Mineral Water, unit price US $2.80\r\n" + //
                        "D: M&M's Chocolate, unit price US $1.50\r\n" + //
                        "E: Hershey's Chocolate Bar, unit price US $1.85\r\n" + //
                        "F: Oreo Cookies, unit price US $1.00\r\n" + //
                        "G: Doritos Tortilla Chips, unit price US $3.25\r\n" + //
                        "H: Pringles Potato Chips, unit price US $3.40");
                JScrollPane scrollPane1 = new JScrollPane(textfornum1);
                textfornum1.setEditable(false);
                cont.add(scrollPane1);
                
                textmoneyArea = new JTextArea();
                JScrollPane scrollPane2 = new JScrollPane(textmoneyArea);
                textmoneyArea.setEditable(false);
                textmoneyArea.setText("");
                for(int i=0;i<8;i++)
                    textmoneyArea.append(item[i]+" : "+itemcount[i]+"\n");
                textmoneyArea.append("total price from item : "+frmt.format(count())+"\n");
                cont.add(scrollPane2);

                JPanel panel1 = new JPanel(new GridLayout(1, 4));
                
                choiseButtons[0] = new JButton("A");
                choiseButtons[1] = new JButton("B");
                choiseButtons[2] = new JButton("C");
                choiseButtons[3] = new JButton("D");
                for(int i=0;i<4;i++){
                    choiseButtons[i].addActionListener(this);
                    panel1.add(choiseButtons[i]);
                }
                cont.add(panel1);
                
                JPanel panel2 = new JPanel(new GridLayout(1,4));
                

                choiseButtons[4] = new JButton("E");
                choiseButtons[5] = new JButton("F");
                choiseButtons[6] = new JButton("G");
                choiseButtons[7] = new JButton("H");
                for(int i=4;i<8;i++){
                    choiseButtons[i].addActionListener(this);
                    panel2.add(choiseButtons[i]);
                }
                cont.add(panel2);

                itemButton = new JButton("Quit");
                itemButton.addActionListener(this);
                cont.add(itemButton);


                dialog.add(cont);
                dialog.setVisible(true);
            }
            else if(e.getSource() == button[3]){
                dialog = new Dialog(frame,"取消商品");
                dialog.setSize(300, 500);
                Container cont = new Container();
                cont.setLayout(new GridLayout(4, 1));
                textmoneyArea = new JTextArea();
                JScrollPane scrollPane2 = new JScrollPane(textmoneyArea);
                textmoneyArea.setEditable(false);
                textmoneyArea.setText("");
                for(int i=0;i<8;i++)
                    textmoneyArea.append(item[i]+" : "+itemcount[i]+"\n");
                textmoneyArea.append("total price from item : "+frmt.format(count())+"\n");
                cont.add(scrollPane2);

                JPanel panel1 = new JPanel(new GridLayout(1, 4));
                

                dontwantitemButtons[0] = new JButton("A");
                dontwantitemButtons[1] = new JButton("B");
                dontwantitemButtons[2] = new JButton("C");
                dontwantitemButtons[3] = new JButton("D");
                for(int i=0;i<4;i++){
                    dontwantitemButtons[i].addActionListener(this);
                    panel1.add(dontwantitemButtons[i]);
                }
                cont.add(panel1);
                
                JPanel panel2 = new JPanel(new GridLayout(1,4));
                

                dontwantitemButtons[4] = new JButton("E");
                dontwantitemButtons[5] = new JButton("F");
                dontwantitemButtons[6] = new JButton("G");
                dontwantitemButtons[7] = new JButton("H");
                for(int i=4;i<8;i++){
                    dontwantitemButtons[i].addActionListener(this);
                    panel2.add(dontwantitemButtons[i]);
                }
                cont.add(panel2);

                dwJButton = new JButton("Confirm");
                dwJButton.addActionListener(this);
                cont.add(dwJButton);


                dialog.add(cont);
                dialog.setVisible(true);
            }
            else if(e.getSource() == button[4]){
                if(money>=count()){
                    dialog = new Dialog(frame,"Checkout");
                    dialog.setSize(300, 200);
                    Container cont = new Container();
                    cont.setLayout(new BorderLayout());

                    JTextArea tex = new JTextArea();
                    float tempcount = 0.0f;
                    tempcount = money-count();
                    tex.setText("");
                    for(int i=0;i<8;i++){
                        if(itemcount[i]!=0){
                            tex.append(item[i]+" x "+itemcount[i]+"\n");
                        }
                    }
                    tex.append("Rest money : "+frmt.format(tempcount)+"\n");
                    tex.setEditable(false);
                    cont.add(tex,BorderLayout.CENTER);

                    butforreset = new JButton("重置販賣機");
                    butforreset.addActionListener(this);
                    cont.add(butforreset,BorderLayout.SOUTH);
                    
                    dialog.add(cont);
                    dialog.setVisible(true);
                }
                else{
                    dialog = new Dialog(frame,"ERROR");
                    dialog.setSize(300, 200);
                    Container cont = new Container();
                    cont.setLayout(new BorderLayout());

                    JTextArea tex = new JTextArea();
                    tex.setText("輸入金額小於商品金額!!"+"\n"+"請加值！");
                    tex.setEditable(false);
                    cont.add(tex,BorderLayout.CENTER);

                    butforerror = new JButton("確定");
                    butforerror.addActionListener(this);
                    cont.add(butforerror,BorderLayout.SOUTH);
                    
                    dialog.add(cont);
                    dialog.setVisible(true);
                }
            }
            else if(e.getSource() == button[5]){
                resetVendingMachine();
                textArea.setText("money : "+frmt.format(money)+"\n");
                for(int i=0;i<8;i++){
                    itemcount[i] = 0;
                    textArea.append(item[i]+" : "+itemcount[i]+"\n");
                }
                textArea.append("total price from item : "+frmt.format(count())+"\n");
            }
            else if(e.getSource() == moneyButtons[0]){
                money+=1;
                textmoneyArea.setText(frmt.format(money));
            }
            else if(e.getSource() == moneyButtons[1]){
                money+=5;
                textmoneyArea.setText(frmt.format(money));
            }
            else if(e.getSource() == moneyButtons[2]){
                money+=10;
                textmoneyArea.setText(frmt.format(money));
            }
            else if(e.getSource() == moneyButtons[3]){
                money+=20;
                textmoneyArea.setText(frmt.format(money));
            }
            else if(e.getSource() == moneyButtons[4]){
                money+=0.05f;
                textmoneyArea.setText(frmt.format(money));
            }
            else if(e.getSource() == moneyButtons[5]){
                money+=0.1f;
                textmoneyArea.setText(frmt.format(money));
            }
            else if(e.getSource() == moneyButtons[6]){
                money+=0.25f;
                textmoneyArea.setText(frmt.format(money));
            }
            else if(e.getSource() == buttonformoney){
                dialog.setVisible(false);
                textArea.setText("money : "+frmt.format(money)+"\n");
                full(textArea);
            }
            else if(e.getSource() == choiseButtons[0]){
                itemcount[0]++;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == choiseButtons[1]){
                itemcount[1]++;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == choiseButtons[2]){
                itemcount[2]++;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == choiseButtons[3]){
                itemcount[3]++;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == choiseButtons[4]){
                itemcount[4]++;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == choiseButtons[5]){
                itemcount[5]++;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == choiseButtons[6]){
                itemcount[6]++;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == choiseButtons[7]){
                itemcount[7]++;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == itemButton){
                dialog.setVisible(false);
                textArea.setText("money : "+frmt.format(money)+"\n");
                full(textArea);
            }
            else if(e.getSource() == dontwantitemButtons[0]){
                if(itemcount[0]!=0)
                    itemcount[0]--;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == dontwantitemButtons[1]){
                if(itemcount[1]!=0)
                    itemcount[1]--;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == dontwantitemButtons[2]){
                if(itemcount[2]!=0)
                    itemcount[2]--;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == dontwantitemButtons[3]){
                if(itemcount[3]!=0)
                    itemcount[3]--;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == dontwantitemButtons[4]){
                if(itemcount[4]!=0)
                    itemcount[4]--;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == dontwantitemButtons[5]){
                if(itemcount[5]!=0)
                    itemcount[5]--;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == dontwantitemButtons[6]){
                if(itemcount[6]!=0)
                    itemcount[6]--;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == dontwantitemButtons[7]){
                if(itemcount[7]!=0)
                    itemcount[7]--;
                textmoneyArea.setText("");
                full(textmoneyArea);
            }
            else if(e.getSource() == dwJButton){
                dialog.setVisible(false);
                textArea.setText("money : "+frmt.format(money)+"\n");
                full(textArea);
            }
            else if(e.getSource() == butforerror){
                dialog.setVisible(false);
            }
            else if(e.getSource() == butforreset){
                dialog.setVisible(false);
                resetVendingMachine();
                textArea.setText("money : "+frmt.format(money)+"\n");
                for(int i=0;i<8;i++){
                    itemcount[i] = 0;
                    textArea.append(item[i]+" : "+itemcount[i]+"\n");
                }
                textArea.append("total price from item : "+frmt.format(count())+"\n");
            }

        }
    }
    public void resetVendingMachine(){
        money = 0.0f;
        for(int i=0;i<8;i++){
            itemcount[i] = 0;
        }
    }
    public void full(JTextArea textArea){
        for(int i=0;i<8;i++)
            textArea.append(item[i]+" : "+itemcount[i]+"\n");
        textArea.append("total price from item : "+frmt.format(count())+"\n");
    }
    public static void main(String[] args) {
        VendingMachine vendFrame = new VendingMachine("VendingMachine");
    }
}


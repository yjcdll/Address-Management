package ui;

import java.awt.Font;

public class JTextField extends javax.swing.JTextField{
    public JTextField(){
        super();
        this.setSize(180,30);
        this.setFont(new Font("微软雅黑",0,14));
        this.setDocument(new StringDeal());
    } 
}

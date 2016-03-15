package ui;

import java.awt.Color;
import java.awt.Font;

public class JLabel extends javax.swing.JLabel{
    public JLabel(String text){
        super(text);
        this.setForeground(new Color(100,200,40));
        this.setFont(new Font("微软雅黑",0,20));//设置字体，0正常，1粗体
        this.setSize(100,40);
    }
}

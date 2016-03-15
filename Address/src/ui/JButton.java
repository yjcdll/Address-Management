/**
 * 自定义的JButton
 */
package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

public class JButton extends javax.swing.JButton{
    public JButton(String text) {
        super(text);
        this.setPreferredSize(new Dimension(100,45));
        this.setBackground(new Color(161,253,216));
        this.setFont(new Font(Font.SERIF,Font.PLAIN,14));
        this.setForeground(new Color(20,50,32));
    }
}

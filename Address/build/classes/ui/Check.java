package ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JButton;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class Check extends JPanel{
    private final Table table;
    private final JButton button=new JButton(">>");
    private final CheckGroup checkGroup;
    public Check(Table table){
        this.table=table;
        checkGroup=new CheckGroup();
        this.add(button);
        button.setComponentPopupMenu(checkGroup);
        button.addActionListener((ActionEvent e) -> {
            //弹出的check要在此按钮的下方
            //checkGroup.setLocation(button.getLocationOnScreen().x+button.getSize().width,button.getLocationOnScreen().y+button.getSize().height);            
            checkGroup.show(button,-button.getSize().width/3*2,button.getSize().height);
        });
        //this.setBackground(Color.red);
    }

private class CheckGroup extends JPopupMenu{
    private final TableColumnModel tcm;
    private final JCheckBox nameCheck=new JCheckBox("姓名");
    private final TableColumn nameColumn;
    private final JCheckBox mobilePhoneCheck=new JCheckBox("手机");
    private final TableColumn mobilePhoneColumn;
    private final JCheckBox emailCheck=new JCheckBox("电子邮箱");
    private final TableColumn emailColumn;
    private final JCheckBox telephoneCheck=new JCheckBox("固定电话");
    private final TableColumn telephoneColumn;
    private final JCheckBox cornetCheck=new JCheckBox("短号");
    private final TableColumn cornetColumn;
    private final JCheckBox birthdayCheck=new JCheckBox("生日");
    private final TableColumn birthdayColumn;
    private final JCheckBox workUnitCheck=new JCheckBox("工作单位");
    private final TableColumn workUnitColumn;
    private final JCheckBox familyAddressCheck=new JCheckBox("家庭地址");
    private final TableColumn familyAddressColumn;
    private final JCheckBox postcodeCheck=new JCheckBox("邮编");
    private final TableColumn postcodeColumn;
    private final JCheckBox instantMessagingCheck=new JCheckBox("即时通讯");
    private final TableColumn instantMessagingColumn;
    private final JCheckBox personalHomepageCheck=new JCheckBox("个人主页");
    private final TableColumn personalHomepageColumn;
    private final JCheckBox commentCheck=new JCheckBox("备注");
    private final TableColumn commentColumn;
        
    public CheckGroup(){
        this.setLayout(new GridLayout(0,1));
        tcm=table.getColumnModel();
        nameColumn=table.getColumn("姓名");
        mobilePhoneColumn=table.getColumn("手机");
        emailColumn=table.getColumn("电子邮箱");
        telephoneColumn=table.getColumn("固定电话");
        cornetColumn=table.getColumn("短号");
        birthdayColumn=table.getColumn("生日");
        workUnitColumn=table.getColumn("工作单位");
        familyAddressColumn=table.getColumn("家庭地址");
        postcodeColumn=table.getColumn("邮编");
        instantMessagingColumn=table.getColumn("即时通讯");
        personalHomepageColumn=table.getColumn("个人主页");
        commentColumn=table.getColumn("备注");
        
        tcm.removeColumn(telephoneColumn);
        tcm.removeColumn(cornetColumn);
        tcm.removeColumn(birthdayColumn);
        tcm.removeColumn(workUnitColumn);
        tcm.removeColumn(familyAddressColumn);
        tcm.removeColumn(postcodeColumn);
        tcm.removeColumn(instantMessagingColumn);
        tcm.removeColumn(personalHomepageColumn);
        tcm.removeColumn(commentColumn);
        
        this.add(nameCheck);nameCheck.setEnabled(false);
        nameCheck.setSelected(true);
        this.add(mobilePhoneCheck);mobilePhoneCheck.setSelected(true);
        mobilePhoneCheck.addActionListener((ActionEvent e) -> {//控制“手机”列的显示或不显示
            if(mobilePhoneCheck.isSelected()) tcm.addColumn(mobilePhoneColumn);
            else tcm.removeColumn(mobilePhoneColumn);
        });
        this.add(emailCheck);emailCheck.setSelected(true);
        emailCheck.addActionListener((ActionEvent e) -> {
            if(emailCheck.isSelected()) tcm.addColumn(emailColumn);
            else tcm.removeColumn(emailColumn);
        });        
        this.add(telephoneCheck);
        telephoneCheck.addActionListener((ActionEvent e) -> {
            if(telephoneCheck.isSelected()) tcm.addColumn(telephoneColumn);
            else tcm.removeColumn(telephoneColumn);
        });        
        this.add(cornetCheck);
        cornetCheck.addActionListener((ActionEvent e) -> {
            if(cornetCheck.isSelected()) tcm.addColumn(cornetColumn);
            else tcm.removeColumn(cornetColumn);
        });        
        this.add(birthdayCheck);
        birthdayCheck.addActionListener((ActionEvent e) -> {
            if(birthdayCheck.isSelected()) tcm.addColumn(birthdayColumn);
            else tcm.removeColumn(birthdayColumn);
        });        
        this.add(workUnitCheck);
        workUnitCheck.addActionListener((ActionEvent e) -> {
            if(workUnitCheck.isSelected()) tcm.addColumn(workUnitColumn);
            else tcm.removeColumn(workUnitColumn);
        });        
        this.add(familyAddressCheck);
        familyAddressCheck.addActionListener((ActionEvent e) -> {
            if(familyAddressCheck.isSelected()) tcm.addColumn(familyAddressColumn);
            else tcm.removeColumn(familyAddressColumn);
        });        
        this.add(postcodeCheck);
        postcodeCheck.addActionListener((ActionEvent e) -> {
            if(postcodeCheck.isSelected()) tcm.addColumn(postcodeColumn);
            else tcm.removeColumn(postcodeColumn);
        });        
        this.add(instantMessagingCheck);
        instantMessagingCheck.addActionListener((ActionEvent e) -> {
            if(instantMessagingCheck.isSelected()) tcm.addColumn(instantMessagingColumn);
            else tcm.removeColumn(instantMessagingColumn);
        });        
        this.add(personalHomepageCheck);
        personalHomepageCheck.addActionListener((ActionEvent e) -> {
            if(personalHomepageCheck.isSelected()) tcm.addColumn(personalHomepageColumn);
            else tcm.removeColumn(personalHomepageColumn);
        });        
        this.add(commentCheck);
        commentCheck.addActionListener((ActionEvent e) -> {
            if(commentCheck.isSelected()) tcm.addColumn(commentColumn);
            else tcm.removeColumn(commentColumn);
        });        
        this.setVisible(false);
        //下面两句话的顺序调转就不能运行了
        //this.setUndecorated(true);
        //this.pack();
    }
}
}

package ui;

import address.Address;
import address.Contact;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ContactButton extends JPanel{
    private final Address address;
    private final Table table;
    private final JButton addContactButton = new JButton("新增联系人");
    private final JButton deleteContactButton = new JButton("删除联系人");
    private final JButton modifyContactButton = new JButton("修改联系人");
    private final Display addFrame;
    private final Display modifyFrame;
    
    public ContactButton(Table table,Address address){
        this.address = address;
        this.addFrame = new Display();
        this.modifyFrame = new Display();
        this.table=table;
        this.add(addContactButton);
        this.add(deleteContactButton);
        this.add(modifyContactButton);
        addContactButton.addActionListener((ActionEvent e) -> {
            addFrame.name1.setText("");
            addFrame.birthday1.setText("");
            addFrame.comment1.setText("");
            addFrame.cornet1.setText("");
            addFrame.email1.setText("");
            addFrame.familyAddress1.setText("");
            addFrame.fixedlineTelephone1.setText("");
            addFrame.instantMessaging1.setText("");
            addFrame.personalHomepage1.setText("");
            addFrame.postCode1.setText("");
            addFrame.telephone1.setText("");
            addFrame.workUnit1.setText("");
            addFrame.setData();
            addFrame.groups1.setText("");
            addFrame.setVisible(true);       
        }); 
        
        addFrame.save.addActionListener((ActionEvent evn) -> {
                Contact contact = new Contact();
                if(addFrame.name1.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"名字不能为空","attention",JOptionPane.WARNING_MESSAGE);   return;}
                int n = JOptionPane.showConfirmDialog(null,"确定要保存吗？","提示",JOptionPane.YES_NO_OPTION);
                if( n == JOptionPane.NO_OPTION) return;
                contact.setName(addFrame.name1.getText());
                contact.setMobilePhone(addFrame.telephone1.getText());
                contact.setEmail(addFrame.email1.getText());
                contact.setTelephone(addFrame.fixedlineTelephone1.getText());
                contact.setCornet(addFrame.cornet1.getText());
                contact.setBirthday(addFrame.birthday1.getText());
                contact.setWorkUnit(addFrame.workUnit1.getText());
                contact.setFamilyAddress(addFrame.familyAddress1.getText());
                contact.setPostcode(addFrame.postCode1.getText());
                contact.setInstantMessaging(addFrame.instantMessaging1.getText());
                contact.setPhoto("");
                contact.setPersonalHomepage(addFrame.personalHomepage1.getText());
                contact.setComment(addFrame.comment1.getText());
                Vector vector = new Vector();
                if(!addFrame.groups1.getText().equals("")){
                    String[] tokens = addFrame.groups1.getText().split(";", 0);
                for(int i = 0;i<tokens.length;i++){  vector.add(tokens[i]);  }
                }               
                contact.setGroup(vector);
                address.addContact(contact);
                addFrame.setVisible(false);
                table.refreshData();
                table.fireTableDataChanged();
            });
        
        deleteContactButton.addActionListener((ActionEvent e) ->{
            
           if(table.getSelectedRow() < 0){
              JOptionPane.showMessageDialog(null,"请选择要删除的联系人。","attention",JOptionPane.INFORMATION_MESSAGE);
              return;
           }
            int n = JOptionPane.showConfirmDialog(null,"确定要删除选中的联系人吗？","提示",JOptionPane.YES_NO_OPTION);
            if( n == JOptionPane.NO_OPTION) return; 
           table.deleteContact();
           table.refreshData();
           table.fireTableDataChanged();
           JOptionPane.showMessageDialog(null,"已删除联系人。","attention",JOptionPane.INFORMATION_MESSAGE);            
        });
    modifyContactButton.addActionListener((ActionEvent e) -> {
        if(table.getSelectedRow() < 0){
            JOptionPane.showMessageDialog(null,"请选择要修改的联系人。","attention",JOptionPane.INFORMATION_MESSAGE);
            return;
        }//判断是否选中
        Contact people ;
        people = table.getValueAt();
        modifyFrame.name1.setText(people.getName());
        modifyFrame.telephone1.setText(people.getMobilePhone());
        modifyFrame.birthday1.setText(people.getBirthday());
        modifyFrame.comment1.setText(people.getComment());
        modifyFrame.cornet1.setText(people.getCornet());
        modifyFrame.email1.setText(people.getEmail());
        modifyFrame.workUnit1.setText(people.getWorkUnit());
        modifyFrame.familyAddress1.setText(people.getFamilyAddress());
        modifyFrame.fixedlineTelephone1.setText(people.getTelephone());
        modifyFrame.setData();
        modifyFrame.groups1.setText("");
        String grouping = "" ;
        if(people.getGroup().isEmpty()){
            modifyFrame.groups1.setText(grouping);   }
        else{
            for(int i = 0; i < people.getGroup().size(); i++){
                grouping = grouping + people.getGroup().elementAt(i) + ";";
            }
            modifyFrame.groups1.setText(grouping);
        }
        modifyFrame.instantMessaging1.setText(people.getInstantMessaging());
        modifyFrame.personalHomepage1.setText(people.getPersonalHomepage());
        modifyFrame.postCode1.setText(people.getPostcode());
        modifyFrame.setVisible(true);
        });
       
      modifyFrame.save.addActionListener((ActionEvent evn) -> {
          Contact contact = new Contact();
          if(modifyFrame.name1.getText().equals("")){
              JOptionPane.showMessageDialog(null,"名字不能为空。","attention",JOptionPane.WARNING_MESSAGE);   return;}
          int n = JOptionPane.showConfirmDialog(null,"确定要保存吗？","提示",JOptionPane.YES_NO_OPTION);
          if( n == JOptionPane.NO_OPTION) return;
          int count = address.indexOfAllContacts(table.getValueAt());
          contact.setName(modifyFrame.name1.getText());
          contact.setMobilePhone(modifyFrame.telephone1.getText());
          contact.setEmail(modifyFrame.email1.getText());
          contact.setTelephone(modifyFrame.fixedlineTelephone1.getText());
          contact.setCornet(modifyFrame.cornet1.getText());
          contact.setBirthday(modifyFrame.birthday1.getText());
          contact.setWorkUnit(modifyFrame.workUnit1.getText());
          contact.setFamilyAddress(modifyFrame.familyAddress1.getText());
          contact.setPostcode(modifyFrame.postCode1.getText());
          contact.setInstantMessaging(modifyFrame.instantMessaging1.getText());
          contact.setPhoto("");
          contact.setPersonalHomepage(modifyFrame.personalHomepage1.getText());
          contact.setComment(modifyFrame.comment1.getText());
          Vector vector = new Vector();
          String[] tokens = modifyFrame.groups1.getText().split(";", 0);
          for(int i = 0;i<tokens.length;i++){  vector.add(tokens[i]);  }
          contact.setGroup(vector);
          address.set(count, contact);
          table.refreshData();
          table.fireTableDataChanged();
          modifyFrame.setVisible(false);
        });   
    }
    
    private class Display extends JFrame{
    private final JLabel title = new JLabel("联系人");
    private final JLabel name = new JLabel("姓名");
    final  JTextField name1 = new JTextField();
    private final JLabel telephone = new JLabel("手机");
    final JTextField telephone1 = new JTextField();
    private final JLabel cornet = new JLabel("短号");
    final JTextField cornet1 = new JTextField();
    private final JLabel email = new JLabel("电子邮箱");
    final JTextField email1 = new JTextField();
    private final JLabel birthday = new JLabel("生日");
    final JTextField birthday1 = new JTextField();
    private final JLabel instantMessaging = new JLabel("即时通讯");
    final JTextField instantMessaging1 = new JTextField();
    private final JLabel workUnit = new JLabel("工作单位");
    final JTextField workUnit1 = new JTextField();
    private final JLabel familyAddress = new JLabel("家庭地址");
    final JTextField familyAddress1 = new JTextField();
    private final JLabel postCode = new JLabel("邮编");
    final JTextField postCode1 = new JTextField();
    private final JLabel comment = new JLabel("备注");
    final JTextField comment1 = new JTextField();
    private final JLabel personalHomepage = new JLabel("个人主页");
    final JTextField personalHomepage1 = new JTextField();
    final JButton save = new JButton("保存");
    private final JLabel fixedlineTelephone = new JLabel("固定电话");
    final JTextField fixedlineTelephone1 = new JTextField();
    private final JLabel groups = new JLabel("组别");
    final JTextField groups1 = new JTextField();
    private final JComboBox g = new JComboBox();
    
   public Display(){
       this.setLayout(null);
       title.setLocation(130,0);
       title.setForeground(new Color(100,130,170));
       title.setFont(new Font("微软雅黑",1,20));
       this.add(title); 
       name.setLocation(45,33);
       this.add(name);
       name1.setLocation(95,40);
       //name1.setDocument(new StringDeal());
       this.add(name1);
       telephone.setLocation(45,68);
       this.add(telephone);
       telephone1.setLocation(95,75);
       telephone1.setDocument(new NumberOnlyDocument());
       this.add(telephone1);
       email.setLocation(5, 103);
       this.add(email);
       email1.setLocation(95, 110);
       //email1.setDocument(new StringDeal());
       this.add(email1);
       fixedlineTelephone.setLocation(5,138);
       this.add(fixedlineTelephone);
       fixedlineTelephone1.setLocation(95,145);
       this.add(fixedlineTelephone1);
       cornet.setLocation(45, 173);
       this.add(cornet);
       cornet1.setLocation(95, 180);
       cornet1.setDocument(new NumberOnlyDocument());
       this.add(cornet1);
       birthday.setLocation(45, 208);
       this.add(birthday);
       birthday1.setDocument(new NumberOnlyDocument());
       birthday1.setLocation(95, 215);
       this.add(birthday1);
       workUnit.setLocation(5,248);
       this.add(workUnit);
       workUnit1.setLocation(95,255);
       //workUnit1.setDocument(new StringDeal());
       this.add(workUnit1);
       familyAddress.setLocation(5,283);
       this.add(familyAddress);
       familyAddress1.setLocation(95,290);
       //familyAddress1.setDocument(new StringDeal());
       this.add(familyAddress1);
       postCode.setLocation(45,318);
       this.add(postCode);
       postCode1.setDocument(new NumberOnlyDocument());
       postCode1.setLocation(95,325);
       this.add(postCode1);
       instantMessaging.setLocation(5,353);
       this.add(instantMessaging);
       instantMessaging1.setLocation(95,360);
       //instantMessaging1.setDocument(new StringDeal());
       this.add(instantMessaging1);
       personalHomepage.setLocation(5,388);
       this.add(personalHomepage);
       personalHomepage1.setLocation(95,395);
       //personalHomepage1.setDocument(new StringDeal());
       this.add(personalHomepage1);
       comment.setLocation(45,423);
       this.add(comment);
       comment1.setLocation(95,430);
       this.add(comment1);
       groups.setLocation(45,458);
       this.add(groups);
      groups1.setEditable(false);
       groups1.setLocation(95,465);
       this.add(groups1);
       g.setSize(100,25);
       g.setLocation(280,468);
       this.add(g); 
       g.addActionListener((ActionEvent e) -> {
           if(groups1.getText().equals(""))
               groups1.setText(groups1.getText() + (String)g.getSelectedItem() + ";");
           else {
               if((groups1.getText().indexOf((String)g.getSelectedItem() + ";")) == -1)
                   groups1.setText(groups1.getText() + (String)g.getSelectedItem() + ";");
               else
                   groups1.setText(groups1.getText().replaceAll((String)g.getSelectedItem() + ";" ,""));
           }
       });
       save.setSize(80,25);
       save.setLocation(125,510);
       this.add(save);
       this.setSize(400,600);
       this.setResizable(false);
       this.getContentPane().setBackground(new Color(243,255,233));   
       this.setLocationRelativeTo(null);
       this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
   }
   
    public void setData(){
        g.removeAllItems();
        for(int i = 1; i < address.getGroupNames().size();i++){
           g.addItem(address.getGroupNames().get(i));
       }
   }
  }
}

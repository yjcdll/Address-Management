package ui;

import address.Address;
import address.Contact;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Table extends JPanel{
    private final Address address;//所有组件共用
    //private final ContactButton contactButton;//table单元格被选中，会影响contactButton的能否点击
    private final JTable table=new JTable();//在Check里面被用到
    private final Model model=new Model();//表格模式
    //private final Renderer render=new Renderer();//渲染器
    private final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);
    private final JScrollPane pane = new JScrollPane(table);

    public Table(Address address) {
        this.address = address;this.refreshData();
        table.setModel(model);       
        table.setRowSorter(sorter);
        //pane.setViewportView(table);
        this.add(pane);
        table.setShowVerticalLines(false);
        table.setRowHeight(40);
        //table.setSelectionBackground(new Color(230,255,255));
        table.setPreferredScrollableViewportSize(new Dimension(930,500));
        //table.setMinimumSize(new Dimension(930,500));
        //this.setBackground(Color.white);
        //table.setOpaque(true);
    }
    public void setFilter(RowFilter rf){
        sorter.setRowFilter(rf);
    }
    public TableColumnModel getColumnModel(){
        return table.getColumnModel();
    }
    public int getSelectedRow() {
        return table.getSelectedRow();
    }
    public void deleteContact(){
        for(int i = 0; i < table.getSelectedRows().length; i++){
            address.deleteContact((Contact) table.getValueAt(table.getSelectedRows()[i], 0));
        }
    }
   
    public Contact getValueAt() {
        int selected = table.getSelectedRow();
        if (selected > -1) {
            return (Contact) table.getValueAt(table.getSelectedRow(), 0);
        } else {
            return null;
        }
    }
    public TableColumn getColumn(Object identifier){
        return table.getColumn(identifier);
    }
    public void fireTableDataChanged(){
        model.fireTableDataChanged();
    }
    /*
    public void displayLastRow(){
        int rowCount = table.getRowCount();  
        table.getSelectionModel().setSelectionInterval(rowCount-1, rowCount-1);  
        Rectangle rect = table.getCellRect(rowCount-1, 0, true); 
        table.scrollRectToVisible(rect); 
    }*/

    public void refreshData(){model.ini();}
    public class Model extends DefaultTableModel{
        public Model(){    
            columnIdentifiers.add("姓名");columnIdentifiers.add("手机");
            columnIdentifiers.add("电子邮箱");columnIdentifiers.add("固定电话");
            columnIdentifiers.add("短号");columnIdentifiers.add("生日");
            columnIdentifiers.add("工作单位");columnIdentifiers.add("家庭地址");
            columnIdentifiers.add("邮编");columnIdentifiers.add("即时通讯");
            columnIdentifiers.add("个人主页");columnIdentifiers.add("备注");
        }
        public void ini(){
            dataVector.clear();
            for(Contact c:address.getAllContacts()){
                Vector v=new Vector();
                v.add(c);v.add(c.getMobilePhone());
                v.add(c.getEmail());v.add(c.getTelephone());
                v.add(c.getCornet());v.add(c.getBirthday());
                v.add(c.getWorkUnit());v.add(c.getFamilyAddress());
                v.add(c.getPostcode());v.add(c.getInstantMessaging());
                v.add(c.getPersonalHomepage());v.add(c.getComment());//没有photo和group
                dataVector.add(v);
            }
        }/*
        public void ini1(){
            Vector<Vector> data=new Vector<>();
            for(Contact c:address.getAllContacts()){
                Vector v=new Vector();
                v.add(c);v.add(c.getMobilePhone());
                v.add(c.getEmail());v.add(c.getTelephone());
                v.add(c.getCornet());v.add(c.getBirthday());
                v.add(c.getWorkUnit());v.add(c.getFamilyAddress());
                v.add(c.getPostcode());v.add(c.getInstantMessaging());
                v.add(c.getPersonalHomepage());v.add(c.getComment());//没有photo和group
                data.add(v);
            }
            model.setDataVector(data,columnIdentifiers);
        }*/
        @Override//使里面的内容不可编辑
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }/*
    private class Renderer extends DefaultTableCellRenderer{
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,boolean isSelected, boolean hasFocus, int row, int column) {
            setBorder(null);
            if (row % 2 == 0)
                setBackground(Color.BLACK); // 设置奇数行底色
            else
                setBackground(new Color(206, 231, 255)); // 设置偶数行底色
            return super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);
        }
    }*/
}

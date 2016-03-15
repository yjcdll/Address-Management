package ui;

import address.Address;
import address.Contact;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;

public class List extends JPanel{
    private final Address address;//所有组件共用，且只new一次
    private final JList list=new JList();
    private final JScrollPane pane = new JScrollPane(list);
    //private final Table table;
    public List(Address address,Table table) {
        this.address=address;
        //this.table=table;
        this.refreshData();//列表显示所有分组
        this.add(pane);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        list.addListSelectionListener((ListSelectionEvent e) -> {
            int index=list.getSelectedIndex();
            if(index>-1&&!e.getValueIsAdjusting())
                table.setFilter(new RowFilter(){//List过滤掉不属于这个分组的联系人
                    @Override
                    public boolean include(Entry entry) {
                        Contact c=(Contact)entry.getValue(0);
                        String groupName=(String)list.getModel().getElementAt(index);
                        //点击list中的“所有联系人”，table就显示所有联系人
                        if(groupName.equals("所有联系人")) return true;
                        //System.out.println(groupName);
                        return c.isBelongsTo(groupName);//属于所选组的联系人才显示（true）
                    }
                });
        });
        //list.setPreferredSize(new Dimension(300,10));
        list.setFixedCellWidth(150);
        list.setFixedCellHeight(30);
        list.setVisibleRowCount(20);
        list.setBackground(new Color(239,254,246));
        
        //this.setPreferredSize(new Dimension(200,200));
        //this.setOpaque(false);
    }
    /**
     * 列表显示所有分组。所有组别已被更新，需要刷新一下list
     */
    public final void refreshData(){
        list.setListData(address.getGroupNames());
    }
    public void addGroup(String newGroupName){
        address.addGroup(newGroupName);
        this.refreshData();
    }
    /**
     * 删除list中被选中的组别
     */
    public void deleteSelectedGroups(){
        java.util.List<String> deleteList=list.getSelectedValuesList();
        for(String s:deleteList)
            if(!s.equals("所有联系人"))//“所有联系人”这个分组不允许删除
                address.deleteGroup(s);
        this.refreshData();
    }
    /**
     * 判断list有没有项被选中
     * @return 
     */
    public boolean isSelectionEmpty(){
        return list.isSelectionEmpty();
    }
    /**
     * 判断所有组别中是否已存在这个组
     * @param group
     * @return 
     */
    public boolean contains(String group){
        return address.getGroupNames().contains(group);
    }
    /**
     * 判断“所有联系人”分组是否被选中
     * @return 
     */
    public boolean isSelectedAllContacts(){
        return list.getSelectedValuesList().contains("所有联系人");
    }
    /*
    public void addListSeletionListener(GroupButton groupButton){
        list.addListSelectionListener((ListSelectionEvent e) -> {
            if(!e.getValueIsAdjusting())
                if(list.getSelectedIndices().length>=1)
                    groupButton.setEnabled(true);
                else groupButton.setEnabled(false);
        });
    }*/
}

package ui;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GroupButton extends JPanel{
    private final JButton addGroup=new JButton("新增分组");
    private final JButton deleteGroup=new JButton("删除分组");
    public GroupButton(List list){
        this.add(addGroup);
        this.add(deleteGroup);
        //this.setBackground(Color.WHITE);
        //this.setPreferredSize(new Dimension((int)(addGroup.getPreferredSize().width*2.4),addGroup.getPreferredSize().height));
        //新增分组
        addGroup.addActionListener((ActionEvent e) -> {
            String newGroupName=JOptionPane.showInputDialog(null,"输入分组名","新增分组",JOptionPane.INFORMATION_MESSAGE);
            //按下取消键
            if(null==newGroupName) return;
            if(newGroupName.contains(" ")||newGroupName.contains(","))
                {JOptionPane.showMessageDialog(null,"组名不允许使用空格或逗号。","使用非法字符",JOptionPane.INFORMATION_MESSAGE);return;}
            //什么都不输入，就表明没添加分组
            if("".equals(newGroupName)) return;
            if(list.contains(newGroupName))
                {JOptionPane.showMessageDialog(null,"这个分组已经存在啦！","",JOptionPane.INFORMATION_MESSAGE);return;}
            list.addGroup(newGroupName);
        });
        //删除所选分组，删除前应让用户确认
        deleteGroup.addActionListener((ActionEvent e) -> {
            if(list.isSelectionEmpty()){
                JOptionPane.showMessageDialog(null,"请选择要删除的分组。","attention",JOptionPane.INFORMATION_MESSAGE);
                return ;
            }
            if(list.isSelectedAllContacts()){
                JOptionPane.showMessageDialog(null,"\"所有联系人\"分组不允许删除。","attention",JOptionPane.INFORMATION_MESSAGE);
                return ;
            }
            //没选择任何组的话，点击按钮是没反应的
            int i=JOptionPane.showConfirmDialog(null, "确定删除所选的组么？", "删除分组", JOptionPane.YES_NO_OPTION);
            if(i==JOptionPane.YES_OPTION){
                list.deleteSelectedGroups();
            }
        });
        //list.addListSeletionListener(this);
    }
    
}

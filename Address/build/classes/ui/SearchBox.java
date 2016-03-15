//查找框，输入全拼，姓名首字母，或部分信息(regex)即可查询
package ui;

import PinYin.PinYin;
import address.Contact;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;

public class SearchBox extends JPanel{
    private final JTextField searchInfo=new JTextField(13);
    private final JButton button=new JButton("查询");
    public SearchBox(Table table){
        super();
        //this.setLayout(new GridLayout(1,2));//一行2列，左边输入框，右边按钮
        this.add(searchInfo);
        this.add(button);
        button.addActionListener((ActionEvent e) -> {
            //此代码在API的RowFilter的orFilter拿的
            ArrayList<RowFilter<Object, Object>> filters = new ArrayList<>(2);
            filters.add(RowFilter.regexFilter(searchInfo.getText()));
            filters.add(new RowFilter(){//SearchBox查询
                    @Override
                    public boolean include(Entry entry) {
                        Contact c=(Contact)entry.getValue(0);
                        String name=c.getName();
                        //按姓名首字母查找
                        Vector<String> firstSpell = PinYin.converterToFirstSpell(name);
                        for(String s:firstSpell)
                            if(s.equals(searchInfo.getText())) return true;
                        //for(String s:firstSpell) System.out.println(s);
                        //按姓名全拼查找
                        Vector<String> quanPin = PinYin.converterToQuanPin(name);
                        for(String s:quanPin)
                            if(s.equals(searchInfo.getText())) return true;                        
                        return false;
                    }
                });
            RowFilter<Object, Object> filter = RowFilter.orFilter(filters);
            table.setFilter(filter);
        });
    }
}

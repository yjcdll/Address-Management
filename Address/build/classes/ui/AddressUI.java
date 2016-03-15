package ui;
//背景颜色http://blog.csdn.net/lishmoshou/article/details/8100120
import address.Address;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;

public class AddressUI extends JFrame{
    private final Address address=new Address();//所有组件共用，且只new一次
    private final Table table=new Table(address);
    private final ContactButton contactButton=new ContactButton(table,address);
    private final Check check=new Check(table);
    private final List list=new List(address,table);
    private final GroupButton groupButton=new GroupButton(list);
    private final FileProcess fileProcessButton=new FileProcess(address,table,list);
    private final SearchBox searchBox=new SearchBox(table);
    
    public AddressUI(){
        super();       
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(null, "确定要退出吗？", "退出", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    int n = JOptionPane.showConfirmDialog(null, "需要保存所做的修改吗？", "保存", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) 
                        address.writeCSV();
                    System.exit(0);
                }
            }
        });

        this.add(list,BorderLayout.WEST);
        
        JPanel buttonPanel=new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(groupButton);
        buttonPanel.add(contactButton);
        buttonPanel.add(fileProcessButton);
        buttonPanel.add(searchBox);
        buttonPanel.add(check);
        JPanel eastPanel=new JPanel();
        //GridBagLayout layout = new GridBagLayout();
        //eastPanel.setLayout(layout);
        this.add(eastPanel);
        eastPanel.setLayout(new BorderLayout());
        eastPanel.add(buttonPanel,BorderLayout.NORTH);
        eastPanel.add(table);
        this.pack();
        //this.setBackground(Color.GREEN);
        //this.getContentPane().setBackground(Color.YELLOW);
        //this.getContentPane().setVisible(true);//如果改为false那么就变成了blue。
        this.setLocationRelativeTo(null);
        this.setTitle("通讯录");
        this.setVisible(true);
    }
    public static void main(String[] args) {
        //设置整个程序的LookAndFeel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            JOptionPane.showMessageDialog(null, "LookAndFeel设置失败!", "Error", JOptionPane.ERROR_MESSAGE);
        }
        AddressUI ui=new AddressUI();
    }
            
}

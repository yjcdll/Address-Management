package ui;

import address.Address;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileProcess extends JPanel{
    private final JButton fileProcessButton=new JButton("导入\\导出");
    private final JPopupMenu fileMenu=new JPopupMenu();
    private final JMenuItem readCSV=new JMenuItem("导入CSV文件");
    private final JMenuItem writeCSV=new JMenuItem("导出CSV文件");
    private final JMenuItem readVCF=new JMenuItem("导入VCF文件");
    private final JMenuItem writeVCF=new JMenuItem("导出VCF文件");
    public FileProcess(Address address,Table table,List list){
        this.add(fileProcessButton);
        fileMenu.add(readCSV);
        fileMenu.add(writeCSV);
        fileMenu.addSeparator();
        fileMenu.add(readVCF);
        fileMenu.add(writeVCF);
        fileProcessButton.setComponentPopupMenu(fileMenu);
        fileProcessButton.addActionListener((ActionEvent e) -> {
            //弹出的fileMenu要在此按钮的右下角
            fileMenu.show(fileProcessButton,fileProcessButton.getSize().width,fileProcessButton.getSize().height);
        });
        readCSV.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser=new JFileChooser(System.getProperty("user.dir"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("csv文件","csv");
            fileChooser.setFileFilter(filter);
            if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                File sourceFile=fileChooser.getSelectedFile();
                address.readCSV(sourceFile);
                table.refreshData();
                //table.repaint();
                table.fireTableDataChanged();
                list.repaint();
            }
        });
        writeCSV.addActionListener((ActionEvent e) -> {
            address.writeCSV();
        });
        readVCF.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser=new JFileChooser(System.getProperty("user.dir"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("vcf文件","vcf");
            fileChooser.setFileFilter(filter);
            if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                File sourceFile=fileChooser.getSelectedFile();
                address.readVCF(sourceFile);
                table.refreshData();
                //table.repaint();
                table.fireTableDataChanged();
                list.repaint();
            }
        });
        writeVCF.addActionListener((ActionEvent e) -> {
            address.writeVCF();
        });
    }
}

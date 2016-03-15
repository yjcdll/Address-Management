package ui;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class StringDeal extends PlainDocument{
    @Override
     public void insertString(int offs, String str, AttributeSet a)throws BadLocationException{     
      if (str == null)
       {
        return;
       }
      char[] upper = str.toCharArray();
      for (int i = 0; i < upper.length; i++)
       {
        if (upper[i] == ' '|| upper[i] == ','){
         str = str.substring(0, i);
         Toolkit.getDefaultToolkit().beep();
        }       
       }
      super.insertString(offs, str , a);
     }
}

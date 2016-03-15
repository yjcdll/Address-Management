package PinYin;
//http://my.oschina.net/u/853107/blog/166790?p={{currentPage-1}}
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;
 
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
 
public class PinYin {
 
    /**
     * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（重当参:cdc,zds,cds,zdc）
     * 
     * @param chines
     *            汉字
     * @return 拼音
     */
    public static Vector<String> converterToFirstSpell(String chines) {
        StringBuilder pinyinName = new StringBuilder();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    // 取得当前汉字的所有全拼
                    String[] strs = PinyinHelper.toHanyuPinyinStringArray(
                            nameChar[i], defaultFormat);
                    if (strs != null) {
                        for (int j = 0; j < strs.length; j++) {
                            // 取首字母
                            pinyinName.append(strs[j].charAt(0));
                            if (j != strs.length - 1) {
                                pinyinName.append(",");
                            }
                        }
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                }
            } else {
                pinyinName.append(nameChar[i]);
            }
            pinyinName.append(" ");
        }
        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
    }
    
     /**
     * 汉字转换位汉语拼音首字母，英文字符不变，特殊字符丢失 支持多音字，生成方式如（重当参:cdc,zds,cds,zdc）
     * 
     * @param chines
     *            汉字
     * @return 拼音
     */
    public static Vector<String> converterToQuanPin(String chines) {
        StringBuilder pinyinName = new StringBuilder();
        char[] nameChar = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < nameChar.length; i++) {
            if (nameChar[i] > 128) {
                try {
                    // 取得当前汉字的所有全拼
                    String[] strs = PinyinHelper.toHanyuPinyinStringArray(
                            nameChar[i], defaultFormat);
                    if (strs != null) {
                        for (int j = 0; j < strs.length; j++) {
                            // 取首字母
                            pinyinName.append(strs[j]);
                            if (j != strs.length - 1) {
                                pinyinName.append(",");
                            }
                        }
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                }
            } else {
                pinyinName.append(nameChar[i]);
            }
            pinyinName.append(" ");
        }
        return parseTheChineseByObject(discountTheChinese(pinyinName.toString()));
    }
    
    /**
     * 去除多音字重复数据
     * 
     * @param theStr
     * @return
     */
    private static List<Map<String, Integer>> discountTheChinese(String theStr) {
        // 去除重复拼音后的拼音列表
        List<Map<String, Integer>> mapList = new ArrayList<>();
        // 用于处理每个字的多音字，去掉重复
        Map<String, Integer> onlyOne = null;
        String[] firsts = theStr.split(" ");
        // 读出每个汉字的拼音
        for (String str : firsts) {
            onlyOne = new Hashtable<String, Integer>();
            String[] china = str.split(",");
            // 多音字处理
            for (String s : china) {
                Integer count = onlyOne.get(s);
                if (count == null) {
                    onlyOne.put(s, 1);
                } else {
                    onlyOne.remove(s);
                    count++;
                    onlyOne.put(s, count);
                }
            }
            mapList.add(onlyOne);
        }
        return mapList;
    }
 
    /**
     * 解析并组合拼音，对象合并方案(推荐使用)
     * 
     * @return
     */
    private static Vector<String> parseTheChineseByObject(
            List<Map<String, Integer>> list) {
        Map<String, Integer> first = null; // 用于统计每一次,集合组合数据
        // 遍历每一组集合
        for (Map<String, Integer> list1 : list) {
            // 每一组集合与上一次组合的Map
            Map<String, Integer> temp = new Hashtable<>();
            // 第一次循环，first为空
            if (first != null) {
                // 取出上次组合与此次集合的字符，并保存
                for (String s : first.keySet()) {
                    for (String s1 : list1.keySet()) {
                        String str = s + s1;
                        temp.put(str, 1);
                    }
                }
                // 清理上一次组合数据
                if (temp != null && temp.size() > 0) {
                    first.clear();
                }
            } else {
                for (String s : list1.keySet()) {
                    String str = s;
                    temp.put(str, 1);
                }
            }
            // 保存组合数据以便下次循环使用
            if (temp != null && temp.size() > 0) {
                first = temp;
            }
        }
        Vector<String> returnStr = new Vector<>();
        if (first != null) {
            // 遍历取出组合字符串
            for (String str : first.keySet()) {
                returnStr .add(str);
            }
        }/*
        if (returnStr.size() > 0) {
            returnStr = returnStr.substring(0, returnStr.size() - 1);
        }*/
        return returnStr;
    }
 /*
    public static void main(String[] args) {
        String str = "靓和曾好";
        for(String s:converterToFirstSpell(str))
            System.out.println(s);
        Vector <String>v=converterToQuanPin(str);
        //v.sort(null);
        for(String s:v)
            System.out.println(s);
    }*/
}
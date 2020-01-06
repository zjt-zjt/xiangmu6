package com.lanou.AddressBook;



import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * ����ת����ƴ������
 *
 * @author ����
 */

public class Pinyin4j {

    HanyuPinyinOutputFormat format = null;

    public static enum Type {
        UPPERCASE,              //ȫ����д
        LOWERCASE,              //ȫ��Сд
        FIRSTUPPER            //����ĸ��д
    }

    public Pinyin4j() {
        format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    }

    /**
     * ת��ȫ����д
     *
     * @param str �ַ���
     * @return strΪ�ú�԰ ,return��ȡ������YHY
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public String toPinYinUppercase(String str) throws BadHanyuPinyinOutputFormatCombination {
        return toPinYin(str, "", Type.UPPERCASE);
    }

    /**
     * ת��ȫ����д
     *
     * @param str   �ַ���
     * @param spera ת����ĸ����ӵ��ַ���,�������ҪΪ""
     * @return strΪ�ú�԰ ,speraΪ** return��ȡ������Y**H**Y
     * @throws BadHanyuPinyinOutputFormatCombination
     */
    public String toPinYinUppercase(String str, String spera) throws BadHanyuPinyinOutputFormatCombination {
        return toPinYin(str, spera, Type.UPPERCASE);
    }

    /**
     * ת��ȫ��Сд
     *
     * @param str �ַ���
     * @throws BadHanyuPinyinOutputFormatCombination
     * @return strΪ�ú�԰ ,return��ȡ������yhy
     */
    public String toPinYinLowercase(String str) throws BadHanyuPinyinOutputFormatCombination {
        return toPinYin(str, "", Type.LOWERCASE);
    }

    /**
     * ת��ȫ��Сд
     *
     * @param str   �ַ���
     * @param spera ת����ĸ����ӵ��ַ���,�������ҪΪ""
     * @throws BadHanyuPinyinOutputFormatCombination
     * @return strΪ�ú�԰ ,speraΪ** return��ȡ������y**h**y
     */
    public String toPinYinLowercase(String str, String spera) throws BadHanyuPinyinOutputFormatCombination {
        return toPinYin(str, spera, Type.LOWERCASE);
    }

    /**
     * ��ȡƴ������ĸ(��д)
     *
     * @param str �ַ���
     * @return strΪ�ú�԰ ,return��ȡ������Y
     * @throws BadHanyuPinyinOutputFormatCombination �쳣��Ϣ
     */
    public String toPinYinUppercaseInitials(String str) throws BadHanyuPinyinOutputFormatCombination {
        String initials = null;
        String py = toPinYinUppercase(str);
        if (py.length() > 1) {
            initials = py.substring(0, 1);
        }
        if (py.length() <= 1) {
            initials = py;
        }
        return initials.trim();
    }

    /**
     * ��ȡƴ������ĸ(Сд)
     *
     * @param str �ַ���
     * @return strΪ�ú�԰ ,return��ȡ������y
     * @throws BadHanyuPinyinOutputFormatCombination �쳣��Ϣ
     */
    public String toPinYinLowercaseInitials(String str) throws BadHanyuPinyinOutputFormatCombination {
        String initials = null;
        String py = toPinYinLowercase(str);
        if (py.length() > 1) {
            initials = py.substring(0, 1);
        }
        if (py.length() <= 1) {
            initials = py;
        }
        return initials.trim();
    }
    

    /**
     * ��strת����ƴ����������Ǻ��ֻ���û�ж�Ӧ��ƴ��������ת��
     *
     * @param str   �ַ���
     * @param spera Ĭ��,��Ϊ""
     * @param type  ת����ʽ
     * @return ����ת����ʽת�����ַ���
     * @throws BadHanyuPinyinOutputFormatCombination �쳣��Ϣ
     */
    public String toPinYin(String str, String spera, Type type) throws BadHanyuPinyinOutputFormatCombination {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        if (type == Type.UPPERCASE) {
            format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        } else {
            format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        }
        String py = "";
        String temp = "";
        String[] t;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if ((int) c <= 128) {
                py += c;
            } else {
                t = PinyinHelper.toHanyuPinyinStringArray(c, format);
                if (t == null) {
                    py += c;
                } else {
                    temp = t[0];
                    if (type == Type.FIRSTUPPER) {
                        temp = t[0].toUpperCase().charAt(0) + temp.substring(1);
                    }
                    if (temp.length() >= 1) {
                        temp = temp.substring(0, 1);
                    }
                    py += temp + (i == str.length() - 1 ? "" : spera);
                }
            }
        }
        return py.trim();
    }
}



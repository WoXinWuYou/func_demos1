package com.lmj.utils.datautil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * MD5 加密工具类
 *
 * @author jinqiang
 * @version 1.0
 * @since 2016.10.16
 */
public class MD5Utils {

    static char hexdigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8',

            '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String md5Encoding(String origin) throws UnsupportedEncodingException {

        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }

        byte[] byteArray = origin.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);

        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }

        return hexValue.toString();
    }

    public static String doubleMd5Encoding(String origin) throws UnsupportedEncodingException {
        String first = md5Encoding(origin);
        first = first + "This is Login password key";
        String second = md5Encoding(first);
        return second;
    }

    public static String getMD5(File file) {

        FileInputStream fis = null;

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");

            fis = new FileInputStream(file);

            byte[] buffer = new byte[2048];

            int length = -1;

//			long s = System.currentTimeMillis();

            while ((length = fis.read(buffer)) != -1) {

                md.update(buffer, 0, length);

            }

            byte[] b = md.digest();

            return byteToHexString(b);

            // 16位加密

            // return buf.toString().substring(8, 24);

        } catch (Exception ex) {

            ex.printStackTrace();

            return null;

        } finally {

            try {
                if (fis != null) {
                    fis.close();
                }

            } catch (IOException ex) {

                ex.printStackTrace();

            }

        }

    }

    /**
     * 把byte[]数组转换成十六进制字符串表示形式
     *
     * @param tmp 要转换的byte[]
     * @return 十六进制字符串表示形式
     */

    private static String byteToHexString(byte[] tmp) {

        String s;

        // 用字节表示就是 16 个字节

        char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，

        // 所以表示成 16 进制需要 32 个字符

        int k = 0; // 表示转换结果中对应的字符位置

        for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节

            // 转换成 16 进制字符的转换

            byte byte0 = tmp[i]; // 取第 i 个字节

            str[k++] = hexdigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,

            // >>> 为逻辑右移，将符号位一起右移

            str[k++] = hexdigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换

        }

        s = new String(str); // 换后的结果转换为字符串

        return s;

    }

	public static void xxxmain(String arg[])throws UnsupportedEncodingException {
		System.out.println(md5Encoding("123456"));

	}

}

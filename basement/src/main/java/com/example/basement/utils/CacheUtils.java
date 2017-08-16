package com.example.basement.utils;

import android.database.Cursor;

import com.example.basement.utils.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;

/**
 * Created by jacky-chen on 2016/6/21.
 * 创建文件路径
 */
public class CacheUtils {
    private static final String FIRST_LINE = "useing";

    /**
     * 获取缓存 ,key 是url,value是Json串
     *
     * @param filename 文件名
     * @return 文本内容
     */
    public static String loadLocal(String filename) {
        File dir = FileUtils.getCacheDir();// 获取缓存所在的文件夹
        String filenameMd = DigestUtils.md5Hex(filename);
        File file = new File(dir, filenameMd);

        if (!file.exists()) {
            return null;
        }
        StringWriter sw = null;
        BufferedReader br = null;
        try {
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            String firstStr = br.readLine();
            if (!firstStr.contains(FIRST_LINE)) {// 设置了过期时间
                long outOfDate = Long.parseLong(firstStr);
                if (System.currentTimeMillis() > outOfDate) {
                    return null;
                }
            }
            sw = new StringWriter();
            String str = null;
            while ((str = br.readLine()) != null) {
                sw.write(str);
            }
            return sw.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(sw);
            closeQuietly(br);
        }
        return null;
    }

    /**
     * 保存文本文件（如json串）到本地文件夹中
     *
     * @param filename  用md5加密为文件名
     * @param json      文本内容
     * @param outofdate 过期时间
     */
    public static void saveLoacl(String filename, String json, long outofdate) {
        File dir = FileUtils.getCacheDir();// 获取缓存所在的文件夹
        String filenameMd = DigestUtils.md5Hex(filename);
        File file = new File(dir, filenameMd);

        BufferedWriter bw = null;
        try {
            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            // 第一行写过期时间
            if (outofdate > 0) {
                bw.write(System.currentTimeMillis() + 1000 * outofdate + "");// 单位为秒
                bw.newLine();
            } else {
                bw.write(FIRST_LINE);// 文件不过期
                bw.newLine();
            }
            bw.write(json);// 把整个json文件保存起来
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeQuietly(bw);
        }
    }

    /**
     * 保存文本文件（如json串）到本地文件夹中
     *
     * @param filename 文件名
     * @param json     文本内容
     */
    public static void saveLoacl(String filename, String json) {
        saveLoacl(filename, json, 0);
    }

  /*  //md5加密方法
    public static String md5(String password) {

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把没一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                // System.out.println(str);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }*/

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable var2) {
            }
        }

    }

    public static void closeQuietly(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Throwable var2) {
            }
        }

    }
}

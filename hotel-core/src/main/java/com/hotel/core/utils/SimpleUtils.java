package com.hotel.core.utils;


import com.hotel.common.utils.Utils;
import com.hotel.core.exception.GlobalException;
import com.hotel.core.model.ResultInfo;

import java.io.*;
import java.util.Collection;

/**
 * 描述:
 * 作者:cc
 * 日期:18-1-11
 */
public class SimpleUtils {


    private SimpleUtils() {

    }

    public static String strJoin(String[] source, String split) {
        if (source == null || source.length == 0) {
            return "";
        }
        String defaultSplit = ",";
        if (split != null && (!"".equals(split.trim()))) {
            defaultSplit = split;
        }
        StringBuilder resultBuilder = new StringBuilder();
        for (String str : source) {
            resultBuilder.append(str).append(defaultSplit);
        }
        String result = "";
        if (!"".equals(resultBuilder.toString())) {
            result = resultBuilder.substring(0, resultBuilder.length() - 1);
        }
        return result;
    }

    public static String collectionJoin(Collection<String> collection, String split) {
        if (collection.isEmpty()) {
            return "";
        }
        String[] strArray = collection.toArray(new String[collection.size()]);
        return strJoin(strArray, split);
    }


    /**
     * 匹配luhn算法：可用于检测银行卡卡号
     *
     * @param cardNo 银行卡号
     * @return 结果
     */
    public static boolean matchLuhn(String cardNo) {
        int[] cardNoArr = new int[cardNo.length()];
        for (int i = 0; i < cardNo.length(); i++) {
            cardNoArr[i] = Integer.valueOf(String.valueOf(cardNo.charAt(i)));
        }
        for (int i = cardNoArr.length - 2; i >= 0; i -= 2) {
            cardNoArr[i] <<= 1;
            cardNoArr[i] = cardNoArr[i] / 10 + cardNoArr[i] % 10;
        }
        int sum = 0;
        for (int i = 0; i < cardNoArr.length; i++) {
            sum += cardNoArr[i];
        }
        return sum % 10 == 0;
    }

    public static <T> int arraySearch(T[] source, T target) {
        if (source == null || source.length == 0 || target == null) {
            return -1;
        }
        for (int i = 0; i < source.length; i++) {
            if (target.equals(source[i])) {
                return i;
            }
        }
        return -1;
    }


    /**
     * 文件路劲组合
     *
     * @param paths 需要组装的路径
     * @return 组装后的结果
     */
    public static String pathJoin(String... paths) {
        String windowSeparate = "\\";
        String commonSeparate = "/";
        if (paths == null || paths.length == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < paths.length; i++) {
            String path = paths[i];
            //忽略空字符串
            if (path == null || "".equals(path)) {
                continue;
            }
            path = path.replace(windowSeparate, commonSeparate);
            path = path.replaceAll("/+", commonSeparate);

            //去掉头部的"/"
            if (path.startsWith(commonSeparate) && (i > 0)) {
                path = path.substring(1, path.length());
            }
            //去掉尾部的"/"
            if (path.endsWith(commonSeparate) && (i < paths.length - 1)) {
                path = path.substring(0, path.length() - 1);
            }
            builder.append(path);
            //非最后的尾部就添加"/"
            if (i < paths.length - 1) {
                builder.append(commonSeparate);
            }
        }
        return builder.toString();
    }

    public static void assertNotEmpty(Object obj, String errorMessage) {
        trueAndThrows(Utils.isEmpty(obj), errorMessage);
    }

    public static void trueAndThrows(boolean express, String errorMessage) {
        if (express) {
            String defaultMessage = "服务器内部错误";
            if (Utils.isNotEmpty(errorMessage)) {
                defaultMessage = errorMessage;
            }
            throw new GlobalException(defaultMessage);
        }
    }

    public static <T> ResultInfo success(T data) {
        ResultInfo resultInfo = ResultInfo.instance();
        if (Utils.isNotEmpty(data)) {
            resultInfo.setData(data);
        }
        return resultInfo;
    }

    public static ResultInfo success() {
        return success(null);
    }

    public static ResultInfo fail(Object data,String failMsg){
        ResultInfo resultInfo=ResultInfo.instance();
        resultInfo.fail();
        resultInfo.setStatusCode(ResultInfo.ERROR);
        if(Utils.isNotEmpty(data)){
            resultInfo.setData(data);
        }

        if(Utils.isNotEmpty(failMsg)){
            resultInfo.setMsg(failMsg);
        }
        return resultInfo;
    }


    public static void inWrite2Out(InputStream inputStream, OutputStream outputStream, boolean closeStream) {
        if (Utils.isEmpty(inputStream) || Utils.isEmpty(outputStream)) {
            throw new IllegalArgumentException("inputStream or outputStream can't be null");
        }
        byte[] buffer = new byte[4096];
        int length;
        try {
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
        } catch (IOException e) {
            closeStream(outputStream, inputStream);
        } finally {
            if (closeStream) {
                closeStream(outputStream, inputStream);
            }
        }

    }


    public static void closeStream(Closeable... closeables) {
        for (Closeable closeable : closeables) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void tryDeleteFile(File file) {
        if (file == null || !file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            //删除目录
            deleteFileDir(file);
            return;
        }
        //删除文件
        deteteFile(file);
    }

    public static void deteteFile(File file) {
        if (file == null || (!file.isFile())) {
            return;
        }
        file.delete();
    }

    public static void deleteFileDir(File file) {
        if (file == null || (!file.isDirectory())) {
            return;
        }
        File[] subFileList = file.listFiles();
        for (File tempFile : subFileList) {
            if (tempFile.isDirectory()) {
                deleteFileDir(tempFile);
                continue;
            }
            //删除文件
            deteteFile(tempFile);
        }
        //删除父级文件
        file.delete();

    }

    public static void tryDeleteFile(String path) {
        if (Utils.isEmpty(path)) {
            return;
        }
        tryDeleteFile(new File(path));
    }

    public static void main(String[] args) {

    }


}

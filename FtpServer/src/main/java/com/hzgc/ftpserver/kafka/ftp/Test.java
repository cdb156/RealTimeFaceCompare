package com.hzgc.ftpserver.kafka.ftp;

import com.hzgc.dubbo.dynamicrepo.FileType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

    private static final Logger LOG = LoggerFactory.getLogger(Test.class);
    private static String fileName = "/17130NCY0HZ0d004-000/16/00/2017_05_23_16_00_15_5704_2.jpg";

    public static String transformNameToKey(String fileName) {
        StringBuilder key = new StringBuilder();

        if (fileName != null && fileName.length() > 0) {
            String ipcID = fileName.substring(1, fileName.indexOf("/", 2));
            String tempKey = fileName.substring(fileName.lastIndexOf("/"), fileName.lastIndexOf("_"))
                    .replace("/", "");
            String prefixName = tempKey.substring(tempKey.lastIndexOf("_") + 1, tempKey.length());
            String timeName = tempKey.substring(2, tempKey.lastIndexOf("_")).replace("_", "");

            StringBuffer prefixNameKey = new StringBuffer();
            prefixNameKey = prefixNameKey.append(prefixName).reverse();
            if (prefixName.length() < 10) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < 10 - prefixName.length(); i++) {
                    stringBuilder.insert(0, "0");
                }
                prefixNameKey.insert(0, stringBuilder);
            }
            key.append(ipcID).append("_").append(timeName).append("_").append(prefixNameKey).append("_00");
        } else {
            key.append(fileName);
        }
        return key.toString();
    }

    /**
     * 通过rowKey解析到文件保存的绝对路径
     *
     * @param rowKey rowKey
     * @param type 文件类型
     * @return 绝对路径
     * rowKey="3B0383FPAG00883_170926192916_0000000524_01"
     */
    public static String key2absolutePath(String rowKey, FileType type) {
        String ipcId = rowKey.substring(0, rowKey.indexOf("_"));
        String timeStr = rowKey.substring(rowKey.indexOf("_") + 1, rowKey.length());
        String year = timeStr.substring(0, 2);
        String month = timeStr.substring(2, 4);
        String day = timeStr.substring(4, 6);
        String hour = timeStr.substring(6, 8);
        String minute = timeStr.substring(8, 10);
        //String second = timeStr.substring(10, 12);

        StringBuilder fileName = new StringBuilder();
        fileName = fileName.append("/opt/ftpserver/").append(ipcId).
                append("/20").append(year).append("/").append(month).append("/").append(day).
                append("/").append(hour).append("/").append(minute).append("/");

        int numType = Integer.parseInt(rowKey.substring(rowKey.lastIndexOf("_") + 1, rowKey.length()));

        if (type == FileType.PICTURE) {
            if (numType == 0) {
                fileName = fileName.append(rowKey).append(".jpg");
            } else if (numType > 0) {
                rowKey = rowKey.substring(0, rowKey.lastIndexOf("_") + 1) + "00";
                fileName = fileName.append(rowKey).append(".jpg");
            } else {
                LOG.warn("rowKey format error :" + rowKey);
            }
        } else if (type == FileType.FACE) {
            if (numType == 0) {
                LOG.info("picture rowKey not analysis face filePath !");
            } else if (numType > 0) {
                fileName = fileName.append(rowKey).append(".jpg");
            } else {
                LOG.warn("rowKey format error :" + rowKey);
            }
        } else if (type == FileType.JSON) {
            if (numType == 0) {
                fileName = fileName.append(rowKey).append(".json");
            } else if (numType > 0) {
                rowKey = rowKey.substring(0, rowKey.lastIndexOf("_") + 1) + "00";
                fileName = fileName.append(rowKey).append(".json");
            } else {
                LOG.warn("rowKey format error :" + rowKey);
            }
        }
        return fileName.toString();
    }

    /**
     * 通过rowKey解析文件保存相对路径
     *
     * @param rowKey rowKey
     * @return 相对路径
     */
    public static String key2relativePath(String rowKey) {
        String ipcId = rowKey.substring(0, rowKey.indexOf("_"));
        String timeStr = rowKey.substring(rowKey.indexOf("_") + 1, rowKey.length());
        String year = timeStr.substring(0, 2);
        String month = timeStr.substring(2, 4);
        String day = timeStr.substring(4, 6);
        String hour = timeStr.substring(6, 8);
        String minute = timeStr.substring(8, 10);
        //String second = timeStr.substring(10, 12);

        StringBuilder filePath = new StringBuilder();
        filePath = filePath.append("/opt/ftpserver/").append(ipcId).
                append("/20").append(year).append("/").append(month).append("/").append(day).
                append("/").append(hour).append("/").append(minute);
        return filePath.toString();
    }

    /**
     * 通过rowKey解析文件名称
     *
     * @param rowKey rowKey
     * @param type   文件类型
     * @return 文件名称
     */
    public static String key2fileName(String rowKey, FileType type) {
        StringBuilder fileName = new StringBuilder();

        int numType = Integer.parseInt(rowKey.substring(rowKey.lastIndexOf("_") + 1, rowKey.length()));

        if (type == FileType.PICTURE) {
            if (numType == 0) {
                fileName = fileName.append(rowKey).append(".jpg");
            } else if (numType > 0) {
                rowKey = rowKey.substring(0, rowKey.lastIndexOf("_") + 1) + "00";
                fileName = fileName.append(rowKey).append(".jpg");
            } else {
                LOG.warn("rowKey format error : " + rowKey);
            }
        } else if (type == FileType.FACE) {
            if (numType == 0) {
                LOG.info("picture rowKey not analysis face fileName !");
            } else if (numType > 0) {
                fileName = fileName.append(rowKey).append(".jpg");
            } else {
                LOG.warn("rowKey format error :" + rowKey);
            }
        } else if (type == FileType.JSON) {
            if (numType == 0) {
                fileName = fileName.append(rowKey).append(".json");
            } else if (numType > 0) {
                rowKey = rowKey.substring(0, rowKey.lastIndexOf("_") + 1) + "00";
                fileName = fileName.append(rowKey).append(".json");
            } else {
                LOG.warn("rowKey format error : " + rowKey);
            }
        } else {
            LOG.warn("method param is error.");
        }
        return fileName.toString();
    }

    private static String key = "3B0383FPAG00883_170926192916_0000000524_00";

    public static void main(String[] args) {
        System.out.println("rowkey        : " + key);
        String picPath = key2absolutePath(key, FileType.PICTURE);
        System.out.println("picture  path : " + picPath);
        String facePath = key2absolutePath(key, FileType.FACE);
        System.out.println("face     Path : " + facePath);
        String jsonPath = key2absolutePath(key, FileType.JSON);
        System.out.println("json     Path : " + jsonPath);
        String relativePath = key2relativePath(key);
        System.out.println("relative Path : " + relativePath);
        String picName = key2fileName(key, FileType.PICTURE);
        System.out.println("picture  Name : " + picName);
        String faceName = key2fileName(key, FileType.FACE);
        System.out.println("face     Name : " + faceName);
        String jsonName = key2fileName(key, FileType.JSON);
        System.out.println("json     Name : " + jsonName);
    }

}

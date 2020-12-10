package com.autuan.common.utils.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 * @className: FTPUtil
 * @author: sen.zhou
 * @description:
 * @date: 2020/6/21 16:18
 * @version: V2.0.0
 * @company:上海奥若拉信息科技集团有限公司
 **/
public class FTPUtil {
    private static Map<String, String> apkDirectory = null;
    private static String FTP_IP = "promote.yupai.net";
    private static String FTP_PORT = "22";
    private static String FTP_USER = "promote_yupai_net";
    private static String FTP_PASSWORD = "4HeEkE4zHcy4k2Sd";

    public FTPUtil() {
    }

    public static void uoloadFile(InputStream  inputStream,String fileName) {
        //创建客户端对象
        FTPClient ftp = new FTPClient();
        try {
//            if (null==apkDirectory){
//                apkDirectory= ApkDirectoryUtil.getApkDirectory();
//            }
            Integer port=Integer.parseInt(FTP_PORT);
            //连接ftp服务器
            ftp.connect(FTP_IP, port);
            //登录
            ftp.login(FTP_USER, FTP_PASSWORD);
            //设置上传路径简化为系统编号
            //String path =apkDirectory.get(systemCode);
            String path ="/ROOT/file/";
            //检查上传路径是否存在 如果不存在返回false
            boolean flag = ftp.changeWorkingDirectory(path);
            if (!flag) {
                //创建上传的路径 该方法只能创建一级目录，在这里如果/home/ftpuser存在则可创建image
                ftp.makeDirectory(path);
            }
            //指定上传路径
            ftp.changeWorkingDirectory(path);
            //指定上传文件的类型 二进制文件
            ftp.setFileType(FTP.BINARY_FILE_TYPE);

            //第一个参数是文件名
            ftp.storeFile(fileName, inputStream);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭文件流
                inputStream.close();
                //退出
                ftp.logout();
                //断开连接
                ftp.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
package com.zhibo.reporting.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesConfig {

	/** 
     * 根据KEY，读取文件对应的值 
     * @param filePath 文件路径，即文件所在包的路径，例如：java/util/config.properties 
     * @param key 键 
     * @return key对应的值 
     */  
    public static String readData(String key) {  
        //获取绝对路径  
        String filePath = PropertiesConfig.class.getResource("/" + "crmConfig.properties").toString();  
        //截掉路径的”file:“前缀  
        filePath = filePath.substring(6);  
        Properties props = new Properties();  
        try {  
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));  
            props.load(in);  
            in.close();  
            String value = props.getProperty(key);  
            return value;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
    /** 
     * 修改或添加键值对 如果key存在，修改, 反之，添加。 
     * @param filePath 文件路径，即文件所在包的路径，例如：java/util/config.properties 
     * @param key 键 
     * @param value 键对应的值 
     */  
    public static void writeData(String key, String value) {
        //获取绝对路径  
        //String filePath = PropertiesConfig.class.getResource("/" + "crmConfig.properties").toString(); 
        String filePath = "D:\\images.properties";
    	//截掉路径的”file:/“前缀  
       // filePath = filePath.substring(6);  
        Properties prop = new Properties();  
        try {  
            File file = new File(filePath);  
            if (!file.exists())  
                file.createNewFile();  
            InputStream fis = new FileInputStream(file);  
            prop.load(fis);  
            //一定要在修改值之前关闭fis  
            fis.close();  
            OutputStream fos = new FileOutputStream(filePath);  
            prop.setProperty(key, value);  
            //保存，并加入注释  
            prop.store(fos, "Update '" + key + "' value");  
            fos.close();  
        } catch (IOException e) {
            System.err.println("Visit " + filePath + " for updating " + value + " value error");  
        }  
    }  
    
    public static void writeData4IP(String key, String value) {  
        //获取绝对路径  
        String filePath = "D:\\blackIp.properties"; 
        //截掉路径的”file:/“前缀  
//        filePath = filePath.substring(6);  
        Properties prop = new Properties();  
        try {  
            File file = new File(filePath);  
            if (!file.exists())  
                file.createNewFile();  
            InputStream fis = new FileInputStream(file);  
            prop.load(fis);  
            //一定要在修改值之前关闭fis  
            fis.close();  
            OutputStream fos = new FileOutputStream(filePath);  
            prop.setProperty(key, value);  
            //保存，并加入注释  
            prop.store(fos, "Update '" + key + "' value");  
            fos.close();  
        } catch (IOException e) {  
            System.err.println("Visit " + filePath + " for updating " + value + " value error");  
        }  
    }  
    public static String readData4IP(String key) {  
        //获取绝对路径  
        String filePath = PropertiesConfig.class.getResource("/" + "blackIp.properties").toString();  
        //截掉路径的”file:“前缀  
        filePath = filePath.substring(6);  
        Properties props = new Properties();  
        try {  
            InputStream in = new BufferedInputStream(new FileInputStream(filePath));  
            props.load(in);  
            in.close();  
            String value = props.getProperty(key);  
            return value;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }  
      
    public static void main(String[] args) {  
    	//获取绝对路径  
        String filePath = "D:\\apache-tomcat-7.0.572\\webapps\\WeiXin\\WEB-INF\\classes\\autoreply.properties";
        //截掉路径的”file:“前缀  
        Properties prop = new Properties();  
        try {  
            File file = new File(filePath);  
            if (!file.exists())  
                file.createNewFile();  
            InputStream fis = new FileInputStream(file);  
            prop.load(fis);  
            //一定要在修改值之前关闭fis  
            fis.close();  
            OutputStream fos = new FileOutputStream(filePath);  
            prop.remove("rule.total.ids");  
            //保存，并加入注释  
            prop.store(fos, "Update rule.total.ids value");  
            fos.close();  
        }catch (Exception e) {
			// TODO: handle exception
		}
    } 
    
    public static String readPro(String filepath, String key){
    	Properties props = new Properties();
    	try {  
            InputStream in = new BufferedInputStream(new FileInputStream(filepath));  
            props.load(in);  
            in.close();  
            String value = props.getProperty(key);  
            return value;  
        } catch (Exception e) {  
            e.printStackTrace();  
            return null;  
        }  
    }
}

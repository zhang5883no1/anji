package com.zhibo.mainTain.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Properties;

import com.zhibo.mainTain.dto.QQBasicInfoDto;

public class PropertiesUtilss {
	 	    
	    // read properties and deal Chinese problem
	    public static String readPropertiesValue(String filename, String key) {
	        Properties properties = new PropertiesUtils();
	        String value = "";
	        try {
	            InputStream inputStream = new FileInputStream(filename);
	            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
	            properties.load(bf);
	            value = properties.getProperty(key);
	            inputStream.close(); // close stream
	            bf.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return value;
	    }
	    // write properties include Chinese
	    public static void writePropertiesFileObj(String filename, Properties properties, String fangjian, QQBasicInfoDto qqDto) throws IOException {
	        if (properties == null) {
	            properties = new PropertiesUtils();
	        }
	        InputStream inputStream = new FileInputStream(filename);
	        BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            properties.load(bf);
            inputStream.close();
	        try {
	            OutputStream outputStream = new FileOutputStream(filename);
	            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream, "utf-8"));
	           
	            properties.setProperty(fangjian+".id", qqDto.getId().toString()); 
	            properties.setProperty(fangjian+".name", qqDto.getName()==null?"":qqDto.getName()); 
	            properties.setProperty(fangjian+".QQ1", qqDto.getQQ1()==null?"":qqDto.getQQ1());
	            properties.setProperty(fangjian+".name1", qqDto.getName1()==null?"":qqDto.getName1());
	            properties.setProperty(fangjian+".QQ2", qqDto.getQQ2()==null?"":qqDto.getQQ2());
	            properties.setProperty(fangjian+".name2", qqDto.getName2()==null?"":qqDto.getName2());
	            properties.setProperty(fangjian+".QQ3", qqDto.getQQ3()==null?"":qqDto.getQQ3());
	            properties.setProperty(fangjian+".name3", qqDto.getName3()==null?"":qqDto.getName3());
	            properties.setProperty(fangjian+".QQ4", qqDto.getQQ4()==null?"":qqDto.getQQ4());
	            properties.setProperty(fangjian+".name4", qqDto.getName4()==null?"":qqDto.getName4());
	            properties.setProperty(fangjian+".QQ5", qqDto.getQQ5()==null?"":qqDto.getQQ5());
	            properties.setProperty(fangjian+".name5", qqDto.getName5()==null?"":qqDto.getName5());
	            properties.setProperty(fangjian+".QQ6", qqDto.getQQ6()==null?"":qqDto.getQQ6());
	            properties.setProperty(fangjian+".name6", qqDto.getName6()==null?"":qqDto.getName6());
	            properties.setProperty(fangjian+".roomId", qqDto.getRoomId()==null?"":qqDto.getRoomId());  
	            properties.store(bw, "Update '" + fangjian+".name" + "' value");
	            bw.close();
	            outputStream.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}

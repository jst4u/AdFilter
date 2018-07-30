package com.loit.core.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertieUtil {
	
    private Properties propertie;
    private FileInputStream inputFile;
    private FileOutputStream outputFile;
    private InputStream in;

    public  PropertieUtil(){
        propertie = new Properties();
    }
    
    /** 
     * ��ʼ��PropertiesParse��
     * @param filePath Ҫ��ȡ�������ļ���·��+���
     */
    public  PropertieUtil(String filePath){
        propertie = new Properties();
        try  {
        	in=new java.io.BufferedInputStream(
        			new FileInputStream(
        					new java.io.File("E://xbank_workspace//UtilProject//src//" +
        							"com//use//yj//util//jdbc.properties")));   
        	 //in=PropertieUtil.class.getClassLoader().getResourceAsStream(filePath);
        	propertie.load(in);
        	in.close();
        } catch (FileNotFoundException ex) {
            System.out.println("��ȡ�����ļ�--->ʧ�ܣ�- ԭ���ļ�·����������ļ�������");
            ex.printStackTrace() ;
        } catch (IOException ex) {
        	System.out.println("װ���ļ�--->ʧ��!");
            ex.printStackTrace() ;
        }
    }
    
    /**
     * ���غ���õ�key��ֵ
     * @param key ȡ����ֵ�ļ�
     * @return key��ֵ
     */
    public String getValue(String key) {
        if(propertie.containsKey(key)) {
            String value = propertie.getProperty(key);//�õ�ĳһ���Ե�ֵ
            return value;
        }
        else 
            return "";
    }
    
    /**
     * ���غ���õ�key��ֵ
     * @param fileName properties�ļ���·��+�ļ���
     * @param key ȡ����ֵ�ļ�
     * @return key��ֵ
     */
    public String getValue(String fileName, String key) {
        try  {
            String value = "";
            inputFile = new FileInputStream(fileName);
            propertie.load(inputFile);
            inputFile.close();
            if(propertie.containsKey(key)){
                value = propertie.getProperty(key);
                return value;
            }else
                return value;
        } catch (Exception e) {
        	System.out.println(e.getMessage());
            return "";
        } 
    }
    
    /**
     * ���properties�ļ������е�key����ֵ
     */
    public void clear() {
        propertie.clear();
    }
    
    /**
     * �ı�����һ��key��ֵ����key������properties�ļ���ʱ��key��ֵ��value����棬
     * ��key������ʱ����key��ֵ��value
     * @param key Ҫ����ļ�
     * @param value Ҫ�����ֵ
     */
    public void setValue(String key, String value) {
        propertie.setProperty(key, value);
    }
    
    /**
     * ����ĺ���ļ���ݴ���ָ�����ļ��У����ļ��������Ȳ����ڡ�
     * @param fileName �ļ�·��+�ļ����
     * @param description �Ը��ļ�������
     */
    public void saveFile(String fileName, String description){
        try {
            outputFile = new FileOutputStream(fileName);
            propertie.store(outputFile, description);
            outputFile.close();
        } catch (Exception e) {
            e.printStackTrace() ;
        }
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PropertieUtil pu = new PropertieUtil("jdbc.properties") ;
		System.out.println(pu.getValue("jdbc.url"));
	}

}

package com.yj;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

import net.sf.json.JSONObject;


public class JsonUtil {
		
	private static void firstUpperJsonValue() throws IOException{
		File file = new File("e:\\aaayj.txt");
		FileInputStream fileInputStream = new FileInputStream(file);
		InputStreamReader ir = new InputStreamReader(fileInputStream);
		BufferedReader bf = new BufferedReader(ir);
		String buff;
		StringBuilder sb = new StringBuilder();
		while((buff = bf.readLine()) != null){
			sb.append(buff);
		}
		System.out.println(recurisionJson(sb.toString()));
	}
	
	private static JSONObject recurisionJson(Object json){
		JSONObject jsonObject = JSONObject.fromObject(json);
		Iterator<String> keys = jsonObject.keys();
		while(keys.hasNext()){
			String key = keys.next();
			Object value =  jsonObject.get(key);
			if(value instanceof JSONObject){
				JSONObject jsonO = recurisionJson(value);
				jsonObject.put(key, jsonO);
			}else{
				String[] aa = value.toString().split("\\s+");
				for (String a :aa) {
					String newJsonValue = a.substring(0, 1).toUpperCase()+a.substring(1);
					value = value.toString().replace(a, newJsonValue);
				}
					jsonObject.put(key, value);
			}
		}
		return jsonObject;
	}
	
	public static void main(String[] args) throws IOException {
		firstUpperJsonValue();
	}
}

package com.jingu.IOT.dao;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class test {

	public static void main(String[] args) {
//		test1();
		test3();
	}

	
	
	
	public static void test3() {
		String data = 
				"[{'id':'1','a':'100','b':'66'}," 
				+ "{'id':'2','a':'95','b':'100'},"
				+ "{'id':'3','a':'85','b':'95'}," 
				+ "{'id':'4','a':'80','b':'85'}," 
				+ "{'id':'5','a':'70','b':'66'},"
				+ "{'id':'6','a':'75','b':'70'}," 
				+ "{'id':'7','a':'60','b':'1'}," 
				+ "{'id':'8','a':'65','b':'60'},"
				+ "{'id':'9','a':'50','b':'1'}," 
				+ "{'id':'10','a':'55','b':'50'},"
				+ "{'id':'11','a':'40','b':'1'}," 
				+ "{'id':'12','a':'45','b':'40'}]";

		JSONArray a = JSONArray.fromObject(data);
		for (int i = 0; i < a.size(); i++) {
			//先循环一次 因为第一条数据不需要判断
			if (i>0) {
				//从第一个对象开始 获取第一个起始值
				JSONObject jo = a.getJSONObject(i-1);
				//从第二个对象开始 获取第二个結束值
				JSONObject jo2 = a.getJSONObject(i);
				if (jo.getString("a").equals(jo2.getString("b"))) {
					System.out.println(jo2.getString("id"));
				}
			}
		}
		
		
		
	}
	
	
	
	
	
	public static void test1() {
		String data = "[{'goodsId':'1','goodsq':'12'}," + "{'goodsId':'2','goodsq':'66'},"
				+ "{'goodsId':'3','goodsq':'15'}," + "{'goodsId':'4','goodsq':'10'}," + "{'goodsId':'5','goodsq':'12'},"
				+ "{'goodsId':'6','goodsq':'10'}," + "{'goodsId':'7','goodsq':'15'}," + "{'goodsId':'8','goodsq':'10'},"
				+ "{'goodsId':'9','goodsq':'77'}," + "{'goodsId':'10','goodsq':'10'},"
				+ "{'goodsId':'11','goodsq':'88'}," + "{'goodsId':'12','goodsq':'99'}]";

		JSONArray a = JSONArray.fromObject(data);
		List<String> goods = new ArrayList<String>();
		// 重复数据的集合
		List<String> goodsq = new ArrayList<String>();
		for (int i = 0; i < a.size(); i++) {
			JSONObject jo = a.getJSONObject(i);
			// 判断这个值是否重复
			if (!goodsq.contains(jo.get("goodsq").toString())) {
				goodsq.add(jo.get("goodsq").toString());
				goods.add(jo.toString());
			}
		}

		for (String string : goods) {
			System.err.println(string);
		}
	}

	public void test2(String url) {

		try {
			InputStream inputStream = new URL(url).openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("utf-8")));
			StringBuffer sBuffer = new StringBuffer();
			int cp;
			while ((cp = br.read()) != -1) {
				sBuffer.append(cp);
			}
			String json = sBuffer.toString();

			JSONObject object = JSONObject.fromObject(json);

			System.out.println(object.toString());

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

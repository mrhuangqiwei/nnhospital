package com.qiwei.hospital.utils.comprehensive;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.os.Message;
import android.util.Log;


import com.qiwei.hospital.utils.httplelper.HttpConnSoap;

public class DBUtil {
	private ArrayList<String> arrayList = new ArrayList<String>();
	private ArrayList<String> brrayList = new ArrayList<String>();
	private ArrayList<String> crrayList = new ArrayList<String>();

	private HttpConnSoap Soap = new HttpConnSoap();
	
	
	

	public static Connection getConnection() {
		Connection con = null;
		try {
			//Class.forName("org.gjt.mm.mysql.Driver");
			//con=DriverManager.getConnection("jdbc:mysql://192.168.0.106:3306/test?useUnicode=true&characterEncoding=UTF-8","root","initial");  		    
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return con;
	}

	/**
	 * 获取所有货物的信息
	 * 
	 * @return
	 */
	public List<HashMap<String, String>> getAllInfo() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();
		new Thread() {  
			@Override  
			public void run() {  
			try {  
			crrayList = Soap.GetWebServre("selectAllCargoInfor", arrayList, brrayList);  
			
			Message message=new Message();
			message.obj=crrayList;
			//myhandler.sendMessage(message);
			//Log.e("2222222222","------------>" +message.obj);
			message.sendToTarget();
		//	handler.sendMessage(message);
			} catch (Exception e) {  
			}  
			}  
			}.start();
		return list;
	

//		HashMap<String, String> tempHash = new HashMap<String, String>();
//		tempHash.put("Cno", "Cno");
//		tempHash.put("Cname", "Cname");
//		tempHash.put("Cnum", "Cnum");
//		list.add(tempHash);
//		
//		for (int j = 0; j < crrayList.size(); j += 3) {
//			HashMap<String, String> hashMap = new HashMap<String, String>();
//			hashMap.put("Cno", crrayList.get(j));
//			hashMap.put("Cname", crrayList.get(j + 1));
//			hashMap.put("Cnum", crrayList.get(j + 2));
//			list.add(hashMap);
//		}
//
//		return list;
	}

	/**
	 * 检查用户名和密码是否一致
	 */
	public  ArrayList<String> getusercheck() {
		List<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();

		arrayList.clear();
		brrayList.clear();
		crrayList.clear();


		new Thread(){
			@Override
			public void run()
			{
				try{
					crrayList = Soap.GetWebServre("getUserInfo", arrayList, brrayList);
				}
				catch(Exception e) {
				}
			}
		}.start();


		return crrayList;
	}


	/**
	 * 注册用户账号和密码
	 * @param phonenum
	 * @param password
	 */
	public void register(String phonenum,String password){
		arrayList.clear();
		brrayList.clear();
		arrayList.add("userid");
		arrayList.add("password");
		brrayList.add(phonenum);
		brrayList.add(password);	
		new Thread(){
			public void run()
			{
			try{
			Soap.GetWebServre("insertuserInfo", arrayList, brrayList);
			}
			catch(Exception e) {
			}
			}
			}.start(); 
		
		
	}
	
	
	
	/**
	 * 删除一条货物信息
	 * 
	 * @return
	 */
	public void deleteCargoInfo(String Cno) {
		arrayList.clear();
		brrayList.clear();
		
		arrayList.add("Cno");
		brrayList.add(Cno);
		new Thread() {  
			@Override  
			
			public void run() {  
			try {  
				
				
				Soap.GetWebServre("deleteCargoInfo", arrayList, brrayList);
			} catch (Exception e) {  
			}  
			}  
			}.start(); 
		
	
	}
}

package com.qiwei.hospital.utils.httplelper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

import android.util.Log;
import android.util.Xml;

public class HttpConnSoap {
	public ArrayList<String> GetWebServre(String methodName,ArrayList<String> Parameters,ArrayList<String>ParValues)
	{
		ArrayList<String> Values=new ArrayList<String>();
		String ServerUrl="http://192.168.101.112:8929/Service1.asmx";
		//String soapAction="http://tempuri.org/LongUserId1";
		String soapAction="http://tempuri.org/"+methodName;
		String data="";
		String soap = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+ "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
				+"<soap:Body />";
		String tps,vps,ts;
		String mreakString="";
		mreakString="<"+methodName+" xmlns=\"http://tempuri.org/\">";
		for ( int i = 0; i < Parameters.size(); i++) {
			tps=Parameters.get(i).toString();
			//���ø÷����Ĳ���Ϊ.net webService�еĲ������
			vps=ParValues.get(i).toString();
			ts="<"+tps+">"+vps+"</"+tps+">";
			mreakString=mreakString+ts;
		}
		mreakString=mreakString+"</"+methodName+">";
	            /*
	            +"<HelloWorld xmlns=\"http://tempuri.org/\">"
	            +"<x>string11661</x>"
	            +"<SF1>string111</SF1>"
	            + "</HelloWorld>"
	            */
		String soap2="</soap:Envelope>";
		String requestData=soap+mreakString+soap2;
		//System.out.println(requestData);

		try{
			URL url =new URL(ServerUrl);
			HttpURLConnection con=(HttpURLConnection)url.openConnection();
			byte[] bytes=requestData.getBytes("utf-8");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setUseCaches(false);
			con.setConnectTimeout(6000);// ���ó�ʱʱ��
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "text/xml;charset=utf-8");
			con.setRequestProperty("SOAPAction",soapAction);
			con.setRequestProperty("Content-Length",""+bytes.length);
			OutputStream outStream=con.getOutputStream();
			outStream.write(bytes);
			outStream.flush();
			outStream.close();
			InputStream inStream=con.getInputStream();

			//data=parser(inStream);
			//System.out.print("11");
			Values= inputStreamtovaluelist(inStream,methodName);
		//	Log.e("这里是valuews",Values.toString());
			//System.out.println(Values.size());
			return Values;

		}
		catch(Exception e)
		{
			System.out.print("2221");
			return null;
		}
	}
	public   ArrayList<String>   inputStreamtovaluelist  (InputStream   in,String MonthsName)   throws   IOException   {
		StringBuffer   out   =   new   StringBuffer();
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		String s1="";
		byte[]   b   =   new   byte[10240];
		ArrayList<String> Values=new ArrayList<String>();
		Values.clear();

		while((s1=reader.readLine())!=null){
			out.append(s1);
		}
/**
		for  (int   n=0;   (n   =   in.read(b))   !=   -1;n++)   {
			s1=new  String(b,   0,   n);
			out.append(s1);
		}**/

		//Log.e("这是s1", s1);
		System.out.println(out);
		//Log.e("这里是out值", out.toString());
		String s23=String.format(out.toString());
	//	Log.e("这是s23",s23);
		//Log.e("这里是out值", out.toString());
		String[ ] s13=s23.split("><");

		//Log.e("这是S13",s13.toString());

		String ifString=MonthsName+"Result";
	Log.e("ifString",ifString);
		String TS="";
		String vs="";

		Boolean getValueBoolean=false;
		for(int i=0;i<s13.length;i++){
			TS=s13[i];
			System.out.println(TS);
		//	Log.e("这是Ts",TS.toString());
			int j,k,l;
			j=TS.indexOf(ifString);
			k=TS.lastIndexOf(ifString);

			if (j>=0)
			{
				System.out.println(j);
				if (getValueBoolean==false)
				{
					getValueBoolean=true;
				}
				else {

				}

				if ((j>=0)&&(k>j))
				{
					System.out.println("FFF"+TS.lastIndexOf("/"+ifString));
					//System.out.println(TS);
					l=ifString.length()+1;
					vs=TS.substring(j+l,k-2);
					//System.out.println("fff"+vs);
					Values.add(vs);
					System.out.println("�˳�"+vs);
					getValueBoolean=false;
					return   Values;
				}

			}
			if (TS.lastIndexOf("/"+ifString)>=0)
			{
				getValueBoolean=false;
				return   Values;
			}
			if ((getValueBoolean)&&(TS.lastIndexOf("/"+ifString)<0)&&(j<0))
			{
				k=TS.length();
				//System.out.println(TS);
				vs=TS.substring(7,k-8);
				//System.out.println("f"+vs);
				Values.add(vs);
			}

		}
//Log.e("后面一个Values",Values.toString());
		return   Values;
	}

}






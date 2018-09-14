package com.jingu.IOT.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.util.Vector;
public class PublicMethod {
	public static int byteToInt2(byte[] b) {
		int mask = 0xff;
		int temp = 0;
		int n = 0;
		for (int i = 0; i < b.length; i++) {
			n <<= 8;
			temp = b[i] & mask;
			n |= temp;
		}
		return n;
	}
	
	public static String getByteToIP(byte[] IPByte) {
		try {
			InetAddress addr = InetAddress.getByAddress(IPByte);
			return addr.toString().substring(1);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static byte[] int2bytes(int num) {
		byte[] b=new byte[2];
		byte high = (byte)((num & 0xff00)>>8);
		byte low = (byte)(num & 0x00ff);
		b[0]=high;
		b[1]=low;
		return b;
	}
	public static byte[] int4bytes(int num) {
		byte[] b=new byte[4];
		for(int i = 4 - 1; i >= 0; i--) { 
			b[i] = (byte) (num & 0xff); 
			num >>>= 8; 
		} 
		return b;
    }
	//20150531 数组 数组起始位置 数组长度2,4
	public static int bytesToInt(byte[] b, int start, int len) {
        int sum = 0;
        int end = start + len;
        for (int i = start; i < end; i++) {
            int n = ((int)b[i]) & 0xff;
            n <<= (--len) * 8;
            sum += n;
        }
        return sum;
    }
	public static byte[] getDeviceID(byte[] value) {
		byte[] DeviceIdBtye = new byte[4];
		System.arraycopy(value, 14, DeviceIdBtye, 0, DeviceIdBtye.length);
		return DeviceIdBtye;
	}
    public static String getFormatDeviceID(byte[] value){
//    	return value[0]+"."+value[1]+"."+value[2]+"."+value[3];
    	String deviceId="";
    	for(int i=0;i<value.length;i++){
    	   String s=Integer.toHexString(value[i]&0xff);
           if(s.length()<2){
        	   if(deviceId.length()==0)
        		   deviceId="0"+s;
        	   else
        	       deviceId=deviceId+".0"+s;
           }else{
        	   if(deviceId.length()==0)
        		   deviceId=s+"";
        	   else
        	       deviceId=deviceId+"."+s;
           }
    	}
    	return deviceId;
    	
    }
	/**
	 * TODO 获取数据区的数据
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] getDataResult(byte[] value) {
		byte[] len = new byte[2];
		len[0] = value[12];
		len[1] = value[13];
		int dataLen = PublicMethod.byteToInt2(len);
		byte[] data = new byte[dataLen - 4];// 减去设备ID占有的4位
		System.arraycopy(value, 18, data, 0, data.length);
		return data;
	}
	/**
	 * TODO 获取数据区的数据
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] getUserDataResult(byte[] value) {
		byte[] len = new byte[2];
		len[0] = value[12];
		len[1] = value[13];
		int dataLen = PublicMethod.byteToInt2(len);
		byte[] data = new byte[dataLen];// 减去设备ID占有的4位
		System.arraycopy(value, 14, data, 0, data.length);
		return data;
	}
	/**
	 * 获取数据区长度，不包括设备ID的4位
	 * 
	 * @param value
	 * @return
	 */
	public static int getDataLength(byte[] value) {
		byte[] len = new byte[2];
		len[0] = value[12];
		len[1] = value[13];
		int dataLen = PublicMethod.byteToInt2(len);
		return dataLen - 4;
	}

	/**
	 * TODO 获取加密后的数据区长度
	 * 
	 * @param value
	 * @return
	 */
	public static int getAESDataLength(byte[] value) {
		byte[] len = new byte[2];
		len[0] = value[10];
		len[1] = value[11];
		int dataLen = PublicMethod.byteToInt2(len);
		return dataLen;
	}

	/**
	 * TODO 获取命令的结果是否正常
	 * 
	 * @param data
	 * @return
	 */
	public static boolean getCommandResult(byte[] data) {
		// 85 5 0 50 10 0 0 0 85 4 0 8 0 6 16 1 0 19 111 107 4
		//[85, 5, -128, 0, 0, 0, 0, 0, 0, 0, 0, 16, 41, 82, -46, -114, 118, -95, -116, 117, -47, -27, 61, 30, 106, -38, -79, -85, -92]
		byte[] AES = new byte[2];
		AES[0] = data[12];
		AES[1] = data[13];
		int AESLen = PublicMethod.byteToInt2(AES);
		byte[] value = new byte[AESLen - 4];
		System.arraycopy(data, 18, value, 0, value.length);
		String result = new String(value);
		if (result.equals("ok"))
			return true;
		else
			return false;
	}
	//判断未加密包数据是否含有66
	public static boolean getCommandResult66(byte[] data) {
		// 85 5 0 50 10 0 0 0 85 4 0 8 0 6 16 1 0 19 111 107 4
		byte[] AES = new byte[2];
		AES[0] = data[12];
		AES[1] = data[13];
		int AESLen = PublicMethod.byteToInt2(AES);
		byte[] value = new byte[AESLen - 4];
		System.arraycopy(data, 18, value, 0, value.length);
//		String result = new String(value);
		if (byteToInt2(value)==102)
			return true;
		else
			return false;
	}
	//""
	public static Vector VraHour=getVraHour();
    public static Vector getVraHour(){
    	Vector hour=new Vector();
    	for(int i=0;i<24;i++){
    	  String value=String.format("%02d", i);
    	  //System.out.println(value);
    	 //。。 hour.add(new org.apache.struts.util.LabelValueBean(value,value));
    	}
    	return hour;
    }
    public static Vector VraMinute=getVraMinute();
    public static Vector getVraMinute(){
    	Vector minute=new Vector();
    	for(int i=0;i<60;i++){
    	  String value=String.format("%02d", i);
    	  //System.out.println(value);
    //。。	  minute.add(new org.apache.struts.util.LabelValueBean(value,value));
    	}
    	return minute;
    }
    public static String getFormatTable(String deviceId){
    	StringTokenizer token=new StringTokenizer(deviceId,".");
        String tableName="";
		while(token.hasMoreTokens()){
	        String value=token.nextToken();
	        if(value.length()<2)  
			    tableName=tableName+"0"+value;
	        else
	        	tableName=tableName+value;
		}
		return "T_VARTRIVER_"+tableName;
	}
    public static String getFormatDeviceId(String deviceId){
    	StringTokenizer token=new StringTokenizer(deviceId,".");
        String frmDeviceId="";
		while(token.hasMoreTokens()){
	        String s=token.nextToken();
	        if(s.length()<2){
	        	   if(frmDeviceId.length()==0)
	        		   frmDeviceId="0"+s;
	        	   else
	        		   frmDeviceId=frmDeviceId+".0"+s;
	           }else{
	        	   if(frmDeviceId.length()==0)
	        		   frmDeviceId=s+"";
	        	   else
	        		   frmDeviceId=frmDeviceId+"."+s;
	           }
		}
		return frmDeviceId;
	}
    public static String[] getArrStrForByte(byte b)
    {
    	String sBin = "" +
        (byte)((b >> 7) & 0x1) +
          (byte)((b >> 6) & 0x1) +
            (byte)((b >> 5) & 0x1) +
              (byte)((b >> 4) & 0x1) +
                (byte)((b >> 3) & 0x1) +
                  (byte)((b >> 2) & 0x1) +
                    (byte)((b >> 1) & 0x1) +
                      (byte)((b >> 0) & 0x1);
    	String[] arrBin=sBin.split("");
    	String[] resultArrBin = resultArrBin=new String[8];    	
    	System.arraycopy(arrBin, 1, resultArrBin, 0, resultArrBin.length);		
         return resultArrBin;
//    	int tmpB=(int)b;		
//		String sBin=Integer.toBinaryString(tmpB);
//		String[] arrBin=sBin.split("");
//		String[] resultArrBin;
//		if(arrBin.length-1<8)
//			resultArrBin=new String[arrBin.length-1];
//		else
//			resultArrBin=new String[8];
//		System.arraycopy(arrBin, 1, resultArrBin, 0, resultArrBin.length);		
    }
    /*依据系统OrderByLength位数，返回sql语句占位符'_',X倍*/
	public static String genReplaceSQLSymbol(int x){
		//Unit.OrderByLength
		String oriStr="________________________________________________________________________________________________________________________________________________________________________________________________________";
	//。。	String resultStr=oriStr.substring(0,/* Unit.OrderByLength*x*/);
		String resultStr =null;
		return resultStr;
	}
	public static String getGlobalSwId(String deviceId,String groupId,String switchId){
		deviceId = deviceId.replaceAll("\\.", "");
		if(groupId.length()==1) groupId="0"+groupId;
		if(switchId.length()==1) switchId="0"+switchId;
		return deviceId+groupId+switchId;
	}
	/* *
	 * Convert byte[] to hex string.这里我们可以将byte转换成int，然后利用Integer.toHexString(int)
	 *来转换成16进制字符串。  
	 * @param src byte[] data  
	 * @return hex string  
	 */     
	public static String bytesToHexString(byte[] src){  
	    StringBuilder stringBuilder = new StringBuilder("");  
	    if (src == null || src.length <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < src.length; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v);  
	        if (hv.length() < 2) {  
	        	
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv);  
	        stringBuilder.append(","); 
	    }  
	    return stringBuilder.toString();  
	} 
	//获得IP地址的长度
	public static int getIpVersion(InetAddress ia)
	{
		byte[] address = ia.getAddress( );
		if (address.length == 4) return 4;
		else
			if (address.length == 16) return 6;
			else return -1;
	}
	//获得地址类型e
	public static char getIpClass(InetAddress ia)
	{
		byte[] address = ia.getAddress( );
		if (address.length != 4)
		{
			throw new IllegalArgumentException("No IPv6 addresses!");
		}
		int firstByte = address[0];
		if ((firstByte & 0x80) == 0) return 'A';
		else if ((firstByte & 0xC0) == 0x80) return 'B';
		else if ((firstByte & 0xE0) == 0xC0) return 'C';
		else if ((firstByte & 0xF0) == 0xE0) return 'D';
		else if ((firstByte & 0xF8) == 0xF0) return 'E';
		else return 'F';
	}
	
	public static boolean isInnerIP(String ipAddress){   
        boolean isInnerIp = false;   
        long ipNum = ipAdrr2Long(ipAddress);   
        /**  
        私有IP：A类  10.0.0.0-10.255.255.255  
               B类  172.16.0.0-172.31.255.255  
               C类  192.168.0.0-192.168.255.255  
        当然，还有127这个网段是环回地址  
        **/  
        long aBegin = ipAdrr2Long("10.0.0.0");   
        long aEnd = ipAdrr2Long("10.255.255.255");   
        long bBegin = ipAdrr2Long("172.16.0.0");   
        long bEnd = ipAdrr2Long("172.31.255.255");   
        long cBegin = ipAdrr2Long("192.168.0.0");   
        long cEnd = ipAdrr2Long("192.168.255.255");   
        isInnerIp = isInnerIpByRang(ipNum,aBegin,aEnd) || isInnerIpByRang(ipNum,bBegin,bEnd) || isInnerIpByRang(ipNum,cBegin,cEnd) || ipAddress.equals("127.0.0.1");   
        return isInnerIp;              
}
	private static long ipAdrr2Long(String ipAddress) {   
	    String [] ip = ipAddress.split("\\.");   
	    long a = Integer.parseInt(ip[0]);   
	    long b = Integer.parseInt(ip[1]);   
	    long c = Integer.parseInt(ip[2]);   
	    long d = Integer.parseInt(ip[3]);   
	  
	    long ipNum = a * 256 * 256 * 256 + b * 256 * 256 + c * 256 + d;   
	    return ipNum;   
	}
	private static boolean isInnerIpByRang(long userIp,long begin,long end){   
	     return (userIp>=begin) && (userIp<=end);   
	}
//	public static void main(String[] args) {
//		
//		int result = 13107;
//		result = (int)(result - 13107l)*100/(65535-13107);
//		System.out.println(result+"");
////		byte[] b = int4bytes(255);
////		toBinaryString
////		String t = PublicMethod.getGlobalSwId("10.10.02.03","1","2");
////		
////		List l=new ArrayList();
////		l.add(0, "1");
////		l.add( "5");
////		l.add("7");
////		l.add("11");
//	}
}

package com.jingu.IOT.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author 【Quill - VastRiver】
 * 首先初始化加密器并设置key
 * 调用解密加密函数
 */
public class AES {

	private final static String algorithm = "AES";
	private final static int HEAD_LEN = 2;
	private static Cipher cipherEN;
	private static Cipher cipherDE;
	/**
	 * 初始化加密器（耗时略长）
	 * 
	 * @param encrypted
	 * @param use_aes
	 * @return
	 */
	static
	{
		try {
			//String[]aesKey=ConfigFile.AESKey.split(",");
			String[]aesKey="1,2,1,2,1,2,1,2,1,2,1,2,1,2,1,2".split(",");
			byte[] key=new byte[16];
			for(int i=0;i<aesKey.length;i++){
				key[i]=(byte)Integer.parseInt(aesKey[i],16);	
			}
			SecretKeySpec skeySpec = new SecretKeySpec(key, algorithm);
			// Cipher cipher = Cipher.getInstance(algorithm);
			cipherEN = Cipher.getInstance("AES/ECB/NoPadding");
			cipherEN.init(Cipher.ENCRYPT_MODE, skeySpec);
			cipherDE = Cipher.getInstance("AES/ECB/NoPadding");
			cipherDE.init(Cipher.DECRYPT_MODE, skeySpec);
		} catch (Exception e) {
		}
	}
//	public static boolean IniCipher(byte[] rawKey)
//	{
//		try {
//			SecretKeySpec skeySpec = new SecretKeySpec(rawKey, algorithm);
//			// Cipher cipher = Cipher.getInstance(algorithm);
//			cipherEN = Cipher.getInstance("AES/ECB/NoPadding");
//			cipherEN.init(Cipher.ENCRYPT_MODE, skeySpec);
//			
//			cipherDE = Cipher.getInstance("AES/ECB/NoPadding");
//			cipherDE.init(Cipher.DECRYPT_MODE, skeySpec);
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}

	/**  
	 * 基本算法加密  
	 *   
	 * @param data  
	 * @param rawKey  
	 * @return  
	 * @throws Exception  
	 */
	private static byte[] encryptByte(byte[] data, byte[] rawKey) {
		// Instantiate the cipher
		try {
//			SecretKeySpec skeySpec = new SecretKeySpec(rawKey, algorithm);
//			// Cipher cipher = Cipher.getInstance(algorithm);
//			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
// 			cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//			//long lStart = System.currentTimeMillis(); 
			byte[] encrypted = cipherEN.doFinal(data);
			//long lUseTime = System.currentTimeMillis() - lStart;
			//System.out.println("Ini加密耗时：" + lUseTime + "毫秒");
			return encrypted;
			// return encryptBASE64(encrypted);
		} catch (Exception e) {
			//App.log.info("AES", "获取加密串出错," + e.getMessage());
			return (byte[]) null;
		}

	}

	/**
	 * 基本算法解密
	 * 
	 * @param encrypted
	 * @param rawKey
	 * @return
	 */
	private static byte[] decryptByte(byte[] encrypted, byte[] rawKey) {
		try {
			byte[] tmp = encrypted;
			System.arraycopy(encrypted, 0, tmp, 0,encrypted.length);
//			byte[] key = rawKey;

//			SecretKeySpec skeySpec = new SecretKeySpec(key, algorithm);
//			Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
//			//Cipher cipher = Cipher.getInstance(algorithm);
// 			cipher.init(Cipher.DECRYPT_MODE, skeySpec);

			byte[] decrypted = cipherDE.doFinal(tmp);
			return decrypted; 
		} catch (Exception e) { 
			return (byte[]) null;
		}

	}
	/**
	 * 封装算法加密
	 * 
	 * @param encrypted
	 * @param use_aes
	 * @return
	 */
	private static byte[] encrypt(byte[] data, boolean use_aes) {
		boolean isShortData=false;
		if(data.length<=14) isShortData=true;
		byte[] key=new byte[1];
		byte[] AesBuffer;//加密后生成的数据
		int tot_len = -1;//欲加密数据总长度
		int data_len = data.length;
		int dataPlain_pos = 0;
		int aesBuffer_pos = 0;
		int copy_plain_len = 16;
		byte[] plain_Tempbuff = new byte[16];//加密明文
		byte[] Aes_Tempbuff = new byte[16];//加密后的密文
		try {
			if (use_aes) {
				tot_len = ((((data.length + HEAD_LEN) + 15) >> 4) << 4);//变为16的整数倍
				//建立加密区缓冲
				AesBuffer = new byte[tot_len];
				//将数据长度写入待加密区
				plain_Tempbuff[0] = (byte) ((data.length & 0xFF00) >> 8);
				plain_Tempbuff[1] = (byte) (data.length & 0xFF);
		

				if ((data_len - dataPlain_pos) < 16)
					copy_plain_len = data_len - dataPlain_pos +HEAD_LEN;
				//剩余14byte数据考入plain_Tempbuff
				System.arraycopy(data, dataPlain_pos, plain_Tempbuff, HEAD_LEN,
						copy_plain_len - HEAD_LEN);

				Aes_Tempbuff = encryptByte(plain_Tempbuff, key);
				System.arraycopy(Aes_Tempbuff, 0, AesBuffer, aesBuffer_pos, 16);

				//清空缓冲区
				Aes_Tempbuff = new byte[16];
				plain_Tempbuff = new byte[16];

				dataPlain_pos += copy_plain_len - HEAD_LEN;
				aesBuffer_pos += 16;
				while (dataPlain_pos < data_len&&(!isShortData)) {
					if ((data_len - dataPlain_pos) < 16)
						copy_plain_len = data_len - dataPlain_pos;
					//继续16byte数据考入plain_Tempbuff
					System.arraycopy(data, dataPlain_pos, plain_Tempbuff, 0,
							copy_plain_len);
					Aes_Tempbuff = encryptByte(plain_Tempbuff, key);
					System.arraycopy(Aes_Tempbuff, 0, AesBuffer, aesBuffer_pos,
							16);

					dataPlain_pos += copy_plain_len;
					aesBuffer_pos += 16;

					//清空缓冲区
					Aes_Tempbuff = new byte[16];
					plain_Tempbuff = new byte[16];
				}
			} else {
				tot_len = data.length + HEAD_LEN;
				//建立加密区缓冲
				AesBuffer = new byte[tot_len];
				//将数据长度写入数据区
				AesBuffer[0] = (byte) ((data.length & 0xFF00) >> 8);
				AesBuffer[1] = (byte) (data.length & 0xFF);
				System.arraycopy(data, 0, AesBuffer, HEAD_LEN, data.length);
			}
		} catch (Exception e) {  
			return (byte[]) null;
		}
		return AesBuffer;

	}
	/**
	 * 封装算法解密
	 * 
	 * @param encrypted
	 * @param use_aes
	 * @return
	 */
	private static byte[] decrypt(byte[] data, boolean use_aes) {
		byte[] key=new byte[1];
		byte[] PlaintBuffer = new byte[data.length];// 解密后生成的数据
		byte[] resultData;// 解密后生成的数据
		int data_len = data.length;
		int resultData_len = data.length;
		int dataAes_pos = 0;
		byte[] plain_Tempbuff = new byte[16];// 解密明文
		byte[] Aes_Tempbuff = new byte[16];// 解密前的密文
		// 清空缓冲区
		Aes_Tempbuff = new byte[16];
		plain_Tempbuff = new byte[16];
		int copy_aes_len = 16;
		try {
			if (use_aes) {
				while (dataAes_pos < data.length && data_len>0) {
					if (data_len<=16)
						copy_aes_len = data_len;
					
					System.arraycopy(data, dataAes_pos, Aes_Tempbuff, 0,copy_aes_len);
					plain_Tempbuff = decryptByte(Aes_Tempbuff, key);
					System.arraycopy(plain_Tempbuff, 0, PlaintBuffer,
							dataAes_pos, plain_Tempbuff.length);
					dataAes_pos += copy_aes_len;
					
					data_len=data_len-16;
					// 清空缓冲区
					Aes_Tempbuff = new byte[16];
					plain_Tempbuff = new byte[16];
				}
				
				byte[] dataLen=new byte[2];
				dataLen[0]=PlaintBuffer[0];
				dataLen[1]=PlaintBuffer[1];
				resultData_len = PublicMethod.byteToInt2(dataLen);
				resultData = new byte[resultData_len];
				System.arraycopy(PlaintBuffer, HEAD_LEN, resultData, 0,
						resultData_len);
			} else {
				resultData_len = (data[0] << 8) | data[1];
				resultData = new byte[resultData_len];
				System.arraycopy(data, HEAD_LEN, resultData, 0, resultData_len);
			}

		} catch (Exception e) {
			return (byte[]) null;
		}
		return resultData;

	}
	/**
	 * 解密入口
	 * @param data
	 * @return
	 */
	public static byte[] DeParseData(byte[] data){
		//[85, 3, -128, 0, 0, 0, 0, 0, 0, 0, 0, 32, 38, -102, -35, 25, -15, -97, 70, -91, -99, 96, 36, 12, -111, 45, -39, 101, -95, -127, -44, 100, 21, 79, 123, -58, -15, 24, -28, 92, 74, 61, -83, -77, -119]
		if(data==null)
			return null;
		if(data[2]==(byte)0x80)
		{
			byte[] AesBuff,PlainBuff,Result;
			PlainBuff=new byte[data.length-12-1];
			System.arraycopy(data,12,PlainBuff,0,PlainBuff.length);
			
			AesBuff=decrypt(PlainBuff,true);
			
			Result=new byte[AesBuff.length+12+3];		

			byte high = (byte)(((AesBuff.length+2) & 0xff00)>>8);
			byte low = (byte)((AesBuff.length+2) & 0x00ff);
				
			System.arraycopy(data,0,Result,0,12);
			Result[10]=high;
			Result[11]=low;

			high = (byte)((AesBuff.length & 0xff00)>>8);
			low = (byte)(AesBuff.length & 0x00ff);
			
			Result[12]=high;
			Result[13]=low;
			
			System.arraycopy(AesBuff,0,Result,14,AesBuff.length);
			
			byte[] crcData=new byte[AesBuff.length+2];
			crcData[0]=high;
			crcData[1]=low;
			System.arraycopy(AesBuff,0,crcData,2,AesBuff.length);
			Result[Result.length-1]=GetCrc(crcData);
			
			Result[2]=(byte)0x80;
			//Result[2]=(byte)aes;
			//DeviceStatusBean bean = new DeviceStatusBean(Result);
			return Result;
		}
		else
			return data;
	}
	/**
	 * 加密入口
	 * @param data
	 * @return
	 */
	public static byte[] EnParseData(byte[] data){
		    if(ConfigFile.isAES.equals("false"))
		    	return data;
            if(data.length==12)
            	return data;
			byte[] AesBuff,PlainBuff,Result;
			PlainBuff=new byte[data.length-14-1];
			System.arraycopy(data,14,PlainBuff,0,PlainBuff.length);
			
			AesBuff=encrypt(PlainBuff,true);
			
			Result=new byte[AesBuff.length+12+1];		

			byte high = (byte)(((AesBuff.length) & 0xff00)>>8);
			byte low = (byte)((AesBuff.length) & 0x00ff);
				
			System.arraycopy(data,0,Result,0,12);
			Result[10]=high;
			Result[11]=low;

			System.arraycopy(AesBuff,0,Result,12,AesBuff.length);
			
			Result[Result.length-1]=GetCrc(AesBuff);
			
			Result[2]=(byte)0x80;
			//DeviceStatusBean bean = new DeviceStatusBean(Result);
			return Result;
	}
	
	private static byte GetCrc(byte[] data)
	{
		return Command.getCRC(data);
	}
	private static byte[] IntToByte(int len)
	{
		byte[] b =new byte[4];
		for(int i=0;i<4;i++)
		{
			int offset=(b.length -1-i)*8;
			b[i]=(byte)((len>>>offset)&0xff);
		}
		return b;
	}
/*	public static void main(String[] args) throws Exception {
		//int te=-(((100&0x80)>>7)-1) * ((100& ~(1 << 8))/256 + (100& ~(1 << 8))%256);
		byte[] testb=IntToByte(15);
//		byte[] data = { (byte) 0xc0, (byte) 0xa8, 0x7, 0x65, 0x1f, (byte) 0x90,
//				0x7b, 0x7f, (byte) 0xf0, 0x2a, 0x1f, (byte) 0x90, 0x10, 0x0};//,
		//[85, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 6, 16, 1, 0, 19, 111, 107, 4]
		String[] a="85 2 -128 0 0 0 0 0 0 0 0 64 -22 -31 -42 -6 60 96 -4 38 -58 6 -89 -49 29 73 -70 -30 -32 7 38 -103 68 124 98 -9 7 37 20 -95 67 -98 27 -8 111 82 125 93 99 -73 -19 -74 -119 111 44 -103 -71 -13 -79 -4 -13 -80 -49 27 -77 120 -67 -11 -87 87 -49 -108 -23 89".split(" ");
	//	            85  3  -128  0  0  0  0  0  0  0  0  32  38  -102  -35  25  -15  -97  70  -91  -99  96  36  12  -111  45  -39  101  -95  -127  -44  100  21  79  123  -58  -15  24  -28  92  74  61  -83  -77  -119 
		//                                                           [-8,  -124, 23, 73, -20, 48, 110, -70, 34, -59, -51, -38, -45, -71, -44, -53, 45, -93,  11, -103, 50, -30, -53, 108, -126, 24, 75, 90, -65, 15, -88, 117]
//		String[] a="85, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 6, 16, 1, 0, 19, 111, 107, 4".split(",");
		            //  85 9 -128 0 0 0 0 0 0 0 0 8 0 6 16 1 0 19 111 107 4 
		            //85  3  -128  0  0  0  0  0  0  0  0  32  38  -102  -35  25  -15 - 97  70  -91  -99  96  36  12  -111  45  -39  101 - 95  -127  -44  100  21  79  123  -58  -15  24  -28  92  74  61 - 83  -77  -119 
		byte[] data = new byte[a.length];
		for(int i=0;i<a.length;i++){
			data[i]=(byte)Integer.parseInt(a[i].trim());
		}
//		byte[]r=encrypt(data,true);
//		r=decrypt(r,true);

		byte[]r=DeParseData(data);
		for(int i=0;i<r.length;i++){
			System.out.print(r[i]+" ");
		}
		System.out.println("");
		r=EnParseData(data);
		for(int i=0;i<r.length;i++){
			System.out.print(r[i]+" ");
		}*/
		
//		byte[]r=DeParseData(data);
//		for(int i=0;i<r.length;i++){
//			System.out.print(r[i]+" ");
//		}
//		System.out.println("");
//		r=EnParseData(r);
//		for(int i=0;i<r.length;i++){
//			System.out.print(r[i]+" ");
//		}
//		//85, 3, -128, 0, 0, 0, 0, 0, 0, 0, 0, 32,
//		//38, -102, -35, 25, -15, -97, 70, -91, -99, 96, 36, 12, -111, 45, -39, 101, -95, -127, -44, 100, 21, 79, 123, -58, -15, 24, -28, 92, 74, 61, -83, -77
//       // -119
//				//0x0, 0x8, 0x10, 0x0, 0x10, 0x0, 0x1, 0x2, 0x1, 0x2 };
////		byte[] data = { (byte)0xc0, (byte)0xa8, 0x7, 0x65, 0x1f, (byte)0x90};
//		//[1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2]
////		String[]aesKey=ConfigFile.AESKey.split(",");
////		byte[] key1=new byte[16];
////		for(int i=0;i<aesKey.length;i++){
////			key1[i]=(byte)Integer.parseInt(aesKey[i],16);	
////		}
//		byte[] key = { 0x01, 0x02, 0x01, 0x02, 0x01, 0x02, 0x01, 0x02, 0x01,
//				0x02, 0x01, 0x02, 0x01, 0x02, 0x01, 0x02 };
//		long lStart = System.currentTimeMillis();
//		//IniCipher(key);
//		long lUseTime = System.currentTimeMillis() - lStart;
//		System.out.println("初始化加密器耗时：" + lUseTime + "毫秒");
//		lStart = System.currentTimeMillis();
//		// 加密   
//		//byte[] encrypted = encrypt(data, true);
//		//lUseTime = System.currentTimeMillis() - lStart;
//		//System.out.println("加密耗时：" + lUseTime + "毫秒");
//		// 解密   
//		lStart = System.currentTimeMillis();
//		byte[] decrypted = decrypt(data, true);// 解密串    
//		lUseTime = System.currentTimeMillis() - lStart;
//		System.out.println("解密耗时：" + lUseTime + "毫秒");
	}
/*}*/
//=========================================================
///**
// * BASE64解密
// * 
// * @param key
// * @return
// * @throws Exception
// */
//public static byte[] decryptBASE64(String key) throws Exception {
//	return (new BASE64Decoder()).decodeBuffer(key);
//}
//
///**  
// * BASE64加密  
// *   
// * @param key  
// * @return  
// * @throws Exception  
// */
//public static String encryptBASE64(byte[] key) throws Exception {
//	return (new BASE64Encoder()).encodeBuffer(key);
//}
//
///**  
// * 加密  
// *   
// * @param data  
// * @param rawKey  
// * @return  
// * @throws Exception  
// */
//public static String encryptStr(String data, String rawKey) {
//	byte[] key = rawKey.getBytes();
//	// Instantiate the cipher   
//	try {
//		SecretKeySpec skeySpec = new SecretKeySpec(key, algorithm);
//		Cipher cipher = Cipher.getInstance(algorithm);
//		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//		byte[] encrypted = cipher.doFinal(data.getBytes());
//		return encryptBASE64(encrypted);
//	} catch (Exception e) {
//		// App.log.info("AES", "获取加密串出错," + e.getMessage());   
//		return "";
//	}
//
//}
//
///**  
// * 解密  
// *   
// * @param encrypted  
// * @param rawKey  
// * @return  
// */
//public static String decrypStr(String encrypted, String rawKey) {
//	try {
//		byte[] tmp = decryptBASE64(encrypted);
//		byte[] key = rawKey.getBytes();
//
//		SecretKeySpec skeySpec = new SecretKeySpec(key, algorithm);
//		Cipher cipher = Cipher.getInstance(algorithm);
//		cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
//
//		byte[] decrypted = cipher.doFinal(tmp);
//		return new String(decrypted);
//	} catch (Exception e) {
//		// App.log.info("AES", "获取解密串出错," + e.getMessage());   
//		return "";
//	}
//
//}

//=========================================================


//
//
// /**
//  * AES加密算法
//  */
// public AES() {
// }
// /** 
//  * 加密 
//  * @param content 需要加密的内容 
//  * @param keyWord  加密密钥 
//  * @return byte[]  加密后的字节数组
//  */  
// public static byte[] encrypt(String content, String keyWord) {  
//         try {             
//                 KeyGenerator kgen = KeyGenerator.getInstance("AES");
//                 SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
//                 secureRandom.setSeed(keyWord.getBytes());  
//                 //kgen.init(128,secureRandom); 
//                 kgen.init(128);
//                 SecretKey secretKey = kgen.generateKey();  
//                 byte[] enCodeFormat = secretKey.getEncoded();  
//                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");  
//                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
//                 byte[] byteContent = content.getBytes("utf-8");  
//                 cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化  
//                 byte[] result = cipher.doFinal(byteContent);  
//                 return result; // 加密  
//         } catch (NoSuchAlgorithmException e) {  
//                 e.printStackTrace();  
//         } catch (NoSuchPaddingException e) {  
//                 e.printStackTrace();  
//         } catch (InvalidKeyException e) {  
//                 e.printStackTrace();  
//         } catch (UnsupportedEncodingException e) {  
//                 e.printStackTrace();  
//         } catch (IllegalBlockSizeException e) {  
//                 e.printStackTrace();  
//         } catch (BadPaddingException e) {  
//                 e.printStackTrace();  
//         }  
//         return null;  
// }
// /**
//  * @param content 需要加密的内容
//  * @param password 加密密钥
//  * @return String  加密后的字符串
//  */
// public static String encrypttoStr(String content, String password){
//  return parseByte2HexStr(encrypt(content,password));  
// }
// /**解密 
//  * @param content  待解密内容 
//  * @param keyWord 解密密钥 
//  * @return  byte[]
//  */  
// public static byte[] decrypt(byte[] content, String keyWord) {
//         try {  
//                 KeyGenerator kgen = KeyGenerator.getInstance("AES");  
//                 SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
//                 secureRandom.setSeed(keyWord.getBytes()); 
//                 
//                 //kgen.init(128);
//                 kgen.init(128,secureRandom);
//                 
//                 SecretKey secretKey = kgen.generateKey();  
//                 byte[] enCodeFormat = secretKey.getEncoded();  
//                 SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");              
//                 Cipher cipher = Cipher.getInstance("AES");// 创建密码器  
//                 cipher.init(Cipher.DECRYPT_MODE, key);// 初始化  
//                 byte[] result = cipher.doFinal(content);  
//                 return result; // 加密  
//         } catch (NoSuchAlgorithmException e) {  
//                 e.printStackTrace();  
//         } catch (NoSuchPaddingException e) {  
//                 e.printStackTrace();  
//         } catch (InvalidKeyException e) {  
//                 e.printStackTrace();  
//         } catch (IllegalBlockSizeException e) {  
//                 e.printStackTrace();  
//         } catch (BadPaddingException e) {  
//                 e.printStackTrace();  
//         }  
//         return null;  
// }
// /**
//  * @param content 待解密内容(字符串)
//  * @param keyWord 解密密钥
//  * @return byte[]
//  */
// public static byte[] decrypt(String content, String keyWord) {
//  return decrypt(parseHexStr2Byte(content),keyWord);  
// }
// /**将二进制转换成16进制 
//  * @param buf 
//  * @return  String
//  */  
// public static String parseByte2HexStr(byte buf[]) {  
//         StringBuffer sb = new StringBuffer();  
//         for (int i = 0; i < buf.length; i++) {  
//                 String hex = Integer.toHexString(buf[i] & 0xFF);  
//                 if (hex.length() == 1) {  
//                         hex = '0' + hex;  
//                 }  
//                 sb.append(hex.toUpperCase());  
//         }  
//         return sb.toString();  
// }
// /**将16进制转换为二进制 
//  * @param hexStr 
//  * @return  byte[]
//  */  
// public static byte[] parseHexStr2Byte(String hexStr) {  
//         if (hexStr.length() < 1)  
//                 return null;  
//         byte[] result = new byte[hexStr.length()/2];  
//         for (int i = 0;i< hexStr.length()/2; i++) {  
//                 int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
//                 int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
//                 result[i] = (byte) (high * 16 + low);  
//         }  
//         return result;  
// }  
//

// public static void main(String[] args) {
//  String content = "HongLonglong";  
//  String Key = "1212121212121212"; 
//  //加密
//  System.out.println("加密前：" + content);
//  String encryptResult = encrypttoStr(content, Key);
//  System.out.println("加密后：" + encryptResult); 
//  //解密  
//  byte[] decryptResult = decrypt(encryptResult,Key);  
//  System.out.println("解密后：" + new String(decryptResult)); 
// }
//}
//运行结果：
//加密前：HongLonglong
//加密后：CE95560CBEA0996DE05D230EE8ABB1F1
//解密后：HongLonglong


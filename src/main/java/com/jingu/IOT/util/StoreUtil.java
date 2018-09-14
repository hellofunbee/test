/**  
*   
* 项目名称：IOT  
* 类名称：StoreUtil  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年12月27日 下午5:52:48  
* 修改人：jianghu  
* 修改时间：2017年12月27日 下午5:52:48  
* 修改备注： 下午5:52:48
* @version   
*   
*/ 
package com.jingu.IOT.util;

import java.util.HashMap;
import java.util.Map;

/**

* @ClassName: StoreUtil
* @Description: TODO
* @author jianghu
* @date 2017年12月27日 下午5:52:48

*/
public class StoreUtil {

	private static Map<String, String> StoreMap ;
	
	static{
//		char[] leftChar = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'}
//		int[] topNum = {1,2,3,4,5,6,7,8,9};
		StoreMap = new HashMap<>();
		StoreMap.put("system01", "A11");
		StoreMap.put("system02", "A21");
		StoreMap.put("system03", "A31");
		StoreMap.put("system04", "A41");
		
		StoreMap.put("admin01", "B11");
		StoreMap.put("admin02", "B21");
		
		StoreMap.put("department", "C11");
		
		StoreMap.put("user01", "D11");
		StoreMap.put("user02", "D21");
		StoreMap.put("user03", "D31");
		StoreMap.put("user04", "D41");
		
		StoreMap.put("goods01", "E11");
		StoreMap.put("goods01", "E21");
		StoreMap.put("goods01", "E31");
		StoreMap.put("goods01", "E41");
		
		StoreMap.put("commission", "F11");
		
		StoreMap.put("gift", "G11");

		StoreMap.put("sale01", "I11");
		StoreMap.put("sale02", "I21");
		StoreMap.put("sale03", "I31");
		

		StoreMap.put("refund01", "J11");
		StoreMap.put("refund02", "J21");
		
		StoreMap.put("analysis01", "K11");
		StoreMap.put("analysis02", "K21");

		
	}
}

/**  
*   
* 项目名称：IOT  
* 类名称：SpringLoggingHelper  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月28日 下午3:57:39  
* 修改人：jianghu  
* 修改时间：2017年11月28日 下午3:57:39  
* 修改备注： 下午3:57:39
* @version   
*   
*/ 
package com.jingu.IOT.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**

* @ClassName: SpringLoggingHelper
* @Description: TODO
* @author jianghu
* @date 2017年11月28日 下午3:57:39

*/
public class SpringLoggingHelper {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void helpMethod(){
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
 
    }
	
	
}

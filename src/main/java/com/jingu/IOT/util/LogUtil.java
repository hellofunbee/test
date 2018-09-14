/**  
*   
* 项目名称：IOT  
* 类名称：LogUtil  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年11月28日 下午5:53:14  
* 修改人：jianghu  
* 修改时间：2017年11月28日 下午5:53:14  
* 修改备注： 下午5:53:14
* @version   
*   
*/ 
package com.jingu.IOT.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import net.sf.json.JSON;

/**

* @ClassName: LogUtil
* @Description: TODO
* @author jianghu
* @date 2017年11月28日 下午5:53:14

*/

@Aspect
@Component
public class LogUtil {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Pointcut("execution(* com.jingu.IOT.dao..*.*(..))")  
    public void executeService(){  
    	  
    }
	
	@Before("executeService()")  
    public void doBeforeAdvice(JoinPoint joinPoint){  
//        System.out.println("我是前置通知!!!");  
//        //获取目标方法的参数信息  
//        Object[] obj = joinPoint.getArgs();  
//        //AOP代理类的信息  
//        joinPoint.getThis();  
//        //代理的目标对象  
//        joinPoint.getTarget();  
//        //用的最多 通知的签名  
//        Signature signature = joinPoint.getSignature();  
//        //代理的是哪一个方法  
//        System.out.println(signature.getName());  
//        //AOP代理类的名字  
//        System.out.println(signature.getDeclaringTypeName());  
//        //AOP代理类的类（class）信息  
//        signature.getDeclaringType();  
//        //获取RequestAttributes  
//        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();  
//        requestAttributes.setAttribute("menu", "a,b,c,d", 0);
////        requestAttributes.set
//        //从获取RequestAttributes中获取HttpServletRequest的信息  
//        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);  
//        //如果要获取Session信息的话，可以这样写：  
//        //HttpSession session = (HttpSession) requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);  
//        Enumeration<String> enumeration = request.getParameterNames();  
//        Map<String,String> parameterMap = Maps.newHashMap();  
//        while (enumeration.hasMoreElements()){  
//            String parameter = enumeration.nextElement();  
//            parameterMap.put(parameter,request.getParameter(parameter));  
//        }  
//        String str = JSON.toJSONString(parameterMap);  
//        if(obj.length > 0) {  
//            System.out.println("请求的参数信息为："+str);  
//        }  
    }  
	
	
//    @AfterReturning(value = "execution(* com.zkn.learnspringboot.web.controller..*.*(..))",returning = "keys",argNames = "keys")  
//    public void doAfterReturningAdvice2(String keys){  
//  
//        System.out.println("第二个后置返回通知的返回值："+keys);  
//    } 
	
}

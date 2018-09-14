/**  
*   
* 项目名称：nxy  
* 类名称：B  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2017年8月3日 下午3:15:42  
* 修改人：jianghu  
* 修改时间：2017年8月3日 下午3:15:42  
* 修改备注： 下午3:15:42
* @version   
*   
*/ 
package com.jingu.IOT.entity;

public class IpcProxyBean {
	private String  s_num="";//一共几路代理
//	private String  s_no="";//第几路代理
/*	private List<IpcProxyHBM> proxyBeans=new ArrayList();
	public IpcProxyBean(String configStr){
		String[] configs=configStr.split(";");
		for(int i=0;i<configs.length;i++){
			String[] element=getElement(configs[i]);
			try {
				//String methodName=element[0].substring(0,1).toUpperCase() + element[0].substring(1);
				String methodName=element[0];
				if(methodName.equals("s_num")){
					String confVal=element[1];					
					methodName=methodName.substring(0,1).toUpperCase() + methodName.substring(1);
					Method method=IpcProxyBean.class.getMethod("set"+methodName, String.class);
					method.invoke(this,confVal);
				}
				
			} catch (Exception e) {
				System.out.println("参数["+element[0]+"]解析错误...");
				e.printStackTrace();
			}
		}
		parseProxyBean(configStr);
		
		
	}
	private void parseProxyBean(String configStr){
		String[] configs=configStr.split("s_no:");
		int cfgsLen=configs.length;
		if(cfgsLen>=2){
			for(int i=1;i<cfgsLen;i++){
				String curStr=configs[i];
				int start= curStr.indexOf(";")+1;
				String cfgStr=curStr.substring(start);
				IpcProxyHBM proxyBean=new IpcProxyHBM(cfgStr);
				proxyBeans.add(proxyBean);
			}
		}
		
	}
	private static String[] getElement(String value){
		String[] els=value.split(":");
		String[] element=new String[2];
		for(int i=0;i<els.length;i++)
			element[i]=els[i];
		return element;
	}
    
	public static void main(String[] args) {
		String str="s_num:2;s_no:0;s_host:192.168.5.141;s_rport:80;s_lport:8080;s_pwr:0;s_pwrval:0;s_timeout:1800;s_no:1;s_host:192.168.5.141;s_rport:554;s_lport:8554;s_pwr:1;s_pwrval:0;s_timeout:1800;";
		IpcProxyBean bean=new IpcProxyBean(str);
//		List ls1=hbm.getUi_Size();
//		List ls2=hbm.getUi_Profile();
		int i=0;
		i++;
	}
	public String getS_num() {
		return s_num;
	}
	public void setS_num(String s_num) {
		this.s_num = s_num;
	}
//	public String getS_no() {
//		return s_no;
//	}
//	public void setS_no(String s_no) {
//		this.s_no = s_no;
//	}
	public List<IpcProxyHBM> getProxyBeans() {
		return proxyBeans;
	}
	public void setProxyBeans(List<IpcProxyHBM> proxyBeans) {
		this.proxyBeans = proxyBeans;
	}*/

}

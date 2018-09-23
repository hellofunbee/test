/**  
*   
* 项目名称：IOT  
* 类名称：ReadExcelService  
* 类描述：  
* 创建人：jianghu  
* 创建时间：2018年3月29日 下午5:37:55  
* 修改人：jianghu  
* 修改时间：2018年3月29日 下午5:37:55  
* 修改备注： 下午5:37:55
* @version   
*   
*/ 
package com.jingu.IOT.service;

import com.jingu.IOT.util.Base64;
import com.jingu.IOT.util.StringUtils;
import com.jingu.IOT.util.ToolUtil;
import com.jingu.IOT.util.excelUtils.xlsx.Cell;
import com.jingu.IOT.util.excelUtils.xlsx.Sheet;
import com.jingu.IOT.util.excelUtils.xlsx.Sheet.SheetRowReader;
import com.jingu.IOT.util.excelUtils.xlsx.SimpleXLSXWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


/**

* @ClassName: ReadExcelService
* @Description: TODO
* @author jianghu
* @date 2018年3月29日 下午5:37:55

*/
@Component
public class ReadExcelService {

	/**
	 * 2018年3月29日
	 * jianghu
	 * @param fileName
	 * TODO
	 */
	public List<Map<String, Object>> readExcel(String fileName) {
//		 TODO Auto-generated method stub
		String encode = Base64.encode(fileName.getBytes());
		String file = "distribute_"+0+"_"+encode;
    	SimpleXLSXWorkbook workbook=getFile(ToolUtil.FILEPATH+file);
    	Sheet sheetToRead =workbook.getSheet(0, false);
   	 	SheetRowReader reader= sheetToRead.newReader();
     	Cell[] row;
        int ckint=0;
		 List<Map<String,Object>> list = new ArrayList<>();
 		 try {
 			while ((row = reader.readRow()) != null) {
				 if (ckint==0) {
    				 ckint++;
    				 continue;
    			 	}
				 	if (row.length==0) {
						break;
					}
					if (row[0]==null) {
						 continue;
					}
				 		String string = row[0].toString();
				 		if(string==null){
				 			continue;
				 		}    					 
				    int j=0;
	                    //第一个是列数，第二个是行数
				    String ctyh_content="";
                	if(row[j]!=null && row[j].getValue()!=null){
                		ctyh_content=StringUtils.trimKg(row[j].toString());//默认最左边编号也算一列 所以这里得j++
                	}
                    j++;
                    String ctyh_answ_one="";
                    if(row[j]!=null && row[j].getValue()!=null){
                    	ctyh_answ_one=StringUtils.trimKg(row[j].toString());
                    }
//                    j++; 
//                    String ctyh_answ_two="";
//                    if(row[j]!=null && row[j].getValue()!=null){
//                    	ctyh_answ_two=StringUtils.trimKg(row[j].toString());
//                    }
//                    j++; 
//                    String ctyh_answ_three="";
//                    if(row[j]!=null && row[j].getValue()!=null){
//                    	ctyh_answ_three=StringUtils.trimKg(row[j].toString());
//                    }
//                    j++; 
//                    String ctyh_answ_four="";
//                    if(row[j]!=null && row[j].getValue()!=null){
//                    	ctyh_answ_four=StringUtils.trimKg(row[j].toString());
//                    }
//                    j++; 
//                    String ctyh_answ_cr="";
//                    if(row[j]!=null && row[j].getValue()!=null){
//                    	ctyh_answ_cr=StringUtils.trimKg(row[j].toString());
//                    }
//                   tmService.uploadTemTYH(new ChoiceYHtopic(0,ctyh_content,ctyh_answ_one,ctyh_answ_two,ctyh_answ_three,ctyh_answ_four,ctyh_answ_cr,word_cell,tbk_de_id,2,ckid));
                    Map<String,Object> map = new HashMap<>();
					map.put("crop", ctyh_content);
					map.put("scale", ctyh_answ_one);
//					map.put("ctyh_answ_two", ctyh_answ_two);
//					map.put("ctyh_answ_three", ctyh_answ_three);
//					map.put("ctyh_answ_four", ctyh_answ_four);
//					map.put("ctyh_answ_cr", ctyh_answ_cr);
					list.add(map);
			 }
 			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
    		System.out.println("读取Excel失败");
    		return null;
//			return new ITextookWResult(2,"文件上传失败！",null);
		}
//		Boolean ckup=tmService.upTYHbyTemp(list,word_cell,tbk_de_id,2,ckid)>0;
//		if (!ckup) {
//			System.out.println("英选汉数据加入正式表失败");
//			return new ITextookWResult(2,"上传失败！请联系管理员。",null);
//		}
		return list;
	}
	
	public List<Map<String, Object>> readExcel2(String fileName) {
//		 TODO Auto-generated method stub
		String encode = Base64.encode(fileName.getBytes());
		String file = "distribute_"+0+"_"+encode;
   	SimpleXLSXWorkbook workbook=getFile(ToolUtil.FILEPATH+file);
   	Sheet sheetToRead =workbook.getSheet(0, false);
  	 	SheetRowReader reader= sheetToRead.newReader();
    	Cell[] row;
       int ckint=0;
		 List<Map<String,Object>> list = new ArrayList<>();
		 try {
			while ((row = reader.readRow()) != null) {
				 if (ckint==0) {
   				 ckint++;
   				 continue;
   			 	}
				 	if (row.length==0) {
						break;
					}
					if (row[0]==null) {
						 continue;
					}
				 		String string = row[0].toString();
				 		if(string==null){
				 			continue;
				 		}    					 
				    int j=0;
	                    //第一个是列数，第二个是行数
				    String ctyh_content="";
               	if(row[j]!=null && row[j].getValue()!=null){
               		ctyh_content=StringUtils.trimKg(row[j].toString());//默认最左边编号也算一列 所以这里得j++
               	}
                   j++;
                   String ctyh_answ_one="";
                   if(row[j]!=null && row[j].getValue()!=null){
                   	ctyh_answ_one=StringUtils.trimKg(row[j].toString());
                   }
//                   j++; 
//                   String ctyh_answ_two="";
//                   if(row[j]!=null && row[j].getValue()!=null){
//                   	ctyh_answ_two=StringUtils.trimKg(row[j].toString());
//                   }
//                   j++; 
//                   String ctyh_answ_three="";
//                   if(row[j]!=null && row[j].getValue()!=null){
//                   	ctyh_answ_three=StringUtils.trimKg(row[j].toString());
//                   }
//                   j++; 
//                   String ctyh_answ_four="";
//                   if(row[j]!=null && row[j].getValue()!=null){
//                   	ctyh_answ_four=StringUtils.trimKg(row[j].toString());
//                   }
//                   j++; 
//                   String ctyh_answ_cr="";
//                   if(row[j]!=null && row[j].getValue()!=null){
//                   	ctyh_answ_cr=StringUtils.trimKg(row[j].toString());
//                   }
//                  tmService.uploadTemTYH(new ChoiceYHtopic(0,ctyh_content,ctyh_answ_one,ctyh_answ_two,ctyh_answ_three,ctyh_answ_four,ctyh_answ_cr,word_cell,tbk_de_id,2,ckid));
                   Map<String,Object> map = new HashMap<>();
					map.put("crop", ctyh_content);
					map.put("scale", ctyh_answ_one);
//					map.put("ctyh_answ_two", ctyh_answ_two);
//					map.put("ctyh_answ_three", ctyh_answ_three);
//					map.put("ctyh_answ_four", ctyh_answ_four);
//					map.put("ctyh_answ_cr", ctyh_answ_cr);
					list.add(map);
			 }
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
   		System.out.println("读取Excel失败");
   		return null;
//			return new ITextookWResult(2,"文件上传失败！",null);
		}
//		Boolean ckup=tmService.upTYHbyTemp(list,word_cell,tbk_de_id,2,ckid)>0;
//		if (!ckup) {
//			System.out.println("英选汉数据加入正式表失败");
//			return new ITextookWResult(2,"上传失败！请联系管理员。",null);
//		}
		return list;
	}
	
	
	
	public List<Object> readExcelProcess(String fileName) {
//		 TODO Auto-generated method stub
	HashMap<String,List<Map<String, Object>>> hashMap = new HashMap<>();
	HashSet<String> pset = new HashSet<String>();
	HashSet<String> cset = new HashSet<String>();
	HashSet<String> dset = new HashSet<String>();
	HashMap<Object,Object> hashMap3 = new HashMap<>();
	String encode = Base64.encode(fileName.getBytes());
	String file = "distribute_"+0+"_"+encode;
  	SimpleXLSXWorkbook workbook=getFile(ToolUtil.FILEPATH+file);
  	Sheet sheetToRead =workbook.getSheet(0, false);
 	SheetRowReader reader= sheetToRead.newReader();
   	Cell[] row;
      int ckint=0;
		 List<Map<String,Object>> list = new ArrayList<>();
		 try {
			while ((row = reader.readRow()) != null) {
				 if (ckint==0) {
  				 ckint++;
  				 continue;
  			 	}
				 	if (row.length==0) {
						break;
					}
//				 	System.out.println(row.length);
				 	if(row.length==6){
				 		continue;
				 	}
					if (row[0]==null) {
						 continue;
					}
				 		String string = row[0].toString();
				 		if(string==null){
				 			continue;
				 		}    					 
				    int j=0;
	                    //第一个是列数，第二个是行数
				    String ctyh_content="";
              	if(row[j]!=null && row[j].getValue()!=null){
              		ctyh_content=StringUtils.trimKg(row[j].toString());//默认最左边编号也算一列 所以这里得j++
              	}
                  j++;
                  String ctyh_answ_one="";
                  if(row[j]!=null && row[j].getValue()!=null){
                  	ctyh_answ_one=StringUtils.trimKg(row[j].toString());
                  }
                  j++; 
                  String ctyh_answ_two="";
                  if(row[j]!=null && row[j].getValue()!=null){
                  	ctyh_answ_two=StringUtils.trimKg(row[j].toString());
                  }
                  j++; 
                  String ctyh_answ_three="";
                  if(row[j]!=null && row[j].getValue()!=null){
                  	ctyh_answ_three=StringUtils.trimKg(row[j].toString());
                  }
                  j++; 
                  String ctyh_answ_four="";
                  if(row[j]!=null && row[j].getValue()!=null){
                  	ctyh_answ_four=StringUtils.trimKg(row[j].toString());
                  }
                  j++; 
                  String ctyh_answ_cr="";
                  if(row[j]!=null && row[j].getValue()!=null){
                  	ctyh_answ_cr=StringUtils.trimKg(row[j].toString());
                  }
                  j++; 
                  String ctyh_answ_five="";
                  if(row[j]!=null && row[j].getValue()!=null){
                	  ctyh_answ_five=StringUtils.trimKg(row[j].toString());
                  }
                  j++; 
                  String ctyh_answ_six="";
                  if(row[j]!=null && row[j].getValue()!=null){
                	  ctyh_answ_six=StringUtils.trimKg(row[j].toString());
                  }
//                 tmService.uploadTemTYH(new ChoiceYHtopic(0,ctyh_content,ctyh_answ_one,ctyh_answ_two,ctyh_answ_three,ctyh_answ_four,ctyh_answ_cr,word_cell,tbk_de_id,2,ckid));
//                  hashMap.put(key, value)
                  Map<String,Object> map = new HashMap<>();
					map.put("d_province", ctyh_content);
					map.put("d_city", ctyh_answ_one);
					map.put("d_district", ctyh_answ_two);
					map.put("province", ctyh_answ_three);
					map.put("city", ctyh_answ_four);
					map.put("district", ctyh_answ_cr);
					map.put("area", ctyh_answ_five);
					map.put("scale", ctyh_answ_six);
					list.add(map);
					pset.add(ctyh_content);
					cset.add(ctyh_answ_one);
					hashMap3.put(ctyh_content, ctyh_answ_three);
					hashMap3.put(ctyh_answ_one, ctyh_answ_four);
			 }
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
  		System.out.println("读取Excel失败");
  		return null;
//			return new ITextookWResult(2,"文件上传失败！",null);
		}
//		Boolean ckup=tmService.upTYHbyTemp(list,word_cell,tbk_de_id,2,ckid)>0;
//		if (!ckup) {
//			System.out.println("英选汉数据加入正式表失败");
//			return new ITextookWResult(2,"上传失败！请联系管理员。",null);
//		}
//		hashMap.put("province",pset);
//		hashMap.put("city",cset);
		ArrayList<Object> plist = new ArrayList<>();
		
		for (String s : pset) {
			HashMap<Object,Object> hashMap2 = new HashMap<>();
			System.out.println(s);
			System.out.println(hashMap3.get(s));
			hashMap2.put("d_province", s);
			hashMap2.put("province", hashMap3.get(s));
			ArrayList<Object> cList = new ArrayList<>();
			for (String ss : cset) {
				if(s.substring(0,2).equals(ss.substring(0,2))){
					HashMap<Object,Object> hashMap4 = new HashMap<>();
					hashMap4.put("d_city", ss);
					hashMap4.put("city", hashMap3.get(ss));
					List<Map<String,Object>> collect = list.stream().filter(x->x.get("d_city").toString().equals(ss)).collect(Collectors.toList());
					hashMap4.put("districts", collect);
					cList.add(hashMap4);
				}
			}
			hashMap2.put("citys", cList);
			plist.add(hashMap2);
		}
		return plist;
	}


	public  SimpleXLSXWorkbook getFile(String filePath) {
    	return new SimpleXLSXWorkbook(new File(filePath));
  }
	public static void main(String[] args) {
		String string ="工作表";
		String encode = Base64.encode(string.getBytes());
		System.out.println("distribute_"+0+"_"+encode);
	}

}

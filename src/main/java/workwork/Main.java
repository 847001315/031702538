/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package workwork;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
//import org.*;
//import net.*;
//import net.sf.json.JSONObject;


public class Main {
	String nandu;
	String dizhi;
	String name;
	String provine;
	String city;
	String qu;
	String dao;
	String lu;
	String hao;
	String last;
	String number;
	public static void main(String[] args)
	{
		/*Scanner scanner = new Scanner(System.in);
        scanner.next();*/
		
        try {
        	File f = new File(args[0]);
            BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(f), "utf-8"));
            BufferedWriter out =null;
            File file=new File(args[1]);
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter= new OutputStreamWriter(fileOutputStream,StandardCharsets.UTF_8);
            out = new BufferedWriter(outputStreamWriter);
            String l = null;
            int k =0;
            out.write("["+ "\r\n");
            while ((l = r.readLine()) != null) {
            	
            	Main one = new Main();
        		one.input(l);//输入完成
        		one.name_spilt();
        		int end = one.number();
        		one.phone_spilt(end);//完成了对电话的分离
        		one.Provinces();
        		one.buchong();
        		one.City();
        		
        		one.getqu();
        		one.getdao();
        		one.getlu();
        		one.gethao();
        		one.getlast();
        		
        		//one.printjson();
        		if(one.provine.equals("北京")||one.provine.equals("重庆")||one.provine.equals("天津")||one.provine.equals("上海")){
        			one.city = one.city+"市";
        		}else if(one.city==""){
        			one.provine=one.provine+"省";
        		}else{
        			one.provine=one.provine+"省";
        			one.city=one.city+"市";
        		}
        		String s=" ";
        		if(k!=0){
        			if(one.nandu.equals("2!")){
        			 s = ",{\"姓名\":"+"\""+one.name+"\""+",\"手机\":"+"\""+one.number+"\""+",\"地址\":[\""+one.provine+"\",\""+one.city+"\",\""+one.qu+"\",\""+one.dao+"\",\""+one.lu+"\"" 
        	                 +",\""+one.hao+"\",\""+one.last+"\"]}";
        		}else if(one.nandu.equals("1!")){
        			 s = ",{\"姓名\":"+"\""+one.name+"\""+",\"手机\":"+"\""+one.number+"\""+",\"地址\":[\""+one.provine+"\",\""+one.city+"\",\""+one.qu+"\",\""+one.dao+"\",\""+one.lu
        	                 +one.hao+one.last+"\"]}";
        		}
        		}else{
        			if(one.nandu.equals("2!")){
           			 s = "{\"姓名\":"+"\""+one.name+"\""+",\"手机\":"+"\""+one.number+"\""+",\"地址\":[\""+one.provine+"\",\""+one.city+"\",\""+one.qu+"\",\""+one.dao+"\",\""+one.lu+"\"" 
           	                 +",\""+one.hao+"\",\""+one.last+"\"]}";
           		}else if(one.nandu.equals("1!")){
           			 s = "{\"姓名\":"+"\""+one.name+"\""+",\"手机\":"+"\""+one.number+"\""+",\"地址\":[\""+one.provine+"\",\""+one.city+"\",\""+one.qu+"\",\""+one.dao+"\",\""+one.lu
           	                 +one.hao+one.last+"\"]}";
           		}
        			
        		}
        		
        		 k++;
        	   
        			String ou = JsonFormart(s);
        			out.write(ou+ "\r\n");
        			
               

            }
            out.write("]"+ "\r\n");
            out.close();
            r.close();


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //scanner.next();
	}

	private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }
	  public static String JsonFormart(String s) {
	        int level = 0;
	        //存放格式化的json字符串
	        StringBuffer jsonForMatStr = new StringBuffer();
	        for(int index=0;index<s.length();index++)//将字符串中的字符逐个按行输出
	        {
	            //获取s中的每个字符
	            char c = s.charAt(index);
//	          System.out.println(s.charAt(index));
	             
	            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
	            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
	                jsonForMatStr.append(getLevelStr(level));
//	                System.out.println("123"+jsonForMatStr);
	            }
	            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
	            switch (c) {
	            case '{':
	            case '[':
	                jsonForMatStr.append(c + "\n");
	                level++;
	                break;
	            case ',':
	                jsonForMatStr.append(c + "\n");            
	                break;
	            case '}':
	            case ']':
	                jsonForMatStr.append("\n");
	                level--;
	                jsonForMatStr.append(getLevelStr(level));
	                jsonForMatStr.append(c);
	                break;
	            default:
	                jsonForMatStr.append(c);
	                break;
	            }
	        }
	        return jsonForMatStr.toString();
	    }

	private void name_spilt(){
		int i ;
		nandu=dizhi.substring(0,2);
		dizhi=dizhi.substring(2);
		boolean find = false;
		for(i=0;i<dizhi.length();i++){
			if(dizhi.charAt(i)==','){
				find = true;
				break;
			}
		}	
		name = dizhi.substring(0,i);
		dizhi=dizhi.substring(i+1,dizhi.length());
		
	}
	private void buchong(){
		if(provine.equals("北京")  ||provine.equals("重庆")  ||  provine.equals("上海") ||provine.equals("天津")  ){
			city=provine;
		}
	}
	//private void 
	private void printjson(){
		String s=" ";
		if(nandu.equals("2!")){
			 s = "{\"姓名\":"+name+",\"手机\":"+number+",\"地址\":{\""+provine+"\",\""+city+"\",\""+qu+"\",\""+dao+"\",\""+lu+"\"" 
	                 +",\""+hao+"\",\""+last+"\"}}";
		}else if(nandu.equals("1!")){
			 s = "{\"姓名\":"+name+",\"手机\":"+number+",\"地址\":{\""+provine+"\",\""+city+"\",\""+qu+"\",\""+dao+"\",\""+lu
	                 +hao+last+"\"}}";
		}
		 
	   
			String out = JsonFormart(s);
			
		
	}
	public void getlast(){
		int i = 0 ;
		boolean findreally=false;
		if(dizhi.length() ==0){
			last="";
		}else{
			last = dizhi.substring(0,dizhi.length()-1);
		}
	}
	public void gethao(){
		int i = 0 ;
		boolean findreally=false;
		
		for(i = 0;i < dizhi.length() ; i++){
			if(dizhi.charAt(i)=='号' ){
				findreally=true;
				break;
			}
		}
		if(findreally == true){
			
			hao = dizhi.substring(0,i+1);
			dizhi=dizhi.substring(i+1);
			
		}else{
			hao="";
		}
	}
	public void getlu(){
		
		int i = 0 ;
		boolean findreally=false;
		for(i = 0;i < dizhi.length() ; i++){
			if(dizhi.charAt(i)=='路' || dizhi.charAt(i)=='街' || dizhi.charAt(i)=='巷'){
				findreally=true;
				break;
			}
		}
		if(findreally == true){
			lu = dizhi.substring(0,i+1);
			dizhi=dizhi.substring(i+1);

		}else{
			lu="";
		}
	}
	public void getdao(){
		int i = 0 ;
		boolean findreally=false;
		for(i = 0;i < dizhi.length() ; i++){
			if((dizhi.charAt(i)=='街' && dizhi.charAt(i+1)=='道')|| dizhi.charAt(i)=='镇' || dizhi.charAt(i)=='乡'){
				findreally=true;
				break; 
			}
		}
		if(findreally == true){
			if(dizhi.charAt(i+1)=='道'){
				dao=dizhi.substring(0,i+2);
				dizhi=dizhi.substring(i+2);
			}else{
				dao = dizhi.substring(0,i+1);
			dizhi=dizhi.substring(i+1);
			}
			

		}else{
			dao="";
		}
	}
	
	public void getqu(){
		
		int i = 0 ;
		int j =0;
		boolean find=false;
		
			LocalUtil lu =  new LocalUtil();
			
			if(city.equals("") || city==null){
				List<String> list1 =lu.getCities("中国", provine);
				for( j=0;j<list1.size();j++ ){
					List<String> list =lu.getqus("中国",provine,list1.get(j));
					String[] strlist={""};
					for(i=0; i<list.size(); i++){
						strlist =dizhi.split(list.get(i),2);
						if(strlist[0].equals(dizhi)){
						}else{
							find=true;
							break;
						}
					}
					if(find==true){
						qu=list.get(i);
						if(strlist[1].charAt(0)=='市'){
							dizhi=strlist[1].substring(1);
						}else{
							dizhi=strlist[1];
						}
					}
					else {
						qu="";
					}
				}
				
			}else{
				List<String> list =lu.getqus("中国",provine,city);
			String[] strlist={""};
			for(i=0; i<list.size(); i++){
				strlist =dizhi.split(list.get(i),2);
				if(strlist[0].equals(dizhi)){
				}else{
					find=true;
					break;
				}
			}
			if(find==true){
				qu=list.get(i);
				if(strlist[1].charAt(0)=='市'){
					dizhi=strlist[1].substring(1);
				}else{
					dizhi=strlist[1];
				}
			}
			else {
				qu="";
			}
			}
			
			
		
	}
	//获得城市
	public void City(){
		if(city!=null){
			if(dizhi.charAt(0)=='市'){
				
				dizhi=dizhi.substring(1);
			}
		}else{
			int i = 0 ;
			boolean find=false;
				LocalUtil lu =  new LocalUtil();
				List<String> list =lu.getCities("中国",provine);
				
				String[] strlist={""};
				for(i=0; i<list.size(); i++){
					strlist =dizhi.split(list.get(i),2);
					if(strlist[0].equals(dizhi)){
					}else{
						find=true;
						break;
					}
				}
				if(find==true){
					city=list.get(i);
					if(strlist[1].charAt(0)=='市'){
						dizhi=strlist[1].substring(1);
					}else{
						dizhi=strlist[1];
					}
				}else{
					city="";
				}
		}
		
	}
	public void Provinces(){
		//先将地址进行处理，得到前两位和前三位分别两个。如果第一个成立则不运行第二个，反之运行第二个。
		String dizhi2,dizhi3;
		boolean two=false;
		dizhi2 = dizhi.substring(0,2);
		dizhi3 = dizhi.substring(0, 3);
		LocalUtil lu =  LocalUtil.getInstance();
		List<String> list = 	lu.getProvinces("中国");
		for(int i=0; i<list.size(); i++){
			if(dizhi2.equals(list.get(i))){
				two = true;
				provine=dizhi2;
				
				break;
			}
		}
		if(two == false){
			
			provine = dizhi3;
			 
		}
		if(two == true){
			String k = dizhi.substring(2,3);
			if(k.equals("省")){
				dizhi = dizhi.substring(3);
			}
			else{
				dizhi=dizhi.substring(2);
			}
		}else{
			String k = dizhi.substring(3,4);
			if(k.equals("省")){
				dizhi = dizhi.substring(4);
			}
			else{
				dizhi=dizhi.substring(3);
		
			}
		}
		
	}
	public void input(String s){
		
		dizhi = s;
	}
	public int  number(){
		int i = dizhi.length();
		char ss[] = dizhi.toCharArray();
		int begin=0;
		int end=0;
		int k=0;
		for(i = 0 ; i < dizhi.length();i++){
			if(ss[i] >= '0' && ss[i] <='9'){
				k++;
			}else{
				k=0;
			}
			if(k==11){
				end = i;
				break;
			}
		}
		return end;
	}
	public void phone_spilt(int end){
		String k;
		
		k = dizhi.substring(end-10,end+1);
		
		
		number = k;
		String[] strlist = dizhi.split(k);
		dizhi = strlist[0]+strlist[1];
	}
}

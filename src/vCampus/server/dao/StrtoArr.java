package vCampus.server.dao;

import java.util.ArrayList;

public class StrtoArr {
	
	public static ArrayList<Integer> strtoArr(String str){
		ArrayList<Integer> gs = new ArrayList<Integer>();
		if( str.length()==0||str.isEmpty()||str.equals("") || str == null) return null;
		else{
			String[] str_list = str.split(",");
			for(int i = 0;i < str_list.length; i ++){
				gs.add(Integer.parseInt(str_list[i]));
			}
			return gs;
		}
	}
	
	
	public static String arrtoStr(ArrayList<Integer> s){
		ArrayList<String> strList = new ArrayList<String>(); 
        for(int i = 0;i < s.size();i ++){
        	strList.add(Integer.toString(s.get(i)));
        }
        String b = String.join(",", strList);
        return b;
	}
	/*
	public static void main(String args[]){
		System.out.println(strtoArr("100,100,111"));
		ArrayList<Integer> gs = new ArrayList<Integer>();
		gs.add(100);
		gs.add(30);
		System.out.println(arrtoStr(gs));
	}
	*/
}

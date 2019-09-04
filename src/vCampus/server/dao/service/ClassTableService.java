package vCampus.server.dao.service;
import vCampus.server.dao.*;
import vCampus.server.dao.model.*;

import java.sql.SQLException;
import java.util.ArrayList;
import vCampus.server.dao.StrtoArr;

public class ClassTableService {
	public static ArrayList<ArrayList<String>> getClassTable(int personID, int week) throws SQLException{
		Student s = new Student();
		s = StudentDao.getStu(personID);
		ArrayList<Integer> idClass =  StrtoArr.strtoArr(s.getTimeTable());
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		
		for(int i = 0; i < idClass.size(); i++){
			
			Lesson tmp = new Lesson();
			tmp = LessonsDao.getRec(idClass.get(i));
			ArrayList<Integer> weeks = tmp.GetTimeTable();
			
			if( weeks.get(0) <= week && weeks.get(1) >= week ){
				for(int j = 2;j < weeks.size();){
					int weekday,start,end;
					if (weeks.get(j) < 100) {
						weekday = weeks.get(j) / 10;
						start = weeks.get(j) - weekday * 10;
						end = weeks.get(j + 1) - weekday * 10;
						j += 2;
					} else {
						weekday = weeks.get(j) / 100;
						start = weeks.get(j) - weekday * 100;
						end = weeks.get(j + 1) - weekday * 100;
						j += 2;
					}
					ArrayList<String> item =new ArrayList<String>();
					item.add(tmp.getLessonName());
					item.add(tmp.getLocation());
					item.add(Integer.toString(weekday));
					item.add(Integer.toString(start));
					item.add(Integer.toString(end));
					list.add(item);
				}
			}				
		}
		return list;
		
	}
	
	//for test
	public static void  main(String[] args) throws SQLException{
		ArrayList<ArrayList<String>> list = getClassTable(1,3);
		for(int i = 0;i < list.size();i ++)
			for(int j = 0; j < list.get(i).size(); j++)
				System.out.println(list.get(i).get(j));
	}
	
}

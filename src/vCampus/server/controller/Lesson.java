package vCampus.server.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import vCampus.bean.LessonBean;
import vCampus.bean.LessonTime;
import vCampus.server.ServerMain;
import vCampus.server.dao.CourseGradeDao;
import vCampus.server.dao.LessonsDao;
import vCampus.server.dao.TeacherDao;
import vCampus.server.dao.model.LessonSelectRec;
import vCampus.server.dao.model.Teacher;
import vCampus.utility.MyDate;
import vCampus.utility.Token;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;

public class Lesson {
	
	private static int getUserId(Message msg) {
		int userId=(int)msg.getData().get("userId");
		if(userId==-2) userId=((Token) msg.getData().get("token")).getUserId();
		return userId;
	}
	
	private static ArrayList<ArrayList<ArrayList<Boolean>>> getVisCube(int userId){
		ArrayList<vCampus.server.dao.model.Lesson> 
			stuLessons=LessonsDao.queryLesson4Stu(userId);
		ArrayList<ArrayList<ArrayList<Boolean>>> visCube=new ArrayList<ArrayList<ArrayList<Boolean>>>();
		//init visCube[1~16][1~5][1~13] all grid to false
		for(int i=0;i<=16;i++) {
			visCube.add(new ArrayList<ArrayList<Boolean>>());
			for(int j=0;j<=5;j++){
				visCube.get(i).add(new ArrayList<Boolean>());
				for(int k=0;k<=13;k++) {
					visCube.get(i).get(j).add(false);
				}
			}
		}
		for(vCampus.server.dao.model.Lesson c:stuLessons) {
			for(LessonTime t:c.getTimeTable()) {
				for(int i=c.getStartWeek();i<=c.getEndWeek();i++) {
					for(int k=t.getStart();k<=t.getEnd();k++) {
						visCube.get(i).get(t.getDay()).set(k, true);
					}
				}
			}
		}
		return visCube;
	}
	
	private static boolean checkCollision(ArrayList<ArrayList<ArrayList<Boolean>>> visCube,
			vCampus.server.dao.model.Lesson c) {
		int S=c.getStartWeek(),T=c.getEndWeek();
		for(int i=S;i<=T;i++) {
			for(LessonTime t:c.getTimeTable()) {
				for(int k=t.getStart();k<=t.getEnd();k++)
					if(visCube.get(i).get(t.getDay()).get(k)==true) {
						//JOptionPane.showMessageDialog(null, "课程冲突！");
						Config.log("判定冲突:");
						Config.log(c.getLessonName());
						Config.log("第"+i+"周,星期"+t.getDay()+",第"+k+"节");
						return true;
					}	
			}
		}
		return false;
	}
	
	static {
		ServerMain.addRequestListener("lesson/selectLesson", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				int userId=getUserId(msg);
				int lessonId=(int) msg.getData().get("lessonId");
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				LessonSelectRec rc=new LessonSelectRec();
				rc.setUserId(userId);
				rc.setLessonId(lessonId);
				rc.setSelectTime(MyDate.nowOnDay(0));
				if(LessonsDao.addLessonSelectRec(rc)) {
					data.put("code",200);
					CourseGradeDao.addCourse(userId, lessonId);
					data.put("message", "选课成功!");
				}else {
					data.put("code",200);
					data.put("error", "选课失败!");
				}

				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("lesson/cancelLesson", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				int userId=getUserId(msg);
				int lessonId=(int) msg.getData().get("lessonId");
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				LessonsDao.removeLessonSelectRec(userId,lessonId);
				
				data.put("code",200);
				data.put("message", "已取消选择!");
				try {
					CourseGradeDao.deleCourse(userId, lessonId);
				} catch (SQLException e) {
					Config.log(e);
				}
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("lesson/queryAllLessons", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				int userId=getUserId(msg);
				Map<String, Object> data = new HashMap<String, Object>();
				
				ArrayList<vCampus.server.dao.model.Lesson> lessonList=LessonsDao.queryAllLessons();
				
				ArrayList<String> nameList = new ArrayList<String>();
				for(vCampus.server.dao.model.Lesson a : lessonList){
					
					try {
						Teacher t = TeacherDao.getTeach(a.getTeacherId());
						nameList.add(t.getName());
					} catch (SQLException e) {
						Config.log(e);
						nameList.add("");
					}
					
				}
				
				ArrayList<ArrayList<ArrayList<Boolean>>> visCube
					=Lesson.getVisCube(userId);
				
				ArrayList<LessonBean> beanList=new ArrayList<LessonBean>();
				for(vCampus.server.dao.model.Lesson c:lessonList) {
					if(LessonsDao.hasLessonSelectRec(userId, c.getID())) {
						c.setStatus("已选");
					}else if(c.getCurNum()>=c.getMaxNum()) {
						c.setStatus("已满");
					}else if(Lesson.checkCollision(visCube, c)) {
						c.setStatus("冲突");
					}else {
						c.setStatus("可选");
					}
					beanList.add(c.toBean());
				}
				
				data.put("nameList", nameList);
				data.put("lessonList", beanList);
				data.put("code",200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		ServerMain.addRequestListener("lesson/queryCourseTable4Week", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				int week=(int) msg.getData().get("week");
				int userId=getUserId(msg);
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				ArrayList<vCampus.server.dao.model.Lesson> lessonList
				=LessonsDao.queryLesson4Stu(userId);
				
				//fool
				ArrayList<LessonTime> beanList=new ArrayList<LessonTime>();
				for(vCampus.server.dao.model.Lesson c:lessonList) {
					if(c.getStartWeek()<=week&&week<=c.getEndWeek()) {
						for(LessonTime t:c.getTimeTable())
							beanList.add(t);
					}
				}
				
				data.put("timeList", beanList);
				
				data.put("code",200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		
		ServerMain.addRequestListener("lesson/queryStuLessons", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
				
				int userId=getUserId(msg);
				
				Map<String, Object> data = new HashMap<String, Object>();
				
				ArrayList<vCampus.server.dao.model.Lesson> lessonList
				=LessonsDao.queryLesson4Stu(userId);

				ArrayList<String> nameList = new ArrayList<String>();
				for(vCampus.server.dao.model.Lesson a : lessonList){
					
					try {
						Teacher t = TeacherDao.getTeach(a.getTeacherId());
						nameList.add(t.getName());
					} catch (SQLException e) {
						Config.log(e);
						nameList.add("");
					}
					
				}
				//fool
				ArrayList<LessonBean> beanList=new ArrayList<LessonBean>();
				for(vCampus.server.dao.model.Lesson c:lessonList) {
					beanList.add(c.toBean());
				}
				
				data.put("lessonList", beanList);
				data.put("nameList", nameList);
				data.put("code",200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
	}
	
	
	
}

package vCampus.client.controller;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import vCampus.bean.LessonBean;
import vCampus.bean.LessonTime;
import vCampus.client.ClientMain;
import vCampus.client.view.lesson.CourseTablePanel;
import vCampus.client.view.lesson.GradePanel;
import vCampus.client.view.lesson.LessonSelectPanel;
import vCampus.client.view.utility.Refreshable;
import vCampus.utility.Config;
import vCampus.utility.loop.LoopOnceAdapter;
import vCampus.utility.loop.Message;


public class Lesson {
	
	private static int getUserId() {
		return -2;
	}
	
	private static void prompt(Object p,Message msg) {
		if(msg.getData().containsKey("message")) {
			JOptionPane.showMessageDialog((JPanel)p, (String)msg.getData().get("message"), "",JOptionPane.INFORMATION_MESSAGE); 
		}
		if(msg.getData().containsKey("error")) {
			JOptionPane.showMessageDialog((JPanel)p, (String)msg.getData().get("error"), "",JOptionPane.WARNING_MESSAGE); 
		}
	}
	
	public static void selectLesson(Refreshable p,int lessonId) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("userId", getUserId());
		data.put("lessonId", lessonId);
		
		Message msg = new Message("lesson/selectLesson", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					p.refresh();
				}else {
					//TODO
					Config.log("selectLesson fail " + code);
				}
			}
		});
	}
	public static void cancelLesson(Refreshable p,int lessonId) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("userId", getUserId());
		data.put("lessonId", lessonId);
		
		Message msg = new Message("lesson/cancelLesson", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					p.refresh();
				}else {
					//TODO
					Config.log("cancelLesson fail " + code);
				}
			}
		});
	}
	public static void queryAllLessons(LessonSelectPanel p) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", getUserId());
		Message msg = new Message("lesson/queryAllLessons", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					ArrayList<LessonBean> lessonList=(ArrayList<LessonBean>) msg.getData().get("lessonList");
					ArrayList<String>  nameList = (ArrayList<String>) msg.getData().get("nameList");
					p.setLessonTable(lessonList, nameList);
				}else {
					//TODO
					Config.log("queryAllLessons fail " + code);
				}
			}
		});
	}
	public static void queryCourseTable4Week(CourseTablePanel p,int week) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("userId", getUserId());
		data.put("week", week);
		
		Message msg = new Message("lesson/queryCourseTable4Week", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					
					ArrayList<LessonTime> timeList=(ArrayList<LessonTime>) msg.getData().get("timeList");
					p.setCourseTable(timeList);
				}else {
					//TODO
					Config.log("queryCourseTable4Week fail " + code);
				}
			}
		});
	}
	public static void queryStuLessons(GradePanel p) {
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("userId", getUserId());
		
		Message msg = new Message("lesson/queryStuLessons", data);
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				int code = (int) msg.getData().get("code");
				prompt(p,msg);
				if (code == 200) {
					ArrayList<String>  nameList = (ArrayList<String>) msg.getData().get("nameList");
					ArrayList<LessonBean> lessonList=(ArrayList<LessonBean>) msg.getData().get("lessonList");
					p.setCourseTable(lessonList, nameList);
				}else {
					//TODO
					Config.log("queryStuLessons fail " + code);
				}
			}
		});
	}
}

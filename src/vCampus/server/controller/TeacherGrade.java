package vCampus.server.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import vCampus.utility.Token;
import vCampus.utility.loop.LoopAlwaysAdapter;
import vCampus.utility.loop.Message;
import vCampus.utility.socket.ResponseSender;
import vCampus.server.dao.*;
import vCampus.server.dao.model.*;
import vCampus.server.*;

public class TeacherGrade {	
	static {

		ServerMain.addRequestListener("TeacherGrade/get_course_list", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
								
                Token token=(Token) msg.getData().get("token");
                int courseId= (Integer)msg.getData().get("courseId");              
                Map<String, Object> data = new HashMap<String, Object>();                               
                vCampus.server.dao.model.Lesson t;
                Student s;
                Object[][] object;
				try {
					t= LessonsDao.queryLesson(courseId);
					ArrayList<Integer> stuList = LessonsDao.queryStudentIds4Lesson(t);
					object = new Object[stuList.size()+1][3];
					for (int i = 0; i < stuList.size(); i++) {
						s = StudentDao.getStu(stuList.get(i));
						int grade = CourseGradeDao.queryGrade(stuList.get(i), courseId);
						object[i][0] = s.getId();
						object[i][1] = s.getName();
						object[i][2] = grade;						
					}
					object[stuList.size()][0] =  object[stuList.size()][1] = object[stuList.size()][2] =  null;
					data.put("object", object);
					data.put("code", 200);
				} catch (SQLException e) {
					Config.log(e);
					data.put("code", 401);
				}		
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});
		
		
		ServerMain.addRequestListener("TeacherGrade/get_grade_list", new LoopAlwaysAdapter() {
			@Override
			public boolean resolveMessage(Message msg, Map<String, Object> transferData) {
								
                Token token=(Token) msg.getData().get("token");
                int courseId= (Integer)msg.getData().get("courseId");              
                Map<Integer, Integer> gradeList = (Map<Integer, Integer>)msg.getData().get("gradeList");  
                Map<String, Object> data = new HashMap<String, Object>();
                for(int i: gradeList.keySet()){
                	Config.log(i);
                	Config.log(gradeList.get(i));
                	int changeRow = CourseGradeDao.updateGrade(i, courseId, gradeList.get(i));
                	if(changeRow <= 0)
                		data.put("code", 401);
                }
                data.put("code", 200);
				((ResponseSender) transferData.get("sender")).send(data);
				return true;
			}			
		});		
		
	}
}
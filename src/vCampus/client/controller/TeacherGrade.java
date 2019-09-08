package vCampus.client.controller;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import vCampus.client.ClientMain;
import vCampus.client.view.*;
import vCampus.utility.loop.*;
public class TeacherGrade {

	public static Object[][] object;
    public static void getCourseList(int courseId) {
    	 
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("token", ClientMain.getTempData().get("token"));
        data.put("courseId", courseId);
        Message msg = new Message("TeacherGrade/get_course_list", data);
        System.out.println(courseId);
        
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = 200 == ((int) msg.getData().get("code"));                 
				if (flag) {
					//成功
					System.out.println("Succeed to update!");
				    GradeListFrame jf = new GradeListFrame( (Object[][]) msg.getData().get("object"), courseId);
				}else {
					//失败
					System.out.println("Failed to update!");
				}
                                       
                            
            }
        });	

    }	
	
    public static void getGradeList(int courseId, Map<Integer, Integer> gradeList) {
   	 
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("token", ClientMain.getTempData().get("token"));
        data.put("courseId", courseId);
        data.put("gradeList", gradeList);
        Message msg = new Message("TeacherGrade/get_grade_list", data);    
        //define a callback
		ClientMain.getSocketLoop().sendMsgWithCallBack(msg, new LoopOnceAdapter() {
			@Override
			public void resolveMessageForSwing(Message msg, Map<String, Object> transferData) {
				boolean flag = 200 == ((int) msg.getData().get("code"));                 
				if (flag) {
					JOptionPane.showMessageDialog(null, "修改成功！");
					System.out.println("Succeed to update!");
				}else {
					JOptionPane.showMessageDialog(null, "修改失败！");
					System.out.println("Failed to update!");
				}
                                       
                            
            }
        });	

    }		
}

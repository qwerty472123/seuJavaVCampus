package vCampus.server.dao.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import vCampus.server.dao.AptRecsDao;
import vCampus.server.dao.DoctorsDao;
import vCampus.server.dao.model.Doctor;

public class DoctorTableService {
	//查询某一时间有空的医生的id名单
	public static List<Doctor> getAvailableDoctor(Integer time){
		List<Doctor> drlist = new ArrayList<Doctor>();
		List<Integer> allid = DoctorsDao.allId();
		for(Integer id : allid) {
			if(DoctorsDao.queryAvailableTime(id).get(time) != 0) {	//查询时间的医生可接待人数不为0
				Doctor d = new Doctor();
				d.setId(id);
				d.setName(DoctorsDao.queryName(id));
				d.setIntrotxt(DoctorsDao.queryIntroTxt(id));
				drlist.add(d);
			}
		}
		return drlist;
	}
	public static int queryAvailableNum(int doctorid, int time) {
		time = (time/10-1) *2 +time % 10;
		return DoctorsDao.queryAvailableTime(doctorid).get(time);
	}
	public static int queryAptNum(int doctorid, Date aptday) {
		return AptRecsDao.queryAptByDoctorAndTime(doctorid, aptday).size();
	}
}

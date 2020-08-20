package service;

import Session.UserSession;
import dao.MileageDao;



public class MileageService {
	
	
	MileageDao mil = new MileageDao();
	UserSession session = new UserSession();
	String sessionId = session.loginUserId; 
		
	//다오에서 추출한 마일리지 값 가져오기
	public void MileageChk(){
		System.out.println(session.loginUserId + "님의 마일리지는" + mil.idChk() + "점입니다.");
	
	}
	
	
	
}	
	
	
	
	
	//Dao에서 추출한 mileage 값 가져오기
		//-> 출력
package dao;

import service.MileageService;
import vo.UserVo;
import Session.UserSession;
import database.Database;



public class MileageDao {
	
	Database db = Database.getInstance();
	UserSession session = new UserSession();
	
/*	//세션 아이디와 db의 userId와 비교하긔 
	//아니 비고할 필요가 없자나
	public boolean idChk(String userId){
		boolean isNotFind = false;
			for(int i = 0; i <db.userList.size(); i++){
				//session의 로그인 유저가 찾은 아이디와 같다면 
				//
				if(!userId.equals(db.userList.get(i).getUserId())){
					isNotFind = true;
					break;
				}
			}
	} //일치하면 true 
	
	
	//id에 맞는 포인트 찾기  
			//id맞는거 = > 포인트 연결 //이건 서비스에서 해야하나...? 
	
	//마일리지 찾기 
	public int melieageChk(boolean idChk, int userMileage){
		boolean isNotFind = false;
		for(int i = 0; i < db.userList.size(); i++){
			boolean mchk = false;
			if(!UserMileage != 0){
				
			}
				
			}
		} return isNotFind;
	*/

	
	//--------------------------------------------------

	//session에서 userid 가져오기 & id 찾기
	public int idChk(){
		//MileageService msv = new MileageService();
		
		
		String sessionId = session.loginUserId; //세션에서 가져옴
		
		//userList의 id와 비교하긔
		for(int i = 0; i< db.userList.size(); i++){
			
			UserVo userid = db.userList.get(i);
			if(sessionId.equals(userid.getUserId())){
				// db.userList.get(db.userList.get(userMileage));
				return userid.getUserMileage(); //마일리지 찾는 메서드 가져오기
			}
		}
		return 0;
		
		
	}
	
	
	
}
	
	
	

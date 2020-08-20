package Z_project;

import java.util.ArrayList;
import java.util.Scanner;

import vo.UserVo;
import database.Database;

public class UserDao {

	private static UserDao instance;

	private UserDao() {
	} // 생성자

	public static UserDao getInstance() {
		if (instance == null) {
			instance = new UserDao();
		}
		return instance;
	}

	// 싱글톤

	Database database = Database.getInstance();

	Scanner s = new Scanner(System.in); // 입력받으려고

	// 아이디 중복검사 userType 별로 나눠서

	public UserVo selectUser(UserVo param){
		UserVo rtnUser = null;
		for(int i = 0; i <database.userList.size(); i++){
			UserVo user = database.userList.get(i);
			
			System.out.println("유저타입을 입력해주세요");
			System.out.println("개인회원은 1번 , 기업회원은 2번 입니다.");
			//service 클래스에 있으니 없어도 되나?
			
			
			int userType = Integer.parseInt(s.nextLine());
			
			//중복검사> userType 별로 나눠서
			boolean flag = true;
			
			switch(userType){
				
				
				case 1 : //개인회원일때
					
					for(int j = 0; j < database.userList.size(); j++)
					{
						//아이디 중복검사
						if(param.getUserId() != null){
							if(!param.getUserId().equals(user.getUserId()));
							flag= false;
						}
						
						//비밀번호 중복검사
						if(param.getUserPass() != null){
							if(!param.getUserPass().equals(user.getUserPass()));
							flag = false;
						}
						
						
						
					}
					
				case 2 : //기업회원일때
					
					for(int j = 0; j < database.userList.size(); j++)
					{
						//아이디 중복검사
						if(param.getUserId() != null){
							if(!param.getUserId().equals(user.getUserId()));
							flag = false;//다르면 flag를 false로!
						}
						
						//비밀번호 중복검사
						if(param.getUserPass() != null){
							if(!param.getUserPass().equals(user.getUserPass()));
							flag = false;
						}
						
						//사업자 등록번호 중복검사
						if(param.getCoNum() != null){
							if(!param.getCoNum().equals(user.getCoNum()));
							flag = false;
						}
						
						//사업자 전화번호 중복검사
						if(param.getCoTel() != null){
							if(!param.getCoTel().equals(user.getCoTel()));
							flag = false;
						}
						
						
						
						
					}
			
					if(flag){
						rtnUser = user;
						break;
					}
			
				}//중복검사 종료
				
			}
		return rtnUser;			
	}
	
	public void insertUser(UserVo user){
		database.userList.add(user);
	}	
	
	public ArrayList <UserVo> selectUserList(){
//		database.settingStart(id); //id 를 파라미터 값으로 보내주는 거 일케 하는거 아냐?
		return database.userList;
	}

	public static Z_project.UserVo slectUserList(Z_project.UserVo user) {
		// TODO Auto-generated method stub
		return null;
	}
	

}

package Z_project;

import java.util.Scanner;
import java.util.regex.Pattern;

import Session.UserSession;

public class UserService {
	
	private static UserService instance;
	private UserService(){}
	public static UserService getInstance(){
		if(instance == null){
			instance = new UserService();
		}
		return instance;
	}//싱글톤
	
	
	
	Scanner s = new Scanner(System.in);
	private UserDao userDao = UserDao.getInstance();
	UserVo user = new UserVo();
	
	
	//회원가입
	public void join(){
		
		
		//userType 선택
		System.out.println("유저타입을 입력해주세요");
		System.out.println("개인회원은 1번 , 기업회원은 2번 입니다.");
		int userType = Integer.parseInt(s.nextLine());
		
		UserVo userChk;
		boolean chk = false;
		chk = false;
		
		
		
		do{
		
				//사용자 정보입력
				System.out.println("아이디 : ");
				String id = s.nextLine();
				user.setId(id);
				user();//user변수생성 어케하지 
				
				
				//아이디 ㅈㅇ복검사
				userChk = userDao.selectUserList(user);
				
				
				//없으면 회원가입
				if(userChk == null){
					
					
					
					//id
					//id 정규식
					for(;;)
					{
						String idRegex = "[/^A-Za-z][A-Za-z0-9]{5, 15}";
						boolean idChk = Pattern.matches(idRegex, id);
						System.out.println(idChk);
						if(idChk == true){
							System.out.println("계속진행하세요");
							chk = true;
							break;
						}else{
							System.out.println("아이디를 다시 입력해주세요");
							continue;
						}
					}
					
					
					//비밀번호
					//pass 정규식
					for(;;)
					{
						System.out.println("비밀번호 : ");
						String pass = s.nextLine();
						
						user.setPass(pass);
						
						String passRegex = "[a-zA-Z0-9!@#$%^&^*_]";
						boolean passChk = Pattern.matches(passRegex, pass);
						System.out.println(passChk);
						
						if(passChk == true){
							System.out.println("계속 진행해주십시오");
							chk = true;
						}else{
							System.out.println("비밀번호를 다시 입력해주세요");
						}
						
						
					}
					
					System.out.println("이름");
					String name = s.nextLine();
					System.out.println("핸드폰번호");
					String phone = s.nextLine();
					
					user.getName(name);
					user.getPhone(phone);
					
					
				}
		}while(userType == 2){
					
					
				
				
				//없으면 회원가입
//			\	if(userChk == null){
//					
//					boolean chk = false;
//					chk = false;
//					
//					//id 정규식
//\					
					
					System.out.println("이름 : ");
					String name = s.nextLine();
					
					System.out.println("전화번호 : ");
					String phone = s.nextLine();
					
					user.setCoName(name);;
					user.setPass(phone);
				
					
					System.out.println("업체 이름 : ");
					String coName = s.nextLine();
					System.out.println("업체 전화번호 : ");
					String coTel = s.nextLine();
					System.out.println("업체 주소 :");
					String coAddr = s.nextLine();
					
					//입력받은 것저장
					user.setCoName(coName);
					user.setCoTel(coTel);
					user.setCoAddr(coAddr);
					
					
					//사업자 등록번호 정규식
					for(;;)
					{
						coAddr = s.nextLine();
						System.out.println("사업자 등록번호");
						System.out.println("예 : 000-00-00000");
						
						String coNum = s.nextLine();
						
						String coNumRegex = "[0-9]{3}[0-9]{2}[0-9]{5}";
						boolean coNumChk = Pattern.matches(coNumRegex, coNum);
						//System.out.println(coNumChk);
						
						if(coNumChk){
							System.out.println("계속진행하세요");
							chk = true;
							break;
						}else{
							System.out.println("사업자 등록번호를 다시 입력하세요");
						}
						
					}
			}
					
					UserVo userCheak = UserDao.slectUserList(user);
					
					//없으면 로그인 실패
					
					
					if(userChk == null){
						System.out.println("아이디 혹은 비밀번호를 잘 못 입력하셨습니다.");
					}else{
						//있으면 로그인
						System.out.println("로그인 성공!");
						System.out.println(userChk.getName() + "님 환영합니다");
						UserSession.loginUserId = userChk;
						//세션에 담아준다
					}
					
					
					
				
				
				
			
		
		
		
		
		
		
	}
	
	
	
	
/*	public void userJoin{
		
		
	}
	
	public void companyJoin{
		
	}*/
	
	
	
}

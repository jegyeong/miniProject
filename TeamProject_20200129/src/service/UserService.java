package service;

import java.util.Scanner;
import java.util.regex.Pattern;

import vo.UserVo;
import Session.UserSession;
import dao.UserDao;
import database.Database;

public class UserService {

//	public void login() {
//		// TODO Auto-generated method stub
//		UserSession session = new UserSession();
//		session.loginUserId = "wooki89";
//		session.loginUserPass = "sjsk1313!#";
//		session.loginUserType = 1;
//	}


//	private static UserService instance;
//	private UserService(){ }
//	public static UserService getInstance(){
//		if(instance==null) {instance=new UserService();} return instance; }
	
	UserVo uvo;
	UserSession session = new UserSession();
	Database db = Database.getInstance();
	Scanner sc = new Scanner(System.in);
	boolean isCorrect = false;
	int userType = 0;
	public boolean chkPatten (String input,int mode){
		boolean regexChk = false;
		
		switch(mode){
		case 1:
			String userIdRegex = "^[a-z0-9_]{5,15}\\W{0}$"; //소문자,숫자 5~15자리&특수문자x
			boolean userIdChk = Pattern.matches(userIdRegex, input);
			if(userIdChk==true)
				regexChk = true; break;
		case 2:
			String userPassRegex ="(?=.*[A-Za-z\\d])(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{10,20}";//대소문자,숫자 10~20자리&특수문자 1개이상포함
			boolean userPassChk = Pattern.matches(userPassRegex,input);
			if(userPassChk==true)
				regexChk = true; break;
		case 3:
			String userNameRegex = "^[가-힣]{2,5}$";//한글3~4자,특수문자x
			boolean userNameChk = Pattern.matches(userNameRegex, input);
			if(userNameChk==true)
				regexChk = true; break;
		case 4:
			String userPhoneRegex = "^(02|010|070)-\\d{3,4}-\\d{3,4}|(02|010|070)\\d{3,4}\\d{3,4}$";
			boolean userPhoneChk = Pattern.matches(userPhoneRegex, input);
			if(userPhoneChk==true)
				regexChk = true; break;
		default :
			break;
		}
		return regexChk;
		
	}
	
	private int selectUserType() {
		boolean isSelect = false;
		do {
		System.out.println("당신은 일반 사용자 입니까 사업자 입니까?");
		System.out.println("일반 사용자라면 1번을, 사업자라면 2번을 입력해 주세요.");
		int typeInt = Integer.parseInt(sc.nextLine());
		if(typeInt>=1 &&typeInt<=2) {
			if(typeInt == 1) {
				System.out.println("일반사용자를 선택 하셨습니다.");
			}else {
				System.out.println("사업자를 선택 하셨습니다.");
			}
			isSelect = true;
			return typeInt;
		}else {
			System.out.println("다시 입력해 주세요.");
		}
		return typeInt;
		}while(!isSelect);
	}
	//아이디
	public void createId(){
		int userType = selectUserType();
		do{
			isCorrect = false;
			uvo = new UserVo();
			
			
			if(userType==1) {
				uvo.setUserType(userType);
				System.out.println("\n 아이디를 입력해 주세요 \n");
				System.out.print("아이디 : ");
				String userId = sc.nextLine();
				chkPatten(userId,1);	
				
				if(chkPatten(userId,1)==true){ //패턴과 맞으면
					boolean userChk = udao.checkUserId(userId); //아이디 중복체크
					if(userChk== false){ //아이디 중복 X
						
						uvo.setUserId(userId);
						isCorrect = true;
						System.out.println("입력 완료\n");
					}
					else{ //아이디 중복 O
						System.out.println("이미 사용중인 아이디입니다.");
					}
					
				}
				else{ //입력 오류
					System.out.println("5~15자리의 영문소문자,숫자만 사용 가능합니다.(특수문자 사용불가)");
				}
			}else {
				//사업자
				
			}
			
			
		}while(!isCorrect);		
	}
	
	//비밀번호
	private void createPass(){
		do{
			isCorrect = false;
			if(uvo.getUserType()==1) {
				System.out.println("\n 사용하실 비밀번호를 입력해 주세요 \n");
				System.out.print("비밀번호 : ");
				String userPass = sc.nextLine();
				chkPatten(userPass,2);
				
				if(chkPatten(userPass,2)==true){
					uvo.setUserPass(userPass);
					isCorrect = true;
					System.out.println("입력 완료\n");
				}
				else{
					System.out.println("10~20자리의 영문 대소문자와 숫자, 특수기호 1개이상만 사용 가능합니다.");
				}
			}else {
				
				//사업자
				
				
				
			}
			
			
		}while(!isCorrect);		
	}
	
	
	//이름
	private void createName(){
		do{
			isCorrect = false;
			if(uvo.getUserType()==1) {
				System.out.println("\n 사용자 이름을 입력해 주세요 \n");
				System.out.print("이름 : ");
				String userName = sc.nextLine();
				chkPatten(userName,3);
				
				if(chkPatten(userName,3)==true){
					uvo.setUserName(userName);
					isCorrect = true;
					System.out.println("입력 완료\n");
				}
				else{
					System.out.println("한글만 사용가능합니다.");
				}
			}else {
				
				//사업자
				
				
				
			}
			
		}while(!isCorrect);		
	}
	
	
	//전화번호
	private void createPhone(){
		do{
			isCorrect = false;
			if(uvo.getUserType()==1) {
				System.out.println("\n 사용하시는 전화번호를 입력해 주세요 \n");
				System.out.print("전화번호 : ");
				String userPhone = sc.nextLine();
				chkPatten(userPhone,4);
				if(chkPatten(userPhone,4)==true){
					userPhone = userPhone.replace("-", "");
					uvo.setUserPhone(userPhone);
					uvo.setUserMileage(0);
					udao.insertUser(uvo);
					System.out.println("인증 완료\n");
					
					isCorrect=true;
				}
				else{
					System.out.println("올바른 형식의 번호가 아닙니다.");
				}
			}else {
				//사업자
				
				
			}
			
		}while(!isCorrect);		
	}
	
	
	//사용자회원가입
	public void signUp(){
		
		System.out.println("========회원가입========");
		
		createId();
		createPass();
		createName();
		createPhone();
		System.out.println("== 회원가입이 완료되었습니다 ==\n 메인 화면으로 이동합니다. ");
		
	}
	//로그인
	public void logIn(){
		
		//아이디,비밀번호 입력
		System.out.println("======== 로그인  ========");
		System.out.print("아이디 : ");
		String userId = sc.nextLine();
		System.out.print("비밀번호 : ");
		String userPass = sc.nextLine();
		
		UserVo user = new UserVo();
		user.setUserId(userId);
		user.setUserPass(userPass);
		
		boolean userLoginChecked = udao.checkLoginuser(userId,userPass);
		
		if(userLoginChecked == false){			
			System.out.println("아이디 혹은 비밀번호를 잘못 입력하셨습니다.\n");
		}
		else{
			System.out.println("\n로그인 성공!!");
			//패스워드 확인 로직 구현
			System.out.println(userId+"님 환영합니다 :)\n");
			// UserList 에서 똑같은 아이디와 패스워드를 가진 인덱스 를 가져와서 세팅
			for(int i = 0; i< db.userList.size(); i++){
				if(userId.equals(db.userList.get(i).getUserId())
						&&userPass.equals(db.userList.get(i).getUserPass())){
					session.loginUserId = userId;
					session.loginUserPass = userPass;
					session.loginUserType = db.userList.get(i).getUserType();
					
				}
			}
		}
	}
	
	//로그아웃
	public void logOut() {
		System.out.println("\n로그아웃이 완료되었습니다.\n");

		session.loginUserId = "guest";
		session.loginUserPass = "guest123!";
		session.loginUserType=0;
		
	}

}

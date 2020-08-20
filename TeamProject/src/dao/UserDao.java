package dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import vo.UserRentVo;
import vo.UserVo;
import Session.UserSession;
import database.Database;

public class UserDao {
	
	Database db = Database.getInstance();
	UserSession session = new UserSession();
//	public boolean checkUserId(String id) {
//		boolean result = true;
//		for(int i = 0; i< checkUserList().size(); i++) {
//			if(!checkUserList().get(i).getUserId().equals(id)) {
//				result = false;
//			}
//		}
//		return result;
//	}

	
	
	


	//유저중복찾기
	public boolean checkUserId(String userId){
		boolean isNotFind = false;
			for(int i = 0; i< db.userList.size(); i++){
				if(!userId.equals(db.userList.get(i).getUserId())){
					isNotFind = false;//중복 아이디 없음
			}else{
				isNotFind = true; //중복 아이디 있음
				break;
			}	
		}
		return isNotFind;
	}
		

	
	public boolean checkLoginuser(String userId,String userPass) {
		boolean isNotFind = false;
		for(int i = 0; i< db.userList.size(); i++){
			if(userId.equals(db.userList.get(i).getUserId())
					&&userPass.equals(db.userList.get(i).getUserPass())){
				isNotFind = true;//중복 아이디 있음
				break;
			}else{
				isNotFind = false; //중복 아이디 없음	
			}	
		}
		return isNotFind;
	}

	
	
	public void insertUser(UserVo user){
		db.userList.add(user);
		
	}
	
	public ArrayList<UserVo> checkUserList() {
		return db.userList;
	}
	


	public int showUserMileage(String userId) {
		int mileage = 0;
		for(int i = 0; i< db.userList.size(); i++) {
			if(userId.equals(db.userList.get(i).getUserId())) {
				mileage = db.userList.get(i).getUserMileage();
				break;
			
			}else {
				mileage = 0;
			}
		}
		return mileage;
	}
	public UserRentVo userRentList(String userId){
		UserRentVo urvo = new UserRentVo();
		boolean isCorrect = false;
		
		for (int i = 0; i < db.userRentList.size(); i++) {
			System.out.println("--------------------------------");
			System.out.println(session.loginUserId + " 님의 차량대여내역");
			if (userId.equals(db.userRentList.get(i).getUserId())) {
				System.out.println(i + 1 + ". "+ db.companyList.get(i).getCoName() + "  | "+ db.userRentList.get(i).getvName());
				System.out.println("\n상세정보를 보려면 원하시는 번호를 입력해주세요.");
				
				do{
					isCorrect = false;
					Scanner sc = new Scanner(System.in);
					int input = sc.nextInt();
					
					if(input==i+1){
						System.out.println("--------------------------------");
						System.out.println(session.loginUserId+" 님의 상세내역");
						showRentInfos(session.loginUserId);
						isCorrect = true;
						break;
					}
					else{
						System.out.println("대여내역이 존재하지 않습니다.\n다시 입력해주세요");
					}
					
				}while(!isCorrect);

			} else {
				System.out.println(userId + " 님의 대여내역이 존재하지 않습니다.");
				System.out.println("이전 화면으로 이동합니다.");

			}
		}
		return urvo;
	}
	public void showRentInfos(String id){ //상세내역전체출력
		rentVehicleInfo(session.loginUserId);
		durationInfo();
		rentCompInfo();
		System.out.println("--------------------------------");
	}
	
	public void rentVehicleInfo(String id){ //렌트한 차량정보
		
		for(int i=0;i<db.userRentList.size();i++){
			if(id.equals(db.userRentList.get(i).getUserId())){
				System.out.println();
				System.out.println(" 대여번호	: "+ db.userRentList.get(i).getRentNum());
				System.out.println(" 차형	: "+ db.userRentList.get(i).getvSeries());
				System.out.println(" 차종	: "+ db.userRentList.get(i).getvName());
				System.out.println(" 차량번호 	: "+ db.userRentList.get(i).getRentVNum());
			}
		}
	}	
	
	
	public void durationInfo(){ //렌트기간정보
				
		DateFormat ex_Date = new SimpleDateFormat("yyyyMMdd",Locale.KOREA);
		DateFormat exactDate = new SimpleDateFormat("yyyy년 MM월 dd일 ",Locale.KOREA);
		DateFormat ex_Hour = new SimpleDateFormat("HH:mm",Locale.KOREA);
		DateFormat exactHour = new SimpleDateFormat("HH시 mm분",Locale.KOREA);
			
		try {
			for (int i = 0; i < db.userRentList.size(); i++) {
				String rentDate = db.userRentList.get(i).getRentDate();
				String returnDate = db.userRentList.get(i).getReturnDate();
				String actualDate = db.userRentList.get(i).getActualReturnDate();
				String rentTime = db.userRentList.get(i).getRentTime();
				String returnTime = db.userRentList.get(i).getReturnTime();
				String actualTime = db.userRentList.get(i).getActualReturnTime();
				
				
				String startDate = "";		String startHour = "";
				String endDate = "";		String endHour = "";
				String overDate = "";		String overHour = "";

				Date dateStart = null;		Date hourStart = null;
				Date dateEnd = null;		Date hourEnd = null;
				Date dateOver = null;		Date hourOver = null;
				
				//날짜형식으로 date객체를 만듬
				dateStart = ex_Date.parse(rentDate);
				dateEnd = ex_Date.parse(returnDate);
				dateOver = ex_Date.parse(actualDate);
				
				hourStart = ex_Hour.parse(rentTime);
				hourEnd = ex_Hour.parse(returnTime);
				hourOver = ex_Hour.parse(actualTime);
				
				//변환시킨 date객체를 원하는 날짜형식으로 다시 변환
				startDate = exactDate.format(dateStart);
				endDate = exactDate.format(dateEnd);
				overDate = exactDate.format(dateOver);
				
				startHour = exactHour.format(hourStart);
				endHour = exactHour.format(hourEnd);
				overHour = exactHour.format(hourOver);
					
				System.out.println(" 대여기간	: "+ startDate+" "+ startHour+" ~ "+endDate+ " "+endHour);
				System.out.println(" 실대여기간	: "+ startDate+" "+ startHour+" ~ "+overDate+" "+overHour);
//				System.out.println(" 실반납일	: "+ overDate +" "+ overHour);
					
				long diffD = dateOver.getTime() - dateEnd.getTime(); //초과일-예정반납일 = 초과시간
				long diffDays = diffD/(24*60*60*1000);	//일단위 표현

				double diffH = (double)hourOver.getTime()-hourEnd.getTime();
				long diffHour = (long)diffH/(60*60*1000);	//시간단위 표현

				int perHour = db.userRentList.get(i).getRentPrice() / 24; //1시간기준-625
				int overCharge = 0, totalCharge =0;
				
				if(diffH%(60*60*1000) == 0){
					System.out.println(" 초과시간	: "+diffDays+"일 "+diffHour+"시간");
					overCharge = (int)(perHour * (diffHour+(diffDays*24)));
					totalCharge = overCharge+db.userRentList.get(i).getRentPrice();
					
				}else{
					System.out.println(" 초과시간	: "+diffDays+"일 "+(diffHour+1)+"시간");
					overCharge = (int)(perHour * (diffHour+1+(diffDays*24)));
					totalCharge = overCharge+db.userRentList.get(i).getRentPrice();
				}
				
//				System.out.println(" 결제금액	: "+ db.userRentList.get(i).getRentPrice()+"원");
//				System.out.println(" 추가금액	: "+ overCharge+"원");
				System.out.println(" 최종금액 	: "+ totalCharge+"원"
									+"  [대여금액 : "+ db.userRentList.get(i).getRentPrice()+"원  "
									+"/ 추가금액 : "+ overCharge+"원]");
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	public void rentCompInfo() { // 렌트차량의 회사정보

		for (int i = 0; i < db.coVehicleList.size(); i++) {
			for (int j = 0; j < db.companyList.size(); j++) {
				if (db.coVehicleList.get(i).getCoSerialNum().equals(db.companyList.get(j).getCoSerialNum())) {
					System.out.println();
					System.out.println(" 업체명	: "+ db.companyList.get(j).getCoName());
					System.out.println(" 전화번호	: "+ db.companyList.get(j).getCoTel());
					System.out.println(" 회사주소	: "+ db.companyList.get(j).getCoAddr());
				}
			}
		}
	}
	
	
	public ArrayList<UserRentVo> allRentedList(){
		
		return db.userRentList;
	}
	
}

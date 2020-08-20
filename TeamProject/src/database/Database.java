package database;

import java.util.ArrayList;
import java.util.Date;

import vo.CarOptionVo;
import vo.CoVehicleVo;
import vo.CompanyVo;
import vo.NoticeVo;
import vo.ReviewVo;
import vo.UserRentVo;
import vo.UserVo;
import vo.VehicleNameVo;
import vo.VehicleSeriesVo;

public class Database {
	
	private static Database instance;
	private Database() {
		super();
	}
	public static Database getInstance() {
		if(instance == null) {
			instance = new Database();
		}
		return instance;
	}
	
	public ArrayList<CarOptionVo> carOptionList = new ArrayList<>();
	public ArrayList<CompanyVo> companyList = new ArrayList<>();
	public ArrayList<CoVehicleVo> coVehicleList = new ArrayList<>();
	public ArrayList<NoticeVo> noticeList = new ArrayList<>();
	ArrayList<ReviewVo> reviewList = new ArrayList<>();
	public ArrayList<UserRentVo> userRentList = new ArrayList<>();
	public ArrayList<UserVo> userList = new ArrayList<>();
	public ArrayList<VehicleSeriesVo> vSeriesList = new ArrayList<>();
	public ArrayList<ArrayList<VehicleNameVo>> vNameList = new ArrayList<>();
	
	
	
	public void settingStart() {
		createVehiclesVo();
		createUserVo();
		createCompanyAndVehicle();
		createNotice();
	}
	private void createVehiclesVo(){
		int count = 0;
		String[] vehicleSeries = {"소형","준중형","중형","고급형","SUV"};
		String[][] vehicleName = {{"모닝","레이","클리오","엑센트","스파크"},
								  {"포르테","아반떼","K3","SM3","벨로스터","아이오닉","크루즈","i30"},
								  {"K5","소나타","SM5","말리부","SM6","스팅어","i40"},
								  {"제네시스","그랜저","K7","K9","임팔라","알페온"},
								  {"티볼리","베뉴","스토닉","코나","스포티지","니로",
									  "소렌토","셀토스","트랙스","투싼","산타페","올란도"}};
		
		for(int i =0; i<vehicleSeries.length; i++) {
			VehicleSeriesVo vvo = new VehicleSeriesVo();
			vvo.setIndex(i+1);
			vvo.setvSeries(vehicleSeries[i]);
			vSeriesList.add(vvo);
		}
		for(int i = 0; i<vehicleSeries.length; i++){
			ArrayList<VehicleNameVo> tempArray = new ArrayList<>();
			for(int j = 0; j<vehicleName[i].length; j++){
				VehicleNameVo vno = new VehicleNameVo();
				vno.setIndex(j+1);
				vno.setvName(vehicleName[i][j]);
				tempArray.add(vno);
			}
			vNameList.add(tempArray);
		}
	}
	private void createUserVo() {
		int[] userType = {1,1,1,1,1};		//1 : user  /  2 : company
		String[] userId = {"sjy001","ljk002","ktu003","jys004","kss005"};
		String[] userPass = {"sjy191216!","ljk191216!","ktu191216!","jys191216!","kss191216!"};
		String[] userName = {"심주영","이제경","김태유","정영수","김승섭"};
		String[] userPhone = {"01011112222","01022223333","01033334444","01044445555","01055556666"};
		int[] userMileage = {10000,10500,7000,8000,3400};
		for(int i = 0; i<userId.length; i++) {
			UserVo uvo = new UserVo();
			uvo.setUserType(userType[i]);
			uvo.setUserId(userId[i]);
			uvo.setUserPass(userPass[i]);
			uvo.setUserName(userName[i]);
			uvo.setUserPhone(userPhone[i]);
			uvo.setUserMileage(userMileage[i]);
			userList.add(uvo);
		}
//		for(int i = 0 ; i< userType.length; i++) {
//		System.out.println(userList.get(i).getUserId() + " / " + userList.get(i).getUserName());
//		}
	}
	private void createCompanyAndVehicle(){
		
		String userId,userPass,userName,userPhone;
		int userType,userMileage;
		UserVo uvo = new UserVo();
		
		uvo.setUserId("wooki89");
		uvo.setUserPass("sjsk12313!!");
		uvo.setUserName("홍종욱");
		uvo.setUserPhone("01092587509");
		uvo.setUserType(2);
		uvo.setUserMileage(0);
		userList.add(uvo);
		String coName,coTel,coAddr,coNum,coSerialNum; 
		CompanyVo cvo = new CompanyVo();
		cvo.setUserId("wooki89");
		cvo.setCoName("홍코렌트카");
		cvo.setCoTel("0421234567");
		cvo.setCoNum("1234578912");
		cvo.setCoAddr("대전광역시 서구 탄방동 78-12");
		cvo.setCoSerialNum("202001211234578912");
		companyList.add(cvo);	
		
		CoVehicleVo cvvo = new CoVehicleVo();
		String vSeries,vName;
		String coVNumber;
		int rentPrice;
		cvvo.setCoSerialNum(cvo.getCoSerialNum());
		cvvo.setvSeries("준중형");
		cvvo.setvName("아반떼");
		cvvo.setCoVNumber("12허1234");
		cvvo.setRentPrice(15000);
		coVehicleList.add(cvvo);
		
		CarOptionVo carvo = new CarOptionVo();
		carvo.setRentVNUm(cvvo.getCoVNumber());
		carvo.setDriveAirBag(1);
		carvo.setAssistantAirBag(1);
		carvo.setRearCamera(1);
		carvo.setRearSensor(1);
		carvo.setBlackBox(1);
		carvo.setFourWD(0);
		carvo.setNavigation(1);
		carvo.setRemoteKey(0);
		carvo.setSmartKey(1);
		carvo.setHeatingSeat(1);
		carvo.setCoolingSeat(0);
		carvo.setSunRoof(1);
		carvo.setBluetooth(1);
		carOptionList.add(carvo);
		
		UserRentVo urvo = new UserRentVo();
		urvo.setUserId("sjy001");
		urvo.setvSeries("준중형");
		urvo.setvName("아반떼");
		urvo.setRentVNum(cvvo.getCoVNumber());
		urvo.setRentDate("20200127");
		urvo.setRentTime("07:30");
		urvo.setReturnDate("20200128");
		urvo.setReturnTime(urvo.getRentTime());
		urvo.setRentNum(urvo.getRentDate()+urvo.getvSeries()+"001");
		urvo.setActualReturnDate("20200127");
		urvo.setActualReturnTime("08:30");
		urvo.setRentPrice(cvvo.getRentPrice());
		userRentList.add(urvo);
		
		
		cvvo = new CoVehicleVo();
		cvvo.setCoSerialNum(cvo.getCoSerialNum());
		cvvo.setvSeries("준중형");
		cvvo.setvName("아반떼");
		cvvo.setCoVNumber("42허9988");
		cvvo.setRentPrice(20000);
		coVehicleList.add(cvvo);
		
		carvo = new CarOptionVo();
		carvo.setRentVNUm(cvvo.getCoVNumber());
		carvo.setDriveAirBag(1);
		carvo.setAssistantAirBag(1);
		carvo.setRearCamera(1);
		carvo.setRearSensor(1);
		carvo.setBlackBox(1);
		carvo.setFourWD(0);
		carvo.setNavigation(1);
		carvo.setRemoteKey(0);
		carvo.setSmartKey(1);
		carvo.setHeatingSeat(1);
		carvo.setCoolingSeat(0);
		carvo.setSunRoof(1);
		carvo.setBluetooth(1);
		carOptionList.add(carvo);
		
		
		
	}
//	static은 공유 인스턴스는 인스턴스마다 새로운 값과 메서드가 다르다
	
	
	
	
	void createNotice() {
		String noticeNum = "n-20200127001";
		String noticeTitle = "니가가라 하와이";
		String noticeContent = "이번에 추첨을 통해서 하와이 3박 4일 여행권을 증정합니다.";
		Date notiDate = new Date();
		NoticeVo nvo = new NoticeVo();
		nvo.setNoticeTitle(noticeTitle);
		nvo.setNoticeContent(noticeContent);
		nvo.setNoticeNum(noticeNum);
		nvo.setNotiDate(notiDate);
		noticeList.add(nvo);
	}
	
}

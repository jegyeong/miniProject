package service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import vo.CarOptionVo;
import vo.CoVehicleVo;
import vo.CompanyVo;
import vo.VehicleNameVo;
import vo.VehicleSeriesVo;
import Session.UserSession;
import dao.CoVehicleDao;
import dao.CompanyDao;
import dao.UserDao;
import database.Database;


public class CompanyService {
   UserService2 us2 = new UserService2();
   Scanner scan = new Scanner(System.in);
   Database db = Database.getInstance();
   CoVehicleDao codao = new CoVehicleDao();
   UserSession session = new UserSession();
   
   public ArrayList<CompanyVo> getRentCompInfos(HashMap<String, String> map) {
		String series = map.get("series");
		String name = map.get("name");
		ArrayList<String> serialArray = codao.rentableCompany(series, name);
		
		CompanyDao cdao = new CompanyDao();
		ArrayList<CompanyVo> compArray = cdao.getCompanyList();
		ArrayList<CompanyVo> temp = new ArrayList<>();
		for(int i = 0; i< serialArray.size(); i++){
			for(int j = 0; j<compArray.size(); j++ ){
				if(serialArray.get(i).equals(compArray.get(j).getCoSerialNum())){
					if(temp.contains(compArray.get(j).getCoSerialNum())){
						
					}else{
						CompanyVo cvo = new CompanyVo();
						cvo.setCoAddr(compArray.get(j).getCoAddr());
						cvo.setCoName(compArray.get(j).getCoName());
						cvo.setCoNum(compArray.get(j).getCoNum());
						cvo.setCoSerialNum(compArray.get(j).getCoSerialNum());
						cvo.setCoTel(compArray.get(j).getCoTel());
						cvo.setUserId(compArray.get(j).getUserId());
						temp.add(cvo);
					}
				}
			}
			
		}
		return temp;
		
	   
	   
	}
   
   
   public void createCompanyInfos(String userId) {
         CompanyVo cvo = new CompanyVo();
         CompanyDao cdao = new CompanyDao();
         System.out.println("\n회사 정보를 입력하는 창 입니다.");
         System.out.println("사용하실 회사 이름을 적어주세요.");
         String coName = scan.nextLine();
         boolean correct = false;
         String coTel = chkCoTel();
         System.out.println("회사 주소를 입력하여 주세요.");
         String coAddr = scan.nextLine();
         System.out.println("사업자 번호를 입력하여 주세요.");
         String coNum = chkCoNum();
         Date today = new Date();      
         DateFormat sdft = new SimpleDateFormat("yyyyMMdd");
         String coSerialNum = String.valueOf(sdft.format(today)) + coNum;
         cvo.setUserId(userId);
         cvo.setCoName(coName);
         cvo.setCoTel(coTel);
         cvo.setCoAddr(coAddr);
         cvo.setCoNum(coNum);
         cvo.setCoSerialNum(coSerialNum);
         cdao.insertCompany(cvo);
         System.out.println("업체 정보 등록이 완료 되었습니다.");
   }
   private String chkCoTel() {
		String coTel = null;
		do {
			System.out.println("\n회사 전화번호를 입력해 주세요. \n");
			System.out.print("회사번호 : ");
			coTel = scan.nextLine();
			us2.chkPattern(coTel,4);
			if(us2.chkPattern(coTel,4)==true){
				coTel = coTel.replace("-", "");
				System.out.println("인증 완료\n");
				return coTel;
			}else{
				System.out.println("올바른 형식의 번호가 아닙니다.");
				coTel = null;
			}
			}while(coTel == null);
		return null;
	}
	
	private String chkCoNum() {
		String coNum = null;
		do {
			System.out.println("\n사업자 번호를 입력해 주세요. \n");
			System.out.print("사업자 번호 : ");
			coNum = scan.nextLine();
			us2.chkPattern(coNum,5);
			if(us2.chkPattern(coNum,5)==true){
				coNum = coNum.replace("-", "");
				System.out.println("인증 완료\n");
				return coNum;
			}else{
				System.out.println("올바른 형식의 번호가 아닙니다.");
				coNum = null;
			}
			}while(coNum == null);
		return null;
	}
	
	public void searchingCoVehicle(String vSeries, String vName) {
		//차 시리즈랑 차 이름 을 가지고 있는 업체 전부 조회
		
		ArrayList<CompanyVo> instancCompanyList;
		for(int i = 0;i < db.coVehicleList.size(); i++){
			if(db.coVehicleList.get(i).getvSeries().equals(vSeries) && db.coVehicleList.get(i).getvSeries().equals(vName)){
				instancCompanyList = new ArrayList<>();
				CompanyVo cvo = new CompanyVo();
//				cvo.setCoName();
//				cvo.setCoTel();
//				cvo.setCoAddr();
//				cvo.setCoNum();
//				cvo.setCoSerialNum();
//				cvo.setUserId();
			
			}
		}
		
	}

	public void showcovehicleMenu() { //차량 등록 메뉴
		int select;
		do{
			
		System.out.println("========차량 등록 메뉴========");
		System.out.println("\t1. 차량 등록");
		System.out.println("\t0. 뒤로가기");
		System.out.println("--------------------------");

		select = Integer.parseInt(scan.nextLine());
			switch(select){
			case 0: 
				break;
			case 1: 
				covehicleInsert(); 
				break;
			default:
				break;
			}
		}while(select != 0);
	}
	
	public void covehicleInsert() { //차량 등록
	      
	      CoVehicleVo covvo = new CoVehicleVo();
	      
	      String coVNumber;
	      
	      boolean isCheckcoVNumber = false;
	      
	      do{
	         System.out.println("==========차량 등록==========");
	         System.out.print("차번 : "); //기본키
	         coVNumber = scan.nextLine();
	         String regex = "^\\d{2}[허|하|호]\\d{4}/*$";
	         Pattern p = Pattern.compile(regex);
	         Matcher m = p.matcher(coVNumber);
	         if(m.matches()){
	            isCheckcoVNumber = true;
	            covvo.setCoVNumber(coVNumber);
	         }else{   
	            System.out.println("잘못 입력 하셨습니다.");
	         }
	      }while(isCheckcoVNumber == false);
	      
	      VehicleService vs = new VehicleService();
	      VehicleSeriesVo vo = vs.gettingSeries();
	      String vSeries = vo.getvSeries();
	      System.out.println("시리즈 : " + vo.getvSeries());
	      System.out.println("--------------------------");
	      covvo.setvSeries(vo.getvSeries());
	      VehicleNameVo cvnvo = vs.gettingVName(vo.getIndex());
	      String vName = cvnvo.getvName();
	      System.out.println("차종 : " + cvnvo.getvName());
	      System.out.println("--------------------------");
	      covvo.setvName(cvnvo.getvName());
	      
	         System.out.print("금액 : ");
	         int rentPrice = Integer.parseInt(scan.nextLine());
	         covvo.setRentPrice(rentPrice);
	         
	         covvo.setCoSerialNum(null);
	         System.out.println("--------------------------");
	         System.out.println("옵션이 있으면 1 없으면 0을 입력해주세요.");
	         
	         for(int i = 1; i <= 13; i++) {
	            ArrayList<CarOptionVo> carOptionList = codao.settingCarOptionList();
	            CarOptionVo cvo = new CarOptionVo();
	            cvo.setRentVNUm(coVNumber);
	            
	            cvo = new CarOptionVo();
	            switch (i) { // 1 : 옵션이 있다.  0 : 옵션이 없다.
	            case 1:
	               System.out.print("운전석에어백 : ");
	               int a = Integer.parseInt(scan.nextLine());
	               cvo.setDriveAirBag(a);
	               break;
	            case 2:
	               System.out.print("조수석에어백 : ");
	               int a1 = Integer.parseInt(scan.nextLine());
	               cvo.setAssistantAirBag(a1);
	               break;
	            case 3:
	               System.out.print("후방카메라 : ");
	               int a2 = Integer.parseInt(scan.nextLine());
	               cvo.setRearCamera(a2);
	               break;
	            case 4:
	               System.out.print("후방센서 : ");
	               int a3 = Integer.parseInt(scan.nextLine());
	               cvo.setRearSensor(a3);
	               break;
	            case 5:
	               System.out.print("블랙박스 : ");
	               int a4 = Integer.parseInt(scan.nextLine());
	               cvo.setBlackBox(a4);
	               break;
	            case 6:
	               System.out.print("후륜구동: ");
	               int a5 = Integer.parseInt(scan.nextLine());
	               cvo.setFourWD(a5);
	               break;
	            case 7:
	               System.out.print("네비게이션 : ");
	               int a6 = Integer.parseInt(scan.nextLine());
	               cvo.setNavigation(a6);
	               break;
	            case 8:
	               System.out.print("리모트키 : ");
	               int a7 = Integer.parseInt(scan.nextLine());
	               cvo.setRemoteKey(a7);
	               break;
	            case 9:
	               System.out.print("스마트키 : ");
	               int a8 = Integer.parseInt(scan.nextLine());
	               cvo.setSmartKey(a8);
	               break;
	            case 10:
	               System.out.print("열선시트 : ");
	               int a9 = Integer.parseInt(scan.nextLine());
	               cvo.setHeatingSeat(a9);
	               break;
	            case 11:
	               System.out.print("통풍시트 : ");
	               int a10 = Integer.parseInt(scan.nextLine());
	               cvo.setCoolingSeat(a10);
	               break;
	            case 12:
	               System.out.print("선루프 : ");
	               int a11 = Integer.parseInt(scan.nextLine());
	               cvo.setSunRoof(a11);
	               break;
	            case 13:
	               System.out.print("블루투스 : ");
	               int a12 = Integer.parseInt(scan.nextLine());
	               cvo.setBluetooth(a12);
	               break;
	            default:
	               break;
	            }
	            carOptionList.add(cvo);
	         }
	         
	         String coSerialNum = codao.getCoSerialNum(session.loginUserId);
	         covvo.setCoSerialNum(coSerialNum);
	      
	         db.coVehicleList.add(covvo);
	         System.out.println("--------------------------");
	         System.out.println(vSeries + " : " + vName + " 차번 : " +coVNumber +  " 가격 : " + rentPrice + " 정보의 차량이 등록되었습니다.");
	         System.out.println("-----------------------------");
	         codao.getCarOptionList();
	         System.out.println("-----------------------------");
	         System.out.println("차량 등록 메뉴로 이동합니다.");
	      }
	
	public void showControlMenu(){ //차량 관리 메뉴
		int select;
		do{
			System.out.println("========차량 관리 메뉴========");
			System.out.println("\t1.차량 수정");
			System.out.println("\t2.차량 삭제");
			System.out.println("\t0.뒤로가기");
			
		select = Integer.parseInt(scan.nextLine());
			switch(select){
			case 0: 
				select=0;
				break;
			case 1: 
				coVehicleUpdate();
				break;
			case 2: 
				covehicleRemove(); 
				break;
			default:
				break;
			}
		}while(select != 0);
	}
	
	public void covehicleRemove() { //차량 삭제	
		boolean isremove = false;
		String coSerialNum = codao.getCoSerialNum(session.loginUserId);
		do {
			if (codao.getCoVehicleList(coSerialNum).size() != 0) {
				isremove = true;
				ArrayList<CoVehicleVo> tempList = codao.getCoVehicleList(coSerialNum);
				for(int i = 0; i < tempList.size(); i++ ){
					System.out.print(i + 1 + "." + tempList.get(i).getCoVNumber());
					System.out.print("\t"+tempList.get(i).getvSeries());
					System.out.print("\t"+tempList.get(i).getvName());
					System.out.print("\t"+tempList.get(i).getRentPrice());
					System.out.println();
				}
				
				System.out.println("-----------------------------");
				System.out.println("삭제하고자 하는 번호를 선택해주세요.");
				int select = Integer.parseInt(scan.nextLine());
				if (select <= codao.getCoVehicleList(coSerialNum).size()) {
					System.out.println(codao.getCoVehicleList(coSerialNum).get(select - 1)
							.getCoVNumber() + "차량의 정보를 선택하셨습니다.");
				}else {
					System.out.println("잘못 입력하여 되돌아갑니다.");
					covehicleRemove();
					break;
				}

				System.out.println("한번 삭제하면 복구되지 않습니다. 그래도 삭제하시겠습니까?");
				System.out.println("----------------------");
				System.out.println("1.예");
				System.out.println("2.아니오");
				System.out.println("----------------------");

				int delete = Integer.parseInt(scan.nextLine());

				switch (delete) {
				case 1:
					for(int i = 0; i< codao.getCoVehicleList().size(); i++){
						if(codao.getCoVehicleList(coSerialNum).get(select -1).getCoSerialNum() == codao.getCoVehicleList().get(i).getCoSerialNum()){
							codao.deleteCoVehicle(i);		
						}
					}
					
					System.out.println("해당 차량의 정보가 삭제되었습니다.");
					isremove = true;
					break;
				case 2:
					System.out.println("상위 메뉴로 돌아갑니다.");
					showControlMenu();
					break;
					default: break;
				}
			}else if(codao.getCoVehicleList(coSerialNum).size() == 0){
				System.out.println("삭제할 차량의 정보가 없습니다. \n상위 메뉴로 돌아갑니다.");
				break;
			}
	}while(isremove == false);
	}
	
	public void coVehicleUpdate(){ //차량 수정	
		boolean isUpdateCovehicle = false;
		String coSerialNum = codao.getCoSerialNum(session.loginUserId);
		do{
			if(codao.getCoVehicleList(coSerialNum).size() != 0){
				isUpdateCovehicle = true;
				System.out.println("--------------------------");
				ArrayList<CoVehicleVo> tempList = codao.getCoVehicleList(coSerialNum);
				for(int i = 0; i < tempList.size(); i++ ){
					System.out.print(i + 1 + "." + tempList.get(i).getCoVNumber());
					System.out.print("\t"+tempList.get(i).getvSeries());
					System.out.print("\t"+tempList.get(i).getvName());
					System.out.print("\t"+tempList.get(i).getRentPrice());
					System.out.println();
				}
				System.out.println("--------------------------");
				System.out.println("수정하고자 하는 번호를 선택해주세요.");
				int select = Integer.parseInt(scan.nextLine());
				if(select <= codao.getCoVehicleList(coSerialNum).size()){
					System.out.println(codao.getCoVehicleList(coSerialNum).get(select - 1).getCoVNumber() + "차량의 정보를 선택하셨습니다.");
				}else{
					System.out.println("잘못 입력하여 되돌아갑니다.");
					coVehicleUpdate();
					break;
				}
				System.out.println("수정하고자 하는 차량정보를 선택해주세요.");
				System.out.println("--------------------------");
				System.out.println("1.차번");
				System.out.println("2.시리즈 / 차종");
				System.out.println("3.금액");
				System.out.println("0.뒤로가기");
				System.out.println("--------------------------");
				
				int selectNum = Integer.parseInt(scan.nextLine());
				
				
				switch(selectNum){
				case 0:
					System.out.println("상위 메뉴로 돌아갑니다.");
					break;
				case 1:
					System.out.print("차번을 수정해주세요: ");
					String updateCoVNumber = scan.nextLine();
					String regex = "^\\d{2}[허|하|호]\\d{4}/*$";
					Pattern p = Pattern.compile(regex);
					Matcher m = p.matcher(updateCoVNumber);
					if(m.matches()){
						isUpdateCovehicle = true;
					}else{	
						System.out.println("잘못 입력 하셨습니다.");
					}
					codao.updateCoVehileNumber(select - 1, updateCoVNumber);
					System.out.println("변경을 완료하였습니다!!");
					break;
				case 2:
					System.out.println("시리즈와 차종을 수정해주세요.");
					VehicleService vs = new VehicleService();
					VehicleSeriesVo vo = vs.gettingSeries();
					VehicleNameVo cvnvo = vs.gettingVName(vo.getIndex());
					System.out.println("시리즈 : " + vo.getvSeries() +" "+ "차종 : " + cvnvo.getvName());
					String updateVseries = vo.getvSeries();
					String updateVname = cvnvo.getvName();
					codao.updateCoVehile(select - 1, updateVseries, updateVname);
					System.out.println("변경을 완료하였습니다!!");
					break;
				case 3:
					System.out.print("금액을 수정해주세요: ");
					int updateRentPrice = Integer.parseInt(scan.nextLine());
					codao.updateCoVehilePrice(select - 1, updateRentPrice);
					System.out.println("변경을 완료하였습니다!!");
					break;
				default:
					break;
				}
			}else{
				System.out.println("정보가 존재하지 않습니다.");
				break;
			}
		}while(isUpdateCovehicle == false);
	}

	public void showReturnMenu() { //차량 반납 메뉴
		int select;
		do{
			System.out.println("========차량 반납 메뉴========");
			System.out.println("1.차량 반납");
			System.out.println("2.리뷰 쓰기");
			System.out.println("0.뒤로가기");
			
			select = Integer.parseInt(scan.nextLine());
			
			switch(select){
			case 0:
				break;
			case 1:
				coVehicleReturn();
				break;
			case 2:
				break;
			default :
				break;
			}
		}while(select != 0);
	}
	
	public void coVehicleReturn() { //차량 반납
		UserDao udao = new UserDao();
		boolean isreturn = false;
		do {
			if (db.userRentList.size() != 0) {
				isreturn = true;
				udao.userRentList(session.loginUserId);
				System.out.println("지금 보신 차량을 반납하시겠습니까? 예/아니오");
			}else{
				udao.userRentList(session.loginUserId);				
				break;
			}
			String select = scan.nextLine();
			if (select.equals("예")) {
				System.out.println("선택하셨습니다. \n반납하시겠습니까?");
			} else {
				System.out.println("상위 메뉴로 돌아갑니다.");
				showReturnMenu();
				break;
			}
			System.out.println("----------------------");
			System.out.println("1.예");
			System.out.println("2.아니오");
			System.out.println("----------------------");

			int selectNum = Integer.parseInt(scan.nextLine());
			switch (selectNum) {
			case 1:
				System.out.println("결제 창으로 넘어갑니다.");
				break;
			case 2:
				System.out.println("상위 메뉴로 돌아갑니다.");
				showReturnMenu();
				break;
			default:
				break;
			}

		} while (isreturn == false);
	}
}


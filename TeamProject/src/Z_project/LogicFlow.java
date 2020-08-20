package Z_project;

import java.util.ArrayList;
import java.util.Scanner;

public class LogicFlow {
	static boolean isfinishedAll = false;
	static boolean isLogin = false;
	private static String userId = "abcr";
	static LogicFlow lf = new LogicFlow();
	Scanner s;
	
	public static void main(String[] args) {
		
		
		
		lf.start();
		
		
		
	}
	
	private void start() {
		int menu = 0;
		s = new Scanner(System.in);
		do{
		isfinishedAll = false;
		System.out.println("대덕렌터카에 오신것을 환영합니다.");
		System.out.println("-------- 메인메뉴 --------\n");
		if(isLogin){
			if(userId.equals("master")){
				System.out.println("1. 업체관리");
				System.out.println("2. 회원관리");
			}else{
				System.out.println("1. 렌트카 검색");
				System.out.println("2. 렌트카 반납");
				System.out.println("3. 자동차 대여내역");
				System.out.println("4. 로그아웃");
			}
		}else{
			System.out.println("1. 렌트카 검색");
			System.out.println("2. 렌트카 반납");
			System.out.println("3. 로그인");
			System.out.println("4. 회원가입");
		}
		System.out.println("0. 앱 종료");
		System.out.println("\n------------------------");
		menu = Integer.parseInt(s.nextLine());
		
		if(menu>=1 && menu<=4){
			//정상적인 행위
			switch (menu) {
			case 1:
				if(isLogin){
					//회원 렌트카 검색
					if(userId.equals("master")){
						//마스터피스
						lf.controlCompany();
					}else{
						//일반 사용자
						lf.rentCar();
					}
				}else{
					//비회원 렌트카 검색
					lf.rentCar();
				}
				break;
			case 2:
				if(isLogin){
					if(userId.equals("master")){
					//마스터피스	
					}else{
					//회원 렌트카 반납
					}
				}else{ System.out.println("회원만 사용가능 한 서비스 입니다."); } //비회원 렌트카 반납
				break;
			case 3:
				if(isLogin){
					//자동차 대여내역
					System.out.println("자동차 대여내역을 보는 메서드 입니다.");
				}else{
					//로그인 하기
					System.out.println("로그인 하는 메서드 입니다.");
					lf.isLogin = true;
				}
				break;
			case 4:
				if(isLogin){
					//로그아웃 하기
					System.out.println("로그아웃 하는 메서드 입니다.");
				}else{
					//회원가입 하기
					System.out.println("회원가입 하는 메서드 입니다.");
				}
				break;
			default: break;
			}
			
		}else if(menu ==0){
			System.out.println("앱을 종료합니다.");
		}else{
			System.out.println("번호를 다시 입력하여 주세요.");
		}
		}while(menu != 0);
	}

	private void rentCar() {
		int select;
		do{
		if(isLogin){
			System.out.println("회원의 렌트카 검색하는 부분입니다.");
			
		}else{
			System.out.println("비회원의 렌트카 검색하는 부분입니다.");
		}
		System.out.println("대여 하고자 하는 차종을 선택해 주세요.");
		System.out.println("------------------------\n");
		System.out.println("1. 소형\n2. 준중형\n3. 중형\n4. SUV\n0. 뒤로가기");
		System.out.println("\n------------------------");
		select = Integer.parseInt(s.nextLine());
		if(select>=1 && select<=4){
			lf.vehicleSize(select);	
		}else if(select == 0){
			System.out.println("뒤로갑니다.\n");
			break;
		}else{
			System.out.println("번호를 다시 입하여 주십시요");
		}
		}while( isfinishedAll == false);
		
	}
	private void vehicleSize(int series) {
		switch (series) {
		case 1:
			System.out.println("소형을 선택 하셨습니다.");
			
			break;
		case 2:
			System.out.println("준중형을 선택 하셨습니다.");
			break;
		case 3:
			System.out.println("중형을 선택 하셨습니다.");
			break;
		case 4:
			System.out.println("SUV를 선택 하셨습니다.");
			break;
		default:
			
			break;
		}
		lf.showCarSeries(series);
	}

	private void showCarSeries(int series) {
		int select = 0;
		System.out.println("---------------------\n");
		do{
			switch (series) {
			case 1:
				System.out.println("소형 차량을 골라주세요.\n");
				System.out.println("1. 마티즈");
				System.out.println("2. 모닝");
				break;
			case 2:
				System.out.println("\n준중형 차량을 골라주세요.");
				System.out.println("1. K3");
				System.out.println("2. 아반떼");
				System.out.println("3. SM3");
				System.out.println("4. 쉐보레 크루즈");
				break;
			case 3:
				System.out.println("\n중형 차량을 골라주세요.");
				System.out.println("1. K5");
				System.out.println("2. 소나타");
				System.out.println("3. SM5");
				System.out.println("4. SM6");
				System.out.println("5. 쉐보레 말리부");
				break;
			case 4:
				System.out.println("\nSUV 차량을 골라주세요.");
				System.out.println("1. 티볼리");
				System.out.println("2. 코란도C");
				System.out.println("3. QM6");
				System.out.println("4. 투싼");
					break;
			default:
				break;
			}
			System.out.println("0. 뒤로가기");
		System.out.println("\n---------------------");
		select = Integer.parseInt(s.nextLine());
		
		if(select == 0){
			break;
		}else{
			//무브무
			//series = 1/ select = 2
			
			
			lf.showLists(series,getCarName(series).get(select-1).getVehicle());
		}
		}while(isfinishedAll == false);
		
	}
	private ArrayList<SampleVo> getCarName(int series){
		ArrayList<SampleVo> array = new ArrayList<>();
		if(series == 1){
		SampleVo vo = new SampleVo();
		vo.setSeries(1);
		vo.setVehicle("마티즈");
		array.add(vo);
		
		vo = new SampleVo();
		vo.setSeries(1);
		vo.setVehicle("모닝");
		array.add(vo);
		}
		return array;
	}

	private void showLists(int series,String vehicle) {
		
		if(isLogin){
		switch (series) {
		case 1:
			System.out.println(vehicle+ "를 선택 하셨습니다.");
			System.out.println("결제하시겠습니까?");
			System.out.println("1. 결제 합니다.");
			System.out.println("2. 뒤로가기");
		System.out.println("3. 처음으로 돌아가기");
			int pay = Integer.parseInt(s.nextLine());
			if(pay == 1){
				System.out.println("\n\n--------결제 창---------\n");
				System.out.println("결제가 완료 되었습니다.\n");
				System.out.println("처음 화면으로 돌아갑니다.");
			System.out.println("\n----------------------------\n");
				
				isfinishedAll = true;
			}else if (pay == 2){
				return;
			}else if(pay == 3){
				isfinishedAll = true;
			}
			break;

		default:
			break;
		}
		}else{
			System.out.println("로그인 후 사용 가능합니다.");
			System.out.println("처음 화면으로 돌아갑니다.");
			try {
				Thread.sleep(2000);
				isfinishedAll = true;
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}

	private void controlCompany() {
		
		System.out.println("업체 관리를 하는 메서드입니다.");
		
	}
	private void contolMember(){
		System.out.println("회원 관리를 하는 메서드 입니다.");
	
	}
}


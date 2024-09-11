package BattleGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
/*
 * 전상문
 */
public class Game {
	
	final int characterCnt = 5; //팀당 캐릭터 갯수
	
	Base characters[]; //캐릭터들
	Base myTeam[];  // 우리팀
	Base yourTeam[]; // 적팀
	
	ArrayList<Integer> myTeamLive; // 우리팀 생존리스트
	ArrayList<Integer> yourTeamLive; // 적팀 생존 리스트
	
	
	HashMap<Integer,String[]> gameHistory = new HashMap<>(); // 게임기록
	
	int gameTurnCnt = 0; //게임 턴
	
	boolean gameEnd = false; //게임 종료
	int winner; // 승리팀 0:우리팀, 1:적팀
	
	Random random = new Random(); // 랜덤 객체
	
	Color color = new Color(); // 색상 클래스
	
	Game(Base[] characters){
		this.characters = characters;
		setFirstAttack();
		setMyTeam();
		setYourTeam();
	}
	
	/* 선공 게임 */
	public void setFirstAttack() {
		CoinToss cointoss = new CoinToss();

		// 우리팀 선공
		if(cointoss.coinTossgo()) {
			String[] teamStr= {"my","your"};
			gameHistory.put(0,teamStr);
		// 적팀 선	공
		}else {
			String[] teamStr= {"your","my"};
			gameHistory.put(0,teamStr);
		}
	}
	
	/* 우리팀 생성 */
	public void setMyTeam() {
		myTeam = new Base[characterCnt]; // 우리 팀 객체 배열
		myTeamLive = new ArrayList<>(); // 우리팀 생존 리스트
		
		for(int i = 0; i < characterCnt; i++) {
			myTeam[i] = characters[i];
			myTeamLive.add(i);
		}
	}
	
	/* 적팀 생성*/
	public void setYourTeam() {
		yourTeam = new Base[characterCnt]; // 상대 팀 객체 배열
		yourTeamLive = new ArrayList<>(); // 적팀 생존 리스트
		
		for(int i = 0; i < characterCnt; i++) {
			int characterNum = random.nextInt(characters.length); // 캐릭터수 만큼 랜덤으로 생성
			yourTeam[i] = makeCharachter();
			yourTeamLive.add(i);
		}
	}
	
	/* 랜덤 캐릭터 생성 */
	public Base makeCharachter() {
		
		int num = random.nextInt(characters.length);
		switch (num) {
	    case 0: //파이리
	    	return new Character1();
	    	
	    case 1:
	    	return new Character2();
	    	
	    case 2:
	    	return new Character3();
	    	
	    case 3:
	    	return new Character4();
	    	
	    case 4:
	    	return new Character5();
	   
	    default:
	        return new Character1();
		}
		
	}
	
	/* 게임 현재 상황 */
	public void showCurrGame() {

		// 적팀  
		int digit = 2;
        for (int j = 0; j < 5; j++) {
            System.out.print("+--------------");
        }
        System.out.println();
       
       
        for (int j = 0; j < 5; j++) {
        	String text =yourTeam[j].name +"(" +yourTeam[j].hp+")"; // 적팀 캐릭터 체력 및 이름
            System.out.printf("|%"+digit+"s"+text+"%-3s"," "," ");
        }
        System.out.println("|");
        
        // 몇턴인지 나타내는곳 
        System.out.println();
        System.out.println("        현재 턴"+gameTurnCnt+"번째입니다.");
        System.out.println();
        
    	// 우리팀
        for (int j = 0; j < 5; j++) {
            System.out.print("+--------------");
        }
        System.out.println();
        // 이름 나타내는곳
        for (int j = 0; j < 5; j++) {
        	String text =myTeam[j].name +"(" +myTeam[j].hp+")"; // 우리팀 캐릭터 체력 및 이름
            System.out.printf("|%"+digit+"s"+text+"%-3s"," "," ");
        }
        System.out.println("|");
	}
	

	/* 우리팀 공격 =>추후에 팀 class 만들어서 관리하는게 더 좋아보인다.*/ 
	public void myTeamAttack() {
		color.turnRed();
		System.out.println("======적팀======");
	    int randomNum = random.nextInt(yourTeamLive.size()); // 생존수 만큼 랜덤으로 생성
		int attackNum= yourTeamLive.get(randomNum); //마지막 index
		yourTeam[attackNum].underAttack(); // 공격
		if(!yourTeam[attackNum].isLive()) { //사망시
			System.out.println("사망");
			yourTeamLive.remove(randomNum);
			//전부 사망
			if(yourTeamLive.size()==0) {
				System.out.println("적팀 전부 사망");
				winner = 0;
				gameEnd = true;
			}
		}
		color.turnBlack();
	}
	/* 적팀 공격 =>추후에 팀 class 만들어서 관리하는게 더 좋아보인다. */
	public void yourTeamAttack() {
	    color.turnBlue();
	    System.out.println("======우리팀======");
		int randomNum = random.nextInt(myTeamLive.size()); // 생존수 만큼 랜덤으로 생성
		int attackNum= myTeamLive.get(randomNum); //마지막 index
		myTeam[attackNum].underAttack(); // 공격
		if(!myTeam[attackNum].isLive()) { // 사망시
			myTeamLive.remove(randomNum);
			//전부 사망
			if(myTeamLive.size()==0) {
				System.out.println("우리팀 전부 사망");
				winner = 1;
				gameEnd = true;
			}
		}

		color.turnBlack();
	}
	
	
	/* 게임 진행 */
	public boolean play() {
		gameTurnCnt++;
		String currTeamturn[] = {"0","0"}; // 공격 기록
		
		for(int i = 0; i < 2 ; i++) {
			if(gameHistory.get(0)[i].equals("my")) {
				myTeamAttack();
			}else {
				yourTeamAttack();
			}
			
			currTeamturn[i] = String.valueOf(1);
			gameHistory.put(gameTurnCnt,currTeamturn); //게임기록 삽입
			
			//게임 종료
			if(gameEnd) {
				break;
			}
		}
		return gameEnd;	
	}

	
	/* 게임 진행 스킵할때 */
	public boolean play(int skipTurn) {
		for(int i = 0; i < skipTurn ; i++) {
			//게임 종료시
			if(play()) {
				break;
			}
		}	
		return gameEnd;
	}
	
	
	/* 게임종료 */
	public void gameEnd() {
		if(winner == 0) {
			color.turnBlue();
			System.out.println("축하합니다. 우리팀이 승리했습니다.");
		}
		if(winner == 1) {
			color.turnRed();
			System.out.println("아쉽게도 적팀이 이겼습니다.");
		}
		showCurrGame();
		System.out.println("======게임기록========");
		System.out.print("선공팀은 :");
		System.out.println(gameHistory.get(0)[0]=="my"?"우리팀":"적팀");
		System.out.println("공격횟수:"+((gameTurnCnt-1)+Integer.parseInt(gameHistory.get(gameTurnCnt)[0]))+ "vs"+((gameTurnCnt-1)+Integer.parseInt(gameHistory.get(gameTurnCnt)[1])));
		color.turnBlack();
	}
	
	
	/* 테스트 코드 */
	public void showCurrCharacter() {
		for(int i = 0; i < characters.length ; i++) {
			System.out.println(characters[i].name);
		}
	}
	
	public void showCurrTeam() {
		for(int i = 0; i < myTeam.length ; i++) {
			System.out.println(myTeam[i]);			
		}
	}
	/* 테스트 코드 끝 */
}



// 글자 색상 클래스 
class Color{
	/*
	 * ANSI escape 코드를 사용해서 색상 변경
	 */
	
	final String black ="\u001B[0m"; // 검정색
	final String red ="\u001B[31m"; // 빨간색
	final String blue ="\u001B[34m"; // 파랑색
	
	// 빨간색으로 변경
	public  void turnRed() {
		System.out.print(red);
		
	}
	
	// 검정색으로 변경
	public  void turnBlack() {
		System.out.print(black);
	}
	
	// 검정색으로 변경
	public  void turnBlue() {
		System.out.print(blue);
	}
}
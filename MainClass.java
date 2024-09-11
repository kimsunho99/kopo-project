package BattleGame;

import java.util.Scanner;

public class MainClass {
	public static void main(String[] args) {
		
		Base characters[] = new Base[5];
		
		String name[] = {"전상문","김유경","김태훈","조하준","김선호"};
		
		boolean gameNow = true; 
		
		characters[0] = new Character1();
		characters[1] = new Character2();
		characters[2] = new Character3();
		characters[3] = new Character4();
		characters[4] = new Character5();
		

		System.out.println("포켓몬 게임을 시작하겠습니다.");
		System.out.println("먼저 캐릭터 소개가 있겠습니다.");
		
		for(int i=0; i<characters.length;i++) {
			System.out.print(characters[i].name +"("+name[i]+")");
			System.out.println();
		}
			
		
		Scanner scan = new Scanner(System.in);

		Game game = new Game(characters); // 게임
		
		String next=""; 
		while(gameNow) {
			System.out.println("엔터키를 누르면 게임이 진행됩니다.");
			next = scan.nextLine();
			
			//skip 입력시
			if(next.equals("skip")) {
				System.out.println("몇번 스킵하시겠습니까?");
				int skipCnt = scan.nextInt();
				 scan.nextLine();
				 
				 if(game.play(skipCnt)) {
						gameNow = false;
						game.gameEnd();
					}else {
						game.showCurrGame();
					}
			}else {
				if(game.play()) {
					gameNow = false;
					game.gameEnd();
				}else {
					game.showCurrGame();
				}
			}
		
			
		}
		
		
	}
}
/*
-게임메뉴
-선공
-사망처리
-승리/패배처리
*/
 
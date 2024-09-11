package BattleGame;
import java.util.Random;
import java.util.Scanner;
/*
 * 김태훈
*/
public class CoinToss {

	public boolean coinTossgo() {
		
		boolean myTurn;

		Scanner s = new Scanner(System.in);
		Random r = new Random();
		Color color = new Color();
		
		
		System.out.println("코인토스를 진행하여 승리시 선공입니다. 사용자는 앞인지 뒤인지 선택해주세요.");
		System.out.println("1. 앞면");
		System.out.println("2. 뒷면");

		int BackForth = s.nextInt();

		int RanNumber = r.nextInt(2) + 1;

		if (RanNumber == 1) {
			System.out.println("앞면이 나왔습니다.");
		} else {
			System.out.println("뒷면이 나왔습니다.");
		}

		if (BackForth == RanNumber) {
	
			color.turnBlue();
			System.out.println("코인토스에서 승리하셨습니다 선공입니다");
			myTurn = true;
			color.turnBlack();

		} else {
			color.turnRed();
			System.out.println("후공입니다.");
			myTurn = false;
			color.turnBlack();
		}

		
		return myTurn;
		
	}

	
}

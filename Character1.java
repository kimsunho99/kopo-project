package BattleGame;

import java.util.Random;

public class Character1 extends Base {
	int count = 0;
	/*
 		전상문
	 */
	public Character1() {
		this.name = "피카츄";
	}
	
	@Override
	public void underAttack() {
		if(count == 0) {
			System.out.println("피카츄 등장!");
			count++;
			}
		
		System.out.println(this.name+" 공격당함");
		Random random = new Random();
		int attckNum = random.nextInt(6);
		if(attckNum == 0) {
			System.out.println("따가워요ㅜㅜ. 체력이 5 감소합니다.");
			this.hp -=5;
		} else if (attckNum == 1) {
			System.out.println("발전소 발견! " + this.name + "감전");
			System.out.println(this.name + "기분 좋아(체력 15증가)");
			this.hp += 15;
		} else if (attckNum == 2) {
			System.out.println("떼구르르~");
			System.out.println(this.name + "가 구르기를 멈출수가 없어요. 어지러워ㅠㅠ");
			System.out.println("체력이 20감소합니다.");
			this.hp -= 20;
		} else if (attckNum == 3) {
			System.out.println("피무룩");
			System.out.println(this.name + "가 치명상을 입었습니다. 체력이 35감소합니다.");
			this.hp -= 35;
		} else if (attckNum == 4) {
			System.out.println(this.name + " 전광석화. 공격을 피했습니다. 체력 감소가 없습니다");
			this.hp -= 0;
		} else {
			System.out.println(this.name + "깨꼬닥");
			this.hp -= this.hp;
		}
		
		if (this.hp < 0) {
			this.hp = 0;
		}

	}
	
}

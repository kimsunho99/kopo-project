package BattleGame;

import java.util.Random;

public class Character2 extends Base {
	int count = 0;
	/*
	 	김유경
	 */
	public Character2() {
		this.name = "꼬부기";
	}

	public Character2(String name) {
		this.name = name;
	}

	@Override
	public void underAttack() {
		if(count == 0) {
		System.out.println("야생의 꼬부기가 나타났다.");
		count++;
		}
		
		System.out.println(this.name + " 공격당함");
		Random random = new Random();
		int attckNum = random.nextInt(6);
		if (attckNum == 0) {
			System.out.println("아! 따가워.");
			System.out.println(this.name +"가 공격을 피하지 못했습니다.(체력 5감소)");
			this.hp -= 5;
		} else if (attckNum == 1) {
			System.out.println(this.name + "가 빠르게 헤엄쳐 공격을 피했습니다.");
			System.out.println(this.name + "는 물이 좋아좋아(체력 15증가)");
			this.hp += 15;
		} else if (attckNum == 2) {
			System.out.println("목말라요 물주세요!!");
			System.out.println(this.name + "가 수분을 빼앗겼습니다.(체력 20감소)");
			this.hp -= 20;
		} else if (attckNum == 3) {
			System.out.println("꼬부기 살려~~");
			System.out.println(this.name + "가 치명상을 입었습니다.(체력 35감소)");
			this.hp -= 35;
		} else if (attckNum == 4) {
			System.out.println("물이다. 물이다. " + this.name + " 물 발견.");
			System.out.println(this.name + "가 공격을 피했습니다.(체력감소 0)");
			this.hp -= 0;
		} else {
			System.out.println("뿌엥ㅠㅠ " + this.name + " 즉사");
			this.hp -= this.hp;
		}
		
		if (this.hp < 0) {
			this.hp = 0;
		}

	}

}

package BattleGame;

import java.util.Random;

public class Character3 extends Base {
	int count = 0;

	/*
 		김태훈
	*/
	public Character3() {
		this.name = "도박몬";
	}
	public Character3(String name) {
		this.name = name;
	}
	
	
	public void underAttack() {
		if(count == 0) {
			System.out.println("판이 깔렸다 " + this.name + "이 돈냄새를 맡았다.");
			count++;
			}
		
		System.out.println(this.name+"공격당함");
		Random random = new Random();
		int attckNum = random.nextInt(99)+1;
		int luckyDice = random.nextInt(6)+1;
		if(attckNum <= 10) {
			System.out.println("돈을 크게 잃었습니다.........눈물이 납니다. 체력 10만큼 줄어듭니다.");
			this.hp -=10;
		}else if (attckNum <= 70) {
			System.out.println("돈을 잃었습니다. 체력이 5만큼 줄어드립니다..");
			this.hp -=5;	
		}else if (attckNum <= 96) {
			System.out.println("본전치기입니다. 공격을 회피하였습니다.");
		}else if (attckNum < 100) {
			System.out.println("도박꾼이 역배 승부예측에 성공하셨습니다. 공격을 회피하고 체력을 5만큼 회복합니다.");
			this.hp +=5;
		}else {
			if (luckyDice == 6) {
			System.out.println(this.name+" <대박 당첨> 주사위를 굴려서 6이 나올시 특수효과가 발동됩니다. 그 외에는 주사위 숫자의 3배만큼 체력을 회복합니다.");
			System.out.println("나온 숫자는"+luckyDice+"입니다");			
			System.out.println("대박당첨 효과로 체력이 회복됩니다.");
			this.hp +=100;}
			
			else {
				System.out.println("나온 숫자는"+luckyDice+"입니다. " + luckyDice*3 +" 만큼 체력을 회복합니다.");	
				this.hp +=luckyDice*3;
				}
			}
		
		if (this.hp < 0) {
			this.hp = 0;
		}
			
	}

}
	


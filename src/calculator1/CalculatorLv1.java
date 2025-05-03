package calculator1;

import java.util.Scanner;

public class CalculatorLv1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int firstNum = sc.nextInt();

            System.out.print("두 번째 숫자를 입력하세요: ");
            int secondNum = sc.nextInt();
            
            System.out.print("두 숫자를 연산할 사칙연산 기호를 입력하세요: ");
            char operator = sc.next().charAt(0);

            // 연산 결과값을 저장하는 변수인 result
            int result = 0;

            switch (operator) {
                case '+':
                    result = firstNum + secondNum;
                    System.out.println("연산 결과는 " + result + "입니다.");
                    break;

                case '-':
                    result = firstNum - secondNum;
                    System.out.println("연산 결과는 " + result + "입니다.");
                    break;

                case '*':
                    result = firstNum * secondNum;
                    System.out.println("연산 결과는 " + result + "입니다.");
                    break;

                case '/':
                    if (secondNum == 0) {
                        System.out.println("나눗셈 연산에서 분모에 0이 입력될 수 없습니다!!!");
                        break;
                    }

                    result = firstNum / secondNum;
                    System.out.println("연산 결과는 " + result + "입니다.");
                    break;

                default:
                    System.out.println("입력하신 " + operator + "은 적절한 연산자 기호가 아닙니다!!!");
                    break;
            }

            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            String answer = sc.next();

            /* 사용자가 exit를 입력하였다면, 계산을 멈추기 위해 while 문을 탈출한다. */
            if (answer.equals("exit")) {
                System.out.println("반복문을 종료합니다.");
                break;
            }
        }
    }
}

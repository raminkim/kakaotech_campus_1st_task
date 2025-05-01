package calculator1;

import java.util.Scanner;

/*
while 반복문 활용한 간단한 사칙 연산 계산기

가정:
 - 입력받는 첫 번째, 두 번째 숫자는 int 타입으로 가정한다.

입력 순서:
 1. 첫 번째 숫자 (int)
 2. 두 번째 숫자 (int)
 3. 연산 기호 (+, -, *, / 중 하나)

종료 조건
 - "더 계산하시겠습니까? (exit 입력 시 종료)" 출력 직후에 "exit" 문자열 입력 시 반복문이 종료된다.

예외 처리
 - 연산자가 +, -, *, /가 아닌 다른 기호나 문자를 입력 시, "적절한 연산 기호가 아닙니다"라고 출력
 - 0으로 나누기 시도 시 (= 연산자가 /이며 두 번째 숫자가 0일 경우), "분모에 0이 입력될 수 없습니다"라고 출력
 */
public class CalculatorLv1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("첫 번째 숫자를 입력하세요: ");
            int firstNum = sc.nextInt();

            System.out.print("두 번째 숫자를 입력하세요: ");
            int secondNum = sc.nextInt();
            
            System.out.print("두 숫자를 연산할 사칙연산 기호를 입력하세요: ");
            char operation = sc.next().charAt(0);

            // 연산 결과값을 저장하는 변수인 result
            int result = 0;

            switch (operation) {
                case '+':
                    result = firstNum + secondNum;
                    break;

                case '-':
                    result = firstNum - secondNum;
                    break;

                case '*':
                    result = firstNum * secondNum;
                    break;

                case '/':
                    if (secondNum == 0) {
                        System.out.println("나눗셈 연산에서 분모에 0이 입력될 수 없습니다!!!");
                        break;
                    }

                    result = firstNum / secondNum;
                    break;

                default:
                    System.out.println("입력하신 " + operation + "은 적절한 연산자 기호가 아닙니다!!!");
                    break;
            }

            System.out.println("연산 결과는 " + result + "입니다.");

            System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
            String answer = sc.next();

            /* 사용자가 exit를 입력하였다면, 계산을 멈추기 위해 while 문을 탈출한다. */
            if (answer.equals("exit"))
                break;
        }
    }
}

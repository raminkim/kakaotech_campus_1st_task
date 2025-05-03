package calculator2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

class Calculator {
    private List<Integer> resultList = new ArrayList<>();

    public int calculate(int firstNumber, int secondNumber, char operator) throws Exception {

        switch (operator) {
            case '+':
                this.resultList.add(firstNumber + secondNumber);
                break;

            case '-':
                this.resultList.add(firstNumber - secondNumber);
                break;

            case '*':
                this.resultList.add(firstNumber * secondNumber);
                break;

            case '/':
                if (secondNumber == 0) {
                    throw new Exception("나눗셈 연산에서 분모에 0이 입력될 수 없습니다!!!");
                }

                this.resultList.add(firstNumber / secondNumber);
                break;

            default:
                throw new Exception("입력하신 " + operator + "은 적절한 연산자 기호가 아닙니다!!!");
        }

        return this.resultList.get(resultList.size()-1);
    }

    /* Getter 메서드 구현 (단, 복사본을 제공함으로써 반환받은 List로 직접 수정은 불가능하도록 한다.) */
    public List<Integer> getResultList() {
        return this.resultList.stream().toList();
    }
    
    /* Setter 메서드 구현 (단, 주어진 리스트가 null일 경우, this.resultList를 새로운 ArrayList로 만든다.) */
    public void setResultList(List<Integer> resultList) {
        if (resultList != null) {
            this.resultList = new ArrayList<>(resultList);
        } else {
            this.resultList = new ArrayList<>();
        }
    }

    /* resultResult 메서드 구현 (저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능)  */
    public void removeResult() {
        int removeElement = this.resultList.remove(0);
        System.out.println("removeResult 메서드 실행 결과: 요소 " + removeElement + "를 제거하였습니다!!!");
    }
}

public class CalculatorLv2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        /* Calculator 인스턴스 생성 */
        Calculator cal = new Calculator();
        boolean exitCheck = false;

        while (true) {
            System.out.println();
            try {
                /* 반복문 시작 */
                System.out.print("첫 번째 숫자를 입력하세요: ");
                int num1 = sc.nextInt();
                System.out.print("두 번째 숫자를 입력하세요: ");
                int num2 = sc.nextInt();

                System.out.print("사칙연산 기호를 입력하세요: ");
                char operator = sc.next().charAt(0);

                /* Calculator 클래스 활용해 계산하기 */
                int result = cal.calculate(num1, num2, operator);
                System.out.println("계산 결과는 " + result + "입니다.");

                /* Getter 메서드 활용해보기 */
                List<Integer> resultList = cal.getResultList();
                System.out.println("계산 후 resultList: " + resultList);

                /* removeResult 메서드 활용해보기 */
                cal.removeResult();
                System.out.println("removeResult 호출 후 resultList: " + cal.getResultList());

                /* Setter 메서드 활용해보기 */
                cal.setResultList(resultList);
                System.out.println("삭제 이전의 resultList로 변경 후 resultList: " + cal.getResultList());
            } catch (InputMismatchException e) {
                /* 예) 위 숫자 입력에 대해서 "ㄷ"으로 잘못 입력하는 경우,
                       버퍼에 [ㄷ, \n] 상태이므로, finally의 sc.next()을 통해 잘못된 입력을 받아가는 것을 방지한다.
                */
                sc.next();
                System.out.println("올바른 입력이 아닙니다. " + e);
            } catch (Exception e) {
                System.out.println("오류 발생, " + e);
            } finally {
                System.out.println("더 계산하시겠습니까? (exit 입력 시 종료)");
                String answer = sc.next();
                /* 사용자가 exit를 입력하였다면, 계산을 멈추기 위해 exitCheck 플래그를 true로 변경한다. */
                if (answer.equals("exit"))
                    exitCheck = true;
            }

            /* exitCheck가 true라면 while 반복문을 탈출한다. */
            if (exitCheck) {
                System.out.println("반복문을 종료합니다.");
                break;
            }
        }
    }
}
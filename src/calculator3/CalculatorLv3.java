package calculator3;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

enum OperatorType {
    /* 각 연산자에 대한 상수를 정의 */
    PLUS('+'), MINUS('-'), MULTIPLY('*'), DIVIDE('/');

    private char operator;

    OperatorType(char operator) {
        this.operator = operator;
    }

    public char getOperator() {
        return this.operator;
    }

    public static OperatorType returnOperator(char operator) {
        for (OperatorType ot: values()) {
            if (ot.operator == operator) {
                return ot;
            }
        }
        return null;
    }
}

class ArithmeticCalculator<T extends Number> {
    private List<Double> resultList = new ArrayList<>();

    public double calculate(T firstNumber, T secondNumber, char operator) throws Exception {
        OperatorType ot = OperatorType.returnOperator(operator);
        /* 일치하는 연산자가 없으면, null을 반환하므로 예외처리한다. */
        if (ot == null) {
            throw new Exception("입력하신 " + operator + "은 적절한 연산자 기호가 아닙니다!!!");
        }

        switch (ot) {
            case PLUS:
                this.resultList.add(firstNumber.doubleValue() + secondNumber.doubleValue());
                break;

            case MINUS:
                this.resultList.add(firstNumber.doubleValue() - secondNumber.doubleValue());
                break;

            case MULTIPLY:
                this.resultList.add(firstNumber.doubleValue() * secondNumber.doubleValue());
                break;

            case DIVIDE:
                if (secondNumber.doubleValue() == 0) {
                    throw new Exception("나눗셈 연산에서 분모에 0이 입력될 수 없습니다!!!");
                }

                this.resultList.add(firstNumber.doubleValue() / secondNumber.doubleValue());
                break;
        }

        return this.resultList.get(resultList.size()-1);
    }

    /* Getter 메서드 구현 (단, 복사본을 제공함으로써 반환받은 List로 직접 수정은 불가능하도록 한다.) */
    public List<Double> getResultList() {
        return this.resultList.stream().toList();
    }

    /* Setter 메서드 구현 (단, 주어진 리스트가 null일 경우, this.resultList를 새로운 ArrayList로 만든다.) */
    public void setResultList(List<Double> resultList) {
        if (resultList != null) {
            this.resultList = new ArrayList<>(resultList);
        } else {
            this.resultList = new ArrayList<>();
        }
    }

    /* resultResult 메서드 구현 (저장된 연산 결과들 중 가장 먼저 저장된 데이터를 삭제하는 기능)  */
    public void removeResult() {
        Double removeElement = this.resultList.remove(0);
        System.out.println("removeResult 메서드 실행 결과: 요소 " + removeElement + "를 제거하였습니다!!!");
    }

    /* showResult 메서드 구현 (저장된 연산 결과들 중 Scanner로 입력받은 값보다 큰 결과값 들을 출력)
    * 이를 출력하기 위해 compareTo라는 함수를 사용하였다.
    * 앞의 수가 크면 0보다 크고, 뒤의 수가 크면 0보다 작고, 같으면 0이다.*/
    public void printResultsGreaterThan(T standard) {
        List<Double> ret = resultList.stream()
                            .filter(result -> result.compareTo(standard.doubleValue()) > 0)
                            .collect(Collectors.toList());

        System.out.print("showResult(" + standard + ") 실행 결과: ");

        // ret에 아무 것도 없을 경우, null을 출력한다.
        if (ret.isEmpty()) {
            System.out.println("null");
            return;
        }

        for (double result: ret) {
            System.out.print(result + " ");
        }
        System.out.println();
    }
}

public class CalculatorLv3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /* ArithmeticCalculator 인스턴스 생성
        * 이때 byte, double, float, int, long, short으로 변환할 수 있는 슈퍼클래스인 Number 사용 */
        ArithmeticCalculator<Number> cal = new ArithmeticCalculator<>();
        boolean exitCheck = false;

        while (true) {
            System.out.println();
            try {
                /* 반복문 시작 */
                System.out.print("첫 번째 실수를 입력하세요: ");
                double num1 = sc.nextDouble();
                System.out.print("두 번째 실수를 입력하세요: ");
                double num2 = sc.nextDouble();

                System.out.print("사칙연산 기호를 입력하세요: ");
                char operator = sc.next().charAt(0);

                /* ArithmeticCalculator 클래스 활용해 계산하기 */
                double result = cal.calculate(num1, num2, operator);
                System.out.println("계산 결과는 " + result + "입니다.");

                /* Getter 메서드 활용해보기 */
                List<Double> resultList = cal.getResultList();
                System.out.println("계산 후 resultList: " + resultList);

                /* removeResult 메서드 활용해보기 */
                cal.removeResult();
                System.out.println("removeResult 호출 후 resultList: " + cal.getResultList());

                /* Setter 메서드 활용해보기 */
                cal.setResultList(resultList);
                System.out.println("삭제 이전의 resultList로 변경 후 resultList: " + cal.getResultList());


                System.out.println();
                System.out.print("해당 입력값보다 더 큰 결과값들만 출력합니다. \n기준값으로 할 실수값을 입력해주세요: ");
                double standard = sc.nextDouble();
                cal.printResultsGreaterThan(standard);

            } catch (InputMismatchException e) {
                /* 예) 위 숫자 입력에 대해서 "ㄷ"으로 잘못 입력하는 경우,
                *      버퍼에 [ㄷ, \n] 상태이므로, catch문의 sc.next()을 통해 잘못된 입력을 받아가는 것을 방지한다.
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

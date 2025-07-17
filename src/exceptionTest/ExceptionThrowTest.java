package exceptionTest;

public class ExceptionThrowTest {
    public static void divide(int a, int b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("0으로 나누면 안돼요.");
        }
        int c = a / b;
        System.out.println(c);
    }

    // 오류
    public static void main(String[] args) {
        int a = 10;
        int b = 0;
        try {
            divide(a, b);
        } catch (ArithmeticException e) {
            // throw new ArithmeticException("0으로 나누면 안돼요.");
            // 의 메세지를 받는다.
            System.out.println(e.getMessage());
        }
    }
}

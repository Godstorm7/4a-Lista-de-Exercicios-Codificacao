import java.util.ArrayList;
import java.util.List;

public class Questao2 {

    public static List<String> generateStrings(int n) {
        List<String> result = new ArrayList<>();
        if (n <= 0) {
            return result; // Se N for 0 ou negativo, não gera cadeias
        } else if (n == 1) {
            result.add("c"); // A única cadeia de comprimento 1
            return result;
        }

        // Para N >= 2, geramos apenas "aa" e "bb" para comprimento 2
        if (n > 1) {
            result.add("a".repeat(n));
            result.add("b".repeat(n));
            return result;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = 1; // Altere o valor de N conforme necessário
        List<String> strings = generateStrings(n);
        for (String s : strings) {
            System.out.println(s);
        }
    }
}
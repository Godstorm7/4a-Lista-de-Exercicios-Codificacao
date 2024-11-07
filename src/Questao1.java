import java.util.*;

public class Questao1 {

    private static final Map<Character, String[]> regras = new HashMap<>();

    public static void definirRegras(char estado, String[] possibilidades) {
        regras.put(estado, possibilidades);
    }

    public static boolean pertenceALinguagem(String cadeia) {
        if (cadeia == null || cadeia.isEmpty()) {
            return false;
        }

        return verificaCadeia("S", cadeia, 0) == cadeia.length();
    }

    private static int verificaCadeia(String simbolo, String cadeia, int posicao) {

        if (posicao > cadeia.length()) {
            return -1;
        }

        String[] possibilidades = regras.get(simbolo.charAt(0));
        if (possibilidades == null) {
            return -1;
        }

        for (String possibilidade : possibilidades) {
            int novaPosicao = posicao;

            for (char c : possibilidade.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    novaPosicao = verificaCadeia(String.valueOf(c), cadeia, novaPosicao);
                    if (novaPosicao == -1) {
                        break;
                    }
                } else if (novaPosicao < cadeia.length() && c == cadeia.charAt(novaPosicao)) {
                    novaPosicao++;
                } else {
                    novaPosicao = -1;
                    break;
                }
            }

            if (novaPosicao != -1 && novaPosicao == cadeia.length()) {
                return novaPosicao;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        definirRegras('S', new String[]{"aA", "bB"});
        definirRegras('A', new String[]{"aA", ""});
        definirRegras('B', new String[]{"bB", ""});

        System.out.println(pertenceALinguagem("a"));    // true
        System.out.println(pertenceALinguagem("aa"));   // true
        System.out.println(pertenceALinguagem("b"));    // true
        System.out.println(pertenceALinguagem("bb"));   // true
        System.out.println(pertenceALinguagem("aab"));  // false
        System.out.println(pertenceALinguagem("abb"));  // false
        System.out.println(pertenceALinguagem("aaa"));   // true
        System.out.println(pertenceALinguagem("bba"));   // false
        System.out.println(pertenceALinguagem(""));      // false
        System.out.println(pertenceALinguagem(null));    // false
    }
}
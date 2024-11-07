import java.util.*;

class Questao3 {
    public String simbolo;
    public List<String> regrasDeProducao;

    public Questao3(String simbolo) {
        this.simbolo = simbolo;
        this.regrasDeProducao = new ArrayList<>();
    }

    public void definirRegra(String regra) {
        regrasDeProducao.add(regra);
    }
}

class EstruturaGramatical {
    public String simboloInicial;
    public Map<String, Questao3> producoes;

    public EstruturaGramatical(String simboloInicial) {
        this.simboloInicial = simboloInicial;
        this.producoes = new HashMap<>();
    }

    public void inicarProducao(String simbolo, String regra) {
        producoes.putIfAbsent(simbolo, new Questao3(simbolo));
        producoes.get(simbolo).definirRegra(regra);
    }
}

class AnalisadorDeGramatica {

    public static boolean pertenceALinguagem(EstruturaGramatical gramatica, String cadeia) {
        return verificaPertinencia(gramatica, gramatica.simboloInicial, cadeia);
    }

    private static boolean verificaPertinencia(EstruturaGramatical gramatica, String simboloAtual, String cadeia) {

        if (cadeia.isEmpty()) {
            for (String regra : gramatica.producoes.getOrDefault(simboloAtual, new Questao3("")).regrasDeProducao) {
                if (regra.equals("ε")) {
                    return true;
                }
            }
            return false;
        }

        Questao3 producaoAtual = gramatica.producoes.get(simboloAtual);
        if (producaoAtual == null) {
            return false;
        }

        for (String regra : producaoAtual.regrasDeProducao) {
            if (regra.isEmpty()) continue;

            if (Character.isLowerCase(regra.charAt(0))) {
                char terminal = regra.charAt(0);
                if (!cadeia.isEmpty() && cadeia.charAt(0) == terminal) {
                    if (verificaPertinencia(gramatica, regra.substring(1), cadeia.substring(1))) {
                        return true;
                    }
                }
            } else {
                if (verificaPertinencia(gramatica, regra, cadeia)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {

        EstruturaGramatical gramatica = new EstruturaGramatical("S");
        gramatica.inicarProducao("S", "aA");
        gramatica.inicarProducao("S", "bB");
        gramatica.inicarProducao("A", "aA");
        gramatica.inicarProducao("A", "bB");
        gramatica.inicarProducao("A", "ε");
        gramatica.inicarProducao("B", "bB");
        gramatica.inicarProducao("B", "aA");
        gramatica.inicarProducao("B", "ε");


        System.out.println(pertenceALinguagem(gramatica, "aaa"));
        System.out.println(pertenceALinguagem(gramatica, "bbb"));
        System.out.println(pertenceALinguagem(gramatica, "bba"));
        System.out.println(pertenceALinguagem(gramatica, "bba"));
        System.out.println(pertenceALinguagem(gramatica, "baba"));
        System.out.println(pertenceALinguagem(gramatica, ""));
        System.out.println(pertenceALinguagem(gramatica, "ε"));
    }
}

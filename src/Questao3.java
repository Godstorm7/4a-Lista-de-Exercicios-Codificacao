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
    public Map<String, Questao3> possibilidades;

    public EstruturaGramatical(String simboloInicial) {
        this.simboloInicial = simboloInicial;
        this.possibilidades = new HashMap<>();
    }

    public void inicarProducao(String simbolo, String regra) {
        possibilidades.putIfAbsent(simbolo, new Questao3(simbolo));
        possibilidades.get(simbolo).definirRegra(regra);
    }
}

class AnalisadorDeGramatica {

    public static boolean pertenceALinguagem(EstruturaGramatical gramatica, String cadeia) {
        return verificaPossibilidade(gramatica, gramatica.simboloInicial, cadeia);
    }

    private static boolean verificaPossibilidade(EstruturaGramatical gramatica, String simboloAtual, String cadeia) {

        if (cadeia.isEmpty()) {
            for (String regra : gramatica.possibilidades.getOrDefault(simboloAtual, new Questao3("")).regrasDeProducao) {
                if (regra.equals("ε")) {
                    return true;
                }
            }
            return false;
        }

        Questao3 possibilidadeAtual = gramatica.possibilidades.get(simboloAtual);
        if (possibilidadeAtual == null) {
            return false;
        }

        for (String regra : possibilidadeAtual.regrasDeProducao) {
            if (regra.isEmpty()) continue;

            if (Character.isLowerCase(regra.charAt(0))) {
                char terminal = regra.charAt(0);
                if (!cadeia.isEmpty() && cadeia.charAt(0) == terminal) {
                    if (verificaPossibilidade(gramatica, regra.substring(1), cadeia.substring(1))) {
                        return true;
                    }
                }
            } else {
                if (verificaPossibilidade(gramatica, regra, cadeia)) {
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

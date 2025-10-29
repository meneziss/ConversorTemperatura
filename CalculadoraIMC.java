public class CalculadoraIMC {

    public double calcularIMC(double peso, double altura) {
        if (altura <= 0) {
            return 0;
        }
        return peso / (altura * altura);
    }

    public String classificarIMC(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        } else if (imc < 25.0) {
            return "Normal";
        } else if (imc < 30.0) {
            return "Sobrepeso";
        } else {
            return "Obesidade";
        }
    }

    static class CalculadoraIMCTest {
        
        private final CalculadoraIMC calculadora = new CalculadoraIMC();
        private int testesExecutados = 0;
        private int testesFalhados = 0;
        private static final double DELTA = 0.01;

        public static void main(String[] args) {
            CalculadoraIMCTest runner = new CalculadoraIMCTest();
            
            System.out.println("--- Executando Testes da CalculadoraIMC ---");
            
            runner.testeCalculoExato();
            runner.testeClassificacaoAbaixo();
            runner.testeClassificacaoNormalInferior();
            runner.testeClassificacaoNormalSuperior();
            runner.testeClassificacaoSobrepesoInferior();
            runner.testeClassificacaoSobrepesoSuperior();
            runner.testeClassificacaoObesidade();
            
            System.out.println("\n---------------------------------------------------");
            System.out.println("Total de Testes Executados: " + runner.testesExecutados);
            System.out.println("Total de Testes Falhados: " + runner.testesFalhados);
            if (runner.testesFalhados == 0) {
                System.out.println("Resultado: SUCESSO! Todos os testes passaram.");
                System.exit(0); 
            } else {
                System.out.println("Resultado: FALHA! " + runner.testesFalhados + " teste(s) falhou(ram).");
                System.exit(1);
            }
        }

        private void verifica(double esperado, double atual, String nomeDoTeste) {
            testesExecutados++;
            if (Math.abs(esperado - atual) > DELTA) {
                testesFalhados++;
                System.out.println("FALHOU: " + nomeDoTeste + " | Esperado: " + esperado + ", Atual: " + atual);
            } else {
                System.out.println("PASSOU: " + nomeDoTeste);
            }
        }
        
        private void verifica(String esperado, String atual, String nomeDoTeste) {
            testesExecutados++;
            if (esperado == null && atual == null || esperado != null && esperado.equals(atual)) {
                System.out.println("PASSOU: " + nomeDoTeste);
            } else {
                testesFalhados++;
                System.out.println("FALHOU: " + nomeDoTeste + " | Esperado: '" + esperado + "', Atual: '" + atual + "'");
            }
        }

        void testeCalculoExato() {
            double imc = calculadora.calcularIMC(70, 1.75); // 70 / (1.75 * 1.75) = 22.857...
            verifica(22.85, imc, "Cálculo de IMC (70kg, 1.75m)");
        }
        
        void testeClassificacaoAbaixo() {
            String c = calculadora.classificarIMC(18.49);
            verifica("Abaixo do peso", c, "Classificação: Abaixo do peso (18.49)");
        }

        void testeClassificacaoNormalInferior() {
            String c = calculadora.classificarIMC(18.5);
            verifica("Normal", c, "Classificação: Normal - Borda Inferior (18.5)");
        }
        
        void testeClassificacaoNormalSuperior() {
            String c = calculadora.classificarIMC(24.99);
            verifica("Normal", c, "Classificação: Normal - Borda Superior (24.99)");
        }

        void testeClassificacaoSobrepesoInferior() {
            String c = calculadora.classificarIMC(25.0);
            verifica("Sobrepeso", c, "Classificação: Sobrepeso - Borda Inferior (25.0)");
        }

        void testeClassificacaoSobrepesoSuperior() {
            String c = calculadora.classificarIMC(29.99);
            verifica("Sobrepeso", c, "Classificação: Sobrepeso - Borda Superior (29.99)");
        }

        void testeClassificacaoObesidade() {
            String c = calculadora.classificarIMC(30.0);
            verifica("Obesidade", c, "Classificação: Obesidade - Borda Inferior (30.0)");
        }
    }
}
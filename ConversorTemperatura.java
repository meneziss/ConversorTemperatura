public class ConversorTemperatura {

    public double celsiusParaFahrenheit(double celsius) {
        double fahrenheit = celsius * 1.8 + 32;
        return fahrenheit;
    }

    public double fahrenheitParaCelsius(double fahrenheit) {
        double celsius = (fahrenheit - 32) / 1.8;
        return celsius;
    }

    static class ConversorTemperaturaTest {
        
        private final ConversorTemperatura conversor = new ConversorTemperatura();
        private int testesExecutados = 0;
        private int testesFalhados = 0;
        
        private static final double DELTA = 0.001; 

        public static void main(String[] args) {
            ConversorTemperaturaTest runner = new ConversorTemperaturaTest();
            
            System.out.println("--- Executando Testes de Conversão de Temperatura ---");
            
            runner.testePontoEbulição();
            runner.testePontoCongelamento();
            runner.testePontoDeEncontro();
            runner.testeZeroAbsolutoInverso();
            runner.testeValorDecimalInverso();
            
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
                System.out.println("FALHOU: " + nomeDoTeste);
                System.out.println("  Esperado: " + esperado);
                System.out.println("  Atual: " + atual);
            } else {
                System.out.println("PASSOU: " + nomeDoTeste);
            }
        }

        void testePontoEbulição() {
            double esperado = 212.0;
            double atual = conversor.celsiusParaFahrenheit(100.0);
            verifica(esperado, atual, "C->F: Ponto de Ebulição (100C)");
        }

        void testePontoCongelamento() {
            double esperado = 32.0;
            double atual = conversor.celsiusParaFahrenheit(0.0);
            verifica(esperado, atual, "C->F: Ponto de Congelamento (0C)");
        }

        void testePontoDeEncontro() {
            double esperado = -40.0;
            double atual = conversor.celsiusParaFahrenheit(-40.0);
            verifica(esperado, atual, "C->F: Ponto de Encontro (-40C)");
        }
        
        void testeZeroAbsolutoInverso() {
            double esperado = -273.15;
            double atual = conversor.fahrenheitParaCelsius(-459.67);
            verifica(esperado, atual, "F->C: Zero Absoluto (-459.67F)");
        }
        
        void testeValorDecimalInverso() {
            double esperado = 10.3;
            double atual = conversor.fahrenheitParaCelsius(50.54);
            verifica(esperado, atual, "F->C: Valor Decimal (50.54F)");
        }
    }
}
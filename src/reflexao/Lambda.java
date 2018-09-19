package reflexao;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Lambda {

    @FunctionalInterface
    interface ValorNUmerico {

        boolean teste(int n);
    }

    @FunctionalInterface
    interface ValorNumerico2 {

        boolean teste(int n, int m);
    }

    public static void filtro(final List<String> lista, final Predicate<String> condicao) {

        lista
                        .stream()
                        .filter((str) -> condicao.test(str))
                        .forEach(System.out::println);
    }

    public static void Capslock(final List<String> lista, final Predicate<String> funcao) {

        lista
                        .stream()
                        .filter(str -> funcao.test(str))
                        .map(str -> str.toUpperCase())
                        .forEach(System.out::println);
    }

    @FunctionalInterface
    interface Calculator {

        public void calculate(int x, int y);
    }

    @FunctionalInterface
    interface Calculator1 {

        public int calculate(int x, int y);
    }

    @FunctionalInterface
    interface Calculator2 {

        public int calculate(int x);

        default Calculator2 andThen(final Calculator2 after) {

            return (final int x) -> after.calculate(this.calculate(x));
        }

        default Calculator2 soma(final Calculator2 after) {

            return (final int x) -> after.calculate(x) + x;
        }

        default Calculator2 multi(final Calculator2 after) {

            return (final int x) -> after.calculate(x) * x;
        }
    }

    @FunctionalInterface
    interface Calculator3 {

        public void calculate(int x);
    }

    public static void main(final String[] args) {

        // Teste Interfaces Funcionais
        final ValorNUmerico ispar = (i) -> (i % 2) == 0;

        final ValorNumerico2 isdiv = (x, y) -> (x % y) == 0;

        final Calculator calc = (e, f) -> System.out.println("Calc: " + (int) Math.pow(e, f));

        final Calculator1 calc1 = (e, f) -> (int) Math.pow(e, f);

        final Calculator2 calc2 = e -> e * e;

        final Calculator3 calc3 = e -> System.out.println("Calc3: " + (e * e));

        System.out.println("TESTE PAR");
        System.out.println(ispar.teste(89));
        System.out.println(ispar.teste(50));
        System.out.println("\nTESTE DIV");
        System.out.println(isdiv.teste(10, 2));
        System.out.println(isdiv.teste(10, 3));

        final List<String> lista1 = Arrays.asList(
                        "SC", "RJ", "PA", "MA",
                        "CE", "PI", "AM", "Mato Grosso do Sul",
                        "Mato Grosso");

        System.out.println("\nIMPRIME FOREACH");
        lista1.forEach(System.out::println);

        System.out.println("\nESTADOS QUE INICIAM COM A LETRA P");
        Lambda.filtro(lista1, (str) -> str.startsWith("P"));

        System.out.println("\nESTADOS QUE FINALIZAM COM A LETRA A");
        Lambda.filtro(lista1, (s) -> s.endsWith("A"));

        System.out.println("\nIMPRIME TODA A LISTA");
        Lambda.filtro(lista1, s -> true);

        System.out.println("\nNÃO IMPRIME A LISTA");
        Lambda.filtro(lista1, s -> false);

        System.out.println("\nIMPRIME COM 10 CARACTERES OU MAIS");
        Lambda.filtro(lista1, s -> s.length() >= 10);

        System.out.println("\nIMPRIME LISTA EM MAIÚSCULO");
        Lambda.Capslock(lista1, s -> String.class.isAssignableFrom(s.getClass()));

        System.out.println("\nIMPRIME CALCULATOR");
        calc.calculate(7, 2);

        System.out.println("Calc1: " + calc1.calculate(11, 2));

        System.out.println("Calc2: " + calc2.calculate(7));

        System.out.println("Calc2.andThen: " + calc2.andThen(calc2).calculate(7));

        System.out.println("Calc2.soma: " + calc2.soma(calc2).calculate(7));

        System.out.println("Calc2.multi: " + calc2.multi(calc2).calculate(7));

        calc3.calculate(11);
    }
}
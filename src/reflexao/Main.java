package reflexao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

public class Main {

    public static void main(final String[] args)
                    throws InstantiationException, IllegalAccessException, NoSuchFieldException, SecurityException, ClassNotFoundException,
                    IllegalArgumentException, InvocationTargetException, NoSuchMethodException {

        final Object pessoa = Pessoa.class.getDeclaredConstructor().newInstance();

        final Object pessoa2 = Class.forName("reflexao.Pessoa").getDeclaredConstructor().newInstance();

        Main.pupulaPessoa(pessoa);
        Main.pupulaPessoa(pessoa2);
        Main.imprimeCampos(pessoa);
        Main.imprimeCampos(pessoa2);
        Main.imprimeConstrutores(Pessoa.class);
        Main.imprimeMetodos(Pessoa.class);

        final Method m = pessoa2.getClass().getMethod("emagrecer", new Class[] { String.class, double.class });
        m.invoke(pessoa2, new Object[] { "Pedro", 0.01 });
        System.out.println(pessoa2);
    }

    private static <T> Object pupulaPessoa(final T objeto) throws IllegalArgumentException, IllegalAccessException {

        int i = 0;
        for (final Field campo : objeto.getClass().getDeclaredFields()) {
            i++;
            campo.setAccessible(true);
            if (String.class.isAssignableFrom(campo.getType())) {
                campo.set(objeto, "Campo " + i);
            } else if (double.class.isAssignableFrom(campo.getType())) {
                campo.setDouble(objeto, new Random().nextDouble());
            }
        }

        return objeto;
    }

    private static <T> void imprimeCampos(final T objeto)
                    throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {

        System.out.println(objeto.toString());
        // for (final Field field : objeto.getClass().getFields()) {
        for (final Field campo : objeto.getClass().getDeclaredFields()) {
            campo.setAccessible(true);
            System.out.println("VALOR OBTIDO COM field.get(object): " + campo.get(objeto));
            System.out.println("NOME DO CAMPO: " + campo.getName());
            System.out.println("DESCRIÇÃO DO CAMPO: " + campo.toString() + "\n");
        }

        System.out.println("CAMPO OBTIDO COM getdeclaredfield(String): " + objeto.getClass().getDeclaredField("nome").toGenericString() + "\n");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    private static void imprimeConstrutores(final Class<?> clazz) {

        System.out.println("CONSTRUTORES:");
        for (final Constructor<?> construtor : clazz.getDeclaredConstructors()) {
            System.out.println(construtor.toString());

        }
    }

    public static void imprimeMetodos(final Class<?> clazz) {

        System.out.println("\nMÉTODOS:");
        for (final Method metodo : clazz.getDeclaredMethods()) {
            System.out.println(metodo.toString());

            // final Class<?> tipoderetorno = metodo.getReturnType();
            // final String nome = metodo.getName();
            // System.out.print(" ");
            // // imprime modificadores, tipo de retorno e nome do método
            // final String modificadores = Modifier.toString(metodo.getModifiers());
            // if (modificadores.length() > 0) {
            // System.out.print(modificadores + " ");
            // }
            // System.out.print(tipoderetorno.getName() + " " + nome + "(");
            // // imprime parameter types
            // final Class<?>[] paramTypes = metodo.getParameterTypes();
            // for (int j = 0; j < paramTypes.length; j++) {
            // if (j > 0) {
            // System.out.print(", ");
            // }
            // System.out.print(paramTypes[j].getName());
            // }
            // System.out.println(");");

        }

    }

}
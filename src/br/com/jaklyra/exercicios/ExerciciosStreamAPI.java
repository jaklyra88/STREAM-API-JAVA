package br.com.jaklyra.exercicios;

//OPERAÇÕES INTERMEDIÁRIAS - SÃO OPERAÇÕES QUE RETORNAM UM STREAM E PODEMOS ENCADEAR OPERAÇÕES
//VARIAS OPERAÇÕES INTERMEDIÁRIAS UMA APÓS A OUTRA

//OPERAÇÕES TERMINAIS - PODEMOS UTILIZAR SOMENTE UMA ATRÁS DA OUTRA - RETORNAM UM OBJETO OU
//UM VALOR

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;


public class ExerciciosStreamAPI {

    public static void main(String[] args){

        List<String> numerosAleatorios = Arrays.asList("1","0","4","1","2","3","9","6","5");


        //
        System.out.println("Imprima todos os elementos desta lista de Stream: ");
        System.out.println("");

        //FOREACH DA AO CONSUMER O MÉTODO QUE ELE IRÁ UTILIZAR É O ACCEPT E SE TRATA DE UM VOID
        //PEGA CADA ELEMENTO DA STREAM (DO TIPO STRING) E IMPRIME


        //FOREACH USA O MÉTODO ACCEPT E IMPRIME TODOS ELEMENTOS DA STREAM

        numerosAleatorios.stream().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);

            }
        });

        //STREAM NÃO MODIFICA A FONTE

        System.out.println("Imprimindo todos os elemnetos da Stream com função Lambda: ");
        System.out.println("");

        //FUNÇÃO LAMBDA
        numerosAleatorios.stream().forEach(s -> System.out.println(s));

        //FUNÇÃO LAMBDA - O PRÓRPIO LIST POSSUI O FOREACH SEM PRECISAR UTILIZAR A STREAM
        numerosAleatorios.forEach(s -> System.out.println(s));

        //FUNÇÃO LAMBDA TRANSFORMADA EM REFERENCE METHOD
        numerosAleatorios.forEach(System.out::println);

        //UTILIZANDO O LIMIT QUE RECE UM LONG QUE É A QUANTIDADE DE ELEMENTOS QUE QUEREMOS PEGAR
        System.out.println("Pegue os cinco primeiros números e coloque dentro de um set: ");
        numerosAleatorios.stream()
                .limit(5) //limit.(numero de elemento que desejamos)
                .collect(Collectors.toSet()) //collect (colete os cinco priemiros elementos da list de Stream e coloque dentro de um set
                .forEach(System.out::println);
        //SÓ IRÁ EXIBIR 4 NÚMEROS, POIS O SET NÃO ACEITA NÚMEROS REPETIDOS,
        //VAI EXIBIR O NÚMERO 1 SOMENTE UMA VEZ


        System.out.println("");
        System.out.println("Transforme esta lista de String em uma lista de números Inteiros: ");
        System.out.println("");

        //TRANSFORMANDO UMA LISTA DE TREAM EM UMA LIST DE NÚMEROS INTEIROS
       // numerosAleatorios.stream()//STREAM<STRING>
                //.map(Integer::parseInt)//REFERENCE METHOD - <STREAM<INTEGER>
               // .collect(Collectors.toList())//Pegando o Stream e colocando dentro de um list de números inteiros - LIST<INTEGER>
               // .forEach(System.out::println);
       // ;

        //COLOCANDO A LISTA EM UMA VARIÁVEL - STEAM NÃO ALTERA OS NÚEMROS ALEATÓRIOS
        List<Integer> collectList = numerosAleatorios.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        ;

        System.out.println(collectList);

                //FORMA COMPLETA
                //.map(new Function<String, Integer>() { //Recebe uma stream e retorna um objeto( Integer)
                //Map recebe uma interface Function  - Há varias formas de utilizar - RETORNA UMA STREAM DE ELEMENTOS-
                //Um fluxo de elementos que iremos informar
                    //@Override
                    //public Integer apply(String s) {
                       //return Integer.parseInt(s);
                    //}
                //})


        System.out.println("");
        System.out.println("Pegue os números pares e maiores que 2 e coloque em uma lista: ");

        /*
        List<Integer> listParesMaioresDois = numerosAleatorios.stream()
                //PEGA CADA VOLAR DA LISTA E TRSNFORMA EM UM TIPO
                .map(Integer::parseInt) //STREAM DE NÚMEROS INTEIROS
                //FILTER PEDE UM PREDICATE
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer i) {
                        if(i % 2 == 0 && i > 2){
                            return true;
                        }
                        return false;
                    }
                }).collect(Collectors.toList());

                  System.out.println(listParesMaioresDois);

         */

        //OU SIMPLIFICANDO

        List<Integer> listaParesMaiosresDois = numerosAleatorios.stream()
                .map(Integer::parseInt)
                .filter(i -> i % 2 ==0 && i > 2)
                .collect(Collectors.toList());
        System.out.println(listaParesMaiosresDois);





        System.out.println("");
        System.out.println("Mostre a média dos números: ");

        //numerosAleatorios.stream()
                       // .mapToInt(new ToIntFunction<String>() {
                          //  @Override
                          //  public int applyAsInt(String s1) {
                          //      return Integer.parseInt(s1);
                          // }
                      //  });

        //OU SIMPLIFICANDO

        // RETORNA UM OPTIONAL DOUBLE - NECESSÁRIO UTILIZAR UMA FUNÇÃO DO OPTIONAL
        //OPTIONAL SERVE PARA TRABALHAR EVITAR RETORNO NULL
        //IFPRESENT É UM CONSUMER E RETORNA UM VOID

        //numerosAleatorios.stream()
                //.mapToInt(Integer::parseInt)
               // .average() //FAZ A MÉDIA DOS VALORES
                //.ifPresent(new DoubleConsumer() { //SE DER TUDO CERTO
                  //  @Override
                   // public void accept(double v) {
                      //  System.out.println(v);//RETORNA O VALOR DA MÉDIA
                   // }
                //});//SE SER ERRO NÃO RETORNA NADA


        //OU SIMPLIFICANDO

        //PODE SER UTILIZADO COM TODAS AS OPERAÇÕES + - * / > <  E ETC
        numerosAleatorios.stream()
                .mapToInt(Integer::parseInt)
                .average()
                .ifPresent(System.out::println);

        System.out.println("");
        System.out.println("remova os valores ímpares: ");

        List<Integer> numerosAleatoriosInteger = numerosAleatorios.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        //REMOVE IF NÃO É UM STREAM, É UM MÉTODO DE LIST E ALTERA A LISTA -  ELE RECEBE UM PREDICATE

        //numerosAleatoriosInteger.removeIf(new Predicate<Integer>() {
           // @Override
           // public boolean test(Integer integer) {
              //  if( integer % 2 != 0){
                 //   return true;
              //  }
               // return false;
           // }
       // });

        //System.out.println(numerosAleatoriosInteger);


        //OU SIMPLIFICANDO

        numerosAleatoriosInteger.removeIf(integer -> (integer % 2 != 0));
        System.out.println(numerosAleatoriosInteger);


        System.out.println("");
        System.out.println("Ignore os 3 primeiros elementos da lista e imprima o restante");

        List<Integer> ignoreTresPrimeiros = numerosAleatorios.stream()
                        .map(Integer::parseInt)
                                .skip(3)
                                        .collect(Collectors.toList());


        System.out.println(ignoreTresPrimeiros);

        System.out.println("");
        System.out.println("Retirando os números repetidos da lista , quantos numeros ficam?");

        List<Integer> numerosSemRepetir = numerosAleatorios.stream()
                        .map(Integer::parseInt)
                        .distinct()
                        .collect(Collectors.toList());

        System.out.println(numerosSemRepetir);



        System.out.println("");
        System.out.println("Mostre o menor valor da lista");

         numerosAleatorios.stream()
                        .mapToInt(Integer::parseInt)
                        .min()
                        .ifPresent(System.out::println);


        System.out.println("");
        System.out.println("Mostre o maior valor da lista");

        numerosAleatorios.stream()
                        .mapToInt(Integer::parseInt)
                        .max()
                        .ifPresent(System.out::println);




        System.out.println("");
        System.out.println("Pegue apenas os números impares e some");

        List<Integer> listaNumerosImpares = numerosAleatorios.stream()
                .map(Integer::parseInt)
                .filter(i -> i % 2 != 0)
                .collect(Collectors.toList());

        int soma = listaNumerosImpares.stream()
                        .mapToInt(Integer::intValue)
                                .sum();

        System.out.println(soma);



        System.out.println("");
        System.out.println("Mostre a lista na ordem numérica");

        //FORMA 1
        List<Integer> listaOrdemNumerica = numerosAleatorios.stream()
                .map(Integer::parseInt)
                .sorted(Comparator.naturalOrder())
                        .collect(Collectors.toList());

        System.out.println(listaOrdemNumerica);

        //FORMA 2
        List<Integer> listaOrdemNumerica1 = numerosAleatorios.stream()
                .map(Integer::parseInt)
                .sorted()
                .collect(Collectors.toList());


        System.out.println(listaOrdemNumerica1);




        System.out.println("");
        System.out.println("Agrupe os valores impares multiplos de 3 e de 5 ");
       //dica  : collect(Collectors.groupingBy(new Function())

        //PRIMEIRO FAZ O FILTRO PARA SELECIONAR AMBOS OS MÚLTIPLOS
        List<Integer> numerosmultiplos = numerosAleatorios.stream()
                .map(Integer::parseInt)
                .filter(i -> i !=0 && i % 3 ==0 ||  i != 0 && i % 5 == 0)
                .collect(Collectors.toList());

        System.out.println(numerosmultiplos);

       //FAZ O COLLECT POR GRUPO, SELECIONANDO SOMENTE UMA DAS OPERAÇÕES, DESTA FORMA
       //ELE IRÁ SEPARAR OS MULTIPLOS DE 3 COMO O GRUPO TRUE E OS MÚLTIPLOS DE 5 COMO FALSE
       Map<Boolean, List<Integer>> gruposMultiplos = numerosmultiplos.stream()
                .collect(Collectors.groupingBy(i -> (i % 3 ==0)));

       System.out.println(gruposMultiplos);


    }


    }


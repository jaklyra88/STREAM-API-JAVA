package br.com.jaklyra.exemplos;

/*

    Dados os seguintes informações dde id e contato, crie um dicionário e ordene este dicionário exibindo
    (nome -id - nome Contato

    id = 1 - Contato = nome: Simba , número: 2222;
    id = 4 - Contato = nome: Cami , número: 5555;
    id = 3 - Contato = nome: Jan, número: 1111;
 */


import java.util.*;
import java.util.function.Function;

public class RefatoracaoOrdenacaoStreamAPI {

    public static void main(String[]args){

        //HASHMAP PARA GERAR EM ORDEM ALEATÓRIA
        System.out.println("--\tOrdem Aleatória\t--");
        Map<Integer,Contato> agenda = new HashMap<>(){{
            put(1, new Contato("Simba", 2222));
            put(4, new Contato("Cami", 5555));
            put(3, new Contato("Jan", 1111));

        }};

        System.out.println("");
        System.out.println("AGENDA");
        System.out.println("");

        for (Map.Entry<Integer, Contato> entry: agenda.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
        }

        //LINKEDHASH EXIBE POR ORDEM DE INSERÇAÕ
        System.out.println("");
        System.out.println("--\tOrdem de inserção\t--");
        Map<Integer, Contato> agenda1 = new LinkedHashMap<>(){{
            put(1 , new Contato("Simba", 2222));
            put(4, new Contato("Cami" , 5555));
            put(3 , new Contato("Jan" , 1111));
        }};

        for (Map.Entry<Integer, Contato> entry : agenda1.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());

        }

        //ORGANIZANDO POR ORDEM DE ID
        System.out.println("");
        System.out.println("--\tOrdem id\t--");
        System.out.println("");
        Map<Integer, Contato> agenda2 = new TreeMap<>(agenda);
        System.out.println(agenda2);

        for (Map.Entry<Integer, Contato> entry: agenda2.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getNome());

        }


        System.out.println("");
        System.out.println("--\tOrdem por numero de telefone\t--");
        System.out.println("");
        //CRIANDO UM SET - INCIANDO COM TREESET - COM CLASSE
        //Set<Map.Entry<Integer, Contato>> set = new TreeSet<>(new ComparatorTelefone());
        //set.addAll(agenda.entrySet());

        //CRIANDO UM SET COM CLASSE ANONIMA

        //COMPARATOR TEM O MÉTODO COMPARE QUE RECEBE UMA INTERFACE FUNCIONAL
        /*Set<Map.Entry<Integer, Contato>> set = new TreeSet<>(new Comparator<Map.Entry<Integer, Contato>>() {
            @Override
            public int compare(Map.Entry<Integer, Contato> cont1, Map.Entry<Integer, Contato> cont2) {
                return Integer.compare(cont1.getValue().getNumero(), cont2.getValue().getNumero());
            }
        });


         */

        //IMPLEMENTANDO  COMPARATOR COM O MÉTODO COMPARE QUE PERMITE FAZER COMPARAÇÃOES INFORMANDO
        //UMA INTERFACE FUNCIONAL (A FUNCTION )
        /*Set<Map.Entry<Integer, Contato>> set = new TreeSet<>(Comparator.comparing
                //NECESSÁRIO INFORMAR O TIPO QUE PRECISAMOS QUE SEJA FEITA A COMPARAÇÃO
                (new Function<Map.Entry<Integer, Contato>, Integer>() {
                    @Override
                    public Integer apply(Map.Entry<Integer, Contato> cont) {
                        //RETORNO PARA FAZER A COMPARAÇÃO
                        return cont.getValue().getNumero();
                    }
                }));


         */

        //UTILIZAÇÃO DE UMA LAMBDA -  ESTRUTURA = ARGUMENTO -> CORPO

        Set<Map.Entry<Integer, Contato>> set = new TreeSet<>
                (Comparator.comparing( tel -> tel.getValue().getNumero()));



        set.addAll(agenda.entrySet());

        for (Map.Entry<Integer, Contato> entry: set) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getNome() +
                    " - " + entry.getValue().getNumero());

        }


        System.out.println("");
        System.out.println("--\tOrdem por nome do contato\t--");
        System.out.println("");
        Set<Map.Entry<Integer, Contato>> set1 = new TreeSet<>
                (Comparator.comparing(cont -> cont.getValue().getNome()));


        set1.addAll(agenda.entrySet());

        for (Map.Entry<Integer, Contato> entry:set1 ) {
            System.out.println(entry.getKey() + " - " + entry.getValue().getNome() + " - "
            + entry.getValue().getNumero());

        }


    }
}

    class Contato{

    private String nome;
    private Integer numero;

        public Contato(String nome, Integer numero) {
            this.nome = nome;
            this.numero = numero;
        }


        public String getNome() {
            return nome;
        }

        public Integer getNumero() {
            return numero;
        }

        @Override
        public String toString() {
            return "{" +
                    "nome='" + nome + '\'' +
                    ", numero=" + numero +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Contato contato = (Contato) o;
            return nome.equals(contato.nome) && numero.equals(contato.numero);
        }

        @Override
        public int hashCode() {
            return Objects.hash(nome, numero);
        }
    }


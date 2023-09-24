import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args){
        Person person1 = new Person(10, "Bartek");
        Person person2 = new Person(30, "Andrzej");
        Person person3 = new Person(40, "Wandzia");
        List<Person> personList = List.of(person1, person2, person3);
        List<Person> anotherPersonList = new ArrayList<>();
        anotherPersonList.add(person1);
        anotherPersonList.add(person2);
        anotherPersonList.add(person3);

        System.out.println("PersonList: " + personList);
        System.out.println("anotherPersonList: " + anotherPersonList);

        Set<Person> personSet = Set.of(person1, person2, person3);
        Set<Person> anotherPersonSet = new HashSet<>();
        anotherPersonSet.add(person1);
        anotherPersonSet.add(person2);
        anotherPersonSet.add(person3);

        System.out.println("sety: "+personSet);
        System.out.println("sety: "+anotherPersonSet);

        Map<Integer, Person> personMap = Map.of(1,person1, 2,person2, 3,person3);
        Map<Integer, Person> mutablePersonMap = new HashMap<>();
        mutablePersonMap.put(1, person1);
        mutablePersonMap.put(2, person2);
        mutablePersonMap.put(3, person3);

        System.out.println("mapy: "+personMap);
        System.out.println("mapy: "+mutablePersonMap);
        //4.1
        List<Integer> ageList = personList.stream()
                .map(Person::getWiek).collect(Collectors.toList());

        int result = ageList.stream()
                .reduce(0, (sum, value) -> sum+value);
        System.out.println("Średni wiek osób:" + result/personList.size());

        //4.2
        List<String> peoplesNames = personList.stream()
                .map(Person::getName).toList();
        System.out.println(peoplesNames);
        //4.3
        List<Person> peopleOver25 = personList.stream()
                .filter(Person -> Person.getWiek() > 25)
                .toList();
        System.out.println(peopleOver25);
        //4.4
        List<Person> peopleSortedbyNames = personList.stream()
                .sorted(Comparator.comparing(Person::getName))
                .toList();
        System.out.println(peopleSortedbyNames);
        //4.5
        personList.stream()
                .forEach(Person -> System.out.println("Imie: "+Person.getName() + " | wiek: "+Person.getWiek()));
        //4.6
        Optional<Person> osobaNajmlodsza = personList.stream()
                .min(Comparator.comparing(Person::getWiek));
        System.out.println("Najmłodsza osoba: "+osobaNajmlodsza.get().getName() + " lat "+osobaNajmlodsza.get().getWiek());
        Optional<Person> osobaNajstarsza = personList.stream()
                .max(Comparator.comparing(Person::getWiek));
        System.out.println("Najstarsza osoba: "+osobaNajstarsza.get().getName() + " lat "+osobaNajstarsza.get().getWiek());
        }
    }
package Level2.Lesson3.Point2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

    /*
     2. Написать простой класс PhoneBook(внутри использовать HashMap):
  - В качестве ключа использовать фамилию
  - В каждой записи всего два поля: phone, e-mail
  - Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов),
  и отдельный метод для поиска e-mail по фамилии. Следует учесть, что под одной фамилией может быть
  несколько записей. Итого должно получиться 3 класса Main, PhoneBook, Person.
     */

public class Main {

    public static void main(String[] args) throws IOException {

        HashMap<String, HashMap<Integer, String>> hashMap = new HashMap<>();

        hashMap = hashMapPhoneBook(hashMap);

        hashMap = searchPhone(hashMap);

        hashMap = searchEmail(hashMap);

       Person person = new Person("Petrov", 8989898, "petrov@mail");

    }

    public static HashMap<String, HashMap<Integer, String>> hashMapPhoneBook(HashMap<String, HashMap<Integer, String>> hashMap) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = br.readLine().split(" ");

        String nameBr = parts[0];
        Integer numPhoneBr = Integer.parseInt(parts[1]);
        String emailBr = parts[2];

        return hashMap;
    }

    // поиск Фамилия - номер телефона  Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов),
    public static HashMap<String, HashMap<Integer, String>> searchPhone(HashMap<String, HashMap<Integer, String>> hashMap) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> listPhoneBookAbonent = new ArrayList<>(); // сюда добавить список телефонов...... КАК????????????

        Set<Map.Entry<String, HashMap<Integer, String>>> entryList = hashMap.entrySet();

        String key = br.readLine();

        if (hashMap.containsKey(key)) {

            for (Map.Entry<String, HashMap<Integer, String>> pair : entryList) {

                if (key.equals(pair.getKey())) {

                    HashMap<Integer, String> value = pair.getValue();

                    for (Map.Entry<Integer, String> num : value.entrySet()) {
                        Integer numAbonent = num.getKey();

                        listPhoneBookAbonent.add(numAbonent);
                    }
                    System.out.println(" Имя " + pair.getKey() + " номер телефона " + listPhoneBookAbonent);
                }
            }
        }
        System.out.println("Номер телефона не найден");
        return hashMap;
    }
    // поиск фамилия - емэйл

    public static HashMap<String, HashMap<Integer, String>> searchEmail(HashMap<String, HashMap<Integer, String>> hashMap) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<String> listEmailBookAbonent = new ArrayList<>(); // сюда добавить список почт адресов...... КАК????????????

        Set<Map.Entry<String, HashMap<Integer, String>>> entryList = hashMap.entrySet();

        String key = br.readLine();

        if (hashMap.containsKey(key)) {

            for (Map.Entry<String, HashMap<Integer, String>> pair : entryList) {

                if (key.equals(pair.getKey())) {

                    HashMap<Integer, String> value = pair.getValue();

                    for (Map.Entry<Integer, String> num : value.entrySet()) {
                        String emailAbonent = num.getValue();

                        listEmailBookAbonent.add(emailAbonent);
                    }
                    System.out.println(" Имя " + pair.getKey() + " адрес электронной почты " + listEmailBookAbonent);
                }
            }
        }
        System.out.println("Aдрес электронной почты не найден");
        return hashMap;
    }
}





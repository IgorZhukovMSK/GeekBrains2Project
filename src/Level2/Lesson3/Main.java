package Level2.Lesson3;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Main {
  /*
     1. Создать массив с набором слов (20-30 слов, должны встречаться повторяющиеся):
            - Найти список слов, из которых состоит текст (дубликаты не считать);
  - Посчитать сколько раз встречается каждое слово (использовать HashMap);
 2. Написать простой класс PhoneBook(внутри использовать HashMap):
            - В качестве ключа использовать фамилию
  - В каждой записи всего два поля: phone, e-mail
  - Отдельный метод для поиска номера телефона по фамилии (ввели фамилию, получили ArrayList телефонов),
  и отдельный метод для поиска e-mail по фамилии. Следует учесть, что под одной фамилией может быть
  несколько записей. Итого должно получиться 3 класса Main, PhoneBook, Person.
    */

    private static final String str = "Однажды весною, Однажды весною, в час небывало жаркого заката, жаркого заката в час небывало жаркого заката";


    //private static void duplicateString(String str) {

    public static void main(String[] args) {

        notDublicate(str);

        

    }

    private static Set<String> notDublicate(String str) {
        String stringTemp = str.toLowerCase().replaceAll("[\\p{P}\\p{S}]", "");
        String[] stringMatrix = stringTemp.split(" ");

        LinkedHashSet<String> stringSet = new LinkedHashSet<>();

        for (int j = 0; j < stringMatrix.length; j++) {

            stringSet.add(stringMatrix[j]);
        }
        System.out.println(stringSet);

       wordCount(stringMatrix);

        return stringSet;
    }

    private static void wordCount(String[] stringMatrix) {
        HashMap<String, Integer> hashMapWordCount = new HashMap<>();

        for (int i = 0; i < stringMatrix.length; i ++){
            int count = 0;
            for (int j = 0; j < stringMatrix.length; j++) {
                if (stringMatrix [i].equals(stringMatrix[j])){
                    count++;
                                            }

                hashMapWordCount.put(stringMatrix[i], count);
            }

        }
        System.out.println(hashMapWordCount);
    }

}


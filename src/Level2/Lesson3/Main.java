package Level2.Lesson3;

import java.util.ArrayList;
import java.util.List;

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

    private static final String str = "Однажды весною, Однажды весною, в час небывало жаркого заката, жаркого заката";
    /*
    в Москве, на Патриарших прудах, " +
            "появились два гражданина. Первый из них, одетый в летнюю серенькую пару, был маленького роста, упитан, " +
            "Однажды весною, в час небывало жаркого заката, в Москве, на Патриарших прудах, появились два гражданина." +
            "лыс, свою приличную шляпу пирожком нес в руке, а на хорошо выбритом лице его помещались сверхъестественных " +
            "размеров очки в черной роговой оправе.";
*/

    //private static void duplicateString(String str) {

    public static void main(String[] args) {


        String stringTemp = str.toLowerCase().replaceAll("[\\p{P}\\p{S}]", "");
        String[] stringMatrix = stringTemp.split(" ");

        //String[] stringNotDuplicate = null;

        List<String> listString = new ArrayList();

        for (int i = 0; i < stringMatrix.length; i++) {

            for (int j = 0; j < stringMatrix.length; j++) {

                if (!stringMatrix[i].equals(stringMatrix[j])) {

                    listString.add(stringMatrix[j]);

                }
            }
        }

        //for (int i = 0; i < listString.size(); i++) {
            System.out.println(listString);

      //  }
    }
}

//        for (String str : stringNotDuplicate) {
//            System.out.println(str);
//        }



//    public static void main(String[] args) {
//
//        duplicateString();
//
//    }


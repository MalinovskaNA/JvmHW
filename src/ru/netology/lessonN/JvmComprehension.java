package ru.netology.lessonN;

public class JvmComprehension {
    // когда jvm встречает новый класс она отдает его для загрузки в систему загрузчиков классов
    // после загрузки происходит связывание, сначала jvm проверяет, что код валиден,
    // потом идет подготовка к выполнению класса
    // например проинициализировать статические поля
    // связывание ссылок на другие классы
    // после этого происходит инициализация самого класса,
    // ClassLoader говорит, что класс загружен и можно его использовать
    // на стадии инициализации выполняются статические инециализаторы и происходит инициализация статических полей
    // класс JvmComprehension загружается в Metaspace. в Metaspace хранися описание классов которые используются в программе

    // после того как загрузился JvmComprehension класс, jvm  ищет точку входа ,видит main  и начинается выполнение программы
    // в Stack Memory создается фрейм main, к который будут добавляться все данные, которые будут использоваться в этом методе

    public static void main(String[] args) {
        int i = 1;                      // 1
        // создается переменная i, значение которой равно 1, и это значение помещается во фрейм main, в стеке(т.к. примитивный тип)
        Object o = new Object();        // 2
        // Создается объект Object в куче, после того как объект был создан,создается переменная о, в которую будет сохранена ссылка на объект,
        // переменная о хранится во фрейм main, в стеке
        Integer ii = 2;                 // 3
        // создается объект Integer (обёртка для примитивного типа int) в куче, после создается переменная ii, в которую будет сохранена ссылка на объект,
        // переменная ii хранится во фрейм main, в стеке, класс Integer загружается в Metaspace(?)
        printAll(o, i, ii);             // 4
        // вызов метода printAll, будет создан новый фрейм в стеке, куда будет передана ссылки на объекты Object, Integer и переменная i, т.е. во втором фрейме будут созданы неявные ссылки
        // на объекты Object, Integer, созданные в куче ранее, и переменная i, далее выполняются п.5 и 6
        System.out.println("finished"); // 7
        // в стеке появится новый фрейм, в который будет передана ссылка на объект String, созданный в куче), а переменная с сохраненной ссылкой будет во фрейме,после выполнения фрейм из стека будет удален
    }
    // после выхода из метода из стека будет удален фрейм  main, стек станет пустым, программа завершит свое выполнение

    private static void printAll(Object o, int i, Integer ii) {
        Integer uselessVar = 700;                   // 5
        // создается объект Integer в куче, после создается переменная uselessVar, в которую будет сохранена ссылка на объект,
        // переменная uselessVar хранится во фрейм printAll, в стеке
        System.out.println(o.toString() + i + ii);  // 6
        // создается еще один фрейм в стеке и передается ссылка на объект Object (при вызове toString() так же создается отдельный фрейм, который после выполнения из стека удаляется),
        // переменная i и еще одна ссылка на объект Integer,
        //после завершения System.out.println будет удален этот фрейм

    }
    // после выхода из метода из стека будет удален фрейм  printAll
}
// сборщик мусора может сработать в любой момент програмы если jvm посчитает это нужным, но в конце программы точно соберутся объекты на которые нет ссылок и все очистится из хипа
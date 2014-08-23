package root.UI.Implementations.console;

import root.UI.UserInterface;
import root.entities.Person;
import root.services.PersonService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Alex on 23.08.14.
 */
public class PersonInterface implements UserInterface{

    Scanner scanner = new Scanner(System.in);
    PersonService personService;

    public PersonInterface(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void run() {


        label:
        while (true) {
            System.out.println("Добро пожаловатьв БД академии");
            System.out.println("Выберите пункт меню");
            System.out.println("1. Создать запись о человеке");
            System.out.println("2. Просмотреть / изменить записи о людях");
            System.out.println("0. Выход из программы");
            String usersChoice = scanner.nextLine();
            switch (usersChoice) {
                case "1":
                    createPerson();
                    break;
                case "2":
                    showPersons();
                    break;
                case "0":
                    break label;
                default:
                    System.out.println("Ошибка ввода");
                    break;
            }
        }
    }

    private void showPersons() {
        List<Person> persons = personService.findAll();
        for (int i = 0; i < persons.size(); i++) {
            System.out.println((i+1) + ". " + persons.get(i).getName() + " " + persons.get(i).getBirthday()
                    + " " + persons.get(i).getPassport());
        }
    }

    private void createPerson() {
        String name;
        String passport;
        System.out.println("Enter name:");
        name = scanner.nextLine();
        System.out.println("Enter passport:");
        passport = scanner.nextLine();
        Date birthday = new Date();

        personService.create(new Person(name, birthday, passport));
    }
}

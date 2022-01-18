package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);
        CarService carService = context.getBean(CarService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        userService.add(user1);
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        userService.add(user2);
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        userService.add(user3);
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");
        userService.add(user4);

        carService.add(new Car(user1, "Lada", 10));
        carService.add(new Car(user3, "VAZ", 1));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        System.out.println(carService.selectUser("Lada", 10));
        System.out.println(carService.selectUser("VAZ", 1));

        context.close();
    }
}

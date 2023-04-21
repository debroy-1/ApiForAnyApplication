package com.example.api.ApiApplication.DAO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.stereotype.Component;
import com.example.api.ApiApplication.user.User;

@Component
public class UserDAOService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;
    static {
        users.add(new User(++userCount, "babul", LocalDate.now().minusYears(20)));
        users.add(new User(++userCount, "deb", LocalDate.now().minusYears(30)));
        users.add(new User(++userCount, "sumi", LocalDate.now().minusYears(40)));
        users.add(new User(++userCount, "anil", LocalDate.now().minusYears(21)));
        users.add(new User(++userCount, "santosh", LocalDate.now().minusYears(31)));

    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> Predicate = user -> user.getId().equals(id);
        return users.stream().filter(Predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        users.add(user);
        return user;
    }
    public   void deleteById(int id) {
        Predicate<? super User> Predicate = user -> user.getId().equals(id);
    users.removeIf(Predicate);
    
    }
}

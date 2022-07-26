package pl.pp.spring.jokeswebapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.model.User;
import pl.pp.spring.jokeswebapp.model.UserProfile;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.UserService;

@Component
public class DataLoader implements CommandLineRunner {

    private final CategoryService categoryService;
    private final UserService userService;

    public DataLoader(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        User jankowalski = new User();
        jankowalski.setUsername("jankowalski");
        jankowalski.setEmail("jankowalski@gmail.com");
        jankowalski.setPassword("qwerty");

        UserProfile jankowalskiProfile = new UserProfile();
        jankowalskiProfile.setFirstName("Jan");
        jankowalskiProfile.setLastName("Kowalski");
        jankowalskiProfile.setUser(jankowalski);

        jankowalski.setUserProfile(jankowalskiProfile);

        User michalnowak = new User();
        michalnowak.setUsername("michalnowak");
        michalnowak.setEmail("michalnowak@gmail.com");
        michalnowak.setPassword("123456");

        Joke joke1 = getExampleJoke1();
        Joke joke2 = getExampleJoke2();

        Category category1 = new Category("Teściowa");
        Category category2 = new Category("Szkoła");
        Category category3 = new Category("Kujon");

        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);

        joke1.getCategories().add(category1);
        joke2.getCategories().add(category2);
        joke2.getCategories().add(category3);

        category1.getJokes().add(joke1);
        category2.getJokes().add(joke2);
        category3.getJokes().add(joke2);

        jankowalski.getJokes().add(joke1);
        jankowalski.getJokes().add(joke2);

        joke1.setUser(jankowalski);
        joke2.setUser(jankowalski);



        userService.save(jankowalski);
        userService.save(michalnowak);

    }

    private Joke getExampleJoke2() {
        Joke joke2 = new Joke();
        joke2.setTitle("Centralny ośrodek kontroli oddawania moczu");
        joke2.setContent("Zwykły uczeń: Masakra, ale ten sprawdzian z biologii był trudny.\n" +
                "Kujon: Nie był w cale taki trudny. No powiedz z czym miałeś problem?\n" +
                "Zwykły uczeń: No np było takie pytanie. Gdzie jest centralny ośrodek kontroli oddawania moczu?\n" +
                "Kujon: No to jest łatwa pytanie. No oczywiście że w korze mózgowej.\n" +
                "Zwykły uczeń: Kurdę, a ja napisałem że w warszawie.\n");
        return joke2;
    }

    private Joke getExampleJoke1() {
        Joke joke1 = new Joke();
        joke1.setTitle("Okup za teściową");
        joke1.setContent("Mężczyzna odbiera telefon:\n" +
                "-Słucham\n" +
                "*Mamy twoją teściową. musisz zapłacić 100 000 zł okupu - słyszy w telefonie.\n" +
                "-A co jeśli nie zapłacę? - Zastanawia się mężczyzna.\n" +
                "*To ją sklonujemy! - odpowiada porywacz.");
        return joke1;
    }

}

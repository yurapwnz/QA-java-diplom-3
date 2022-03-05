import com.objectsPage.MainPage;
import com.objectsPage.RegistrationPage;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserRegistrationTest {

    RegistrationPage registrationPage = page(RegistrationPage.class);
    MainPage mainPage;

    public static Faker faker = new Faker();
    String email = faker.name().lastName() + "@yandex.ru";
    String password = faker.internet().password();
    String name = faker.name().firstName();
    String shortPassword = faker.internet().password(1, 5);


    @Before
    public void before() {
        Configuration.startMaximized = true;
        mainPage = open(MainPage.URL, MainPage.class);
        // Для MacOS
        //System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver");
        // Для Windows
        // System.setProperty("webdriver.chrome.driver", "src/resources/yandexdriver.exe");
    }

    @Test
    @Description ("Регистрация нового пользоватля. Авторизация новым пользователем")
    public void userRegistrationTest (){

        mainPage
                .clickLoginButton()
                .regLinkClick()
                .setName(name)
                .setEmail(email)
                .setPassword(password)
                .regButtonClick()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick();

        boolean buttonShow = mainPage.arrangeOrderButtonVisible();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", buttonShow);

    }

    @Test
    @Description ("Попытка регистрации нового пользоватля, пароль менее 6 мисволов")
    public void userRegistrationIncorrectPasswordTest (){

        String expectedErrorMessage = "Некорректный пароль";

        mainPage
                .clickLoginButton()
                .regLinkClick()
                .setName(name)
                .setEmail(email)
                .setPassword(shortPassword)
                .regButtonClick();

        String actualErrorMessage = registrationPage.getPassErrorMessageText();

        assertEquals(expectedErrorMessage,actualErrorMessage);
    }

    @After
    public void tearDown (){
        webdriver().driver().close();
    }
}

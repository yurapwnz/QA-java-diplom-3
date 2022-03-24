import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import com.objectsPage.MainPage;
import static com.codeborne.selenide.WebDriverConditions.*;
import static org.junit.Assert.*;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.Map;
import com.UserOperations;

public class UserLoginTest {

    Map<String, String> user = new UserOperations().register();
    String email = user.get("email");
    String password = user.get("password");
    MainPage mainPage;

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
    @Description("Авторизация пользователя. Кнопка 'Войти в аккаунт'")
    public void loginUserWithLoginButtonTest(){

        mainPage
                .clickLoginButton()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", mainPage.arrangeOrderButtonVisible());

    }
    @Test
    @Description ("Авторизация пользователя. Кнопка 'Личный кабинет'")
    public void loginUserWithCabinetButtonTest(){

        mainPage
                .clickCabinetButton()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", mainPage.arrangeOrderButtonVisible());
    }
    @Test
    @Description ("Авторизация пользователя. Кнопка 'Войти' на странице регистрации")
    public void loginUserWithLoginButtonInRegPageTest(){

        mainPage
                .clickLoginButton()
                .regLinkClick()
                .loginLinkClick()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", mainPage.arrangeOrderButtonVisible());

    }
    @Test
    @Description ("Авторизация пользователя. Кнопка 'Войти' страница востановления пароля")
    public void loginUserWithResetPasswordLinkTest(){

        mainPage
                .clickLoginButton()
                .resetPasswordLinkClick()
                .loginLinkClick()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/"));
        assertTrue("Button invisible", mainPage.arrangeOrderButtonVisible());

    }
    @Test
    @Description ("Выход пользователя")
    public void logoutUserTest(){

        mainPage
                .clickLoginButton()
                .setEmail(email)
                .setPassword(password)
                .loginButtonClick()
                .clickCabinetButton()
                .exitButtonClick();

        webdriver().shouldHave(url("https://stellarburgers.nomoreparties.site/login"));
    }

    @After //Удаляем созданого пользователя
    public void tearDown() {
        UserOperations.delete();
        webdriver().driver().close();
    }
}

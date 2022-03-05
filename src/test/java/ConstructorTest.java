import com.objectsPage.MainPage;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import static org.junit.Assert.assertTrue;

public class ConstructorTest {

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
    @Description("Проверка что есть скролл к элементу конструктора 'Булки'. Блок 'Булки' отображается.")
    public void scrollWithBurgerElementTest(){

        mainPage
                .fillingButtonClick()
                .bunButtonClick();

        boolean blockVisible = mainPage.burgerBlock.isDisplayed();

        assertTrue("Block is invisible",blockVisible);
    }

    @Test
    @Description ("Проверка что есть скролл к элементу конструктора 'Соусы'. Блок 'Соусы' отображается.")
    public void scrollWithSauceElementTest(){

        mainPage
                .sauceButtonClick();

        boolean blockVisible = mainPage.sauceBlock.isDisplayed();

        assertTrue("Block is invisible",blockVisible);
    }

    @Test
    @Description ("Проверка что есть скролл к элементу конструктора 'Начинки'. Блок 'Начинки' отображается.")
    public void scrollWithFillingElementTest(){

        mainPage
                .fillingButtonClick();

        boolean blockVisible = mainPage.fillingBlock.isDisplayed();

        assertTrue("Block is invisible",blockVisible);
    }

    @After
    public void tearDown (){
        webdriver().driver().close();
    }
}

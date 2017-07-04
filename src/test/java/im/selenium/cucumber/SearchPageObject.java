package im.selenium.cucumber;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPageObject {
    private static WebDriver webDriver;

    public SearchPageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    private By searchTermField = By.id("lst-ib");
    private By submitSearch = By.name("btnK");
    private By searchResults = By.id("search");

    public void enterSearchTerm(String searchTerm) {
        webDriver.findElement(searchTermField).sendKeys(searchTerm);
    }

    public void submitSearch() {
        webDriver.findElement(submitSearch).click();
    }

    public void acceptSearchResult(int id) {
        List<WebElement> searchResultList = webDriver.findElement(searchResults).findElements(By.tagName("h3"));

        searchResultList.get(id).findElements(By.tagName("a")).get(0).click();
    }
}

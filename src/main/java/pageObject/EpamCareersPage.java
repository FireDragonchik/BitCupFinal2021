package pageObject;

import framework.elements.*;
import org.openqa.selenium.By;

import static framework.utils.WebElementUtils.DEFAULT_TIMEOUT;

public class EpamCareersPage extends AbstractPage {

    public enum Language {
        GLOBAL_ENGLISH("Global"),
        HUNGARY_ENGLISH("Hungary"),
        BELARUS_RUSSIAN("Беларусь");

        private String text;

        Language(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    private final String XPATH_LANGUAGE_OPTION = "//ul[contains(@class,'header')]//nav[contains(@class,'location')]//li/a[contains(text(),'%s')]";
    private final String XPATH_LOCATION_COUNTRY = "//form[contains(@id,'jobSearch')]//ul//li[@aria-label='%s']";
    private final String XPATH_LOCATION_CITY = XPATH_LOCATION_COUNTRY + "//li[contains(text(),'%s')]";
    private final String XPATH_SKILLS_OPTION = "//form[contains(@id,'jobSearch')]//label[text()='Skills']//following-sibling::div/div[contains(@class,'multi')]//span[contains(text(),'%s')]";
    private final String XPATH_WORK_OPTION = "//form[contains(@id,'jobSearch')]//label[contains(text(),'%s')]//preceding-sibling::input";

    private final Button btnLocation = new Button(By.xpath("//ul[contains(@class,'header')]//button[contains(@class,'location')]"), "Location button");
    private final Button btnSearch = new Button(By.xpath("//form[contains(@id,'jobSearch')]//button"), "Search button");
    private final Input txtKeywordField = new Input(By.xpath("//form[contains(@id,'jobSearch')]//label[text()='Keyword']//following-sibling::input"), "Keyword input");
    private final Input txtLocation = new Input(By.xpath("//form[contains(@id,'jobSearch')]//label[text()='Location']//following-sibling::div//span[@role='textbox']"), "Location input");
    private final DropDown drpdSkills = new DropDown(By.xpath("//form[contains(@id,'jobSearch')]//label[text()='Skills']//following-sibling::div/div[contains(@class,'selected')]"), "Skills dropdown");

    private final Label lblCareers = new Label(By.xpath("//nav[contains(@class,'breadcrumbs')]//li/a[text()='Careers']"), "Careers lbl");

    public void waitPageLoaded() {

        webElementUtils().waitForVisibilityOf(lblCareers, DEFAULT_TIMEOUT);
    }

    public void clickLocationBtn() {

        webElementUtils().waitElementToBeClickable(btnLocation, DEFAULT_TIMEOUT);
        btnLocation.click();
    }

    public void selectLocation(Language location) {

        clickLocationBtn();
        getLanguageOption(location).click();
    }

    public void fillKeyword(String keyword) {

        webElementUtils().waitForVisibilityOf(txtKeywordField, DEFAULT_TIMEOUT);
        txtKeywordField.clearAndType(keyword);
    }

    public void fillLocation(String country, String city) {

        webElementUtils().waitForVisibilityOf(txtLocation, DEFAULT_TIMEOUT);
        txtLocation.click();

        webElementUtils().waitForVisibilityOf(getLocationCountry(country), DEFAULT_TIMEOUT);
        getLocationCountry(country).click();

        webElementUtils().waitForVisibilityOf(getLocationCity(country, city), DEFAULT_TIMEOUT);
        getLocationCity(country, city).click();
    }

    public void selectSkill(String skillName) {

        drpdSkills.click();
        webElementUtils().waitForVisibilityOf(getChbSkillOption(skillName), DEFAULT_TIMEOUT);
        getChbSkillOption(skillName).click();
    }

    public void selectWorkOption(String workOption) {

        webElementUtils().waitForVisibilityOf(getChbWorkOption(workOption), DEFAULT_TIMEOUT);
        getChbWorkOption(workOption).click();
    }

    public void fillSearchCriteria(String keyword, String country, String city, String skillName, String workOption) {

        fillKeyword(keyword);
        fillLocation(country, city);
        selectSkill(skillName);
        selectWorkOption(workOption);
        drpdSkills.click();
        btnSearch.click();
    }

    private Checkbox getChbWorkOption(String workOption) {

        return new Checkbox(By.xpath(String.format(XPATH_WORK_OPTION, workOption)), "Work option " + workOption);
    }

    private Checkbox getChbSkillOption(String skillName) {

        return new Checkbox(By.xpath(String.format(XPATH_SKILLS_OPTION, skillName)), String.format("Skill %s option", skillName));
    }

    private Label getLanguageOption(Language language) {

        return new Label(By.xpath(String.format(XPATH_LANGUAGE_OPTION, language.getText())), String.format("Language %s option", language.getText()));
    }

    private Label getLocationCountry(String country) {

        return new Label(By.xpath(String.format(XPATH_LOCATION_COUNTRY, country)), String.format("Location country %s option", country));
    }

    private Label getLocationCity(String country, String city) {

        return new Label(By.xpath(String.format(XPATH_LOCATION_CITY, country, city)), String.format("Location city %s option", city));
    }
}

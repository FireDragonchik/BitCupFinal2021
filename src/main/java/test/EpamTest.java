package test;

import framework.browser.BrowserUtils;
import framework.elements.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pageObject.EpamCareersPage;

import java.util.List;

public class EpamTest extends BaseTest {

    private final String CAREERS_PAGE_URL = "https://www.epam.com/careers";

    private final String KEYWORD = "Jenkins";
    private final String COUNTRY = "Belarus";
    private final String CITY = "Minsk";
    private final String SKILL = "Software Test Engineering";
    private final String WORK_OPTION = "Remote";


    @Test
    public void checkJobs() {

        BrowserUtils.get(CAREERS_PAGE_URL);
        EpamCareersPage epamCareersPage = new EpamCareersPage();
        epamCareersPage.waitPageLoaded();

        epamCareersPage.selectLocation(EpamCareersPage.Language.GLOBAL_ENGLISH);

        BrowserUtils.get(CAREERS_PAGE_URL);

        epamCareersPage.fillSearchCriteria(KEYWORD, COUNTRY, CITY, SKILL, WORK_OPTION);

        List<WebElement> list = epamCareersPage.getSearchResultValues();

        logger.info("OK, test ended");
    }
}

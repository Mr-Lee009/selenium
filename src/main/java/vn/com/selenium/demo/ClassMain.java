package vn.com.selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class ClassMain {
    public static void main(String[] args) {
        WebDriver webDriver = null;
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\ducla\\.cache\\selenium\\chromedriver\\win32\\111.0.5563.64\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://developers.momo.vn/v2/#/");

        //todo: Xpath = //tagename[@Attibute='value']
        String[] elements = new String[]{
                "//*[@id=\"__next\"]/main/section[2]/div/div/div[1]/h1",
                "//*[@id=\"__next\"]/main/section[2]/div/div/div[1]/h1",
                "//*[@class=\"text-momo font-bold\"]",
                "//*[@class=\"lang-json\"]",
                "/html/body/nav/ul/li/a"
        };

        int i = 1;
        try {

            for (String str : elements) {
                System.out.printf("\n[%s][element %s]================================\n", i, str);
                List<WebElement> elementLis = webDriver.findElements(By.xpath(str));
                if (!elementLis.isEmpty() && elementLis.size() > 0) {
                    getTextElement(elementLis);
                } else {
                    System.out.println("elementLis is empty!");
                }
                System.out.printf("\n[%s][end run element %s]================================\n", i, str);
                System.out.printf("");
                i++;
            }

            /*webDriver.findElement(By.xpath("//*[@class=\"lang-json\"]"));

            WebElement textSearch = webDriver.findElement(By.xpath(INPUT_TEXT_SEARCH));
            //textSearch.click();
            textSearch.sendKeys("payment");

            webDriver.findElement(By.xpath(INPUT_TEXT_SEARCH)).click();*/

        } catch (Exception e) {//*[@id="__next"]/nav[2]/div/div[3]/form/div/input
            System.err.printf("\n[%s]Exception error: " + e.getMessage(), i);
        }
        System.out.println("title = " + webDriver.getTitle());
    }

    public static void getTextElement(List<WebElement> webElements) {
        System.out.printf("SIZE = " + webElements.size());
        for (WebElement webElement : webElements) {
            if (webElement.isEnabled()) {
                System.out.printf("\nTEXT: %s, URL: %s", webElement.getText(), webElement.getAttribute("href"));
                if ("link".equalsIgnoreCase(webElement.getAriaRole())) {
                    sleep(1000);
                    webElement.click();
                }
            } else {
                System.out.println("is not Enabled");
            }
        }
    }

    public static void sleep(int sec) {
        try {
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static String INPUT_TEXT_SEARCH = "//*[@id=\"__next\"]/nav[2]/div/div[3]/form/div/input";
    private static String BTN_SEARCH = "//*[@id=\"__next\"]/nav[2]/div/div[3]/form/div/button";
}

package edu.vdt.test;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Location("index.xhtml")
public class PlayerPage {
    @FindBy(id = "playerForm:name")
    WebElement name;

    @FindBy(id = "playerForm:age")
    WebElement age;

    @FindBy(id = "playerForm:addPlayer")
    WebElement addPlayer;

    @FindBy(id = "messages")
    WebElement messages;

    public void addPlayer(){
        name.sendKeys("Vijay Tidake");
        age.sendKeys("28");
        Graphene.guardAjax(addPlayer).click();
        Assert.assertNotNull(messages);
        if(messages != null){
            Assert.assertTrue("Player added",
                    messages.getText().contains("added"));
        }
    }
}

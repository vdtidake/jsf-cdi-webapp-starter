package edu.vdt.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import javax.inject.Inject;
import java.net.URL;

@RunWith(Arquillian.class)
public class PlayerTest {

    @Drone
    private WebDriver browser;

    @ArquillianResource
    private URL deploymentUrl;

    @Page
    PlayerPage playerPage;

    @Deployment(order = 1)
    public static WebArchive createDeployment() {
        return Deployments.createWar();
    }

    @Test
    @InSequence(1)
    @RunAsClient
    public void deploy_app() {
        browser.get(
                deploymentUrl.toExternalForm()
        );
    }

    @Test
    @InSequence(2)
    @RunAsClient
    public void addPlayer() {
        browser.get(
                deploymentUrl.toExternalForm()
        );
        playerPage.addPlayer();
    }

}

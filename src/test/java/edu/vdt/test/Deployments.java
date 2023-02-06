package edu.vdt.test;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.GenericArchive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.importer.ExplodedImporter;
import org.jboss.shrinkwrap.api.importer.ZipImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import java.io.File;

public class Deployments {

    private static final String BASE_TESTING_URL = "http://localhost:8080";
    private static final String WEBAPP_SRC = "src/main/webapp";
    private static final String RESOURCES = "src/main/resources";

    @Deployment(testable = true)
    public static WebArchive createWar() {
        return createShrinkWar();
    }

    static WebArchive createShrinkWar() {
//        File[] files = Maven.resolver().loadPomFromFile("pom.xml")
//            .importRuntimeDependencies().resolve().withTransitivity().asFile();
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "ROOT.war");
        webArchive.addPackages(true, "edu.vdt");
        webArchive.merge(
                ShrinkWrap.create(GenericArchive.class).as(ExplodedImporter.class).importDirectory(WEBAPP_SRC).as(GenericArchive.class), "/", Filters.includeAll()
        );
//
//        // webArchive.addAsLibraries(files);
//        webArchive.addAsResource("test-context.xml", "META-INF/context.xml");
        //webArchive.setWebXML("test-web.xml");
        return webArchive;
    }

}

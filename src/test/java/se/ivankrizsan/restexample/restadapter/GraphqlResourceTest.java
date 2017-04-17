package se.ivankrizsan.restexample.restadapter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.unitils.reflectionassert.ReflectionAssert;
import org.unitils.thirdparty.org.apache.commons.io.IOUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import se.ivankrizsan.restexample.domain.Circle;
import se.ivankrizsan.restexample.helpers.CircleEntityFactory;
import se.ivankrizsan.restexample.helpers.EntityFactory;
import se.ivankrizsan.restexample.helpers.JsonConverter;
import se.ivankrizsan.restexample.repositories.CircleRepository;
import se.ivankrizsan.restexample.repositories.customisation.JpaRepositoryCustomisationsImpl;

/**
 * Created by lhoussou on 12/04/2017.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@EnableJpaRepositories(basePackages = {"se.ivankrizsan.restexample.repositories"},
        repositoryBaseClass = JpaRepositoryCustomisationsImpl.class)
public class GraphqlResourceTest extends
        AbstractTestNGSpringContextTests {
    /* Constant(s): */
    protected static final int ENDPOINT_PORT = 8080;
    protected static final long TEST_TIMEOUT = 300000;

    /* Instance variable(s): */
    @Autowired
    protected CircleRepository mCircleRepository;

    protected EntityFactory<Circle> mEntityFactory;
    protected CrudRepository<Circle, Long> mEntityRepository;
    protected String mResourceUrlPath;
    protected Circle mExpectedEntity;
    protected int mCreateEntityIndex;

    /**
     * Sets up RestAssured test framework before tests.
     */
    @BeforeTest
    public void setUpRestAssured() {
        RestAssured.reset();
        RestAssured.port = ENDPOINT_PORT;
        RestAssured.basePath = "";
    }

    @BeforeMethod
    public void prepareBeforeTest() {
        mEntityFactory = new CircleEntityFactory();
        mEntityRepository = mCircleRepository;
        mResourceUrlPath = GraphqlResource.PATH;

        /////////////////////////
        // super.prepareBeforeTest();
        mCreateEntityIndex = (int) Math.round(Math.random() * 100);
        final Circle theEntity = mEntityFactory.createEntity(mCreateEntityIndex);
        mExpectedEntity = mEntityRepository.save(theEntity);

        Assert.assertNotNull(mExpectedEntity);
        Assert.assertNotNull(mExpectedEntity.getId());
        /////////////////////////
    }

    /**
     * Tests retrieving one entity.
     * An entity should be retrieved and the properties of the entity should have the
     * same values as the entity persisted before the test.
     *
     * @throws IOException If error occurs. Indicates test failure.
     */
    @Test(timeOut = TEST_TIMEOUT)
    public void testGetEntity() throws IOException {
        final Response theResponse = RestAssured.
                given().
                contentType("application/json").
                accept("application/json").
                when().
                get(mResourceUrlPath + "/" + mExpectedEntity.getId());
        final String theResponseJson = theResponse.prettyPrint();
        theResponse.
                then().
                statusCode(200).
                contentType(ContentType.JSON);

        final Object theRetrievedEntity = JsonConverter.jsonToObject(
                theResponseJson, mExpectedEntity.getClass());
        ReflectionAssert.assertLenientEquals(
                "Retrieved entity should have the correct property values",
                mExpectedEntity, theRetrievedEntity);
    }


    /**
     * Tests retrieving all entities.
     * All entities should be retrieved and the properties of the entity should have the
     * same values as the entities persisted before the test.
     *
     * @throws IOException If error occurs. Indicates test failure.
     */
    @Test(timeOut = TEST_TIMEOUT)
    public void testGetAllEntities() throws IOException {
        final String inBody = IOUtils.toString(this.getClass().getResourceAsStream("/graphqlpayloads/requestGetCircles.json"));
        final Response theResponse = RestAssured.
                given().log().all().
                contentType("application/json").
                accept("application/json").
                body(inBody).
                when().
                post(mResourceUrlPath);
        final String theResponseJson = theResponse.prettyPrint();
        theResponse.
                then().log().all().
                statusCode(200).
                contentType(ContentType.JSON);

        final Object theRetrievedEntity = JsonConverter.jsonToObject(
                theResponseJson, mExpectedEntity.getClass());
        ReflectionAssert.assertLenientEquals(
                "Retrieved entity should have the correct property values",
                mExpectedEntity, theRetrievedEntity);
    }
}
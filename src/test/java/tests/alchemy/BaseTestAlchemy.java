package tests.alchemy;

import org.junit.jupiter.api.BeforeAll;
import tests.AbstractBaseTest;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class BaseTestAlchemy extends AbstractBaseTest {
    @BeforeAll
    public void setupAlchemy() throws MalformedURLException, URISyntaxException {
        setup("com.ilyin.alchemy", "some other main screen");
    }
}

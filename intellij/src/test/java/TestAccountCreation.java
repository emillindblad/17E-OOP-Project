
import edu.tda367.Controllers.AccountCreationStatus;
import edu.tda367.Controllers.CreateAccountController;
import edu.tda367.View.SceneHandler;
import javafx.application.Application;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class TestAccountCreation extends Application {

    private CreateAccountController caController;

    @BeforeClass
    @Override
    public void start(Stage stage) throws IOException {
        caController = new CreateAccountController(new SceneHandler(stage));
    }

    @Test
    public void testCreateAccountSuccess() {
        AccountCreationStatus status = caController.createAccountAttempt("a","b","c","b","b","1","1","q","2","g","f");
        assertEquals(AccountCreationStatus.SUCCESS, status);
    }

    @Test
    public void testPasswordMissmatch() {
        AccountCreationStatus status = caController.createAccountAttempt("a","b","c","no","yes","1","1","q","2","g","f");
        assertEquals(AccountCreationStatus.PASSWORD_MISSMATCH, status);
    }

    @Test
    public void testFieldEmpty() {
        AccountCreationStatus status = caController.createAccountAttempt("","b","c","yes","yes","1","1","q","2","g","f");
        assertEquals(AccountCreationStatus.EMPTY_FIELD, status);
    }

    @Test
    public void testZipCodeError() {
        AccountCreationStatus status = caController.createAccountAttempt("a","b","c","yes","yes","1","1","q","bad","g","f");
        assertEquals(AccountCreationStatus.ZIPCODE_ERROR, status);
    }
}

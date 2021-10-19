package edu.tda367.Controllers;

import edu.tda367.View.SceneHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CreateListingController extends ListingViewController {

    public CreateListingController(SceneHandler sceneHandler) {
        super(sceneHandler);
    }

    @Override
    public String doneButton(String[] formData, File src, String destPath) {
        int userId = userHandler.getUserID();
        if (validateData(formData)) { //Return true if valid input.
            if(src != null) {
                try {
                    Files.copy(src.toPath(), new File(destPath + src.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("did save");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            listingHandler.createListingFromForm(formData, userId);
            goBack();
            return "Success";
        }
        else {
            System.out.println("Form input failed validation!");
            return "Fail";
        }
    }

    @Override
    public void goBack() {
        sceneHandler.switchTo("browse");
    }

    @Override
    public String getFXMLName() {
        return "createlisting";
    }

    @Override
    public void update() {

    }

}

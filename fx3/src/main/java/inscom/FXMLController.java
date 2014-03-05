package inscom;

import java.net.URL;
import java.util.ResourceBundle;

//import org.w3c.dom.events.MouseEvent;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void dragCircle(MouseEvent event) {
    	System.out.println(String.valueOf(event.getSceneX()) + " " + String.valueOf(event.getSceneY()));
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

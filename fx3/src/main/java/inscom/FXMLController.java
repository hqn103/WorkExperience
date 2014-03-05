package inscom;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    public Circle sourceCircle;
    public Rectangle targetRectangle;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @FXML
    private void handleDragDetected(MouseEvent event) {
    	Dragboard db = sourceCircle.startDragAndDrop(TransferMode.ANY);
    	ClipboardContent content = new ClipboardContent();
    	content.putString(sourceCircle.getId());
    	db.setContent(content);
    	event.consume();
    	//System.out.println(String.valueOf(event.getSceneX()) + " " + String.valueOf(event.getSceneY()));
    }
    
    @FXML
    private void handleDragOver(DragEvent event) {
        if (event.getGestureSource() != targetRectangle && event.getDragboard().hasString()) {
            /* allow for both copying and moving, whatever user chooses */
            event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
        }
        
        event.consume();    	
    }
    
    @FXML
    private void handleDragEntered(DragEvent event) {
        if (event.getGestureSource() != targetRectangle && event.getDragboard().hasString()) {
        	targetRectangle.setFill(Color.GREEN);
        }
               
        event.consume();        
    }
    
    @FXML
    private void handleDragExited(DragEvent event) {
    	targetRectangle.setFill(Color.CYAN);
    	
    	event.consume();
    }
    
    @FXML
    private void handleMouseDrag(MouseEvent event) {
    	System.out.println(String.valueOf(event.getSceneX()) + " " + String.valueOf(event.getSceneY()));
    }    
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

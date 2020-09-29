package carbon;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;

public class MainWindow {
    public CarbonModel model = new CarbonModel();
    @FXML private Controller carbonController;
    @FXML private ResultsTab resultsTabController;
    public void init(){
        carbonController.formatLayout();
        carbonController.model = model;
        resultsTabController.model = model;
        resultsTabController.populateHeaders();
        resultsTabController.setupLayout();
    }
}

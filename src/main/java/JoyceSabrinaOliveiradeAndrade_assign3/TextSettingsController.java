/**
 * TextSettingsController class is a JavaFX controller for a text customization
 ** application. By using this class, the users can modify various properties of
 * a text label, size, alignment, style which could be bold and italic, the text
 * colour, and background colour.
 *
 * The class extends the JavaFx Application also defines the behaviour
 * for the associated FXML file.
 *
 * @author Joyce Sabrina Oliveira de Andrade
 * @version 1.0
 */


package JoyceSabrinaOliveiradeAndrade_assign3;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.Optional;

import static javafx.application.Application.launch;

public class TextSettingsController extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource
                ("fxml.fxml"));

        VBox root = loader.load();

        TextSettingsController controller = loader.getController();

        Scene scene = new Scene(root);
        primaryStage.setTitle("Text Settings Application");
        scene.getStylesheets().add("styles.css");
        System.out.print(scene.getStylesheets());
              //  toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
        //linking stylesheets

//        primaryStage.setScene(scene);
//        primaryStage.show();

    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
    @FXML
    private Label textLabel;

    @FXML
    private ComboBox<String> textColorComboBox;

    @FXML
    private TextField textEntryField;

    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private ToggleGroup alignmentToggleGroup;

    @FXML
    private CheckBox boldCheckBox;

    @FXML
    private CheckBox italicCheckBox;

    @FXML
    private ComboBox<String> bgColorComboBox;

    @FXML
    private RadioButton smallSizeRadioButton;

    @FXML
    private RadioButton mediumSizeRadioButton;

    @FXML
    private RadioButton largeSizeRadioButton;

    @FXML
    private RadioButton leftAlignmentRadioButton;
    @FXML
    private RadioButton centerAlignmentRadioButton;
    @FXML
    private RadioButton rightAlignmentRadioButton;


    // Arrays for efficiency
    private final String[] bgColorClasses = {"background-grey",
            "background-wheat", "background-white"};
    private final Color[] textColorValues = {Color.BLACK,
            Color.DARKGREEN, Color.NAVY};

    @FXML
    public void initialize() {
        // Initialize UI components or set default values here
        sizeToggleGroup = new ToggleGroup();
        System.out.println(smallSizeRadioButton);
        smallSizeRadioButton.setToggleGroup(sizeToggleGroup);
        mediumSizeRadioButton.setToggleGroup(sizeToggleGroup);
        largeSizeRadioButton.setToggleGroup(sizeToggleGroup);

        alignmentToggleGroup = new ToggleGroup();
        leftAlignmentRadioButton.setToggleGroup(alignmentToggleGroup);
        centerAlignmentRadioButton.setToggleGroup(alignmentToggleGroup);
        rightAlignmentRadioButton.setToggleGroup(alignmentToggleGroup);


        textColorComboBox.getItems().addAll("Black", "Dark Green", "Navy");
        bgColorComboBox.getItems().addAll("Grey", "Wheat", "White");
        textColorComboBox.getSelectionModel().select("Black");
        bgColorComboBox.getSelectionModel().select("Grey");
    }

    @FXML
    public void handleSizeChange(MouseEvent mouseEvent) {
        RadioButton selectedRadioButton = (RadioButton)
                sizeToggleGroup.getSelectedToggle();
        System.out.println(selectedRadioButton);
        if (selectedRadioButton != null) {
            String sizeClass = selectedRadioButton.getText();
            textLabel.getStyleClass().clear();
            textLabel.getStyleClass().add(sizeClass);
        }
    }

    @FXML
    public void handleAlignmentChange(MouseEvent mouseEvent) {
        RadioButton selectedRadioButton = (RadioButton) alignmentToggleGroup.getSelectedToggle();

        if (selectedRadioButton != null) {
            String alignment = selectedRadioButton.getText();
            if (alignment.toUpperCase().equals("RIGHT")) {
                textLabel.setAlignment(Pos.BASELINE_RIGHT);

            }
            if (alignment.toUpperCase().equals("LEFT")) {
                textLabel.setAlignment(Pos.BASELINE_LEFT);

            }
            if (alignment.toUpperCase().equals("CENTER")) {
                textLabel.setAlignment(Pos.CENTER);

            }

        }
    }

    @FXML
    public void handleBoldChange(MouseEvent event) {
        System.out.println("SHERE CHANGE NOLD");
        if (boldCheckBox.isSelected()) {
            textLabel.getStyleClass().add("bold");
        } else {
            textLabel.getStyleClass().remove("bold");
        }
    }

    @FXML
    public void handleItalicChange(MouseEvent event) {
        if (italicCheckBox.isSelected()) {
            textLabel.getStyleClass().add("italic");
        } else {
            textLabel.getStyleClass().remove("italic");
        }
    }

    @FXML
    public void handleForegroundColorChange() {
        int selectedIndex = textColorComboBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < textColorValues.length) {
            textLabel.setTextFill(textColorValues[selectedIndex]);
        }
    }

    @FXML
    public void handleBackgroundColorChange(ActionEvent actionEvent) {
        int selectedIndex = bgColorComboBox.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && selectedIndex < bgColorClasses.length) {
            textLabel.getStyleClass().removeAll(bgColorClasses);
            textLabel.getStyleClass().add(bgColorClasses[selectedIndex]);
        }
    }

    @FXML
    public void handleTextChange(ActionEvent actionEvent) {
        String newText = textEntryField.getText();
        textLabel.setText(newText);
        textEntryField.clear();
        textEntryField.requestFocus();
    }

    @FXML
    public void handleReset() {
        // Reset size toggle group
        sizeToggleGroup.selectToggle(null);

        // Reset alignment toggle group
        alignmentToggleGroup.selectToggle(null);


        boldCheckBox.setSelected(false);
        italicCheckBox.setSelected(false);
        textColorComboBox.getSelectionModel().select("Black");
        bgColorComboBox.getSelectionModel().select("Grey");
        textEntryField.clear();

        textLabel.getStyleClass().clear();
        textLabel.getStyleClass().add("medium-font");
        textLabel.setAlignment(Pos.CENTER);
        textLabel.setFont(Font.font("Arial", FontWeight.NORMAL,
                FontPosture.REGULAR, 16));
        textLabel.setTextFill(Color.BLACK);
        textLabel.setBackground(null);
        textLabel.setText("Sample Text");
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Do you really want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0); //exiting
        }

    }
}

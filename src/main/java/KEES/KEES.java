package KEES;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


/**
 *
 * @author Mahmoud
 */
 
public class KEES extends Application {

    // CLI Main
    // public static void main(String[] args) {
    //     try (Connection conn = DataBase.CreateTabelesOneTime()) {
    //         Scanner input = new Scanner(System.in);   
    //         System.out.println("welcome to KEES Prototype:");
    //         System.out.println("1. add Question");
    //         System.out.println("2. create exam");
    //         int choice = Integer.parseInt(input.nextLine());   
    //         if(choice == 1) {
    //             boolean isSuccess = DataBase.addQuestionsFromFile("test.txt", conn, input);
    //         } else if(choice == 2) {
    //             boolean isSuccess = DataBase.createExamWithConditions("testExam.txt", conn, input);     
    //         }
    //         input.close();
    //     } catch (Exception e) {
    //         System.out.println(e);
    //     }
    // }

    
    // GUI Main
    @Override
    public void start(Stage primaryStage) {
        
        // Creating UI elements
        Label nameLabel = new Label("Name:");
        TextField nameField = new TextField();

        Label ageLabel = new Label("Age:");
        TextField ageField = new TextField();

        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear");

        // Event Handling
        submitButton.setOnAction(e -> {
            String name = nameField.getText();
            String age = ageField.getText();
            System.out.println("Submitted Name: " + name + ", Age: " + age);
        });

        clearButton.setOnAction(e -> {
            nameField.clear();
            ageField.clear();
            System.out.println("Fields Cleared");
        });

        // Layouts
        VBox root = new VBox(10); // Vertical layout with 10px spacing
        HBox nameRow = new HBox(10, nameLabel, nameField);
        HBox ageRow = new HBox(10, ageLabel, ageField);
        HBox buttonRow = new HBox(10, submitButton, clearButton);

        root.getChildren().addAll(nameRow, ageRow, buttonRow);

        // Set up the scene and stage
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setTitle("Simple JavaFX App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

package KEES;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
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

        Separator verticalSeparator = new Separator();
        verticalSeparator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        verticalSeparator.setPrefHeight(600); 

        BorderPane mainLayout = new BorderPane();

        ListView<String> sidebar = new ListView<>();
        sidebar.getItems().addAll("Add Question", "Update Question", "Create Exam");
        sidebar.getSelectionModel().select(0);
        sidebar.setPrefWidth(150);
        sidebar.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                switch (newVal) {
                    case "Add Question" -> mainLayout.setCenter(new AddQuestionView());
                    case "Update Question" -> mainLayout.setCenter(new UpdateQuestionView());
                    case "Create Exam" -> mainLayout.setCenter(new CreateExamView());
                    default -> {
                    }
                }

            }
        });

        mainLayout.setLeft(sidebar);
        mainLayout.setCenter(new AddQuestionView());

        Scene scene = new Scene(mainLayout, 700, 400);
        // scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("KEES Exam System");
        primaryStage.show();
    }
    
    public static class AddQuestionView extends VBox{
        public AddQuestionView() {
            
            mcqFormView mcqview = new mcqFormView();
            EssayFormView essayview = new EssayFormView();
            ToggleGroup tg = new ToggleGroup();

            RadioButton mcq = new RadioButton("MCQ Question");
            RadioButton essay = new RadioButton("Essay Question");
            mcq.setToggleGroup(tg);
            essay.setToggleGroup(tg);
            mcq.setSelected(true);
            essayview.setVisible(false);

            tg.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
                if (newToggle == mcq) {
                    mcqview.setVisible(true);
                    essayview.setVisible(false);

                } else if (newToggle == essay) {
                    mcqview.setVisible(false);
                    essayview.setVisible(true);

                } 
            });

            HBox radioSelection = new HBox(20);
            radioSelection.setPadding(new Insets(10, 0, 15, 10));
            radioSelection.getChildren().addAll(mcq, essay);


            StackPane contentPane = new StackPane();
            contentPane.getChildren().addAll(mcqview, essayview);
            contentPane.setPadding(new Insets(0, 10, 0, 10));
            mcqview.setVisible(true);
    

            BorderPane QuestionsPane = new BorderPane();
            QuestionsPane.setTop(radioSelection);
            QuestionsPane.setCenter(contentPane);


            getChildren().add(QuestionsPane);

        }
    }

    public static class mcqFormView extends VBox{
        public mcqFormView() {
            Label qtext = new Label("Question: ");
            TextArea qtextField = new TextArea();
            qtextField.setMaxSize(394, 80);
            qtextField.setWrapText(true);
            HBox qtextBox = new HBox(qtext, qtextField);
            qtextBox.setPadding(new Insets(0, 0, 10, 0));

            /* ########################################### */

            Label atext = new Label("Choice A: ");
            TextArea atextField = new TextArea();
            Label spacer = new Label("    ");
            atextField.setMaxSize(230, 45);
            atextField.setWrapText(true);
            Label topictext = new Label("Topic/disease: ");
            TextField topictextField = new TextField();
            VBox topicBox = new VBox(topictext, topictextField);
            // topictextField.setMaxSize(230, 40);

            HBox atextBox = new HBox(atext, atextField, spacer, topicBox);
            atextBox.setPadding(new Insets(0, 0, 10, 0));

            Label btext = new Label("Choice B: ");
            TextArea btextField = new TextArea();
            Label spacer1 = new Label("    ");
            btextField.setMaxSize(230, 45);
            btextField.setWrapText(true);
            Label answertext = new Label("Answer: ");
            TextField answertextField = new TextField();
            VBox answerBox = new VBox(answertext, answertextField);
            // topictextField.setMaxSize(230, 40);

            HBox btextBox = new HBox(btext, btextField, spacer1, answerBox);
            btextBox.setPadding(new Insets(0, 0, 10, 0));

            Label ctext = new Label("Choice C: ");
            TextArea ctextField = new TextArea();
            Label spacer2 = new Label("    ");
            ctextField.setMaxSize(230, 45);
            ctextField.setWrapText(true);
            Label difficultytext = new Label("Difficulty: ");
            TextField difficultytextField = new TextField();
            VBox difficultyBox = new VBox(difficultytext, difficultytextField);
            // topictextField.setMaxSize(230, 40);

            HBox ctextBox = new HBox(ctext, ctextField, spacer2, difficultyBox);
            ctextBox.setPadding(new Insets(0, 0, 10, 0));

            Label dtext = new Label("Choice D: ");
            TextArea dtextField = new TextArea();
            Label spacer3 = new Label("    ");
            Label spacer4 = new Label("    ");
            spacer4.setPadding(new Insets(10, 0, 0, 0));
            dtextField.setMaxSize(230, 45);
            dtextField.setWrapText(true);
            Button addButton = new Button("    Add\nQuestion");
            Button LoadButton = new Button("    Load\nQuestions");

            HBox dtextBox = new HBox(dtext, dtextField, spacer3, addButton, spacer4, LoadButton);
            dtextBox.setPadding(new Insets(0, 0, 10, 0));

            /* ########################################### */

            addButton.setOnAction(e -> {
                try {
                    String qtextstr = qtextField.getText();
                    String atextFieldstr = atextField.getText();
                    String btextFieldstr = btextField.getText();
                    String ctextFieldstr = ctextField.getText();
                    String dtextFieldstr = dtextField.getText();
                    String topictextFieldstr = topictextField.getText();
                    String answertextFieldstr = answertextField.getText();
                    String difficultytextFieldstr = difficultytextField.getText();
                    
                    // DataBase db = DataBase.getInstance();
                    Connection conn = DataBase.CreateTabelesOneTime();

                    DataBase.addMcqQuestion(qtextstr, atextFieldstr, btextFieldstr, ctextFieldstr, dtextFieldstr,
                    topictextFieldstr, answertextFieldstr, difficultytextFieldstr, conn);
                    
                    qtextField.clear();
                    atextField.clear();
                    btextField.clear();
                    ctextField.clear();
                    dtextField.clear();
                    topictextField.clear();
                    answertextField.clear();
                    difficultytextField.clear();

                    System.out.println("added one question successfully");
                    // System.out.println(qtextstr);
                    // System.out.println(atextFieldstr);
                    // System.out.println(btextFieldstr);
                    // System.out.println(ctextFieldstr);
                    // System.out.println(dtextFieldstr);
                    // System.out.println(topictextFieldstr);
                    // System.out.println(answertextFieldstr);
                    // System.out.println(difficultytextFieldstr);
                } catch (SQLException ex) {
                    System.out.println(e);
                }
            });

            LoadButton.setOnAction(e -> {

                try {
                    Connection conn = DataBase.CreateTabelesOneTime();
                    Stage fileStage = new Stage();
                    
                    FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Select a File");

                    // Set extension filters (optional)
                    fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                        new FileChooser.ExtensionFilter("Word Documents", "*.docx"),
                        new FileChooser.ExtensionFilter("All Files", "*.*")
                    );

                    // Show open file dialog
                    File selectedFile = fileChooser.showOpenDialog(fileStage);
                    
                    // Check if a file was selected
                    if (selectedFile != null) {
                        
                        DataBase.addQuestionsFromFile(selectedFile.getAbsolutePath(), conn);

                        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    }

                } catch (SQLException e1) {
                    System.out.println(e);
                }
            });
            
            
    
            this.getChildren().addAll(qtextBox, atextBox, btextBox, ctextBox, dtextBox);
        }
    }

    public static class EssayFormView extends VBox{
        public EssayFormView() {
            Label qtext = new Label("Question:   ");
            TextArea qtextField = new TextArea();
            qtextField.setMaxSize(394, 60);
            qtextField.setWrapText(true);
            HBox qtextBox = new HBox(qtext, qtextField);
            qtextBox.setPadding(new Insets(0, 0, 10, 0));

            /* ########################################### */

            Label topictext = new Label("Topic:         ");
            TextField topictextField = new TextField();

            Label spacing0 = new Label("    ");

            Label typetext = new Label("Type:      ");
            TextField typetextField = new TextField();

            HBox atextBox = new HBox(topictext, topictextField, spacing0, typetext, typetextField);
            atextBox.setPadding(new Insets(0, 0, 10, 0));

            Label answertext = new Label("Answer:      ");
            TextField answertextField = new TextField();
            
            HBox btextBox = new HBox(answertext, answertextField);
            btextBox.setPadding(new Insets(0, 0, 10, 0));

            Label difficultytext = new Label("Difficulty:    ");
            TextField difficultytextField = new TextField();
            Label spacer3 = new Label("    ");
            Label spacer4 = new Label("    ");
            Button addButton = new Button("Add Question");
            Button LoadButton = new Button("Load Questions");

            HBox difficultyBox = new HBox(addButton, spacer3, LoadButton);
            difficultyBox.setPadding(new Insets(0, 0, 10, 0));

            HBox ctextBox = new HBox(difficultytext, difficultytextField, spacer4, difficultyBox);
            ctextBox.setPadding(new Insets(0, 0, 10, 0));

            addButton.setOnAction(e -> {
                try {
                    String qtextstr = qtextField.getText();
                    String typetextFieldstr = typetextField.getText();
                    String topictextFieldstr = topictextField.getText();
                    String answertextFieldstr = answertextField.getText();
                    String difficultytextFieldstr = difficultytextField.getText();
                    
                    Connection conn = DataBase.CreateTabelesOneTime();

                    DataBase.addEssayQuestion(qtextstr, topictextFieldstr, answertextFieldstr, difficultytextFieldstr, typetextFieldstr, conn);
                    
                    qtextField.clear();
                    topictextField.clear();
                    answertextField.clear();
                    difficultytextField.clear();
                    typetextField.clear();

                    System.out.println("added one question successfully");
                    // System.out.println(qtextstr);
                    // System.out.println(atextFieldstr);
                    // System.out.println(btextFieldstr);
                    // System.out.println(ctextFieldstr);
                    // System.out.println(dtextFieldstr);
                    // System.out.println(topictextFieldstr);
                    // System.out.println(answertextFieldstr);
                    // System.out.println(difficultytextFieldstr);
                } catch (SQLException ex) {
                    System.out.println(e);
                }
            });

            LoadButton.setOnAction(e -> {

                Stage fileStage = new Stage();

                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select a File");

                // Set extension filters (optional)
                fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                    new FileChooser.ExtensionFilter("Word Documents", "*.docx"),
                    new FileChooser.ExtensionFilter("All Files", "*.*")
                );

                // Show open file dialog
                File selectedFile = fileChooser.showOpenDialog(fileStage);

                // Check if a file was selected
                if (selectedFile != null) {
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                }
            });

            this.getChildren().addAll(qtextBox, atextBox, btextBox, ctextBox);
        }
    }
    
    public static class UpdateQuestionView extends VBox{
        public UpdateQuestionView() {
            getChildren().add(new Button("update question"));
        }
    }

    public static class CreateExamView extends VBox{
        public CreateExamView() {
            getChildren().add(new Button("create exam"));
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

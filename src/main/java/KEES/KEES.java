package KEES;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.*;


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
        VBox sidebar = new VBox(20);
        sidebar.setPrefWidth(150);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPadding(new Insets(20, 0, 0, 0));

        Button addQuestionButton = new Button("Add Questions");
        Button UpdateQuestionButton = new Button("Update Questions");
        Button CreateExamButton = new Button("Create exam");
        sidebar.getChildren().addAll(addQuestionButton, UpdateQuestionButton, CreateExamButton);

        Separator verticalSeparator = new Separator();
        verticalSeparator.setOrientation(javafx.geometry.Orientation.VERTICAL);
        verticalSeparator.setPrefHeight(600); 

        HBox sidebarContainer = new HBox();
        sidebarContainer.getChildren().addAll(sidebar, verticalSeparator);

        
        BorderPane mainLayout = new BorderPane();
        mainLayout.setLeft(sidebarContainer);
        mainLayout.setCenter(new AddQuestionView());

        addQuestionButton.setOnAction(
            e -> {
                mainLayout.setCenter(new AddQuestionView());
            }
        );
        UpdateQuestionButton.setOnAction(
            e -> {
                mainLayout.setCenter(new UpdateQuestionView());
            }
        );
        CreateExamButton.setOnAction(
            e -> {
                mainLayout.setCenter(new CreateExamView());
            }
        );



        // Set up the scene and stage
        Scene scene = new Scene(mainLayout, 700, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("KEES Exam System");
        primaryStage.show();
    }
    
    

    // public static class FeaturesSideBar extends VBox{
    //     public FeaturesSideBar() {
    //     }
    // }
    
    public static class AddQuestionView extends VBox{
        public AddQuestionView() {
            
            mcqFormView mcqview = new mcqFormView();
            EssayFormView essayview = new EssayFormView();
            ShEssayFormView shessayview = new ShEssayFormView();
            ToggleGroup tg = new ToggleGroup();

            RadioButton mcq = new RadioButton("MCQ Question");
            RadioButton essay = new RadioButton("Essay Question");
            RadioButton sh_essay = new RadioButton("Short-Essay Question");
            mcq.setToggleGroup(tg);
            essay.setToggleGroup(tg);
            sh_essay.setToggleGroup(tg);
            mcq.setSelected(true);

            tg.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
                if (newToggle == mcq) {
                mcqview.setVisible(true);
                essayview.setVisible(false);
                shessayview.setVisible(false);
            } else if (newToggle == essay) {
                mcqview.setVisible(false);
                essayview.setVisible(true);
                shessayview.setVisible(false);
            } else if (newToggle == sh_essay) {
                mcqview.setVisible(false);
                essayview.setVisible(false);
                shessayview.setVisible(true);
            }
            });

            HBox radioSelection = new HBox(20);
            radioSelection.setPadding(new Insets(20, 0, 10, 10));
            radioSelection.getChildren().addAll(mcq, essay, sh_essay);


            StackPane contentPane = new StackPane();
            contentPane.getChildren().addAll(mcqview, essayview, shessayview);
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
            qtextField.setMaxSize(394, 60);
            qtextField.setWrapText(true);
            HBox qtextBox = new HBox(qtext, qtextField);
            qtextBox.setPadding(new Insets(0, 0, 10, 0));

            /* ########################################### */

            Label atext = new Label("Choice A: ");
            TextArea atextField = new TextArea();
            Label spacer = new Label("    ");
            atextField.setMaxSize(230, 45);
            atextField.setWrapText(true);
            Label topictext = new Label("Topic/Desiese: ");
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
            Label spacer4 = new Label(" or ");
            spacer4.setPadding(new Insets(10, 0, 0, 0));
            dtextField.setMaxSize(230, 45);
            dtextField.setWrapText(true);
            Button addButton = new Button("    Add\nQuestion");
            Button LoadButton = new Button("    Load\nQuestions");

            HBox dtextBox = new HBox(dtext, dtextField, spacer3, addButton, spacer4, LoadButton);
            dtextBox.setPadding(new Insets(0, 0, 10, 0));

            /* ########################################### */




            
    
            this.getChildren().addAll(qtextBox, atextBox, btextBox, ctextBox, dtextBox);
        }
    }

    public static class EssayFormView extends VBox{
        public EssayFormView() {
            Label qtext = new Label("Question: ");
            TextArea qtextField = new TextArea();
            qtextField.setMaxSize(394, 60);
            qtextField.setWrapText(true);
            HBox qtextBox = new HBox(qtext, qtextField);
            qtextBox.setPadding(new Insets(0, 0, 10, 0));

            /* ########################################### */

            Label atext = new Label("Choice A: ");
            TextArea atextField = new TextArea();
            Label spacer = new Label("    ");
            atextField.setMaxSize(230, 45);
            atextField.setWrapText(true);
            Label topictext = new Label("Topic/Desiese: ");
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
            Label spacer4 = new Label(" or ");
            spacer4.setPadding(new Insets(10, 0, 0, 0));
            dtextField.setMaxSize(230, 45);
            dtextField.setWrapText(true);
            Button addButton = new Button("    Add\nQuestion");
            Button LoadButton = new Button("    Load\nQuestions");

            HBox dtextBox = new HBox(dtext, dtextField, spacer3, addButton, spacer4, LoadButton);
            dtextBox.setPadding(new Insets(0, 0, 10, 0));

            /* ########################################### */




            
    
            this.getChildren().addAll(qtextBox, atextBox, btextBox, ctextBox, dtextBox);
        }
    }

    public static class ShEssayFormView extends VBox{
        public ShEssayFormView() {
            Label qtext = new Label("Question: ");
            TextArea qtextField = new TextArea();
            qtextField.setMaxSize(394, 60);
            qtextField.setWrapText(true);
            HBox qtextBox = new HBox(qtext, qtextField);
            qtextBox.setPadding(new Insets(0, 0, 10, 0));

            /* ########################################### */

            Label atext = new Label("Choice A: ");
            TextArea atextField = new TextArea();
            Label spacer = new Label("    ");
            atextField.setMaxSize(230, 45);
            atextField.setWrapText(true);
            Label topictext = new Label("Topic/Desiese: ");
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
            Label spacer4 = new Label(" or ");
            spacer4.setPadding(new Insets(10, 0, 0, 0));
            dtextField.setMaxSize(230, 45);
            dtextField.setWrapText(true);
            Button addButton = new Button("    Add\nQuestion");
            Button LoadButton = new Button("    Load\nQuestions");

            HBox dtextBox = new HBox(dtext, dtextField, spacer3, addButton, spacer4, LoadButton);
            dtextBox.setPadding(new Insets(0, 0, 10, 0));

            /* ########################################### */




            
    
            this.getChildren().addAll(qtextBox, atextBox, btextBox, ctextBox, dtextBox);
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

package KEES;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DataBase {
    private static DataBase singltonDatabase = null;

    public static DataBase getInstance() {
        if(DataBase.singltonDatabase == null) {
            DataBase.singltonDatabase = new DataBase();
        }

        return DataBase.singltonDatabase;
    }

    public static Connection CreateTabelesOneTime() throws SQLException {
        String url = "jdbc:sqlite:./BanksDB.db";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();

            String createMcqTableSQL = """
                CREATE TABLE IF NOT EXISTS mcqQuestions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    qText TEXT NOT NULL,
                    c1 TEXT NOT NULL,
                    c2 TEXT NOT NULL,
                    c3 TEXT NOT NULL,
                    c4 TEXT NOT NULL,
                    topic TEXT NOT NULL,
                    ans TEXT NOT NULL,
                    difficulty INTEGER NOT NULL
                );
                """;
            stmt.execute(createMcqTableSQL);

            String createEssayTableSQL = """
                CREATE TABLE IF NOT EXISTS essayQuestions (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    qText TEXT NOT NULL,
                    topic TEXT NOT NULL,
                    ans TEXT NOT NULL,
                    difficulty INTEGER NOT NULL
                );
                """;
            stmt.execute(createEssayTableSQL);

            return conn;
    }

    public static boolean addQuestionsFromFile(String filePath, Connection conn, Scanner input) {
        // int id;
        String qtext = "";
        String c1 = "";
        String c2 = "";
        String c3 = "";
        String c4 = "";
        String topic;
        String ans;
        int difficulty;
        
            
        System.out.println("Question type: ");
        System.out.println("1. MCQ");
        System.out.println("2. Essay");
        System.out.println("3. Short essay");
        int qtype = Integer.parseInt(input.nextLine());
        
        if(qtype == 1) {
            
            // System.out.println("id: ");
            // id = Integer.parseInt(input.nextLine());
            // System.out.println("qtext: ");
            // qtext = input.nextLine();
            // System.out.println("c1: ");
            // c1 = input.nextLine();
            // System.out.println("c2: ");
            // c2 = input.nextLine();
            // System.out.println("c3: ");
            // c3 = input.nextLine();
            // System.out.println("c4: ");
            // c4 = input.nextLine();
            // System.out.println("topic: ");
            // topic = input.nextLine();
            // System.out.println("ans: ");
            // ans = input.nextLine();
            // System.out.println("difficulty: ");
            // difficulty = Integer.parseInt(input.nextLine());

            try (Scanner testInput = new Scanner(new File(filePath))) {

                while(testInput.hasNextLine()) {
                    String line = testInput.nextLine();

                    if(line.isEmpty()) {
                        // continue;

                    } else if (line.charAt(0) == 'A' && line.charAt(1) == '.') {  // a
                        c1 = line.substring(3).toLowerCase();

                    } else if (line.charAt(0) == 'B' && line.charAt(1) == '.') { // b
                        c2 = line.substring(3).toLowerCase();
                        
                    } else if (line.charAt(0) == 'C' && line.charAt(1) == '.') { // c
                        c3 = line.substring(3).toLowerCase();
                        
                    } else if (line.charAt(0) == 'D' && line.charAt(1) == '.') { // d
                        c4 = line.substring(3).toLowerCase();

                    } else if (line.contains("***")) { // ***

                        String[] parts = line.split(" ");

                        ans = parts[1].toLowerCase();
                        topic = parts[2].toLowerCase();
                        difficulty = Integer.parseInt(parts[3]);

                        // mcqQuestion mcqQ = new mcqQuestion(/*id,*/ qtext, c1, c2, c3, c4, topic, ans, difficulty);
                    
                        String sqlInsertMCQ = """
                            INSERT INTO mcqQuestions (qText, c1, c2, c3, c4, topic, ans, difficulty)
                            VALUES (?, ?, ?, ?, ?, ?, ?, ?)
                            """;
                            
                        PreparedStatement pstmt = conn.prepareStatement(sqlInsertMCQ);
                        pstmt.setString(1, qtext);
                        pstmt.setString(2, c1);
                        pstmt.setString(3, c2);
                        pstmt.setString(4, c3);
                        pstmt.setString(5, c4);
                        pstmt.setString(6, topic);
                        pstmt.setString(7, ans);
                        pstmt.setInt(8, difficulty);
                        pstmt.executeUpdate();

                        qtext = "";

                    } else {
                        qtext += line;
                    }
                    
                }
                
                testInput.close();
                return true;

            } catch (NumberFormatException | FileNotFoundException | SQLException e) {
                System.out.println(e);
                return false;

            }
            
        } else if (qtype == 2) {
            
            // System.out.println("id: ");
            // id = Integer.parseInt(input.nextLine());
            // System.out.println("qtext: ");
            // qtext = input.nextLine();
            // System.out.println("topic: ");
            // topic = input.nextLine();
            // System.out.println("ans: ");
            // ans = input.nextLine();
            // System.out.println("difficulty: ");
            // difficulty = Integer.parseInt(input.nextLine());
            
            // essayQuestion essQ = new essayQuestion(id, qtext, topic, ans, difficulty);

            // String sqlInsertEssay = """
            //     INSERT INTO essayQuestions (qText, topic, ans, difficulty)
            //             VALUES (?, ?, ?, ?)
            //     """;

            // PreparedStatement pstmt = conn.prepareStatement(sqlInsertEssay);
            // pstmt.setString(1, qtext);
            // pstmt.setString(2, topic);
            // pstmt.setString(3, ans);
            // pstmt.setInt(4, difficulty);
            // pstmt.executeUpdate();
            return true;
            
        }

        return true;
    }

    public static boolean createExamWithConditions(String saveFilePath, Connection conn, Scanner input) {

        System.out.println("Number Of Questions in the whole exam: ");
        int totalNumOfQuestions = Integer.parseInt(input.nextLine());
        
        String doc = "";
        while(totalNumOfQuestions != 0) {
            
            System.out.println("Topic of questions to add: ");
            String questionTopic = input.nextLine().toLowerCase();

            System.out.println("difficulty of the questions to add: ");
            int difficultyOfThatQuestion = Integer.parseInt(input.nextLine().toLowerCase());

            System.out.println("Number Of Questions of that type: ");
            int numOfThatQuestion = Integer.parseInt(input.nextLine().toLowerCase());

            String selectSQL = """
                SELECT * 
                FROM mcqQuestions
                WHERE topic = ? AND difficulty = ?
                LIMIT ?
                """;

            try (PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
                
                pstmt.setString(1, questionTopic);
                pstmt.setInt(2, difficultyOfThatQuestion);
                pstmt.setInt(3, numOfThatQuestion);

                ResultSet rs = pstmt.executeQuery();

                while(rs.next()) {

                    doc += rs.getString("id") + ". " +  rs.getString("qText") + 
                    "\nA.  " +  rs.getString("c1") + 
                    "\nB.  " +  rs.getString("c2") + 
                    "\nC.  " +  rs.getString("c3") + 
                    "\nD.  " +  rs.getString("c4") + 
                    "\nANS:  " +  rs.getString("ans")  + 
                    "\nTopic:  " +  rs.getString("topic")  + 
                    "\nDifficulty:  " +  rs.getString("difficulty") + "\n\n";

                }

            } catch (SQLException e) {
                System.out.println(e);
                return false;
            }

            totalNumOfQuestions -= numOfThatQuestion;
        }
        
        // System.out.println(doc);
        File testExam = new File(saveFilePath);
        try (FileWriter writter = new FileWriter(testExam)) {
            writter.write(doc);
            writter.close();

        } catch (IOException e) {
            System.out.println(e);
            return false;

        }
        return true;
    }
}

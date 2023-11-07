package Survey;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Survey {


    public static void ViewSurveys(){
        String fileName = "src/Text Files/Surveys.txt";

        List<String> listOfSurveys;
        try {
            listOfSurveys = Files.readAllLines(Paths.get(fileName));
            for (int i = 0; i < listOfSurveys.size(); i++){
                String[] e1 = listOfSurveys.get(i).split("␜");
                List<String> surveyDetails = Arrays.asList(e1);
                // for (int i2 = 0; i2 < surveyDetails.size();){
                    System.out.println("SID: " + surveyDetails.get(0));
                    System.out.println("Survey Title: " + surveyDetails.get(1));
                    System.out.println("Created By: " + surveyDetails.get(2));
                    System.out.println("Status: " + surveyDetails.get(3));
                    String[] e2 = surveyDetails.get(4).split("␝");
                    List<String> questionList = Arrays.asList(e2);
                    for (int i3 = 0; i3 < questionList.size(); i3++){
                        if (i3 % 2 == 0){
                            System.out.println("Question Type: " + questionList.get(i3));
                        } else {
                            String[] e3 = questionList.get(i3).split("␞");
                            List<String> questionDetails = Arrays.asList(e3);
                            System.out.println("Question " + ((i3 / 2) + 1) + " : " + questionDetails.get(0));
                            for (int i4 = 1; i4 < questionDetails.size(); i4++){
                                System.out.println("Answer " + i4 + ": " + questionDetails.get(i4));
                            }
                        }
                    }
                    // break;
                // }
                System.out.println("-----------------------------------");
            }
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

    public static void ChangeStatus(String surveyID, String status){
        String fileName = "src/Text Files/Surveys.txt";

        List<String> listOfSurveys;
        try {
            listOfSurveys = Files.readAllLines(Paths.get(fileName));
            for (int i = 0; i < listOfSurveys.size(); i++){
                String[] e1 = listOfSurveys.get(i).split("␜");
                List<String> surveyDetails = Arrays.asList(e1);
                if (surveyID.equals(surveyDetails.get(0))){
                    // surveyDetails.set(3, status);
                    String newRecord = "";
                    for (int i2 = 0; i2 < surveyDetails.size(); i2++){
                        if (i2 == 3){
                            newRecord = newRecord + status;
                        } else {
                            newRecord = newRecord + surveyDetails.get(i2);
                        }
                        if (i2 != (surveyDetails.size() - 1)) {
                            newRecord = newRecord + "␜";
                        }
                    }
                    UpdateFile(i, newRecord);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }

    }

    public static void CreateSurvey(){
        String fileName = "src/Text Files/Surveys.txt";
        List<String> listOfSurveys;
        int index = 1;

        File file = new File(fileName);
        if (file.length() == 0){
            index = 1;
        } else {
            try {
                listOfSurveys = Files.readAllLines(Paths.get(fileName));
                String[] e1 = listOfSurveys.get(listOfSurveys.size() - 1).split("␜");
                List<String> surveyDetails = Arrays.asList(e1);
                index =  Integer.valueOf(surveyDetails.get(0).substring(1));
                index++;
            } catch (FileNotFoundException e1) {
                System.out.println("File Not Found");
                e1.printStackTrace();
            } catch (IOException e1) {
                System.out.println("IO Exception");
                e1.printStackTrace();
            }
            
        }

        String surveyTitle = "Survey " + index;
        String createdBy = "SC3";
        String status = "not-approved";
        String questionDetails = " ";

        try {
            listOfSurveys = Files.readAllLines(Paths.get(fileName));
            String newRecord = "S" + index + "␜" + surveyTitle + "␜" + createdBy + "␜" + status + "␜"  + questionDetails;
            System.out.println(newRecord);
            UpdateFile(listOfSurveys.size() + 1, newRecord);

            // FileWriter fileWritter = new FileWriter(file,true);        
            // BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            // bufferWritter.write(newRecord);
            // bufferWritter.close();
            // fileWritter.close();
        } catch (IOException e) {
            System.out.println("IO Exception");
            e.printStackTrace();
        }
    }

    public static void EditTitle(String surveyID, String newTitle){
        String fileName = "src/Text Files/Surveys.txt";

        List<String> listOfSurveys;
        try {
            listOfSurveys = Files.readAllLines(Paths.get(fileName));
            for (int i = 0; i < listOfSurveys.size(); i++){
                String[] e1 = listOfSurveys.get(i).split("␜");
                List<String> surveyDetails = Arrays.asList(e1);
                if (surveyID.equals(surveyDetails.get(0))){
                    String newRecord = "";
                    for (int i2 = 0; i2 < surveyDetails.size(); i2++){
                        if (i2 == 1){
                            newRecord = newRecord + newTitle;
                        } else {
                            newRecord = newRecord + surveyDetails.get(i2);
                        }
                        if (i2 != (surveyDetails.size() - 1)) {
                            newRecord = newRecord + "␜";
                        }
                    }
                    UpdateFile(i, newRecord);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

    public static void AddQuestion(String surveyID){
        String fileName = "src/Text Files/Surveys.txt";

        //build the question and answers into a single string
        // qNo = 0 means question 1, qNo = 1 means question 2 etc...
        int qNo = 3;
        String questionType = "MCQ";
        String question = "How many days in January2?";

        List<String> mcqAnswers = new ArrayList<>();
        mcqAnswers.add("29");
        mcqAnswers.add("30");
        mcqAnswers.add("31");
        mcqAnswers.add("32");

        String polarAnswer1 = "Yes";
        String polarAnswer2 = "No";

        String singleQuestion = questionType + "␝" + question;
        switch (questionType) {
            case "MCQ":
                for (int i = 0; i < mcqAnswers.size(); i++){
                    singleQuestion = singleQuestion + "␞" + mcqAnswers.get(i);
                }
                break;
            case "Polar":
                singleQuestion = singleQuestion + "␞" + polarAnswer1 + "␞" + polarAnswer2;
                break;
            default:
                break;
        }

        //inserts new question into surveydetails array
        List<String> listOfSurveys;
        try {
            listOfSurveys = Files.readAllLines(Paths.get(fileName));
            for (int i = 0; i < listOfSurveys.size(); i++){
                String[] e1 = listOfSurveys.get(i).split("␜");
                List<String> surveyDetails = Arrays.asList(e1);
                if (surveyID.equals(surveyDetails.get(0))){
                    String[] e2 = surveyDetails.get(4).split("␝");
                    List<String> questionList = Arrays.asList(e2);
                    String fullQuestions = "";
                    for (int i2 = 0; i2 < questionList.size(); i2++){
                        if (i2 == (qNo * 2)){
                            fullQuestions = fullQuestions + singleQuestion + "␝";
                        }
                        fullQuestions = fullQuestions + questionList.get(i2);
                        if (i2 != (questionList.size() - 1)) {
                            fullQuestions = fullQuestions + "␝";
                        }
                    }
                    if ((qNo * 2) >= questionList.size()){
                        fullQuestions = fullQuestions + "␝" + singleQuestion;
                    }
                    surveyDetails.set(4, fullQuestions);
                    String newRecord = "";
                    for (int i2 = 0; i2 < surveyDetails.size(); i2++){
                            newRecord = newRecord + surveyDetails.get(i2);
                        if (i2 != (surveyDetails.size() - 1)) {
                            newRecord = newRecord + "␜";
                        }
                    }
                    UpdateFile(i, newRecord);
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

    public static void EditQuestion(String surveyID){
        String fileName = "src/Text Files/Surveys.txt";

        // qNo = 0 means question 1, qNo = 1 means question 2 etc...
        int qNo = 1;

        String newQ = "What is your gender?";
        String newA1 = "M";
        String newA2 = "F";
        // String newA3 = "29";
        // String newA4 = "30";

        String singleQuestion = "";
        
        List<String> listOfSurveys;
        try {
            listOfSurveys = Files.readAllLines(Paths.get(fileName));
            for (int i = 0; i < listOfSurveys.size(); i++){
                String[] e1 = listOfSurveys.get(i).split("␜");
                List<String> surveyDetails = Arrays.asList(e1);
                if (surveyID.equals(surveyDetails.get(0))){
                    String[] e2 = surveyDetails.get(4).split("␝");
                    List<String> questionList = Arrays.asList(e2);
                    for (int i3 = 0; i3 < questionList.size(); i3++){
                        if (i3 == ((qNo * 2) + 1)){
                            String[] e3 = questionList.get(i3).split("␞");
                            List<String> questionDetails = Arrays.asList(e3);
                            //set new question and answers here
                            questionDetails.set(0, newQ);
                            questionDetails.set(1, newA1);
                            questionDetails.set(2, newA2);
                            // questionDetails.set(3, newA3);
                            // questionDetails.set(4, newA4);
                            for (int i4 = 0; i4 < questionDetails.size(); i4++){
                                singleQuestion = singleQuestion + questionDetails.get(i4);
                                if (i4 != questionDetails.size() - 1){
                                    singleQuestion = singleQuestion +  "␞";
                                }
                            }
                            System.out.println(singleQuestion);
                            questionList.set(i3, singleQuestion);
                            String fullQuestions = "";
                            for (int i4 = 0; i4 < questionList.size(); i4++){
                                fullQuestions = fullQuestions + questionList.get(i4);
                                if (i4 != (questionList.size() - 1)) {
                                    fullQuestions = fullQuestions + "␝";
                                }
                            }
                            surveyDetails.set(4, fullQuestions);
                            String newRecord = "";
                            for (int i2 = 0; i2 < surveyDetails.size(); i2++){
                                    newRecord = newRecord + surveyDetails.get(i2);
                                if (i2 != (surveyDetails.size() - 1)) {
                                    newRecord = newRecord + "␜";
                                }
                            }
                            UpdateFile(i, newRecord);
                        }
                    }
                }
            }
        }  catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }

    }

    public static void UpdateFile(int lineNumber, String newRecord) throws IOException {
        // Path path = Paths.get("src/Text Files/Surveys.txt");
        // List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        // lines.set(lineNumber, data);
        // Files.write(path, lines, StandardCharsets.UTF_8);

        String fileName = "src/Text Files/Surveys.txt";
        List<String> listOfSurveys;
        try {
            listOfSurveys = Files.readAllLines(Paths.get(fileName));
            if (lineNumber >= listOfSurveys.size()){
                listOfSurveys.add(newRecord);
            } else {
                listOfSurveys.set(lineNumber, newRecord);
            }
            File file = new File(fileName);
            FileWriter fileWritter = new FileWriter(file, false);        
            BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
            for (int i = 0; i < listOfSurveys.size(); i++){
                if (i != 0){
                    bufferWritter.write("\n");
                }
                bufferWritter.write(listOfSurveys.get(i));
            }
            bufferWritter.close();
            fileWritter.close();
            System.out.println("Updated Successfully");
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

}

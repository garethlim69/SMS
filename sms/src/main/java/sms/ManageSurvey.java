package sms;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class ManageSurvey implements Initializable{




    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

    }













    
    public static void ViewSurveys(){
        String fileName = "target/classes/Text Files/Surveys.txt";

        List<String> listOfSurveys;
        try {
            listOfSurveys = Files.readAllLines(Paths.get(fileName));
            for (int i = 0; i < listOfSurveys.size(); i++){
                String[] e1 = listOfSurveys.get(i).split("␜");
                List<String> surveyDetails = Arrays.asList(e1);
                for (int i2 = 0; i2 < surveyDetails.size();){
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
                    break;
                }
                System.out.println("-----------------------------------");
            }
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

}

package sms;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


public class ManageSurvey implements Initializable{
    @FXML private Label lblSID1;
    @FXML private Label lblSID2;
    @FXML private Label lblSID3;
    @FXML private Label lblSID4;
    @FXML private Label lblSID5;

    @FXML private Label lblTitle1;
    @FXML private Label lblTitle2;
    @FXML private Label lblTitle3;
    @FXML private Label lblTitle4;
    @FXML private Label lblTitle5;

    @FXML private Label lblStatus1;
    @FXML private Label lblStatus2;
    @FXML private Label lblStatus3;
    @FXML private Label lblStatus4;
    @FXML private Label lblStatus5;

    @FXML private Label lblSCID1;
    @FXML private Label lblSCID2;
    @FXML private Label lblSCID3;
    @FXML private Label lblSCID4;
    @FXML private Label lblSCID5;

    @FXML private Button btnView1;
    @FXML private Button btnView2;
    @FXML private Button btnView3;
    @FXML private Button btnView4;
    @FXML private Button btnView5;

    @FXML private Button btnBlock1;
    @FXML private Button btnBlock2;
    @FXML private Button btnBlock3;
    @FXML private Button btnBlock4;
    @FXML private Button btnBlock5;

    @FXML private Button btnResponses1;
    @FXML private Button btnResponses2;
    @FXML private Button btnResponses3;
    @FXML private Button btnResponses4;
    @FXML private Button btnResponses5;

    @FXML private Button btnDelete1;
    @FXML private Button btnDelete2;
    @FXML private Button btnDelete3;
    @FXML private Button btnDelete4;
    @FXML private Button btnDelete5;

    @FXML private Label lblPageNo;
    @FXML private Button btnNext;
    @FXML private Button btnPrev;
    
    int pageNo;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        pageNo = 1;
        ViewSurveys(pageNo);
    }

    @FXML
    private void switchAdminDashboard() throws IOException {
        App.setRoot("adminDashboard");
    }


    public void PreviousPage(){
        pageNo--;
        if (pageNo == 1) {
            btnPrev.setDisable(true);
            btnNext.setDisable(false);
        }
        ViewSurveys(pageNo);
    }

    public void NextPage(){
        pageNo++;
        if (pageNo > 1) {
            btnPrev.setDisable(false);
        }
        ViewSurveys(pageNo);
    }
    
    @FXML
    public void ViewSurveys(int pageNo){
        String fileName = "target/classes/Text Files/Surveys.txt";
        HashMap<String, String> surveyDetailsMap = new HashMap<String, String>();

        lblSID2.setText("");
        lblTitle2.setText("");
        lblStatus2.setText("");
        lblSCID2.setText("");
        btnView2.setVisible(false);
        btnBlock2.setVisible(false);
        btnResponses2.setVisible(false);
        btnDelete2.setVisible(false);

        lblSID3.setText("");
        lblTitle3.setText("");
        lblStatus3.setText("");
        lblSCID3.setText("");
        btnView3.setVisible(false);
        btnBlock3.setVisible(false);
        btnResponses3.setVisible(false);
        btnDelete3.setVisible(false);

        lblSID4.setText("");
        lblTitle4.setText("");
        lblStatus4.setText("");
        lblSCID4.setText("");
        btnView4.setVisible(false);
        btnBlock4.setVisible(false);
        btnResponses4.setVisible(false);
        btnDelete4.setVisible(false);

        lblSID5.setText("");
        lblTitle5.setText("");
        lblStatus5.setText("");
        lblSCID5.setText("");
        btnView5.setVisible(false);
        btnBlock5.setVisible(false);
        btnResponses5.setVisible(false);
        btnDelete5.setVisible(false);

        List<String> listOfSurveys;
        try {
            listOfSurveys = Files.readAllLines(Paths.get(fileName));
            int noOfSurveys = listOfSurveys.size();
            if (pageNo * 5 > listOfSurveys.size()){
                btnNext.setDisable(true);
            } else {
                noOfSurveys = 5;
            }
            if (pageNo == 1){
                btnPrev.setDisable(true);
            }
            lblPageNo.setText("Page " + String.valueOf(pageNo) + "/" + (int)Math.ceil((10 / 5.0)));
            for (int i = 0; i < listOfSurveys.size(); i++){
                String[] e1 = listOfSurveys.get(i).split("␜");
                List<String> surveyDetails = Arrays.asList(e1);
                if (!surveyDetails.get(3).equals("deleted")){
                    surveyDetailsMap.put("S" + (i + 1) + "SID", surveyDetails.get(0));
                    surveyDetailsMap.put("S" + (i + 1) + "Title", surveyDetails.get(1));
                    surveyDetailsMap.put("S" + (i + 1) + "SCID", surveyDetails.get(2));
                    surveyDetailsMap.put("S" + (i + 1) + "Status", surveyDetails.get(3));
                }
            }
            int i = (pageNo * 5) - 4;

            // System.out.println(listOfSurveys.size() % 5);

            lblSID1.setText(surveyDetailsMap.get("S" + i + "SID"));
            lblTitle1.setText(surveyDetailsMap.get("S" + i + "Title"));
            lblStatus1.setText(surveyDetailsMap.get("S" + i + "Status"));
            lblSCID1.setText(surveyDetailsMap.get("S" + i + "SCID"));
            switch (surveyDetailsMap.get("S" + i + "Status")) {
                case "not-approved":
                    btnBlock1.setText("approve");
                    //ChangeStatus(surveyDetailsMap.get("S" + (i + 1)+ "SID"), "approved")
                    break;
                case "approved":
                    btnBlock1.setText("block");
                    //ChangeStatus(surveyDetailsMap.get("S" + (i + 1)+ "SID"), "blocked")
                    break;
                case "blocked":
                    btnBlock1.setText("unblock");
                    //ChangeStatus(surveyDetailsMap.get("S" + (i + 1)+ "SID"), "approved")
                    break;
            }

            if (noOfSurveys % 5 > 1 || noOfSurveys % 5 == 0){
                lblSID2.setText(surveyDetailsMap.get("S" + (i + 1)+ "SID"));
                lblTitle2.setText(surveyDetailsMap.get("S" + (i + 1) + "Title"));
                lblStatus2.setText(surveyDetailsMap.get("S" + (i + 1) + "Status"));
                lblSCID2.setText(surveyDetailsMap.get("S" + (i + 1) + "SCID"));
                btnView2.setVisible(true);
                btnBlock2.setVisible(true);
                switch (surveyDetailsMap.get("S" + (i + 1) + "Status")) {
                    case "not-approved":
                        btnBlock2.setText("approve");
                        //ChangeStatus(surveyDetailsMap.get("S" + (i + 1)+ "SID"), "approved")
                        break;
                    case "approved":
                        btnBlock2.setText("block");
                        //ChangeStatus(surveyDetailsMap.get("S" + (i + 1)+ "SID"), "blocked")
                        break;
                    case "blocked":
                        btnBlock2.setText("unblock");
                        //ChangeStatus(surveyDetailsMap.get("S" + (i + 1)+ "SID"), "approved")
                        break;
                }
                btnResponses2.setVisible(true);
                btnDelete2.setVisible(true);
                if (noOfSurveys % 5 > 2 || noOfSurveys % 5 == 0){
                    lblSID3.setText(surveyDetailsMap.get("S" + (i + 2) + "SID"));
                    lblTitle3.setText(surveyDetailsMap.get("S" + (i + 2) + "Title"));
                    lblStatus3.setText(surveyDetailsMap.get("S" + (i + 2) + "Status"));
                    lblSCID3.setText(surveyDetailsMap.get("S" + (i + 2) + "SCID"));
                    btnView3.setVisible(true);
                    btnBlock3.setVisible(true);
                    switch (surveyDetailsMap.get("S" + (i + 2) + "Status")) {
                        case "not-approved":
                            btnBlock3.setText("approve");
                            //ChangeStatus(surveyDetailsMap.get("S" + (i + 2)+ "SID"), "approved")
                            break;
                        case "approved":
                            btnBlock3.setText("block");
                            //ChangeStatus(surveyDetailsMap.get("S" + (i + 2)+ "SID"), "blocked")
                            break;
                        case "blocked":
                            btnBlock3.setText("unblock");
                            //ChangeStatus(surveyDetailsMap.get("S" + (i + 2)+ "SID"), "approved")
                            break;
                    }
                    btnResponses3.setVisible(true);
                    btnDelete3.setVisible(true);
                    if (noOfSurveys % 5 > 3 || noOfSurveys % 5 == 0){
                        lblSID4.setText(surveyDetailsMap.get("S" + (i + 3) + "SID"));
                        lblTitle4.setText(surveyDetailsMap.get("S" + (i + 3) + "Title"));
                        lblStatus4.setText(surveyDetailsMap.get("S" + (i + 3) + "Status"));
                        lblSCID4.setText(surveyDetailsMap.get("S" + (i + 3) + "SCID"));
                        btnView4.setVisible(true);
                        btnBlock4.setVisible(true);
                        switch (surveyDetailsMap.get("S" + (i + 3) + "Status")) {
                            case "not-approved":
                                btnBlock4.setText("approve");
                                //ChangeStatus(surveyDetailsMap.get("S" + (i + 3)+ "SID"), "approved")
                                break;
                            case "approved":
                                btnBlock4.setText("block");
                                //ChangeStatus(surveyDetailsMap.get("S" + (i + 3)+ "SID"), "blocked")
                                break;
                            case "blocked":
                                btnBlock4.setText("unblock");
                                //ChangeStatus(surveyDetailsMap.get("S" + (i + 3)+ "SID"), "approved")
                                break;
                        }
                        btnResponses4.setVisible(true);
                        btnDelete4.setVisible(true);
                        if (noOfSurveys % 5 == 0){
                            lblSID5.setText(surveyDetailsMap.get("S" + (i + 4) + "SID"));
                            lblTitle5.setText(surveyDetailsMap.get("S" + (i + 4) + "Title"));
                            lblStatus5.setText(surveyDetailsMap.get("S" + (i + 4) + "Status"));
                            lblSCID5.setText(surveyDetailsMap.get("S" + (i + 4) + "SCID"));
                            btnView5.setVisible(true);
                            btnBlock5.setVisible(true);
                            switch (surveyDetailsMap.get("S" + (i + 4) + "Status")) {
                                case "not-approved":
                                    btnBlock5.setText("approve");
                                    //ChangeStatus(surveyDetailsMap.get("S" + (i + 4)+ "SID"), "approved")
                                    break;
                                case "approved":
                                    btnBlock5.setText("block");
                                    //ChangeStatus(surveyDetailsMap.get("S" + (i + 4)+ "SID"), "blocked")
                                    break;
                                case "blocked":
                                    btnBlock5.setText("unblock");
                                    //ChangeStatus(surveyDetailsMap.get("S" + (i + 4)+ "SID"), "approved")
                                    break;
                            }
                            btnResponses5.setVisible(true);
                            btnDelete5.setVisible(true);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

}

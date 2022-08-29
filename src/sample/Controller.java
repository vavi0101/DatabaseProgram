package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.swing.*;

public class Controller
{
    @FXML
    private ListView<String> lvData;

    private int recordID;
    private String name, color,famous;
    private int id, size;

    @FXML
    private TextField txtID;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtColor;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtFamous;

    //Class object
    private DBManager manager = new DBManager();

   //List
    private ObservableList<String>planets = FXCollections.observableArrayList();

   //onAction handlers
    @FXML
    void HandleCreateButton(ActionEvent event)
    {
        manager.createTable();
    }

    @FXML
    void HandlePopulateData(ActionEvent event)
    {
        manager.populateDatabase();
    }

    @FXML

    void HandleDelete(ActionEvent event)
    {
        //delete
        manager.deleteRecord(recordID);
    }

    @FXML
    void HandleDisplay(ActionEvent event)
    {
        displayDataBase();
    }

    @FXML
    void HandleEdit(ActionEvent event)
    {
        manager.getRecordById(recordID);
        //Edit records
        manager.editRecord(id, name, color, size, famous);
        clearTextFields();
        displayDataBase();
    }

    @FXML
    void HandleEnterID(ActionEvent event)
    {

        try {
            //get the text out of the id text field
            //txtID.getText();
            String temp = txtID.getText();
            //parse into an int
            //int ID =1;
            recordID = Integer.parseInt(temp);
        }
        catch (StringIndexOutOfBoundsException id) {

            JOptionPane.showMessageDialog(null, " Enter a valid number");
        }

        //Create an empty array of Strings
        String[] cols = new String[4];
        //Call record ID and assign to the array that you already have
        cols = manager.getRecordById(recordID);

        //now assign elements of the array to the text fields on the form

        cols[0] = txtName.getText();
        cols[1] = txtColor.getText();
        cols[2] = txtSize.getText();
        cols[3] = txtFamous.getText();
    }

    @FXML
    void HandleInsert(ActionEvent event)
    {

        //text fields are empty user enters information
        getRecordValues();
        //get the next new id by calling
        recordID = manager.getLastID();
        recordID++;

        //insert records
        /*manager.insert(Integer.parseInt(txtID.getText()),txtName.getText(),
                txtColor.getText(),Integer.parseInt(txtSize.getText()),txtFamous.getText());*/

        manager.insert(recordID, name, color, size, famous);
        displayDataBase();
        clearTextFields();

    }

    private void displayDataBase()
    {
        planets.clear();
        //get the ID number of the last ID
        int numRecords = manager.getLastID();
        //get the array of strings which are my records
        //using a loop
        String[] records = new String[5];
        for(int i =1; i<numRecords; i++)
        {

            // call manager.getRecordByID();
            //check if the record is blank continue
            records = manager.getRecordById(i);
            if(records == null)
            {
                continue;
            }
            //else create a formatted string of record
            else
            {
                String planetList = records[0]+" "+records[1]+" "+records[2]+" "+records[3];



                planets.add(planetList);
            }

            //when done with the loop add the list to the list view
            lvData.setItems(planets);
        }


    }

    private void clearTextFields()
    {
        //clear all 5 text fields
        txtID.clear();
        txtColor.clear();
        txtName.clear();
        txtSize.clear();
        txtFamous.clear();
    }

    private void getRecordValues()
    {
        //gets the contents of the records on the form and parse

         id = Integer.parseInt(txtID.getText());
         name  =txtName.getText();
         color = txtColor.getText();
         size =Integer.parseInt(txtSize.getText());
         famous = txtFamous.getText();



    }
}



package main.Operations;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import main.controller.Client;
import main.controller.DrawCanvas;
import main.controller.Main;

import java.util.ArrayList;

public class SubmitOperation extends Operation implements Client.ServerResponse {

    private String request;
    private ArrayList<String> inputNames;
    private String outputPath;
    private String language;

    public SubmitOperation(String request, ArrayList<String> inputNames, String outputPath, String language){
       this.request = request;
       this.inputNames = inputNames;
       this.outputPath = outputPath;
       this.language = language;
       Main.client.setServerResponse(SubmitOperation.this);
    }

    @Override
    public void setCanvas(DrawCanvas canvas) {

    }

    @Override
    public void operate() {

        Thread x = new Thread(((Runnable)()-> Main.client.sendMessage(request)));
        x.start();
        Main.loadFrame.showStage();
    }

    @Override
    public void response(String message, String response) {
        if(response.equals("200")){
            String names = "";
            if(message.contains("compile")){
                for(String name : inputNames){
                    names += " views/" + name +".viw";
                }
                this.request = "equalize " + this.language + names + " json/";
            }else if(message.contains("equalize")){
                for(String name : inputNames){
                    names += " json/"+ name +".json";
                }
                this.request = "generate " + this.language + names + " " + this.outputPath;
            }else if(message.contains("generate")){
                endLoading("Sketch Convert to Code Successfully", Alert.AlertType.INFORMATION);
                System.out.println("Sketch converted to code");
                return;
            }else{
                return;
            }
            this.operate();
        }else{
            if(message.contains("compile")){
                endLoading("Error happened while compiling", Alert.AlertType.ERROR);
            }else if(message.contains("equalize")){
                endLoading("Error happened while equalizing", Alert.AlertType.ERROR);
            }else if(message.contains("generate")){
                endLoading("Error happened while generating code", Alert.AlertType.ERROR);
            }else{
                endLoading("Unknown Error Happened", Alert.AlertType.ERROR);
            }
            return;
        }
    }

    private void endLoading(String message, Alert.AlertType type){
        Platform.runLater(()->{
            Alert alert = new Alert(type);
            alert.setHeaderText(message);
            Main.loadFrame.hideStage();
            alert.showAndWait();
        });
    }

}

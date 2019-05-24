package main.Operations;

import javafx.scene.control.Alert;
import main.controller.Client;
import main.controller.DrawCanvas;
import main.controller.Main;

public class SubmitOperation extends Operation implements Client.ServerResponse {

    private String request;
    private String inputName;
    private String outputPath;
    private String language;

    public SubmitOperation(String request, String inputName,String outputPath, String language){
       this.request = request;
       this.inputName = inputName;
       this.outputPath = outputPath;
       this.language = language;
       Main.client.setServerResponse(SubmitOperation.this);
    }

    @Override
    public void setCanvas(DrawCanvas canvas) {

    }

    @Override
    public void operate() {
        ((Runnable)()->Main.client.sendMessage(request)).run();
    }

    @Override
    public void response(String message, String response) {
        if(response.equals("200")){
            if(message.contains("compile")){
                this.request = "equalize " + this.language + " views/" + inputName +".viw json/";
            }else if(message.contains("equalize")){
                this.request = "generate " + this.language + " json/"+ inputName +".json " + this.outputPath;
            }else if(message.contains("generate")){
                System.out.println("Sketch converted to code");
                Main.loadFrame.hideStage();
                Alert error = new Alert(Alert.AlertType.INFORMATION);
                error.setHeaderText("Sketch Convert to Code Successfully");
                error.showAndWait();
                return;
            }else{
                return;
            }
            this.operate();
        }
    }

}

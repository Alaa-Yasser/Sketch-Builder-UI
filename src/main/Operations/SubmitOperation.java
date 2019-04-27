package main.Operations;

import main.sample.Client;
import main.sample.DrawCanvas;
import main.sample.Main;

import java.io.File;

public class SubmitOperation extends Operation implements Client.ServerResponse {

    private String request;
    private String inputName;
    private String outputPath;
    public SubmitOperation(String request, String inputName,String outputPath){
       this.request = request;
       this.inputName = inputName;
       this.outputPath = outputPath;
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
                this.request = "equalize views/" + inputName +".viw json/";
            }else if(message.contains("equalize")){
                this.request = "generate json/"+ inputName +".json " + this.outputPath;
            }else if(message.contains("generate")){
                System.out.println("Sketch converted to code");
                return;
            }else{
                return;
            }
            this.operate();
        }
    }

}

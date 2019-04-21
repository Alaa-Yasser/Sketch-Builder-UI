package Operations;

import sample.Client;
import sample.DrawCanvas;
import sample.Main;

public class SubmitOperation extends Operation implements Client.ServerResponse {

    private String request;
    private String outputPath;
    public SubmitOperation(String request, String outputPath){
       this.request = request;
       this.outputPath = outputPath;
       Main.client.setServerResponse(SubmitOperation.this);
       this.operate();
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
                this.request = "equalize views/ json/";
            }else if(message.contains("equalize")){
                this.request = "generate json/ " + this.outputPath;
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

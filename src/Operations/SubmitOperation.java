package Operations;

import sample.Client;
import sample.DrawCanvas;

public class SubmitOperation extends Operation implements Client.ServerResponse {

    private Client client;


    public SubmitOperation(Client client){
       this.client = client;
        client.setServerResponse(SubmitOperation.this);
    }

    @Override
    public void setCanvas(DrawCanvas canvas) {

    }

    @Override
    public void operate() {
        //try {
        ((Runnable)()->client.sendMessage("compile -p IMG_7504.jpg")).run();
                //client.join();

        //}catch (InterruptedException ir){
         //   ir.printStackTrace();
       // }

    }

    @Override
    public void response(String message, String response) {
        if(response.equals("200")){
            if(message.contains("compile")){
                client.sendMessage("equalize views/ json/");
            }else if(message.contains("equalize")){
                client.sendMessage("generate json/ code/");
            }else if(message.contains("generate")){
                System.out.println("Sketch converted to code");
            }
        }
    }
}

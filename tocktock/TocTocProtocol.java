import java.net.*;
import java.io.*;

public class TocTocProtocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int ANOTHER = 2;
    private int state = WAITING;

    private String[] clues = { "www.uol.com", "www.google.com", "www.globo.com.br", "www.youtube.com", "www.spotify.com.br" };
    private String[] answers = { "200.147.3.157",
                                 "200.131.173.231",
                                 "186.192.90.5",
                                 "142.250.78.206",
                                 "193.14.90.203"};

    public String processInput(String theInput) {
        String theOutput = null;
        if (state == WAITING) {
            theOutput = "Digite o nome a ser mapeado:";
            state = SENTKNOCKKNOCK;
        } else if (state == SENTKNOCKKNOCK) {
            boolean achou = false;
            for(int i = 0; i < clues.length; i ++){
                if (theInput.equalsIgnoreCase(clues[i])) {
                    achou = true;
                    theOutput = answers[i] + " Quer outra? (s/n)";
                    state = ANOTHER;
                    break;
                }
            }
            if(achou == false){
                theOutput = "Nome nao cadastrado! Tente novamente - nome a ser mapeado:";
                state = SENTKNOCKKNOCK;
            }
        } else if (state == ANOTHER) {
            if (theInput.equalsIgnoreCase("s")) {
                theOutput = "Digite o nome a ser mapeado:";
                state = SENTKNOCKKNOCK;
            } else {
                theOutput = "Tchau.";
                state = WAITING;
            }
        }
        return theOutput;
    }
}
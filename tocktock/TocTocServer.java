import java.net.*;
import java.io.*;

public class TocTocServer {
    public static void main(String[] args) throws IOException {
        
        if (args.length != 1) {
            System.err.println("Use: java TocTocServer <numero da porta>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try ( 
            ServerSocket serverSocket = new ServerSocket(portNumber);
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        ) {
            String inputLine, outputLine;
            
            // Initiate conversation with client
            TocTocProtocol kkp = new TocTocProtocol();
            outputLine = kkp.processInput(null);
            out.println(outputLine);

            while ((inputLine = in.readLine()) != null) {
                outputLine = kkp.processInput(inputLine);
                out.println(outputLine);
                if (outputLine.equals("Tchau."))
                    break;
            }
        } catch (IOException e) {
            System.out.println("Erro detectado enquanto tentando ouvir na porta "
                + portNumber + " ou ouvindo para uma conexao");
            System.out.println(e.getMessage());
        }
    }
}
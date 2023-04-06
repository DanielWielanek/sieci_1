import java.io.*;
import java.net.Socket;
class Chat{
	BufferedReader bufferedReader;
	BufferedWriter bufferedWriter;
	BufferedReader screenReader; 
	public Chat(Socket socket) throws IOException {
	       bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	       bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
           screenReader = new BufferedReader(new InputStreamReader(System.in));
	}
	String GetMessage() throws IOException {
		return bufferedReader.readLine();
	}
	void SendMessage(String input) throws IOException {
        bufferedWriter.write(input);
        bufferedWriter.write("\n");
        bufferedWriter.flush();
	}
	String Cin() throws IOException {
		return screenReader.readLine();
	}
	
}

public class ClientSocket {
   public static void main(String[] args) throws Exception{
       Socket socket = new Socket("194.29.175.31", 12347);
       Chat chat = new Chat(socket);
       chat.SendMessage("Hello there");
       String line = chat.GetMessage();//end info
       System.out.println(line);
       while (line!=null){
           String input = chat.Cin();
           chat.SendMessage(input);
           line = chat.GetMessage();
           System.out.println("Server:" +line);
           if(input.contains("END")) {
        	   System.exit(0);
           }
       }
   }
}

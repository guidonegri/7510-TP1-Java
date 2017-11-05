package ar.uba.fi.tdd.rulogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try{
            while (true){
                System.out.print("Enter query (0 to exit): ");
		    	input = br.readLine();
		    	if (input.equals("0")) break;
		        boolean result = kb.answer(input);
		        System.out.print("Your query result is: " + result + "\n");
            }
        }catch(IOException e){
            System.err.println("Error.");
        }
    }
}

package c230926.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Make {
	public static void main(String[] args) {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        List<String> strings = new ArrayList<>();

        try {
            String str;

            while (!(str = br.readLine()).equals("")) {
                strings.add(str);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        
        System.out.println("String html = \"\";");
        for (String string : strings) {
        	System.out.println("html +=\""+string+"\";");			
		}
	}
}

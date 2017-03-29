package pack1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataCollect {
	
	public static void main (String [] args) throws Exception {
		
		String file = "data2";
		Set<String>li = new HashSet<String>();
		File f = new File(file);
		File [] files = f.listFiles();
		
		for(int i = 0; i < files.length; i++){
			
			BufferedReader br = new BufferedReader(new FileReader(files[i]));
			String line; 
			while((line = br.readLine())!= null){
				//String [] r = line.split("\t");
				li.add(line);
			}br.close();
			
		}
		print("EmiliaTot.csv", li);
	}
	
	public static void print(String file, Set<String>li) throws Exception{
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
		
		for(String s: li){
			out.println(s);
		}
		out.close();
	}

}

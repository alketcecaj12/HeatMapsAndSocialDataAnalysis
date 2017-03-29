package pack1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class PLSMethods {
	
	public static void loadData(List<PLS>l, String file) throws Exception{
		
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line;
		
		while((line = br.readLine())!= null){
			
		String[]r = line.split("\t");	
		String h = r[0];
		int i = Integer.parseInt(r[1]);
		int c = (int)Long.parseLong(r[2]);
		long t = Long.parseLong(r[3]);
		double lat = Double.parseDouble(r[4]);
		double lon = Double.parseDouble(r[5]);
		double rad = Double.parseDouble(r[6]);
		l.add(new PLS(h, i, c, t, lat, lon, rad));
		
		}br.close();
		
	}
	public static List<PLS> pls_Users(String pls_user) throws Exception{
		String inflow = "C:\\Users\\Alket\\junocode\\FlickrAPI\\AllData";
		List<PLS>list = new ArrayList<PLS>();
		File Folder = new File(inflow);
		File files[];
		files = Folder.listFiles();

		try{
			String line = "";

			if(files.length>1)
			{
				for(int i = 0;i<files.length; i++){

					BufferedReader br = new BufferedReader(new FileReader(files[i]));
					System.out.print("reading...");
					System.out.println(files[i].getName()+", number: "+i);
					
					while((line = br.readLine())!=null){

						String [] riga = line.split("\t");
						String hash = riga[0];
						if(hash.equals(pls_user)){
							int imsi = Integer.parseInt(riga[1]);
							int cellac = Integer.parseInt(riga[2]);
							long t = Long.parseLong(riga[3]);
							double lat = Double.parseDouble(riga[4]);
							double lon = Double.parseDouble(riga[5]);
							double rad = Double.parseDouble(riga[6]);
							list.add(new PLS(hash, imsi, cellac, t, lat, lon, rad));
						}

					}
					br.close();
				}
			}
		}catch( Exception ex){
			ex.printStackTrace(); 
		}
           return list;
		//printList(list, "TotalIMSIFiles6.txt");
	}


	public static void print(String file, List<PLS>list)throws Exception{
		
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
		
		for (int i = 0; i < list.size(); i++) {
			out.print(list.get(i).h+"\t");
			out.print(list.get(i).imsi+"\t");
			out.print(list.get(i).cellac+"\t");
			out.print(list.get(i).time+"\t");
			out.print(list.get(i).lat+"\t");
			out.print(list.get(i).lon+"\t");
			out.println(list.get(i).radius);
		}
		out.close();
	}
}

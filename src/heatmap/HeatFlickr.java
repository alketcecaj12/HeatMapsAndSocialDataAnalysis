package heatmap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.gps.utils.LatLonPoint;
import org.gps.utils.LatLonUtils;

public class HeatFlickr {
	
	public static void main (String [] args) throws Exception{

		String file = "EmiliaTot.csv";	
		List<double[]>ll = new ArrayList<double[]>();
		BufferedReader br = new BufferedReader(new FileReader(new File(file)));
		String line; 
		while((line = br.readLine())!= null){
			//System.out.println(line);

			String [] r = line.split("\t");
			if(r.length== 7){
				double lat = Double.parseDouble(r[3]);
				double lon = Double.parseDouble(r[4]);
				System.out.println(lat+", "+lon);
				ll.add(new double[]{lat, lon});
			}
		}br.close();

		HeatInsta.genHeatmap2(ll, "HeatMapFlickr.html");
	}

}

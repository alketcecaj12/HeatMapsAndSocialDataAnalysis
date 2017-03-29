package heatmap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.gps.utils.LatLonPoint;
import org.gps.utils.LatLonUtils;


public class HeatInsta{
    public static void main (String [] args)throws Exception{
       String file = "E:\\countriesnodesc\\ITALY.txt";
       List<double[]> l = new ArrayList<double[]>();
       
       List<double[]> ll = new ArrayList<double[]>();
       ll.add(new double[]{44.7, 12.183333});
       
       ll.add(new double[]{44.493889, 11.342778});
       ll.add(new double[]{44.05, 12.566667});
       ll.add(new double[] {44.80, 10.333333});
       ll.add(new double[]{43.833333, 11.966667});
       ll.add(new double[]{44.433333, 10.4});
       ll.add(new double[]{44.2225, 12.040833});
       ll.add(new double[] {44.353333, 11.714167});
       ll.add(new double[]{44.417778, 12.199444});
       ll.add(new double[]{44.644722, 10.925556});
       
       BufferedReader br = new BufferedReader(new FileReader(new File(file)));
       String line; 
       while((line = br.readLine())!= null){
    	 //  System.out.println(line);
           String [] r = line.split("\t");
        
           double lat = Double.parseDouble(r[1]);
           double lon = Double.parseDouble(r[2]);
           LatLonPoint p1 = new LatLonPoint(lat, lon);
           
           for(int i = 0; i < ll.size(); i++){
        	   LatLonPoint p2 = new LatLonPoint(ll.get(i)[0], ll.get(i)[1]);
        	   double d = LatLonUtils.getHaversineDistance(p1, p2);
        	   if(d <35000){
        	   l.add(new double[] {lat, lon});
        	   break;
        	   }
           }
           
           
       }br.close();
       print("InstaCoord.csv", l);
       genHeatmap2(l, "HeatMapFlickr.html");
}
public static void genHeatmap2(List<double[]>l, String file) throws Exception{
		
		PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<title>Heatmaps</title>");
		out.println("<style>");
		out.println("html, body, #map-canvas {");
		out.println("height: 100%;");
		out.println("margin: 0;");
		out.println("padding: 0;");
		out.println("}");

		out.println("</style>");
		out.println("<script src=\"https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=visualization\"></script>");
		out.println("<script>");
		out.println("var map, pointarray, heatmap;");

		out.println("var taxiData = [");
		int counter = 0; 
		for(int i = 0; i < l.size(); i++){
			counter++;
			if(counter == l.size()){
			out.println("new google.maps.LatLng("+l.get(i)[0]+", "+l.get(i)[1]+")");
			}
			else{
				out.println("new google.maps.LatLng("+l.get(i)[0]+", "+l.get(i)[1]+"),");
			}
		}
		out.println("];");

		out.println("function initialize() {");
		out.println("var mapOptions = {");
		out.println("zoom: 10,");
		out.println("center: new google.maps.LatLng("+l.get(l.size()/2)[0]+","+l.get(l.size()/2)[1]+"),");
		out.println("mapTypeId: google.maps.MapTypeId.SATELLITE");
		out.println("};");

		out.println("map = new google.maps.Map(document.getElementById('map-canvas'),");
		out.println("mapOptions);");

		out.println("var pointArray = new google.maps.MVCArray(taxiData);");

		out.println("heatmap = new google.maps.visualization.HeatmapLayer({");
		out.println("data: pointArray");
		out.println("});");

		out.println("heatmap.setMap(map);");
		out.println("}");

		out.println("function toggleHeatmap() {");
		out.println("heatmap.setMap(heatmap.getMap() ? null : map);");
		out.println("}");

		out.println("function changeGradient() {");
		out.println("var gradient = [");
		out.println("'rgba(0, 255, 255, 0)',");
		out.println("'rgba(0, 255, 255, 1)',");
		out.println("'rgba(0, 191, 255, 1)',");
		out.println("'rgba(0, 127, 255, 1)',");
		out.println("'rgba(0, 63, 255, 1)'");
		    
		out.println("]");
		out.println("heatmap.set('gradient', heatmap.get('gradient') ? null : gradient);");
		out.println("}");

		out.println("function changeRadius() {");
		out.println("heatmap.set('radius', heatmap.get('radius') ? null : 20);");
		out.println("}");

		out.println("function changeOpacity() {");
		out.println("heatmap.set('opacity', heatmap.get('opacity') ? null : 0.2);");
		out.println("}");

		out.println("google.maps.event.addDomListener(window, 'load', initialize);");
		out.println("</script>");
		out.println("</head>");

		out.println("<body>");
		out.println("<div id=\"panel\">");
		out.println("<button onclick=\"toggleHeatmap()\">Toggle Heatmap</button>");
		out.println("<button onclick=\"changeGradient()\">Change gradient</button>");
		out.println("<button onclick=\"changeRadius()\">Change radius</button>");
		out.println("<button onclick=\"changeOpacity()\">Change opacity</button>");
		out.println("</div>");
		out.println("<div id=\"map-canvas\"></div>");
		out.println("</body>");
		out.println("</html>");
		out.close();
	}
	
     public static void print(String file, List<double[]>s)throws Exception{
       
       PrintWriter out = new PrintWriter(new FileWriter(new File(file)));
       for(int i = 0; i < s.size(); i++){
       out.println(s.get(i)[0]+","+s.get(i)[1]);
       }
       out.close();
   }
}

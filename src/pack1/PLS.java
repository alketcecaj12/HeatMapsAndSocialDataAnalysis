package pack1;

public class PLS {
	
	String h;
	int imsi;
	int cellac;
	long time;
	double lat;
	double lon;
	double radius;
	
	public PLS(String hash, int im, int cell, long t, double la, double lo, double r){
		this.h = hash;
		this.imsi = im;
		this.cellac = cell;
		this.time = t;
		this.lat = la;
		this.lon = lo;
		this.radius = r;
	}
	
	
	public String toString(){
		return h+","+imsi+","+cellac+","+time+","+lat+","+lon+","+radius;
	}

}

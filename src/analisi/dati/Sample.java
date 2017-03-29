package analisi.dati;

import java.util.ArrayList;

public class Sample {

	double media = 0;
    double devstd = 0;
    ArrayList<Double> sam;
	
    public Sample (double m, double s){
    this.media = m;
	this.devstd = s;
	sam = new ArrayList<Double>();
	}
	
    public void addGeo(String line){
	
		String[] s = line.split(";"); 
		double[] g = new double[s.length];
		
		g[0] = Double.parseDouble(s[0]);
		
		sam.add(g[0]);
		
	}
    public void addOur(String line){
		
		String[] s = line.split(";"); 
		double[] o = new double[s.length];
		o[2] = Double.parseDouble(s[2]);
	
		sam.add(o[2]);
	}
	public void addAfri(String line){
		String[] s = line.split(";"); 
		double[] a = new double[s.length];
		a[1] = Double.parseDouble(s[1]);
	
		sam.add(a[1]);
	
	}
	public double med(){
		double somma = 0;
         for(int i = 0; i < sam.size(); i++){
		  somma += sam.get(i);
		}
        //System.out.println(somma);
         media = somma/sam.size();
         
		return  media;
	}
		public double devst(){
	    double med = 0;
	    med = med();
	   double sommas = 0;
		for(int j = 0; j < sam.size(); j++){
			 sommas += Math.pow((sam.get(j)-med), 2);
		}
		//System.out.println(" sommas: "+sommas);
		devstd = Math.sqrt(sommas/(sam.size()));
		
		return devstd;	
	}
	public String toString(){
		
		return "our ha media: "+media+", dev.std "+devstd;
	}
	
}

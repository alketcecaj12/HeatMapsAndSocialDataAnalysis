package analisi.dati;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class Main2Sample {

	public static void main(String [] args) throws Exception{
		
    BufferedReader br = new BufferedReader(new FileReader(new File("data/seriedati.txt")));
		Sample geonames = new Sample(0,1);
		Sample our = new Sample(0,1);
		Sample afri = new Sample(0,1);
		String line ;

	    	while((line = br.readLine())!= null){
			line = line.trim();
	    	geonames.addGeo(line);
	    	our.addOur(line);
	    	afri.addAfri(line);
		    }
	    	br.close();
	       System.out.println("la media di geonames: "+geonames.med());
	       System.out.println("la media di afripop è "+afri.med());
	       System.out.println("la media di our: "+ our.med());
	       System.out.println("-----------------------------------------");
	       System.out.println("la dev. std di geonames: "+geonames.devst());
	       System.out.println("la dev std di afripop;"+afri.devst());
	       System.out.println("la dev std di our: "+our.devst());
           
	       Main2Sample m2s = new Main2Sample();
	       System.out.println("-----------------------------------------");
	       System.out.println("la cov di geonames/ours è: "+m2s.covarianza(geonames, our));
	       System.out.println("il coeff. di Pearson di geonames/ours è:"+m2s.pearson(geonames, our));
	       System.out.println("il coeff. di ERRE QUADRO di geonames/ours è:"+m2s.erreQuadro(geonames, our));      
	       System.out.println("-----------------------------------------");
	       System.out.println("la cov di geonames/afripop è: "+m2s.covarianza(geonames, afri));
	       System.out.println("il coeff. di Pearson di geonames/afripop è: "+m2s.pearson(geonames, afri));
	       System.out.println("il coeff. di ERRE QUADRO di geonames/afripop è: "+m2s.erreQuadro(geonames, afri));
	       System.out.println("-----------------------------------------");
	       System.out.println("la cov di afripop/ours è: "+m2s.covarianza(afri, our));
	       System.out.println("il coeff. di Pearson di afripop/ours è: "+m2s.pearson(afri, our));
	       System.out.println("il coeff. di ERRE QUADRO di afripop/ours è: "+m2s.erreQuadro(afri, our));
	       
	       
	}
	public static double covarianza(Sample a, Sample b){
		double cov = 0;
		double media_a = a.med();
		//System.out.println("media_a:"+media_a);
		double media_b = b.med();

		double somma_a = 0;
		double somma_b = 0;
		double sommatoria = 0; 
		double prod_i = 0;
		for(int i = 0; i < a.sam.size(); i++){
			somma_a = (a.sam.get(i)-media_a);
	        somma_b = (b.sam.get(i)-media_b);
		 
		prod_i += (somma_a*somma_b);
		
		}
		
		sommatoria = prod_i;
		cov = (sommatoria/a.sam.size());
		return cov;
	}
	
	public static double pearson(Sample a, Sample b){
		
		double p_coeff = 0;
		double cov = covarianza(a, b);
		double devstd_a = a.devst();
	    System.out.println("devstd_a "+devstd_a);
		double devstd_b = b.devst();
		System.out.println("devstd_b "+devstd_b);
			
		p_coeff = cov/(devstd_a*devstd_b);
		return p_coeff;
	}
	public static double erreQuadro(Sample x, Sample y){
		
		double erreq = 0; 
		
		double b1 = pearson(x,y)*(y.devst()/x.devst());
		double b0 = y.med()-(b1*x.med());
		double SSR = 0;
		double SST = 0;
		double sommassr = 0;
        for(int i = 0; i < x.sam.size(); i++){
			 sommassr += Math.pow((x.sam.get(i)-x.med()), 2); 
		}
        SSR = (b1*b1)*sommassr;
		for(int j = 0; j < y.sam.size(); j++){
		SST+=Math.pow((y.sam.get(j)-y.med()), 2);	
		}
		erreq = SSR/SST;
		return erreq;
	}
}


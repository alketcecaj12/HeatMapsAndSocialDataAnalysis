package pack1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2PLS {

	public static void main (String[] args)throws Exception{
		
		String file = "data.txt";
		List<PLS>l = new ArrayList<PLS>();
		
		PLSMethods.loadData(l, file);
		List<PLS>list = null;
		Map<String, List<PLS>>map = new HashMap<String, List<PLS>>();
		
		for (int i = 0; i < l.size(); i++) {
			
			list = new ArrayList<PLS>();
			String hash = l.get(i).h;
			
			list = PLSMethods.pls_Users(hash);
			map.put(hash, list);
			

		}
		for (String s: map.keySet()) {
			PLSMethods.print(s+".txt",map.get(s) );
		}
	}
	
}

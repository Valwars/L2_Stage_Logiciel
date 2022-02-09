package Model;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class TraitementFichier {
	
	private HashMap<String,String> hm = new HashMap<>();
	
	public TraitementFichier(String file_path) throws IOException {
		remplir(file_path);
	}
	
	public void remplir(String url) throws IOException {
		
		System.out.println("Je rempli les données");
		String line=Files.readAllLines(Paths.get(url)).get(0);
		String parts[]=line.split("   ");
	    String nbre_ions=parts[1];
	    String nbre_ions2=parts[2];
	    String flag=parts[3];
	    String num=parts[4];
	    
	    String line2=Files.readAllLines(Paths.get(url)).get(1);
	    String parts2[]=line2.split("  ");
	    String volume=parts2[1];
	    String x=parts2[2];
	    String y=parts2[3];
	    String z=parts2[4];
	    String POTIM=parts2[5];
	    
	    String line3=Files.readAllLines(Paths.get(url)).get(2);
	    String parts3[]=line3.split("  ");
	    String Temperature=parts3[1];
	    
	    String line6=Files.readAllLines(Paths.get(url)).get(5);
	    String parts4[]=line6.split("    ");
	    String E_max=parts4[1];
	    String parts5[]=parts4[2].split("  ");
	    String E_min=parts5[0];
	    String NEDOS=parts5[1];
	    String Efermi=parts4[3];
	    
	    FileReader fr=new FileReader(url);
	    BufferedReader br=new BufferedReader(fr);
	    for (String line7=Files.readAllLines(Paths.get(url)).get(6); line!=null; br.readLine()) {
	    	String parts6[]=line7.split("    ");
	    	String parts7[]=parts6[1].split("  ");
	    	String E=parts7[0];
	    	String f_up=parts7[1];
	    	String f_down=parts7[2];
	    	String g_up=parts7[3];
	    	String g_down=parts7[4];
	    	hm.put("E",E);
	    	hm.put("f_up",f_up);
	    	hm.put("f_down",f_down);
	    	hm.put("g_up",g_up);
	    	hm.put("g_down",g_down);
	    }
	    
	    hm.put("nombre d'ions",nbre_ions);
	    hm.put("nombre d'ions 2",nbre_ions2);
	    hm.put("flag",flag);
	    hm.put("numero",num);
	    hm.put("volume",volume);
	    hm.put("1x",x);
	    hm.put("1y",y);
	    hm.put("1z",z);
	    hm.put("POTIM",POTIM);
	    hm.put("Temperature",Temperature);
	    hm.put("E(max)",E_max);
	    hm.put("E(min)",E_min);
	    hm.put("NEDOS",NEDOS);
	    hm.put("Efermi",Efermi);
	    
	    System.out.println(hm);
	    
	   
	}
	
	public String getNbIons() {
		return this.hm.get("nombre d'ions");
	}
	public String getNbIons2() {
		return this.hm.get("nombre d'ions 2");
	}
	
	public String EMax() {
		return this.hm.get("E(max)");
	}
	
	public String EMin() {
		return this.hm.get("E(max)");
	}
	
}

package rdfconv;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;





public class RDFconverter {
	
	 String FILE_PATH = "C:\\Users\\Renuka Venkat\\Downloads\\spring20\\WB\\ass1\\data.csv";
	 ArrayList<String> list, actor, movie, location;
	
	public static void main(String[] args) {
		RDFconverter rdfconverter = new RDFconverter();
				rdfconverter.read();
                rdfconverter.convert();
	}

	
        private void convert(){
            
        	Model mymodel = ModelFactory.createDefaultModel();
        
        	Property movieprop = mymodel.createProperty("http://renuka.org/label/movie"); //Movie
        	Property countryprop =  mymodel.createProperty("http://renuka.org/label/country"); //Country
		
        	for(int i = 1;i<list.size();i++) 
        	{
        		Resource actorRes = mymodel.createResource("http://renuka.org/resource/"+actor.get(i));
        		actorRes.addProperty(movieprop,movie.get(i));
        		actorRes.addProperty(countryprop, location.get(i));
        	}
		//mymodel.write(System.out, "TURTLE");
		
        	try 
        	{
        		Writer wr = new FileWriter("C:\\Users\\Renuka Venkat\\Downloads\\spring20\\WB\\ass1\\assoutput.txt");
        		mymodel.write(wr,"TURTLE");
        	}
        	catch(Exception e) 
        	{
        		e.printStackTrace();
        	}
        }
        private void read() {
    		int i=0;
    		
    		list = new ArrayList<String>();
    		actor = new ArrayList<String>();
    		movie = new ArrayList<String>();
    		location = new ArrayList<String>();
    		
    		File file = new File(FILE_PATH);
    		if(file.exists())
    		{
    			try {
                                   
    				FileReader fr = new FileReader(file);
    				@SuppressWarnings("resource")
    				BufferedReader bf = new BufferedReader(fr);
    				String data = "";
    				while((data = bf.readLine())!= null) {
    						list.add(data);                                                
                                                    String datasplit[] = data.split(",");
                                                    for(int j=0;j<datasplit.length;j++)
                                                    {
                                                        if(j==0)
                                                            actor.add(datasplit[j]);
                                                        if(j==1)
                                                            movie.add(datasplit[j]);
                                                        if(j==2)
                                                            location.add(datasplit[j]);
                                                    }
                                                    i++;
    				}
    			}
    			catch( IOException io)
    			{
    				io.printStackTrace();
    			}
    			
    		}
                    else
                        System.out.print("File doesn't exist");
    	}
}

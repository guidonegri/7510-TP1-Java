package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class KnowledgeBase {

	private List<Fact> db_facts = new ArrayList<Fact>();
	private List<Rule> db_rules = new ArrayList<Rule>();
  
	static final String query_pattern = "(.*)\\((.*)\\)";
	static final String fact_pattern = "(.*)\\((.*)\\)\\.";
	static final String rule_pattern = "(.*)\\((.*)\\) :- (.*)\\.";
	
	public List<Fact> getFacts() { return db_facts; }
	
	public List<Rule> getRules() { return db_rules; }
	
	public void parseDB(String db_path){
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			file = new File (db_path);
			fr = new FileReader (file);
			br = new BufferedReader(fr);
	
			String line;
		    while( (line = br.readLine()) != null ) 
		       //System.out.println(line);
		    	if (line.matches(rule_pattern)) {
					Rule rule = parseRule(line);
					db_rules.add(rule);
					
				} else if (line.matches(fact_pattern)) {
					Fact fact = parseFact(line);
					db_facts.add(fact);	
				
				} else {
					System.out.println("Error: wrong database. Line: " + line);
				}
		  	}
	  catch(Exception e){
	     e.printStackTrace();
	     
	  } finally {
		  System.out.println(db_facts.get(0));
		  try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
   }
	
	private Rule parseRule(String line){
		return new Rule();
	}
	
	private Fact parseFact(String line){
		
		return new Fact();
	}
	
	
	public boolean answer(String query) {
		return true;
	}

}

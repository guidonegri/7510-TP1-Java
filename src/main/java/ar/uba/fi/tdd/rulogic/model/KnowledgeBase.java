package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KnowledgeBase {

	private List<Fact> db_facts = new ArrayList<Fact>();
	private List<Rule> db_rules = new ArrayList<Rule>();
  
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
		  try{                    
            if( null != fr ){   
               fr.close();     
            }                  
         }catch (Exception e2){ 
            e2.printStackTrace();
         }
      }
   }
	
	public Rule parseRule(String line){
		Pattern p = Pattern.compile(rule_pattern);
		Matcher m = p.matcher(line);
		Rule rule = null;
		
		if(m.find()){
			String name = m.group(1);
			String params_str = m.group(2);
			String[] params = params_str.split(", ");
			String facts_str = m.group(3).replace("),", ")-");
			String[] facts = facts_str.split("- ");
			rule = new Rule(name, params, facts);
		}
		return rule;
	}
	
	public Fact parseFact(String line){
		Pattern p = Pattern.compile(fact_pattern);
		Matcher m = p.matcher(line);
		Fact fact = null;
		
		if(m.find()){
			String name = m.group(1);
			String params_str = m.group(2);
			String[] params = params_str.split(", ");
			fact = new Fact(name, params);
		}
		return fact;
	}
	
	public boolean validQuery(String query){
		return query.matches(fact_pattern);
	}
	
	private boolean factQuery(Fact query){
		for (int i = 0; i < db_facts.size(); i++){
			Fact fact = db_facts.get(i);
			if (fact.compare(query)) return true;
		}
		return false;
	}
	
	private boolean ruleQuery(Fact query){
		for (int i = 0; i < db_rules.size(); i++){
			Rule rule = db_rules.get(i);
			if (! rule.compare(query)) continue;
			String[] rule_facts = rule.getFacts();
			
			for (int j = 0; j < rule_facts.length; j++){
				String fact_str = rule_facts[j];
				String replaced_fact = replaceRuleFactWithQueryParams(fact_str, rule.getParams(), query.getParams());	
				replaced_fact = replaced_fact + ".";
				Fact new_fact = parseFact(replaced_fact);
				boolean fact_result = factQuery(new_fact);
				if (fact_result == false) return false;
			}
			return true;
		}
		return false;
	}
	
	public String replaceRuleFactWithQueryParams(String rule_fact, String[] params_db, String[] params_query){
		for (int i = 0; i < params_db.length; i++){
			String aux = rule_fact.replace(params_db[i], params_query[i]);
			rule_fact = aux;
		}
		return rule_fact;
		
	}
	
	public boolean answer(String query) {
		if (! validQuery(query)) { System.out.println("Error: wrong query."); return false; }
		Fact q = parseFact(query);
		if (factQuery(q)) return true;
		else if (ruleQuery(q)) return true;
		return false;
	}

}

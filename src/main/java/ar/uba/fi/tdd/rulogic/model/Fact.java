package ar.uba.fi.tdd.rulogic.model;

import java.util.Arrays;
import java.util.List;

public class Fact {
    private String name;
    private String[] params;
    
    public Fact(String _name, String[] _params){
    	name = _name;
    	params = _params;
    }

    public String getName(){ return name; }
    
    public String[] getParams() { return params; }
    
    public boolean compare(Fact query){
    	boolean result = false;
    	if ( name.equals(query.getName()) ){
    		result = Arrays.equals(params, query.getParams());
    	}
    	return result;
    }
}

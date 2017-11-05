package ar.uba.fi.tdd.rulogic.model;

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
 
    
    /*this.compare = function (query) {
		if (this.name == query.getName()){
			let params_query = query.getParams();
			let is_same = (this.params.length == params_query.length) && this.params.every( function(element, index) { return element === params_query[index]; });
			return is_same;
		}
		return false;
    }*/
}

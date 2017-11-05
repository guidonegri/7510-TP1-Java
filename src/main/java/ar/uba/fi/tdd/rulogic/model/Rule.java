package ar.uba.fi.tdd.rulogic.model;

public class Rule {
    private String name;
    private String[] params;
    private String[] facts;
    
    public Rule(String _name, String[] _params, String[] _facts){
    	name = _name;
    	params = _params;
    	facts = _facts;
    }

    public String getName(){ return name; }
    public String[] getParams() { return params; }
    public String[] getFacts() { return facts; }
}

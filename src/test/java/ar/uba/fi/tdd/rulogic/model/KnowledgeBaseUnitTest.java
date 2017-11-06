package ar.uba.fi.tdd.rulogic.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class KnowledgeBaseUnitTest {
	
	private KnowledgeBase kb;
	
	@Before
	public void setUp() throws Exception {
		kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/numbers.db");
	}
	
	// TEST - PARSE DB
	@Test
	public void test_db_facts_length() {
		Assert.assertEquals(kb.getFacts().size(), 9);
	}
	
	@Test
	public void test_db_rules_length() {
		Assert.assertEquals(kb.getRules().size(), 1);
	}
	
	@Test
	public void test_db_facts_first_item_name() {
		Assert.assertEquals(kb.getFacts().get(0).getName(), "add");
	}
	
	@Test
	public void test_db_facts_first_item_params() {
		Assert.assertEquals(kb.getFacts().get(0).getParams()[0], "zero");
		Assert.assertEquals(kb.getFacts().get(0).getParams()[1], "zero");
		Assert.assertEquals(kb.getFacts().get(0).getParams()[2], "zero");
	}
	
	@Test
	public void test_db_rules_first_item_name() {
		Assert.assertEquals(kb.getRules().get(0).getName(), "subtract");
	}
	
	@Test
	public void test_db_rules_first_item_params() {
		Assert.assertEquals(kb.getRules().get(0).getParams()[0], "X");
		Assert.assertEquals(kb.getRules().get(0).getParams()[1], "Y");
		Assert.assertEquals(kb.getRules().get(0).getParams()[2], "Z");
	}
	
	// TEST: PARSE FACT
	@Test
	public void test_parse_fact_one_param() {
		Fact fact = kb.parseFact("varon(pepe).");
		Assert.assertEquals(fact.getName(), "varon");
		Assert.assertEquals(fact.getParams()[0], "pepe");
	}
	
	@Test
	public void test_parse_fact_two_params() {
		Fact fact = kb.parseFact("padre(pepe, jose).");
		Assert.assertEquals(fact.getName(), "padre");
		Assert.assertEquals(fact.getParams()[0], "pepe");
		Assert.assertEquals(fact.getParams()[1], "jose");
	}
	
	
	// TEST: PARSE RULE
	@Test
	public void test_parse_rule_with_two_facts() {
		Rule rule = kb.parseRule("hijo(X, Y) :- varon(X), padre(Y, X).");
		Assert.assertEquals(rule.getName(), "hijo");
		Assert.assertEquals(rule.getParams()[0], "X");
		Assert.assertEquals(rule.getParams()[1], "Y");
		Assert.assertEquals(rule.getFacts()[0], "varon(X)");
		Assert.assertEquals(rule.getFacts()[1], "padre(Y, X)");
	}
	
	@Test
	public void test_parse_rule_with_one_fact() {
		Rule rule = kb.parseRule("subtract(X, Y, Z) :- add(Y, Z, X).");
		Assert.assertEquals(rule.getName(), "subtract");
		Assert.assertEquals(rule.getParams()[0], "X");
		Assert.assertEquals(rule.getParams()[1], "Y");
		Assert.assertEquals(rule.getParams()[2], "Z");
		Assert.assertEquals(rule.getFacts()[0], "add(Y, Z, X)");
	}

	// TEST: VALID QUERY
	@Test
	public void test_valid_query_one_param() {
		Assert.assertTrue(kb.validQuery("varon(juan)."));
	}
	
	@Test
	public void test_valid_query_two_params() {
		Assert.assertTrue(kb.validQuery("padre(roberto, cecilia)."));
	}
	
	@Test
	public void test_invalid_query() {
		Assert.assertFalse(kb.validQuery("hijo()"));
	}
	
	@Test
	public void test_invalid_query2() {
		Assert.assertFalse(kb.validQuery("varon(juan) pedro."));
	}
	
	@Test
	public void test_invalid_query3() {
		Assert.assertFalse(kb.validQuery("varon"));
	}

}

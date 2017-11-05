package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		
	}

	// QUERY: FACTS
	@Test
	public void test_valid_query_varon() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertTrue(kb.answer("varon(juan)."));
	}
	
	@Test
	public void test_valid_query_mujer() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertTrue(kb.answer("mujer(cecilia)."));
	}
	
	@Test
	public void test_valid_query_two_params() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertTrue(kb.answer("padre(juan, pepe)."));
	}
	
	@Test
	public void test_invalid_fact_query_format() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertFalse(kb.answer("varon (juan) ."));
	}
	
	@Test
	public void test_no_match_query_one_param() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertFalse(kb.answer("varon(maria)."));
	}
	
	
	@Test
	public void test_no_match_query_two_params() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertFalse(kb.answer("hermano(alfredo, roberto)."));
	}	

	// QUERY: RULES
	@Test
	public void test_valid_query_hijo() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertTrue(kb.answer("hijo(pepe, juan)."));
	}
	
	@Test
	public void test_no_match_query_hija() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertFalse(kb.answer("hija(maria, roberto)."));
	}
	
	@Test
	public void test_invalid_rule_query_format() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertFalse(kb.answer("hijo ( pepe, juan "));
	}
}

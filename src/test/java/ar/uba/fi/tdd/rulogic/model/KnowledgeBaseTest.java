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

	@Test
	public void test_valid_query_varon() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertTrue(this.knowledgeBase.answer("varon(juan)."));
	}
	
	@Test
	public void test_valid_query_mujer() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertTrue(this.knowledgeBase.answer("mujer(cecilia)."));
	}
	
	@Test
	public void test_valid_query_two_params() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertTrue(this.knowledgeBase.answer("padre(juan, pepe)."));
	}
	
	@Test
	public void test_invalid_query_format() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertFalse(this.knowledgeBase.answer("varon (juan) ."));
	}
	
	@Test
	public void test_no_match_query_one_param() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertFalse(this.knowledgeBase.answer("varon(maria)."));
	}
	
	
	@Test
	public void test_no_match_query_two_params() {
		KnowledgeBase kb = new KnowledgeBase();
		kb.parseDB("src/main/resources/rules.db");
		Assert.assertFalse(this.knowledgeBase.answer("hermano(alfredo, roberto)."));
	}	
	
}

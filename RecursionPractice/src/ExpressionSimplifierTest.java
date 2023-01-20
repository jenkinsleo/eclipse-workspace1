import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

public class ExpressionSimplifierTest extends TestCase  {

	public void test() {
		
		ExpressionSimplifier es = new ExpressionSimplifier();
		
		assertEquals(5.1, es.simplifyExpression("3.1+2"));
		assertEquals(12.0, es.simplifyExpression("3+2+7"));
		assertEquals(17.0, es.simplifyExpression("3+2*7"));
		assertEquals(5.0, es.simplifyExpression("(3+2)"));
		assertEquals(45.0, es.simplifyExpression("(3+2)*(2+7)"));
		assertEquals(20.5, es.simplifyExpression("3.2+17.3"));

	}

}

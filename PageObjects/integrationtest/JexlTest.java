import static org.junit.Assert.*;

import java.util.HashMap;

import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.UnifiedJEXL;
import org.apache.commons.jexl2.UnifiedJEXL.Expression;
import org.junit.Test;


public class JexlTest {
	private Expression expr;
	private JexlContext ctx;

	@Test
	public void jexlTesting(){
		JexlEngine jexengine = new JexlEngine();
		ctx = new MapContext();
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("key","value");
		ctx.set("someBean",map);
		
		UnifiedJEXL jexl = new UnifiedJEXL(jexengine );
		expr = jexl.parse("${someBean['key']} extra text");
		assertEquals(expr.evaluate(ctx),"value extra text");
	}
}

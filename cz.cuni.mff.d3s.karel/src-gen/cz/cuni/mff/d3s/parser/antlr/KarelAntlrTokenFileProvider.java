/*
* generated by Xtext
*/
package cz.cuni.mff.d3s.parser.antlr;

import java.io.InputStream;
import org.eclipse.xtext.parser.antlr.IAntlrTokenFileProvider;

public class KarelAntlrTokenFileProvider implements IAntlrTokenFileProvider {
	
	public InputStream getAntlrTokenFile() {
		ClassLoader classLoader = getClass().getClassLoader();
    	return classLoader.getResourceAsStream("cz/cuni/mff/d3s/parser/antlr/internal/InternalKarel.tokens");
	}
}
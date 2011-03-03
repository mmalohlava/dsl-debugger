/**
 * 
 */
package cz.cuni.mff.d3s.dsl.debugger.traceability;

import org.eclipse.core.resources.IResource;

/**
 * Describe mapping 1:1.
 * 
 * @author Michal Malohlava
 *
 */
public class SimpleMapping {
	
	IResource source;	
	int sourceLineNumber;
	
	int targetLineNumber;
	int targetStartChar;
	int targetEndChar;
	IResource target;
	
	public int getSourceLineNumber() {
		return sourceLineNumber;
	}
	public int getTargetLineNumber() {
		return targetLineNumber;
	}
	public int getTargetStartChar() {
		return targetStartChar;
	}
	public int getTargetEndChar() {
		return targetEndChar;
	}
	public IResource getSource() {
		return source;
	}
	public IResource getTarget() {
		return target;
	}
}

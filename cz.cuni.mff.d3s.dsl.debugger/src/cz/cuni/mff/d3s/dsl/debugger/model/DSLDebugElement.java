/**
 * 
 */
package cz.cuni.mff.d3s.dsl.debugger.model;

import org.eclipse.debug.core.model.DebugElement;
import org.eclipse.debug.core.model.IDebugTarget;

import cz.cuni.mff.d3s.dsl.debugger.IDSLDebuggerConstants;

/**
 * @author Michal Malohlava
 *
 */
public class DSLDebugElement extends DebugElement {

	public DSLDebugElement(IDebugTarget target) {
		super(target);
	}

	@Override
	public String getModelIdentifier() {		
		return IDSLDebuggerConstants.DSL_DEBUG_MODEL_ID;
	}
	
	public DSLDebugTarget getDSLDebugTarget() {
		return (DSLDebugTarget) getDebugTarget();
	}
}

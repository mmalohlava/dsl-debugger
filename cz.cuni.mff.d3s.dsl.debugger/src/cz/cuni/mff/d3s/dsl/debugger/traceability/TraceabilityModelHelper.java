/**
 * 
 */
package cz.cuni.mff.d3s.dsl.debugger.traceability;

import org.eclipse.acceleo.traceability.GeneratedFile;
import org.eclipse.acceleo.traceability.GeneratedText;
import org.eclipse.acceleo.traceability.InputElement;
import org.eclipse.acceleo.traceability.ModelFile;
import org.eclipse.acceleo.traceability.TraceabilityModel;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parsetree.AbstractNode;
import org.eclipse.xtext.parsetree.CompositeNode;
import org.eclipse.xtext.parsetree.NodeAdapter;
import org.eclipse.xtext.parsetree.NodeUtil;

/**
 * TODO move this class to a separate plugin.
 * 
 * @author Michal Malohlava
 *
 */
public class TraceabilityModelHelper {
	
	public static SimpleMapping getMappingToJava(IResource source, int lineNumber, TraceabilityModel traceModel, EObject dslProgramModel) {
		SimpleMapping sMap = new SimpleMapping();
		sMap.sourceLineNumber = lineNumber;
		sMap.source = source;
		
		if (fillMappingToJava(sMap, traceModel, dslProgramModel)) {
			return sMap;
		} else {
			return null;
		}
	}
	
	public static boolean fillMappingToJava(SimpleMapping sMap, TraceabilityModel traceModel, EObject dslProgramModel) {		
		
		EObject eo = getElementForLine(sMap.sourceLineNumber, dslProgramModel);
		InputElement associatedInputElement = getAssociatedInputElement(eo, traceModel);
				
		if (associatedInputElement!=null) {
			for(GeneratedFile gFile : traceModel.getGeneratedFiles()) {
				for (GeneratedText gText : gFile.getGeneratedRegions()) {
					if (gText.getSourceElement() == associatedInputElement) {
						sMap.targetStartChar = gText.getStartOffset();
						sMap.targetEndChar = gText.getEndOffset();
						sMap.target = ResourcesPlugin.getWorkspace().getRoot().findMember(gText.getOutputFile().getPath().replaceFirst(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString(), ""));
						
						return true;
					}
				}
			}
		}
		
		return false;
	}
		
	public static InputElement getAssociatedInputElement(EObject eo, TraceabilityModel traceModel) {
		CompositeNode cNode1 = getCompositeNodeForEObject(eo);
		
		if (cNode1 != null) {
			for(ModelFile modelFile : traceModel.getModelFiles()) {
				// FIXME check for the same model file
				for(InputElement ie : modelFile.getInputElements()) {
					EObject modelElement = ie.getModelElement();
					CompositeNode cNode2 = getCompositeNodeForEObject(modelElement);
					
					if (equals(cNode1,cNode2)) {
						return ie;
					}
				}
			}
		}
		
		return null;
	}
	
	private static boolean equals(CompositeNode cNode1, CompositeNode cNode2) {
		return cNode1 != null && cNode2 != null
				&& cNode1.getOffset() == cNode2.getOffset()
				&& cNode1.getTotalOffset() == cNode2.getTotalOffset()
				&& cNode1.getTotalLength() == cNode2.getTotalLength();	
	}

	public static EObject getElementForLine(int lineNumber, EObject dslProgramModel) {
		
		AbstractNode aNode = getCompositeNodeForEObject(dslProgramModel);
		
		return getElementForAbstractNode(aNode, lineNumber);
	}
	
	protected static EObject getElementForAbstractNode(AbstractNode aNode, int lineNumber) {
		if (lineNumber == aNode.getLine() 
				|| 
			(lineNumber > aNode.getLine() && lineNumber <= aNode.endLine())
			) {
				if (aNode instanceof CompositeNode) {
					for (AbstractNode childANode : ((CompositeNode) aNode).getChildren()) {
						EObject result = getElementForAbstractNode(childANode, lineNumber);
						if (result != null) {
							return result;
						}
					}
				} else {
					return NodeUtil.getNearestSemanticObject(aNode);
				}
			
		} else {
			return null;			
		}
		
		return null;
	}
	
	public static CompositeNode getCompositeNodeForEObject(EObject eo) {
		NodeAdapter nodeAdapter = NodeUtil.getNodeAdapter(eo);
		if (nodeAdapter!=null) {
			return nodeAdapter.getParserNode();			
		}
		
		return null;
	}
}

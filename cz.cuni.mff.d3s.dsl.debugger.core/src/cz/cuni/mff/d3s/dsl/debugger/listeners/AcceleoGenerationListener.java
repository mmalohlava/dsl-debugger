package cz.cuni.mff.d3s.dsl.debugger.listeners;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.acceleo.engine.event.AcceleoTextGenerationEvent;
import org.eclipse.acceleo.engine.event.IAcceleoTextGenerationListener;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;

public class AcceleoGenerationListener implements IAcceleoTextGenerationListener {

	@Override
	public void textGenerated(AcceleoTextGenerationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void filePathComputed(AcceleoTextGenerationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fileGenerated(AcceleoTextGenerationEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void generationEnd(AcceleoTextGenerationEvent event) {
		EObject eo = event.getTraceabilityInformation();
		try {
			Resource r = new XMIResourceImpl(URI.createPlatformResourceURI("test-karel/trace-model.xmi", false));
			
			Map options = new HashMap();
			options.put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
//			options.put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
			options.put(XMLResource.OPTION_SKIP_ESCAPE_URI, Boolean.FALSE);
//			options.put(XMLResource.OPTION_XML_MAP, xmlMap);
			r.getContents().add(eo);			
			r.save(options);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}			
	}

	@Override
	public boolean listensToGenerationEnd() {
		return true;
	}

}

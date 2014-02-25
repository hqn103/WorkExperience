package mil.army.inscom;

import mil.army.inscom.dpf.ucd.model.ArtifactWrapper;
import mil.army.inscom.dpf.ucd.model.Attachment;
import mil.army.inscom.dpf.ucd.model.ScopedTermBuilders;
import mil.army.inscom.dpf.ucd.resolver.AMContext;
import mil.army.inscom.ucd.model.*;
import mil.army.inscom.ucd.model.UnstructuredText.Field;
import mil.army.inscom.ucd.model.objects.UCDNode;
import mil.army.inscom.ucd.model.objects.UCDObject;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ModelGenerate {
    private static final Logger logger = LoggerFactory.getLogger(ModelGenerate.class);
    private Map<String, String> uniqueTerms = new HashMap<String, String>();
    
    @Test
    public void test() throws Exception {
    	//logger.info("Hello ");
    	
    	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder builder = null;
    	try {
    	    builder = builderFactory.newDocumentBuilder();
    	} catch (ParserConfigurationException e) {
    	    e.printStackTrace();  
    	}

    	try {
    	    Document document = builder.parse(new FileInputStream("/home/hnguyen/reddisk/standard-models/src/test/resources/data/cidne/raw/cidnehumintahr0.xml"));
    	    Node root = document.getFirstChild();
    	    String rootName = uniqueName(root.getNodeName(), root.getNodeName());
//    	    printTerm(rootName);
    	    
    	    NodeList childs = root.getChildNodes();
    	    if (childs.getLength() > 0) {
    	    	for (int i=0; i<childs.getLength(); i++) {
    	    		if (childs.item(i).getNodeType() == 1) {
    	    			String term = uniqueName(childs.item(i).getNodeName(), rootName);
    	    			printArtifact(term, "/" + root.getNodeName() + "/" + childs.item(i).getNodeName());
//    	    			printTerm(term);
//    	    			printStatement(rootName, term);
        	    		
        	    		if (childs.item(i).getChildNodes().getLength() > 0) {
        	    			NodeList grandChilds = childs.item(i).getChildNodes();
        	    			for (int j=0; j<grandChilds.getLength(); j++) {
        	    				if (grandChilds.item(j).getNodeType() == 1) {
        	    					String innerTerm = uniqueName(grandChilds.item(j).getNodeName(), term);
        	    					printArtifact(innerTerm, "/" + root.getNodeName() + "/" + childs.item(i).getNodeName() + "/" + grandChilds.item(j).getNodeName());
//        	    					printTerm(innerTerm);
//        	    					printStatement(term, innerTerm);        	    		
        	    				}
        	    			}
        	    		}
    	    		}
    	    	}
    	    }
    	} catch (SAXException e) {
    	    e.printStackTrace();
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    }
    
    private void printTerm(String term) {
    	System.out.println("<term handle=\"" + term + "\"><concept label=\"" + term + "\" model=\"CIDNE\" /></term>");
    }
    
    private void printStatement(String subject, String object) {
    	System.out.println("<statement handle=\"has-" + object + "\"><subject handle=\"" + subject + "\" /><predicate label=\"hasValue\" model=\"urn:mil.army.inscom.ucd\" /><object handle=\"" + object + "\" multiplicity=\"each\" /></statement>");
    }
    
    private void printArtifact(String term, String xpath) {
    	System.out.println("<term handle=\"" + term + "\"><primitive eval=\"xpath\" type=\"string\" required=\"false\">" + xpath + "</primitive></term>");
    }
    
    private String uniqueName(String term, String parentTerm) {
    	String newName = term;
    	if (uniqueTerms.containsKey(term)) {
    		uniqueTerms.put(parentTerm + term, parentTerm + term);
    		newName = parentTerm + term;
    	} else {
    		uniqueTerms.put(term, term);
    	}
//    	System.out.println(newName + "\n");
    	return newName;
    }
   
    
    class Statement {
    	protected String term;
    	protected String object;
    	protected String predicate = "hasValue";
    }
	
}
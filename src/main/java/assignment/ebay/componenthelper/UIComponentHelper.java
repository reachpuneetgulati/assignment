package assignment.ebay.componenthelper;

import java.io.File;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/*
 * 
 * base class for activities has been declared abstract so that it is not instantiated and only extended
 * It prepares the UI component locators from a xml and provides it to the functions in the respective 
 * activities
 *  
 */

public abstract class UIComponentHelper {

	private String mTitle;
	private HashMap<String,String> mUIMap;
	
	private Logger mLog = LogManager.getLogger(this.getClass().getName());
	
	public UIComponentHelper(String className) {
		mLog.debug("Instantiating activity:"+className);
		mTitle= null;
		mUIMap = new HashMap<String, String>();
		initUIMap(className);
	}
	
	//populates the UI map for the activity of the name className
	private void initUIMap(String className) {
		try {
			File inputFile = new File("./resources/ComponentLocators.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
			doc.getDocumentElement().normalize();
			NodeList list = doc.getElementsByTagName("activity");
			
			if(list.getLength() == 0)
				throw new Exception("No UI Mapping present for "+className);
			
			for(int i = 0; i < list.getLength(); i++) {
				if(list.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element e = (Element)list.item(i);
					if(e.hasAttribute("name") && e.getAttribute("name").equals(className)) {
						NodeList el = e.getChildNodes();
						for(int j =0; j<el.getLength();j++) {
							if(el.item(j).getNodeType() == Node.ELEMENT_NODE) {
								Element en = (Element)el.item(j);
								if(en.getTagName().equals("title")) {
									mTitle = en.getTextContent();
									continue;
								}
								mUIMap.put(en.getTagName(), en.getTextContent());
							}
						}
					}
				}
			}
		}catch (Exception e) {
			
		}
	}
	
	//returns the title of the activity
	public String getTitle() {
		return mTitle;
	}
	
	//returns the element to operate on
	public String getElementXPath(String uiKey) {
		return mUIMap.containsKey(uiKey)? mUIMap.get(uiKey):null;
	}
	
	public void logMsg(String msg) {
		mLog.debug(msg);
	}
}

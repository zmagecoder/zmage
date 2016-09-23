package com.mage.platform.framework.widget.utils;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 挂件xml参数解析工具
 * @author kingapex
 * 2010-2-4下午04:11:25
 */
public class WidgetXmlUtil {

	private  WidgetXmlUtil(){}
	
	/**
	 * 返回的Map规则
	 * key为挂件id
	 * value为挂件参数Map
	 * @param path
	 * @return
	 */
	public static Map<String, Map<String, Map<String,String>>> parse(String path){
		try {
		    Document document = paseParamDoc(path);
		    return parsedoc(document);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("load ["+path +"] widget file error" );
		} 	 
	}
	
	private static Map<String,Map<String,Map<String,String>>> parsedoc(Document doc){
		Map<String,Map<String,Map<String,String>>>  params= new LinkedHashMap<String,Map<String,Map<String,String>>>();
		Node widgets = doc.getFirstChild();
		if(widgets==null) throw new RuntimeException("widget xml error[page node is null]");
		NodeList nodeList  = widgets.getChildNodes(); //子元素为page结点
		for(int i=0;i<nodeList.getLength();i++){
			Node page= nodeList.item(i);
			if(page.getNodeType() == Node.ELEMENT_NODE){
				Map<String, Map<String, String>>  widgetParams = parse(page);
				params.put( ((Element)page).getAttribute("id")  , widgetParams);
			}
		}
		return params;
	}
	
	/**
	 * 将一个page结点转 为Map
	 * @param document
	 * @return
	 */
	private static Map<String, Map<String, String>> parse(Node page){
		
		Map<String, Map<String, String>> params = new LinkedHashMap<String, Map<String,String>>();
		if(page==null) throw new RuntimeException("widget xml error[page node is null]");
		NodeList nodeList  = page.getChildNodes();
		for(int i=0;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element widgetEl = (Element)node;
				String main = widgetEl.getAttribute("main");

				Map<String,String> param = parae(widgetEl);
				if("yes".equals(main)){ //如果是主挂件，标识id为main
					params.put("main", param);
				}else	{	
					params.put(widgetEl.getAttribute("id"), param);
				}
				
			}
		}
		return params;
	}
	
	
	/**
	 * 将一个参数 widget 结点元素转为参数Map
	 * @param element
	 * @return
	 */
	private static  Map<String,String> parae(Element element){
		
		NodeList nodeList  = element.getChildNodes();
		Map<String, String> param = new LinkedHashMap<String, String>();
		param.put("widgetid", element.getAttribute("id"));
		
		for(int i=0;i<nodeList.getLength();i++){
			Node node = nodeList.item(i);
			
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element attr = (Element)node;
				String name = attr.getNodeName();
				String value = attr.getTextContent();
				param.put(name, value);
			}
		}
		return param;
	}
	
	/**
	 * 给定一个挂件xml地址，解析为document
	 * @param path
	 * @return
	 */
	private static Document paseParamDoc(String path){
		try {
		    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
		    Document document = builder.parse(in);
		    return document;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("load ["+path +"] widget file error" );
		} 	 
	}
	
}

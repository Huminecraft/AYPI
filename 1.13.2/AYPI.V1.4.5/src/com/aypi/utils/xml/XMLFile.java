package com.aypi.utils.xml;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.bukkit.entity.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aypi.Aypi;

public class XMLFile {
	
	private File file;
	private ArrayList<MCBalise> mcBalises;
	
	public XMLFile(File file) {
		this.file = file;
		this.mcBalises = new ArrayList<MCBalise>();
	}
	
	public void load() {

		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		try {
			final DocumentBuilder builder = factory.newDocumentBuilder();
			final Document document = builder.parse(file);
			
			//Affichage du prologue
		    System.out.println("*************PROLOGUE************");
		    System.out.println("name : "+file.getName());
		    System.out.println("version : " + document.getXmlVersion());
		    System.out.println("encode : " + document.getXmlEncoding());		
	        System.out.println("standalone : " + document.getXmlStandalone());
	        
	        final Element racine = document.getDocumentElement(); 
	        
	        if (racine.getNodeName().equalsIgnoreCase("server")) {
	        	
	        	final NodeList racineNoeuds = racine.getChildNodes();
	        	
	        	mcBalises.addAll(createBalise(racineNoeuds));
	        	
	        } else {
	        	System.out.println("ERROR: La racine de votre document doit �tre une balise <server></server>");
	        }
			
		} catch(Exception e) {e.printStackTrace();}
		
		printBalise(mcBalises);
		
	}
	
	private void printBalise(ArrayList<MCBalise> mcb) {
		for (MCBalise m : mcb) {
			if (m.haveChildren()) {
				System.out.println(m.getName()+"\\/");
				printBalise(m.getChildrens());
			} else {
				System.out.println("- "+m.getName()+" -");
			}
			
		}
	}
	
	private ArrayList<MCBalise> createBalise(NodeList node) {
		
		ArrayList<MCBalise> mcBalises = new ArrayList<MCBalise>();
		
		for (int i = 0 ; i < node.getLength() ; i++) {
    		
			if (node.item(i).getNodeType() == Node.ELEMENT_NODE) {
				for (MCBalise mc : Aypi.getXMLFileManager().getMCBalises()) {
					if (node.item(i).getNodeName().equalsIgnoreCase(mc.getName())) {
						MCBalise mb = mc;
						mb.setUpAttributes(node.item(i).getAttributes());
						mb.setContent(node.item(i).getTextContent());
						for (MCBalise children : createBalise(node.item(i).getChildNodes())) {
							mb.addChildren(children);
						}
						this.mcBalises.add(mb);
						break;
					}
				}
				
				
			}
    		
    	}
		
		return mcBalises;

	}
	
	public void destroy() {
		while (mcBalises.size() != 0) {
			mcBalises.remove(0);
		}
		Aypi.getXMLFileManager().removeXMLFile(this);
	}
	
	public void addMCBalise(MCBalise mcBalise) {
		mcBalises.add(mcBalise);
	}
	
	public ArrayList<MCBalise> getMCBalises() {
		return mcBalises;
	}
	
	public File getFile() {
		return file;
	}

	public void executesAllBalises(String name, Player player) {
		
		for (MCBalise mcb : mcBalises) {
			System.out.println(mcb.getName());
			if (mcb.getName().equalsIgnoreCase(name)) {
				mcb.execute(player);
			}
			
			if (mcb.haveChildren()) {
				executesAllBalises(mcb, name, player);
			}
			
		}
		
	}
	
	public void executesAllBalises(MCBalise mcBalise, String name, Player player) {
		
		for (MCBalise mcb : mcBalise.getChildrens()) {
			
			if (mcb.getName().equalsIgnoreCase(name)) {
				mcb.execute(player);
			}
			
			if (mcb.haveChildren()) {
				executesAllBalises(mcb, name, player);
			}
			
		}
		
	}
	
}

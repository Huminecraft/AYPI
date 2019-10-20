package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;

public class IDBalise extends MCBalise
{
	public static final String NAME = "ID";
	
	private int ID;
	
	public IDBalise()
	{
		super(NAME);
	}

	@Override
	public void setUpCustomAttributes(NamedNodeMap namedNodeMap)
	{		
	}

	@Override
	public void customExecute(Player player, XMLFile xmlFile)
	{
		ID = Integer.parseInt(xmlFile.getScriptManager().compile(getContent(), 0));		
	}
	
	@Override
	public MCBalise getInstance()
	{
		return new IDBalise();
	}
	
	public int getID()
	{
		return ID;
	}
}

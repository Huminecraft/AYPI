package com.aypi.utils.xml.balises;

import org.bukkit.entity.Player;
import org.w3c.dom.NamedNodeMap;

import com.aypi.utils.xml.MCBalise;
import com.aypi.utils.xml.XMLFile;

public class ItemBalise extends MCBalise  {

	public static final String NAME = "item";
	
	private String item;
	
	public ItemBalise()
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
		item = getString(xmlFile.getScriptManager().compile(getContent(), 0), xmlFile);		
	}

	@Override
	public MCBalise getInstance() {
		return new ItemBalise();
	}
	
	public String getItem()
	{
		return item;
	}
}

package com.otaku.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Vector;

public class VectorTools {

	
	public static Vector sortVectorStringOfInteger(Vector v)throws Exception 
	{
		Vector v1 = new Vector();
		ListIterator li1 = v1.listIterator();
		if(v!=null) {
			ListIterator li = v.listIterator();
			while(li.hasNext()) {
				String tmp = (String)li.next();
				//System.out.println("tmpo="+tmp);
				li1.add(Integer.valueOf(tmp));
			}
		}
		Collections.sort(v1);

		return v1;
	}
	
    public static Vector removeDuplicateFromVector(Vector v)throws Exception 
	{
		Vector v1 = new Vector(v);
		ListIterator li = v.listIterator();
		while(li.hasNext())
		{
			String baris = (String)li.next();
			//System.out.println("v "+baris);
		}	
	

		v1 = new Vector(new LinkedHashSet(v));
		ListIterator li1 = v1.listIterator();
		while(li1.hasNext())
		{
			String baris = (String)li1.next();
			//System.out.println("v1 "+baris);
		}	
		return v1;
	}
    
    
}

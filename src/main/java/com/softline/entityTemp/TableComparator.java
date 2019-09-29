package com.softline.entityTemp;

import java.util.Comparator;


public class TableComparator implements Comparator {


	public int compare(Object obj1,Object obj2)
	{
		TableCell cell1=(TableCell) obj1;
		TableCell cell2=(TableCell) obj2;
		if(cell1.getStartrow()<cell2.getStartrow())
			return -1;
		if(cell1.getStartrow()>cell2.getStartrow())
			return 1;
		if(cell1.getStartcol()<cell2.getStartcol())
			return -1;
		if(cell1.getStartcol()>cell2.getStartcol())
			return 1;
		else
			return 0;
	}
}

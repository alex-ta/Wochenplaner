package com.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.data.Entry;

public class EntryDao extends Dao<Entry>{

	@Override
	Class<Entry> getDaoClass() {		
		return Entry.class;
	}
	
	
	public Date updateEntries() {
		Calendar current = Calendar.getInstance();
		current.add(Calendar.MONTH, -6); 	
//		current.set(Calendar.DAY_OF_MONTH, 1);
        Calendar next = Calendar.getInstance();
        next.add(Calendar.MONTH, +6); 	
//		next.set(Calendar.DAY_OF_MONTH, next.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		Calendar del = Calendar.getInstance();
		del.add(Calendar.MONTH, -18); 	
		del.set(Calendar.DAY_OF_MONTH, 1);
		
        this.deleteByDate(del.getTime());
        List<Entry> entries = new LinkedList<>();
        // this year
        while (current.before(next)) {
        	Entry e = new Entry();
        	e.setId(current.getTime());
        	entries.add(e);
            current.add(Calendar.DATE, 1);
        }
        
        System.out.println("Saving : "+entries.size());
        List<Entry> entriesTable = this.getAll();
        entries.removeAll(entriesTable);
    	//entries.removeAll(entriesTable);
		System.out.println("Saving real: "+entries.size());
	
        
        this.batchSave(entries);
        return next.getTime();
	}
	
}

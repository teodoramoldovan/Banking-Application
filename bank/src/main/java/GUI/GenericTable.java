package GUI;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class GenericTable {
	
	public GenericTable() {};
	
	public <T> DefaultTableModel createTable(ArrayList<T> objects) {
		ArrayList<String> coloane=new ArrayList<String>();
		if(objects.size()>0) {
			for(Field f:objects.get(0).getClass().getDeclaredFields()) {
				coloane.add(f.getName());				
			}
		}
		DefaultTableModel table=new DefaultTableModel(coloane.toArray(),0);
		for(Object object:objects) {
			Vector<String> valori = new Vector<String>();
			for(Field f:object.getClass().getDeclaredFields()) {
					f.setAccessible(true);				
					try {
						Object val=f.get(object);
						valori.add(val.toString());
						//System.out.println(valori);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}				
			}
			table.addRow(valori);
		}
		return table;
	}
}


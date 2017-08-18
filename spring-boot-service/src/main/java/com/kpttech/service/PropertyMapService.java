package com.kpttech.service;

import java.util.HashMap;
import java.util.Map;

import com.github.abel533.mapperhelper.EntityHelper;

public class PropertyMapService {

	protected EntityHelper.EntityTable table;
    //属性和列对应
    protected Map<String, EntityHelper.EntityColumn> propertyMap;
    
	public PropertyMapService(Class<?> entityClass) {
		EntityHelper.EntityTable table = EntityHelper.getEntityTable(entityClass);
        propertyMap = new HashMap<String, EntityHelper.EntityColumn>(table.getEntityClassColumns().size());
        for (EntityHelper.EntityColumn column : table.getEntityClassColumns()) {
            propertyMap.put(column.getProperty(), column);
        }
	}
	
	public String getColumn(String property) {
        if (propertyMap.containsKey(property)) {
            return propertyMap.get(property).getColumn();
        } else {
            return null;
        }
    }
}

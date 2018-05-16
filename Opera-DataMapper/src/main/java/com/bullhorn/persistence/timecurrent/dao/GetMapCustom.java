package com.bullhorn.persistence.timecurrent.dao;

import java.util.List;

import com.bullhorn.persistence.timecurrent.model.MapKV;

public interface GetMapCustom {

	List<MapKV> getMapDetail(String mapName);
	
}

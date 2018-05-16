package com.bullhorn.json.model;

import java.util.List;

import com.bullhorn.persistence.timecurrent.model.MapKV;

public class TargetMappings {
	public List<MapKV> Mappings;
	public TargetMappings(List<MapKV> Mappings) {
		this.Mappings = Mappings;
	}
}

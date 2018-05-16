package com.bullhorn.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bullhorn.json.model.AssignmentRequest;
import com.bullhorn.json.model.SourceAssignments;
import com.bullhorn.json.model.TargetAssignments;
import com.bullhorn.persistence.timecurrent.dao.GetMap;
import com.bullhorn.persistence.timecurrent.model.MapKV;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Component
public class DataMapper {

	private static final Logger LOGGER = LoggerFactory.getLogger(DataMapper.class);

	@Autowired
	GetMap dao;

	public TargetAssignments ProcessMapping(SourceAssignments srcAsses) {
		LOGGER.info("Processing DataMapping for {}",srcAsses.toString());
		LOGGER.info("Recieved : {}", srcAsses.getData().toString());

		// Get mapping from database
		List<MapKV> map = dao.getMapDetail(srcAsses.getMapName());
		LOGGER.debug("Map : {}", map.size());
		map.forEach((x) -> {
			LOGGER.debug("Fetched from DB - {}", x.getAttribute() + " SJ " + x.getExpression());
		});

		// Get assignment list from the recieved payload
		List<Map<String, Object>> assignmentList = DataMapper.GetAssignmentList(srcAsses.getData());
		
		// Process the mapping (Source to Target)
		Gson gson = new Gson();
		List<AssignmentRequest> processedAsses = new ArrayList<AssignmentRequest>();
		assignmentList.forEach((al) -> {
			AssignmentRequest req = gson.fromJson(DataMapper.MapAssignment(map, al), AssignmentRequest.class);
			processedAsses.add(req);
		});

		return new TargetAssignments(processedAsses);
	}

	public static List<Map<String, Object>> GetAssignmentList(JsonObject[] json) {

		List<Map<String, Object>> assignmentList = new ArrayList<Map<String, Object>>();

/*		JsonParser parser = new JsonParser();
		JsonElement tree = parser.parse(jsonStr);
		JsonArray assArr = tree.getAsJsonArray();*/

		LOGGER.info("No. of Assignments: " + json.length);
		
		for(JsonObject o:json) {
			Set<String> keys = o.keySet();
			Map<String, Object> kvMap = new HashMap<String, Object>();
			keys.forEach((k) -> {
				LOGGER.info("[K]: " + k + " || [V]: " + o.get(k).toString());
				kvMap.put(k, o.get(k));
			});
			assignmentList.add(kvMap);			
		}

/*		assArr.forEach((element) -> {
			JsonObject o = element.getAsJsonObject();
			Set<String> keys = o.keySet();
			Map<String, Object> kvMap = new HashMap<String, Object>();
			keys.forEach((k) -> {
				LOGGER.info("[K]: " + k + " || [V]: " + o.get(k).toString());
				kvMap.put(k, o.get(k));
			});
			assignmentList.add(kvMap);
		});*/

		return assignmentList;
	}

	public static String MapAssignment(List<MapKV> map, Map<String, Object> al) {

		// Initiate the JavaScript engine
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

		for (Entry<String, Object> entry : al.entrySet()) {
			engine.put(entry.getKey(), entry.getValue().toString().replace("\"", ""));
		}

		Map<String, String> outMap = new LinkedHashMap<String, String>();

		map.forEach((m) -> {
			Object o = null;
			try {
				o = engine.eval(m.getExpression());
				String val = o.toString();
				LOGGER.info("{} - {}", m.getAttribute(), val);
				outMap.put(m.getAttribute(), val);
			} catch (ScriptException e) {
				e.printStackTrace();
			}
		});

		return new Gson().toJson(outMap);
	}

}

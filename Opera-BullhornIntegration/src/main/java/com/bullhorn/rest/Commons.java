package com.bullhorn.rest;

import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class Commons {

	public static Response getWebReponseNoParams(String url, RequestType reqType) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = null;
		switch (reqType) {
		case GET:
			response = invocationBuilder.get();
			break;
		case POST:
			response = invocationBuilder.post(null);
			break;
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getWebReponse(String url, Map<String, String> params, RequestType reqType, T retType) {

		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(url);

		for (String key : params.keySet()) {
			String value = params.get(key);
			webTarget = webTarget.queryParam(key, value);
		}

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		Response response = null;
		switch (reqType) {
		case GET:
			response = invocationBuilder.get();
			break;
		case POST:
			response = invocationBuilder.post(null);
			break;
		}

		if (retType instanceof String)
			return (T) response.readEntity(String.class);
		else
			return (T) response;

	}

}

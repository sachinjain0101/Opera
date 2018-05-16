package com.bullhorn.rest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.bullhorn.rest.authmodel.BhAccessInfo;
import com.bullhorn.rest.authmodel.BhTokenInfo;
import com.google.gson.Gson;

public class BhAuthOps {

	public static BhAccessInfo getBHToken(String bhTokenUrl, BhTokenInfo bhTokenInfo) throws IOException {

		Map<String, String> params = new HashMap<String, String>();
		params.put("version", "*");
		params.put("access_token", bhTokenInfo.getAccessToken());

		String content = Commons.getWebReponse(bhTokenUrl, params, RequestType.POST, "");

		Gson gson = new Gson();
		BhAccessInfo bai = gson.fromJson(content, BhAccessInfo.class);

		return bai;

	}

	// @SuppressWarnings("unused")
	public static BhTokenInfo getToken(String tokenUrl, String clientId, String clientSecret, String authCode)
			throws IOException {

		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "authorization_code");
		params.put("code", authCode);
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);

		String content = Commons.getWebReponse(tokenUrl, params, RequestType.POST, "");

		Gson gson = new Gson();
		BhTokenInfo bti = gson.fromJson(content, BhTokenInfo.class);

		return bti;
	}

	public static String getAuthCode(String authUrl, String clientId, String username, String password)
			throws URISyntaxException, IOException {

		Map<String, String> params = new HashMap<String, String>();
		params.put("client_id", clientId);
		params.put("username", username);
		params.put("password", password);
		params.put("response_type", "code");
		params.put("action", "Login");

		Response response = null;
		response = Commons.getWebReponse(authUrl, params, RequestType.GET, response);
		Map<String, List<String>> query = response.getStringHeaders();

		URL url = new URL((query.get("Location")).get(0));
		String code = getQueryMap(url.getQuery()).get("code");

		// System.out.println("Auth Code: \t"+code);
		return code;
	}

	public static Map<String, String> getQueryMap(String query) throws UnsupportedEncodingException {
		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, URLDecoder.decode(value, "UTF-8"));
		}
		return map;
	}

}

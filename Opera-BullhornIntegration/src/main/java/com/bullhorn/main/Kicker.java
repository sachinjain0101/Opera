package com.bullhorn.main;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import com.bullhorn.model.Placement;
import com.bullhorn.rest.BhAuthOps;
import com.bullhorn.rest.Commons;
import com.bullhorn.rest.RequestType;
import com.bullhorn.rest.authmodel.BhAccessInfo;
import com.bullhorn.rest.authmodel.BhTokenInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Kicker {

//	public static final String CLIENT_ID = "d719f0d8-4900-404a-8dda-31349769b4b5";
//	public static final String USERNAME = "ibis.rest";
//	public static final String PASSWORD = "Diversified1!";
//	public static final String CLIENT_SECRET = "greAAx4lAnGrazaD0VqCoczzRxhmGGUv";

	
	//TODO: Do some crap
	public static final String CLIENT_ID = "fa1a5dc9-587e-47e2-a7d6-f2ebc232f322";
	public static final String USERNAME = "ngdemo.peoplenet";
	public static final String PASSWORD = "bhpeople123!";
	public static final String CLIENT_SECRET = "3LdmlJ5ZAn8dnRHJuYonDExR";

	public static final String TOKEN_URL = "https://auth.bullhornstaffing.com/oauth/token";
	public static final String AUTH_URL = "https://auth.bullhornstaffing.com/oauth/authorize";
	public static final String BH_TOKEN_URL = "https://rest.bullhornstaffing.com/rest-services/login";

	public static void main(String[] args) throws URISyntaxException, IOException {

		// Get Authentication Code
		String authCode = BhAuthOps.getAuthCode(AUTH_URL, CLIENT_ID, USERNAME, PASSWORD);
		System.out.println(authCode);
		// Get Token
		BhTokenInfo bhTokenInfo = BhAuthOps.getToken(TOKEN_URL, CLIENT_ID, CLIENT_SECRET, authCode);
		System.out.println(bhTokenInfo.getAccessToken());

		// Get Bullhorn Token
		BhAccessInfo bhAcessInfo = BhAuthOps.getBHToken(BH_TOKEN_URL, bhTokenInfo);
		System.out.println(bhAcessInfo.getBhRestToken());

		// System.out.println(bhAcessInfo.toString());

		// String url =
		// bhAcessInfo.getBhRestUrl()+"entity/Candidate/11207?"+bhAcessInfo.getBhRestToken()+"&fields=*";

		/*
		 * 
		 * int days = 30;
		 * 
		 * DateTime endDate = DateTime.Now.AddDays(-days);
		 * 
		 * var epoch = new DateTime(1970, 1, 1, 0, 0, 0, DateTimeKind.Utc);
		 * 
		 * var unixLogMillis = (long)(endDate.ToUniversalTime() -
		 * epoch).TotalMilliseconds;
		 * 
		 * string whereClause = "(dateEnd IS NULL OR dateEnd >= " + unixLogMillis +
		 * ") AND "; whereClause +=
		 * " status in ('Submitted', 'Approved', 'Rejected', 'Re-Submitted') ";
		 * 
		 * int start = 0; int maxCount = 500;
		 * 
		 * var webQueryParams = new List<WebApiQueryParam>();
		 * 
		 * webQueryParams.Add(new WebApiQueryParam { ParameterName = "fields",
		 * ParameterValue = "*" }); webQueryParams.Add(new WebApiQueryParam {
		 * ParameterName = "where", ParameterValue = whereClause });
		 * webQueryParams.Add(new WebApiQueryParam { ParameterName = "start",
		 * ParameterValue = start.ToString(CultureInfo.InvariantCulture) });
		 * webQueryParams.Add(new WebApiQueryParam { ParameterName = "count",
		 * ParameterValue = maxCount.ToString(CultureInfo.InvariantCulture) });
		 * 
		 */

		String url = bhAcessInfo.getRestUrl() + "query/Placement";

		Map<String, String> parms = new HashMap<String, String>();
		parms.put("where", "status in ('Submitted', 'Approved', 'Rejected', 'Re-Submitted')");
		parms.put("fields", "*");
		parms.put("BhRestToken", bhAcessInfo.getBhRestToken());

		String result = Commons.getWebReponse(url, parms, RequestType.GET, "");

		Gson gson = new Gson();

		JsonParser parser = new JsonParser();
		JsonElement tree = parser.parse(result);
		
		if(tree.isJsonObject()) {
			JsonObject obj = tree.getAsJsonObject();
			JsonElement data = obj.get("data");
			
			if(data.isJsonArray()) {
				
				((JsonArray)data).forEach((v)->{
					JsonObject o = v.getAsJsonObject();
					
					Placement p= gson.fromJson(o.toString(),Placement.class);
					
					System.out.println("--"+p.toString());
					//System.out.println("--"+o.get("id"));
					
				});
				
				
				 
			}
			
		}

	}

}

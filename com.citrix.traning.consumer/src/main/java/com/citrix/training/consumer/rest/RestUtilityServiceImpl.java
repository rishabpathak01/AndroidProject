package com.citrix.training.consumer.rest;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import com.citrix.training.consumer.RestResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestUtilityServiceImpl implements RestUtilityService {

	private static PoolingHttpClientConnectionManager cm;

	private static CloseableHttpClient httpClient = null;

	private static ObjectMapper objectMapper = new ObjectMapper();

	static {
		cm = new PoolingHttpClientConnectionManager();
		// Increase max total connection to 200
		cm.setMaxTotal(200);
		// Increase default max connection per route to 20
		cm.setDefaultMaxPerRoute(20);
		httpClient = HttpClients.custom().setConnectionManager(cm).build();
	}

	public <T> RestResponse<T> get(String url, final Class<T> responseType) {
		// Create a custom response handler
		ResponseHandler<T> responseHandler = new ResponseHandler<T>() {
			public T handleResponse(final HttpResponse response)
					throws ClientProtocolException, IOException {
				int status = response.getStatusLine().getStatusCode();
				System.out.println("..Response..." + status);
				if (status >= 200 && status < 300) {
					HttpEntity entity = response.getEntity();
					String value = entity != null ? EntityUtils
							.toString(entity) : null;
					T finalReponse = null;
					if (StringUtils.isNotBlank(value)) {
						finalReponse = objectMapper.readValue(value,
								responseType);
					}
					return finalReponse;
				} else {
					throw new ClientProtocolException(
							"Unexpected response status: " + status);
				}
			}
		};
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("accept", "application/json");
		T finalT = null;
		try {
			finalT = httpClient.execute(httpGet, responseHandler);
			System.out.println("..string s.." + finalT);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public <T> RestResponse<T> post(String url, Class<T> responseType) {
		// TODO Auto-generated method stub
		return null;
	}

	public <T> RestResponse<T> put(String url, Class<T> responseType) {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(String url) {
		// TODO Auto-generated method stub

	}

}

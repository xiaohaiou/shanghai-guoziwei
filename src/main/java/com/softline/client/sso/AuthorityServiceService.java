package com.softline.client.sso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * AuthorityServiceService service = new AuthorityServiceService();
 * IAuthorityService portType = service.getAuthorityServicePort();
 * portType.cancelLogin(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "AuthorityServiceService", targetNamespace = "http://impl.webService.softline.com/", wsdlLocation = "http://10.123.16.197:8080/bim_sso/cxf/authorityService?WSDL")
public class AuthorityServiceService extends Service {

	private final static URL AUTHORITYSERVICESERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(com.softline.client.sso.AuthorityServiceService.class
					.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.softline.client.sso.AuthorityServiceService.class.getResource(".");
			//url = new URL(baseUrl,"http://10.123.16.197:8080/bim_sso/cxf/authorityService?WSDL");
			url = new URL(baseUrl,"http://10.123.16.197:8080/bim_sso/cxf/authorityService?WSDL");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'http://10.123.16.197:8080/bim_sso/cxf/authorityService?WSDL', retrying as a local file");
			logger.warning(e.getMessage());
		}
		AUTHORITYSERVICESERVICE_WSDL_LOCATION = url;
	}

	public AuthorityServiceService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public AuthorityServiceService() {
		super(AUTHORITYSERVICESERVICE_WSDL_LOCATION, new QName(
				"http://impl.webService.softline.com/",
				"AuthorityServiceService"));
	}

	/**
	 * 
	 * @return returns IAuthorityService
	 */
	@WebEndpoint(name = "AuthorityServicePort")
	public IAuthorityService getAuthorityServicePort() {
		return super.getPort(new QName("http://impl.webService.softline.com/",
				"AuthorityServicePort"), IAuthorityService.class);
	}

}

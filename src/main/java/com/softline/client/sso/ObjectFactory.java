package com.softline.client.sso;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.softline.client.sso package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _AddNewLoginResponse_QNAME = new QName(
			"http://webService.softline.com/", "addNewLoginResponse");
	private final static QName _AddNewLogin_QNAME = new QName(
			"http://webService.softline.com/", "addNewLogin");
	private final static QName _CancelLoginResponse_QNAME = new QName(
			"http://webService.softline.com/", "cancelLoginResponse");
	private final static QName _CancelLogin_QNAME = new QName(
			"http://webService.softline.com/", "cancelLogin");
	private final static QName _CheckLogin_QNAME = new QName(
			"http://webService.softline.com/", "checkLogin");
	private final static QName _CheckLoginResponse_QNAME = new QName(
			"http://webService.softline.com/", "checkLoginResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.softline.client.sso
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link AddNewLogin }
	 * 
	 */
	public AddNewLogin createAddNewLogin() {
		return new AddNewLogin();
	}

	/**
	 * Create an instance of {@link CancelLogin }
	 * 
	 */
	public CancelLogin createCancelLogin() {
		return new CancelLogin();
	}

	/**
	 * Create an instance of {@link CheckLoginResponse }
	 * 
	 */
	public CheckLoginResponse createCheckLoginResponse() {
		return new CheckLoginResponse();
	}

	/**
	 * Create an instance of {@link AddNewLoginResponse }
	 * 
	 */
	public AddNewLoginResponse createAddNewLoginResponse() {
		return new AddNewLoginResponse();
	}

	/**
	 * Create an instance of {@link SsoCheckResult }
	 * 
	 */
	public SsoCheckResult createSsoCheckResult() {
		return new SsoCheckResult();
	}

	/**
	 * Create an instance of {@link CancelLoginResponse }
	 * 
	 */
	public CancelLoginResponse createCancelLoginResponse() {
		return new CancelLoginResponse();
	}

	/**
	 * Create an instance of {@link CheckLogin }
	 * 
	 */
	public CheckLogin createCheckLogin() {
		return new CheckLogin();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link AddNewLoginResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "addNewLoginResponse")
	public JAXBElement<AddNewLoginResponse> createAddNewLoginResponse(
			AddNewLoginResponse value) {
		return new JAXBElement<AddNewLoginResponse>(_AddNewLoginResponse_QNAME,
				AddNewLoginResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AddNewLogin }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "addNewLogin")
	public JAXBElement<AddNewLogin> createAddNewLogin(AddNewLogin value) {
		return new JAXBElement<AddNewLogin>(_AddNewLogin_QNAME,
				AddNewLogin.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CancelLoginResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "cancelLoginResponse")
	public JAXBElement<CancelLoginResponse> createCancelLoginResponse(
			CancelLoginResponse value) {
		return new JAXBElement<CancelLoginResponse>(_CancelLoginResponse_QNAME,
				CancelLoginResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CancelLogin }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "cancelLogin")
	public JAXBElement<CancelLogin> createCancelLogin(CancelLogin value) {
		return new JAXBElement<CancelLogin>(_CancelLogin_QNAME,
				CancelLogin.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link CheckLogin }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "checkLogin")
	public JAXBElement<CheckLogin> createCheckLogin(CheckLogin value) {
		return new JAXBElement<CheckLogin>(_CheckLogin_QNAME, CheckLogin.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link CheckLoginResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "checkLoginResponse")
	public JAXBElement<CheckLoginResponse> createCheckLoginResponse(
			CheckLoginResponse value) {
		return new JAXBElement<CheckLoginResponse>(_CheckLoginResponse_QNAME,
				CheckLoginResponse.class, null, value);
	}

}

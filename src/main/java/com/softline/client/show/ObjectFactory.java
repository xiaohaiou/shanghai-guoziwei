package com.softline.client.show;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the com.softline.client.show package.
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

	private final static QName _GetAllDefTaskDEFResponse_QNAME = new QName(
			"http://webService.softline.com/", "getAllDefTaskDEFResponse");
	private final static QName _GetDefTaskDEFResponse_QNAME = new QName(
			"http://webService.softline.com/", "getDefTaskDEFResponse");
	private final static QName _AlarmCount_QNAME = new QName(
			"http://webService.softline.com/", "alarmCount");
	private final static QName _GetDefTaskDEF_QNAME = new QName(
			"http://webService.softline.com/", "getDefTaskDEF");
	private final static QName _AlarmTimeOutCount_QNAME = new QName(
			"http://webService.softline.com/", "alarmTimeOutCount");
	private final static QName _GetAllDefTaskDEF_QNAME = new QName(
			"http://webService.softline.com/", "getAllDefTaskDEF");
	private final static QName _ModifyDefTask_QNAME = new QName(
			"http://webService.softline.com/", "ModifyDefTask");
	private final static QName _ModifyDefTaskResponse_QNAME = new QName(
			"http://webService.softline.com/", "ModifyDefTaskResponse");
	private final static QName _AlarmCountResponse_QNAME = new QName(
			"http://webService.softline.com/", "alarmCountResponse");
	private final static QName _AlarmTimeOutCountResponse_QNAME = new QName(
			"http://webService.softline.com/", "alarmTimeOutCountResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: com.softline.client.show
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link GetDefTaskDEF }
	 * 
	 */
	public GetDefTaskDEF createGetDefTaskDEF() {
		return new GetDefTaskDEF();
	}

	/**
	 * Create an instance of {@link ModifyDefTask }
	 * 
	 */
	public ModifyDefTask createModifyDefTask() {
		return new ModifyDefTask();
	}

	/**
	 * Create an instance of {@link AlarmTimeOutCountResponse }
	 * 
	 */
	public AlarmTimeOutCountResponse createAlarmTimeOutCountResponse() {
		return new AlarmTimeOutCountResponse();
	}

	/**
	 * Create an instance of {@link ModifyDefTaskResponse }
	 * 
	 */
	public ModifyDefTaskResponse createModifyDefTaskResponse() {
		return new ModifyDefTaskResponse();
	}

	/**
	 * Create an instance of {@link AlarmCount }
	 * 
	 */
	public AlarmCount createAlarmCount() {
		return new AlarmCount();
	}

	/**
	 * Create an instance of {@link GetAllDefTaskDEF }
	 * 
	 */
	public GetAllDefTaskDEF createGetAllDefTaskDEF() {
		return new GetAllDefTaskDEF();
	}

	/**
	 * Create an instance of {@link DefTask }
	 * 
	 */
	public DefTask createDefTask() {
		return new DefTask();
	}

	/**
	 * Create an instance of {@link AlarmTimeOutCount }
	 * 
	 */
	public AlarmTimeOutCount createAlarmTimeOutCount() {
		return new AlarmTimeOutCount();
	}

	/**
	 * Create an instance of {@link GetDefTaskDEFResponse }
	 * 
	 */
	public GetDefTaskDEFResponse createGetDefTaskDEFResponse() {
		return new GetDefTaskDEFResponse();
	}

	/**
	 * Create an instance of {@link GetAllDefTaskDEFResponse }
	 * 
	 */
	public GetAllDefTaskDEFResponse createGetAllDefTaskDEFResponse() {
		return new GetAllDefTaskDEFResponse();
	}

	/**
	 * Create an instance of {@link AlarmCountResponse }
	 * 
	 */
	public AlarmCountResponse createAlarmCountResponse() {
		return new AlarmCountResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetAllDefTaskDEFResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "getAllDefTaskDEFResponse")
	public JAXBElement<GetAllDefTaskDEFResponse> createGetAllDefTaskDEFResponse(
			GetAllDefTaskDEFResponse value) {
		return new JAXBElement<GetAllDefTaskDEFResponse>(
				_GetAllDefTaskDEFResponse_QNAME,
				GetAllDefTaskDEFResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetDefTaskDEFResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "getDefTaskDEFResponse")
	public JAXBElement<GetDefTaskDEFResponse> createGetDefTaskDEFResponse(
			GetDefTaskDEFResponse value) {
		return new JAXBElement<GetDefTaskDEFResponse>(
				_GetDefTaskDEFResponse_QNAME, GetDefTaskDEFResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link AlarmCount }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "alarmCount")
	public JAXBElement<AlarmCount> createAlarmCount(AlarmCount value) {
		return new JAXBElement<AlarmCount>(_AlarmCount_QNAME, AlarmCount.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link GetDefTaskDEF }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "getDefTaskDEF")
	public JAXBElement<GetDefTaskDEF> createGetDefTaskDEF(GetDefTaskDEF value) {
		return new JAXBElement<GetDefTaskDEF>(_GetDefTaskDEF_QNAME,
				GetDefTaskDEF.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link AlarmTimeOutCount }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "alarmTimeOutCount")
	public JAXBElement<AlarmTimeOutCount> createAlarmTimeOutCount(
			AlarmTimeOutCount value) {
		return new JAXBElement<AlarmTimeOutCount>(_AlarmTimeOutCount_QNAME,
				AlarmTimeOutCount.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link GetAllDefTaskDEF }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "getAllDefTaskDEF")
	public JAXBElement<GetAllDefTaskDEF> createGetAllDefTaskDEF(
			GetAllDefTaskDEF value) {
		return new JAXBElement<GetAllDefTaskDEF>(_GetAllDefTaskDEF_QNAME,
				GetAllDefTaskDEF.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ModifyDefTask }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "ModifyDefTask")
	public JAXBElement<ModifyDefTask> createModifyDefTask(ModifyDefTask value) {
		return new JAXBElement<ModifyDefTask>(_ModifyDefTask_QNAME,
				ModifyDefTask.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ModifyDefTaskResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "ModifyDefTaskResponse")
	public JAXBElement<ModifyDefTaskResponse> createModifyDefTaskResponse(
			ModifyDefTaskResponse value) {
		return new JAXBElement<ModifyDefTaskResponse>(
				_ModifyDefTaskResponse_QNAME, ModifyDefTaskResponse.class,
				null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link AlarmCountResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "alarmCountResponse")
	public JAXBElement<AlarmCountResponse> createAlarmCountResponse(
			AlarmCountResponse value) {
		return new JAXBElement<AlarmCountResponse>(_AlarmCountResponse_QNAME,
				AlarmCountResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link AlarmTimeOutCountResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://webService.softline.com/", name = "alarmTimeOutCountResponse")
	public JAXBElement<AlarmTimeOutCountResponse> createAlarmTimeOutCountResponse(
			AlarmTimeOutCountResponse value) {
		return new JAXBElement<AlarmTimeOutCountResponse>(
				_AlarmTimeOutCountResponse_QNAME,
				AlarmTimeOutCountResponse.class, null, value);
	}

}

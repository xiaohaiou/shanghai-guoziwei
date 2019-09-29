package com.softline.client.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for hhPage complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="hhPage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isDel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="pageDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pageNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portalId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hhPage", propOrder = { "id", "isDel", "pageDescription",
		"pageName", "pageNum", "portalId" })
public class HhPage {

	protected Integer id;
	protected Integer isDel;
	protected String pageDescription;
	protected String pageName;
	protected String pageNum;
	protected Integer portalId;

	/**
	 * Gets the value of the id property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setId(Integer value) {
		this.id = value;
	}

	/**
	 * Gets the value of the isDel property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getIsDel() {
		return isDel;
	}

	/**
	 * Sets the value of the isDel property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setIsDel(Integer value) {
		this.isDel = value;
	}

	/**
	 * Gets the value of the pageDescription property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPageDescription() {
		return pageDescription;
	}

	/**
	 * Sets the value of the pageDescription property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPageDescription(String value) {
		this.pageDescription = value;
	}

	/**
	 * Gets the value of the pageName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * Sets the value of the pageName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPageName(String value) {
		this.pageName = value;
	}

	/**
	 * Gets the value of the pageNum property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPageNum() {
		return pageNum;
	}

	/**
	 * Sets the value of the pageNum property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPageNum(String value) {
		this.pageNum = value;
	}

	/**
	 * Gets the value of the portalId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getPortalId() {
		return portalId;
	}

	/**
	 * Sets the value of the portalId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setPortalId(Integer value) {
		this.portalId = value;
	}

}

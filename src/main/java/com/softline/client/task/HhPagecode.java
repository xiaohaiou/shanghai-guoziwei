package com.softline.client.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for hhPagecode complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="hhPagecode">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codeName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="isDel" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="pageId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="pageNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portalId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="seqNo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hhPagecode", propOrder = { "code", "codeName", "id", "isDel",
		"pageId", "pageNum", "portalId", "seqNo" })
public class HhPagecode {

	protected String code;
	protected String codeName;
	protected Integer id;
	protected Integer isDel;
	protected Integer pageId;
	protected String pageNum;
	protected Integer portalId;
	protected Integer seqNo;

	/**
	 * Gets the value of the code property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the value of the code property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCode(String value) {
		this.code = value;
	}

	/**
	 * Gets the value of the codeName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getCodeName() {
		return codeName;
	}

	/**
	 * Sets the value of the codeName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setCodeName(String value) {
		this.codeName = value;
	}

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
	 * Gets the value of the pageId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getPageId() {
		return pageId;
	}

	/**
	 * Sets the value of the pageId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setPageId(Integer value) {
		this.pageId = value;
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

	/**
	 * Gets the value of the seqNo property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getSeqNo() {
		return seqNo;
	}

	/**
	 * Sets the value of the seqNo property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setSeqNo(Integer value) {
		this.seqNo = value;
	}

}

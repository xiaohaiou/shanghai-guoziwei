package com.softline.client.task;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for sysFile complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="sysFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileRemark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileUuid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="releaseId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="typeId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sysFile", propOrder = { "fileName", "filePath", "fileRemark",
		"fileUuid", "id", "releaseId", "typeId" })
public class SysFile {

	protected String fileName;
	protected String filePath;
	protected String fileRemark;
	protected String fileUuid;
	protected Integer id;
	protected Integer releaseId;
	protected Integer typeId;

	/**
	 * Gets the value of the fileName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the value of the fileName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFileName(String value) {
		this.fileName = value;
	}

	/**
	 * Gets the value of the filePath property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * Sets the value of the filePath property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFilePath(String value) {
		this.filePath = value;
	}

	/**
	 * Gets the value of the fileRemark property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFileRemark() {
		return fileRemark;
	}

	/**
	 * Sets the value of the fileRemark property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFileRemark(String value) {
		this.fileRemark = value;
	}

	/**
	 * Gets the value of the fileUuid property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getFileUuid() {
		return fileUuid;
	}

	/**
	 * Sets the value of the fileUuid property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setFileUuid(String value) {
		this.fileUuid = value;
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
	 * Gets the value of the releaseId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getReleaseId() {
		return releaseId;
	}

	/**
	 * Sets the value of the releaseId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setReleaseId(Integer value) {
		this.releaseId = value;
	}

	/**
	 * Gets the value of the typeId property.
	 * 
	 * @return possible object is {@link Integer }
	 * 
	 */
	public Integer getTypeId() {
		return typeId;
	}

	/**
	 * Sets the value of the typeId property.
	 * 
	 * @param value
	 *            allowed object is {@link Integer }
	 * 
	 */
	public void setTypeId(Integer value) {
		this.typeId = value;
	}

}

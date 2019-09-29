package com.softline.service.report;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.softline.entity.HhUsers;
import com.softline.entity.HrPersonOrganization;
import com.softline.entity.HrPersongroup;
import com.softline.entity.HrPersonitem;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;
/**
 * 
 * @author tch
 *
 */
public interface IHrPersongroupService {

	/**
	 * 查询
	 * @param entity 查询实体
	 * @param curPageNum 当前页
	 * @param pageSize 页大小
	 * @return
	 */
	public MsgPage getHrPersongroupListView(HrPersongroup entity, Integer curPageNum, Integer pageSize);
	
	
	
	/**
	 * 
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public MsgPage getHrPersonitemListView(HrPersongroup entity, Integer curPageNum, Integer pageSize);
	
	
	/**
	 * 查询
	 * @param id 查询ID
	 * @return
	 */
	public HrPersongroup getHrPersongroup(Integer id);
	
	
	/**
	 * 保存更新
	 * @param entity
	 * @param use
	 * @param organizationFile 
	 * @return
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public CommitResult saveHrPersongroup(HrPersongroup entity,HhUsers use,MultipartFile itemfile, MultipartFile organizationFile) throws FileNotFoundException, IOException;
	/**
	 * 删除
	 * @param entity
	 * @param use
	 * @return
	 */
	public CommitResult deleteHrPersongroup(Integer id,HhUsers use);
	
	/**
	 * 审核
	 * @param entityid  采购ID
	 * @param examStr 审核备注
	 * @param examResult 审核意见
	 * @param use
	 * @return
	 */
	public CommitResult saveHrPersongroupExamine(Integer entityid,String examStr,Integer examResult,HhUsers use);


	/**
	 * 
	 * @param entity
	 * @param curPageNum
	 * @param pageSize
	 * @return
	 */
	public MsgPage getHrPersonorganizationListView(HrPersongroup entity, Integer curPageNum, Integer pageSize);



	/**
	 * 获取财务人员数据
	 * @param entity
	 * @param request
	 * @param map
	 */
	public List<HrPersongroup> getHrPersongroup(HrPersongroup entity);


	/**
	 * 获取财务人员编制数据
	 * @param entity
	 * @param request
	 * @param map
	 */
	public List<HrPersonOrganization> getHrPersonorganization(Integer id);




	/**
	 * 获取财务人员资质数据
	 * @param entity
	 * @param request
	 * @param map
	 */
	public List<HrPersonitem> getHrPersonitem(Integer id);


	/**
	 * 财务人员资质导出
	 * @param entity
	 * @param request
	 * @param map
	 */
	public XSSFWorkbook getHrPeresonitemList(List<HrPersongroup> exportLists,List<HrPersonitem> exportList);



	/**
	 * 财务人员编制数据导出
	 * @param entity
	 * @param request
	 * @param map
	 */
	public XSSFWorkbook getHrPeresonorganizationList(List<HrPersongroup> exportLists,List<HrPersonOrganization> exportList);



}

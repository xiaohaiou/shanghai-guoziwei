package com.softline.service.bimr;

import java.util.List;

import org.nfunk.jep.function.Str;

import com.softline.entity.HhUsers;
import com.softline.entity.bimr.BimrRiskEvent;
import com.softline.entity.bimr.BimrRiskEventAdoptstrategy;
import com.softline.entity.bimr.BimrRiskEventAdoptstrategyFeedback;
import com.softline.entity.bimr.BimrRiskEventFeedback;
import com.softline.entity.bimr.BimrRiskEventRelevance;
import com.softline.entity.bimr.BimrRiskEventRelevancetrack;
import com.softline.entityTemp.CommitResult;
import com.softline.util.MsgPage;

/**
 * 风险事件
 * 
 * @author tj
 */
public interface IRiskEventService {

	MsgPage getRiskEventListView(BimrRiskEvent entity, Integer curPageNum, Integer pageSize,Integer highunitmeasure);
	
	MsgPage getRiskEventMeasureListView(BimrRiskEvent entity, Integer curPageNum, Integer pageSize,String insidetrackpersonid);
	
	MsgPage getRiskFeedBackListView(BimrRiskEvent entity, Integer curPageNum, Integer pageSize);
	
	MsgPage getExamineTracklist(BimrRiskEventRelevancetrack entity, Integer curPageNum, Integer pageSize);
	
    CommitResult saveRiskEvent(BimrRiskEvent entity,HhUsers use,Integer type);
    
    BimrRiskEvent getRiskEventById(Integer id);
    
    
    CommitResult deleteRiskEvent(BimrRiskEvent entity, HhUsers use);
    
    List<BimrRiskEventAdoptstrategy> getAdoptstrategy(Integer eventId);
    
    
    /* MsgPage getRiskEventResultListView(BimrRiskEvent entity, Integer curPageNum, Integer pageSize);*/
    
    List<BimrRiskEventFeedback> getRiskEventFeedbackList(Integer eventId);
    
    List<BimrRiskEventAdoptstrategyFeedback> getAdoptstrategyFeedbcakList(Integer eventId);
    
    MsgPage getRiskEventRelevanceListView(BimrRiskEvent entity, Integer curPageNum, Integer pageSize);
    
    List<BimrRiskEventRelevance> getRelevanceList(Integer eventId);
    
    CommitResult saveRiskEventRelevance(BimrRiskEvent entity,HhUsers use,List<BimrRiskEventRelevance> relevanceList);

    
    /**
   	 * 审核
   	 * @param entityid  采购ID
   	 * @param examStr 审核备注
   	 * @param examResult 审核意见
   	 * @param use
   	 * @return
   	 */
   	 CommitResult saveRelevanceExamine(BimrRiskEvent entity,String examStr,Integer examResult,HhUsers use);
   	 
   	 
   	CommitResult  updatestauts(BimrRiskEvent entity,Integer status,HhUsers use);
   	
   	CommitResult  updatestautsfortrack(BimrRiskEvent entity,BimrRiskEventRelevancetrack track,HhUsers use);
   	
	CommitResult  savetrack(BimrRiskEventRelevancetrack entity,HhUsers use);
	
	CommitResult  saveFeedback(BimrRiskEventFeedback entity,HhUsers use);
	
	CommitResult  saveistrackover(BimrRiskEvent entity,HhUsers use);
   	
	CommitResult deleteTrack(BimrRiskEventRelevancetrack entity, HhUsers use);
    /**
   	 * 风险事件审核
   	 * @param entityid  采购ID
   	 * @param examStr 审核备注
   	 * @param examResult 审核意见
   	 * @param use
   	 * @return
   	 */
   	 CommitResult saveriskeventExamine(BimrRiskEvent entity,List<BimrRiskEventAdoptstrategy> addoptstrategyList,String examStr,Integer examResult,HhUsers use,String optype,BimrRiskEventRelevancetrack track);
   	 
   	 
   	MsgPage getRiskEventSecondList(BimrRiskEventRelevance entity, Integer curPageNum, Integer pageSize);
   	
   	
   	MsgPage getRiskEventSecondListforDetail(BimrRiskEventRelevance entity, Integer curPageNum, Integer pageSize);
   	
   	List<BimrRiskEventRelevancetrack> getRelevancetrackList(Integer eventId);
   	
   	BimrRiskEventRelevancetrack getBimrRiskEventRelevancetrack(Integer trackId);
   	
   	BimrRiskEventFeedback getBimrRiskEventFeedback(Integer feedbackid);
   	 	
   	BimrRiskEventAdoptstrategyFeedback getBimrRiskEventAdoptstrategyFeedback(Integer feedbackid);
   	
   	CommitResult deleteFeedback(BimrRiskEventFeedback entity, HhUsers use);
   	
   	CommitResult deleteFeedbacks(BimrRiskEventAdoptstrategyFeedback entity, HhUsers use);
   	
   	BimrRiskEventAdoptstrategyFeedback getAdoptstrategyFeedback(Integer AdoptstrategyFeedbcakId);
   	
    
    CommitResult  saveAdoptstrategyFeedbackList(BimrRiskEvent entity,List<BimrRiskEventAdoptstrategyFeedback> list,HhUsers use);
    
    CommitResult  saveAdoptstrategyFeedback(BimrRiskEventAdoptstrategyFeedback entity,HhUsers use);
    
    CommitResult  updatestautsforfeedback(BimrRiskEvent entity,HhUsers use);
    
    CommitResult saveFeedbackExamine(BimrRiskEvent entity,String examStr,Integer examResult,HhUsers use);
	
}

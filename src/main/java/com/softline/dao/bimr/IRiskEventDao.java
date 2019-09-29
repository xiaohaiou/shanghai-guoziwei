package com.softline.dao.bimr;

import java.util.List;

import com.softline.entity.bimr.BimrRiskEvent;
import com.softline.entity.bimr.BimrRiskEventAdoptstrategy;
import com.softline.entity.bimr.BimrRiskEventAdoptstrategyFeedback;
import com.softline.entity.bimr.BimrRiskEventFeedback;
import com.softline.entity.bimr.BimrRiskEventRelevance;
import com.softline.entity.bimr.BimrRiskEventRelevancetrack;

/**
 * 风险事件
 * 
 * @author tj
 */
public interface IRiskEventDao {

    int getRiskEventListCount(BimrRiskEvent entity,Integer highunitmeasure);
	
    List<BimrRiskEvent> getRiskEventList(BimrRiskEvent entity, Integer offsize, Integer pagesize,Integer highunitmeasure);
    
    int getRiskEventMeasureListCount(BimrRiskEvent entity,String insidetrackpersonid);
    
    List<BimrRiskEvent> getRiskEventMeasureList(BimrRiskEvent entity, Integer offsize, Integer pagesize,String insidetrackpersonid);
    
    List<BimrRiskEvent> getRiskEventByCode(String code,int id);
    
    int getRiskFeedBackListCount(BimrRiskEvent entity);
    
    List<BimrRiskEvent> getRiskFeedBackList(BimrRiskEvent entity, Integer offsize, Integer pagesize);
    
    int getExamineTracklistCount(BimrRiskEventRelevancetrack entity);
    
    List<Object> getExamineTracklist(BimrRiskEventRelevancetrack entity, Integer offsize, Integer pagesize);
    
    BimrRiskEvent getRiskEventById(Integer id);
    
    List<BimrRiskEventAdoptstrategy> getAdoptstrategy(Integer eventId);
    
     List<BimrRiskEventFeedback> getRiskEventFeedbackList(Integer eventId);
    
     List<BimrRiskEventAdoptstrategyFeedback> getAdoptstrategyFeedbcakList(Integer eventId);    
    
/*    int getRiskEventResultListCount(BimrRiskEvent entity);
    
    List<BimrRiskEvent> getRiskEventResultList(BimrRiskEvent entity, Integer offsize, Integer pagesize);*/
    
    List<Object> getRiskEventRelevanceList(BimrRiskEvent entity, Integer offsize, Integer pagesize);
    
    List<BimrRiskEventRelevance> getRelevanceList(Integer eventId);
    
    int getRiskEventSecondListCount(BimrRiskEventRelevance entity);
    
    List<Object> getRiskEventSecondList(BimrRiskEventRelevance entity, Integer offsize, Integer pagesize);
	
    List<Integer> getRiskEventID(BimrRiskEventRelevance entity);
    
    int getRiskEventSecondListforDetailCount(BimrRiskEventRelevance entity,String idString);
    
    List<BimrRiskEvent> getRiskEventSecondforDetailList(BimrRiskEventRelevance entity, Integer offsize, Integer pagesize,String idString);
    
	List<BimrRiskEventRelevancetrack> getRelevancetrackList(Integer eventId);
    
	BimrRiskEventRelevancetrack getBimrRiskEventRelevancetrack(Integer trackId);
	
	BimrRiskEventFeedback getBimrRiskEventFeedback(Integer feedbackid);
	
	BimrRiskEventAdoptstrategyFeedback getAdoptstrategyFeedbcak(Integer AdoptstrategyFeedbcakId);

	BimrRiskEventAdoptstrategyFeedback getBimrRiskEventAdoptstrategyFeedback(Integer feedbackid);
}

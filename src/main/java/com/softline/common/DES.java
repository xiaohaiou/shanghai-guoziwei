package com.softline.common;

import java.io.File;

import com.softline.util.ThreeDes;


public class DES {
	
	/**
	 * 加密
	 * @param args
	 * @return
	 */
	public static String encryptMode(String args) {  
        String key ="bimc.hna.net";
        byte[] keyBytes;
		try {
			keyBytes = ThreeDes.getKeyBtyes(key);
			byte[] encoded = ThreeDes.encryptMode(keyBytes, args.getBytes());  
	        return ThreeDes.byte2hex(encoded);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }  
	/**
	 * 解密
	 * @param args
	 * @return
	 */
	public static String decryptMode(String args) {  
        String key ="bimc.hna.net";
        byte[] keyBytes;
		try {
			keyBytes = ThreeDes.getKeyBtyes(key);
			byte[] srcBytes = ThreeDes.decryptMode(keyBytes, ThreeDes.hexStringToByte(args));  
	        return new String(srcBytes);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
    }  
	//opt
	public static final String public_path = File.separator+DES.decryptMode("F19AE31EE19681E4")+File.separator;
	//   /temp
	public static final String temp_path = public_path+DES.decryptMode("B210C4B2E519A641")+File.separator;
	
	//template
	public static final String template_path = File.separator+DES.decryptMode("6247A07F3311D2A53115978241AB16DC")+File.separator;
	//临时文件用于导出audit
	public static final String audit = template_path+DES.decryptMode("C7D9C85F7AE7F31D")+File.separator;
	//境外资产填报导出 422FC6A0D0B96DE38E515C6F65B650EC
	public static final String overseasasset = template_path+DES.decryptMode("422FC6A0D0B96DE38E515C6F65B650EC")+File.separator;
	//境外资产审核导出 F2285285EA59C8DED2E277CC10903B846EB4C948A696C730465A89EACA12D1545A6B56C778D34F5B950CA5AD75FDC3A7
	public static final String overseasassetexamine = template_path+DES.decryptMode("F2285285EA59C8DED2E277CC10903B846EB4C948A696C730465A89EACA12D1545A6B56C778D34F5B950CA5AD75FDC3A7")+File.separator;
	//   /report
	//管理口径核算数据导出2A3CB05EB10DB10903B910816BA8951159A453DC148E3BD7
	public static final String reportmanageadjust = template_path+DES.decryptMode("2A3CB05EB10DB10903B910816BA8951159A453DC148E3BD7")+File.separator;
	//主要财务指标数据导出49534CD64B091A793E6CB5F60A22727E4711284634705041
	public static final String MainFinancialIndicator = template_path+DES.decryptMode("49534CD64B091A793E6CB5F60A22727E4711284634705041")+File.separator;
	
	public static final String report_path = public_path+DES.decryptMode("B6699491E4105BE1")+File.separator;
	//   /reportgroup
	public static final String reportgroup_path = public_path+DES.decryptMode("0F5722F70A8FDE4BBBA6FE4C8AD6E46E")+File.separator;
	
    //taxpay
	public static final String taxpay_path = public_path+DES.decryptMode("F4A36B82ECB1FB03")+File.separator;
	
	//hrfinance
	public static final String hrfinance_path = public_path+DES.decryptMode("8D653082A7381D5884F0D75933583362")+File.separator;
	
	
	//loanfinance
	public static final String loanfinance_path = public_path+DES.decryptMode("A3D3409A8D99D01DFFBC4279C6152BC2")+File.separator;
	
	//receivabledebt
	public static final String receivabledebt_path = public_path+DES.decryptMode("08D01C6F97E11ED32244C498EF1D8045")+File.separator;
	
	//重大工程附件路径project
	//--  /opt_1/upload/
	public static final String OPT_PATH = File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("2F3859BD1AB42CDD")+File.separator;
	//-- /opt_1/upload/work/project/
	public static final String PROJECT_PATH = OPT_PATH+DES.decryptMode("5F009D4B555EDF74")+File.separator+DES.decryptMode("7841EDF6C68003A1")+File.separator;
	//-- /opt_1/upload/work/project/wk/
	public static final String PROJECT_WK_PATH = PROJECT_PATH+DES.decryptMode("E1881B35BB4CE86F")+File.separator;
	//--/opt_1/upload/work/project/pj/
	public static final String PROJECT_PJ_PATH = PROJECT_PATH+DES.decryptMode("9932844E145DB1FF")+File.separator;
	//--/opt_1/upload/work/project/lxVideo/
	public static final String PROJECT_LXVIDEO_PATH = PROJECT_PATH+DES.decryptMode("AB331916AC487479")+File.separator;
	//--/opt_1/weekLine/
	//周报路径
	public static final String PROJECT_WEEKLINE_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("5CFEA4F648CA49153115978241AB16DC")+File.separator;
	//--/opt_1/weekLine/normal/
	//一般周报路径
	public static final String PROJECT_WEEKLINE_NORMAL_PATH =PROJECT_WEEKLINE_PATH+DES.decryptMode("AE2CF235DF1376AD")+File.separator;
	//--/opt_1/weekLine/temp/
	//临时周报路径
	public static final String PROJECT_WEEKLINE_TEMP_PATH =PROJECT_WEEKLINE_PATH+DES.decryptMode("B210C4B2E519A641")+File.separator;
	//--/opt_1/weekLine/history/
	//历史周报路径
	public static final String PROJECT_WEEKLINE_HISTORY_PATH =PROJECT_WEEKLINE_PATH+DES.decryptMode("93015E39508AB1C1")+File.separator;
	//工作台知识库附件路径 :/opt_1/knowledge/
	public static final String KNOWLEDGE_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("C3ECB311A7A8AA2484F0D75933583362")+File.separator;
	//知识库附件路径 :/opt_1/knowledgeStoreHouse/
	public static final String KNOWLEDGESTOREHOUSE_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("C3ECB311A7A8AA24D3CDC7DA6DA3EFD0C32EFD367EE1FB1D")+File.separator;
	//新媒体周报路径:/opt_1/newMediaWeekLine/
	public static final String NEWMEDIA_WEEKLINE_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("8906EF9B6128C16542C064C71AD8ADC83115978241AB16DC")+File.separator;
	//舆情周报路径:/opt_1/consensusWeekLine/
	public static final String CONSENSUS_WEEKLINE_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("0D74DC70A112A41EB4CB11987D76145584F0D75933583362")+File.separator;
	//融资项目进展附件路径:/opt_1/financingProjectProgressEnclosure/
	public static final String FINANCING_PROJECT_PROGRESS_ENCLOSURE_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("2D9C68C5BE94016D20A6948265EA2F969A613A3D8592A791FAE750B03435A88C84F0D75933583362")+File.separator;
	//融资项目进展附件路径:/opt_1/reportFinancingShareEnclosure/
	public static final String REPORT_FINANCING_SHARE_ENCLOSURE_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("B248F748A276822323BF1FEA7589BCBAE888AA38FB5D5B4192C44579EEEEFE6D")+File.separator;
	//融资项目进展附件路径:/opt_1/reportFinancingInnovateEnclosure/
	public static final String REPORT_FINANCING_INNOVATE_ENCLOSURE_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("B248F748A27682238DAB053733A135548BF12B6F5F54F556824E7BCBD61105D33115978241AB16DC")+File.separator;
	
	/** 合规培训附件路径 /opt_1/train/ */
	public static final String TRAIN_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("8AE80121DDA2859E")+File.separator;
	/** 合同范本附件路径 /opt_1/contractModel/ */
	public static final String CONTRACTMODEL_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("3EE4BB6C317E90BC9AA753AC8C68D880")+File.separator;
	/** 员工问责附件路径 /opt_1/duty/ */
	public static final String DUTY_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("9E36DC33226462C5")+File.separator;
	
	/** 维护报告办结信息附件路径 /opt_1/duty/ */
	public static final String REPORTBJ_PATH =File.separator+DES.decryptMode("9F6B2A31CEC5D053")+File.separator+DES.decryptMode("96F4E2C3185F55B23115978241AB16DC")+File.separator;
	
	//--/opt_1/bimr_work/InsideAuditProject
	public static final String INSIDE_AUDIT_PROJECT = DES.decryptMode("F2285285EA59C8DED2E277CC10903B84DED169CF79FF6F14832D73B125510FCF367587046269F055")+File.separator;
	//--/opt_1/bimr_work/BimrCivilcaseWeek
	public static final String BIMR_CIVIL_CASE = DES.decryptMode("F2285285EA59C8DED2E277CC10903B84C4DEDF688966F6C8CF3CFE0CA15D53BA9DA99E4E6C88420A")+File.separator;
	//--/opt_1/bimr_work/BimrCriminalcaseWeek
	public static final String BIMR_CRIMINAL_CASE = DES.decryptMode("F2285285EA59C8DED2E277CC10903B84488268A1122250C41DE6E01D729DD4CD2B36FE74AB5A24ED")+File.separator;
    ///opt_1/bimr_work/BimrComlpianceNewReport
	public static final String BIMR_COMLPIANCE_REPORT = DES.decryptMode("F2285285EA59C8DED2E277CC10903B846EB4C948A696C73050A44DB575DC086ED63BDCFE52D19A643115978241AB16DC")+File.separator;
}

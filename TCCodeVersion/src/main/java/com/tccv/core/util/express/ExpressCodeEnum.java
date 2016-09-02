package com.tccv.core.util.express;

import java.util.List;

import org.apache.commons.lang.enums.Enum;

public class ExpressCodeEnum extends Enum {

	private static final long serialVersionUID = 670291253944680209L;
	private String e_code;
	private String e_name;

	public ExpressCodeEnum(String _e_code, String _e_name) {
		super(_e_code);
		setE_code(_e_code);
		setE_name(_e_name);
	}

	public String getE_code() {
		return e_code;
	}

	public void setE_code(String e_code) {
		this.e_code = e_code;
	}

	public String getE_name() {
		return e_name;
	}

	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	
	public static String getName(String code) throws Exception{
		ExpressCodeEnum expressCodeEnum = (ExpressCodeEnum) ExpressCodeEnum.getEnum(ExpressCodeEnum.class, code);
		if(null!=expressCodeEnum){
			return expressCodeEnum.getE_name();
		}else{
			return "";
		}
	}
	
	public static List<ExpressCodeEnum> getExpressCode() throws Exception{
		
		List<ExpressCodeEnum> list = ExpressCodeEnum.getEnumList(ExpressCodeEnum.class);
		/*for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getE_name()+"--"+list.get(i).getE_code());
		}*/
		return list;
	}
	
	  
	public static final ExpressCodeEnum E_7TLSWL = new ExpressCodeEnum("7TLSWL", "7天连锁物流");
	public static final ExpressCodeEnum E_AJ = new ExpressCodeEnum("AJ", "安捷快递");
	public static final ExpressCodeEnum E_ANE = new ExpressCodeEnum("ANE", "安能物流");
	public static final ExpressCodeEnum E_AXD = new ExpressCodeEnum("AXD", "安信达快递");
	public static final ExpressCodeEnum E_BALUNZHI = new ExpressCodeEnum("BALUNZHI", "巴伦支快递");
	public static final ExpressCodeEnum E_BFDF = new ExpressCodeEnum("BFDF", "百福东方");
	public static final ExpressCodeEnum E_BKWL = new ExpressCodeEnum("BKWL", "宝凯物流");
	public static final ExpressCodeEnum E_BQXHM = new ExpressCodeEnum("BQXHM", "北青小红帽");
	public static final ExpressCodeEnum E_BSWL = new ExpressCodeEnum("BSWL", "邦送物流");
	public static final ExpressCodeEnum E_BTWL = new ExpressCodeEnum("BTWL", "百世物流");
	public static final ExpressCodeEnum E_CCES = new ExpressCodeEnum("CCES", "CCES快递");
	public static final ExpressCodeEnum E_CITY100 = new ExpressCodeEnum("CITY100", "城市100");
	public static final ExpressCodeEnum E_COE = new ExpressCodeEnum("COE", "COE东方快递");
	public static final ExpressCodeEnum E_CSCY = new ExpressCodeEnum("CSCY", "长沙创一");
	public static final ExpressCodeEnum E_CXWL = new ExpressCodeEnum("CXWL", "传喜物流");
	public static final ExpressCodeEnum E_DBL = new ExpressCodeEnum("DBL", "德邦");
	public static final ExpressCodeEnum E_DCWL = new ExpressCodeEnum("DCWL", "德创物流");
	public static final ExpressCodeEnum E_DHWL = new ExpressCodeEnum("DHWL", "东红物流");
	public static final ExpressCodeEnum E_DSWL = new ExpressCodeEnum("DSWL", "D速物流");
	public static final ExpressCodeEnum E_DTKD = new ExpressCodeEnum("DTKD", "店通快递");
	public static final ExpressCodeEnum E_DTWL = new ExpressCodeEnum("DTWL", "大田物流");
	public static final ExpressCodeEnum E_DYWL = new ExpressCodeEnum("DYWL", "大洋物流快递");
	public static final ExpressCodeEnum E_EMS = new ExpressCodeEnum("EMS", "EMS");
	public static final ExpressCodeEnum E_FAST = new ExpressCodeEnum("FAST", "快捷速递");
	public static final ExpressCodeEnum E_FBKD = new ExpressCodeEnum("FBKD", "飞豹快递");
	public static final ExpressCodeEnum E_FEDEX = new ExpressCodeEnum("FEDEX", "FedEx联邦快递");
	public static final ExpressCodeEnum E_FHKD = new ExpressCodeEnum("FHKD", "飞狐快递");
	public static final ExpressCodeEnum E_FKD = new ExpressCodeEnum("FKD", "飞康达");
	public static final ExpressCodeEnum E_FYPS = new ExpressCodeEnum("FYPS", "飞远配送");
	public static final ExpressCodeEnum E_FYSD = new ExpressCodeEnum("FYSD", "凡宇速递");
	public static final ExpressCodeEnum E_GDEMS = new ExpressCodeEnum("GDEMS", "广东邮政");
	public static final ExpressCodeEnum E_GDKD = new ExpressCodeEnum("GDKD", "冠达快递");
	public static final ExpressCodeEnum E_GHX = new ExpressCodeEnum("GHX", "挂号信");
	public static final ExpressCodeEnum E_GKSD = new ExpressCodeEnum("GKSD", "港快速递");
	public static final ExpressCodeEnum E_GSD = new ExpressCodeEnum("GSD", "共速达");
	public static final ExpressCodeEnum E_GTKD = new ExpressCodeEnum("GTKD", "广通速递");
	public static final ExpressCodeEnum E_GTO = new ExpressCodeEnum("GTO", "国通快递");
	public static final ExpressCodeEnum E_GTSD = new ExpressCodeEnum("GTSD", "高铁速递");
	public static final ExpressCodeEnum E_HBJH = new ExpressCodeEnum("HBJH", "河北建华");
	public static final ExpressCodeEnum E_HFWL = new ExpressCodeEnum("HFWL", "汇丰物流");
	public static final ExpressCodeEnum E_HHKD = new ExpressCodeEnum("HHKD", "华航快递");
	public static final ExpressCodeEnum E_HHTT = new ExpressCodeEnum("HHTT", "天天快递");
	public static final ExpressCodeEnum E_HLKD = new ExpressCodeEnum("HLKD", "韩润物流");
	public static final ExpressCodeEnum E_HLWL = new ExpressCodeEnum("HLWL", "恒路物流");
	public static final ExpressCodeEnum E_HMJKD = new ExpressCodeEnum("HMJKD", "黄马甲快递");
	public static final ExpressCodeEnum E_HMSD = new ExpressCodeEnum("HMSD", "海盟速递");
	public static final ExpressCodeEnum E_HOAU = new ExpressCodeEnum("HOAU", "天地华宇");
	public static final ExpressCodeEnum E_hq568 = new ExpressCodeEnum("hq568", "华强物流");
	public static final ExpressCodeEnum E_HQKY = new ExpressCodeEnum("HQKY", "华企快运");
	public static final ExpressCodeEnum E_HSWL = new ExpressCodeEnum("HSWL", "昊盛物流");
	public static final ExpressCodeEnum E_HTKY = new ExpressCodeEnum("HTKY", "百世汇通");
	public static final ExpressCodeEnum E_HTWL = new ExpressCodeEnum("HTWL", "户通物流");
	public static final ExpressCodeEnum E_HXLWL = new ExpressCodeEnum("HXLWL", "华夏龙物流");
	public static final ExpressCodeEnum E_HYLSD = new ExpressCodeEnum("HYLSD", "好来运快递");
	public static final ExpressCodeEnum E_JD = new ExpressCodeEnum("JD", "京东快递");
	public static final ExpressCodeEnum E_JGSD = new ExpressCodeEnum("JGSD", "京广速递");
	public static final ExpressCodeEnum E_JIUYE = new ExpressCodeEnum("JIUYE", "九曳供应链");
	public static final ExpressCodeEnum E_JJKY = new ExpressCodeEnum("JJKY", "佳吉快运");
	public static final ExpressCodeEnum E_JLDT = new ExpressCodeEnum("JLDT", "嘉里大通");
	public static final ExpressCodeEnum E_JTKD = new ExpressCodeEnum("JTKD", "捷特快递");
	public static final ExpressCodeEnum E_JXD = new ExpressCodeEnum("JXD", "急先达");
	public static final ExpressCodeEnum E_JYKD = new ExpressCodeEnum("JYKD", "晋越快递");
	public static final ExpressCodeEnum E_JYM = new ExpressCodeEnum("JYM", "加运美");
	public static final ExpressCodeEnum E_JYSD = new ExpressCodeEnum("JYSD", "久易快递");
	public static final ExpressCodeEnum E_JYWL = new ExpressCodeEnum("JYWL", "佳怡物流");
	public static final ExpressCodeEnum E_KLWL = new ExpressCodeEnum("KLWL", "康力物流");
	public static final ExpressCodeEnum E_KTKD = new ExpressCodeEnum("KTKD", "快淘快递");
	public static final ExpressCodeEnum E_KYDSD = new ExpressCodeEnum("KYDSD", "快优达速递");
	public static final ExpressCodeEnum E_KYWL = new ExpressCodeEnum("KYWL", "跨越速递");
	public static final ExpressCodeEnum E_LB = new ExpressCodeEnum("LB", "龙邦快递");
	public static final ExpressCodeEnum E_LBKD = new ExpressCodeEnum("LBKD", "联邦快递");
	public static final ExpressCodeEnum E_LHKD = new ExpressCodeEnum("LHKD", "蓝弧快递");
	public static final ExpressCodeEnum E_LHT = new ExpressCodeEnum("LHT", "联昊通速递");
	public static final ExpressCodeEnum E_LJD = new ExpressCodeEnum("LJD", "乐捷递");
	public static final ExpressCodeEnum E_LJS = new ExpressCodeEnum("LJS", "立即送");
	public static final ExpressCodeEnum E_MB = new ExpressCodeEnum("MB", "民邦速递");
	public static final ExpressCodeEnum E_MDM = new ExpressCodeEnum("MDM", "门对门");
	public static final ExpressCodeEnum E_MHKD = new ExpressCodeEnum("MHKD", "民航快递");
	public static final ExpressCodeEnum E_MLWL = new ExpressCodeEnum("MLWL", "明亮物流");
	public static final ExpressCodeEnum E_MSKD = new ExpressCodeEnum("MSKD", "闽盛快递");
	public static final ExpressCodeEnum E_NEDA = new ExpressCodeEnum("NEDA", "能达速递");
	public static final ExpressCodeEnum E_NJSBWL = new ExpressCodeEnum("NJSBWL", "南京晟邦物流");
	public static final ExpressCodeEnum E_PADTF = new ExpressCodeEnum("PADTF", "平安达腾飞快递");
	public static final ExpressCodeEnum E_PXWL = new ExpressCodeEnum("PXWL", "陪行物流");
	public static final ExpressCodeEnum E_QCKD = new ExpressCodeEnum("QCKD", "全晨快递");
	public static final ExpressCodeEnum E_QFKD = new ExpressCodeEnum("QFKD", "全峰快递");
	public static final ExpressCodeEnum E_QRT = new ExpressCodeEnum("QRT", "全日通快递");
	public static final ExpressCodeEnum E_RFD = new ExpressCodeEnum("RFD", "如风达");
	public static final ExpressCodeEnum E_RLWL = new ExpressCodeEnum("RLWL", "日昱物流");
	public static final ExpressCodeEnum E_SAD = new ExpressCodeEnum("SAD", "赛澳递");
	public static final ExpressCodeEnum E_SAWL = new ExpressCodeEnum("SAWL", "圣安物流");
	public static final ExpressCodeEnum E_SBWL = new ExpressCodeEnum("SBWL", "盛邦物流");
	public static final ExpressCodeEnum E_SDHH = new ExpressCodeEnum("SDHH", "山东海红");
	public static final ExpressCodeEnum E_SDWL = new ExpressCodeEnum("SDWL", "上大物流");
	public static final ExpressCodeEnum E_SF = new ExpressCodeEnum("SF", "顺丰快递");
	public static final ExpressCodeEnum E_SFWL = new ExpressCodeEnum("SFWL", "盛丰物流");
	public static final ExpressCodeEnum E_SHLDHY = new ExpressCodeEnum("SHLDHY", "上海林道货运");
	public static final ExpressCodeEnum E_SHWL = new ExpressCodeEnum("SHWL", "盛辉物流");
	public static final ExpressCodeEnum E_SJWL = new ExpressCodeEnum("SJWL", "穗佳物流");
	public static final ExpressCodeEnum E_ST = new ExpressCodeEnum("ST", "速通物流");
	public static final ExpressCodeEnum E_STO = new ExpressCodeEnum("STO", "申通快递");
	public static final ExpressCodeEnum E_STSD = new ExpressCodeEnum("STSD", "三态速递");
	public static final ExpressCodeEnum E_SURE = new ExpressCodeEnum("SURE", "速尔快递");
	public static final ExpressCodeEnum E_SXHMJ = new ExpressCodeEnum("SXHMJ", "山西红马甲");
	public static final ExpressCodeEnum E_SYJHE = new ExpressCodeEnum("SYJHE", "沈阳佳惠尔");
	public static final ExpressCodeEnum E_SYKD = new ExpressCodeEnum("SYKD", "世运快递");
	public static final ExpressCodeEnum E_THTX = new ExpressCodeEnum("THTX", "通和天下");
	public static final ExpressCodeEnum E_TSSTO = new ExpressCodeEnum("TSSTO", "唐山申通");
	public static final ExpressCodeEnum E_UAPEX = new ExpressCodeEnum("UAPEX", "全一快递");
	public static final ExpressCodeEnum E_UC = new ExpressCodeEnum("UC", "优速快递");
	public static final ExpressCodeEnum E_WJWL = new ExpressCodeEnum("WJWL", "万家物流");
	public static final ExpressCodeEnum E_WTP = new ExpressCodeEnum("WTP", "微特派");
	public static final ExpressCodeEnum E_WXWL = new ExpressCodeEnum("WXWL", "万象物流");
	public static final ExpressCodeEnum E_XBWL = new ExpressCodeEnum("XBWL", "新邦物流");
	public static final ExpressCodeEnum E_XFEX = new ExpressCodeEnum("XFEX", "信丰快递");
	public static final ExpressCodeEnum E_XGYZ = new ExpressCodeEnum("XGYZ", "香港邮政");
	public static final ExpressCodeEnum E_XLYT = new ExpressCodeEnum("XLYT", "祥龙运通");
	public static final ExpressCodeEnum E_XYT = new ExpressCodeEnum("XYT", "希优特");
	public static final ExpressCodeEnum E_YADEX = new ExpressCodeEnum("YADEX", "源安达快递");
	public static final ExpressCodeEnum E_YBJ = new ExpressCodeEnum("YBJ", "邮必佳");
	public static final ExpressCodeEnum E_YCWL = new ExpressCodeEnum("YCWL", "远成物流");
	public static final ExpressCodeEnum E_YD = new ExpressCodeEnum("YD", "韵达快递");
	public static final ExpressCodeEnum E_YDH = new ExpressCodeEnum("YDH", "义达国际物流");
	public static final ExpressCodeEnum E_YFEX = new ExpressCodeEnum("YFEX", "越丰物流");
	public static final ExpressCodeEnum E_YFHEX = new ExpressCodeEnum("YFHEX", "原飞航物流");
	public static final ExpressCodeEnum E_YFSD = new ExpressCodeEnum("YFSD", "亚风快递");
	public static final ExpressCodeEnum E_YJSD = new ExpressCodeEnum("YJSD", "银捷速递");
	public static final ExpressCodeEnum E_YLSY = new ExpressCodeEnum("YLSY", "亿领速运");
	public static final ExpressCodeEnum E_YMWL = new ExpressCodeEnum("YMWL", "英脉物流");
	public static final ExpressCodeEnum E_YSH = new ExpressCodeEnum("YSH", "亿顺航");
	public static final ExpressCodeEnum E_YSKY = new ExpressCodeEnum("YSKY", "音素快运");
	public static final ExpressCodeEnum E_YTD = new ExpressCodeEnum("YTD", "易通达");
	public static final ExpressCodeEnum E_YTFH = new ExpressCodeEnum("YTFH", "一统飞鸿");
	public static final ExpressCodeEnum E_YTKD = new ExpressCodeEnum("YTKD", "运通快递");
	public static final ExpressCodeEnum E_YTO = new ExpressCodeEnum("YTO", "圆通速递");
	public static final ExpressCodeEnum E_YXWL = new ExpressCodeEnum("YXWL", "宇鑫物流");
	public static final ExpressCodeEnum E_YZPY = new ExpressCodeEnum("YZPY", "邮政平邮/小包");
	public static final ExpressCodeEnum E_ZENY = new ExpressCodeEnum("ZENY", "增益快递");
	public static final ExpressCodeEnum E_ZHQKD = new ExpressCodeEnum("ZHQKD", "汇强快递");
	public static final ExpressCodeEnum E_ZJS = new ExpressCodeEnum("ZJS", "宅急送");
	public static final ExpressCodeEnum E_ZMKM = new ExpressCodeEnum("ZMKM", "芝麻开门");
	public static final ExpressCodeEnum E_ZRSD = new ExpressCodeEnum("ZRSD", "中睿速递");
	public static final ExpressCodeEnum E_ZTE = new ExpressCodeEnum("ZTE", "众通快递");
	public static final ExpressCodeEnum E_ZTKY = new ExpressCodeEnum("ZTKY", "中铁快运");
	public static final ExpressCodeEnum E_ZTO = new ExpressCodeEnum("ZTO", "中通速递");
	public static final ExpressCodeEnum E_ZTWL = new ExpressCodeEnum("ZTWL", "中铁物流");
	public static final ExpressCodeEnum E_ZTWY = new ExpressCodeEnum("ZTWY", "中天万运");
	public static final ExpressCodeEnum E_ZWYSD = new ExpressCodeEnum("ZWYSD", "中外运速递");
	public static final ExpressCodeEnum E_ZYWL = new ExpressCodeEnum("ZYWL", "中邮物流");
	public static final ExpressCodeEnum E_ZZJH = new ExpressCodeEnum("ZZJH", "郑州建华");

}

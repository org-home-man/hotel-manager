package com.hotel.admin.model;

import com.hotel.common.entity.BusinessEntity;
import com.hotel.common.utils.Utils;

/**
 * ---------------------------
 * 客房信息铺表 (BizRoomExt)         
 * ---------------------------
 */
public class BizRoomExt extends BusinessEntity{

	/** 客房编号 */
	private String roomCode;
	/** 免费WIFY */
	private String iswify;
	/** 24H front */
	private String isfront;
	/** 无障碍通道 */
	private String isbarrifr;
	/** 阳台/露台 */
	private String isbalcony;
	/** 厨房 */
	private String iskitchen;
	/** 窗户 */
	private String iswindow;
	/** 空调 */
	private String isheat;
	/** 冰箱 */
	private String isicebox;
	/** 熨衣设备 */
	private String isiron;
	/** 禁烟房 */
	private String isnosmk;
	/** 景观房 */
	private String islandscape;
	/** 高楼层 */
	private String ishighrise;
	/** 停车场 */
	private String ispark;
	/** 健身房 */
	private String isgym;
	/** 游泳池 */
	private String isswmp;
	/** 海滩 */
	private String isbeach;
	/** 温泉 */
	private String ishotsp;
	/** 儿童中心 */
	private String ischildct;
	/** 客房服务 */
	private String isroomserv;
	/** 按摩 */
	private String isknead;
	/** 行政酒廊 */
	private String islounge;
	/** 附近有24H便利店 */
	private String issuper;
	/** 附近有公交车 */
	private String isbus;
	/** 附近有轨道交通站 */
	private String istrafic;
	/** 附近有餐厅 */
	private String isrestau;

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getIswify() {
		return iswify;
	}

	public void setIswify(String iswify) {
		if(Utils.isEmpty(iswify)){
			this.iswify = "2";
			return;
		}
		this.iswify = iswify;
	}

	public String getIsfront() {
		return isfront;
	}

	public void setIsfront(String isfront) {
		if(Utils.isEmpty(isfront)){
			this.isfront = "2";
			return;
		}
		this.isfront = isfront;
	}

	public String getIsbarrifr() {
		return isbarrifr;
	}

	public void setIsbarrifr(String isbarrifr) {
		if(Utils.isEmpty(isbarrifr)){
			this.isbarrifr = "2";
			return;
		}
		this.isbarrifr = isbarrifr;
	}

	public String getIsbalcony() {
		return isbalcony;
	}

	public void setIsbalcony(String isbalcony) {
		if(Utils.isEmpty(isbalcony)){
			this.isbalcony = "2";
			return;
		}
		this.isbalcony = isbalcony;
	}

	public String getIskitchen() {
		return iskitchen;
	}

	public void setIskitchen(String iskitchen) {
		if(Utils.isEmpty(iskitchen)){
			this.iskitchen = "2";
			return;
		}
		this.iskitchen = iskitchen;
	}

	public String getIswindow() {
		return iswindow;
	}

	public void setIswindow(String iswindow) {
		if(Utils.isEmpty(iswindow)){
			this.iswindow = "2";
			return;
		}
		this.iswindow = iswindow;
	}

	public String getIsheat() {
		return isheat;
	}

	public void setIsheat(String isheat) {
		if(Utils.isEmpty(isheat)){
			this.isheat = "2";
			return;
		}
		this.isheat = isheat;
	}

	public String getIsicebox() {
		return isicebox;
	}

	public void setIsicebox(String isicebox) {
		if(Utils.isEmpty(isicebox)){
			this.isicebox = "2";
			return;
		}
		this.isicebox = isicebox;
	}

	public String getIsiron() {
		return isiron;
	}

	public void setIsiron(String isiron) {
		if(Utils.isEmpty(isiron)){
			this.isiron = "2";
			return;
		}
		this.isiron = isiron;
	}

	public String getIsnosmk() {
		return isnosmk;
	}

	public void setIsnosmk(String isnosmk) {
		if(Utils.isEmpty(isnosmk)){
			this.isnosmk = "2";
			return;
		}
		this.isnosmk = isnosmk;
	}

	public String getIslandscape() {
		return islandscape;
	}

	public void setIslandscape(String islandscape) {
		if(Utils.isEmpty(islandscape)){
			this.islandscape = "2";
			return;
		}
		this.islandscape = islandscape;
	}

	public String getIshighrise() {
		return ishighrise;
	}

	public void setIshighrise(String ishighrise) {
		if(Utils.isEmpty(ishighrise)){
			this.ishighrise = "2";
			return;
		}
		this.ishighrise = ishighrise;
	}

	public String getIspark() {
		return ispark;
	}

	public void setIspark(String ispark) {
		if(Utils.isEmpty(ispark)){
			this.ispark = "2";
			return;
		}
		this.ispark = ispark;
	}

	public String getIsgym() {
		return isgym;
	}

	public void setIsgym(String isgym) {
		if(Utils.isEmpty(isgym)){
			this.isgym = "2";
			return;
		}
		this.isgym = isgym;
	}

	public String getIsswmp() {
		return isswmp;
	}

	public void setIsswmp(String isswmp) {
		if(Utils.isEmpty(isswmp)){
			this.isswmp = "2";
			return;
		}
		this.isswmp = isswmp;
	}

	public String getIsbeach() {
		return isbeach;
	}

	public void setIsbeach(String isbeach) {
		if(Utils.isEmpty(isbeach)){
			this.isbeach = "2";
			return;
		}
		this.isbeach = isbeach;
	}

	public String getIshotsp() {
		return ishotsp;
	}

	public void setIshotsp(String ishotsp) {
		if(Utils.isEmpty(ishotsp)){
			this.ishotsp = "2";
			return;
		}
		this.ishotsp = ishotsp;
	}

	public String getIschildct() {
		return ischildct;
	}

	public void setIschildct(String ischildct) {
		if(Utils.isEmpty(ischildct)){
			this.ischildct = "2";
			return;
		}
		this.ischildct = ischildct;
	}

	public String getIsroomserv() {
		return isroomserv;
	}

	public void setIsroomserv(String isroomserv) {
		if(Utils.isEmpty(isroomserv)){
			this.isroomserv = "2";
			return;
		}
		this.isroomserv = isroomserv;
	}

	public String getIsknead() {
		return isknead;
	}

	public void setIsknead(String isknead) {
		if(Utils.isEmpty(isknead)){
			this.isknead = "2";
			return;
		}
		this.isknead = isknead;
	}

	public String getIslounge() {
		return islounge;
	}

	public void setIslounge(String islounge) {
		if(Utils.isEmpty(islounge)){
			this.islounge = "2";
			return;
		}
		this.islounge = islounge;
	}

	public String getIssuper() {
		return issuper;
	}

	public void setIssuper(String issuper) {
		if(Utils.isEmpty(issuper)){
			this.issuper = "2";
			return;
		}
		this.issuper = issuper;
	}

	public String getIsbus() {
		return isbus;
	}

	public void setIsbus(String isbus) {
		if(Utils.isEmpty(isbus)){
			this.isbus = "2";
			return;
		}
		this.isbus = isbus;
	}

	public String getIstrafic() {
		return istrafic;
	}

	public void setIstrafic(String istrafic) {
		if(Utils.isEmpty(istrafic)){
			this.istrafic = "2";
			return;
		}
		this.istrafic = istrafic;
	}

	public String getIsrestau() {
		return isrestau;
	}

	public void setIsrestau(String isrestau) {
		if(Utils.isEmpty(isrestau)){
			this.isrestau = "2";
			return;
		}
		this.isrestau = isrestau;
	}


}
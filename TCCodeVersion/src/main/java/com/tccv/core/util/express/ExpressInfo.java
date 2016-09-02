package com.tccv.core.util.express;

import java.util.List;

public class ExpressInfo {
	
//	快递公司编码
	private String shipperCode;
//	快递公司名称
	private String shipperName;
//	物流运单号
	private String logisticCode;
//	成功与否
	private boolean success = false;
//	失败原因
	private String reason;
//	物流状态：2-在途中,3-签收,4-问题件
	private Integer state;
//	状态节点
	private List<Trace> traces;
	public String getShipperCode() {
		return shipperCode;
	}
	public void setShipperCode(String shipperCode) {
		this.shipperCode = shipperCode;
	}
	public String getShipperName() {
		return shipperName;
	}
	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}
	public String getLogisticCode() {
		return logisticCode;
	}
	public void setLogisticCode(String logisticCode) {
		this.logisticCode = logisticCode;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public List<Trace> getTraces() {
		return traces;
	}
	public void setTraces(List<Trace> traces) {
		this.traces = traces;
	}
	
	
}

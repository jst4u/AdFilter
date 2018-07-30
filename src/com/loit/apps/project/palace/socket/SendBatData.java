package com.loit.apps.project.palace.socket;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;

import com.loit.apps.project.palace.model.AppSendRecordModel;
import com.loit.apps.project.palace.service.AppSendRecordManagerImpl;
import com.loit.core.spring.SpringContext;

public class SendBatData extends Thread {

	private String chargeCode;
	private String tempreture;
	private String voltage;
	private String volume;
	private String getTime;

	public SendBatData(String chargeCode, String tempreture, String voltage, String volume, String getTime) {
		this.chargeCode = chargeCode;
		this.tempreture = tempreture;
		this.voltage = voltage;
		this.volume = volume;
		this.getTime = getTime;
	}

	/**
	 * 发送电池监测信息
	 * 
	 * @param chargeCode
	 *            电池编号
	 * @param tempreture
	 *            温度
	 * @param voltage
	 *            电压
	 * @param volume
	 *            电量
	 * @param getTime
	 *            时间
	 */
	public void run() {
		AppSendRecordManagerImpl appSendRecordManager = (AppSendRecordManagerImpl) SpringContext.getBean("appSendRecordManager");
		AppSendRecordModel model = new AppSendRecordModel();
		model.setSendType("电池设备");
		model.setEquCode(chargeCode);
		model.setData1(tempreture);
		model.setData2(voltage);
		model.setData3(volume);
		model.setSendTime(getTime);

		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		String url = "http://172.17.2.8/thunderserver/DataService.asmx";
		String json = "{\"ChargeCode\":\"" + chargeCode + "\", \"Tempreture\":\"" + tempreture + "\", \"Voltage\":\"" + voltage + "\", \"Volume\":\"" + volume + "\", \"GetTime\":\"" + getTime + "\"}";
		Object[] param = new Object[1];
		param[0] = json;
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(url));
			call.setOperationName(new QName("http://tempuri.org/", "addChargeMonitor"));
			call.addParameter(new QName("http://tempuri.org/", "strEntity"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING); // 返回参数的类型
			call.setSOAPActionURI("http://tempuri.org/addChargeMonitor");
			Object res = call.invoke(param);
			model.setRes(res.toString());
			System.out.println(res);
		} catch (Exception e) {
			model.setRes(e.getLocalizedMessage());
			e.printStackTrace();
		}
		appSendRecordManager.save(model);
	}

}

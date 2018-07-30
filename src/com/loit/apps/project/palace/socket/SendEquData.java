package com.loit.apps.project.palace.socket;

import java.net.URL;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;

import com.loit.apps.project.palace.model.AppSendRecordModel;
import com.loit.apps.project.palace.service.AppSendRecordManagerImpl;
import com.loit.core.spring.SpringContext;

public class SendEquData extends Thread {

	private String equCode;
	private String equType;
	private String dataType;
	private String getTime;

	public SendEquData(String equCode, String equType, String dataType, String getTime) {
		this.equCode = equCode;
		this.equType = equType;
		this.dataType = dataType;
		this.getTime = getTime;
	}

	/**
	 * 发送设备监测信息
	 * 
	 * @param equCode
	 *            设备编号
	 * @param equType
	 *            设备类型
	 * @param dataType
	 *            数据类型
	 * @param getTime
	 *            时间
	 */
	public void run() {
		AppSendRecordManagerImpl appSendRecordManager = (AppSendRecordManagerImpl) SpringContext.getBean("appSendRecordManager");
		AppSendRecordModel model = new AppSendRecordModel();
		model.setSendType("传感设备");
		model.setEquCode(equCode);
		model.setData1(equType);
		model.setData2(dataType);
		model.setSendTime(getTime);

		org.apache.axis.client.Service service = new org.apache.axis.client.Service();
		String url = "http://172.17.2.8/thunderserver/DataService.asmx";
		String json = "{\"EquCode\":\"" + equCode + "\", \"EquType\":\"" + equType + "\", \"DataType\":\"" + dataType + "\", \"GetTime\":\"" + getTime + "\"}";
		Object[] param = new Object[1];
		param[0] = json;
		try {
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(url));
			call.setOperationName(new QName("http://tempuri.org/", "addEquMonitor"));
			call.addParameter(new QName("http://tempuri.org/", "strEntity"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
			call.setUseSOAPAction(true);
			call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING); // 返回参数的类型
			call.setSOAPActionURI("http://tempuri.org/addEquMonitor");
			Object res = call.invoke(param);
			model.setRes(res.toString());
			System.out.println(res);
		} catch (Exception e) {
			model.setRes(e.getLocalizedMessage());
			e.printStackTrace();
		}
		appSendRecordManager.save(model);
	}

	public static void main(String[] args) {
		System.out.println("Start invoking....");
		SendData send = new SendData();
		send.sendGatewayData();
	}

}

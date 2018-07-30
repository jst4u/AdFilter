package com.loit.apps.project.palace.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.loit.apps.project.palace.model.AppGatewayStateModel;
import com.loit.apps.project.palace.socket.BaseTool;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;

@Service
public class AppGatewayStateManagerImpl extends CommonManager {

	public AppGatewayStateModel get(String id) {
		return this.dao.get(AppGatewayStateModel.class, id);
	}

	public List<AppGatewayStateModel> getAll() {
		return this.dao.getAll(AppGatewayStateModel.class, null, null);
	}

	public List<AppGatewayStateModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(AppGatewayStateModel.class, orderBy, pagingInfo);
	}

	public List<AppGatewayStateModel> findByExample(AppGatewayStateModel example) {
		return this.dao.findByExample(example);
	}

	public AppGatewayStateModel save(AppGatewayStateModel model) {
		return this.dao.save(model);
	}

	public AppGatewayStateModel save(byte[] content) {
		String gatewayId = "";
		for (int i = 2; i < 10; i++) {
			gatewayId += BaseTool.getByteHexString(content[i]);
		}
		// 获得RSSI信号强度
		String rssiSignalIntensity = BaseTool.getByteHexString(content[18]);
		// 获得发送时间
		String sendTime = "";
		for (int i = 19; i < 23; i++) {
			sendTime = BaseTool.getByteHexString(content[i]) + sendTime;
		}
		long time = Long.parseLong(sendTime, 16) * 1000;

		AppGatewayStateModel gateway = new AppGatewayStateModel();
		gateway.setGatewayId(BaseTool.convertHexToString(gatewayId));
		gateway.setRemarks1(gatewayId);
		gateway.setRssiSignalIntensity(rssiSignalIntensity);
		gateway.setSendTime(new Date(time).toLocaleString());
		gateway.setSystemTime(new Date());
		return this.dao.save(gateway);
	}

	public List<AppGatewayStateModel> saveAll(Collection<AppGatewayStateModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(AppGatewayStateModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<AppGatewayStateModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(AppGatewayStateModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(AppGatewayStateModel.class, ids);
	}

}

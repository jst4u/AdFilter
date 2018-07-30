package com.loit.apps.project.palace.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.loit.apps.project.palace.model.AppRelayStateModel;
import com.loit.apps.project.palace.socket.BaseTool;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;

@Service
public class AppRelayStateManagerImpl extends CommonManager {

	public AppRelayStateModel get(String id) {
		return this.dao.get(AppRelayStateModel.class, id);
	}

	public List<AppRelayStateModel> getAll() {
		return this.dao.getAll(AppRelayStateModel.class, null, null);
	}

	public List<AppRelayStateModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(AppRelayStateModel.class, orderBy, pagingInfo);
	}

	public List<AppRelayStateModel> findByExample(AppRelayStateModel example) {
		return this.dao.findByExample(example);
	}

	public AppRelayStateModel save(AppRelayStateModel model) {
		return this.dao.save(model);
	}

	public AppRelayStateModel save(byte[] content) {
		// 获得中继ID
		String relayId = "";
		for (int i = 2; i < 10; i++) {
			relayId += BaseTool.getByteHexString(content[i]);
		}
		// 获得发送时间
		String sendTime = "";
		for (int i = 26; i < 30; i++) {
			sendTime = BaseTool.getByteHexString(content[i]) + sendTime;
		}
		long time = Long.parseLong(sendTime, 16) * 1000;

		AppRelayStateModel relay = new AppRelayStateModel();
		relay.setRelayId(BaseTool.convertHexToString(relayId));
		relay.setRemarks1(relayId);
		relay.setSendTime(new Date(time).toLocaleString());
		relay.setSystemTime(new Date());
		return this.dao.save(relay);
	}

	public List<AppRelayStateModel> saveAll(Collection<AppRelayStateModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(AppRelayStateModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<AppRelayStateModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(AppRelayStateModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(AppRelayStateModel.class, ids);
	}

}

package com.loit.apps.project.palace.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.loit.apps.project.palace.model.AppNodeStateModel;
import com.loit.apps.project.palace.socket.BaseTool;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;

@Service
public class AppNodeStateManagerImpl extends CommonManager {

	public AppNodeStateModel get(String id) {
		return this.dao.get(AppNodeStateModel.class, id);
	}

	public List<AppNodeStateModel> getAll() {
		return this.dao.getAll(AppNodeStateModel.class, null, null);
	}

	public List<AppNodeStateModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(AppNodeStateModel.class, orderBy, pagingInfo);
	}

	public List<AppNodeStateModel> findByExample(AppNodeStateModel example) {
		return this.dao.findByExample(example);
	}

	public AppNodeStateModel save(AppNodeStateModel model) {
		return this.dao.save(model);
	}

	public AppNodeStateModel save(byte[] content) {
		// 获得节点ID
		String nodeId = "";
		for (int i = 10; i < 18; i++) {
			nodeId += BaseTool.getByteHexString(content[i]);
		}
		// 获得电流值
		String currentValue = "";
		for (int i = 18; i < 22; i++) {
			currentValue += BaseTool.getByteHexString(content[i]);
		}
		// 获得电压值
		String voltageValue = "";
		for (int i = 22; i < 26; i++) {
			voltageValue += BaseTool.getByteHexString(content[i]);
		}
		// 获得发送时间
		String sendTime = "";
		for (int i = 26; i < 30; i++) {
			sendTime = BaseTool.getByteHexString(content[i]) + sendTime;
		}
		long time = Long.parseLong(sendTime, 16) * 1000;
		// 是否重发
		String isRepeat = BaseTool.getByteHexString(content[30]);
		// 重发次数
		String repeatTimes = BaseTool.getByteHexString(content[31]);
		// 重发时间
		String repeatTime = "";
		for (int i = 32; i < 36; i++) {
			repeatTime = BaseTool.getByteHexString(content[i]) + repeatTime;
		}
		long reTime = Long.parseLong(repeatTime, 16) * 1000;

		AppNodeStateModel node = new AppNodeStateModel();
		node.setNodeId(BaseTool.convertHexToString(nodeId));
		node.setRemarks1(nodeId);
		node.setCurrentValue(currentValue);
		node.setVoltageValue(voltageValue);
		node.setSendTime(new Date(time).toLocaleString());
		node.setSystemTime(new Date());
		node.setIsRepeat(isRepeat);
		node.setRepeatTimes(repeatTimes);
		node.setRepeatTime(new Date(reTime).toLocaleString());
		return this.dao.save(node);
	}

	public List<AppNodeStateModel> saveAll(Collection<AppNodeStateModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(AppNodeStateModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<AppNodeStateModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(AppNodeStateModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(AppNodeStateModel.class, ids);
	}

}

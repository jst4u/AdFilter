package com.loit.apps.project.palace.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.loit.apps.project.palace.model.AppBatteryStateModel;
import com.loit.apps.project.palace.socket.SendBatData;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.spring.CommonManager;

@Service
public class AppBatteryStateManagerImpl extends CommonManager {

	public AppBatteryStateModel get(String id) {
		return this.dao.get(AppBatteryStateModel.class, id);
	}

	public List<AppBatteryStateModel> getAll() {
		return this.dao.getAll(AppBatteryStateModel.class, null, null);
	}

	public List<AppBatteryStateModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(AppBatteryStateModel.class, orderBy, pagingInfo);
	}

	public List<AppBatteryStateModel> findByExample(AppBatteryStateModel example) {
		return this.dao.findByExample(example);
	}

	public AppBatteryStateModel save(AppBatteryStateModel model) {
		return this.dao.save(model);
	}

	public AppBatteryStateModel save(String batteryData) {
		// 电池数据:WZ12306WZ00028WZ00089WZ00011
		String batteryVoltage = batteryData.substring(2, 7);
		String batteryTemperature = batteryData.substring(9, 14);
		String residualCapacity = batteryData.substring(16, 21);
		String batteryId = batteryData.substring(23, 28);

		batteryVoltage = Integer.parseInt(batteryVoltage) / 100.0 + "";
		batteryTemperature = Integer.parseInt(batteryTemperature) + "";
		residualCapacity = Integer.parseInt(residualCapacity) + "";

		System.out.println("***电池电压值：" + batteryVoltage + "温度值:" + batteryTemperature + "剩余容量%比值:" + residualCapacity + "电源ID号:" + batteryId);

		AppBatteryStateModel battery = new AppBatteryStateModel();
		battery.setBatteryVoltage(batteryVoltage);
		battery.setBatteryTemperature(batteryTemperature);
		battery.setResidualCapacity(residualCapacity);
		battery.setBatteryId(batteryId);
		battery.setSystemTime(new Date());
		battery = this.dao.save(battery);
		// 发送给中兵
		new SendBatData(batteryId, batteryTemperature, batteryVoltage, residualCapacity, new Date().toLocaleString()).start();
		return battery;
	}

	public List<AppBatteryStateModel> saveAll(Collection<AppBatteryStateModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(AppBatteryStateModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<AppBatteryStateModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(AppBatteryStateModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(AppBatteryStateModel.class, ids);
	}

}

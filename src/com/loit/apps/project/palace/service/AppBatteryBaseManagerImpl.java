package com.loit.apps.project.palace.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.project.palace.model.AppBatteryBaseModel;
import com.loit.apps.project.palace.model.AppBatteryStateModel;
import com.loit.apps.project.palace.socket.Client;
import com.loit.apps.project.palace.socket.Server;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.commom.query.QueryInfo;
import com.loit.core.spring.CommonManager;

@Service
public class AppBatteryBaseManagerImpl extends CommonManager {

	@Autowired
	private AppBatteryStateManagerImpl appBatteryStateManager;

	public AppBatteryBaseModel get(String id) {
		return this.dao.get(AppBatteryBaseModel.class, id);
	}

	public List<AppBatteryBaseModel> getAll() {
		return this.dao.getAll(AppBatteryBaseModel.class, null, null);
	}

	public List<AppBatteryBaseModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(AppBatteryBaseModel.class, orderBy, pagingInfo);
	}

	public List<AppBatteryBaseModel> findByExample(AppBatteryBaseModel example) {
		return this.dao.findByExample(example);
	}

	public AppBatteryBaseModel save(AppBatteryBaseModel model) {
		return this.dao.save(model);
	}

	public List<AppBatteryBaseModel> saveAll(Collection<AppBatteryBaseModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(AppBatteryBaseModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<AppBatteryBaseModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(AppBatteryBaseModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(AppBatteryBaseModel.class, ids);
	}

	/**
	 * 获得电池异常条数
	 * 
	 * @return
	 */
	public int getExceptionCount() {
		String sql = "select * FROM(" + "select IF(b.sttime IS NULL, '异常', IF(TIMESTAMPDIFF(MINUTE, b.sttime, CURRENT_TIMESTAMP()) < 10, '正常', '异常')) AS state, b.sttime, a.* "
				+ "from app_battery_base AS a LEFT JOIN (" + "select battery_ID, MAX(system_TIME) AS sttime from app_battery_state " + "group by battery_ID) AS b "
				+ "ON a.battery_ID = b.battery_ID )AS c WHERE c.state='异常'";
		int count = this.dao.findBySql(sql, null, null, null).size();
		System.out.println("获得电池异常条数:" + count);
		return count;
	}

	/**
	 * 获取电池状态列表
	 * 
	 * @return
	 */
	public List<AppBatteryBaseModel> getBatteryStateList(QueryInfo param) throws Exception {
		List<AppBatteryBaseModel> allList = this.getAll();
		List<AppBatteryBaseModel> list = new ArrayList<AppBatteryBaseModel>();
		for (AppBatteryBaseModel appBatteryBaseModel : allList) {
			// 电池ID
			String batteryId = appBatteryBaseModel.getBatteryId();
			// 电池状态
			String batteryState = appBatteryBaseModel.getBatteryState();
			/*
			 * 根据电池ID，去电池数据表中查找最新数据，根据最新数据的发送时间，判断与当前时间相差多久（电源数据每15分钟发送一次）。
			 * 超过半小时则判定为异常状态
			 */
			// 数据表中的该ID的最新时间
			AppBatteryStateModel batteryStateModel = new AppBatteryStateModel();
			batteryStateModel.setBatteryId(batteryId);
			// 若电池历史数据中存在该ID数据
			List<AppBatteryStateModel> tempList = appBatteryStateManager.findByExample(batteryStateModel);
			if (!tempList.isEmpty()) {
				// 取最新时间
				Date dataTime = tempList.get(0).getSystemTime();
				for (AppBatteryStateModel appBatteryStateModel : tempList) {
					if (appBatteryStateModel.getSystemTime().after(dataTime)) {
						dataTime = appBatteryStateModel.getSystemTime();
					}
				}
				// 计算和当前时间的差值
				Date nowTime = new Date();
				long l = nowTime.getTime() - dataTime.getTime();
				long min = ((l / (60 * 1000)));
				System.out.println("相差：" + min + "分");
				if (min < 30) {
					batteryState = "正常";
				} else {
					batteryState = "异常";
				}
			} else {
				batteryState = "";
			}
			appBatteryBaseModel.setBatteryState(batteryState);
			this.save(appBatteryBaseModel);
			list.add(appBatteryBaseModel);
		}
		// 排序
		try {
			list = this.getBatteryBaseOrderList(param, list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据排序信息，把电池信息进行排序
	 * 
	 * @param param
	 * @param batteryBasetList
	 * @return
	 */
	public List<AppBatteryBaseModel> getBatteryBaseOrderList(QueryInfo param, List<AppBatteryBaseModel> batteryBasetList) {
		String orderby = param.getOrderBy();
		switch (orderby) {
		case "batteryId asc":
			Collections.sort(batteryBasetList, new Comparator<AppBatteryBaseModel>() {
				public int compare(AppBatteryBaseModel o1, AppBatteryBaseModel o2) {
					return (o1.getBatteryId().compareTo(o2.getBatteryId()));
				}
			});
			break;
		case "batteryId desc":
			Collections.sort(batteryBasetList, new Comparator<AppBatteryBaseModel>() {
				public int compare(AppBatteryBaseModel o1, AppBatteryBaseModel o2) {
					return (o2.getBatteryId().compareTo(o1.getBatteryId()));
				}
			});
			break;
		case "batteryState asc":
			Collections.sort(batteryBasetList, new Comparator<AppBatteryBaseModel>() {
				public int compare(AppBatteryBaseModel o1, AppBatteryBaseModel o2) {
					return o1.getBatteryState().compareTo(o2.getBatteryState());
				}
			});
			break;
		case "batteryState desc":
			Collections.sort(batteryBasetList, new Comparator<AppBatteryBaseModel>() {
				public int compare(AppBatteryBaseModel o1, AppBatteryBaseModel o2) {
					return o2.getBatteryState().compareTo(o1.getBatteryState());
				}
			});
			break;
		default:
			Collections.sort(batteryBasetList, new Comparator<AppBatteryBaseModel>() {
				public int compare(AppBatteryBaseModel o1, AppBatteryBaseModel o2) {
					return (o2.getBatteryId().compareTo(o1.getBatteryId()));
				}
			});
			break;
		}
		// 分页
		PagingInfo pageInfo = param.getPagingInfo();
		if (null != pageInfo) {
			pageInfo.setTotalRows(batteryBasetList.size());
			List<AppBatteryBaseModel> res = new ArrayList<AppBatteryBaseModel>();
			int i = pageInfo.getCurrentRow();
			int lastIndex = i + pageInfo.getPageSize();
			for (; i < lastIndex && i < batteryBasetList.size(); i++) {
				res.add(batteryBasetList.get(i));
			}
			return res;
		}
		return batteryBasetList;
	}

	/**
	 * 远程关闭电池
	 * 
	 * @param ip
	 *            电池IP
	 * @return
	 */
	public int turnoff(String ip) {
		Map<Long, IoSession> map = Server.acceptor.getManagedSessions();
		Set set = map.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<Long, IoSession> entry = (Map.Entry<Long, IoSession>) it.next();
			if (entry.getValue().getRemoteAddress().toString().contains(ip)) {
				entry.getValue().write(57);
				return 1;
			}
		}
		return 0;
	}

	/**
	 * 远程关闭所有电池
	 * 
	 * @return
	 */
	public int turnoffAll() {
		Map<Long, IoSession> map = Server.acceptor.getManagedSessions();
		Set set = map.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry<Long, IoSession> entry = (Map.Entry<Long, IoSession>) it.next();
			entry.getValue().write(57);
		}
		return 1;
	}

	/**
	 * 启动设备端口监听程序，启动电池端口监听程序
	 */
	public void start() {
//		new Client().start();
		new Server().start();
	}

}

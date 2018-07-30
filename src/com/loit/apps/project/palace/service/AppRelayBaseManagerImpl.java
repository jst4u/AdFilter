package com.loit.apps.project.palace.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.project.palace.model.AppRelayBaseModel;
import com.loit.apps.project.palace.model.AppRelayStateModel;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.commom.query.QueryInfo;
import com.loit.core.spring.CommonManager;

@Service
public class AppRelayBaseManagerImpl extends CommonManager{

	@Autowired
	private AppRelayStateManagerImpl appRelayStateManager;
	
	public AppRelayBaseModel get(String id) {
		return this.dao.get(AppRelayBaseModel.class, id);
	}

	public List<AppRelayBaseModel> getAll() {
		return this.dao.getAll(AppRelayBaseModel.class,null,null);
	}
	
	public List<AppRelayBaseModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(AppRelayBaseModel.class, orderBy, pagingInfo);
	}

	public List<AppRelayBaseModel> findByExample(AppRelayBaseModel example) {
		return this.dao.findByExample(example);
	}

	public AppRelayBaseModel save(AppRelayBaseModel model) {
		return this.dao.save(model);
	}

	public List<AppRelayBaseModel> saveAll(Collection<AppRelayBaseModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(AppRelayBaseModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<AppRelayBaseModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(AppRelayBaseModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(AppRelayBaseModel.class, ids);
	}
	
	
	/**
	 * 获得中继异常条数
	 * @return
	 */
	public int getExceptionCount() {
		String sql = "select * FROM("
				+"select IF(b.sttime IS NULL, '异常', IF(TIMESTAMPDIFF(MINUTE, b.sttime, CURRENT_TIMESTAMP()) < 10, '正常', '异常')) AS state, b.sttime, a.* "
				+"from app_relay_base AS a LEFT JOIN ("
						+"select relay_ID, MAX(system_TIME) AS sttime from app_relay_state "
						+"group by relay_ID) AS b "
						+"ON a.relay_ID = b.relay_ID )AS c WHERE c.state='异常'";
		int count = this.dao.findBySql(sql, null, null, null).size();
		System.out.println("获得中继异常条数:"+count);
		return count;
	}
	

	/**
	 * 获取中继状态列表
	 * 
	 * @return
	 */
	public List<AppRelayBaseModel> getRelayStateList(QueryInfo param) throws Exception {
		List<AppRelayBaseModel> allList = this.getAll();
		List<AppRelayBaseModel> list = new ArrayList<AppRelayBaseModel>();
		for (AppRelayBaseModel appRelayBaseModel : allList) {
			// 中继ID
			String relayId = appRelayBaseModel.getRelayId();
			// 中继状态
			String relayState = appRelayBaseModel.getRelayState();
			/*
			 * 根据中继ID，去中继数据表中查找最新数据，根据最新数据的发送时间，判断与当前时间相差多久（中继数据一小时发送一次）。
			 * 超过一个半小时则判定为离线状态，超过两个小时则判定为异常状态
			 */
			// 数据表中的该ID的最新时间
			AppRelayStateModel relayStateModel = new AppRelayStateModel();
			relayStateModel.setRelayId(relayId);
			// 若中继历史数据中存在该ID数据
			List<AppRelayStateModel> tempList =appRelayStateManager.findByExample(relayStateModel);
			if (!tempList.isEmpty()) {
				//取最新时间
				Date dataTime = tempList.get(0).getSystemTime();
				for (AppRelayStateModel appRelayStateModel : tempList) {
					if(appRelayStateModel.getSystemTime().after(dataTime)){
						dataTime = appRelayStateModel.getSystemTime();
					}
				}
				// 计算和当前时间的差值
				Date nowTime = new Date();
				long l = nowTime.getTime() - dataTime.getTime();
				long min = ((l / (60 * 1000)));
				System.out.println("相差：" + min + "分");
				if (min < 90) {
					relayState = "正常";
				} else {
					relayState = "异常";
				}
			}else{
				relayState = "";
			}
			appRelayBaseModel.setRelayState(relayState);
			this.save(appRelayBaseModel);
			list.add(appRelayBaseModel);
		}
//		 排序
		 try {
		 list = this.getRelayBaseOrderList(param, list);
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		return list;
	}

	/**
	 * 根据排序信息，把中继信息进行排序
	 * 
	 * @param param
	 * @param RelayBasetList
	 * @return
	 */
	public List<AppRelayBaseModel> getRelayBaseOrderList(QueryInfo param, List<AppRelayBaseModel> relayBasetList) {
		String orderby = param.getOrderBy();
		switch (orderby) {
		case "relayId asc":
			Collections.sort(relayBasetList, new Comparator<AppRelayBaseModel>() {
				public int compare(AppRelayBaseModel o1, AppRelayBaseModel o2) {
					return (o1.getRelayId().compareTo(o2.getRelayId()));
				}
			});
			break;
		case "relayId desc":
			Collections.sort(relayBasetList, new Comparator<AppRelayBaseModel>() {
				public int compare(AppRelayBaseModel o1, AppRelayBaseModel o2) {
					return (o2.getRelayId().compareTo(o1.getRelayId()));
				}
			});
			break;
		case "relayState asc":
			Collections.sort(relayBasetList, new Comparator<AppRelayBaseModel>() {
				public int compare(AppRelayBaseModel o1, AppRelayBaseModel o2) {
					return o1.getRelayState().compareTo(o2.getRelayState());
				}
			});
			break;
		case "relayState desc":
			Collections.sort(relayBasetList, new Comparator<AppRelayBaseModel>() {
				public int compare(AppRelayBaseModel o1, AppRelayBaseModel o2) {
					return o2.getRelayState().compareTo(o1.getRelayState());
				}
			});
			break;
		default:
			Collections.sort(relayBasetList, new Comparator<AppRelayBaseModel>() {
				public int compare(AppRelayBaseModel o1, AppRelayBaseModel o2) {
					return (o2.getRelayId().compareTo(o1.getRelayId()));
				}
			});
			break;
		}
		// 分页
		PagingInfo pageInfo = param.getPagingInfo();
		if (null != pageInfo) {
			pageInfo.setTotalRows(relayBasetList.size());
			List<AppRelayBaseModel> res = new ArrayList<AppRelayBaseModel>();
			int i = pageInfo.getCurrentRow();
			int lastIndex = i + pageInfo.getPageSize();
			for (; i < lastIndex && i < relayBasetList.size(); i++) {
				res.add(relayBasetList.get(i));
			}
			return res;
		}
		return relayBasetList;
	}
}

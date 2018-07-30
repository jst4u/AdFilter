package com.loit.apps.project.palace.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.project.palace.model.AppGatewayBaseModel;
import com.loit.apps.project.palace.model.AppGatewayStateModel;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.commom.query.QueryInfo;
import com.loit.core.spring.CommonManager;

@Service
public class AppGatewayBaseManagerImpl extends CommonManager{

	@Autowired
	private AppGatewayStateManagerImpl appGatewayStateManager;
	public AppGatewayBaseModel get(String id) {
		return this.dao.get(AppGatewayBaseModel.class, id);
	}

	public List<AppGatewayBaseModel> getAll() {
		return this.dao.getAll(AppGatewayBaseModel.class,null,null);
	}
	
	public List<AppGatewayBaseModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(AppGatewayBaseModel.class, orderBy, pagingInfo);
	}

	public List<AppGatewayBaseModel> findByExample(AppGatewayBaseModel example) {
		return this.dao.findByExample(example);
	}

	public AppGatewayBaseModel save(AppGatewayBaseModel model) {
		return this.dao.save(model);
	}

	public List<AppGatewayBaseModel> saveAll(Collection<AppGatewayBaseModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(AppGatewayBaseModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<AppGatewayBaseModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(AppGatewayBaseModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(AppGatewayBaseModel.class, ids);
	}

	
	/**
	 * 获得网关异常条数
	 * @return
	 */
	public int getExceptionCount() {
		String sql = "select * FROM("
				+"select IF(b.sttime IS NULL, '异常', IF(TIMESTAMPDIFF(MINUTE, b.sttime, CURRENT_TIMESTAMP()) < 10, '正常', '异常')) AS state, b.sttime, a.* "
				+"from app_gateway_base AS a LEFT JOIN ("
						+"select gateway_ID, MAX(SEND_TIME) AS sttime from app_gateway_state "
						+"group by gateway_ID) AS b "
						+"ON a.gateway_ID = b.gateway_ID )AS c WHERE c.state='异常'";
		int count = this.dao.findBySql(sql, null, null, null).size();
		System.out.println("获得网关异常条数:"+count);
		return count;
	}
	
	/**
	 * 获取网关状态列表
	 * 
	 * @return
	 */
	public List<AppGatewayBaseModel> getGatewayStateList(QueryInfo param) throws Exception {
		List<AppGatewayBaseModel> allList = this.getAll();
		List<AppGatewayBaseModel> list = new ArrayList<AppGatewayBaseModel>();
		for (AppGatewayBaseModel appGatewayBaseModel : allList) {
			// 网关ID
			String gatewayId = appGatewayBaseModel.getGatewayId();
			// 网关状态
			String gatewayState = appGatewayBaseModel.getGatewayState();
			/*
			 * 根据网关ID，去网关数据表中查找最新数据，根据最新数据的发送时间，判断与当前时间相差多久（网关数据每5分钟发送一次）。
			 * 超过10分钟则判定为离线状态，超过20分钟则判定为异常状态
			 */
			// 数据表中的该ID的最新时间
			AppGatewayStateModel gatewayStateModel = new AppGatewayStateModel();
			gatewayStateModel.setGatewayId(gatewayId);
			// 若网关历史数据中存在该ID数据
			List<AppGatewayStateModel> tempList =appGatewayStateManager.findByExample(gatewayStateModel);
			if (!tempList.isEmpty()) {
				//取最新时间
				Date dataTime = tempList.get(0).getSystemTime();
				for (AppGatewayStateModel appGatewayStateModel : tempList) {
					if(appGatewayStateModel.getSystemTime().after(dataTime)){
						dataTime = appGatewayStateModel.getSystemTime();
					}
				}
				// 计算和当前时间的差值
				Date nowTime = new Date();
				long l = nowTime.getTime() - dataTime.getTime();
				long min = ((l / (60 * 1000)));
				System.out.println("相差：" + min + "分");
				if (min < 10) {
					gatewayState = "正常";
				} else {
					gatewayState = "异常";
				}
			}else{
				gatewayState = "";
			}
			appGatewayBaseModel.setGatewayState(gatewayState);
			this.save(appGatewayBaseModel);
			list.add(appGatewayBaseModel);
		}
//		 排序
		 try {
		 list = this.getGatewayBaseOrderList(param, list);
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		return list;
	}

	/**
	 * 根据排序信息，把网关信息进行排序
	 * 
	 * @param param
	 * @param GatewayBasetList
	 * @return
	 */
	public List<AppGatewayBaseModel> getGatewayBaseOrderList(QueryInfo param, List<AppGatewayBaseModel> gatewayBasetList) {
		String orderby = param.getOrderBy();
		switch (orderby) {
		case "gatewayId asc":
			Collections.sort(gatewayBasetList, new Comparator<AppGatewayBaseModel>() {
				public int compare(AppGatewayBaseModel o1, AppGatewayBaseModel o2) {
					return (o1.getGatewayId().compareTo(o2.getGatewayId()));
				}
			});
			break;
		case "gatewayId desc":
			Collections.sort(gatewayBasetList, new Comparator<AppGatewayBaseModel>() {
				public int compare(AppGatewayBaseModel o1, AppGatewayBaseModel o2) {
					return (o2.getGatewayId().compareTo(o1.getGatewayId()));
				}
			});
			break;
		case "gatewayState asc":
			Collections.sort(gatewayBasetList, new Comparator<AppGatewayBaseModel>() {
				public int compare(AppGatewayBaseModel o1, AppGatewayBaseModel o2) {
					return o1.getGatewayState().compareTo(o2.getGatewayState());
				}
			});
			break;
		case "gatewayState desc":
			Collections.sort(gatewayBasetList, new Comparator<AppGatewayBaseModel>() {
				public int compare(AppGatewayBaseModel o1, AppGatewayBaseModel o2) {
					return o2.getGatewayState().compareTo(o1.getGatewayState());
				}
			});
			break;
		default:
			Collections.sort(gatewayBasetList, new Comparator<AppGatewayBaseModel>() {
				public int compare(AppGatewayBaseModel o1, AppGatewayBaseModel o2) {
					return (o2.getGatewayId().compareTo(o1.getGatewayId()));
				}
			});
			break;
		}
		// 分页
		PagingInfo pageInfo = param.getPagingInfo();
		if (null != pageInfo) {
			pageInfo.setTotalRows(gatewayBasetList.size());
			List<AppGatewayBaseModel> res = new ArrayList<AppGatewayBaseModel>();
			int i = pageInfo.getCurrentRow();
			int lastIndex = i + pageInfo.getPageSize();
			for (; i < lastIndex && i < gatewayBasetList.size(); i++) {
				res.add(gatewayBasetList.get(i));
			}
			return res;
		}
		return gatewayBasetList;
	}
}

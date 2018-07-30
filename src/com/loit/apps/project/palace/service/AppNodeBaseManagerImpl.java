package com.loit.apps.project.palace.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.project.palace.model.AppNodeBaseModel;
import com.loit.apps.project.palace.model.AppNodeStateModel;
import com.loit.core.commom.query.PagingInfo;
import com.loit.core.commom.query.QueryInfo;
import com.loit.core.spring.CommonManager;

@Service
public class AppNodeBaseManagerImpl extends CommonManager{

	@Autowired
	private AppNodeStateManagerImpl appNodeStateManager;
	
	public AppNodeBaseModel get(String id) {
		return this.dao.get(AppNodeBaseModel.class, id);
	}

	public List<AppNodeBaseModel> getAll() {
		return this.dao.getAll(AppNodeBaseModel.class,null,null);
	}
	
	public List<AppNodeBaseModel> getAll(String orderBy, PagingInfo pagingInfo) {
		return this.dao.getAll(AppNodeBaseModel.class, orderBy, pagingInfo);
	}

	public List<AppNodeBaseModel> findByExample(AppNodeBaseModel example) {
		return this.dao.findByExample(example);
	}

	public AppNodeBaseModel save(AppNodeBaseModel model) {
		return this.dao.save(model);
	}

	public List<AppNodeBaseModel> saveAll(Collection<AppNodeBaseModel> models) {
		return this.dao.saveAll(models);
	}

	public void remove(AppNodeBaseModel model) {
		this.dao.remove(model);
	}

	public void removeAll(Collection<AppNodeBaseModel> models) {
		this.dao.removeAll(models);
	}

	public void removeByPk(String id) {
		this.dao.removeByPk(AppNodeBaseModel.class, id);
	}

	public void removeAllByPk(Collection<String> ids) {
		this.dao.removeAllByPk(AppNodeBaseModel.class, ids);
	}

	/**
	 * 获得节点异常条数
	 * @return
	 */
	public int getExceptionCount() {
		String sql = "select * FROM("
				+"select IF(b.sttime IS NULL, '异常', IF(TIMESTAMPDIFF(MINUTE, b.sttime, CURRENT_TIMESTAMP()) < 10, '正常', '异常')) AS state, b.sttime, a.* "
				+"from app_node_base AS a LEFT JOIN ("
						+"select node_ID, MAX(system_TIME) AS sttime from app_node_state "
						+"group by node_ID) AS b "
						+"ON a.node_ID = b.node_ID )AS c WHERE c.state='异常'";
		int count = this.dao.findBySql(sql, null, null, null).size();
		System.out.println("获得节点异常条数:"+count);
		return count;
	}
	
	
	/**
	 * 获取节点状态列表
	 * 
	 * @return
	 */
	public List<AppNodeBaseModel> getNodeStateList(QueryInfo param) throws Exception {
		List<AppNodeBaseModel> allList = this.getAll();
		List<AppNodeBaseModel> list = new ArrayList<AppNodeBaseModel>();
		for (AppNodeBaseModel appNodeBaseModel : allList) {
			// 节点ID
			String nodeId = appNodeBaseModel.getNodeId();
			// 节点状态
			String nodeState = appNodeBaseModel.getNodeState();
			/*
			 * 根据节点ID，去节点数据表中查找最新数据，根据最新数据的发送时间，判断与当前时间相差多久（隔一个小时发送一次）。
			 * 超过一个半小时则判定为离线状态，超过两个小时则判定为异常状态
			 */
			// 数据表中的该ID的最新时间
			AppNodeStateModel nodeStateModel = new AppNodeStateModel();
			nodeStateModel.setNodeId(nodeId);
			// 若节点历史数据中存在该ID数据
			List<AppNodeStateModel> tempList =appNodeStateManager.findByExample(nodeStateModel);
			if (!tempList.isEmpty()) {
				//取最新时间
				Date dataTime = tempList.get(0).getSystemTime();
				for (AppNodeStateModel appNodeStateModel : tempList) {
					if(appNodeStateModel.getSystemTime().after(dataTime)){
						dataTime = appNodeStateModel.getSystemTime();
					}
				}
				// 计算和当前时间的差值
				Date nowTime = new Date();
				long l = nowTime.getTime() - dataTime.getTime();
				long min = ((l / (60 * 1000)));
				System.out.println("相差：" + min + "分");
				if (min < 90) {
					nodeState = "正常";
				} else {
					nodeState = "异常";
				}
			}else{
				nodeState = "";
			}
			appNodeBaseModel.setNodeState(nodeState);
			this.save(appNodeBaseModel);
			list.add(appNodeBaseModel);
		}
//		 排序
		 try {
		 list = this.getNodeBaseOrderList(param, list);
		 } catch (Exception e) {
		 e.printStackTrace();
		 }
		return list;
	}

	/**
	 * 根据排序信息，把节点信息进行排序
	 * 
	 * @param param
	 * @param nodeBasetList
	 * @return
	 */
	public List<AppNodeBaseModel> getNodeBaseOrderList(QueryInfo param, List<AppNodeBaseModel> nodeBasetList) {
		String orderby = param.getOrderBy();
		switch (orderby) {
		case "nodeId asc":
			Collections.sort(nodeBasetList, new Comparator<AppNodeBaseModel>() {
				public int compare(AppNodeBaseModel o1, AppNodeBaseModel o2) {
					return (o1.getNodeId().compareTo(o2.getNodeId()));
				}
			});
			break;
		case "nodeId desc":
			Collections.sort(nodeBasetList, new Comparator<AppNodeBaseModel>() {
				public int compare(AppNodeBaseModel o1, AppNodeBaseModel o2) {
					return (o2.getNodeId().compareTo(o1.getNodeId()));
				}
			});
			break;
		case "nodeState asc":
			Collections.sort(nodeBasetList, new Comparator<AppNodeBaseModel>() {
				public int compare(AppNodeBaseModel o1, AppNodeBaseModel o2) {
					return o1.getNodeState().compareTo(o2.getNodeState());
				}
			});
			break;
		case "nodeState desc":
			Collections.sort(nodeBasetList, new Comparator<AppNodeBaseModel>() {
				public int compare(AppNodeBaseModel o1, AppNodeBaseModel o2) {
					return o2.getNodeState().compareTo(o1.getNodeState());
				}
			});
			break;
		default:
			Collections.sort(nodeBasetList, new Comparator<AppNodeBaseModel>() {
				public int compare(AppNodeBaseModel o1, AppNodeBaseModel o2) {
					return (o2.getNodeId().compareTo(o1.getNodeId()));
				}
			});
			break;
		}
		// 分页
		PagingInfo pageInfo = param.getPagingInfo();
		if (null != pageInfo) {
			pageInfo.setTotalRows(nodeBasetList.size());
			List<AppNodeBaseModel> res = new ArrayList<AppNodeBaseModel>();
			int i = pageInfo.getCurrentRow();
			int lastIndex = i + pageInfo.getPageSize();
			for (; i < lastIndex && i < nodeBasetList.size(); i++) {
				res.add(nodeBasetList.get(i));
			}
			return res;
		}
		return nodeBasetList;
	}
}

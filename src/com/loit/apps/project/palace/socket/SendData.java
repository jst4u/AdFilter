package com.loit.apps.project.palace.socket;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loit.apps.project.palace.model.AppGatewayBaseModel;
import com.loit.apps.project.palace.model.AppNodeBaseModel;
import com.loit.apps.project.palace.model.AppRelayBaseModel;
import com.loit.apps.project.palace.service.AppGatewayBaseManagerImpl;
import com.loit.apps.project.palace.service.AppNodeBaseManagerImpl;
import com.loit.apps.project.palace.service.AppRelayBaseManagerImpl;
import com.loit.core.spring.CommonManager;

@Service
public class SendData extends CommonManager {
	@Autowired
	private AppGatewayBaseManagerImpl appGatewayBaseManager;
	@Autowired
	private AppRelayBaseManagerImpl appRelayBaseManager;
	@Autowired
	private AppNodeBaseManagerImpl appNodeBaseManager;

	/**
	 * 发送网关信息：根据网关ID，去网关数据表中查找最新数据，根据最新数据的发送时间，判断与当前时间相差多久（网关数据每5分钟发送一次）。
	 * 超过10分钟则判定为异常状态
	 */
	public void sendGatewayData() {
		// 判断网关状态
		String sql = "select IF(b.sttime IS NULL, '异常', IF(TIMESTAMPDIFF(MINUTE, b.sttime, CURRENT_TIMESTAMP()) < 10, '正常', '异常')) AS state, b.sttime, a.* "
				+ " from app_gateway_base AS a LEFT JOIN (" + " select GATEWAY_ID, MAX(SEND_TIME) AS sttime from app_gateway_state" + " group by GATEWAY_ID) AS b "
				+ " ON a.GATEWAY_ID = b.GATEWAY_ID ";
		List<HashMap<String, String>> list = this.dao.findBySql(sql, null, null, null);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).get("STATE") + list.get(i).get("GATEWAY_ID"));
				String state = list.get(i).get("STATE");
				String gatewayId = list.get(i).get("GATEWAY_ID");
				AppGatewayBaseModel appGatewayBaseModel = new AppGatewayBaseModel();
				appGatewayBaseModel.setGatewayId(gatewayId);
				List<AppGatewayBaseModel> findList = appGatewayBaseManager.findByExample(appGatewayBaseModel);
				if (!findList.isEmpty()) {
					appGatewayBaseModel = findList.get(0);
					if (!state.equals(appGatewayBaseModel.getGatewayState())) {
						appGatewayBaseModel.setGatewayState(state);
						appGatewayBaseManager.save(appGatewayBaseModel);
						if (state.equals("正常")) {// 异常到正常---1
							// 发送网关数据
							new SendEquData(gatewayId, "A000", "1", new Date().toLocaleString()).start();
						} else if (state.equals("异常")) {// 正常到异常---2
							// 发送网关数据
							new SendEquData(gatewayId, "A000", "2", new Date().toLocaleString()).start();
						}
					}
				}
			}
		}
	}

	/**
	 * 发送中继信息：根据中继ID，去中继数据表中查找最新数据，根据最新数据的发送时间，判断与当前时间相差多久（中继数据一小时发送一次）。
	 * 超过75分钟则判定为异常状态
	 */
	public void sendRelayData() {
		// 判断中继状态
		String sql = "select IF(b.sttime IS NULL, '异常', IF(TIMESTAMPDIFF(MINUTE, b.sttime, CURRENT_TIMESTAMP()) < 75, '正常', '异常')) AS state, b.sttime, a.* " + " from app_relay_base AS a LEFT JOIN ("
				+ " select RELAY_ID, MAX(SEND_TIME) AS sttime from app_relay_state" + " group by RELAY_ID) AS b " + " ON a.RELAY_ID = b.RELAY_ID ";
		List<HashMap<String, String>> list = this.dao.findBySql(sql, null, null, null);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).get("STATE") + list.get(i).get("RELAY_ID"));
				String state = list.get(i).get("STATE");
				String relayId = list.get(i).get("RELAY_ID");
				AppRelayBaseModel appRelayBaseModel = new AppRelayBaseModel();
				appRelayBaseModel.setRelayId(relayId);
				List<AppRelayBaseModel> findList = appRelayBaseManager.findByExample(appRelayBaseModel);
				if (!findList.isEmpty()) {
					appRelayBaseModel = findList.get(0);
					if (!state.equals(appRelayBaseModel.getRelayState())) {
						appRelayBaseModel.setRelayState(state);
						appRelayBaseManager.save(appRelayBaseModel);
						if (state.equals("正常")) {// 异常到正常---1
							// 发送中继数据
							new SendEquData(relayId, "A001", "1", new Date().toLocaleString()).start();
						} else if (state.equals("异常")) {// 正常到异常---2
							// 发送中继数据
							new SendEquData(relayId, "A001", "2", new Date().toLocaleString()).start();
						}
					}
				}
			}
		}
	}

	/**
	 * 发送节点信息：根据节点ID，去节点数据表中查找最新数据，根据最新数据的发送时间，判断与当前时间相差多久（隔一个小时发送一次）。
	 * 超过75分钟则判定为异常状态
	 */
	public void sendNodeData() {
		// 判断节点状态
		String sql = "select IF(b.sttime IS NULL, '异常', IF(TIMESTAMPDIFF(MINUTE, b.sttime, CURRENT_TIMESTAMP()) < 75, '正常', '异常')) AS state, b.sttime, a.* " + " from app_node_base AS a LEFT JOIN ("
				+ " select NODE_ID, MAX(SEND_TIME) AS sttime from app_node_state" + " group by NODE_ID) AS b " + " ON a.NODE_ID = b.NODE_ID ";
		List<HashMap<String, String>> list = this.dao.findBySql(sql, null, null, null);
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).get("STATE") + list.get(i).get("NODE_ID"));
				String state = list.get(i).get("STATE");
				String nodeId = list.get(i).get("NODE_ID");
				AppNodeBaseModel appNodeBaseModel = new AppNodeBaseModel();
				appNodeBaseModel.setNodeId(nodeId);
				List<AppNodeBaseModel> findList = appNodeBaseManager.findByExample(appNodeBaseModel);
				if (!findList.isEmpty()) {
					appNodeBaseModel = findList.get(0);
					if (!state.equals(appNodeBaseModel.getNodeState())) {
						appNodeBaseModel.setNodeState(state);
						appNodeBaseManager.save(appNodeBaseModel);
						if (state.equals("正常")) {// 异常到正常---1
							// 发送节点数据
							new SendEquData(nodeId, "7003", "1", new Date().toLocaleString()).start();
						} else if (state.equals("异常")) {// 正常到异常---2
							// 发送节点数据
							new SendEquData(nodeId, "7003", "2", new Date().toLocaleString()).start();
						}
					}
				}
			}
		}
	}

}

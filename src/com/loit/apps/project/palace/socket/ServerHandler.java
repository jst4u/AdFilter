package com.loit.apps.project.palace.socket;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loit.apps.project.palace.service.AppBatteryStateManagerImpl;
import com.loit.apps.project.palace.socket.protocal.BatProtocalPack;
import com.loit.apps.project.tianxing.service.TxSoftwareInfoManagerImpl;
import com.loit.core.spring.SpringContext;

public class ServerHandler extends IoHandlerAdapter {
	public static Logger logger = LoggerFactory.getLogger(ServerHandler.class);

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		logger.info("服务端与客户端创建连接...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		logger.info("服务端与客户端连接打开...");
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		BatProtocalPack pack = (BatProtocalPack) message;
		String msg = pack.toString();
		logger.info("服务端接收到的数据为：" + msg);

		// 根据获取的参数设置电池数据，并存到数据库
//		AppBatteryStateManagerImpl appBatteryStateManager = (AppBatteryStateManagerImpl) SpringContext.getBean("appBatteryStateManager");
//		appBatteryStateManager.save(msg);
//		TxSoftwareInfoManagerImpl txSoftwareInfoManager = (TxSoftwareInfoManagerImpl) SpringContext.getBean("txSoftwareInfoManager");
//		txSoftwareInfoManager.save(msg);
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		logger.info("服务端发送信息成功...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("服务端连接已经失效");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		 logger.info("服务端进入空闲状态...");
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.error("服务端发送异常...", cause);
		session.close(true);
	}
}

package com.loit.apps.project.palace.socket;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loit.apps.project.palace.service.AppGatewayStateManagerImpl;
import com.loit.apps.project.palace.service.AppNodeStateManagerImpl;
import com.loit.apps.project.palace.service.AppRelayStateManagerImpl;
import com.loit.apps.project.palace.socket.protocal.EquProtocalPack;
import com.loit.core.spring.SpringContext;

public class ClientHandler extends IoHandlerAdapter {
	private static Logger logger = LoggerFactory.getLogger(ClientHandler.class);

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		EquProtocalPack pack = (EquProtocalPack) message;
		String msg = pack.toString();
		logger.info("客户端接收到的信息为：" + msg);
		byte[] content = pack.getContent();
		String ctrl = BaseTool.getByteHexString(content[0]) + BaseTool.getByteHexString(content[1]);// 控制字
		switch (ctrl) {
		case "A000":// 网关
			IoBuffer bufGateway = IoBuffer.allocate(100).setAutoExpand(true);
			for (int i = 0; i < 10; i++) {
				bufGateway.put(content[i]);
			}
			if (pack.isRight()) {// 校验和正确
				bufGateway.put((byte) 0x01);

				// 根据获取的参数设置网关数据，并存到数据库
				AppGatewayStateManagerImpl appGatewayStateManager = (AppGatewayStateManagerImpl) SpringContext.getBean("appGatewayStateManager");
				appGatewayStateManager.save(content);
			} else {
				bufGateway.put((byte) 0x00);
			}
			bufGateway.flip();
			byte[] byteGateway = new byte[bufGateway.limit()];
			bufGateway.get(byteGateway);
			session.write(new EquProtocalPack(byteGateway));
			System.out.println("应答网关数据：{" + bufGateway + "}");
			break;
		case "7003":// 中继、节点
			IoBuffer bufNode = IoBuffer.allocate(100).setAutoExpand(true);
			for (int i = 0; i < 2; i++) {
				bufNode.put(content[i]);
			}
			for (int i = 10; i < 18; i++) {
				bufNode.put(content[i]);
			}
			if (pack.isRight()) {// 校验和正确
				bufNode.put((byte) 0x01);

				// 根据获取的参数设置中继数据，并存到数据库
				AppRelayStateManagerImpl appRelayStateManager = (AppRelayStateManagerImpl) SpringContext.getBean("appRelayStateManager");
				appRelayStateManager.save(content);

				// 根据获取的参数设置节点数据，并存到数据库
				AppNodeStateManagerImpl appNodeStateManager = (AppNodeStateManagerImpl) SpringContext.getBean("appNodeStateManager");
				appNodeStateManager.save(content);
			} else {
				bufNode.put((byte) 0x00);
			}
			bufNode.flip();
			byte[] byteNode = new byte[bufNode.limit()];
			bufNode.get(byteNode);
			session.write(new EquProtocalPack(byteNode));
			System.out.println("应答中继、节点数据：{" + bufNode + "}");
			break;
		default:
			break;
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("client走人...");
		// session.getService().dispose(true);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.error("客户端发生异常...", cause);
		session.close(true);
	}
}

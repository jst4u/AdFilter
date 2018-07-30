package com.loit.apps.project.palace.socket;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.loit.apps.project.palace.socket.protocal.EquProtocalCodecFactory;
import com.loit.core.commom.SysConfig;

public class Client extends Thread {

	private static Logger logger = LoggerFactory.getLogger(Client.class);
	private static String HOST = SysConfig.pros.getProperty("palace.equ.host", "172.17.2.5");
	private static int PORT = Integer.parseInt(SysConfig.pros.getProperty("palace.equ.port", "9000"));

	public void run() {
		TimerTask task = new TimerTask() {
			IoConnector connector = new Client().getConnector();
			IoSession session = null;

			@Override
			public void run() {
				// System.out.println("客户端是否链接正常，5秒验证一次..." + new Date().toLocaleString());
				if (null == session || !session.isConnected()) {
					while (true) {
						try {
							ConnectFuture future = connector.connect(new InetSocketAddress(HOST, PORT));
							future.awaitUninterruptibly();
							session = future.getSession();
							break;
						} catch (Exception e) {
							logger.error("客户端链接异常，5秒后重试..." + new Date().toLocaleString());
							e.printStackTrace();
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, 0, 5000);
	}

	private IoConnector getConnector() {
		IoConnector connector = new NioSocketConnector();
		connector.setConnectTimeoutMillis(30000);
		connector.getFilterChain().addLast("logging", new LoggingFilter());
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new EquProtocalCodecFactory(Charset.forName("UTF-8"))));
		connector.setHandler(new ClientHandler());
		return connector;
	}

}

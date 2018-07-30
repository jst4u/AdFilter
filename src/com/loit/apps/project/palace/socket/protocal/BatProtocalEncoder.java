package com.loit.apps.project.palace.socket.protocal;

import java.nio.charset.Charset;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class BatProtocalEncoder implements ProtocolEncoder {

	private final Charset charset;

	public BatProtocalEncoder(Charset charset) {
		this.charset = charset;
	}

	/**
	 * 在此处实现对ProtocalPack包的编码工作，并把它写入输出流中
	 */
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
		IoBuffer buf = IoBuffer.allocate(100).setAutoExpand(true);
		buf.putInt((int) message);
		buf.flip();
		out.write(buf);
	}

	@Override
	public void dispose(IoSession arg0) throws Exception {
	}
}
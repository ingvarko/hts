package org.red5.demos.oflaDemo;

import java.net.UnknownHostException;

import org.red5.server.adapter.ApplicationAdapter;
import org.red5.server.api.IConnection;
import org.red5.server.api.Red5;
import org.red5.server.api.scope.IScope;
import org.red5.server.api.stream.IBroadcastStream;
import org.red5.server.api.stream.IPlayItem;
import org.red5.server.api.stream.IServerStream;
import org.red5.server.api.stream.ISubscriberStream;

import com.hts.exceptions.AppException;
import com.hts.service.BroadcastStreamServiceImpl;
import com.hts.service.IpAddressServiceImpl;

public class Application extends ApplicationAdapter {

	private IScope appScope;

	private IServerStream serverStream;

	@Override
	public void streamPublishStart(IBroadcastStream stream) {
		BroadcastStreamServiceImpl broadcastStreamServiceImpl = new BroadcastStreamServiceImpl();
		try {
			broadcastStreamServiceImpl.create(stream.getPublishedName());
		} catch (AppException e) {
			e.printStackTrace();
		} finally {
			super.streamPublishStart(stream);
		}
	}

	@Override
	public void streamBroadcastClose(IBroadcastStream stream) {
		// log w3c connect event
		IConnection conn = Red5.getConnectionLocal();
		// converted to seconds
		long publishDuration = (System.currentTimeMillis() - stream
				.getCreationTime()) / 1000;
		if (conn != null) {
			log.info(
					"IY - W3C x-category:stream x-event:unpublish c-ip:{} cs-bytes:{} sc-bytes:{} x-sname:{} x-file-length:{} x-name:{}",
					new Object[] { conn.getRemoteAddress(),
							conn.getReadBytes(), conn.getWrittenBytes(),
							stream.getName(), publishDuration,
							stream.getPublishedName() });

			BroadcastStreamServiceImpl broadcastStreamServiceImpl = new BroadcastStreamServiceImpl();
			try {
				broadcastStreamServiceImpl.unregisterBroadcastStream(stream
						.getPublishedName());
			} catch (AppException e) {
				e.printStackTrace();
			} finally {
				super.streamBroadcastClose(stream);
			}
		}
	}

	@Override
	public void streamPlayItemPlay(ISubscriberStream stream, IPlayItem item,
			boolean isLive) {
		// log w3c connect event
		log.info(
				"IY - W3C x-category:stream x-event:play c-ip:{} x-sname:{} x-name:{}",
				new Object[] { Red5.getConnectionLocal().getRemoteAddress(),
						stream.getName(), item.getName() });
		log.info("Checking if conection allowed for c-ip{} to stream: {}", Red5
				.getConnectionLocal().getRemoteAddress(), item.getName());
		try {
			boolean status = new IpAddressServiceImpl()
			.isBroadcastStreamAllowedForIP(Red5.getConnectionLocal()
					.getRemoteAddress(), item.getName());
			
			log.info("Is connection alloved:{}", status );
			if (!status){
//			if (true){
				log.info("Closing connection for  c-ip{} to stream: {}", Red5
				.getConnectionLocal().getRemoteAddress(), item.getName());
				stream.close();
			}
		} catch (UnknownHostException e) {
			log.error(e.toString());
			e.printStackTrace();
		} catch (AppException e) {
			log.error(e.toString());
			e.printStackTrace();
		} finally {
			super.streamPlayItemPlay(stream, item, isLive);
		}
	}

	/** {@inheritDoc} */
	@Override
	public boolean appStart(IScope app) {
		super.appStart(app);
		log.info("oflaDemo appStart");
		System.out.println("oflaDemo appStart");
		appScope = app;
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public boolean appConnect(IConnection conn, Object[] params) {
		log.info("oflaDemo appConnect");
		// Trigger calling of "onBWDone", required for some FLV players

		// commenting out the bandwidth code as it is replaced by the mina
		// filters
		// measureBandwidth(conn);
		// if (conn instanceof IStreamCapableConnection) {
		// IStreamCapableConnection streamConn = (IStreamCapableConnection)
		// conn;
		// SimpleConnectionBWConfig bwConfig = new SimpleConnectionBWConfig();
		// bwConfig.getChannelBandwidth()[IBandwidthConfigure.OVERALL_CHANNEL] =
		// 1024 * 1024;
		// bwConfig.getChannelInitialBurst()[IBandwidthConfigure.OVERALL_CHANNEL]
		// =
		// 128 * 1024;
		// streamConn.setBandwidthConfigure(bwConfig);
		// }

		// if (appScope == conn.getScope()) {
		// serverStream = StreamUtils.createServerStream(appScope, "live0");
		// SimplePlayItem item = new SimplePlayItem();
		// item.setStart(0);
		// item.setLength(10000);
		// item.setName("on2_flash8_w_audio");
		// serverStream.addItem(item);
		// item = new SimplePlayItem();
		// item.setStart(20000);
		// item.setLength(10000);
		// item.setName("on2_flash8_w_audio");
		// serverStream.addItem(item);
		// serverStream.start();
		// try {
		// serverStream.saveAs("aaa", false);
		// serverStream.saveAs("bbb", false);
		// } catch (Exception e) {}
		// }

		return super.appConnect(conn, params);
	}

	/** {@inheritDoc} */
	@Override
	public void appDisconnect(IConnection conn) {
		log.info("oflaDemo appDisconnect");
		if (appScope == conn.getScope() && serverStream != null) {
			serverStream.close();
		}
		super.appDisconnect(conn);
	}
}

//package org.leo.thread.pool;
//
//import java.util.HashSet;
//import java.util.Set;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.LinkedBlockingQueue;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * 内部消息出传送对象,单例对象. 注意：如果是多线程在处理，那么对于那种有明确先后处理次序的消息不能使用。
// * */
//public class InnerMsgTransfer {
//	private static InnerMsgTransfer instance;
//	private Logger log = LoggerFactory.getLogger(this.getClass());
//
//	/**
//	 * 监听者缓存
//	 * */
//	private final ConcurrentHashMap<InnerMsgTypeEnum, Set<InnerMsgListener>> listenerMap = new ConcurrentHashMap<InnerMsgTypeEnum, Set<InnerMsgListener>>();
//	
//	/**
//	 * 线程池 参数1：保留的线程池大小.如果希望是单线程，则改为1 参数2：线程池的最大大小 参数3：空闲线程结束的超时时间 参数4：
//	 * 空闲线程结束的超时时间的单位 参数5：存放任务的队列
//	 * */
//	private final ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 6, 1,
//			TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
//
//	private InnerMsgTransfer() {
//	}
//
//	public static InnerMsgTransfer getInstance() {
//		if (instance == null) {
//			synchronized (InnerMsgTransfer.class) {
//				if (instance == null) {
//					instance = new InnerMsgTransfer();
//				}
//			}
//		}
//		return instance;
//	}
//
//	/**
//	 * 注册内部消息监听器（消费者）<br />
//	 * 
//	 * @param listener
//	 *            消息的消费者<br />
//	 * @param msgType
//	 *            消息类型
//	 * */
//	public void registerInnerMsgListener(InnerMsgListener listener, InnerMsgTypeEnum msgType) {
//		if (msgType == null) {
//			return;
//		}
//		// 所有的监听者
//		Set<InnerMsgListener> listeners = listenerMap.get(msgType);
//		if (listeners == null) {
//			synchronized (this) {
//				if (listeners == null) {
//					listeners = new HashSet<InnerMsgListener>();
//					listenerMap.put(msgType, listeners);
//				}
//			}
//		}
//		listeners.add(listener);
//	}
//
//	/**
//	 * 发送内部消息
//	 * 
//	 * @param msgType
//	 *            消息类型
//	 * @param msg
//	 *            传送的消息
//	 * */
//	public void transferInnerMsg(InnerMsgTypeEnum msgType, Object msg) {
//		if (msgType == null) {
//			return;
//		}
//		executor.execute(new InnerMsgTask(msgType, msg));
//	}
//
//	/**
//	 * 内部类，用于缓存内部消息
//	 * */
//	private class InnerMsgTask implements Runnable {
//		public InnerMsgTypeEnum msgType;
//		public Object msg;
//
//		public InnerMsgTask(InnerMsgTypeEnum msgType, Object msg) {
//			this.msgType = msgType;
//			this.msg = msg;
//		}
//
//		@Override
//		public void run() {
//			// 所有的监听者
//			Set<InnerMsgListener> listeners = listenerMap.get(msgType);
//			if (listeners == null) {
//				return;
//			}
//			for (InnerMsgListener listener : listeners) {
//				listener.onInnerMsg(msgType, msg);
//			}
//		}
//	}
//
//	public static void main(String[] args) {
//		InnerMsgTransfer transfer = InnerMsgTransfer.getInstance();
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "1");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "2");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "3");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "4");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "5");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "6");
//		transfer.transferInnerMsg(InnerMsgTypeEnum.AREA_DELE, "7");
//	}
//}

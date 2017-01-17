package com.mage.context;

import com.mage.common.MageCommonData;


/**
 * 线程变量接口
 * 此对象只做dubbo、rop等方式调用的入参，出参对象信息
 * @author pzh
 * @date 2016年10月8日 下午4:16:07
 */
public class CoreThreadLocalHolder {
	
	private static final CoreThreadLocalHolder local = new CoreThreadLocalHolder();
	//外系统订单参数
	
	private  ThreadLocal<MageCommonData> mageCommonDataLocal = new ThreadLocal<MageCommonData>(); //DefaultDubbo调用时的统一入口
	
	public static CoreThreadLocalHolder getInstance(){
		return local;
	}

	public  MageCommonData  getMageCommonData() {
		if (mageCommonDataLocal.get() == null) {
			mageCommonDataLocal.set(new MageCommonData());
		}
		return mageCommonDataLocal.get();
	}
	
	/*dubbo调用结束后销毁时调*/
	public void clear(){
		if(mageCommonDataLocal.get() !=null)
			mageCommonDataLocal.get().setMageRequest(null); //调用完成直接清空
	}
}

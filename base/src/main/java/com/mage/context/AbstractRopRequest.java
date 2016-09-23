package com.mage.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mage.common.Temporary;
import com.mage.database.NotDbField;
import com.mage.param.req.RopRequest;
import com.mage.param.req.ZteRequest;
import com.mage.utils.CommonTools;

/**
 * <pre>
 * 所有请求对象应该通过扩展此抽象类实现
 * </pre>
 * 
 * @author
 * @version 1.0
 */
public abstract class AbstractRopRequest implements RopRequest {

	private static final long serialVersionUID = 1L;
	private String is_dirty ="no";//缺省为脏数据
	private String db_action ="";//数据库操作
	private String asy ="no";//异步操作
	private String need_query ="yes";//默认子节点不需要查询
	private List<String> changeFiels =new ArrayList<String>(); //需要更新的字段
	
	private Map<String, Object> dyn_field;					   //动态扩充字段
	
	@NotDbField
	public String getDb_action() {
		return db_action;
	}
	public void setDb_action(String db_action) {
		this.db_action = db_action;
	}
	@NotDbField
	public String getAsy() {
		return asy;
	}
	public void setAsy(String asy) {
		this.asy = asy;
	}
	@NotDbField
	public String getNeed_query() {
		return need_query;
	}
	public void setNeed_query(String need_query) {
		this.need_query = need_query;
	}
	@NotDbField
	public String getIs_dirty() {
		return is_dirty;
	}
	public void setIs_dirty(String is_dirty) {
		this.is_dirty = is_dirty;
	}
	/**
	 * 获取TOP的API名称。
	 * 
	 * @return API名称
	 */
	@NotDbField
	public List<String> getChangeFiels() {
		return changeFiels;
	}
	@NotDbField
	public void setChangeFiels(List<String> changeFiels) {
		this.changeFiels = changeFiels;
	}
	@NotDbField
	public Map<String, Object> getDyn_field() {
		return dyn_field;
	}
	public void setDyn_field(Map<String, Object> dyn_field) {
		this.dyn_field = dyn_field;
	}
	
	//@NotNull
	protected String sessionId = "";
	
	//@NotNull
	public String  userSessionId = "";

	@Temporary
	private RopRequestContext ropRequestContext;

	@NotDbField
	public RopRequestContext getRopRequestContext() {
		return ropRequestContext;
	}
	@NotDbField
	public final void setRopRequestContext(RopRequestContext ropRequestContext) {
		this.ropRequestContext = ropRequestContext;
	}

	@NotDbField
	@Deprecated
	public String getSessionId() { //后续改用getUserSessionId获取sessionId
		return sessionId;
	}

	@NotDbField
	@Deprecated
	public void setSessionId(String sessionId) {//后续改用getUserSessionId获取sessionId
		this.sessionId = sessionId;
	}
	
	@NotDbField
	public String getUserSessionId() {
		
		//dubbo、rop方式调用，zteRequest sessionId优先级最高
		ZteRequest zteRequest = CoreThreadLocalHolder.getInstance().getZteCommonData().getZteRequest();
		if(zteRequest!=null && !isEmpty(zteRequest.userSessionId)) //用户登录的sessionId
		{
			userSessionId = zteRequest.userSessionId;
			GlobalThreadLocalHolder.getInstance().setUserSessionUUID(userSessionId);
		}
		//后面按正常逻辑处理，rop、dubbo方式调用必须生成sessionId，生成sessionId后走缓存那套session共享的逻辑
		if(isEmpty(userSessionId) || "null".equals(userSessionId)){
			userSessionId = UUID.randomUUID().toString().replace("-", "");//add by wui强制生成session_id
//			userSessionId = CommonNTools.getUserSessionId();
		}else if(!userSessionId.equals(CommonTools.getUserSessionId())){
			 GlobalThreadLocalHolder.getInstance().setUserSessionUUID(userSessionId);
		}
//		System.out.print(userSessionId+"==========================================================================getSessionId");
		return userSessionId;
	}

	/**
	 * 验证一个字符串是否为空
	 * 
	 * @param str
	 * @return 如果为空返回真，如果不为空返回假
	 */

	public static boolean isEmpty(String str) {
		if (str == null || "".equals(str))
			return true;

		String pattern = "\\S";
		Pattern p = Pattern.compile(pattern, 2 | Pattern.DOTALL);
		Matcher m = p.matcher(str);
		return !m.find();
	}
	
	public void setRealUserSessionId(String userSessionId) {
		this.userSessionId ="";
	}
	
	/**
	 * 登录后设置会话id
	 */
	@NotDbField
	public void setUserSessionId(String userSessionId) {
		if(isEmpty(userSessionId)){
			this.userSessionId =""; //add by wui
			return;
		}
		this.userSessionId =userSessionId;
		GlobalThreadLocalHolder.getInstance().setUserSessionUUID(userSessionId);
		ZteRequest zteRequest = CoreThreadLocalHolder.getInstance().getZteCommonData().getZteRequest();
		if(zteRequest!=null && isEmpty(zteRequest.userSessionId))
		{
			zteRequest.userSessionId = userSessionId;
		}
		//System.out.print(userSessionId+"==========================================================================setSessionId");
	}
}

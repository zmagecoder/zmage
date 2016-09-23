package com.mage.cms.utils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

import com.mage.cms.common.CmsContext;
import com.mage.cms.common.CmsServiceFactory;
import com.mage.cms.common.model.AppPage;
import com.mage.cms.common.model.Modual;
import com.mage.cms.common.params.dynamic.resp.GetDynamicPluginResp;
import com.mage.cms.common.params.img.vo.Adv;
import com.mage.cms.consts.CmsConst;
import com.mage.database.Page;
import com.mage.platform.framework.config.EopSetting;
import com.mage.platform.framework.context.EopContext;
import com.mage.platform.framework.context.ThreadContextHolder;
import com.mage.platform.framework.utils.BeanUtils;
import com.mage.platform.framework.utils.ListUtil;
import com.mage.platform.framework.utils.ManagerUtils;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;


/**
 * CMS工具类
 * @author pzh
 * @date 2014.05.07
 */
public class CmsUtil {

	private static final String separator = File.separator;
	
	
	
	/**
	 * 获取当前用户资源数据
	 * @param start
	 * @param end
	 * @param res_type
	 * @param res_id
	 * @param res_value
	 * @return key: res_id,res_value 结果集与页面所需结果集一致
	 */
	public static List<Map<String, String>> getResourceDatas(int start,int end,String res_type,String res_id,String res_value){
		List<Map<String, String>> res_list = new ArrayList<Map<String,String>>();
		List list = new ArrayList();
		
		return res_list;
	}
	
	
	/**
	 * 获取资源结果集
	 * @param objList
	 * @param res_id
	 * @param res_value
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static List<Map<String, String>> getSourceList(List objList,String res_id,String res_value) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		
		List<Map<String, String>> resList = new ArrayList<Map<String,String>>();
		
		if(!ListUtil.isEmpty(objList)){
			for(Object obj:objList){
				Map<String, String> resMap = new HashMap<String, String>();
				
				resMap.put("res_id", getValue(obj, res_id));
				if(!StringUtils.isEmpty(res_value)){
					String value = "";
					String[] res_values  = res_value.split("-");
					for (int i = 0; i < res_values.length; i++) {
						String val = getValue(obj, res_values[i]);
						value += val + "-";
					}
					if(!StringUtils.isEmpty(value)){
						value = value.substring(0,value.length()-1);
					}
					resMap.put("res_value", value);
				}
				resList.add(resMap);
			}
		}
		return resList;
	}
	
	
	/**
	 * 根据对象key获取对象属性
	 * @param obj
	 * @param key
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static String getValue(Object obj,String key) throws SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		String value = null;
		if(!StringUtils.isEmpty(key)){
			Class clazz = obj.getClass();
			String getMethod = "get" + key.replaceFirst(key.split("")[1], key.split("")[1].toUpperCase());
			Method m = clazz.getDeclaredMethod(getMethod);
			value = String.valueOf(m.invoke(obj));
		}
		return value;
	}
	
	/**
	 * 获取商品资源分页数据
	 * @param pageNo
	 * @param pageSize
	 * @param goods_name
	 * @return
	 */
	public static Page getGoodsPage(int pageNo,int pageSize,String goods_name){
		
		return new Page();
	}
	
	
	/**
	 * 获取广告或视频资源分页数据
	 * @param pageNo
	 * @param pageSize
	 * @param adv_name
	 * @param adv_type
	 * @return
	 */
	public static Page getAdvPage(int pageNo,int pageSize,Adv adv){
		
		return new Page();
	}
	
	/**
	 * 获取公告资源
	 * @param pageNo
	 * @param pageSize
	 * @param notice_name
	 * @return
	 */
	public static Page getNoticePage(int pageNo,int pageSize,String notice_name){
		
		return new Page();
	}
	
	
	/**
	 * 生成页面与图片文件
	 * @param map pre_fix:文件前缀 ;data:写入了数据的map对象;page_name:页面名称(eg:ott_community.html)
	 * @return map img_path:图片文件地址;html_path:页面文件地址
	 * @throws Exception
	 */
	public static Map<String, Object> getImgAndHtml(Map<String, Object> params) throws Exception{
		Map<String, Object> resMap = new HashMap<String, Object>();	//返回的结果
//		String pre_fix = (String) params.get("pre_fix"); 	
		
		//获取页面
		String html = getHtml(params);
		resMap.put("img_path", "");
		resMap.put("html_path", html);
		
//		Date d = new Date();
//		long current = d.getTime();
		
//		final String img_name = "ott_" + pre_fix + "_" + current + ".png";	//生成的图片名称
//		final String html_name = "ott_" + pre_fix + "_" + current + ".html";	//生成的页面名称
		
		//上传的路径
//		final String path =  EopSetting.IMG_SERVER_PATH +EopContext.getContext().getContextPath()+separator+"attachment"+separator+"cms";
		
		
		//上传html文件
//		FileOutputStream fileoutputstream = new FileOutputStream(path+separator+html_name);
//		byte tag_bytes[] = html.getBytes("utf-8");        
//		fileoutputstream.write(tag_bytes);
//		fileoutputstream.close(); 
		
		//同步html文件至ftp
//		UploadTools.syToFtpFile(html_name, path+separator+html_name, separator+"attachment"+separator+"cms");
		
		
		//返回的页面路径
//		String res_html_path = EopSetting.FILE_STORE_PREFIX+"/attachment/cms/"+html_name;
//		resMap.put("html_path", res_html_path);
		
//		HttpServletRequest request = ServletActionContext.getRequest();
//		//访问页面的地址
//		String uri = request.getScheme()+"://"+request.getServerName()+
//				":"+request.getServerPort() + UploadUtilc.replacePath(res_html_path);
//		//访问页面，生成流
//		URL url = new URL(uri);
//		URLConnection connection = url.openConnection();
//		InputStream in = connection.getInputStream();
//		Reader reader = new InputStreamReader(in,"utf-8");
//		InputSource is = new InputSourceImpl(reader, uri);
//		
//		虚拟机下执行报错
//		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//		final int img_width =dim.width;
//		final int img_height=dim.height;
//		
//		final int img_width =1600;
//		final int img_height=1200;
//		
//		final HtmlPanel htmlPanel = new HtmlPanel();
//		UserAgentContext ucontext = new LocalUserAgentContext();
//		HtmlRendererContext rendererContext = new LocalHtmlRendererContext(htmlPanel, ucontext);
//		
//		//生成图片文件
//		htmlPanel.setPreferredWidth(img_width);
//		DocumentBuilderImpl builder = new DocumentBuilderImpl(rendererContext.getUserAgentContext(), rendererContext);
//		Document document = builder.parse(is);
//		htmlPanel.setDocument(document, rendererContext);
//		final JFrame frame = new JFrame();
//		frame.getContentPane().add(htmlPanel);
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				frame.pack();
//				frame.setVisible(true);
//				
//				BufferedImage image = new BufferedImage(img_width,img_height, BufferedImage.TYPE_INT_ARGB);
//				SwingUtilities.paintComponent(image.createGraphics(), 
//						htmlPanel,new JPanel(), 0, 0, img_width, img_height);
//				try {
//					ImageIO.write((RenderedImage) image, "png", new File(path+separator+img_name));
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				frame.setVisible(false);
//			}
//		});
//		
//		//同步图片文件到ftp
//		UploadTools.syToFtpFile(img_name , path+separator+img_name, separator+"attachment"+separator+"cms");
//		
//		
//		//返回的图片路径
//		String res_img_parh = EopSetting.FILE_STORE_PREFIX+"/attachment/cms/"+img_name;
//		resMap.put("img_path", res_img_parh);
		
		return resMap;
	}
	
	
	private static String getHtml(Map params) throws IOException, TemplateException{
		String page_name = (String) params.get("page_name"); 
		String page_folder = EopContext.getContext().getContextPath()+ EopSetting.IMG_SERVER_DOMAIN
								+separator+"attachment"+separator+"cms"+separator;
		
		page_folder = page_folder.replaceAll("//", "/");

		//生成html页面
		Configuration cfg = new Configuration();
		cfg.setNumberFormat("0.##");
		cfg.setServletContextForTemplateLoading(ThreadContextHolder.getHttpRequest().getSession().getServletContext(), page_folder);
		cfg.setObjectWrapper(new DefaultObjectWrapper());			
		cfg.setDefaultEncoding("UTF-8");
		cfg.setLocale(java.util.Locale.CHINA);
		cfg.setEncoding(java.util.Locale.CHINA, "UTF-8");
		Template temp = cfg.getTemplate(page_name);
		ByteOutputStream stream = new ByteOutputStream();
		Writer out = new OutputStreamWriter(stream);
		temp.process(params.get("data"), out);
		out.flush();
		String html = stream.toString();
		out.close();
		
		return html;
	}
	
	public Map toFirstLowerMap(Map tmMap){
		
		return null;
	}
	
	
	public  static <T> T getJsonParams(HttpServletRequest req,HttpServletResponse resp,Class<T> t) throws IOException{
	   String jsonReq = CmsServiceFactory.getCmsLineService().getCmsContext().getJsonReq();//FileBaseUtil.readStreamToString(req.getInputStream());
	   T obj =BeanUtils.jsonToBean(jsonReq,t);
	   return (T) obj;
	}
	
	public static String firstToLowerCase(String str){
		//首字母转小写
		Pattern p=Pattern.compile("\"[A-Z]");
        Matcher m=p.matcher(str);
        while(m.find()){
        	str=str.replace(m.group(),m.group().toLowerCase());
        }
        return str;
	}
	
	public static String firstToUpperCase(String str){
		//首字母转大写
		//json串中要转限定为键值对中键，值不转。
		//举例：{'name':'sguo'} 可以转-> {'Name':'sguo'} 不能转-> {'Name':'Sguo'}
		Pattern p=Pattern.compile("([\\{|\\,])\"[a-z]");
        Matcher m=p.matcher(str);
        while(m.find()){
        	str=str.replace(m.group(),m.group().toUpperCase());
        }
        return str;
	}
	
	
	/**
	 * 构建modual对象基本信息
	 * @return
	 */
	public static Modual getBaseModual(){
		Modual modual = new Modual();
		CmsContext cmsContext = CmsServiceFactory.getCmsLineService().getCmsContext();
		modual.setTemplete_id(cmsContext.getTpl().getTemplate_id());
		modual.setModual_name("");
		modual.setModual_code("Individual");
//		modual.setCreate_time(DBTUtil.current());
		modual.setState(CmsConst.MODUAL_STATE_0);
		modual.setSeq(CmsConst.MODUAL_SEQ_1);
		modual.setRecord_state(CmsConst.RECORD_STATE_A);
		modual.setNrecord_state(CmsConst.NRECORD_STATE_A);
		modual.setSave_state(CmsConst.SAVE_STATE_N);
//		modual.setUser_id(ManagerUtils.getUserId());
		if(null != cmsContext.getApp())
			modual.setApp_id(cmsContext.getApp().getApp_id());
		modual.setSource_from(ManagerUtils.getSourceFrom());
		return modual;
	}
	
	
	/**
	 * 构建appPage对象基本信息
	 * @return
	 */
	public static AppPage getAppPage(){
		AppPage appPage = new AppPage();
		appPage.setPage_code("Individual");
		appPage.setSource_from(ManagerUtils.getSourceFrom());
		
		return appPage;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String  jsonReq = "{\"template\":{\"ExtensionData\":null,\"AdPhone\":\"400客服电话！\",\"AdPhoto\":\"\"," +
				"\"AdPhotoId\":\"0\",\"AdPhotoTitle\":\"\",\"AdPhotoUrl\":\"http://http://\",\"AdText\":\"\"," +
				"\"AdType\":\"1\",\"AdUrl\":\"http://http://\",\"BorderStyle\":\"2\",\"Description\":\"综合商城-通用行业\"," +
				"\"Device\":\"0\",\"Header\":\"\",\"HeaderStyle\":\"0\",\"HelpTitle\":[{\"ExtensionData\":null," +
				"\"Name\":\"注册登录\"},{\"ExtensionData\":null,\"Name\":\"找回密码\"},{\"ExtensionData\":null,\"Name\":\"如何下单\"}," +
				"{\"ExtensionData\":null,\"Name\":\"单据状态\"},{\"ExtensionData\":null,\"Name\":\"快递选择\"}," +
				"{\"ExtensionData\":null,\"Name\":\"配送时间\"},{\"ExtensionData\":null,\"Name\":\"线下付款\"}," +
				"{\"ExtensionData\":null,\"Name\":\"网银支付\"},{\"ExtensionData\":null,\"Name\":\"退货换货\"}," +
				"{\"ExtensionData\":null,\"Name\":\"取消订单\"},{\"ExtensionData\":null,\"Name\":\"购物卡\"}," +
				"{\"ExtensionData\":null,\"Name\":\"折扣卡\"}],\"HelpTitleJson\":\"[{Name: \\\"注册登录\\\"}, {Name: \\\"找回密码\\\"}, {Name: \\\"如何下单\\\"}, {Name: \\\"单据状态\\\"}, {Name: \\\"快递选择\\\"}, {Name: \\\"配送时间\\\"}, {Name: \\\"线下付款\\\"}, {Name: \\\"网银支付\\\"}, {Name: \\\"退货换货\\\"}, {Name: \\\"取消订单\\\"}, {Name: \\\"购物卡\\\"}, {Name: \\\"折扣卡\\\"}]\",\"Id\":\"89903\",\"Keyword\":\"综合商城-通用行业\",\"Logo\":\"http://img.zhuyun.cn/M00/D7/C0/wKgJNFOTGheAdCv6AAAbWRiuEVc866.jpg\",\"LogoPhotoId\":\"2786629\",\"LogoType\":\"0\",\"MainStyle\":\"0\",\"MajorColor\":\"3\",\"MinorColor\":\"0\",\"Name\":\"综合商城\",\"SaveLevel\":\"2\",\"SearchKey\":\"婚纱\",\"ShowHeader\":\"true\",\"ShowHeaderAd\":\"true\",\"ShowHelp\":\"true\",\"ShowNav\":\"false\",\"ShowZhuyun\":\"true\",\"StoreId\":\"15496\",\"TemplateId\":\"3\",\"Title\":\"综合商城-通用行业\",\"SearchKeyDisplay\":[\"婚纱\"]}}";
		
		jsonReq = "{\"template\":{\"ExtensionData\":null,\"AdPhone\":\"400客服电话！\",\"AdPhoto\":\"\",\"AdPhotoId\":\"0\",\"AdPhotoTitle\":\"\",\"AdPhotoUrl\":\"http://http://\",\"AdText\":\"\",\"AdType\":\"1\",\"AdUrl\":\"http://http://\",\"BorderStyle\":\"2\",\"Description\":\"综合商城-通用行业\",\"Device\":\"0\",\"Footer\":\"<p align=\\\"center\\\" style=\\\"orphans:2;white-space:normal;widows:2;background-color:#FFFFFF;margin-top:0px;margin-bottom:0px;color:#666666;font-family:'Microsoft YaHei', SimSun, Arial;padding:0px;\\\">\n\t<span style=\\\"outline:none;text-decoration:none;color:#666666;margin:0px;padding:0px;font-family:'Microsoft YaHei';line-height:2;\\\"> </span> \n</p>\n<p align=\\\"center\\\" style=\\\"margin-top:0px;margin-bottom:0px;padding:0px;font-family:'Microsoft YaHei', SimSun, Arial;-webkit-text-size-adjust:none;color:#666666;line-height:normal;white-space:normal;background-color:#FFFFFF;\\\">\n\t<span style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei';outline:none;line-height:2;font-size:12px;\\\">关于我们 | 联系我们 | 媒体报道 | 品牌授权 | 商务合作</span> \n</p>\n<p align=\\\"center\\\" style=\\\"margin-top:0px;margin-bottom:0px;padding:0px;font-family:'Microsoft YaHei', SimSun, Arial;-webkit-text-size-adjust:none;color:#666666;line-height:normal;white-space:normal;background-color:#FFFFFF;\\\">\n\t<span style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei';outline:none;line-height:2;font-size:12px;\\\">ICP备案号： Copyright &copy; 2013&nbsp;</span><span style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei';line-height:2;\\\"><span style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei', SimSun, Arial;font-size:12px;\\\">mpoo.cn &nbsp; All Rights Reserved&nbsp;</span><a href=\\\"http://www.zhuyun.cn/\\\" target=\\\"_blank\\\" style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei', SimSun, Arial;outline:none;text-decoration:none;\\\"><span style=\\\"margin:0px;padding:0px;color:#666666;font-size:12px;\\\">筑云</span></a><span style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei', SimSun, Arial;font-size:12px;\\\">&nbsp;</span></span><span style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei';line-height:2;font-size:12px;\\\">版权所有,违者必究</span> \n</p>\n<p align=\\\"center\\\" style=\\\"margin-top:0px;margin-bottom:0px;padding:0px;font-family:'Microsoft YaHei', SimSun, Arial;-webkit-text-size-adjust:none;color:#666666;line-height:normal;white-space:normal;background-color:#FFFFFF;\\\">\n\t<img src=\\\"http://img.zhuyun.cn/M00/01/29/wKgJNFIjQWqAJKM9AAAIu06yUwg699.png\\\" style=\\\"margin:0px;padding:0px;border-style:none;vertical-align:middle;\\\" /><span style=\\\"margin:0px;padding:0px;font-size:12px;\\\">&nbsp;</span><img title=\\\"网站备案\\\" src=\\\"http://img.zhuyun.cn/M00/01/24/wKgJNFIhXSiAHPFHAAAGom5QmAk433.png\\\" height=\\\"50\\\" width=\\\"110\\\" style=\\\"margin:0px;padding:0px;border-style:none;vertical-align:middle;\\\" /><span style=\\\"margin:0px;padding:0px;font-size:12px;\\\">&nbsp;&nbsp;</span><img src=\\\"http://img.zhuyun.cn/M00/01/7F/wKgJNFIy2qyAEa3yAAAMqg2PFik097.jpg\\\" style=\\\"margin:0px;padding:0px;border-style:none;vertical-align:middle;\\\" /><span style=\\\"margin:0px;padding:0px;font-size:12px;\\\">&nbsp;</span><img title=\\\"网银\\\" src=\\\"http://img.zhuyun.cn/M00/01/24/wKgJNFIhXS-AVNvhAAAJ3K_rPvM818.png\\\" height=\\\"50\\\" width=\\\"140\\\" style=\\\"margin:0px;padding:0px;border-style:none;vertical-align:middle;\\\" /><span style=\\\"margin:0px;padding:0px;font-size:12px;\\\">&nbsp;&nbsp;</span><img title=\\\"财付通\\\" src=\\\"http://img.zhuyun.cn/M00/01/24/wKgJNFIhXQGAEFdmAAAJY5UuIgc748.png\\\" height=\\\"50\\\" width=\\\"100\\\" style=\\\"margin:0px;padding:0px;border-style:none;vertical-align:middle;\\\" /><span style=\\\"margin:0px;padding:0px;font-size:12px;\\\">&nbsp;</span><img title=\\\"360绿色网站认证\\\" src=\\\"http://img.zhuyun.cn/M00/01/24/wKgJNFIhXPyATQdDAAANboo37WU985.png\\\" height=\\\"50\\\" width=\\\"100\\\" style=\\\"margin:0px;padding:0px;border-style:none;vertical-align:middle;\\\" /> \n</p>\n<p align=\\\"center\\\" style=\\\"margin-top:0px;margin-bottom:0px;padding:0px;font-family:'Microsoft YaHei', SimSun, Arial;-webkit-text-size-adjust:none;color:#666666;line-height:normal;white-space:normal;background-color:#FFFFFF;\\\">\n\t<span style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei';line-height:2;font-size:12px;\\\">技术支持：&nbsp;</span><span style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei';outline:none;line-height:2;\\\"><span style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei', SimSun, Arial;outline:none;text-decoration:none;color:#666666;\\\"><a href=\\\"http://www.zhuyun.cn/\\\" target=\\\"_blank\\\" style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei', SimSun, Arial;outline:none;text-decoration:none;color:#2A586F;\\\"><span style=\\\"color:#666666;font-size:12px;\\\">筑云独立网店系统提供商</span></a></span></span> \n</p>\n<p align=\\\"center\\\" style=\\\"orphans:2;white-space:normal;widows:2;background-color:#FFFFFF;margin-top:0px;margin-bottom:0px;color:#666666;font-family:'Microsoft YaHei', SimSun, Arial;padding:0px;\\\">\n\t<span style=\\\"margin:0px;padding:0px;font-family:'Microsoft YaHei';line-height:2;\\\"></span><span style=\\\"outline:none;margin:0px;padding:0px;font-family:'Microsoft YaHei';line-height:2;\\\"><a href=\\\"http://www.zhuyun.cn/\\\" target=\\\"_blank\\\" style=\\\"outline:none;text-decoration:none;color:#2A586F;margin:0px;padding:0px;\\\"></a></span> \n</p>\",\"Header\":\"\",\"HeaderStyle\":\"0\",\"HelpTitle\":[{\"ExtensionData\":null,\"Name\":\"注册登录\"},{\"ExtensionData\":null,\"Name\":\"找回密码\"},{\"ExtensionData\":null,\"Name\":\"如何下单\"},{\"ExtensionData\":null,\"Name\":\"单据状态\"},{\"ExtensionData\":null,\"Name\":\"快递选择\"},{\"ExtensionData\":null,\"Name\":\"配送时间\"},{\"ExtensionData\":null,\"Name\":\"线下付款\"},{\"ExtensionData\":null,\"Name\":\"网银支付\"},{\"ExtensionData\":null,\"Name\":\"退货换货\"},{\"ExtensionData\":null,\"Name\":\"取消订单\"},{\"ExtensionData\":null,\"Name\":\"购物卡\"},{\"ExtensionData\":null,\"Name\":\"折扣卡\"}],\"HelpTitleJson\":\"[{Name: \\\"注册登录\\\"}, {Name: \\\"找回密码\\\"}, {Name: \\\"如何下单\\\"}, {Name: \\\"单据状态\\\"}, {Name: \\\"快递选择\\\"}, {Name: \\\"配送时间\\\"}, {Name: \\\"线下付款\\\"}, {Name: \\\"网银支付\\\"}, {Name: \\\"退货换货\\\"}, {Name: \\\"取消订单\\\"}, {Name: \\\"购物卡\\\"}, {Name: \\\"折扣卡\\\"}]\",\"Id\":\"89903\",\"Keyword\":\"综合商城-通用行业\",\"Logo\":\"http://img.zhuyun.cn/M00/D7/C0/wKgJNFOTGheAdCv6AAAbWRiuEVc866.jpg\",\"LogoPhotoId\":\"2786629\",\"LogoType\":\"0\",\"MainStyle\":\"0\",\"MajorColor\":\"2\",\"MinorColor\":\"0\",\"Name\":\"综合商城\",\"SaveLevel\":\"2\",\"SearchKey\":\"婚纱\",\"ShowHeader\":\"true\",\"ShowHeaderAd\":\"true\",\"ShowHelp\":\"true\",\"ShowNav\":\"false\",\"ShowZhuyun\":\"true\",\"StoreId\":\"15496\",\"TemplateId\":\"3\",\"Title\":\"综合商城-通用行业\",\"SearchKeyDisplay\":[\"婚纱\"]}}";
		
		String temp = "font-family:'Microsoft YaHei', font-family:'Microsoft YaHei'";
		temp = temp.replace("'", "\\'");
		
		jsonReq = "{\"@type\":\"com.ztesoft.net.cmsline.paramsj.dynamic.resp.GetDynamicPluginResp\",\"error\":\"\",\"message\":{\"CheckType\":0,\"ExtensionData\":null,\"Id\":834,\"InputType\":0,\"ShowTitle\":3,\"StoreId\":17044,\"Style\":0,\"Title\":\"动态插件\"},\"success\":true}";
		
		GetDynamicPluginResp storeLayOutReq = BeanUtils.jsonToBean(jsonReq, GetDynamicPluginResp.class);
		System.out.println("11111111");
		
		GetDynamicPluginResp resp = new GetDynamicPluginResp();
		resp.setMessage(null);
		String ssss = BeanUtils.beanToJson(resp);
		
		System.out.println("11111111");
	}
}

package com.mage.platform.framework.web.view;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.view.freemarker.FreeMarkerView;

import com.mage.platform.framework.MageFactory;

/**
 * 重写freemark视图解析类
 * @author pzh
 * @date 2016年6月3日 下午1:45:12
 */
public class MageFreeMarkerView extends FreeMarkerView {
	
	@Override
	protected void exposeHelpers(Map<String, Object> model,
            HttpServletRequest request) throws Exception {
        model.put("context_path", request.getContextPath());
        super.exposeHelpers(model, request);
        //界面处理
        MageFactory.getWebProcessor().process(model, request);
    }
	
}

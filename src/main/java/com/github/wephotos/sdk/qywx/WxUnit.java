package com.github.wephotos.sdk.qywx;

import com.github.wephotos.sdk.qywx.exception.ErrcodeException;
import com.github.wephotos.sdk.qywx.exception.HttpSessionInvalidException;
import com.github.wephotos.sdk.qywx.msg.send.Messager;
import com.github.wephotos.sdk.qywx.msg.send.NewsMessage;
import com.github.wephotos.sdk.qywx.utils.WXUtils;

/**
 * 测试
 * @author Administrator
 *
 */
public class WxUnit {

	public static void main(String[] args) throws ErrcodeException, HttpSessionInvalidException {
		WXUtils.initConfig("classpath:com/github/wephotos/sdk/qywx/config/weixin-config.xml");
		
		NewsMessage news = new NewsMessage();
		news.setTouser("110");
		news.add("百度", "百度一下，你就知道", "https://www.baidu.com", "https://www.baidu.com/img/bd_logo1.png");
		Messager.send(news);
	}
}

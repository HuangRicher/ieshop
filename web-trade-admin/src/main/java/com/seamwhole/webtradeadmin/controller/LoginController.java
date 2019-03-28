package com.seamwhole.webtradeadmin.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.seamwhole.webtradeadmin.shiro.ShiroUtils;
import com.seamwhole.webtradeadmin.util.ResponseObject;
import com.seamwhole.webtradeadmin.util.StringDataUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 登录相关
 */
@Controller
public class LoginController {

    @Autowired
    private Producer producer;

    @RequestMapping("/")
    public String toLogin(){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (username == null){
            return "login";
        }else{
            return "redirect:/index";
        }
    }


    @RequestMapping("/captcha.jpg")
    public void captcha(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        //request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    /**
     * 登录
     */
    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseObject login(String username, String password, String captcha,HttpServletRequest request) throws IOException {
        Object kaptcha =ShiroUtils.getKaptcha(Constants.KAPTCHA_SESSION_KEY);
        if(null == kaptcha){
            return ResponseObject.error("验证码已失效");
        }
        if (!captcha.equalsIgnoreCase((String)kaptcha)) {
            return ResponseObject.error("验证码不正确");
        }

        try {
            Subject subject = ShiroUtils.getSubject();
            //sha256加密
            password = StringDataUtils.md5(password,username);
            UsernamePasswordToken token = new UsernamePasswordToken(username, password,false);
            subject.login(token);
        } catch (UnknownAccountException e) {
            return ResponseObject.error(e.getMessage());
        } catch (IncorrectCredentialsException e) {
            return ResponseObject.error(e.getMessage());
        } catch (LockedAccountException e) {
            return ResponseObject.error(e.getMessage());
        } catch (AuthenticationException e) {
            return ResponseObject.error("账户验证失败");
        }

        return ResponseObject.ok();
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout() {
        ShiroUtils.logout();
        return "redirect:/";
    }

}

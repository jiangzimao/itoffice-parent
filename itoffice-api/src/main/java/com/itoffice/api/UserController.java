/**   
 * Copyright © 2015 itofficewb. All rights reserved.
 * 
 * @Title: UserController.java 
 * @Prject: itoffice-api
 * @Package: com.itoffice.api 
 * @author: jiangzimao@126.com   
 * @date: 2015年8月2日 下午9:09:00 
 * @version: V1.0   
 */
package com.itoffice.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.itoffice.persistence.domain.User;

/** 
 * @ClassName: UserController 
 * @author: jiangzimao@126.com
 * @date: 2015年8月2日 下午9:09:00  
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value="save",method=RequestMethod.POST)
    public User save(@RequestParam("file") CommonsMultipartFile files, @ModelAttribute User user){
        return user;
    }
    
    @RequestMapping(value="getUserInfo",method=RequestMethod.GET)
    public User getUserInfo(String userId){
        User user = new User();
        user.setUserId(userId);
        return user;
    }
    
}

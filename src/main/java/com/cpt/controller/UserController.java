package com.cpt.controller;

import com.cpt.entity.User;
import com.cpt.from.UserFrom;
import com.cpt.result.Result;
import com.cpt.service.UserService;
import com.github.pagehelper.PageInfo;


import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findUser")
    public Result getUserList(@RequestParam("username") String username, @RequestParam("password") String password) {


        //查找用户
        User user = userService.findUsername(username);

        if (user != null && user.getUsername().equals(username) && user.getPassword().equals(password)) {

            return Result.success(200, "success", user);
        } else {

            return Result.fail(-1, "fail");

        }
    }




    @PostMapping("/getUserAll")
    public Result getUserAllList() {

        List<User> userList = userService.selectUserList();
        return Result.success(200, "success", userList);

    }


    @PostMapping("/getAll")
    public Result getTest(@RequestBody UserFrom from) throws ExecutionException, InterruptedException {


        AtomicReference<PageInfo<User>> list= new AtomicReference<>(new PageInfo<User>());

        long start=System.currentTimeMillis();

        ThreadPoolExecutor executor=new ThreadPoolExecutor(
                50,1000,15, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(5),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        //异步完成
        CompletableFuture<Void> goodsFuture=CompletableFuture.runAsync(()->{

           list.set(userService.pageUserList(from));

        },executor);

     //等待完成
       CompletableFuture.allOf(goodsFuture).get();

       long end=System.currentTimeMillis();

       System.out.println(end-start);

        return Result.success(200,"success",list);
    }
    
}

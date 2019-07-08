package com.example.gateway.filter;

import com.example.gateway.common.HttpResp;
import com.example.gateway.common.JwtUtil;
import com.example.gateway.dao.UserDao;
import com.example.gateway.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

/**
 * @author qiwenshuai
 * @note 目前只记录了request方式为POST请求的方式
 * @since 19-5-16 17:29 by jdk 1.8
 */
@Component
class TokenFiltert implements GlobalFilter, Ordered {


    @Autowired
    UserDao userDao;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest originalRequest = exchange.getRequest();
        URI originalRequestUrl = originalRequest.getURI();
        HttpHeaders headers = originalRequest.getHeaders();

//        if(headers.containsKey("ticket")){
//           String ticket= headers.get("ticket").get(0);
//            HttpResp resp =userDao.getUserByUserId(JwtUtil.getUserId(ticket));
//            ObjectMapper mapper = new ObjectMapper();
//            User user=mapper.convertValue(resp.getContent(),User.class);
////            User user = (User)resp.getContent();
//            System.out.println(user);
//        }else{
//
//        }
//        List<String> ticket = headers.get("ticket");
//        System.out.println(ticket.get(0));


        //只记录http的请求
        String scheme = originalRequestUrl.getScheme();
        if ((!"http".equals(scheme) && !"https".equals(scheme))) {
            return chain.filter(exchange);
        }
        //这是我要打印的log-StringBuilder
        StringBuilder logbuilder = new StringBuilder();
        //我自己的log实体
        // 返回解码
//        RecorderServerHttpResponseDecorator response = new RecorderServerHttpResponseDecorator(exchange.getResponse(), logNotes, filterService);
        //请求解码
//        RecorderServerHttpRequestDecorator recorderServerHttpRequestDecorator = new RecorderServerHttpRequestDecorator(exchange.getRequest());
        //增加过滤拦截吧
//        ServerWebExchange ex = exchange.mutate()
//                .request(recorderServerHttpRequestDecorator)
//                .response(response)
//                .build();
        //  观察者模式 打印一下请求log
        // 这里可以在 配置文件中我进行配置
//        if (logger.isDebugEnabled()) {
//        response.beforeCommit(() -> Mono.defer(() -> printLog(logbuilder, response)));
////        }
//        return recorderOriginalRequest(logbuilder, ex, logNotes)
//                .then(chain.filter(ex))
//                .then();
//        return null;
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

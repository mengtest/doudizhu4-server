package com.randioo.doudizhu_server.http.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.randioo.doudizhu_server.http.action.RaceListAction;
import com.randioo.http_server.server.LiteServlet;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

@Service
public class RaceListServlet extends LiteServlet {
	
	@Autowired
	private RaceListAction raceListAction ;
	@Override
	protected void doGet(HttpExchange exchange) {
		Headers responseHeaders = exchange.getResponseHeaders();
		try {
			exchange.sendResponseHeaders(200, 0);
			responseHeaders.set("Content-Type", "text/plain");
		} catch (IOException e) {
			e.printStackTrace();
		}
		OutputStream outputStream = exchange.getResponseBody();
		try (BufferedOutputStream bos = new BufferedOutputStream(outputStream)) {
			try {
				System.out.println(raceListAction);
//				cancelRaceAction = SpringContext.getBean("cancelRaceAction");
				String raceList = raceListAction.getList(exchange, null);
				bos.write(raceList.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
				bos.write("kick failed".getBytes());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpExchange liteRequest) {

	}
}

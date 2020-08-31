package com.quick.yjk.order;

import java.util.List;
import java.util.Map;

import com.quick.yjk.vo.OrderVo;

public interface OrderService {
	
	public int goOrder(OrderVo orderVo);
	
	public List<OrderVo> orderList(Map<String,Object> paramMap);
	
	public int updateOrder(OrderVo orderVo);
	
}

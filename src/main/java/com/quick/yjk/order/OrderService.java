package com.quick.yjk.order;

import java.util.List;

import com.quick.yjk.vo.OrderVo;

public interface OrderService {
	
	public int goOrder(OrderVo orderVo);
	
	public List<OrderVo> orderList();
	
	public int updateOrder(OrderVo orderVo);
	
}

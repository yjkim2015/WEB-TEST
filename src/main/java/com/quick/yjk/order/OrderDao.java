package com.quick.yjk.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.quick.yjk.vo.OrderVo;

@Repository
public interface OrderDao {

	public int goOrder(OrderVo orderVo);
	
	public List<OrderVo> orderList();
	
	public int updateOrder(OrderVo orderVo);
}

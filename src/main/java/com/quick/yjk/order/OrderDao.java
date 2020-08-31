package com.quick.yjk.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.quick.yjk.vo.OrderVo;

@Repository
public interface OrderDao {

	public int goOrder(OrderVo orderVo);
	
	public List<OrderVo> orderList(Map<String,Object> paramMap);
	
	public int updateOrder(OrderVo orderVo);
}

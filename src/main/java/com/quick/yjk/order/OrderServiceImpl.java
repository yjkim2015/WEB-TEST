package com.quick.yjk.order;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quick.yjk.vo.OrderVo;

@Service
public class OrderServiceImpl implements OrderService {

	private transient final OrderDao orderDao;
	
	@Autowired
	public OrderServiceImpl(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@Override
	public int goOrder(OrderVo orderVo) {
		return orderDao.goOrder(orderVo);
	}

	@Override
	public List<OrderVo> orderList(Map<String,Object> paramMap) {
		return orderDao.orderList(paramMap);
	}

	@Override
	public int updateOrder(OrderVo orderVo) {
		return orderDao.updateOrder(orderVo);
	}

}

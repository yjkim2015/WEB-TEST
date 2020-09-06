package com.quick.yjk.warehouse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WarehouseController {

	
	@RequestMapping(value="/warehouse/orderList")
	public String warehouseOrderList() {
		return "/warehouse/orderList";	
	}
}

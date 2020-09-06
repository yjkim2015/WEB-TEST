package com.quick.yjk.order;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quick.yjk.accounting.user.UserService;
import com.quick.yjk.common.component.CommonController;
import com.quick.yjk.common.constants.PushType;
import com.quick.yjk.common.push.PushService;
import com.quick.yjk.vo.OrderVo;
import com.quick.yjk.vo.PushVo;
import com.quick.yjk.vo.ResultVo;
import com.quick.yjk.vo.UserVo;

@Controller
public class OrderController extends CommonController {

	private transient final OrderService orderService;
	
	private transient final PushService pushService;
	
	private transient final UserService userService;

	@Autowired
	public OrderController(OrderService orderService, PushService pushService, UserService userService) {
		super();
		this.orderService = orderService;
		this.pushService = pushService;
		this.userService = userService;
	}
	
	@ResponseBody
	@RequestMapping(value="/order", method = RequestMethod.POST)
	public ResponseEntity<ResultVo> order(@RequestBody final OrderVo orderVo) {
		
		ResultVo result = null;
		
		try {
			result = new ResultVo(orderService.goOrder(orderVo),HttpStatus.OK);
			
			UserVo userVo = new UserVo();
			userVo.setLoginId(orderVo.getLoginId());
			userVo = userService.selectOneUser(userVo);
			orderVo.setBrandName(userVo.getBrandName());
			PushVo pushVo = new PushVo();
			pushVo.setPayload(orderVo);
			pushVo.setType(PushType.ORDER);
			pushService.pushTo("root", pushVo);
		}
		catch (Exception e) {
			result = new ResultVo(HttpStatus.INTERNAL_SERVER_ERROR);
			result.setReason(e.getMessage());
		}		
		return result.build();
	}
	
	@ResponseBody
	@RequestMapping(value="/orderList", method = RequestMethod.GET)
	public ResponseEntity<ResultVo> orderList(@RequestParam Map<String,Object> paramMap) {
		ResultVo result = null;
		try {
			result = new ResultVo(orderService.orderList(paramMap),HttpStatus.OK);
		}
		catch (Exception e) {
			result = new ResultVo(HttpStatus.INTERNAL_SERVER_ERROR);
			result.setReason(e.getMessage());
		}		
		return result.build();
	}
	
	@ResponseBody
	@RequestMapping(value="/updateOrder", method = RequestMethod.POST)
	public ResponseEntity<ResultVo> orderUpdate(@RequestBody  OrderVo orderVo) {
		ResultVo result = null;
		
		try {
			result = new ResultVo(orderService.updateOrder(orderVo),HttpStatus.OK);
			UserVo user = new UserVo();
			if ( orderVo.getLoginId().equals("root")) {
				orderVo.setLoginId(null);
				user.setDriverNum(orderVo.getDriverNum());
			}
			
			user =userService.selectOneUser(user);
			PushVo pushVo = new PushVo();
			pushVo.setPayload(orderVo);
			pushVo.setType(PushType.APPROVE_ORDER);
			pushService.pushTo(user.getLoginId(), pushVo);

		}
		catch (Exception e) {
			result = new ResultVo(HttpStatus.INTERNAL_SERVER_ERROR);
			result.setReason(e.getMessage());
		}		
		return result.build();
	}
	
}

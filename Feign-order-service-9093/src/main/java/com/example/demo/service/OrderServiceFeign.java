package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.client.CustomerClient;
import com.example.demo.client.ProductClient;
import com.example.demo.dao.OrderDao;
import com.example.demo.model.dto.ItemDto;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.po.Customer;
import com.example.demo.model.po.Item;
import com.example.demo.model.po.Order;
import com.example.demo.model.po.Product;

@Service
public class OrderServiceFeign {
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CustomerClient customerClient;
	
	@Autowired
	private ProductClient productClient;
	
	// 將order 從 po轉dto
	private OrderDto converToDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setOrderDate(order.getOrderDate());
		// 到http://localhost:9092/customers/1 取得客戶資料
		// 到FEIGN-CUSTOMER-SERVICE-9092 下的 /customers/1 取得客戶資料
		Customer customer = customerClient.getCustomerById(order.getCustomerId()).getData();
		orderDto.setCustomer(customer);
		// 尋訪每一項item
		for(Item item : order.getItems()) {
			ItemDto itemDto = convertToDto(item);
			orderDto.getItemDtos().add(itemDto);
		}
		
		return orderDto;
	}
	
	// 將item 從 po 轉 dto
	private ItemDto convertToDto(Item item) {
		ItemDto itemDto = new ItemDto();
		itemDto.setId(item.getId());
		itemDto.setQuantity(item.getQuantity());
		itemDto.setSubtotal(item.getSubtotal());
		// 到http://localhost:9092/customers/1 取得客戶資料
		// 到FEIGN-PRODUCT-SERVICE-9091 下的 /products/1 取得客戶資料
		Product product = productClient.getProductById(item.getProductId()).getData();
		itemDto.setProduct(product);
		return itemDto;
	}
	
	// 查詢單筆訂單
	public OrderDto getOrderById(Integer orderId) {
		Order order = orderDao.getOrderById(orderId);
		// po轉dto
		OrderDto orderDto = converToDto(order);
		return orderDto;
	}
}

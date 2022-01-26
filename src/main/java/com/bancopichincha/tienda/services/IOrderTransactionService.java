package com.bancopichincha.tienda.services;

import com.bancopichincha.tienda.dto.OrderTransactionRequest;

public interface IOrderTransactionService {

	public void makeOrder(OrderTransactionRequest orderTransactionRequest);
}

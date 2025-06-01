import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';

const Container = styled.div`
  padding: 2rem;
`;

const OrderCard = styled.div`
  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 1rem;
  margin-bottom: 1rem;
`;

export default function OrderListPage() {
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    axios.get('/api/orders')
      .then(res => setOrders(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <Container>
      <h2>Danh sách đơn gọi món</h2>
      {orders.map(order => (
        <OrderCard key={order.id}>
          <p><strong>Mã đơn:</strong> {order.id}</p>
          <p><strong>Khách hàng:</strong> {order.customerName}</p>
          <p><strong>Thời gian:</strong> {order.time}</p>
          <p><strong>Món ăn:</strong> {order.items.join(', ')}</p>
          <p><strong>Tổng tiền:</strong> {order.total} VND</p>
          <p><strong>Trạng thái:</strong> {order.status}</p>
        </OrderCard>
      ))}
    </Container>
  );
}
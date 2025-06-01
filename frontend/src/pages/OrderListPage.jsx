import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';

const Container = styled.div`
  padding: 2rem;
`;

const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  margin-top: 2rem;
`;

const Th = styled.th`
  text-align: left;
  padding: 0.75rem;
  background-color: #f5f5f5;
  border-bottom: 2px solid #ccc;
`;

const Td = styled.td`
  padding: 0.75rem;
  border-bottom: 1px solid #e0e0e0;
  vertical-align: top;
`;

const StatusBadge = styled.span`
  padding: 0.3rem 0.6rem;
  border-radius: 10px;
  color: white;
  font-weight: bold;
  background-color: ${props => {
    switch (props.status) {
      case 'PENDING': return '#f1c40f';
      case 'FULFILLED': return '#2ecc71';
      case 'CANCELLED': return '#e74c3c';
      default: return '#7f8c8d';
    }
  }};
`;

export default function OrderListPage() {
  const [orders, setOrders] = useState([]);
  const [menuItems, setMenuItems] = useState([]);

  useEffect(() => {
    const token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NCwic3ViIjoiYWRtaW5ubkBnbWFpbC5jb20iLCJyb2xlIjoiTUFOQUdFUiIsImlhdCI6MTc0ODc2OTE5MSwiZXhwIjoxNzQ4NzcyNzkxfQ.Zms98eu5iXhagOqqxgUvLlXd6h3PrQTo9_Rbcw7VxiM";

    axios.get('/api/orders', { headers: { Authorization: token } })
      .then(res => setOrders(res.data))
      .catch(err => console.error(err));

    axios.get('/api/menu-items', { headers: { Authorization: token } })
      .then(res => setMenuItems(res.data))
      .catch(err => console.error(err));
  }, []);

  const getMenuName = (id) => menuItems.find(m => m.id === id)?.name || `Món #${id}`;
  const getUserName = (id) => `User #${id}`;

  return (
    <Container>
      <h2>Danh sách đơn gọi món</h2>
      <Table>
        <thead>
          <tr>
            <Th>Mã đơn</Th>
            <Th>Khách hàng</Th>
            <Th>Thời gian</Th>
            <Th>Món ăn</Th>
            <Th>Tổng tiền</Th>
            <Th>Trạng thái</Th>
          </tr>
        </thead>
        <tbody>
          {orders.map(order => (
            <tr key={order.id}>
              <Td>{order.id}</Td>
              <Td>{getUserName(order.userId)}</Td>
              <Td>{order.orderTime ? new Date(order.orderTime).toLocaleString() : 'Không có'}</Td>
              <Td>
                {order.items.map((item, index) => (
                  <div key={index}>
                    {getMenuName(item.menuItemId)}
                  </div>
                ))}
              </Td>
              <Td>{order.items.reduce((total, item) => total + item.quantity * item.price, 0)}₫</Td>
              <Td><StatusBadge status={order.status}>{order.status}</StatusBadge></Td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}

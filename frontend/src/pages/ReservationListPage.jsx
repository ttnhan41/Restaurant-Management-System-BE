import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';

const Container = styled.div`
  padding: 2rem;
`;

const ReservationCard = styled.div`
  border: 1px solid #ccc;
  border-radius: 10px;
  padding: 1rem;
  margin-bottom: 1rem;
`;

const Button = styled.button`
  margin-right: 1rem;
`;

export default function ReservationListPage() {
  const [reservations, setReservations] = useState([]);

  useEffect(() => {
    axios.get('/api/reservations')
      .then(res => setReservations(res.data))
      .catch(err => console.error(err));
  }, []);

  const handleAction = (id, action) => {
    axios.put(`/api/reservations/${id}/${action}`)
      .then(() => {
        setReservations(prev => prev.map(r =>
          r.id === id ? { ...r, status: action === 'confirm' ? 'Đã xác nhận' : 'Đã hủy' } : r
        ));
      });
  };

  return (
    <Container>
      <h2>Danh sách yêu cầu đặt bàn</h2>
      {reservations.map(resv => (
        <ReservationCard key={resv.id}>
          <p><strong>Khách hàng:</strong> {resv.customerName}</p>
          <p><strong>Số bàn:</strong> {resv.tableNumber}</p>
          <p><strong>Số ghế:</strong> {resv.seats}</p>
          <p><strong>Thời gian:</strong> {resv.time}</p>
          <p><strong>Ghi chú:</strong> {resv.note || 'Không có'}</p>
          <p><strong>Trạng thái:</strong> {resv.status}</p>
          <Button onClick={() => handleAction(resv.id, 'confirm')}>Xác nhận</Button>
          <Button onClick={() => handleAction(resv.id, 'cancel')}>Hủy</Button>
        </ReservationCard>
      ))}
    </Container>
  );
}

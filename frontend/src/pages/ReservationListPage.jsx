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
    switch (props.$status) {
      case 'PENDING': return '#f1c40f';
      case 'CONFIRMED': return '#2ecc71';
      case 'CANCELLED': return '#e74c3c';
      default: return '#7f8c8d';
    }
  }};
`;

const ActionButton = styled.button`
  margin-right: 0.5rem;
  padding: 0.4rem 0.8rem;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;

  &:hover {
    background-color: #2980b9;
  }
`;

export default function ReservationListPage() {
  const [reservations, setReservations] = useState([]);

  const token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NCwic3ViIjoiYWRtaW5ubkBnbWFpbC5jb20iLCJyb2xlIjoiTUFOQUdFUiIsImlhdCI6MTc0ODc2OTE5MSwiZXhwIjoxNzQ4NzcyNzkxfQ.Zms98eu5iXhagOqqxgUvLlXd6h3PrQTo9_Rbcw7VxiM";

  useEffect(() => {
    axios.get('/api/reservations', {
      headers: { Authorization: token }
    })
    .then(res => {
      console.log('Response:', res.data);  // log để kiểm tra ghi chú
      setReservations(res.data);
    })
    .catch(err => console.error(err));
  }, []);

  const handleAction = (id, action) => {
    const status = action === 'confirm' ? 'CONFIRMED' : 'CANCELLED';
    axios.put('/api/reservations/updateStatus', {
      id,
      status
    }, {
      headers: { Authorization: token }
    })
    .then(() => {
      setReservations(prev =>
        prev.map(r =>
          r.id === id ? { ...r, status } : r
        )
      );
    })
    .catch(err => console.error(err));
  };

  return (
    <Container>
      <h2>Danh sách yêu cầu đặt bàn</h2>
      <Table>
        <thead>
          <tr>
            <Th>Khách hàng</Th>
            <Th>Số bàn</Th>
            <Th>Số ghế</Th>
            <Th>Thời gian</Th>
            <Th>Ghi chú</Th>
            <Th>Trạng thái</Th>
            <Th>Hành động</Th>
          </tr>
        </thead>
        <tbody>
          {reservations.map(resv => (
            <tr key={resv.id}>
              <Td>{resv.customerName || `User #${resv.userId}`}</Td>
              <Td>{resv.tableId ?? 'Không có'}</Td>
              <Td>{resv.guestsCount ?? 'Không có'}</Td>
              <Td>
                {resv.reservationTime
                  ? new Date(resv.reservationTime).toLocaleString('vi-VN', {
                      timeZone: 'Asia/Ho_Chi_Minh'
                    })
                  : 'Không có'}
              </Td>
              <Td>
                {resv.note !== null && resv.note !== undefined && resv.note.trim() !== ''
                  ? resv.note
                  : 'Không có'}
              </Td>
              <Td>
                <StatusBadge $status={resv.status}>{resv.status}</StatusBadge>
              </Td>
              <Td>
                <ActionButton onClick={() => handleAction(resv.id, 'confirm')}>Xác nhận</ActionButton>
                <ActionButton onClick={() => handleAction(resv.id, 'cancel')}>Hủy</ActionButton>
              </Td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Container>
  );
}

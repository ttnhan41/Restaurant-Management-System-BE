// src/pages/ReservationPage.jsx
import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';

const Wrapper = styled.div`
  max-width: 1100px;
  margin: 0 auto;
  padding: 2rem;
`;

const Nav = styled.nav`
  margin-bottom: 2rem;
  a {
    margin-right: 1.5rem;
    color: #007bff;
    text-decoration: none;
    font-weight: 500;
    &:hover {
      text-decoration: underline;
    }
  }
`;

const Title = styled.h2`
  margin-bottom: 1rem;
`;

const Container = styled.div`
  display: flex;
  gap: 2rem;
  @media (max-width: 768px) {
    flex-direction: column;
  }
`;

const LeftPane = styled.div`
  flex: 1;
`;

const TableElement = styled.table`
  width: 100%;
  border-collapse: collapse;
`;

const Th = styled.th`
  background-color: #28a745;
  color: #fff;
  padding: 0.75rem;
  border: 1px solid #ccc;
  text-align: left;
`;

const Td = styled.td`
  padding: 0.6rem 0.8rem;
  border: 1px solid #ccc;
`;

const TableRow = styled.tr`
  background-color: ${props => (props.available ? '#e8f7ec' : '#fde7e7')};
  &:hover {
    background-color: ${props => (props.available ? '#d0eed8' : '#fbcfcf')};
  }
`;

const ActionButton = styled.button`
  padding: 0.4rem 0.9rem;
  background-color: #007bff;
  color: white;
  border: none;
  border-radius: 4px;
  font-size: 0.9rem;
  cursor: pointer;
  transition: background-color 0.2s;
  &:hover {
    background-color: #0056b3;
  }
`;

const RightPane = styled.div`
  flex: 2;
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(130px, 1fr));
  gap: 1rem;
`;

const TableBox = styled.div`
  min-width: 130px;
  height: 130px;
  border-radius: 12px;
  background-color: ${props => (props.available ? '#d4edda' : '#f8d7da')};
  border: 2px solid ${props => (props.available ? '#28a745' : '#dc3545')};
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-weight: 600;
  text-align: center;
  box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
  transition: transform 0.2s;
  &:hover {
    transform: translateY(-3px);
  }
`;

export default function ReservationPage() {
  const [tables, setTables] = useState([]);

  
  const token =
    'Bearer eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NCwic3ViIjoiYWRtaW5ubkBnbWFpbC5jb20iLCJyb2xlIjoiTUFOQUdFUiIsImlhdCI6MTc0ODc4NDA2NSwiZXhwIjoxNzQ4Nzg3NjY1fQ.WZnmG-icsQ6zkQXUCjytkMP0TROMWqsTEM07L18m5WY';

  useEffect(() => {
    axios
      .get('/api/tables', { headers: { Authorization: token } })
      .then(res => {
        console.log('GET /api/tables →', res.data);
        setTables(res.data);
      })
      .catch(err => console.error('Lỗi lấy bảng:', err));
  }, []);

  const toggleStatus = (id, currentStatus) => {
    const table = tables.find(t => t.id === id);
    axios.put(`/api/tables/${id}`, {
      tableNumber: table.tableNumber,
      seats: table.seats,
      available: !currentStatus
    }, {
      headers: { Authorization: token }
    })
    .then(() => {
      setTables(prev =>
        prev.map(t => t.id === id ? { ...t, available: !currentStatus } : t)
      );
    })
    .catch(err => console.error('Lỗi đổi trạng thái bàn:', err));
  };


  return (
    <Wrapper>
      <Title>Danh sách bàn ăn</Title>
      <Container>
        <LeftPane>
          <TableElement>
            <thead>
              <tr>
                <Th>ID</Th>
                <Th>Số bàn</Th>
                <Th>Số ghế</Th>
                <Th>Trạng thái</Th>
                <Th>Hành động</Th>
              </tr>
            </thead>
            <tbody>
              {tables.map(table => (
                <TableRow key={table.id} available={table.available}>
                  <Td>{table.id}</Td>
                  <Td>{table.tableNumber}</Td>
                  <Td>{table.seats}</Td>
                  <Td>{table.available ? 'Trống' : 'Đã đặt'}</Td>
                  <Td>
                    <ActionButton
                      onClick={() => toggleStatus(table.id, table.available)}
                    >
                      Chuyển trạng thái
                    </ActionButton>
                  </Td>
                </TableRow>
              ))}
            </tbody>
          </TableElement>
        </LeftPane>

        <RightPane>
          {tables.map(table => (
            <TableBox key={table.id} available={table.available}>
              <div>Bàn #{table.tableNumber}</div>
              <div>{table.seats} ghế</div>
              <div>{table.available ? '✔ Trống' : '❌ Đã đặt'}</div>
            </TableBox>
          ))}
        </RightPane>
      </Container>
    </Wrapper>
  );
}

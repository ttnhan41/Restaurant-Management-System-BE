// src/pages/ManagerDashboardPage.jsx
import React, { useEffect, useState } from 'react';
import styled from 'styled-components';
import axios from 'axios';

const Wrapper = styled.div`
  padding: 2rem;
`;

const Title = styled.h2`
  margin-bottom: 1.5rem;
`;

const ButtonGroup = styled.div`
  margin-bottom: 1rem;
  button {
    margin-right: 1rem;
    padding: 0.5rem 1rem;
    font-weight: bold;
    border: none;
    border-radius: 5px;
    cursor: pointer;
  }
  .block { background-color: black; color: white; }
  .unblock { background-color: #28a745; color: white; }
  .delete { background-color: #dc3545; color: white; }
`;

const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
`;

const Th = styled.th`
  background: #17a2b8;
  color: white;
  padding: 0.75rem;
  border: 1px solid #ccc;
`;

const Td = styled.td`
  padding: 0.7rem;
  border: 1px solid #ccc;
  text-align: center;
`;

export default function ManagerDashboardPage() {
  const [users, setUsers] = useState([]);

  const token = 'Bearer eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NCwic3ViIjoiYWRtaW5ubkBnbWFpbC5jb20iLCJyb2xlIjoiTUFOQUdFUiIsImlhdCI6MTc0ODc4NDA2NSwiZXhwIjoxNzQ4Nzg3NjY1fQ.WZnmG-icsQ6zkQXUCjytkMP0TROMWqsTEM07L18m5WY';

  useEffect(() => {
    axios.get('/api/users', {
      headers: { Authorization: token }
    })
      .then(res => {
        const managers = res.data.filter(u => u.role === 'MANAGER');
        setUsers(managers);
      })
      .catch(err => console.error('Lỗi khi tải danh sách:', err));
  }, []);


  return (
    <Wrapper>
      <Title>Manage Restaurant</Title>
      <ButtonGroup>
        <button className="block">Block</button>
        <button className="unblock">Unblock</button>
        <button className="delete">Delete</button>
      </ButtonGroup>

      <Table>
        <thead>
          <tr>
            <Th>S.No</Th>
            <Th>Email</Th>
            <Th>Role</Th>
            <Th>Actions</Th>
          </tr>
        </thead>
        <tbody>
          {users.map((u, index) => (
            <tr key={u.id}>
              <Td>{index + 1}</Td>
              <Td>{u.email}</Td>
              <Td>{u.role}</Td>
              <Td>
                <button className="block" style={{ fontSize: '0.75rem', marginRight: '0.5rem' }}>✖</button>
                <button className="unblock" style={{ fontSize: '0.75rem', marginRight: '0.5rem' }}>✔</button>
                <button className="delete" style={{ fontSize: '0.75rem' }}>🗑️</button>
              </Td>
            </tr>
          ))}
        </tbody>
      </Table>
    </Wrapper>
  );
}

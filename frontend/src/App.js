import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import OrderListPage from './pages/OrderListPage';
import ReservationListPage from './pages/ReservationListPage';

function App() {
  return (
    <Router>
      <nav style={{ padding: '1rem', backgroundColor: '#f0f0f0' }}>
        <Link to="/orders" style={{ marginRight: '1rem' }}>Đơn gọi món</Link>
        <Link to="/reservations">Yêu cầu đặt bàn</Link>
      </nav>
      <Routes>
        <Route path="/orders" element={<OrderListPage />} />
        <Route path="/reservations" element={<ReservationListPage />} />
      </Routes>
    </Router>
  );
}

export default App;

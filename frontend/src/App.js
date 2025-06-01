// src/App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import OrderListPage from './pages/OrderListPage';
import ReservationPage from './pages/ReservationPage';
import ManagerDashboardPage from './pages/ManagerDashboardPage';

function App() {
    return (
        <Router>
            <nav style={{ padding: '1rem', backgroundColor: '#f0f0f0' }}>
                <Link to="/orders" style={{ marginRight: '1rem' }}>Đơn gọi món</Link>
                <Link to="/reservations" style={{ marginRight: '1rem' }}>Trang đặt bàn</Link>
                <Link to="/managers">Trang quản lý</Link>
            </nav>
            <Routes>
                <Route path="/orders" element={<OrderListPage />} />
                <Route path="/reservations" element={<ReservationPage />} />
                <Route path="/managers" element={<ManagerDashboardPage />} />
            </Routes>
        </Router>
    );
}

export default App;

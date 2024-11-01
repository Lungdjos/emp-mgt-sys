import './App.css';
import ListEmployeeComp from './components/ListEmployeeComp.jsx';
import HeaderComp from './components/HeaderComp.jsx';
import FooterComp from './components/FooterComp.jsx';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

function App() {
  return (
    <Router> {/* Use Router instead of BrowserRouter */}
      <HeaderComp />
      <Routes>
        {/* http://localhost:3001 */}
        <Route path="/" element={<ListEmployeeComp />} />
        {/* http://localhost:3001/employees */}
        <Route path="/employees" element={<ListEmployeeComp />} />
      </Routes>
      <FooterComp />
    </Router>
  );
}

export default App;

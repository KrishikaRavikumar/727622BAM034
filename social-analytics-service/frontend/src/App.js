import React from "react";
import { BrowserRouter as Router, Routes, Route, Link } from "react-router-dom";
import TopUsers from "./components/TopUsers";
import Posts from "./components/Posts";

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-100 p-4">
        <nav className="flex justify-center space-x-4 mb-6">
          <Link to="/users" className="text-blue-500 hover:underline">Top Users</Link>
          <Link to="/posts?type=popular" className="text-blue-500 hover:underline">Popular Posts</Link>
          <Link to="/posts?type=latest" className="text-blue-500 hover:underline">Latest Posts</Link>
        </nav>
        <Routes>
          <Route path="/users" element={<TopUsers />} />
          <Route path="/posts" element={<Posts />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
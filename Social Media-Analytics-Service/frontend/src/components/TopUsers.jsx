import React, { useEffect, useState } from "react";
import axios from "axios";

const TopUsers = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/users")
      .then(res => setUsers(res.data))
      .catch(err => console.error(err));
  }, []);

  return (
    <div className="bg-white p-6 rounded shadow-md max-w-xl mx-auto">
      <h2 className="text-xl font-bold mb-4">Top Users by Comment Count</h2>
      <ul className="space-y-2">
        {users.map(user => (
          <li key={user.id} className="p-2 border-b">{user.name} (ID: {user.id})</li>
        ))}
      </ul>
    </div>
  );
};

export default TopUsers;
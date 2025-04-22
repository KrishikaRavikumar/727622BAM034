import { useEffect, useState } from 'react';
import api from '../api/api';
import UserCard from '../components/UserCard';

const TopUsers = () => {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    api.get('/top-users').then(res => setUsers(res.data));
  }, []);

  return (
    <div>
      <h2 className="text-2xl font-bold mb-4">Top Users</h2>
      <div className="space-y-4">
        {users.map(user => <UserCard key={user.id} user={user} />)}
      </div>
    </div>
  );
};

export default TopUsers;

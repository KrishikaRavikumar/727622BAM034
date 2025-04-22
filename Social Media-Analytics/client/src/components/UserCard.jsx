const UserCard = ({ user }) => (
    <div className="bg-white shadow rounded-xl p-4 flex items-center gap-4">
      <img src={user.image} alt={user.name} className="w-12 h-12 rounded-full" />
      <div>
        <h4 className="text-lg font-semibold">{user.name}</h4>
        <p className="text-sm text-gray-500">Posts: {user.postCount}</p>
      </div>
    </div>
  );
  
  export default UserCard;
  
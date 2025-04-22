import { Link } from 'react-router-dom';

const Navbar = () => {
  return (
    <nav className="bg-white shadow p-4 mb-6">
      <div className="max-w-5xl mx-auto flex gap-6 text-lg font-semibold">
        <Link to="/" className="hover:text-blue-600">Feed</Link>
        <Link to="/top-users" className="hover:text-blue-600">Top Users</Link>
        <Link to="/trending-posts" className="hover:text-blue-600">Trending Posts</Link>
      </div>
    </nav>
  );
};

export default Navbar;
